package com.ejdoc.metainfo.seralize.paraser.impl.javaparaser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.resolution.declarations.*;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.ejdoc.metainfo.seralize.paraser.impl.AbstractMetaInfoParaser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

public class JavaParaserMetaInfoParaser extends AbstractMetaInfoParaser {
    private static final Logger log = LoggerFactory.getLogger(JavaParaserMetaInfoParaser.class);

    private Set<String> srcDirCache = new HashSet<>();

//    private JavaParaser
    private JavaParser javaParser;

    public JavaParaserMetaInfoParaser() {
        super();
//        this.javaParser = new JavaParser();
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        CombinedTypeSolver combinedTypeSolverNewCode = new CombinedTypeSolver();
        combinedTypeSolverNewCode.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration();
        parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
        parserConfiguration.setStoreTokens(false);
        parserConfiguration.setAttributeComments(false);
        parserConfiguration.setLexicalPreservationEnabled(false);
        if(CollectionUtil.isNotEmpty(metaFileInfos)){
            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                String parent = metaFileInfo.getMetaFile().getParent();
                if(!srcDirCache.contains(parent)){
                    combinedTypeSolverNewCode.add(new JavaParserTypeSolver(parent, parserConfiguration));
                    srcDirCache.add(parent);
                }

            }

        }
        this.javaParser = createParserWithResolver(combinedTypeSolverNewCode);
    }

    public JavaParaserMetaInfoParaser(MetaFileRead metaFileRead) {
        super(metaFileRead);
//        this.javaParser = new JavaParser();

        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        CombinedTypeSolver combinedTypeSolverNewCode = new CombinedTypeSolver();
        combinedTypeSolverNewCode.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration();
        parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
        parserConfiguration.setStoreTokens(false);
        parserConfiguration.setAttributeComments(false);
        parserConfiguration.setLexicalPreservationEnabled(false);
        if(CollectionUtil.isNotEmpty(metaFileInfos)){
            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                String parent = metaFileInfo.getMetaFile().getParent();
                if(!srcDirCache.contains(parent)){
                    combinedTypeSolverNewCode.add(new JavaParserTypeSolver(parent, parserConfiguration));
                    srcDirCache.add(parent);
                }

            }

        }
        this.javaParser = createParserWithResolver(combinedTypeSolverNewCode);
    }

    @Override
    public List<JavaModuleMeta> parseAllJavaModuletMeta() {
        List<JavaModuleMeta> javaModuleMetas = new ArrayList<>();
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        try {
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();

            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {

                processJavaClassMeta( metaFileInfo,javaClassMetaList);

            }

            javaModuleMetas =  groupJavaClassMetaByModule(javaClassMetaList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return javaModuleMetas;

    }

    /**
     * 因为解析的是注释文件，在java文件中，有注释的地方是
     * 1.文件头；2.成员变量；3.成员变量实体；4.方法；5.方法入参；6.方法入参实体类
     * @param metaFileInfo
     * @param javaClassMetaList
     * @throws FileNotFoundException
     */
    private void processJavaClassMeta(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList) throws FileNotFoundException {
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        Optional<CompilationUnit> result = this.javaParser.parse(metaFileInfo.getMetaFile()).getResult();

        //抽象语法树的根节点
        CompilationUnit rootAst = result.get();

        NodeList<TypeDeclaration<?>> classTypeDataList = rootAst.getTypes();
        if(CollectionUtil.isNotEmpty(classTypeDataList)){
            boolean addMeta = false;
            for (TypeDeclaration<?> typeDeclaration : classTypeDataList) {
                if(typeDeclaration instanceof ClassOrInterfaceDeclaration){
                    addMeta = true;
                    ClassOrInterfaceDeclaration declaration = (ClassOrInterfaceDeclaration)typeDeclaration;
                    paraseClassMetaProp(javaClassMeta,metaFileInfo,declaration,rootAst);
                    paraseJavaSouce(javaClassMeta,metaFileInfo,declaration,rootAst);
                    paraseSuperJavaClass(javaClassMeta,declaration,rootAst);
                    paraseModifiers(javaClassMeta,declaration,rootAst);
                    paraseInterfaces(javaClassMeta,declaration,rootAst);
                    paraseMembers(javaClassMeta,declaration,rootAst);

                }
            }
            if(addMeta){
                javaClassMetaList.add(javaClassMeta);
            }
        }

    }

    protected JavaParser createParserWithResolver(TypeSolver typeSolver) {
        return new JavaParser(new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver)));
    }
    private void paraseMembers(JavaClassMeta javaClassMeta, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {
        NodeList<BodyDeclaration<?>> members = javaClass.getMembers();
        List<JavaFieldMeta> javaFieldMetas = new ArrayList<>();
        List<JavaMethodMeta> javaMethodMetas = new ArrayList<>();
        for (BodyDeclaration<?> member : members) {
            JavaFieldMeta javaFieldMeta = paraseField(member, javaClassMeta);
            if(javaFieldMeta != null){
                javaFieldMetas.add(javaFieldMeta);
            }
            JavaMethodMeta javaMethodMeta = paraseMethod(member, javaClassMeta);
            if(javaMethodMeta != null){
                javaMethodMetas.add(javaMethodMeta);
            }
        }

        javaClassMeta.setFields(javaFieldMetas);
        javaClassMeta.setMethods(javaMethodMetas);

    }

    private JavaMethodMeta paraseMethod(BodyDeclaration<?> member, JavaClassMeta javaClassMeta) {

        if(member instanceof  MethodDeclaration){
            JavaMethodMeta javaMethodMeta = new JavaMethodMeta();
            MethodDeclaration methodDeclaration = (MethodDeclaration)member;
            ResolvedMethodDeclaration resolve = methodDeclaration.resolve();
            ResolvedType returnTypeDeclaration = getMethodReturnResolvedType(resolve);
            String name = resolve.getName();
            Optional<Javadoc> javadoc = methodDeclaration.getJavadoc();
            Optional<JavadocComment> javadocComment = methodDeclaration.getJavadocComment();

            //方法名与方法体
            String nameAsString = methodDeclaration.getNameAsString();
            if(nameAsString.equals("create")){
                methodDeclaration.getNameAsString();
            }
            javaMethodMeta.setName(methodDeclaration.getNameAsString());
            javaMethodMeta.setCallSignature(methodDeclaration.getDeclarationAsString());

            //返回类型
            JavaTypeMeta returnType = new JavaTypeMeta();
            Type type = methodDeclaration.getType();
//            returnType.setBinaryName(type.toString());
            returnType.setFullyQualifiedName(type.toString());
            if(returnTypeDeclaration != null){
                returnType.setFullyQualifiedName(returnTypeDeclaration.describe());
            }
            returnType.setValue(type.toString());
            NodeList<TypeParameter> typeParameters = methodDeclaration.getTypeParameters();
            if(CollectionUtil.isNotEmpty(typeParameters)){
                returnType.setGenericValue(typeParameters.get(0).getNameAsString());
            }
            javaMethodMeta.setReturnType(returnType);

            //修饰符
            JavaMemberMeta javaMemberMeta = new JavaMemberMeta();
            List<String> modifiers = methodDeclaration.getModifiers().stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            javaMemberMeta.setModifiers(modifiers);
            javaMethodMeta.setJavaMemberMeta(javaMemberMeta);

            //注解和注释
            JavaModelMeta javaModelMeta = new JavaModelMeta();
            javaModelMeta.setLineNumber(methodDeclaration.getBegin().get().line);
            //避免方法太长 方法体内容不要了
//            javaModelMeta.setCodeBlock(methodDeclaration.getTokenRange().get().toString());
            List<JavaDocletTagMeta>  tags = new ArrayList<>();
            if(javadoc.isPresent()){
                JavadocDescription description = javadoc.get().getDescription();
                List<JavadocDescriptionElement> elements = description.getElements();
                if(CollectionUtil.isNotEmpty(elements)){
                    javaModelMeta.setComment(elements.get(0).toText());
                }
                List<JavadocBlockTag> blockTags = javadoc.get().getBlockTags();
                if(CollectionUtil.isNotEmpty(blockTags)){
                    JavaDocletTagMeta docletTagMeta = null;
                    for (JavadocBlockTag blockTag : blockTags) {
                        docletTagMeta = new JavaDocletTagMeta();
                        docletTagMeta.setType(blockTag.getType().name());
                        docletTagMeta.setName(blockTag.getName().orElse(""));
                        docletTagMeta.setTagName(blockTag.getTagName());
                        docletTagMeta.setValue(blockTag.getContent().toText());
                        tags.add(docletTagMeta);
                    }
                    javaModelMeta.setTags(tags);
                }
            }
            javaMethodMeta.setJavaModelMeta(javaModelMeta);

            //入参与异常
            boolean setResult = false;
            JavaExecutableMeta javaExecutableMeta = new JavaExecutableMeta();
            List<JavaParameterMeta> javaParameters = new ArrayList<>();
            int numberOfParams = resolve.getNumberOfParams();
            if(numberOfParams > 0){
                JavaParameterMeta javaParameterMeta = null;
                for (int i = 0; i < numberOfParams; i++) {
                    ResolvedParameterDeclaration param = resolve.getParam(i);
                    Parameter parameter = methodDeclaration.getParameter(i);
                    ResolvedType resolvedParamType = getParamResolvedType(param);
                    javaParameterMeta = new JavaParameterMeta();
                    javaParameterMeta.setName(param.getName());

                    JavaClassMeta paramClass = new JavaClassMeta();
                    paramClass.setFullClassName(parameter.getTypeAsString());
                    if(resolvedParamType != null){
                        paramClass.setFullClassName(resolvedParamType.describe());
                    }
                    paramClass.setClassName(parameter.getTypeAsString());
                    javaParameterMeta.setJavaClass(paramClass);


                    javaParameters.add(javaParameterMeta);
                }

                javaExecutableMeta.setParameters(javaParameters);
                setResult = true;
            }


            int numberOfSpecifiedExceptions = resolve.getNumberOfSpecifiedExceptions();
            if(numberOfSpecifiedExceptions > 0){
                List<JavaTypeMeta> exceptionTypes = new ArrayList<>();
                JavaTypeMeta ex = null;
                for (int i = 0; i < numberOfSpecifiedExceptions; i++) {
                    ResolvedType specifiedException = getThrowResolvedType(resolve, i);
                    ReferenceType thrownException = methodDeclaration.getThrownException(i);
                    ex = new JavaTypeMeta();
                    ex.setFullyQualifiedName(thrownException.asString());
                    if(specifiedException != null){
                        ex.setFullyQualifiedName(specifiedException.describe());
                    }
                    ex.setValue(thrownException.asString());
                    exceptionTypes.add(ex);
                }
                javaExecutableMeta.setExceptionTypes(exceptionTypes);
                setResult = true;
            }

            if(setResult){
                javaMethodMeta.setJavaExecutableMeta(javaExecutableMeta);
            }


            return javaMethodMeta;
        }
        return null;
    }

    private static ResolvedType getThrowResolvedType(ResolvedMethodDeclaration resolve, int i) {
        try {
            ResolvedType specifiedException = resolve.getSpecifiedException(i);
            return specifiedException;
        } catch (Exception e) {
            log.debug("getThrowResolvedType error",e);
            return null;
        }
    }

    private static ResolvedType getParamResolvedType(ResolvedParameterDeclaration param) {

        try {
            ResolvedType resolvedParamType = param.getType();
            return resolvedParamType;
        } catch (Exception e) {
            log.debug("getParamResolvedType error",e);
            return null;
        }
    }


    private static ResolvedType getMethodReturnResolvedType(ResolvedMethodDeclaration resolve) {
        try {
            ResolvedType returnTypeDeclaration = resolve.getReturnType();
            return returnTypeDeclaration;
        } catch (Exception e) {
            log.debug("getResolvedType error",e);
            return null;
        }
    }

    private JavaFieldMeta paraseField(BodyDeclaration<?> member, JavaClassMeta javaClassMeta) {

        if(member instanceof  FieldDeclaration){
            FieldDeclaration fieldDeclaration = (FieldDeclaration)member;
            Optional<Javadoc> javadoc = fieldDeclaration.getJavadoc();


            NodeList<Modifier> modifiers = fieldDeclaration.getModifiers();
            VariableDeclarator variable = fieldDeclaration.getVariable(0);

            JavaModelMeta javaModelMeta = new JavaModelMeta();
            javaModelMeta.setCodeBlock(fieldDeclaration.getTokenRange().get().toString());
            javaModelMeta.setLineNumber(fieldDeclaration.getBegin().get().line);

            List<JavaDocletTagMeta>  tags = new ArrayList<>();
            if(javadoc.isPresent()){
                JavadocDescription description = javadoc.get().getDescription();
                List<JavadocDescriptionElement> elements = description.getElements();
                if(CollectionUtil.isNotEmpty(elements)){
                    javaModelMeta.setComment(description.getElements().get(0).toText());
                }
                List<JavadocBlockTag> blockTags = javadoc.get().getBlockTags();
                if(CollectionUtil.isNotEmpty(blockTags)){
                    JavaDocletTagMeta docletTagMeta = null;
                    for (JavadocBlockTag blockTag : blockTags) {
                        docletTagMeta = new JavaDocletTagMeta();
                        docletTagMeta.setType(blockTag.getType().name());
                        docletTagMeta.setName(blockTag.getName().orElse(""));
                        docletTagMeta.setTagName(blockTag.getTagName());
                        docletTagMeta.setValue(blockTag.getContent().toText());
                        tags.add(docletTagMeta);
                    }
                    javaModelMeta.setTags(tags);
                }

            }

            JavaClassMeta returnTypeMeta = new JavaClassMeta();
            List<String> modifierList = modifiers.stream().map(modifier -> modifier.getKeyword().name()).collect(Collectors.toList());
            returnTypeMeta.setModifiers(modifierList);

            ResolvedTypeDeclaration resolvedTypeDeclaration = getFieldResolvedTypeDeclaration(fieldDeclaration);
            returnTypeMeta.setClassName(variable.getType().asString());
            returnTypeMeta.setFullClassName(variable.getType().asString());
            if(resolvedTypeDeclaration != null){
                returnTypeMeta.setFullClassName(resolvedTypeDeclaration.getQualifiedName());
            }

            JavaFieldMeta fieldMeta = new JavaFieldMeta();
            fieldMeta.setJavaModelMeta(javaModelMeta);
            fieldMeta.setType(returnTypeMeta);
            fieldMeta.setInitializationExpression(variable.getTokenRange().get().toString());
            return fieldMeta;
        }

        return null;
    }

    private static ResolvedTypeDeclaration getFieldResolvedTypeDeclaration(FieldDeclaration fieldDeclaration) {
        try {
            ResolvedFieldDeclaration resolve = fieldDeclaration.resolve();
            ResolvedTypeDeclaration resolvedTypeDeclaration = resolve.declaringType();
            return resolvedTypeDeclaration;
        } catch (Exception e) {
            log.debug("getFieldResolvedTypeDeclaration error",e);
            return null;
        }
    }

    private void paraseInterfaces(JavaClassMeta javaClassMeta, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {
        NodeList<ClassOrInterfaceType> implementedTypes = javaClass.getImplementedTypes();
        if(CollectionUtil.isNotEmpty(implementedTypes)){
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();
            for (ClassOrInterfaceType implementedType : implementedTypes) {
                javaClassMetaList.add(convertByJavaClass(implementedType));
                javaClassMeta.setInterfaces(javaClassMetaList);
            }
        }
    }

    private JavaClassMeta convertByJavaClass(ClassOrInterfaceType implementedType) {
        if(implementedType == null){
            return null;
        }
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        javaClassMeta.setClassName(implementedType.getNameAsString());
        javaClassMeta.setFullClassName(implementedType.getNameAsString());
        ResolvedType resolve = getRefClassResolvedType(implementedType);
        if(resolve != null){
            javaClassMeta.setFullClassName(resolve.describe());
        }
        return javaClassMeta;
    }

    private static ResolvedType getRefClassResolvedType(ClassOrInterfaceType implementedType) {
        try {
            ResolvedType resolve = implementedType.resolve();
            return resolve;
        } catch (Exception e) {
            log.debug("getRefClassResolvedType error",e);
            return null;
        }
    }

    private void paraseModifiers(JavaClassMeta javaClassMeta, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {
        NodeList<Modifier> modifiers = javaClass.getModifiers();
        List<String> collect = modifiers.stream().map(modifier -> modifier.getKeyword().name()).collect(Collectors.toList());
        javaClassMeta.setModifiers(collect);
    }
    private void paraseSuperJavaClass(JavaClassMeta javaClassMeta, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {

        NodeList<ClassOrInterfaceType> extendedTypes = javaClass.getExtendedTypes();
//        ResolvedReferenceTypeDeclaration resolve = javaClass.resolve();
//        if(resolve != null){
//            List<ResolvedReferenceType> allAncestors = resolve.getAllAncestors();
//            List<ResolvedReferenceType> ancestors = resolve.getAncestors();
//        }


        if(CollectionUtil.isNotEmpty(extendedTypes)){
            ClassOrInterfaceType classOrInterfaceType = extendedTypes.get(0);
            javaClassMeta.setSuperJavaClass(convertByJavaClass(classOrInterfaceType));
        }


    }

    private JavaClassMeta convertByJavaClass(ClassOrInterfaceType classOrInterfaceType, CompilationUnit rootAst) {
        if(classOrInterfaceType == null){
            return null;
        }
        Node node = classOrInterfaceType.getParentNode().get();
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        String packageName = rootAst.getPackageDeclaration().get().getNameAsString();
        javaClassMeta.setFullClassName(StrUtil.join(".",packageName,classOrInterfaceType.getNameAsString()));
        javaClassMeta.setClassName(classOrInterfaceType.getNameAsString());


        return javaClassMeta;
    }



    private void paraseJavaSouce(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {
        Optional<PackageDeclaration> packageDeclarationOptional = rootAst.getPackageDeclaration();
        if(packageDeclarationOptional.isPresent()){
            PackageDeclaration packageDeclaration = rootAst.getPackageDeclaration().get();

            CompilationUnit node = (CompilationUnit)javaClass.getParentNode().get();
            List<String> importList = node.getImports().stream().map(importDeclaration -> importDeclaration.getNameAsString()).collect(Collectors.toList());
            javaClassMeta.setImports(importList);

            try {
                javaClassMeta.setUrl(node.getStorage().get().getPath().toUri().toURL());
            } catch (MalformedURLException e) {
            }

            javaClassMeta.setClassNamePrefix(packageDeclaration.getNameAsString()+".");
            javaClassMeta.setPackageName(packageDeclaration.getNameAsString());
        }
    }

    /**
     * 解析class基本信息
     *
     * @param javaClassMeta
     * @param metaFileInfoDto
     * @param javaClass
     * @param rootAst
     */
    private void paraseClassMetaProp(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto, ClassOrInterfaceDeclaration javaClass, CompilationUnit rootAst) {
        SimpleName simpleName = javaClass.getName();
        javaClassMeta.setClassName(simpleName.getIdentifier());
        javaClassMeta.setFullClassName(javaClass.getFullyQualifiedName().get());
        javaClassMeta.setValue(simpleName.getIdentifier());
        if(javaClass.isAbstract()){
            javaClassMeta.setAbstractClass(javaClass.isAbstract());
        }
        if(javaClass.isEnumDeclaration()){
            javaClassMeta.setEnumClass(javaClass.isEnumDeclaration());
        }
        if(javaClass.isFinal()){
            javaClassMeta.setFinalClass(javaClass.isFinal());
        }
        if(javaClass.isInterface()){
            javaClassMeta.setInterfaceClass(javaClass.isInterface());
        }
        if(javaClass.isPrivate()){
            javaClassMeta.setPrimitiveClass(javaClass.isPrivate());
        }
        if(javaClass.isPrivate()){
            javaClassMeta.setPrivateClass(javaClass.isPrivate());
        }
       if(javaClass.isProtected()){
           javaClassMeta.setProtectedClass(javaClass.isProtected());
       }
        if(javaClass.isPublic()){
            javaClassMeta.setPublicClass(javaClass.isPublic());
        }
        if(javaClass.isAnnotationDeclaration()){
            javaClassMeta.setAnnotationClass(javaClass.isAnnotationDeclaration());
        }
        if(javaClass.isStatic()){
            javaClassMeta.setStaticClass(javaClass.isStatic());
        }
        if(javaClass.isEmpty()){
            javaClassMeta.setVoidClass(javaClass.isEmpty());
        }
        javaClassMeta.setModuleName(metaFileInfoDto.getModuleName());
        javaClassMeta.setProjectName(metaFileInfoDto.getProjectName());
    }

    private static String describe(Node node) {
        if (node instanceof MethodDeclaration) {
            MethodDeclaration methodDeclaration = (MethodDeclaration)node;
            return "Method " + methodDeclaration.getDeclarationAsString();
        }
        if (node instanceof ConstructorDeclaration) {
            ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration)node;
            return "Constructor " + constructorDeclaration.getDeclarationAsString();
        }
        if (node instanceof ClassOrInterfaceDeclaration) {
            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration)node;
            if (classOrInterfaceDeclaration.isInterface()) {
                return "Interface " + classOrInterfaceDeclaration.getName();
            } else {
                return "Class " + classOrInterfaceDeclaration.getName();
            }
        }
        if (node instanceof EnumDeclaration) {
            EnumDeclaration enumDeclaration = (EnumDeclaration)node;
            return "Enum " + enumDeclaration.getName();
        }
        if (node instanceof FieldDeclaration) {
            FieldDeclaration fieldDeclaration = (FieldDeclaration)node;
            List<String> varNames = fieldDeclaration.getVariables().stream().map(v -> v.getName().getId()).collect(Collectors.toList());
            return "Field " + String.join(", ", varNames);
        }
        return node.toString();
    }

    private List<JavaModuleMeta> groupJavaClassMetaByModule(Collection<JavaClassMeta> javaClassMetaList) {

        List<JavaModuleMeta> result = new ArrayList<>();

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            Map<String, List<JavaClassMeta>> groupResultMap = groupByModelueName(javaClassMetaList);

            groupResultMap.forEach((groupName,javaClassMetaGroupList) ->{
                JavaModuleMeta javaModuleMeta = new JavaModuleMeta();
                javaModuleMeta.setName(groupName);
                javaModuleMeta.setJavaClassMetas(javaClassMetaGroupList);
                result.add(javaModuleMeta);
            });

        }
        return result;
    }

    private static Map<String, List<JavaClassMeta>> groupByModelueName(Collection<JavaClassMeta> javaClassMetaList) {
        //            Map<String, List<JavaClassMeta>> groupResultMap = javaClassMetaList.stream().collect(Collectors.groupingBy(JavaClassMeta::getModuleName));
        Map<String, List<JavaClassMeta>> groupResultMap = new HashMap<>();
        for (JavaClassMeta javaClassMeta : javaClassMetaList) {
            String moduleName = javaClassMeta.getModuleName();
            if(StrUtil.isNotBlank(moduleName)){
                if(groupResultMap.containsKey(moduleName)){
                    groupResultMap.get(moduleName).add(javaClassMeta);
                }else {
                    List<JavaClassMeta> groupJavaClassMetaList =new ArrayList<>();
                    groupJavaClassMetaList.add(javaClassMeta);
                    groupResultMap.put(moduleName,groupJavaClassMetaList);
                }

            }else{
                log.warn("Not obtained module name javaClassMeta:{}",JSONUtil.toJsonStr(javaClassMeta));
            }

        }
        return groupResultMap;
    }

    public  void parseOneFile(CompilationUnit cu) {
        // 类型声明
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        for (TypeDeclaration<?> type : types) {
            System.out.println("## " + type.getName());
            // 成员
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            members.forEach(this::processNode);
        }
    }

    /**
     * 处理类型,方法,成员
     *
     * @param node
     */
    public  void processNode(Node node) {
        if (node instanceof TypeDeclaration) {
            // 类型声明
            // do something with this type declaration

        } else if (node instanceof MethodDeclaration) {
            // 方法声明
            // do something with this method declaration
            String methodName = ((MethodDeclaration) node).getName().getIdentifier();
            System.out.println("方法: " + methodName);

        } else if (node instanceof FieldDeclaration) {
            // 成员变量声明
            // do something with this field declaration
            // 注释
            Comment comment = node.getComment().orElse(null);

            // 变量
            NodeList<VariableDeclarator> variables = ((FieldDeclaration) node).getVariables();
            SimpleName fieldName = variables.get(0).getName();
            if (comment != null) {
                System.out.print(comment.getContent());

            }
            System.out.print("\t");
            System.out.print(fieldName);
            System.out.println();
        }else if(node instanceof Parameter){
            SimpleName name = ((Parameter) node).getName();
        }

        for (Node child : node.getChildNodes()) {
            processNode(child);
        }
    }
}
