package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class JavaSourceMeta implements Serializable {

    private URL url;

    private JavaPackageMeta javaPackageMeta;

    private List<String> imports;

    private String classNamePrefix;

    private String packageName;

    private JavaModelMeta javaModelMeta;


    public JavaPackageMeta getJavaPackageMeta() {
        return javaPackageMeta;
    }

    public void setJavaPackageMeta(JavaPackageMeta javaPackageMeta) {
        this.javaPackageMeta = javaPackageMeta;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getClassNamePrefix() {
        return classNamePrefix;
    }

    public void setClassNamePrefix(String classNamePrefix) {
        this.classNamePrefix = classNamePrefix;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }
}
