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

public class InnerClassMemberParse extends AbstractJavaParseMemberParse{


    public InnerClassMemberParse(){
    }
    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaClassMeta> innerClassList = initJavaClassList(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                innerClassList.addAll(parseFieldMember(member,metaFileInfo,javaParserMetaContext));
            }
        }
        if(CollectionUtil.isNotEmpty(innerClassList)){
            parseInnerClassInfo(innerClassList);
            javaClassMeta.setInnerClasses(innerClassList);
        }
    }

    private void parseInnerClassInfo(List<JavaClassMeta> innerClassList) {
        //设置className
        for (JavaClassMeta javaClassMeta : innerClassList) {
            String fullClassName = javaClassMeta.getFullClassName();
            String classNamePrefix = javaClassMeta.getClassNamePrefix();
            String className = fullClassName.substring(classNamePrefix.length());
            javaClassMeta.setClassName(className);
        }
    }

    private List<JavaClassMeta> initJavaClassList(JavaClassMeta javaClassMeta) {
        List<JavaClassMeta> innerClasses = javaClassMeta.getInnerClasses();
        if(CollectionUtil.isEmpty(innerClasses)){
            return new ArrayList<>();
        }
        return javaClassMeta.getInnerClasses();
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
            return innerClass.isInnerClass();
        }
        return false;
    }
}
