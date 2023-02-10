package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaParameterMeta implements Serializable {

    private String name;

    private JavaTypeMeta type;

    private JavaClassMeta javaClass;

    private JavaExecutableMeta executable;

    private JavaClassMeta declaringClass;

    private Boolean varArgs;

    private String value;

    private String fullyQualifiedName;

    private String canonicalName;

    private String resolvedValue;

    private String resolvedGenericValue;

    private String resolvedFullyQualifiedName;

    private String resolvedGenericFullyQualifiedName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaTypeMeta getType() {
        return type;
    }

    public void setType(JavaTypeMeta type) {
        this.type = type;
    }

    public JavaClassMeta getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(JavaClassMeta javaClass) {
        this.javaClass = javaClass;
    }

    public JavaExecutableMeta getExecutable() {
        return executable;
    }

    public void setExecutable(JavaExecutableMeta executable) {
        this.executable = executable;
    }

    public JavaClassMeta getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(JavaClassMeta declaringClass) {
        this.declaringClass = declaringClass;
    }

    public Boolean getVarArgs() {
        return varArgs;
    }

    public void setVarArgs(Boolean varArgs) {
        this.varArgs = varArgs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getResolvedValue() {
        return resolvedValue;
    }

    public void setResolvedValue(String resolvedValue) {
        this.resolvedValue = resolvedValue;
    }

    public String getResolvedGenericValue() {
        return resolvedGenericValue;
    }

    public void setResolvedGenericValue(String resolvedGenericValue) {
        this.resolvedGenericValue = resolvedGenericValue;
    }

    public String getResolvedFullyQualifiedName() {
        return resolvedFullyQualifiedName;
    }

    public void setResolvedFullyQualifiedName(String resolvedFullyQualifiedName) {
        this.resolvedFullyQualifiedName = resolvedFullyQualifiedName;
    }

    public String getResolvedGenericFullyQualifiedName() {
        return resolvedGenericFullyQualifiedName;
    }

    public void setResolvedGenericFullyQualifiedName(String resolvedGenericFullyQualifiedName) {
        this.resolvedGenericFullyQualifiedName = resolvedGenericFullyQualifiedName;
    }
}
