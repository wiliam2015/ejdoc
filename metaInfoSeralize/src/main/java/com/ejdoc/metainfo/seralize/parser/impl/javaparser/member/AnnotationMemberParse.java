package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.*;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.javadoc.Javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnnotationMemberParse extends AbstractJavaParseMemberParse{

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration) {
        List<JavaMethodMeta> javaFieldMetas = initJavaFieldMetas(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                javaFieldMetas.add(parseMember(member,metaFileInfo,typeDeclaration));
            }
        }
        javaClassMeta.setMethods(javaFieldMetas);
    }

    private List<JavaMethodMeta> initJavaFieldMetas(JavaClassMeta javaClassMeta) {
        if(CollectionUtil.isEmpty(javaClassMeta.getMethods())){
            return new ArrayList<>();
        }
        return javaClassMeta.getMethods();
    }
    private JavaMethodMeta parseMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration) {
        AnnotationMemberDeclaration annotationMemberDeclaration = (AnnotationMemberDeclaration) member;
        JavaMethodMeta javaFieldMeta = new JavaMethodMeta();
        javaFieldMeta.setName(annotationMemberDeclaration.getNameAsString());

        NodeList<Modifier> modifiers = annotationMemberDeclaration.getModifiers();
        JavaClassMeta returnTypeMeta = new JavaClassMeta();
        if(CollectionUtil.isNotEmpty(modifiers)){
            List<String> modifierList = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            returnTypeMeta.setModifiers(modifierList);
        }

        returnTypeMeta.setClassName(annotationMemberDeclaration.getType().asString());
        returnTypeMeta.setFullClassName(annotationMemberDeclaration.getType().asString());
        setFieldResolvedTypeDeclaration(returnTypeMeta,annotationMemberDeclaration.getType());

        javaFieldMeta.setReturns(returnTypeMeta);
        Optional<Expression> defaultValue = annotationMemberDeclaration.getDefaultValue();
        if(defaultValue.isPresent()){
            Expression expression = defaultValue.get();
            JavaClassMeta defaultVal =parseAnnoMethodDefaulVal(expression);
            javaFieldMeta.setDefaultValue(defaultVal);
        }
        Optional<Javadoc> javadoc = annotationMemberDeclaration.getJavadoc();
        NodeList<AnnotationExpr> annotations = annotationMemberDeclaration.getAnnotations();
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        createJavaDocTag(javadoc,javaModelMeta);
        setAnnotations(annotations,javaModelMeta);
        return javaFieldMeta;
    }

    private JavaClassMeta parseAnnoMethodDefaulVal(Expression expression) {
        List<JavaClassMeta> result = new ArrayList<>();
        parseExpression(expression,result);
        if(CollectionUtil.isNotEmpty(result)){
           return result.get(0);
        }
        return null;
    }


    private boolean accept(BodyDeclaration<?> member) {
        return member.isAnnotationMemberDeclaration();
    }
}
