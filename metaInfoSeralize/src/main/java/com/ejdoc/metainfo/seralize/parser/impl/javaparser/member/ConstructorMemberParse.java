package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.enums.EnvPropEnum;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ConstructorMemberParse extends MethodMemberParse{
    private static final Logger log = LoggerFactory.getLogger(ConstructorMemberParse.class);

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaConstructorMeta> constructorMetas = new ArrayList<>();
        List<JavaConstructorMeta> result = new ArrayList<>();
        String compileIncludePrivate = javaParserMetaContext.getEnvPropVal(EnvPropEnum.compile_include_private.getCode(), "");
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                JavaConstructorMeta javaConstructorMeta = parseContructorMember(member);
                constructorMetas.add(javaConstructorMeta);

            }
        }
        if(CollectionUtil.isNotEmpty(constructorMetas)){
            for (JavaConstructorMeta constructorMeta : constructorMetas) {
                if(filterModifier(compileIncludePrivate,constructorMeta.getModifiers())){
                    result.add(constructorMeta);
                }
            }
        }else{
            JavaConstructorMeta javaConstructorMeta = new JavaConstructorMeta();
            javaConstructorMeta.setName(javaClassMeta.getClassName());
            javaConstructorMeta.setModifiers(ListUtil.of("public"));
            javaConstructorMeta.setPrivates(false);
            javaConstructorMeta.setPublics(true);
            javaConstructorMeta.setProtecteds(false);
            javaConstructorMeta.setUniqueId(javaClassMeta.getClassName());
            result.add(javaConstructorMeta);
        }

        replaceConstructMethodFullClassRefByImport(javaClassMeta,result);
        paraseConstructTypeParameterFlag(result,javaClassMeta);
        javaClassMeta.setConstructors(result);
    }

    /**
     * 解析方法体对类型参数打标
     * @param methodMetas
     */
    private void paraseConstructTypeParameterFlag(List<JavaConstructorMeta> methodMetas,JavaClassMeta javaClassMeta) {
        boolean isClassTypePara = BooleanUtil.isTrue(javaClassMeta.getTypeParameter());
        Map<String, String> typeParameterMap = new HashMap<>();
        if(isClassTypePara){
            typeParameterMap.putAll(javaClassMeta.getTypeParameters().stream().collect(Collectors.toMap(JavaTypeParameterMeta::getName, JavaTypeParameterMeta::getName)));
        }
        for (JavaConstructorMeta methodMeta : methodMetas) {

            List<JavaParameterMeta> parameters = methodMeta.getParameters();
            if(CollectionUtil.isNotEmpty(parameters)){
                for (JavaParameterMeta parameter : parameters) {
                    JavaClassMeta javaClass = parameter.getJavaClass();
                    if(typeParameterMap.containsKey(javaClass.getClassName())){
                        javaClass.setTypeParameter(true);
                    }
                    List<JavaClassMeta> typeArguments = javaClass.getTypeArguments();
                    setParaseTypeParameterFlag(typeParameterMap, typeArguments);
                    if(CollectionUtil.isNotEmpty(typeArguments)){
                        for (JavaClassMeta typeArgument : typeArguments) {
                            if(typeArgument.getTypeArgExtend() == null){
                                continue;
                            }
                            setParaseTypeParameterFlag(typeParameterMap, ListUtil.of(typeArgument.getTypeArgExtend()));
                        }
                    }
                }
            }
        }
    }

    /**
     * 将解析失败的全名称路径进一步替换成import的
     * @param javaClassMeta
     * @param methodMetas
     */
    private void replaceConstructMethodFullClassRefByImport(JavaClassMeta javaClassMeta, List<JavaConstructorMeta> methodMetas) {
        List<JavaClassMeta> readyReplaceFullClassNameList = new ArrayList<>();
        for (JavaConstructorMeta methodMeta : methodMetas) {
            List<JavaParameterMeta> parameters = methodMeta.getParameters();
            if(CollectionUtil.isNotEmpty(parameters)){
                for (JavaParameterMeta parameter : parameters) {
                    addClassMetaList(readyReplaceFullClassNameList,parameter.getJavaClass());
                }
            }
            List<JavaClassMeta> exceptions = methodMeta.getExceptions();
            if(CollectionUtil.isNotEmpty(exceptions)){
                for (JavaClassMeta exception : exceptions) {
                    addClassMetaList(readyReplaceFullClassNameList,exception);
                }
            }
        }
        replaceFullClassNameByImport(readyReplaceFullClassNameList, javaClassMeta.getImports());
    }
    private JavaConstructorMeta parseContructorMember(BodyDeclaration<?> member) {
        JavaConstructorMeta javaConstructorMeta = new JavaConstructorMeta();
        JavaMethodMeta javaMethodMeta = parseConstructorsMember(member);
        javaConstructorMeta.setName(javaMethodMeta.getName());
        javaConstructorMeta.setJavaModelMeta(javaMethodMeta.getJavaModelMeta());
        javaConstructorMeta.setModifiers(javaMethodMeta.getModifiers());
        javaConstructorMeta.setParameters(javaMethodMeta.getParameters());
        javaConstructorMeta.setExceptions(javaMethodMeta.getExceptions());
        javaConstructorMeta.setCallSignature(javaMethodMeta.getCallSignature());
        javaConstructorMeta.setPrivates(javaMethodMeta.getPrivates());
        javaConstructorMeta.setPublics(javaMethodMeta.getPublics());
        javaConstructorMeta.setProtecteds(javaMethodMeta.getProtecteds());
        javaConstructorMeta.setSourceCode(javaMethodMeta.getSourceCode());
        javaConstructorMeta.setVarArgs(javaMethodMeta.getVarArgs());
        javaConstructorMeta.setUniqueId(javaMethodMeta.getUniqueId());
        return javaConstructorMeta;
    }


    protected JavaMethodMeta parseConstructorsMember(BodyDeclaration<?> member) {
        JavaMethodMeta javaMethodMeta = new JavaMethodMeta();
        ConstructorDeclaration methodDeclaration = (ConstructorDeclaration)member;
        ResolvedConstructorDeclaration resolve = methodDeclaration.resolve();

        Optional<Javadoc> javadoc = methodDeclaration.getJavadoc();
        NodeList<AnnotationExpr> annotations = methodDeclaration.getAnnotations();

        //方法名与方法体
        javaMethodMeta.setName(methodDeclaration.getNameAsString());
        javaMethodMeta.setCallSignature(methodDeclaration.getDeclarationAsString());
        //修饰符
        parseMethodModifiers(javaMethodMeta, methodDeclaration.getModifiers());
        //注解和注释
        parseMethodDocTag(javaMethodMeta, javadoc,annotations);
        //入参与异常
        parseMethodParamAndException(javaMethodMeta, methodDeclaration, resolve);
        //方法唯一ID解析
        parseMethodUniqueId(javaMethodMeta);

        return javaMethodMeta;
    }

    /**
     * 方法唯一ID解析
     * @param javaMethodMeta
     */
    private  void parseMethodUniqueId(JavaMethodMeta javaMethodMeta) {
        StringBuilder uniqueId = new StringBuilder(javaMethodMeta.getName());
        List<JavaParameterMeta> parameters = javaMethodMeta.getParameters();
        if(CollectionUtil.isNotEmpty(parameters)){
            for (JavaParameterMeta parameter : parameters) {
                JavaClassMeta javaClass = parameter.getJavaClass();
                if(javaClass != null){
                    String className = javaClass.getClassName();
                    //数组类型
                    if(StrUtil.isNotBlank(javaClass.getArrayFullClassName())){
                        className = className.replace("[","").replace("]","").trim();
                        className +="-a";
                    }
                    uniqueId.append("-");
                    uniqueId.append(className);
                }
            }
        }
        javaMethodMeta.setUniqueId(uniqueId.toString().toLowerCase());
    }

    private boolean accept(BodyDeclaration<?> member) {
        return member.isConstructorDeclaration();
    }

}
