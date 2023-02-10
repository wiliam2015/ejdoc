package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaModuleMeta implements Serializable {

    private String name;


    private List<JavaClassMeta> javaClassMetas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JavaClassMeta> getJavaClassMetas() {
        return javaClassMetas;
    }

    public void setJavaClassMetas(List<JavaClassMeta> javaClassMetas) {
        this.javaClassMetas = javaClassMetas;
    }
}
