package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.JavaClassImportMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaTypeParameterMeta;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    protected   void setTypeParametersFromDeclaration(NodeList<TypeParameter> typeParameters, JavaClassMeta returnType) {
        if(CollectionUtil.isNotEmpty(typeParameters)){
            List<JavaTypeParameterMeta> typeParametersResult = new ArrayList<>();
            JavaTypeParameterMeta javaTypeParameterMeta = null;
            for (TypeParameter typeParameter : typeParameters) {
                javaTypeParameterMeta = new JavaTypeParameterMeta();
                javaTypeParameterMeta.setName(typeParameter.getNameAsString());

                NodeList<ClassOrInterfaceType> typeBound = typeParameter.getTypeBound();
                if(CollectionUtil.isNotEmpty(typeBound)){
                    for (ClassOrInterfaceType classOrInterfaceType : typeBound) {
                        SimpleName name = classOrInterfaceType.getName();
                        JavaClassMeta type = new JavaClassMeta();
                        type.setClassName(name.getIdentifier());
                        type.setFullClassName(name.getIdentifier());
                        try {
                            setFullClassNameFromResolvedType(type,classOrInterfaceType.resolve());
                        } catch (UnsolvedSymbolException ue){
                            log.debug("setTypeParametersFromDeclaration error",ue);
                            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
                        }
                        javaTypeParameterMeta.setType(type);
                    }
                }
                typeParametersResult.add(javaTypeParameterMeta);
            }
            returnType.setTypeParameters(typeParametersResult);
        }
    }

    /**
     * 循环设置类型参数
     * @param parameter
     * @param paramClass
     */
    protected   void setTypeArgumentsFromType(Type parameter, JavaClassMeta paramClass) {
        if(parameter.isClassOrInterfaceType()){
            ClassOrInterfaceType paramclassOrInterfaceType = parameter.asClassOrInterfaceType();
            paramClass.setClassName(paramclassOrInterfaceType.getName().getId());
            Optional<NodeList<Type>> typeArguments = paramclassOrInterfaceType.getTypeArguments();
            if(typeArguments.isPresent()){
                List<JavaClassMeta> typeArgumentsList = new ArrayList<>();
                JavaClassMeta typeClassMeth = null;
                for (Type type : typeArguments.get()) {
                    ResolvedType resolve = null;

                    typeClassMeth = new JavaClassMeta();
                    typeClassMeth.setClassName(type.asString());
                    try {
                        resolve = type.resolve();
                    }catch(UnsolvedSymbolException ue) {
                        log.debug("setTypeArgumentsFromType type.resolve UnsolvedSymbolException error",ue);
                        UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
                    }catch (Exception e) {
                        log.error("setTypeArgumentsFromType type.resolve error {}",paramClass.getClassName(),e);
                    }
                    setFullClassNameFromResolvedType(typeClassMeth,resolve);


                    ResolvedType typeArgResolve = null;
                    try {
                        if(type.isWildcardType()){
                            WildcardType wildcardType = type.asWildcardType();
                            Optional<ReferenceType> extendedType = wildcardType.getExtendedType();
                            if(extendedType.isPresent()){
                                JavaClassMeta typeArgExtend = new JavaClassMeta();
                                if(extendedType.get().isClassOrInterfaceType()){
                                    typeArgExtend.setClassName(extendedType.get().asClassOrInterfaceType().getNameAsString());
                                    typeArgExtend.setFullClassName(extendedType.get().asClassOrInterfaceType().getNameAsString());
                                }
                                typeArgResolve =extendedType.get().resolve();

                                if(typeArgResolve != null){
                                    setFullClassNameFromResolvedType(typeArgExtend,typeArgResolve);
                                }
                                typeClassMeth.setTypeArgExtend(typeArgExtend);
                            }
                        }
                    } catch(UnsolvedSymbolException ue) {
                        log.debug("setTypeArgumentsFromType type.resolve UnsolvedSymbolException error",ue);
                        UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
                    }catch (Exception e) {
                        log.error("setTypeArgumentsFromType type.resolve error {}",paramClass.getClassName(),e);
                    }



                    typeArgumentsList.add(typeClassMeth);
                    setTypeArgumentsFromType(type,typeClassMeth);
                }
                paramClass.setTypeArguments(typeArgumentsList);
            }
        }
    }
    protected abstract void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext);







}
