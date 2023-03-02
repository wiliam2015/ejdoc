package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaParameterMeta implements Serializable {

    private String name;


    private JavaClassMeta javaClass;

    /**是否是参数变量*/
    private boolean varArgs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaClassMeta getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(JavaClassMeta javaClass) {
        this.javaClass = javaClass;
    }


    public boolean isVarArgs() {
        return varArgs;
    }

    public void setVarArgs(boolean varArgs) {
        this.varArgs = varArgs;
    }
}
