package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JavaParameterMeta implements Serializable {

    private String name;


    private JavaClassMeta javaClass;

    /**是否是参数变量*/
    private boolean varArgs;

    private Map<String,Object> extProp;

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
