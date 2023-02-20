package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaTypeMeta implements Serializable {

    private String fullyQualifiedName;

    private String name;


    private List<String> typeParameters;

    private String dependencyRelativePath;

    private String dependencyAbsolutePath;


    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(List<String> typeParameters) {
        this.typeParameters = typeParameters;
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
