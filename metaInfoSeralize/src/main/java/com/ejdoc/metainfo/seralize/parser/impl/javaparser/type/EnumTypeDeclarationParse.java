package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaModelMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.javadoc.Javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnumTypeDeclarationParse extends AbstractJavaParserTypeDeclarationParse{


    public EnumTypeDeclarationParse(List<JavaParserMemberParse> javaParserMemberParseList) {
        super(javaParserMemberParseList);
    }

    @Override
    protected void doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo,JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration) {
        if(typeDeclaration.isEnumDeclaration()){
            parseInterfaces(javaClassMeta, typeDeclaration);

            parseEnumEntry(javaClassMeta, (EnumDeclaration) typeDeclaration);
        }
    }

    private void parseEnumEntry(JavaClassMeta javaClassMeta, EnumDeclaration typeDeclaration) {
        EnumDeclaration declaration = typeDeclaration;
        NodeList<EnumConstantDeclaration> entries = declaration.getEntries();
        if(CollectionUtil.isNotEmpty(entries)){
            List<JavaClassMeta> enumEntries = new ArrayList<>();
            for (EnumConstantDeclaration entry : entries) {
                JavaClassMeta enumEntry = parseEnumEntry(entry, javaClassMeta);
                if(enumEntry != null){
                    enumEntries.add(enumEntry);
                }
            }
            javaClassMeta.setEnumConstants(enumEntries);
        }
    }


    protected void parseInterfaces(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration) {
        EnumDeclaration declaration=(EnumDeclaration)typeDeclaration;
        NodeList<ClassOrInterfaceType> implementedTypes = declaration.getImplementedTypes();
        if(CollectionUtil.isNotEmpty(implementedTypes)){
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();
            for (ClassOrInterfaceType implementedType : implementedTypes) {
                javaClassMetaList.add(convertClassOrInterfaceTypeToSimpleClassMeta(implementedType));
                javaClassMeta.setInterfaces(javaClassMetaList);
            }
        }
    }
    @Override
    public boolean accept(TypeDeclaration<?> typeDeclaration,MetaFileInfoDto metaFileInfo) {

        return ObjectUtil.isNotNull(typeDeclaration) && typeDeclaration.isEnumDeclaration();
    }

    private JavaClassMeta parseEnumEntry(EnumConstantDeclaration entry, JavaClassMeta javaClassMeta) {
        Optional<Javadoc> javadoc = entry.getJavadoc();
        SimpleName name = entry.getName();
        NodeList<Expression> arguments = entry.getArguments();
        NodeList<AnnotationExpr> annotations = entry.getAnnotations();
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        createJavaDocTag(javadoc,javaModelMeta);

        if(CollectionUtil.isNotEmpty(arguments)){
            for (Expression argument : arguments) {
            }
        }
        JavaClassMeta fieldMeta = new JavaClassMeta();
        fieldMeta.setJavaModelMeta(javaModelMeta);
        fieldMeta.setClassName(name.getIdentifier());
        return fieldMeta;
    }
}
