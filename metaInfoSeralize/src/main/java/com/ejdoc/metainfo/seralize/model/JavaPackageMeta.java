package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaPackageMeta implements Serializable {

    private String name;

    private JavaPackageMeta parentPackage;


    private List<JavaPackageMeta> subPackages;

    private JavaModelMeta javaModelMeta;


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

    public JavaPackageMeta getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(JavaPackageMeta parentPackage) {
        this.parentPackage = parentPackage;
    }

    public List<JavaPackageMeta> getSubPackages() {
        return subPackages;
    }

    public void setSubPackages(List<JavaPackageMeta> subPackages) {
        this.subPackages = subPackages;
    }
}
