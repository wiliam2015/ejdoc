package com.ejdoc.metainfo.seralize.parser.impl.javaparser.type;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse;
import com.ejdoc.metainfo.seralize.util.EjdocStrUtil;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractJavaParserTypeDeclarationParse extends BaseJavaParse implements JavaParserTypeDeclarationParse {
    private static final Logger log = LoggerFactory.getLogger(JavaParserMetaInfoParser.class);

    private List<JavaParserMemberParse> javaParserMemberParseList;

    public AbstractJavaParserTypeDeclarationParse(List<JavaParserMemberParse> javaParserMemberParseList){
        this.javaParserMemberParseList = javaParserMemberParseList;
    }
    @Override
    public List<JavaClassMeta> parseTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaClassMeta> result = new ArrayList<>();
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        parseClassMetaProp(javaClassMeta, metaFileInfo, typeDeclaration,javaParserMetaContext);
        parseJavaSouce(javaClassMeta, rootAst,javaParserMetaContext);
        parseDocAndAnnotation(javaClassMeta, typeDeclaration, rootAst,javaParserMetaContext);
        parseNestedJavaClass(javaClassMeta, typeDeclaration, rootAst,javaParserMetaContext);
        parseModifiers(javaClassMeta, typeDeclaration,javaParserMetaContext);
        parseMembers(javaClassMeta, metaFileInfo, typeDeclaration,javaParserMetaContext);
        List<JavaClassMeta> childJavaClassMetas = parseChildTypeToJavaClassMeta(metaFileInfo,javaClassMeta,rootAst,typeDeclaration,javaParserMetaContext);
        parseClassDeclaration(javaClassMeta,javaParserMetaContext);
        parseClassInnerIndex(javaClassMeta);
        result.add(javaClassMeta);
        if(CollectionUtil.isNotEmpty(childJavaClassMetas)){
            result.addAll(childJavaClassMetas);
        }
        return result;
    }

    /**
     * 创建类内部索引
     * @param javaClassMeta 类信息
     */
    private void parseClassInnerIndex(JavaClassMeta javaClassMeta) {
        List<JavaFieldMeta> fields = javaClassMeta.getFields();
        if(CollectionUtil.isNotEmpty(fields)){
            Map<String,String> fieldMetaIndex = new HashMap<>();
            for (JavaFieldMeta meta : fields) {
                fieldMetaIndex.put(meta.getUniqueId(),"true");
            }
            javaClassMeta.setFieldMetaIndex(fieldMetaIndex);
        }
        List<JavaMethodMeta> methods = javaClassMeta.getMethods();
        if(CollectionUtil.isNotEmpty(methods)){
            Map<String,String> methodMetaIndex = new HashMap<>();
            for (JavaMethodMeta meta : methods) {
                methodMetaIndex.put(meta.getUniqueId(),"true");
            }
            javaClassMeta.setMethodMetaIndex(methodMetaIndex);
        }
        List<JavaConstructorMeta> constructors = javaClassMeta.getConstructors();
        if(CollectionUtil.isNotEmpty(constructors)){
            Map<String,String> constructMetaIndex = new HashMap<>();
            for (JavaConstructorMeta meta : constructors) {
                constructMetaIndex.put(meta.getUniqueId(),"true");
            }
            javaClassMeta.setConstructorMetaIndex(constructMetaIndex);
        }
    }

    private  List<JavaClassMeta>  parseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        if(accept(typeDeclaration,metaFileInfo)){
            return doParseChildTypeToJavaClassMeta(metaFileInfo,javaClassMeta,rootAst,typeDeclaration,javaParserMetaContext);
        }
        return null;
    }

    protected  void parseMembers(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext){
        List<JavaParserMemberParse> memberParseList = getJavaParserMemberParseList();
        if(CollectionUtil.isNotEmpty(memberParseList)){
            for (JavaParserMemberParse javaParserMemberParse : memberParseList) {
                javaParserMemberParse.parseMemberToJavaClassMeta(javaClassMeta,metaFileInfo,typeDeclaration,javaParserMetaContext);
            }
        }
    }



    protected abstract List<JavaClassMeta> doParseChildTypeToJavaClassMeta(MetaFileInfoDto metaFileInfo, JavaClassMeta javaClassMeta, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext);

    /**
     * 解析类声明结构
     * @param javaClassMeta
     */
    protected void parseClassDeclaration(JavaClassMeta javaClassMeta,JavaParserMetaContext javaParserMetaContext) {
        javaClassMeta.setDeclarationStructure(javaClassMeta.parseDeclarationStructure());
    }


    protected void parseModifiers(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        NodeList<Modifier> modifiers = ObjectUtil.isNull(typeDeclaration) ?null:typeDeclaration.getModifiers();
        if(CollectionUtil.isNotEmpty(modifiers)){
            List<String> collect = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            javaClassMeta.setModifiers(collect);
        }

    }
    /**
     *  嵌套类解析
     * @param javaClassMeta
     * @param typeDeclaration
     * @param rootAst
     */
    protected void parseNestedJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        if(ObjectUtil.isNotNull(typeDeclaration)){

        }
    }

    protected void parseSuperJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration) {

        if(typeDeclaration.isClassOrInterfaceDeclaration()){
            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) typeDeclaration;
            NodeList<ClassOrInterfaceType> extendedTypes = classOrInterfaceDeclaration.getExtendedTypes();

            if(CollectionUtil.isNotEmpty(extendedTypes)){
                List<JavaClassMeta> superClasses = new ArrayList<>();
                for (ClassOrInterfaceType extendedType : extendedTypes) {
                    superClasses.add(convertClassOrInterfaceTypeToSimpleClassMeta(extendedType,javaClassMeta.getImports()));
                }
                javaClassMeta.setSuperClasses(superClasses);
            }else{
                if(classOrInterfaceDeclaration.isInterface()){
                    return;
                }
                if(classOrInterfaceDeclaration.isEnumDeclaration()){
                    return;
                }
                if(classOrInterfaceDeclaration.isAnnotationDeclaration()){
                    return;
                }
                JavaClassMeta superJavaClassMeta = new JavaClassMeta();
                superJavaClassMeta.setClassName("Object");
                superJavaClassMeta.setFullClassName("java.lang.Object");
                superJavaClassMeta.setPackageName("java.lang");
                superJavaClassMeta.setJdkClass(true);
                superJavaClassMeta.setInterfaceClass(false);
                superJavaClassMeta.setClassNamePrefix("java.lang.");
                superJavaClassMeta.setModifiers(ListUtil.of("public"));
                javaClassMeta.setSuperClasses(ListUtil.of(superJavaClassMeta));
            }
        }
    }

    protected JavaClassMeta convertClassOrInterfaceTypeToSimpleClassMeta(ClassOrInterfaceType implementedType, List<JavaClassImportMeta> imports) {
        if(implementedType == null){
            return null;
        }
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        Optional<NodeList<Type>> typeArguments = implementedType.getTypeArguments();
        if(typeArguments.isPresent()){
            List<JavaClassMeta> typeArgsList = new ArrayList<>();
            for (Type type : typeArguments.get()) {
                JavaClassMeta typeArgs = new JavaClassMeta();
                setTypeArgumentsFromType(type,typeArgs);
                typeArgsList.add(typeArgs);
            }
            javaClassMeta.setTypeArguments(typeArgsList);
        }
        javaClassMeta.setClassName(implementedType.getNameAsString());
        javaClassMeta.setFullClassName(implementedType.getNameAsString());
        ResolvedType resolve = getRefClassResolvedType(implementedType);
        setFullClassNameFromResolvedType(javaClassMeta,resolve);
        if(StrUtil.equals(javaClassMeta.getFullClassName(),javaClassMeta.getClassName())){
            List<String> importPackageClass = getImportPackageClass(imports);
            if(CollectionUtil.isNotEmpty(importPackageClass)){
                for (String packageClass : importPackageClass) {
                    if(packageClass.endsWith(javaClassMeta.getClassName())){
                        javaClassMeta.setFullClassName(packageClass);
                    }
                }
            }
        }
        return javaClassMeta;
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
                    JavaClassMeta typeArgClassMeth = new JavaClassMeta();
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


    protected  ResolvedType getRefClassResolvedType(ClassOrInterfaceType implementedType) {
        try {
            ResolvedType resolve = implementedType.resolve();
            return resolve;
        }catch (UnsolvedSymbolException ue){
            log.debug("getRefClassResolvedType error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
            return null;
        } catch (Exception e) {
            log.error("getRefClassResolvedType error",e);
            return null;
        }
    }

    protected void parseJavaSouce(JavaClassMeta javaClassMeta, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        Optional<PackageDeclaration> packageDeclarationOptional = rootAst.getPackageDeclaration();
        if(packageDeclarationOptional.isPresent()){
            PackageDeclaration packageDeclaration = rootAst.getPackageDeclaration().get();

            CompilationUnit node = rootAst;
            List<JavaClassImportMeta> javaClassImportMetas = new ArrayList<>();
            NodeList<ImportDeclaration> imports = node.getImports();
            if(CollectionUtil.isNotEmpty(imports)){
                JavaClassImportMeta importMeta = null;
                for (ImportDeclaration anImport : imports) {
                    importMeta = new JavaClassImportMeta();
                    importMeta.setName(anImport.getNameAsString());
                    importMeta.setAsteriskImport(anImport.isAsterisk());
                    importMeta.setStaticImport(anImport.isStatic());
                    importMeta.setPhantom(anImport.isPhantom());
                    javaClassImportMetas.add(importMeta);
                }
            }
            javaClassMeta.setImports(javaClassImportMetas);

//            try {
//                URL url = node.getStorage().get().getPath().toUri().toURL();
//                String path = url.getPath();
//                javaClassMeta.setUrl(url);
//                javaClassMeta.setAbsolutePath(node.getStorage().get().getPath().toFile().getAbsolutePath());
//                javaClassMeta.setRelativePath(path.substring(path.indexOf("src")));
//            } catch (MalformedURLException e) {
//            }

            javaClassMeta.setClassNamePrefix(packageDeclaration.getNameAsString()+".");
            javaClassMeta.setPackageName(packageDeclaration.getNameAsString());
        }
    }
    protected void parseDocAndAnnotation(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst,JavaParserMetaContext javaParserMetaContext) {
        if(typeDeclaration != null){
            Optional<Javadoc> javadoc = typeDeclaration.getJavadoc();
            NodeList<AnnotationExpr> annotations = typeDeclaration.getAnnotations();
            JavaModelMeta javaModelMeta = new JavaModelMeta();
            createJavaDocTag(javadoc,javaModelMeta);
            setAnnotations(annotations, javaModelMeta);
            javaClassMeta.setJavaModelMeta(javaModelMeta);
            javaClassMeta.setClassDesc(getClassDesc(javaModelMeta));

        }else{
            if(ObjectUtil.isNotNull(rootAst)){
                Optional<Comment> comment = rootAst.getComment();
                if(comment.isPresent()){
                    Comment docComment = comment.get();
                    if(docComment instanceof JavadocComment){
                        JavadocComment javadocComment =(JavadocComment)docComment;
                        Javadoc javadoc = javadocComment.parse();
                        JavaModelMeta javaModelMeta = new JavaModelMeta();
                        createJavaDocTag(Optional.ofNullable(javadoc),javaModelMeta);
                        javaClassMeta.setJavaModelMeta(javaModelMeta);
                        javaClassMeta.setClassDesc(getClassDesc(javaModelMeta));
                    }else{
                        JavaModelMeta javaModelMeta = new JavaModelMeta();
                        javaModelMeta.setComment(comment.get().getContent());
                        javaClassMeta.setJavaModelMeta(javaModelMeta);
                        javaClassMeta.setClassDesc(getClassDesc(javaModelMeta));
                    }
                }
            }
        }
    }

    private String getClassDesc(JavaModelMeta javaModelMeta) {
        String classDesc = "";
        JavaDocCommentMeta javaDocComment = javaModelMeta.getJavaDocComment();
        if(javaDocComment != null){
            String javaDocData = javaDocComment.getJavaDocComment();
            if(javaDocData.length() > 0){
                classDesc = EjdocStrUtil.getFirstComment(javaDocData);
            }
        }
        return classDesc.replace("\n","");
    }


    /**
     * 解析class基本信息
     *
     * @param javaClassMeta
     * @param metaFileInfoDto
     * @param typeDeclaration
     */
    protected void parseClassMetaProp(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto,TypeDeclaration<?> typeDeclaration,JavaParserMetaContext javaParserMetaContext) {
        javaClassMeta.setModuleName(metaFileInfoDto.getModuleName());
        javaClassMeta.setModuleDesc(metaFileInfoDto.getModuleDesc());
        javaClassMeta.setProjectName(metaFileInfoDto.getProjectName());
        if(typeDeclaration != null){
            SimpleName simpleName = typeDeclaration.getName();
            javaClassMeta.setClassName(simpleName.getIdentifier());
            javaClassMeta.setFullClassName(typeDeclaration.getFullyQualifiedName().get());
            javaClassMeta.setValue(simpleName.getIdentifier());
            javaClassMeta.setNestedClass(typeDeclaration.isNestedType());

            if(typeDeclaration.isPrivate()){
                javaClassMeta.setPrimitiveClass(typeDeclaration.isPrivate());
            }
            if(typeDeclaration.isPrivate()){
                javaClassMeta.setPrivateClass(typeDeclaration.isPrivate());
            }
            if(typeDeclaration.isProtected()){
                javaClassMeta.setProtectedClass(typeDeclaration.isProtected());
            }
            if(typeDeclaration.isPublic()){
                javaClassMeta.setPublicClass(typeDeclaration.isPublic());
            }
            if(typeDeclaration.isAnnotationDeclaration()){
                javaClassMeta.setAnnotationClass(typeDeclaration.isAnnotationDeclaration());
            }
            if(typeDeclaration.isEnumDeclaration()){
                javaClassMeta.setEnumClass(typeDeclaration.isEnumDeclaration());
            }
            if(typeDeclaration.isStatic()){
                javaClassMeta.setStaticClass(typeDeclaration.isStatic());
            }
            if(typeDeclaration.isEmpty()){
                javaClassMeta.setVoidClass(typeDeclaration.isEmpty());
            }

            if(typeDeclaration.isClassOrInterfaceDeclaration()){
                ClassOrInterfaceDeclaration javaClass = (ClassOrInterfaceDeclaration)typeDeclaration;
                NodeList<TypeParameter> typeParameters = javaClass.getTypeParameters();
                if(CollectionUtil.isNotEmpty(typeParameters)){
                    setTypeParametersFromDeclaration(typeParameters,javaClassMeta);
                    javaClassMeta.setTypeParameter(true);
                }

                if(javaClass.isAbstract()){
                    javaClassMeta.setAbstractClass(javaClass.isAbstract());
                }
                if(javaClass.isFinal()){
                    javaClassMeta.setFinalClass(javaClass.isFinal());
                }
                if(javaClass.isInterface()){
                    javaClassMeta.setInterfaceClass(javaClass.isInterface());
                }
                if(javaClass.isInnerClass()){
                    javaClassMeta.setInnerClass(javaClass.isInnerClass());
                }
            }
        }
    }

    protected  void setTypeParametersFromDeclaration(NodeList<TypeParameter> typeParameters, JavaClassMeta javaClassMeta) {
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
                        Optional<NodeList<Type>> typeArguments = classOrInterfaceType.getTypeArguments();
                        if(typeArguments.isPresent()){
                            List<JavaClassMeta> typeArgs = new ArrayList<>();
                            for (Type typeArg : typeArguments.get()) {
                                JavaClassMeta classMeta = new JavaClassMeta();
                                setTypeArgumentsFromType(typeArg,classMeta);
                                typeArgs.add(classMeta);
                            }
                            type.setTypeArguments(typeArgs);
                        }

                        javaTypeParameterMeta.setType(type);
                    }
                }
                typeParametersResult.add(javaTypeParameterMeta);
            }
            javaClassMeta.setTypeParameters(typeParametersResult);
        }
    }
    public List<JavaParserMemberParse> getJavaParserMemberParseList() {
        return javaParserMemberParseList;
    }

    public void setJavaParserMemberParseList(List<JavaParserMemberParse> javaParserMemberParseList) {
        this.javaParserMemberParseList = javaParserMemberParseList;
    }
}
