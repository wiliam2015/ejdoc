package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaModuleMeta implements Serializable {

    private String name;

    private String desc;

    public JavaModuleMeta(){}

    public JavaModuleMeta(String name,String desc){
        this.name = name;
        this.desc = desc;
    }
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

    @Override
    public int hashCode() {
        if(name == null){
            return super.hashCode();
        }
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof JavaModuleMeta){
            JavaModuleMeta objEqual = (JavaModuleMeta)obj;
           return objEqual.getName().equals(this.name);
        }
        return super.equals(obj);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
