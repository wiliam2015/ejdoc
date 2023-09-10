package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserCreateFactory;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.ClassTypeDeclarationParse;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * 嵌套类解析
 */
public class NestedClassMemberParse extends AbstractJavaParseMemberParse{


    public NestedClassMemberParse(){
    }
    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaClassMeta> nestedClasses = initJavaClassList(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                nestedClasses.addAll(parseFieldMember(member,metaFileInfo,javaParserMetaContext));
            }
        }
        if(CollectionUtil.isNotEmpty(nestedClasses)){
            parseNestedClassInfo(nestedClasses);
            javaClassMeta.setNestedClasses(nestedClasses);
        }
    }

    private void parseNestedClassInfo(List<JavaClassMeta> innerClassList) {
        //设置className
        for (JavaClassMeta javaClassMeta : innerClassList) {
            String fullClassName = javaClassMeta.getFullClassName();
            String classNamePrefix = javaClassMeta.getClassNamePrefix();
            String className = fullClassName.substring(classNamePrefix.length());
            javaClassMeta.setClassName(className);
        }
    }
    private List<JavaClassMeta> initJavaClassList(JavaClassMeta javaClassMeta) {
        List<JavaClassMeta> nestedClasses = javaClassMeta.getNestedClasses();
        if(CollectionUtil.isEmpty(nestedClasses)){
            return new ArrayList<>();
        }
        return javaClassMeta.getNestedClasses();
    }

    private List<JavaClassMeta> parseFieldMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo,JavaParserMetaContext javaParserMetaContext) {
        ClassOrInterfaceDeclaration innerClass = (ClassOrInterfaceDeclaration)member;
        ClassTypeDeclarationParse classTypeDeclarationParse = JavaParserCreateFactory.createDefaultClassTypeDeclarationParse();
        return classTypeDeclarationParse.parseTypeToJavaClassMeta(metaFileInfo,innerClass.findCompilationUnit().get(),innerClass,javaParserMetaContext);
    }

    private boolean accept(BodyDeclaration<?> member) {
        boolean classOrInterfaceDeclaration = member.isClassOrInterfaceDeclaration();
        if(classOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration innerClass = (ClassOrInterfaceDeclaration)member;
            return innerClass.isNestedType() && !innerClass.isInnerClass();
        }
        return false;
    }
}
