package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.enums.EnvPropEnum;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaConstructorMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;
import com.ejdoc.metainfo.seralize.model.JavaParameterMeta;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConstructorMemberParse extends MethodMemberParse{
    private static final Logger log = LoggerFactory.getLogger(ConstructorMemberParse.class);

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaConstructorMeta> constructorMetas = new ArrayList<>();
        String compileIncludePrivate = javaParserMetaContext.getEnvPropVal(EnvPropEnum.compile_include_private.getCode(), "");
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                JavaConstructorMeta javaConstructorMeta = parseContructorMember(member);
                if(filterModifier(compileIncludePrivate,javaConstructorMeta.getModifiers())){
                    constructorMetas.add(javaConstructorMeta);
                }
            }
        }
        javaClassMeta.setConstructors(constructorMetas);
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
                    uniqueId.append("-");
                    uniqueId.append(javaClass.getClassName());
                }
            }
        }
        javaMethodMeta.setUniqueId(uniqueId.toString().toLowerCase());
    }

    private boolean accept(BodyDeclaration<?> member) {
        return member.isConstructorDeclaration();
    }

}
