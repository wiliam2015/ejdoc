package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;
import com.ejdoc.metainfo.seralize.model.JavaParameterMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractJavaParseMemberParse extends BaseJavaParse implements JavaParserMemberParse {
    private static final Logger log = LoggerFactory.getLogger(AbstractJavaParseMemberParse.class);

    @Override
    public void parseMemberToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration) {
        doParseMembers(javaClassMeta,metaFileInfo,typeDeclaration);
    }

    public void doParseMembers(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration) {
        if(ObjectUtil.isNull(typeDeclaration)){
            return;
        }

        NodeList<BodyDeclaration<?>> members= typeDeclaration.getMembers();

        if(CollectionUtil.isNotEmpty(members)){
            parseBodyDeclarationToJavaClassMeta(javaClassMeta, metaFileInfo, members, typeDeclaration);
//            log.warn("not support BodyDeclaration");
        }

//        List<JavaFieldMeta> javaFieldMetas = new ArrayList<>();
//        List<JavaMethodMeta> javaMethodMetas = new ArrayList<>();
//        for (BodyDeclaration<?> member : members) {
//
////            JavaFieldMeta javaFieldMeta = parseField(member, javaClassMeta);
////            if(javaFieldMeta != null){
////                javaFieldMetas.add(javaFieldMeta);
////                continue;
////            }
////            JavaMethodMeta javaMethodMeta = parseMethod(member, javaClassMeta);
////            if(javaMethodMeta != null){
////                javaMethodMetas.add(javaMethodMeta);
////                continue;
////            }
//            if(member.isClassOrInterfaceDeclaration()){
//                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) member;
////                parseTypeToJavaClassMeta(metaFileInfo,classOrInterfaceDeclaration.findCompilationUnit().get(),classOrInterfaceDeclaration);
//                continue;
//            }
//            log.warn("not support BodyDeclaration:{}",member);
//        }
//
//        javaClassMeta.setFields(javaFieldMetas);
//        javaClassMeta.setMethods(javaMethodMetas);

    }

    protected abstract void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration);






}
