package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.github.javaparser.ast.body.TypeDeclaration;

/**
 * javaParser  member成员类型解析器
 */
public interface JavaParserMemberParse {


    /**
     * 将Member解析成JavaClassMeta
     * @param javaClassMeta
     * @param metaFileInfo
     * @param typeDeclaration
     * @return
     */
    void parseMemberToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext);
}
