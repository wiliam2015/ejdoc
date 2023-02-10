package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;

public class JavaConstructorMeta implements Serializable {

    private JavaMemberMeta javaMemberMeta;

    private JavaModelMeta javaModelMeta;

    private JavaExecutableMeta javaExecutableMeta;

    public JavaMemberMeta getJavaMemberMeta() {
        return javaMemberMeta;
    }

    public void setJavaMemberMeta(JavaMemberMeta javaMemberMeta) {
        this.javaMemberMeta = javaMemberMeta;
    }

    public JavaModelMeta getJavaModelMeta() {
        return javaModelMeta;
    }

    public void setJavaModelMeta(JavaModelMeta javaModelMeta) {
        this.javaModelMeta = javaModelMeta;
    }

    public JavaExecutableMeta getJavaExecutableMeta() {
        return javaExecutableMeta;
    }

    public void setJavaExecutableMeta(JavaExecutableMeta javaExecutableMeta) {
        this.javaExecutableMeta = javaExecutableMeta;
    }
}
