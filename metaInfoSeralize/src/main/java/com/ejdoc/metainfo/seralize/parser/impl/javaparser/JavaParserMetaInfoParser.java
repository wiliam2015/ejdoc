package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.AbstractMetaInfoParser;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.ejdoc.metainfo.seralize.util.MetaPathUtil;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.resolution.declarations.*;
import com.github.javaparser.resolution.types.*;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class JavaParserMetaInfoParser extends AbstractMetaInfoParser {
    private static final Logger log = LoggerFactory.getLogger(JavaParserMetaInfoParser.class);

    /**
     * 文件目录缓存
     */
    private Set<String> srcDirCache = new HashSet<>();
    /**
     * 包名信息
     */
    private static final String PACKAGE_INFO_SOURCE="package-info.java";
    private static final String PACKAGE_INFO="package-info";

//    private Javaparser
    private JavaParser javaParser;

    public JavaParserMetaInfoParser() {
        super();

        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();

        String projectSourceDir = metaFileRead.getMetaEnvironment().getProjectSourceDir();
        CombinedTypeSolver combinedTypeSolverNewCode = new CombinedTypeSolver();
        combinedTypeSolverNewCode.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration();
        parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
//        parserConfiguration.setStoreTokens(false);
//        parserConfiguration.setAttributeComments(false);
//        parserConfiguration.setLexicalPreservationEnabled(false);
        if(CollectionUtil.isNotEmpty(metaFileInfos)){
            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                String parent = metaFileInfo.getMetaFile().getParent();

                int src = parent.indexOf(projectSourceDir);
                if(src != -1){
                    String sourceDir = parent.substring(0,src+projectSourceDir.length());
                    if(!srcDirCache.contains(sourceDir)){
                        combinedTypeSolverNewCode.add(new JavaParserTypeSolver(sourceDir, parserConfiguration));
                        srcDirCache.add(sourceDir);
                    }
                }
            }

        }
        this.javaParser = createParserWithResolver(combinedTypeSolverNewCode);
    }

    public JavaParserMetaInfoParser(MetaFileRead metaFileRead) {
        super(metaFileRead);

        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();

        String projectSourceDir = metaFileRead.getMetaEnvironment().getProjectSourceDir();
        CombinedTypeSolver combinedTypeSolverNewCode = new CombinedTypeSolver();
        combinedTypeSolverNewCode.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration();
        parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
//        parserConfiguration.setStoreTokens(false);
//        parserConfiguration.setAttributeComments(false);
//        parserConfiguration.setLexicalPreservationEnabled(false);
        if(CollectionUtil.isNotEmpty(metaFileInfos)){
            for (MetaFileInfoDto metaFileInfo : metaFileInfos) {
                String parent = metaFileInfo.getMetaFile().getParent();

                int src = parent.indexOf(projectSourceDir);
                if(src != -1){
                    String sourceDir = parent.substring(0,src+projectSourceDir.length());
                    if(!srcDirCache.contains(sourceDir)){
                        combinedTypeSolverNewCode.add(new JavaParserTypeSolver(sourceDir, parserConfiguration));
                        srcDirCache.add(sourceDir);
                    }
                }
            }

        }
        this.javaParser = createParserWithResolver(combinedTypeSolverNewCode);
    }

    @Override
    public List<JavaModuleMeta> parseAllJavaModuletMeta() {
        List<JavaModuleMeta> javaModuleMetas = new ArrayList<>();
        JavaParserMetaContext javaParserMetaContext = new JavaParserMetaContext();
        List<MetaFileInfoDto> metaFileInfos = metaFileRead.readAllMetaFile();
        try {
            List<JavaClassMeta> javaClassMetaList = new ArrayList<>();

            preParsingJavaClass(javaParserMetaContext, metaFileInfos, javaClassMetaList);

            postParsingJavaClass(javaParserMetaContext, javaClassMetaList);

            javaModuleMetas =  groupJavaClassMetaByModule(javaClassMetaList);

        } catch (Exception e) {
            log.error("parseAllJavaModuletMeta error",e);
            throw new RuntimeException(e);
        }

        return javaModuleMetas;

    }

    /**
     * 后置解析java类信息 依赖于之前已经解析出来的数据
     * @param javaParserMetaContext
     * @param javaClassMetaList
     */
    private void postParsingJavaClass(JavaParserMetaContext javaParserMetaContext, List<JavaClassMeta> javaClassMetaList) {
        for (JavaClassMeta javaClassMeta : javaClassMetaList) {
            processPostJavaClassMeta( javaClassMeta, javaParserMetaContext);
        }
    }

    /**
     * 后置加工java元数据信息
     * @param javaClassMeta
     * @param javaParserMetaContext
     */
    private void processPostJavaClassMeta(JavaClassMeta javaClassMeta, JavaParserMetaContext javaParserMetaContext) {
        parseClassDependPath(javaClassMeta, javaParserMetaContext);
    }

    /**
     * 解析java依赖路径 设置成相对路径和绝对路径
     * @param javaClassMeta
     * @param javaParserMetaContext
     */
    private void parseClassDependPath(JavaClassMeta javaClassMeta, JavaParserMetaContext javaParserMetaContext) {
        Map<String, JavaParserDependPath> dependPathMap = javaParserMetaContext.getDependPathMap();

        parseSupperClassDependPath(javaClassMeta, dependPathMap);

        parseInterfaceDependPath(javaClassMeta, dependPathMap);

        parseContructorDependPath(javaClassMeta, dependPathMap);

        parseFiledDependPath(javaClassMeta, dependPathMap);

        parseMethodDependPath(javaClassMeta, dependPathMap);
    }

    private  void parseMethodDependPath(JavaClassMeta javaClassMeta, Map<String, JavaParserDependPath> dependPathMap) {
        List<JavaMethodMeta> methods = javaClassMeta.getMethods();
        String absolutePath = javaClassMeta.getAbsolutePath();
        if(CollectionUtil.isNotEmpty(methods)){
            for (JavaMethodMeta method : methods) {
                JavaClassMeta returns = method.getReturns();
                setRelativePath(dependPathMap, absolutePath, returns);
                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        setRelativePath(dependPathMap, absolutePath, javaClass);
                    }
                }
                parseBaseClassDependPath(method.getExceptions(),absolutePath, dependPathMap);
            }
        }
    }

    private  void parseContructorDependPath(JavaClassMeta javaClassMeta, Map<String, JavaParserDependPath> dependPathMap) {
        String absolutePath = javaClassMeta.getAbsolutePath();
        List<JavaConstructorMeta> constructors = javaClassMeta.getConstructors();
        if(CollectionUtil.isNotEmpty(constructors)){
            for (JavaConstructorMeta constructor : constructors) {
                JavaExecutableMeta javaExecutableMeta = constructor.getJavaExecutableMeta();
                if(javaExecutableMeta != null){
                    List<JavaParameterMeta> parameters = javaExecutableMeta.getParameters();
                    if(CollectionUtil.isNotEmpty(parameters)){
                        for (JavaParameterMeta parameter : parameters) {
                            JavaClassMeta javaClass = parameter.getJavaClass();
                            setRelativePath(dependPathMap,absolutePath, javaClass);
                        }
                    }
                    parseBaseClassDependPath(javaExecutableMeta.getExceptions(),absolutePath, dependPathMap);
                }
            }
        }
    }

    private  void parseFiledDependPath(JavaClassMeta javaClassMeta, Map<String, JavaParserDependPath> dependPathMap) {
        List<JavaFieldMeta> fields = javaClassMeta.getFields();
        String absolutePath = javaClassMeta.getAbsolutePath();
        if(CollectionUtil.isNotEmpty(fields)){
            for (JavaFieldMeta field : fields) {
                JavaClassMeta type = field.getType();
                setRelativePath(dependPathMap,absolutePath, type);
            }
        }
    }

    private  void parseInterfaceDependPath(JavaClassMeta javaClassMeta, Map<String, JavaParserDependPath> dependPathMap) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getInterfaces();
        String absolutePath = javaClassMeta.getAbsolutePath();
        parseBaseClassDependPath(javaClassMetaList,absolutePath,dependPathMap);
    }

    private  void parseSupperClassDependPath(JavaClassMeta javaClassMeta, Map<String, JavaParserDependPath> dependPathMap) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getSuperClasses();
        String absolutePath = javaClassMeta.getAbsolutePath();
        parseBaseClassDependPath(javaClassMetaList,absolutePath,dependPathMap);

    }

    private  void parseBaseClassDependPath(List<JavaClassMeta> javaClassMetaList,String absolutePath, Map<String, JavaParserDependPath> dependPathMap) {

        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                setRelativePath(dependPathMap, absolutePath,javaClassMeta);
            }
        }
    }

    /**
     * 设置相对路径
     * @param dependPathMap
     * @param absolutePath
     * @param classMeta
     */
    private  void setRelativePath(Map<String, JavaParserDependPath> dependPathMap, String absolutePath, JavaClassMeta classMeta) {
        String fullClassName = classMeta.getFullClassName();
        if(dependPathMap.containsKey(fullClassName)){
            JavaParserDependPath javaParserDependPath = dependPathMap.get(fullClassName);
            String dependAbsolutePath = javaParserDependPath.getAbsolutePath();
            String relativePath = MetaPathUtil.calRelativePath(dependAbsolutePath, absolutePath);
            //依赖信息是自己
            if(StrUtil.equals(dependAbsolutePath,absolutePath)){
                relativePath = classMeta.getClassName();
            }
            if(StrUtil.isNotBlank(relativePath)){
                relativePath = relativePath.replace(".java","");
                classMeta.setDependencyRelativePath(relativePath);
            }


        }
    }

    /**
     * 前置解析java类信息，直接依赖获取到的文件信息即可获取
     * @param javaParserMetaContext
     * @param metaFileInfos
     * @param javaClassMetaList
     * @throws FileNotFoundException
     */
    private void preParsingJavaClass(JavaParserMetaContext javaParserMetaContext, List<MetaFileInfoDto> metaFileInfos, List<JavaClassMeta> javaClassMetaList) throws FileNotFoundException {
        for (MetaFileInfoDto metaFileInfo : metaFileInfos) {

            processJavaClassMeta( metaFileInfo, javaClassMetaList, javaParserMetaContext);

        }
    }

    /**
     * 因为解析的是注释文件，在java文件中，有注释的地方是
     * 1.文件头；2.成员变量；3.成员变量实体；4.方法；5.方法入参；6.方法入参实体类
     * @param metaFileInfo
     * @param javaClassMetaList
     * @param javaParserMetaContext
     * @throws FileNotFoundException
     */
    private void processJavaClassMeta(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext) throws FileNotFoundException {

        Optional<CompilationUnit> result = this.javaParser.parse(metaFileInfo.getMetaFile()).getResult();

        //抽象语法树的根节点
        CompilationUnit rootAst = result.get();
        //解析包信息
        parsePackageInfo(metaFileInfo,javaClassMetaList, rootAst);
        //解析具体的类信息
        parseJavaClassInfo(metaFileInfo, javaClassMetaList, javaParserMetaContext, rootAst);


    }

    private List<JavaClassMeta> parseJavaClassInfo(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext, CompilationUnit rootAst) {
        NodeList<TypeDeclaration<?>> classTypeDataList = rootAst.getTypes();
        if(CollectionUtil.isNotEmpty(classTypeDataList)){
            for (TypeDeclaration<?> typeDeclaration : classTypeDataList) {
                parseSingleTypeDeclaration(metaFileInfo, javaClassMetaList, javaParserMetaContext, rootAst, typeDeclaration);
            }
        }
        return javaClassMetaList;
    }

    private void parseSingleTypeDeclaration(MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext, CompilationUnit rootAst, TypeDeclaration<?> typeDeclaration) {
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        parseClassMetaProp(javaClassMeta, metaFileInfo, typeDeclaration);
        parseJavaSouce(javaClassMeta, metaFileInfo, typeDeclaration, rootAst);
//        parseDocAndAnnotation(javaClassMeta, metaFileInfo, typeDeclaration, rootAst);
        parseSuperJavaClass(javaClassMeta, typeDeclaration, rootAst);
        parseNestedJavaClass(javaClassMeta, typeDeclaration, rootAst);
        parseModifiers(javaClassMeta, typeDeclaration.getModifiers(), rootAst);
        parseInterfaces(javaClassMeta, typeDeclaration, rootAst);
        parseMembers(javaClassMeta, metaFileInfo, javaClassMetaList, javaParserMetaContext, typeDeclaration, rootAst);
        parseClassDeclaration(javaClassMeta, rootAst);
        parseMutiFilePathToContext(javaClassMeta, javaParserMetaContext);
        javaClassMetaList.add(javaClassMeta);
    }

    /**
     * todo 嵌套类解析
     * @param javaClassMeta
     * @param typeDeclaration
     * @param rootAst
     */
    private void parseNestedJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst) {

    }

    /**
     * 解析多级文件路径到解析上下文,绝对路径
     * @param javaClassMeta
     * @param javaParserMetaContext
     */
    private void parseMutiFilePathToContext(JavaClassMeta javaClassMeta, JavaParserMetaContext javaParserMetaContext) {
        JavaParserDependPath javaParserDependPath = new JavaParserDependPath();
        javaParserDependPath.setFileName(javaClassMeta.getFullClassName());
        javaParserDependPath.setAbsolutePath(javaClassMeta.getAbsolutePath());
        javaParserMetaContext.addDependPath(javaClassMeta.getFullClassName(), javaParserDependPath);
    }

    /**
     * 解析类声明结构
     * @param javaClassMeta
     * @param rootAst
     */
    private void parseClassDeclaration(JavaClassMeta javaClassMeta, CompilationUnit rootAst) {
        javaClassMeta.setDeclarationStructure(javaClassMeta.parseDeclarationStructure());
    }

    /**
     * 解析package-info文件
     * @param metaFileInfo
     * @param javaClassMetaList
     * @param rootAst
     * @return
     */
    private void parsePackageInfo(MetaFileInfoDto metaFileInfo , List<JavaClassMeta> javaClassMetaList,CompilationUnit rootAst) {
        if(PACKAGE_INFO_SOURCE.equals(metaFileInfo.getMetaFileName())){
            JavaClassMeta javaClassMeta = new JavaClassMeta();
            parseJavaSouce(javaClassMeta, metaFileInfo,null, rootAst);
            javaClassMeta.setModuleName(metaFileInfo.getModuleName());
            javaClassMeta.setProjectName(metaFileInfo.getProjectName());
            javaClassMeta.setClassName(PACKAGE_INFO);
            javaClassMeta.setValue(PACKAGE_INFO);
            javaClassMeta.setFullClassName(javaClassMeta.getClassNamePrefix()+PACKAGE_INFO);
            javaClassMetaList.add(javaClassMeta);
        }
    }

    protected JavaParser createParserWithResolver(TypeSolver typeSolver) {
        return new JavaParser(new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver)));
    }
    private void parseMembers(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, List<JavaClassMeta> javaClassMetaList, JavaParserMetaContext javaParserMetaContext, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst) {
        NodeList<BodyDeclaration<?>> members= typeDeclaration.getMembers();
        List<JavaFieldMeta> javaFieldMetas = new ArrayList<>();
        List<JavaFieldMeta> enumEntries = new ArrayList<>();
        List<JavaMethodMeta> javaMethodMetas = new ArrayList<>();
        for (BodyDeclaration<?> member : members) {
            JavaFieldMeta javaFieldMeta = parseField(member, javaClassMeta);
            if(javaFieldMeta != null){
                javaFieldMetas.add(javaFieldMeta);
            }
            JavaMethodMeta javaMethodMeta = parseMethod(member, javaClassMeta);
            if(javaMethodMeta != null){
                javaMethodMetas.add(javaMethodMeta);
            }
            if(member.isClassOrInterfaceDeclaration()){
                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) member;
                parseSingleTypeDeclaration(metaFileInfo,javaClassMetaList, javaParserMetaContext,classOrInterfaceDeclaration.findCompilationUnit().get(),classOrInterfaceDeclaration);
            }
        }

        if(typeDeclaration.isEnumDeclaration()){
            EnumDeclaration declaration = (EnumDeclaration) typeDeclaration;
            NodeList<EnumConstantDeclaration> entries = declaration.getEntries();
            if(CollectionUtil.isNotEmpty(entries)){
                for (EnumConstantDeclaration entry : entries) {
                    JavaFieldMeta enumEntry = parseEnumEntry(entry, javaClassMeta);
                    if(enumEntry != null){
                        enumEntries.add(enumEntry);
                    }
                }
            }
        }

        javaClassMeta.setFields(javaFieldMetas);
        javaClassMeta.setMethods(javaMethodMetas);
        javaClassMeta.setEnumConstants(enumEntries);

    }

    private JavaFieldMeta parseEnumEntry(EnumConstantDeclaration entry, JavaClassMeta javaClassMeta) {
        Optional<Javadoc> javadoc = entry.getJavadoc();
        SimpleName name = entry.getName();
        NodeList<Expression> arguments = entry.getArguments();
        NodeList<AnnotationExpr> annotations = entry.getAnnotations();
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        createJavaDocTag(javadoc,javaModelMeta);

        if(CollectionUtil.isNotEmpty(arguments)){
            for (Expression argument : arguments) {
            }
        }
        JavaFieldMeta fieldMeta = new JavaFieldMeta();
        fieldMeta.setJavaModelMeta(javaModelMeta);
        fieldMeta.setName(name.getIdentifier());
        return fieldMeta;
    }

    private JavaMethodMeta parseMethod(BodyDeclaration<?> member, JavaClassMeta javaClassMeta) {

        if(member instanceof  MethodDeclaration){
            JavaMethodMeta javaMethodMeta = new JavaMethodMeta();
            MethodDeclaration methodDeclaration = (MethodDeclaration)member;
            ResolvedMethodDeclaration resolve = methodDeclaration.resolve();

            Optional<Javadoc> javadoc = methodDeclaration.getJavadoc();

            //方法名与方法体
            javaMethodMeta.setName(methodDeclaration.getNameAsString());
            javaMethodMeta.setCallSignature(methodDeclaration.getDeclarationAsString());
            //返回类型
            parseMethodReturnType(javaMethodMeta, methodDeclaration, resolve);
            //修饰符
            parseMethodModifiers(javaMethodMeta, methodDeclaration);
            //注解和注释
            parseMethodDocTag(javaMethodMeta, methodDeclaration, javadoc);
            //入参与异常
            parseMethodParamAndException(javaMethodMeta, methodDeclaration, resolve);

            return javaMethodMeta;
        }
        return null;
    }

    private void parseMethodParamAndException(JavaMethodMeta javaMethodMeta, MethodDeclaration methodDeclaration, ResolvedMethodDeclaration resolve) {

        parseMethodParameters(methodDeclaration, resolve, javaMethodMeta);

        parseMethodExceptions(methodDeclaration, resolve, javaMethodMeta);

    }

    private void parseMethodExceptions(MethodDeclaration methodDeclaration, ResolvedMethodDeclaration resolve, JavaMethodMeta javaMethodMeta) {
        int numberOfSpecifiedExceptions = resolve.getNumberOfSpecifiedExceptions();
        if(numberOfSpecifiedExceptions > 0){
            List<JavaClassMeta> exceptionTypes = new ArrayList<>();
            JavaClassMeta ex = null;
            for (int i = 0; i < numberOfSpecifiedExceptions; i++) {
                ResolvedType specifiedException = getThrowResolvedType(resolve, i);
                ReferenceType thrownException = methodDeclaration.getThrownException(i);
                ex = new JavaClassMeta();
                ex.setFullClassName(thrownException.asString());
                setFullClassNameFromResolvedType(ex,specifiedException);

                ex.setClassName(thrownException.asString());
                exceptionTypes.add(ex);
            }
            javaMethodMeta.setExceptions(exceptionTypes);
        }
    }

    private void parseMethodParameters(MethodDeclaration methodDeclaration, ResolvedMethodDeclaration resolve, JavaMethodMeta javaMethodMeta) {

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
                javaParameterMeta.setVarArgs(parameter.isVarArgs());

                JavaClassMeta paramClass = new JavaClassMeta();
                paramClass.setClassName(parameter.getTypeAsString());

                setTypeArgumentsFromType(parameter.getType(), paramClass);

                paramClass.setFullClassName(parameter.getTypeAsString());
                setFullClassNameFromResolvedType(paramClass,resolvedParamType);

                NodeList<Modifier> paramModifiers = parameter.getModifiers();
                if(CollectionUtil.isNotEmpty(paramModifiers)){
                    List<String> modifiers = paramModifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
                    paramClass.setModifiers(modifiers);
                }

                javaParameterMeta.setJavaClass(paramClass);
                javaParameters.add(javaParameterMeta);
            }

            javaMethodMeta.setParameters(javaParameters);
        }
    }

    private  void parseMethodDocTag(JavaMethodMeta javaMethodMeta, MethodDeclaration methodDeclaration, Optional<Javadoc> javadoc) {
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        javaModelMeta.setLineNumber(methodDeclaration.getBegin().get().line);
        //避免方法太长 方法体内容不要了
//            javaModelMeta.setCodeBlock(methodDeclaration.getTokenRange().get().toString());
        createJavaDocTag(javadoc, javaModelMeta);
        javaMethodMeta.setJavaModelMeta(javaModelMeta);
    }

    private  void parseMethodModifiers(JavaMethodMeta javaMethodMeta, MethodDeclaration methodDeclaration) {
        List<String> modifiers = methodDeclaration.getModifiers().stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
        javaMethodMeta.setModifiers(modifiers);
    }

    private void parseMethodReturnType(JavaMethodMeta javaMethodMeta, MethodDeclaration methodDeclaration, ResolvedMethodDeclaration resolve) {
        ResolvedType returnTypeDeclaration = getMethodReturnResolvedType(resolve);
        JavaClassMeta returnType = new JavaClassMeta();
        Type type = methodDeclaration.getType();
        returnType.setClassName(type.toString());
        returnType.setFullClassName(type.toString());

        setFullClassNameFromResolvedType(returnType,returnTypeDeclaration);

        setTypeArgumentsFromType(type, returnType);

        setTypeParametersFromDeclaration(methodDeclaration.getTypeParameters(), returnType);
        javaMethodMeta.setReturns(returnType);
    }

    private  void setTypeParametersFromDeclaration(NodeList<TypeParameter> typeParameters, JavaClassMeta returnType) {
        if(CollectionUtil.isNotEmpty(typeParameters)){
            List<String> typeParamList = typeParameters.stream().map(Node::toString).collect(Collectors.toList());
            returnType.setTypeParameters(typeParamList);
        }
    }

    /**
     * 循环设置类型参数
     * @param parameter
     * @param paramClass
     */
    private  void setTypeArgumentsFromType(Type parameter, JavaClassMeta paramClass) {
        if(parameter.isClassOrInterfaceType()){
            ClassOrInterfaceType paramclassOrInterfaceType = parameter.asClassOrInterfaceType();
            paramClass.setClassName(paramclassOrInterfaceType.getName().getId());
            Optional<NodeList<Type>> typeArguments = paramclassOrInterfaceType.getTypeArguments();
            if(typeArguments.isPresent()){
                List<JavaClassMeta> typeArgumentsList = new ArrayList<>();
                JavaClassMeta typeClassMeth = null;
                for (Type type : typeArguments.get()) {
                    typeClassMeth = new JavaClassMeta();
                    ResolvedType resolve = type.resolve();
                    typeClassMeth.setClassName(type.asString());
                    setFullClassNameFromResolvedType(typeClassMeth,resolve);
                    typeArgumentsList.add(typeClassMeth);
                    setTypeArgumentsFromType(type,typeClassMeth);
                }
                paramClass.setTypeArguments(typeArgumentsList);
            }
        }
    }

    /**
     * 创建javadoc标签
     * @param javadoc
     * @param javaModelMeta
     */
    private static void createJavaDocTag(Optional<Javadoc> javadoc, JavaModelMeta javaModelMeta) {
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
                    String typeVal = blockTag.getType().name();
                    Optional<String> typeName = blockTag.getName();
                    docletTagMeta.setType(blockTag.getType().name());
                    docletTagMeta.setName(typeName.orElse(""));
                    docletTagMeta.setTagName(blockTag.getTagName());
                    docletTagMeta.setValue(blockTag.getContent().toText());
                    if(typeVal.equals("PARAM") && typeName.isPresent()){
                        if(typeName.get().matches("<.*>")){
                            docletTagMeta.setType("TYPEPARAM");
                            docletTagMeta.setTagName("typeParm");
                            String typeNameStr = typeName.orElse("");
                            typeNameStr = typeNameStr.replace("<","");
                            typeNameStr = typeNameStr.replace(">","");
                            docletTagMeta.setName(typeNameStr);
                        }
                    }

                    tags.add(docletTagMeta);
                }
                javaModelMeta.setTags(tags);
            }
        }
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

    private JavaFieldMeta parseField(BodyDeclaration<?> member, JavaClassMeta javaClassMeta) {

        if(member instanceof  FieldDeclaration){
            FieldDeclaration fieldDeclaration = (FieldDeclaration)member;
            Optional<Javadoc> javadoc = fieldDeclaration.getJavadoc();

            NodeList<Modifier> modifiers = fieldDeclaration.getModifiers();
            VariableDeclarator variable = fieldDeclaration.getVariable(0);

            JavaModelMeta javaModelMeta = new JavaModelMeta();
            javaModelMeta.setCodeBlock(fieldDeclaration.getTokenRange().get().toString());
            javaModelMeta.setLineNumber(fieldDeclaration.getBegin().get().line);

            createJavaDocTag(javadoc,javaModelMeta);

            JavaClassMeta returnTypeMeta = new JavaClassMeta();
            List<String> modifierList = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            returnTypeMeta.setModifiers(modifierList);

            returnTypeMeta.setClassName(variable.getType().asString());
            returnTypeMeta.setFullClassName(variable.getType().asString());
            setFieldResolvedTypeDeclaration(returnTypeMeta,variable);


            JavaFieldMeta fieldMeta = new JavaFieldMeta();
            fieldMeta.setJavaModelMeta(javaModelMeta);
            fieldMeta.setType(returnTypeMeta);
            Optional<Expression> initializer = variable.getInitializer();
            initializer.ifPresent(expression -> fieldMeta.setInitializer(expression.toString()));
            fieldMeta.setName(variable.getNameAsString());
            fieldMeta.setInitializationExpression(variable.getTokenRange().get().toString());
            return fieldMeta;
        }

        return null;
    }
    private void setFieldResolvedTypeDeclaration(JavaClassMeta returnTypeMeta, VariableDeclarator variable) {
        try {
            ResolvedType resolve = variable.getType().resolve();
            setFullClassNameFromResolvedType(returnTypeMeta,resolve);
        } catch (Exception e) {
            log.debug("setFieldResolvedTypeDeclaration error",e);
        }

    }
    private void setFullClassNameFromResolvedType(JavaClassMeta classMeta, ResolvedType resolve) {
        try {
            if(resolve.isArray()){
                ResolvedArrayType resolvedArrayType = resolve.asArrayType();
                ResolvedType componentType = resolvedArrayType.getComponentType();
                classMeta.setArrayFullClassName(resolvedArrayType.describe());
                setFullClassNameFromResolvedType(classMeta,componentType);
            }else if(resolve.isConstraint()){
                ResolvedLambdaConstraintType resolvedLambdaConstraintType = resolve.asConstraintType();
                classMeta.setFullClassName( resolvedLambdaConstraintType.describe());
            }else if(resolve.isNumericType() || resolve.isPrimitive()){
                ResolvedPrimitiveType resolvedPrimitiveType = resolve.asPrimitive();
                String boxTypeQName = resolvedPrimitiveType.getBoxTypeQName();
                classMeta.setFullClassName( boxTypeQName);
            }else if(resolve.isInferenceVariable() || resolve.isReference() || resolve.isReferenceType()){
                ResolvedReferenceType resolvedReferenceType = resolve.asReferenceType();
                String qualifiedName = resolvedReferenceType.getQualifiedName();
                classMeta.setFullClassName(qualifiedName);
            }else if(resolve.isTypeVariable()){
                ResolvedTypeVariable resolvedTypeVariable = resolve.asTypeVariable();
                String qualifiedName = resolvedTypeVariable.qualifiedName();
                classMeta.setFullClassName(qualifiedName);
            }else if(resolve.isUnionType()){
                ResolvedUnionType resolvedUnionType = resolve.asUnionType();
                classMeta.setFullClassName( resolvedUnionType.describe());
            }else if(resolve.isWildcard()){
                ResolvedWildcard resolvedWildcard = resolve.asWildcard();
                classMeta.setFullClassName( resolvedWildcard.describe());
            }
        } catch (Exception e) {
            log.debug("setFullClassNameFromResolvedType error",e);
        }
    }

    private static ResolvedType getFieldResolvedTypeDeclaration(VariableDeclarator variable) {
        try {
            ResolvedType resolve = variable.getType().resolve();
            return resolve;
        } catch (Exception e) {
            log.debug("getFieldResolvedTypeDeclaration error",e);
            return null;
        }
    }

    private void parseInterfaces(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst) {
        NodeList<ClassOrInterfaceType> implementedTypes = null;
        if(typeDeclaration.isClassOrInterfaceDeclaration()){
            ClassOrInterfaceDeclaration declaration=(ClassOrInterfaceDeclaration)typeDeclaration;
            implementedTypes = declaration.getImplementedTypes();
        }else if(typeDeclaration.isEnumDeclaration()){
            EnumDeclaration declaration=(EnumDeclaration)typeDeclaration;
            implementedTypes = declaration.getImplementedTypes();
        }
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
        Optional<NodeList<Type>> typeArguments = implementedType.getTypeArguments();
        if(typeArguments.isPresent()){
            List<String> collect = typeArguments.get().stream().map(Type::asString).collect(Collectors.toList());
            javaClassMeta.setTypeParameters(collect);
        }
        javaClassMeta.setClassName(implementedType.getNameAsString());
        javaClassMeta.setFullClassName(implementedType.getNameAsString());
        ResolvedType resolve = getRefClassResolvedType(implementedType);
        setFullClassNameFromResolvedType(javaClassMeta,resolve);
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

    private void parseModifiers(JavaClassMeta javaClassMeta, NodeList<Modifier> modifiers, CompilationUnit rootAst) {
        if(CollectionUtil.isNotEmpty(modifiers)){
            List<String> collect = modifiers.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
            javaClassMeta.setModifiers(collect);
        }

    }
    private void parseSuperJavaClass(JavaClassMeta javaClassMeta, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst) {

        if(typeDeclaration.isClassOrInterfaceDeclaration()){
            NodeList<ClassOrInterfaceType> extendedTypes = ((ClassOrInterfaceDeclaration)typeDeclaration).getExtendedTypes();

            if(CollectionUtil.isNotEmpty(extendedTypes)){
                List<JavaClassMeta> superClasses = new ArrayList<>();
                for (ClassOrInterfaceType extendedType : extendedTypes) {
                    superClasses.add(convertByJavaClass(extendedType));
                }
                javaClassMeta.setSuperClasses(superClasses);
            }
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



    private void parseJavaSouce(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto, TypeDeclaration<?> typeDeclaration, CompilationUnit rootAst) {
        Optional<PackageDeclaration> packageDeclarationOptional = rootAst.getPackageDeclaration();
        if(packageDeclarationOptional.isPresent()){
            PackageDeclaration packageDeclaration = rootAst.getPackageDeclaration().get();

            CompilationUnit node = rootAst;
            List<String> importList = node.getImports().stream().map(NodeWithName::getNameAsString).collect(Collectors.toList());
            javaClassMeta.setImports(importList);

            try {
                URL url = node.getStorage().get().getPath().toUri().toURL();
                String path = url.getPath();
                javaClassMeta.setUrl(url);
                javaClassMeta.setAbsolutePath(node.getStorage().get().getPath().toFile().getAbsolutePath());
                javaClassMeta.setRelativePath(path.substring(path.indexOf("src")));
            } catch (MalformedURLException e) {
            }
            if(typeDeclaration != null){
                Optional<Javadoc> javadoc = typeDeclaration.getJavadoc();
                NodeList<AnnotationExpr> annotations = typeDeclaration.getAnnotations();
                JavaModelMeta javaModelMeta = new JavaModelMeta();
                createJavaDocTag(javadoc,javaModelMeta);
                if(CollectionUtil.isNotEmpty(annotations)){
                    for (AnnotationExpr annotation : annotations) {
                        Name name = annotation.getName();
                        setAnnotationExpr(annotation,javaModelMeta);
                    }
                }

                javaClassMeta.setJavaModelMeta(javaModelMeta);
            }else{
                Optional<Comment> comment = rootAst.getComment();
                if(comment.isPresent()){
                    JavaModelMeta javaModelMeta = new JavaModelMeta();
                    javaModelMeta.setComment(comment.get().getContent());
                    javaClassMeta.setJavaModelMeta(javaModelMeta);
                }
            }


            javaClassMeta.setClassNamePrefix(packageDeclaration.getNameAsString()+".");
            javaClassMeta.setPackageName(packageDeclaration.getNameAsString());
        }
    }

    private void setAnnotationExpr(AnnotationExpr annotation, JavaModelMeta javaModelMeta) {
        List<JavaAnnotationMeta> annotations = javaModelMeta.getAnnotations();
        if(CollectionUtil.isEmpty(annotations)){
            annotations = new ArrayList<>();
        }
        JavaAnnotationMeta javaAnnotationMeta = new JavaAnnotationMeta();
        javaAnnotationMeta.setName(annotation.getNameAsString());
        if(annotation.isSingleMemberAnnotationExpr()){
            SingleMemberAnnotationExpr annotationExpr = (SingleMemberAnnotationExpr) annotation;
            Expression memberValue = annotationExpr.getMemberValue();
            if(memberValue.isArrayInitializerExpr()){
                ArrayInitializerExpr arrayInitializerExpr = (ArrayInitializerExpr) memberValue;
                NodeList<Expression> values = arrayInitializerExpr.getValues();
                if(CollectionUtil.isNotEmpty(values)){
                    for (Expression value : values) {

                    }
                }
            }
        }else if(annotation.isMarkerAnnotationExpr()){
            MarkerAnnotationExpr annotationExpr =(MarkerAnnotationExpr) annotation;

        }else if(annotation.isNormalAnnotationExpr()){
            NormalAnnotationExpr annotationExpr =(NormalAnnotationExpr) annotation;
            NodeList<MemberValuePair> pairs = annotationExpr.getPairs();
        }else if(annotation.isAssignExpr()){

        }else if(annotation.isArrayAccessExpr()){

        }else if(annotation.isBinaryExpr()){

        }else if(annotation.isCastExpr()){

        }else if(annotation.isArrayInitializerExpr()){

        }else if(annotation.isArrayCreationExpr()){

        }else if(annotation.isBooleanLiteralExpr()){

        }else if(annotation.isCharLiteralExpr()){

        }else if(annotation.isThisExpr()){

        }else if(annotation.isNameExpr()){

        }
        annotations.add(javaAnnotationMeta);
        javaModelMeta.setAnnotations(annotations);

    }

    /**
     * 解析class基本信息
     *
     * @param javaClassMeta
     * @param metaFileInfoDto
     * @param typeDeclaration
     */
    private void parseClassMetaProp(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfoDto,TypeDeclaration<?> typeDeclaration) {
        javaClassMeta.setModuleName(metaFileInfoDto.getModuleName());
        javaClassMeta.setProjectName(metaFileInfoDto.getProjectName());
        if(typeDeclaration != null){

            SimpleName simpleName = typeDeclaration.getName();
            javaClassMeta.setClassName(simpleName.getIdentifier());
            javaClassMeta.setFullClassName(typeDeclaration.getFullyQualifiedName().get());
            javaClassMeta.setValue(simpleName.getIdentifier());

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
                    List<String> typeParameterList = new ArrayList<>();
                    for (TypeParameter typeParameter : typeParameters) {
                        typeParameterList.add(typeParameter.getNameAsString());
                    }
                    javaClassMeta.setTypeParameters(typeParameterList);
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
                    javaClassMeta.setInterfaceClass(javaClass.isInnerClass());
                }
            }



        }

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
