package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserCreateFactory;
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
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration) {
        List<JavaClassMeta> innerClassList = initJavaClassList(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                innerClassList.add(parseFieldMember(member,metaFileInfo));
            }
        }
        if(CollectionUtil.isNotEmpty(innerClassList)){
            javaClassMeta.setInnerClasses(innerClassList);
        }
    }

    private List<JavaClassMeta> initJavaClassList(JavaClassMeta javaClassMeta) {
        List<JavaClassMeta> innerClasses = javaClassMeta.getInnerClasses();
        if(CollectionUtil.isEmpty(innerClasses)){
            return new ArrayList<>();
        }
        return javaClassMeta.getInnerClasses();
    }

    private JavaClassMeta parseFieldMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo) {
        ClassOrInterfaceDeclaration innerClass = (ClassOrInterfaceDeclaration)member;
        ClassTypeDeclarationParse classTypeDeclarationParse = JavaParserCreateFactory.createDefaultClassTypeDeclarationParse();
        return classTypeDeclarationParse.parseTypeToJavaClassMeta(metaFileInfo,innerClass.findCompilationUnit().get(),innerClass);
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
