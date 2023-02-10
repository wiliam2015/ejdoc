package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaTypeMeta implements Serializable {


    private String binaryName;

    private String canonicalName;

    private String genericCanonicalName;

    private String fullyQualifiedName;

    private String genericFullyQualifiedName;

    private String value;

    private String genericValue;

    private String genericString;



    public String getBinaryName() {
        return binaryName;
    }

    public void setBinaryName(String binaryName) {
        this.binaryName = binaryName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getGenericCanonicalName() {
        return genericCanonicalName;
    }

    public void setGenericCanonicalName(String genericCanonicalName) {
        this.genericCanonicalName = genericCanonicalName;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public String getGenericFullyQualifiedName() {
        return genericFullyQualifiedName;
    }

    public void setGenericFullyQualifiedName(String genericFullyQualifiedName) {
        this.genericFullyQualifiedName = genericFullyQualifiedName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGenericValue() {
        return genericValue;
    }

    public void setGenericValue(String genericValue) {
        this.genericValue = genericValue;
    }

    public String getGenericString() {
        return genericString;
    }

    public void setGenericString(String genericString) {
        this.genericString = genericString;
    }

}
