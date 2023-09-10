package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;
import com.ejdoc.metainfo.seralize.model.*;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.javadoc.description.JavadocInlineTag;
import com.github.javaparser.javadoc.description.JavadocSnippet;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.resolution.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 解析基类，公共方法
 */
public class BaseJavaParse {
    private static final Logger log = LoggerFactory.getLogger(BaseJavaParse.class);


    protected void setFullClassNameFromResolvedType(JavaClassMeta classMeta, ResolvedType resolve) {
        try {
            if(resolve == null){
                return;
            }

            if(resolve.isArray()){
                ResolvedArrayType resolvedArrayType = resolve.asArrayType();
                ResolvedType componentType = resolvedArrayType.getComponentType();
                classMeta.setArrayFullClassName(resolvedArrayType.describe());
                setFullClassNameFromResolvedType(classMeta,componentType);
            }else if(resolve.isConstraint()){
                ResolvedLambdaConstraintType resolvedLambdaConstraintType = resolve.asConstraintType();
                classMeta.setFullClassName( resolvedLambdaConstraintType.describe());
            }else if(resolve.isPrimitive()){
                ResolvedPrimitiveType resolvedPrimitiveType = resolve.asPrimitive();
                String boxTypeQName = resolvedPrimitiveType.getBoxTypeQName();
                classMeta.setFullClassName( boxTypeQName);
            }else if(resolve.isTypeVariable()){
                ResolvedTypeVariable resolvedTypeVariable = resolve.asTypeVariable();
                String qualifiedName = resolvedTypeVariable.qualifiedName();
                classMeta.setFullClassName(qualifiedName);
            }else if(resolve.isReferenceType()){
                ResolvedReferenceType resolvedReferenceType = resolve.asReferenceType();
                String qualifiedName = resolvedReferenceType.getQualifiedName();
                classMeta.setFullClassName(qualifiedName);
            }else if(resolve.isUnionType()){
                ResolvedUnionType resolvedUnionType = resolve.asUnionType();
                classMeta.setFullClassName( resolvedUnionType.describe());
            }else if(resolve.isWildcard()){
                ResolvedWildcard resolvedWildcard = resolve.asWildcard();
                classMeta.setFullClassName( resolvedWildcard.describe());
            }
        }catch(UnsolvedSymbolException ue) {
            log.debug("setFullClassNameFromResolvedType type.resolve UnsolvedSymbolException error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
        } catch (Exception e) {
            log.error("setFullClassNameFromResolvedType error",e);
        }
    }



    protected void setAnnotations(NodeList<AnnotationExpr> annotations, JavaModelMeta javaModelMeta) {
        if(CollectionUtil.isNotEmpty(annotations)){
            for (AnnotationExpr annotation : annotations) {
                setAnnotationExpr(annotation, javaModelMeta);
            }
        }
    }


    protected void setAnnotationExpr(AnnotationExpr annotation, JavaModelMeta javaModelMeta) {
        List<JavaAnnotationMeta> annotations = javaModelMeta.getAnnotations();
        if(CollectionUtil.isEmpty(annotations)){
            annotations = new ArrayList<>();
        }


        Map<String, Object> namedParameterMap = new HashMap<>();
        JavaAnnotationMeta javaAnnotationMeta = new JavaAnnotationMeta();

        try {
            ResolvedAnnotationDeclaration resolve = annotation.resolve();
            JavaClassMeta classMeta = new JavaClassMeta();
            classMeta.setClassName(annotation.getNameAsString());
            classMeta.setFullClassName(resolve.getQualifiedName());
            javaAnnotationMeta.setType(classMeta);
        }catch(UnsolvedSymbolException ue) {
            log.debug("setAnnotationExpr type.resolve UnsolvedSymbolException error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
        } catch (Exception e) {
            log.error("setAnnotationExpr set type error",e);
        }

        javaAnnotationMeta.setName(annotation.getNameAsString());
        if(annotation.isSingleMemberAnnotationExpr()){
            SingleMemberAnnotationExpr annotationExpr = (SingleMemberAnnotationExpr) annotation;
            List<JavaClassMeta> list = ListUtil.list(false);
            parseExpression(annotationExpr.getMemberValue(),list);
            if(CollectionUtil.size(list) > 1){
                List<String> expressionValList = list.stream().map(JavaClassMeta::getValue).collect(Collectors.toList());
                namedParameterMap.put("value",expressionValList);
            }else if(CollectionUtil.size(list) == 1){
                namedParameterMap.put("value",list.get(0).getValue());
            }
        }else if(annotation.isMarkerAnnotationExpr()){
            MarkerAnnotationExpr annotationExpr =(MarkerAnnotationExpr) annotation;

        }else if(annotation.isNormalAnnotationExpr()){
            NormalAnnotationExpr annotationExpr =(NormalAnnotationExpr) annotation;
            NodeList<MemberValuePair> pairs = annotationExpr.getPairs();
            if(CollectionUtil.isNotEmpty(pairs)){

                for (MemberValuePair pair : pairs) {
                    List<JavaClassMeta> list = ListUtil.list(false);
                    parseExpression(pair.getValue(),list);
                    if(CollectionUtil.size(list) > 1){
                        List<String> expressionValList = list.stream().map(JavaClassMeta::getValue).collect(Collectors.toList());
                        namedParameterMap.put(pair.getNameAsString(),expressionValList);
                    }else if(CollectionUtil.size(list) == 1){
                        namedParameterMap.put(pair.getNameAsString(),list.get(0).getValue());
                    }
                }
            }
        }
        javaAnnotationMeta.setProperties(namedParameterMap);
        annotations.add(javaAnnotationMeta);
        javaModelMeta.setAnnotations(annotations);

    }
    /**
     * 创建javadoc标签
     * @param javadoc
     * @param javaModelMeta
     */
    protected  void createJavaDocTag(Optional<Javadoc> javadoc, JavaModelMeta javaModelMeta) {
        List<JavaDocletTagMeta> tags = new ArrayList<>();
        if(javadoc.isPresent()){
            JavadocDescription description = javadoc.get().getDescription();
            List<JavadocDescriptionElement> elements = description.getElements();


            if(CollectionUtil.isNotEmpty(elements)){
                List<JavaDocCommentElementMeta> commentMetas = getJavaDocCommentElementMetas(elements);
                JavaDocCommentMeta javaDocCommentMeta = new JavaDocCommentMeta();
                javaDocCommentMeta.setJavaDocCommentElementMetas(commentMetas);
                javaModelMeta.setComment(javaDocCommentMeta.getJavaDocComment());
                javaModelMeta.setJavaDocComment(javaDocCommentMeta);
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
                    List<JavadocDescriptionElement> docTags = blockTag.getContent().getElements();

                    if(CollectionUtil.size(docTags) > 0){
                        docletTagMeta.setValues(getJavaDocCommentElementMetas(docTags));
                    }

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

    private  List<JavaDocCommentElementMeta> getJavaDocCommentElementMetas(List<JavadocDescriptionElement> elements) {
        List<JavaDocCommentElementMeta> commentMetas = new ArrayList<>();
        JavaDocCommentElementMeta javaDocCommentElementMeta = null;
        for (JavadocDescriptionElement element : elements) {
            if(element instanceof JavadocInlineTag){
                javaDocCommentElementMeta = new JavaDocCommentElementMeta();
                JavadocInlineTag inlineTag = (JavadocInlineTag)element;
                javaDocCommentElementMeta.setContent(inlineTag.getContent());
                javaDocCommentElementMeta.setType(inlineTag.getType().name());
                javaDocCommentElementMeta.setTagName(inlineTag.getName());
                commentMetas.add(javaDocCommentElementMeta);
            }else if(element instanceof JavadocSnippet){
                javaDocCommentElementMeta = new JavaDocCommentElementMeta();
                JavadocSnippet javadocSnippet = (JavadocSnippet)element;
                javaDocCommentElementMeta.setContent(javadocSnippet.toText());
                javaDocCommentElementMeta.setType(JavaDocCommentTypeEnum.TEXT.getName());
                javaDocCommentElementMeta.setTagName(JavaDocCommentTypeEnum.TEXT.getCode());
                commentMetas.add(javaDocCommentElementMeta);
            }
        }
        return commentMetas;
    }


    protected void parseExpression(Expression expression,List<JavaClassMeta> result) {
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        if(expression.isStringLiteralExpr()){
            StringLiteralExpr expr = (StringLiteralExpr) expression;
            javaClassMeta.setValue(expr.getValue());
            javaClassMeta.setClassName("String");
            javaClassMeta.setFullClassName("java.lang.String");
            result.add(javaClassMeta);
        }else if(expression.isClassExpr()){
            ClassExpr expr =(ClassExpr) expression;
            javaClassMeta.setClassName(expr.getTypeAsString());
            javaClassMeta.setValue(expr.toString());
            setFieldResolvedTypeDeclaration(javaClassMeta,expr.getType());
            result.add(javaClassMeta);
        }else if(expression.isBooleanLiteralExpr()){
            BooleanLiteralExpr expr = (BooleanLiteralExpr) expression;
            javaClassMeta.setValue(String.valueOf(expr.getValue()));
            javaClassMeta.setClassName("boolean");
            javaClassMeta.setFullClassName("java.lang.Boolean");
            result.add(javaClassMeta);
        }else if(expression.isFieldAccessExpr()){
            FieldAccessExpr expr =(FieldAccessExpr) expression;
            javaClassMeta.setClassName(expr.getScope().toString());
            javaClassMeta.setFullClassName(expr.getScope().toString());
            javaClassMeta.setValue(expr.getNameAsString());
            result.add(javaClassMeta);
        }else if(expression.isNameExpr()){
            NameExpr expr =(NameExpr) expression;
            javaClassMeta.setValue(expr.getNameAsString());
            result.add(javaClassMeta);
        }else if(expression.isArrayInitializerExpr()){
            ArrayInitializerExpr expr =(ArrayInitializerExpr) expression;
            NodeList<Expression> values = expr.getValues();
            if(CollectionUtil.isNotEmpty(values)){
                for (Expression value : values) {
                    parseExpression(value,result);
                }
            }
        }
    }


    protected void setFieldResolvedTypeDeclaration(JavaClassMeta returnTypeMeta, Type type) {
        try {
            ResolvedType resolve = type.resolve();
            setFullClassNameFromResolvedType(returnTypeMeta,resolve);
        } catch (UnsolvedSymbolException ue){
            log.debug("setFieldResolvedTypeDeclaration error",ue);
            UnSolvedSymbolTool.addUnSolveTOCache(ue.getMessage());
        }
        catch (Exception e) {
            log.error("setFieldResolvedTypeDeclaration error",e);
        }

    }

}
