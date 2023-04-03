package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserCreateFactory;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.ClassTypeDeclarationParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.EnumTypeDeclarationParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

public class EnumMemberParse extends AbstractJavaParseMemberParse{


    public EnumMemberParse(){
    }
    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaClassMeta> javaFieldMetas = initJavaFieldMetas(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                javaFieldMetas.add(parseFieldMember(member,metaFileInfo,typeDeclaration,javaParserMetaContext));
            }
        }
        List<JavaClassMeta> javaClassMetaList = CollectionUtil.sortByProperty(javaFieldMetas, "className");
        javaClassMeta.setEnumConstants(javaClassMetaList);
    }

    private List<JavaClassMeta> initJavaFieldMetas(JavaClassMeta javaClassMeta) {
        if(CollectionUtil.isEmpty(javaClassMeta.getEnumConstants())){
            return new ArrayList<>();
        }
        return javaClassMeta.getEnumConstants();
    }

    private JavaClassMeta parseFieldMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        EnumDeclaration enumDeclaration = (EnumDeclaration) member;
        CompilationUnit compilationUnit = (CompilationUnit)typeDeclaration.getParentNode().get();
        EnumTypeDeclarationParse enumTypeDeclarationParse = JavaParserCreateFactory.createDefaultEnumTypeDeclarationParse();
        return enumTypeDeclarationParse.parseTypeToJavaClassMeta(metaFileInfo,compilationUnit,enumDeclaration,javaParserMetaContext);
    }

    private boolean accept(BodyDeclaration<?> member) {
        return member.isEnumDeclaration();
    }
}
