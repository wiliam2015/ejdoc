package com.ejdoc.metainfo.seralize.model;

public class JavaTypeParameterMeta {

    private String name;

    private JavaClassMeta type;

    public JavaTypeParameterMeta(){}

    public JavaTypeParameterMeta(String name){
        this.name = name;
    }

    public JavaTypeParameterMeta(String name,JavaClassMeta type){
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaClassMeta getType() {
        return type;
    }

    public void setType(JavaClassMeta type) {
        this.type = type;
    }
}
