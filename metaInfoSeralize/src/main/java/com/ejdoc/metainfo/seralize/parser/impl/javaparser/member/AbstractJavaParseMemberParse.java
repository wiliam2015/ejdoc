package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.JavaClassImportMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJavaParseMemberParse extends BaseJavaParse implements JavaParserMemberParse {
    private static final Logger log = LoggerFactory.getLogger(AbstractJavaParseMemberParse.class);

    @Override
    public void parseMemberToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        doParseMembers(javaClassMeta,metaFileInfo,typeDeclaration,javaParserMetaContext);
    }

    public void doParseMembers(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        if(ObjectUtil.isNull(typeDeclaration)){
            return;
        }

        NodeList<BodyDeclaration<?>> members= typeDeclaration.getMembers();

        if(CollectionUtil.isNotEmpty(members)){
            parseBodyDeclarationToJavaClassMeta(javaClassMeta, metaFileInfo, members, typeDeclaration,javaParserMetaContext);
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


    /**
     * 过滤修饰符
     * @param compileIncludePrivate 是否包含私有属性
     * @param modifiers 修饰符
     * @return
     */
    protected boolean filterModifier(String compileIncludePrivate, List<String> modifiers) {
        if(CollectionUtil.isEmpty(modifiers)){
            return true;
        }
        if(!BooleanUtil.toBoolean(compileIncludePrivate)){
            long noneModifierCount = modifiers.stream().filter(modifier ->
                    StrUtil.equals(modifier, "private")).count();
            if(noneModifierCount > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 根据导入的包替换全路径名称
     * @param javaClassMetaList
     * @param importsClass
     */
   protected void replaceFullClassNameByImport( List<JavaClassMeta> javaClassMetaList,List<JavaClassImportMeta> importsClass){
       if(CollectionUtil.isNotEmpty(javaClassMetaList)){
           for (JavaClassMeta javaClassMeta : javaClassMetaList) {
               if(StrUtil.equals(javaClassMeta.getFullClassName(),javaClassMeta.getClassName())){
                   List<String> importPackageClass = getImportPackageClass(importsClass);
                   if(CollectionUtil.isNotEmpty(importPackageClass)){
                       for (String packageClass : importPackageClass) {
                           if(packageClass.endsWith(javaClassMeta.getClassName())){
                               javaClassMeta.setFullClassName(packageClass);
                           }
                       }
                   }
               }
           }
       }

}
    protected void addClassMetaList(List<JavaClassMeta> classMetaList,JavaClassMeta classMeta){
        if(classMeta != null){
            classMetaList.add(classMeta);
            List<JavaClassMeta> typeArguments = classMeta.getTypeArguments();
            if(CollectionUtil.isNotEmpty(typeArguments)){
                classMetaList.addAll(typeArguments);
            }
        }
    }
    protected List<String> getImportPackageClass(List<JavaClassImportMeta> importsClass){
        List<String> imports = new ArrayList<>();
        //导入子包引入
        for (JavaClassImportMeta importInfo : importsClass) {
            if(!importInfo.getName().startsWith("java")){
                if(importInfo.isAsteriskImport() && !importInfo.isStaticImport()){
                    List<JavaClassMeta> packageClassList = MetaIndexContext.getClassMetaByPackage(importInfo.getName());
                    if(CollectionUtil.isNotEmpty(packageClassList)){
                        for (JavaClassMeta javaClassMeta : packageClassList) {
                            imports.add(javaClassMeta.getFullClassName());
                        }
                    }
                }else{
                    imports.add(importInfo.getName());
                }
            }
        }
        return imports;
    }


    protected abstract void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext);






}
