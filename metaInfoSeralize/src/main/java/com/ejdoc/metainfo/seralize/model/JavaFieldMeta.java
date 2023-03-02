package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaFieldMeta implements Serializable {

    private String name;

    private String initializer;

    private JavaClassMeta type;

    private String initializationExpression;

    private boolean enumConstant;

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


}
