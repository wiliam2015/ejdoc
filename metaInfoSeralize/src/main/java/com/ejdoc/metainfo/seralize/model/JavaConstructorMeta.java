package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaConstructorMeta implements Serializable {


    private String name;
    private String callSignature;

    private List<String> modifiers;

    private JavaModelMeta javaModelMeta;

    List<JavaParameterMeta> parameters;

    private List<JavaClassMeta> exceptions;

    private String sourceCode;

    private Boolean varArgs;

    private Boolean privates;

    private Boolean protecteds;

    private Boolean publics;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallSignature() {
        return callSignature;
    }

    public void setCallSignature(String callSignature) {
        this.callSignature = callSignature;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }

    public List<JavaParameterMeta> getParameters() {
        return parameters;
    }

    public void setParameters(List<JavaParameterMeta> parameters) {
        this.parameters = parameters;
    }

    public List<JavaClassMeta> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<JavaClassMeta> exceptions) {
        this.exceptions = exceptions;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Boolean getVarArgs() {
        return varArgs;
    }

    public void setVarArgs(Boolean varArgs) {
        this.varArgs = varArgs;
    }

    public Boolean getPrivates() {
        return privates;
    }

    public void setPrivates(Boolean privates) {
        this.privates = privates;
    }

    public Boolean getProtecteds() {
        return protecteds;
    }

    public void setProtecteds(Boolean protecteds) {
        this.protecteds = protecteds;
    }

    public Boolean getPublics() {
        return publics;
    }

    public void setPublics(Boolean publics) {
        this.publics = publics;
    }
}
