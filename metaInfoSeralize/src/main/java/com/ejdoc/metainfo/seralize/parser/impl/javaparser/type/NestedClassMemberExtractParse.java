package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * 嵌套类抽取
 */
public class NestedClassMemberExtractParse extends AbstractJavaParserTypeDeclarationParse{

    /**
     * 包名信息
     */
    private static final String PACKAGE_INFO_SOURCE="package-info.java";
    public NestedClassMemberExtractParse(List<JavaParserMemberParse> javaParserMemberParseList) {
        super(javaParserMemberParseList);
    }

    @Override
    protected List<JavaClassMeta> doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        parseSuperJavaClass(javaClassMeta, typeDeclaration);
        parseInterfaces(javaClassMeta, typeDeclaration);

        List<JavaClassMeta> result = new ArrayList<>();
        List<JavaClassMeta> nestedClasses = javaClassMeta.getNestedClasses();
        if(CollectionUtil.isNotEmpty(nestedClasses)){
            for (JavaClassMeta nestedClass : nestedClasses) {
                nestedClass.setNestedClass(true);
                nestedClass.setNestedClassFullClassName(javaClassMeta.getFullClassName());
                nestedClass.setNestedClassName(javaClassMeta.getClassName());
                result.add(nestedClass);
                result.addAll(this.doParseChildTypeToJavaClassMeta(metaFileInfo, nestedClass, rootAst, typeDeclaration, javaParserMetaContext));
            }
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getInnerClasses())){
            for (JavaClassMeta innerClass : javaClassMeta.getInnerClasses()) {
                innerClass.setInnerClass(true);
                innerClass.setNestedClassFullClassName(javaClassMeta.getFullClassName());
                innerClass.setNestedClassName(javaClassMeta.getClassName());
                result.add(innerClass);
                result.addAll(this.doParseChildTypeToJavaClassMeta(metaFileInfo,innerClass,rootAst,typeDeclaration,javaParserMetaContext));
            }
        }
        return result;
    }

    protected void parseInterfaces(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration) {

        ClassOrInterfaceDeclaration declaration=(ClassOrInterfaceDeclaration)typeDeclaration;
        NodeList<ClassOrInterfaceType> implementedTypes = declaration.getImplementedTypes();
        if(CollectionUtil.isNotEmpty(implementedTypes)){
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();
            for (ClassOrInterfaceType implementedType : implementedTypes) {
                javaClassMetaList.add(convertClassOrInterfaceTypeToSimpleClassMeta(implementedType,javaClassMeta.getImports()));
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
        if(typeDeclaration == null || !typeDeclaration.isClassOrInterfaceDeclaration()){
            return false;
        }
        NodeList<BodyDeclaration<?>> members = typeDeclaration.getMembers();
        if(CollectionUtil.isNotEmpty(members)){
            for (BodyDeclaration<?> member : members) {
                if(isNestedClass(member)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNestedClass(BodyDeclaration<?> member) {
        boolean classOrInterfaceDeclaration = member.isClassOrInterfaceDeclaration();
        if(classOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration innerClass = (ClassOrInterfaceDeclaration)member;
            return innerClass.isNestedType();
        }
        return false;
    }
}
