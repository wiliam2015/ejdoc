package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaModelMeta;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.javadoc.Javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FieldMemberParse extends AbstractJavaParseMemberParse{

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration) {
        List<JavaFieldMeta> javaFieldMetas = initJavaFieldMetas(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                javaFieldMetas.add(parseFieldMember(member,metaFileInfo,typeDeclaration));
            }
        }
        javaClassMeta.setFields(javaFieldMetas);
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
