package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaParameterMeta implements Serializable {

    private String name;

    private JavaTypeMeta type;

    private JavaClassMeta javaClass;

    private String dependencyRelativePath;

    private String dependencyAbsolutePath;

    private JavaExecutableMeta executable;

    private JavaClassMeta declaringClass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaTypeMeta getType() {
        return type;
    }

    public void setType(JavaTypeMeta type) {
        this.type = type;
    }

    public JavaClassMeta getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(JavaClassMeta javaClass) {
        this.javaClass = javaClass;
    }

    public JavaExecutableMeta getExecutable() {
        return executable;
    }

    public void setExecutable(JavaExecutableMeta executable) {
        this.executable = executable;
    }

    public JavaClassMeta getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(JavaClassMeta declaringClass) {
        this.declaringClass = declaringClass;
    }

    public String getDependencyRelativePath() {
        return dependencyRelativePath;
    }

    public void setDependencyRelativePath(String dependencyRelativePath) {
        this.dependencyRelativePath = dependencyRelativePath;
    }

    public String getDependencyAbsolutePath() {
        return dependencyAbsolutePath;
    }

    public void setDependencyAbsolutePath(String dependencyAbsolutePath) {
        this.dependencyAbsolutePath = dependencyAbsolutePath;
    }
}
