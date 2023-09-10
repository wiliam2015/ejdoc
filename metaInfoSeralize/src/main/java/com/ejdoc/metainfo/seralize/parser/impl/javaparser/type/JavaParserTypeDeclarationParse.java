package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

/**
 * javaParser  TypeDeclaration类型解析器
 */
public interface JavaParserTypeDeclarationParse {


    /**
     * 是否接受此类型解析
     * @param typeDeclaration
     * @return
     */
    boolean accept(TypeDeclaration<?> typeDeclaration,MetaFileInfoDto metaFileInfo);

    /**
     * 将typeDeclaration解析成JavaClassMeta
     * @param metaFileInfo
     * @param rootAst
     * @param typeDeclaration
     * @return
     */
    List<JavaClassMeta> parseTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext);
}
