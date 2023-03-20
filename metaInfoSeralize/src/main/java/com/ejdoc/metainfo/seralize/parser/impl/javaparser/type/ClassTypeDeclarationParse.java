package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;

public class ClassTypeDeclarationParse extends AbstractJavaParserTypeDeclarationParse{

    /**
     * 包名信息
     */
    private static final String PACKAGE_INFO_SOURCE="package-info.java";
    public ClassTypeDeclarationParse(List<JavaParserMemberParse> javaParserMemberParseList) {
        super(javaParserMemberParseList);
    }

    @Override
    protected void doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo,JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration) {
        parseSuperJavaClass(javaClassMeta, typeDeclaration);
        parseInterfaces(javaClassMeta, typeDeclaration);
    }

    protected void parseInterfaces(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration) {

        ClassOrInterfaceDeclaration declaration=(ClassOrInterfaceDeclaration)typeDeclaration;
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
        //不解析包信息
        if(ObjectUtil.isNotNull(metaFileInfo) && PACKAGE_INFO_SOURCE.equals(metaFileInfo.getMetaFileName())){
            return false;
        }
        return ObjectUtil.isNotNull(typeDeclaration) && typeDeclaration.isClassOrInterfaceDeclaration();
    }
}
