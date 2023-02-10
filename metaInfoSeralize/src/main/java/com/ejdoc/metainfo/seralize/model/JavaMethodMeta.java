package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaMethodMeta implements Serializable {

    private String name;
    private String callSignature;

    private JavaClassMeta returns;

    private Boolean propertyAccessor;

    private JavaTypeMeta propertyType;


    private String propertyName;

    private JavaTypeMeta returnType;

    private Boolean defaultMethod;

    private JavaModelMeta javaModelMeta;

    private JavaMemberMeta javaMemberMeta;


    private JavaExecutableMeta javaExecutableMeta;

    public String getCallSignature() {
        return callSignature;
    }

    public void setCallSignature(String callSignature) {
        this.callSignature = callSignature;
    }

    public JavaClassMeta getReturns() {
        return returns;
    }

    public void setReturns(JavaClassMeta returns) {
        this.returns = returns;
    }

    public Boolean getPropertyAccessor() {
        return propertyAccessor;
    }

    public void setPropertyAccessor(Boolean propertyAccessor) {
        this.propertyAccessor = propertyAccessor;
    }

    public JavaTypeMeta getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(JavaTypeMeta propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public JavaTypeMeta getReturnType() {
        return returnType;
    }

    public void setReturnType(JavaTypeMeta returnType) {
        this.returnType = returnType;
    }

    public Boolean getDefaultMethod() {
        return defaultMethod;
    }

    public void setDefaultMethod(Boolean defaultMethod) {
        this.defaultMethod = defaultMethod;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }

    public JavaMemberMeta getJavaMemberMeta() {
        return javaMemberMeta;
    }

    public void setJavaMemberMeta(JavaMemberMeta javaMemberMeta) {
        this.javaMemberMeta = javaMemberMeta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaExecutableMeta getJavaExecutableMeta() {
        return javaExecutableMeta;
    }

    public void setJavaExecutableMeta(JavaExecutableMeta javaExecutableMeta) {
        this.javaExecutableMeta = javaExecutableMeta;
    }
}
