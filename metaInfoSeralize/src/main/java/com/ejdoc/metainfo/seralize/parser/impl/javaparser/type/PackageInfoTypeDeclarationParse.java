package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

public class PackageInfoTypeDeclarationParse extends AbstractJavaParserTypeDeclarationParse{
    /**
     * 包名信息
     */
    private static final String PACKAGE_INFO_SOURCE="package-info.java";
    private static final String PACKAGE_INFO="package-info";

    public PackageInfoTypeDeclarationParse(List<JavaParserMemberParse> javaParserMemberParseList) {
        super(javaParserMemberParseList);
    }

    @Override
    protected void doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo,JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration) {
        parseJavaSouce(javaClassMeta, rootAst);
        parseDocAndAnnotation(javaClassMeta, null, rootAst);
        javaClassMeta.setModuleName(metaFileInfo.getModuleName());
        javaClassMeta.setProjectName(metaFileInfo.getProjectName());
        javaClassMeta.setClassName(PACKAGE_INFO);
        javaClassMeta.setValue(PACKAGE_INFO);
        javaClassMeta.setFullClassName(javaClassMeta.getClassNamePrefix()+PACKAGE_INFO);
    }


    @Override
    public boolean accept(TypeDeclaration<?> typeDeclaration,MetaFileInfoDto metaFileInfo) {
        if(PACKAGE_INFO_SOURCE.equals(metaFileInfo.getMetaFileName())){
            return true;
        }
        return false;
    }
}
