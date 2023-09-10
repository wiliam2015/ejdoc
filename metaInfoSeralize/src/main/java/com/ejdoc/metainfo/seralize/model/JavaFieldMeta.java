package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaFieldMeta implements Serializable {

    private String name;

    /**内部唯一ID*/
    private String uniqueId;
    private String initializer;

    private JavaClassMeta type;

    private List<String> modifiers;

    private JavaClassMeta annoDefaultVal;

    private String initializationExpression;

    private Boolean enumField;

    private Boolean annoField;

    private JavaModelMeta javaModelMeta;


    public JavaClassMeta getType() {
        return type;
    }

    public void setType(JavaClassMeta type) {
        this.type = type;
    }

    public String getInitializationExpression() {
        return initializationExpression;
    }

    public void setInitializationExpression(String initializationExpression) {
        this.initializationExpression = initializationExpression;
    }

    public JavaClassMeta getAnnoDefaultVal() {
        return annoDefaultVal;
    }

    public void setAnnoDefaultVal(JavaClassMeta annoDefaultVal) {
        this.annoDefaultVal = annoDefaultVal;
    }

    public Boolean getEnumField() {
        return enumField;
    }

    public void setEnumField(Boolean enumField) {
        this.enumField = enumField;
    }

    public Boolean getAnnoField() {
        return annoField;
    }

    public void setAnnoField(Boolean annoField) {
        this.annoField = annoField;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
