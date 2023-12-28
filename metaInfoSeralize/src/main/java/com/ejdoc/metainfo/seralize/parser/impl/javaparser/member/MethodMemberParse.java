package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.enums.EnvPropEnum;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaContext;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class MethodMemberParse extends AbstractJavaParseMemberParse{
    private static final Logger log = LoggerFactory.getLogger(MethodMemberParse.class);

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration, JavaParserMetaContext javaParserMetaContext) {
        List<JavaMethodMeta> javaMethodMetas =initJavaMethodMeta(javaClassMeta);
        String compileIncludePrivate = javaParserMetaContext.getEnvPropVal(EnvPropEnum.compile_include_private.getCode(), "");
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                JavaMethodMeta javaMethodMeta = parseMethodMember(member, metaFileInfo, typeDeclaration);
                if(filterModifier(compileIncludePrivate,javaMethodMeta.getModifiers())){
                    javaMethodMetas.add(javaMethodMeta);
                }
            }
        }

        List<JavaMethodMeta> methodMetas = CollectionUtil.sortByProperty(javaMethodMetas, "name");
        replaceMethodFullClassRefByImport(javaClassMeta, methodMetas);
        paraseTypeParameterFlag(methodMetas,javaClassMeta);
        javaClassMeta.setMethods(methodMetas);
    }

    /**
     * 解析方法体对类型参数打标
     * @param methodMetas
     */
    private void paraseTypeParameterFlag(List<JavaMethodMeta> methodMetas,JavaClassMeta javaClassMeta) {
        boolean isClassTypePara = BooleanUtil.isTrue(javaClassMeta.getTypeParameter());
        Map<String, String> typeParameterMap = new HashMap<>();
        if(isClassTypePara){
            typeParameterMap.putAll(javaClassMeta.getTypeParameters().stream().collect(Collectors.toMap(JavaTypeParameterMeta::getName, JavaTypeParameterMeta::getName)));
        }
        for (JavaMethodMeta methodMeta : methodMetas) {
            JavaClassMeta returns = methodMeta.getReturns();
            if(returns == null){
                continue;
            }

            if(CollectionUtil.isNotEmpty(returns.getTypeParameters())){
                returns.setTypeParameter(true);
                typeParameterMap.putAll(returns.getTypeParameters().stream().collect(Collectors.toMap(JavaTypeParameterMeta::getName, JavaTypeParameterMeta::getName)));
            }
            if(typeParameterMap.containsKey(returns.getClassName())){
                returns.setTypeParameter(true);
            }
            List<JavaClassMeta> returnTypeArguments = returns.getTypeArguments();
            setParaseTypeParameterFlag(typeParameterMap, returnTypeArguments);

            List<JavaParameterMeta> parameters = methodMeta.getParameters();
            if(CollectionUtil.isNotEmpty(parameters)){
                for (JavaParameterMeta parameter : parameters) {
                    JavaClassMeta javaClass = parameter.getJavaClass();
                    if(typeParameterMap.containsKey(javaClass.getClassName())){
                        javaClass.setTypeParameter(true);
                    }
                    List<JavaClassMeta> typeArguments = javaClass.getTypeArguments();
                    setParaseTypeParameterFlag(typeParameterMap, typeArguments);
                    if(CollectionUtil.isNotEmpty(typeArguments)){
                        for (JavaClassMeta typeArgument : typeArguments) {
                            if(typeArgument.getTypeArgExtend() == null){
                                continue;
                            }
                            setParaseTypeParameterFlag(typeParameterMap, ListUtil.of(typeArgument.getTypeArgExtend()));
                        }
                    }

                }
            }
        }
    }

    protected void setParaseTypeParameterFlag(Map<String, String> typeParameterMap, List<JavaClassMeta> returnTypeArguments) {
        if(CollectionUtil.isNotEmpty(returnTypeArguments)){
            for (JavaClassMeta typeArgument : returnTypeArguments) {
                if(typeParameterMap.containsKey(typeArgument.getClassName())){
                    typeArgument.setTypeParameter(true);
                }
                setParaseTypeParameterFlag(typeParameterMap,typeArgument.getTypeArguments());
            }
        }
    }

    /**
     * 将解析失败的全名称路径进一步替换成import的
     * @param javaClassMeta
     * @param methodMetas
     */
    private void replaceMethodFullClassRefByImport(JavaClassMeta javaClassMeta, List<JavaMethodMeta> methodMetas) {
        List<JavaClassMeta> readyReplaceFullClassNameList = new ArrayList<>();
        for (JavaMethodMeta methodMeta : methodMetas) {
            addClassMetaList(readyReplaceFullClassNameList,methodMeta.getReturns());
            List<JavaParameterMeta> parameters = methodMeta.getParameters();
            if(CollectionUtil.isNotEmpty(parameters)){
                for (JavaParameterMeta parameter : parameters) {
                    addClassMetaList(readyReplaceFullClassNameList,parameter.getJavaClass());
                }
            }
            List<JavaClassMeta> exceptions = methodMeta.getExceptions();
            if(CollectionUtil.isNotEmpty(exceptions)){
                for (JavaClassMeta exception : exceptions) {
                    addClassMetaList(readyReplaceFullClassNameList,exception);
                }
            }
        }
        replaceFullClassNameByImport(readyReplaceFullClassNameList, javaClassMeta.getImports());
    }


    private List<JavaMethodMeta> initJavaMethodMeta(JavaClassMeta javaClassMeta) {
        if(CollectionUtil.isEmpty(javaClassMeta.getMethods())){
            return new ArrayList<>();
        }
        return javaClassMeta.getMethods();
    }

    protected JavaMethodMeta parseMethodMember(BodyDeclaration<?> member, MetaFileInfoDto metaFileInfo, TypeDeclaration<?> typeDeclaration) {
        JavaMethodMeta javaMethodMeta = new JavaMethodMeta();
        MethodDeclaration methodDeclaration = (MethodDeclaration)member;
        ResolvedMethodDeclaration resolve = methodDeclaration.resolve();

        Optional<Javadoc> javadoc = methodDeclaration.getJavadoc();
        NodeList<AnnotationExpr> annotations = methodDeclaration.getAnnotations();

        //方法名与方法体
        javaMethodMeta.setName(methodDeclaration.getNameAsString());
        javaMethodMeta.setCallSignature(methodDeclaration.getDeclarationAsString());
        //返回类型
        parseMethodReturnType(javaMethodMeta, methodDeclaration, resolve);
        //修饰符
        parseMethodModifiers(javaMethodMeta, methodDeclaration.getModifiers());
        //注解和注释
        parseMethodDocTag(javaMethodMeta, javadoc,annotations);
        //入参与异常
        parseMethodParamAndException(javaMethodMeta, methodDeclaration, resolve);
        //方法唯一ID解析
        parseMethodUniqueId(javaMethodMeta);
        return javaMethodMeta;
    }

    /**
     * 方法唯一ID解析
     * @param javaMethodMeta
     */
    private  void parseMethodUniqueId(JavaMethodMeta javaMethodMeta) {
        StringBuilder uniqueId = new StringBuilder(javaMethodMeta.getName());
        List<JavaParameterMeta> parameters = javaMethodMeta.getParameters();
        if(CollectionUtil.isNotEmpty(parameters)){
            for (JavaParameterMeta parameter : parameters) {
                JavaClassMeta javaClass = parameter.getJavaClass();
                if(javaClass != null){
                    String className = javaClass.getClassName();
                    //数组类型
                    if(StrUtil.isNotBlank(javaClass.getArrayFullClassName())){
                        className = className.replace("[","").replace("]","").trim();
                        className +="-a";
                    }
                    uniqueId.append("-");
                    uniqueId.append(className);
                }
            }
        }
        javaMethodMeta.setUniqueId(uniqueId.toString().toLowerCase());
    }

    private boolean accept(BodyDeclaration<?> member) {
        return member.isMethodDeclaration();
    }

    protected void parseMethodParamAndException(JavaMethodMeta javaMethodMeta, CallableDeclaration methodDeclaration, ResolvedMethodLikeDeclaration resolve) {

        parseMethodParameters(methodDeclaration, resolve, javaMethodMeta);

        parseMethodExceptions(methodDeclaration, resolve, javaMethodMeta);

    }


    private  ResolvedType getThrowResolvedType(ResolvedMethodLikeDeclaration resolve, int i) {
        try {
            ResolvedType specifiedException = resolve.getSpecifiedException(i);
            return specifiedException;
        }catch(UnsolvedSymbolException ue) {
            log.debug("setTypeArgumentsFromType type.resolve UnsolvedSymbolException error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
            return null;
        } catch (Exception e) {
            log.error("getThrowResolvedType error",e);
            return null;
        }
    }

    private  ResolvedType getParamResolvedType(ResolvedParameterDeclaration param) {

        try {
            ResolvedType resolvedParamType = param.getType();
            return resolvedParamType;
        } catch (UnsolvedSymbolException ue){
            log.debug("getParamResolvedType error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
            return null;
        }catch (Exception e) {
            log.error("getParamResolvedType error",e);
            return null;
        }
    }

    private void parseMethodExceptions(CallableDeclaration methodDeclaration, ResolvedMethodLikeDeclaration resolve, JavaMethodMeta javaMethodMeta) {
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

    private void parseMethodParameters(CallableDeclaration methodDeclaration, ResolvedMethodLikeDeclaration resolve, JavaMethodMeta javaMethodMeta) {

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

    protected void parseMethodReturnType(JavaMethodMeta javaMethodMeta, MethodDeclaration methodDeclaration, ResolvedMethodDeclaration resolve) {
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

    protected  void parseMethodDocTag(JavaMethodMeta javaMethodMeta, Optional<Javadoc> javadoc,NodeList<AnnotationExpr> annotations) {
        JavaModelMeta javaModelMeta = new JavaModelMeta();
        //避免方法太长 方法体内容不要了
//            javaModelMeta.setCodeBlock(methodDeclaration.getTokenRange().get().toString());
        createJavaDocTag(javadoc, javaModelMeta);
        setAnnotations(annotations,javaModelMeta);
        javaMethodMeta.setJavaModelMeta(javaModelMeta);
    }


    protected  void parseMethodModifiers(JavaMethodMeta javaMethodMeta, NodeList<Modifier> modifiersList) {
        List<String> modifiers = modifiersList.stream().map(modifier -> modifier.getKeyword().asString()).collect(Collectors.toList());
        javaMethodMeta.setModifiers(modifiers);
    }
    protected static ResolvedType getMethodReturnResolvedType(ResolvedMethodDeclaration resolve) {
        try {
            ResolvedType returnTypeDeclaration = resolve.getReturnType();
            return returnTypeDeclaration;
        } catch (UnsolvedSymbolException ue){
            log.debug("getMethodReturnResolvedType UnsolvedSymbolException",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
            return null;
        }catch (Exception e) {
            log.error("getMethodReturnResolvedType error",e);
            return null;
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










}
