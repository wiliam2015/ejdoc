package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaFieldMeta implements Serializable {

    private JavaClassMeta type;

    private String initializationExpression;

    private boolean enumConstant;

    private JavaClassMeta enumConstantClass;

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

    public boolean isEnumConstant() {
        return enumConstant;
    }

    public void setEnumConstant(boolean enumConstant) {
        this.enumConstant = enumConstant;
    }

    public JavaClassMeta getEnumConstantClass() {
        return enumConstantClass;
    }

    public void setEnumConstantClass(JavaClassMeta enumConstantClass) {
        this.enumConstantClass = enumConstantClass;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }
}
