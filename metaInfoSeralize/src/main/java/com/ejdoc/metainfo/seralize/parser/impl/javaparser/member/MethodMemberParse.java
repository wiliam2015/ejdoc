package com.ejdoc.metainfo.seralize.parser.impl.javaparser.member;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MethodMemberParse extends AbstractJavaParseMemberParse{
    private static final Logger log = LoggerFactory.getLogger(MethodMemberParse.class);

    @Override
    protected void parseBodyDeclarationToJavaClassMeta(JavaClassMeta javaClassMeta, MetaFileInfoDto metaFileInfo, NodeList<BodyDeclaration<?>> members, TypeDeclaration<?> typeDeclaration) {
        List<JavaMethodMeta> javaMethodMetas =initJavaMethodMeta(javaClassMeta);
        for (BodyDeclaration<?> member : members) {
            if(accept(member)){
                javaMethodMetas.add(parseMethodMember(member,metaFileInfo,typeDeclaration));
            }
        }
        javaClassMeta.setMethods(javaMethodMetas);
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

        return javaMethodMeta;
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
                    typeClassMeth = new JavaClassMeta();
                    ResolvedType resolve = null;
                    try {
                        resolve = type.resolve();
                    }catch(UnsolvedSymbolException ue) {
                        log.debug("setTypeArgumentsFromType type.resolve UnsolvedSymbolException error",ue);
                        UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
                    }catch (Exception e) {
                        log.error("setTypeArgumentsFromType type.resolve error {}",paramClass.getClassName(),e);
                    }
                    typeClassMeth.setClassName(type.asString());
                    setFullClassNameFromResolvedType(typeClassMeth,resolve);
                    typeArgumentsList.add(typeClassMeth);
                    setTypeArgumentsFromType(type,typeClassMeth);
                }
                paramClass.setTypeArguments(typeArgumentsList);
            }
        }
    }


    private  void setTypeParametersFromDeclaration(NodeList<TypeParameter> typeParameters, JavaClassMeta returnType) {
        if(CollectionUtil.isNotEmpty(typeParameters)){
            List<String> typeParamList = typeParameters.stream().map(Node::toString).collect(Collectors.toList());
            returnType.setTypeParameters(typeParamList);
        }
    }







}
