package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaMethodMeta implements Serializable {

    private String name;
    /**方法内部唯一ID*/
    private String uniqueId;
    private String callSignature;

    private List<String> modifiers;

    private JavaModelMeta javaModelMeta;

    private JavaClassMeta returns;

    List<JavaParameterMeta> parameters;

    private List<JavaClassMeta> exceptions;

    /**默认值,枚举类型使用*/
    private JavaClassMeta defaultValue;

    private Boolean defaultMethod;

    private String sourceCode;

    private Boolean varArgs;

    private Boolean abstracts;

    private Boolean finals;

    private Boolean natives;

    private Boolean privates;

    private Boolean protecteds;

    private Boolean publics;

    private Boolean statics;

    private Boolean strictfps;

    private Boolean synchronizeds;

    private Boolean transients;

    private Boolean volatiles;

    /**
     * 扩展属性
     */
    private Map<String,Object> extProp;

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

    public JavaClassMeta getReturns() {
        return returns;
    }

    public void setReturns(JavaClassMeta returns) {
        this.returns = returns;
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

    public Boolean getDefaultMethod() {
        return defaultMethod;
    }

    public void setDefaultMethod(Boolean defaultMethod) {
        this.defaultMethod = defaultMethod;
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

    public Boolean getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(Boolean abstracts) {
        this.abstracts = abstracts;
    }

    public Boolean getFinals() {
        return finals;
    }

    public void setFinals(Boolean finals) {
        this.finals = finals;
    }

    public Boolean getNatives() {
        return natives;
    }

    public void setNatives(Boolean natives) {
        this.natives = natives;
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

    public Boolean getStatics() {
        return statics;
    }

    public void setStatics(Boolean statics) {
        this.statics = statics;
    }

    public Boolean getStrictfps() {
        return strictfps;
    }

    public void setStrictfps(Boolean strictfps) {
        this.strictfps = strictfps;
    }

    public Boolean getSynchronizeds() {
        return synchronizeds;
    }

    public void setSynchronizeds(Boolean synchronizeds) {
        this.synchronizeds = synchronizeds;
    }

    public Boolean getTransients() {
        return transients;
    }

    public void setTransients(Boolean transients) {
        this.transients = transients;
    }

    public Boolean getVolatiles() {
        return volatiles;
    }

    public void setVolatiles(Boolean volatiles) {
        this.volatiles = volatiles;
    }

    public JavaClassMeta getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(JavaClassMeta defaultValue) {
        this.defaultValue = defaultValue;
    }


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Map<String, Object> getExtProp() {
        return extProp;
    }

    public void setExtProp(Map<String, Object> extProp) {
        this.extProp = extProp;
    }

    public void putExtProp(String key,Object value){
        if(this.extProp == null){
            this.extProp = new HashMap<>();
        }
        this.extProp.put(key,value);
    }
}
