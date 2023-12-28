package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.enums.EnvPropEnum;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.javadoc.Javadoc;

import java.util.*;
import java.util.stream.Collectors;

public class FieldMemberParse extends AbstractJavaParseMemberParse{

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaFieldMeta> javaFieldMetas = initJavaFieldMetas(javaClassMeta);
        String compileIncludePrivate = javaParserMetaContext.getEnvPropVal(EnvPropEnum.compile_include_private.getCode(), "");
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                JavaFieldMeta javaFieldMeta = parseFieldMember(member, metaFileInfo, typeDeclaration);
                if(filterModifier(compileIncludePrivate,javaFieldMeta.getModifiers())){
                    javaFieldMetas.add(javaFieldMeta);
                }
            }
        }
        List<JavaFieldMeta> fieldMetas = CollectionUtil.sortByProperty(javaFieldMetas, "name");
        replaceFieldFullClassRefByImport(javaClassMeta,javaFieldMetas);
        paraseTypeParameterFlag(javaFieldMetas,javaClassMeta);
        javaClassMeta.setFields(fieldMetas);
    }

    private void paraseTypeParameterFlag(List<JavaFieldMeta> javaFieldMetas, JavaClassMeta javaClassMeta) {
        boolean isClassTypePara = BooleanUtil.isTrue(javaClassMeta.getTypeParameter());
        Map<String, String> typeParameterMap = new HashMap<>();
        if(isClassTypePara){
            typeParameterMap.putAll(javaClassMeta.getTypeParameters().stream().collect(Collectors.toMap(JavaTypeParameterMeta::getName, JavaTypeParameterMeta::getName)));
        }
        for (JavaFieldMeta javaFieldMeta : javaFieldMetas) {
            JavaClassMeta returns = javaFieldMeta.getType();
            if(returns == null){
                continue;
            }

            if(CollectionUtil.isNotEmpty(returns.getTypeParameters())){
                returns.setTypeParameter(true);
                typeParameterMap.putAll(returns.getTypeParameters().stream().collect(Collectors.toMap(JavaTypeParameterMeta::getName, JavaTypeParameterMeta::getName)));
            }
            if(typeParameterMap.containsKey(returns.getClassName())){
                returns.setTypeParameter(true);
            }
            List<JavaClassMeta> returnTypeArguments = returns.getTypeArguments();
            if(CollectionUtil.isNotEmpty(returnTypeArguments)){
                for (JavaClassMeta typeArgument : returnTypeArguments) {
                    if(typeParameterMap.containsKey(typeArgument.getClassName())){
                        typeArgument.setTypeParameter(true);
                    }
                }
            }
        }
    }


    /**
     * 将解析失败的全名称路径进一步替换成import的
     * @param javaClassMeta
     */
    private void replaceFieldFullClassRefByImport(JavaClassMeta javaClassMeta, List<JavaFieldMeta> javaFieldMetas) {
        List<JavaClassMeta> readyReplaceFullClassNameList = new ArrayList<>();
        for (JavaFieldMeta methodMeta : javaFieldMetas) {
            addClassMetaList(readyReplaceFullClassNameList,methodMeta.getType());
        }
        replaceFullClassNameByImport(readyReplaceFullClassNameList, javaClassMeta.getImports());
    }
    private JavaFieldMeta parseFieldMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration) {

        JavaFieldMeta fieldMeta = new JavaFieldMeta();

        FieldDeclaration fieldDeclaration = (FieldDeclaration)member;
        Optional<Javadoc> javadoc = fieldDeclaration.getJavadoc();

        NodeList<Modifier> modifiers = fieldDeclaration.getModifiers();
        VariableDeclarator variable = fieldDeclaration.getVariable(0);

        JavaModelMeta javaModelMeta = new JavaModelMeta();
        javaModelMeta.setCodeBlock(fieldDeclaration.getTokenRange().get().toString());
        javaModelMeta.setLineNumber(fieldDeclaration.getBegin().get().line);

        createJavaDocTag(javadoc,javaModelMeta);

        List<String> modifierList = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
        fieldMeta.setModifiers(modifierList);

        JavaClassMeta returnTypeMeta = new JavaClassMeta();
        returnTypeMeta.setClassName(variable.getType().asString());
        returnTypeMeta.setFullClassName(variable.getType().asString());
        setFieldResolvedTypeDeclaration(returnTypeMeta,variable.getType());



        fieldMeta.setJavaModelMeta(javaModelMeta);
        fieldMeta.setType(returnTypeMeta);
        Optional<Expression> initializer = variable.getInitializer();
        initializer.ifPresent(expression -> fieldMeta.setInitializer(expression.toString()));
        fieldMeta.setName(variable.getNameAsString());
        fieldMeta.setInitializationExpression(variable.getTokenRange().get().toString());
        fieldMeta.setUniqueId(fieldMeta.getName().toLowerCase());
        return fieldMeta;
    }

    private List<JavaFieldMeta> initJavaFieldMetas(JavaClassMeta javaClassMeta) {
        if(CollectionUtil.isEmpty(javaClassMeta.getFields())){
            return new ArrayList<>();
        }
        return javaClassMeta.getFields();
    }

    private boolean accept(BodyDeclaration<?> member) {
        return member.isFieldDeclaration();
    }

}
