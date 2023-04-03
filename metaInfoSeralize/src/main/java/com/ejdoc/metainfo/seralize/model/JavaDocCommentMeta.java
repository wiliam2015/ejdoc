package com.ejdoc.metainfo.seralize.model;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

public class JavaDocCommentMeta {


    private List<JavaDocCommentElementMeta> javaDocCommentElementMetas;

    private String javaDocComment;

    public String getJavaDocComment() {
        if(StrUtil.isNotBlank(javaDocComment)){
            return javaDocComment;
        }
        if(CollectionUtil.isNotEmpty(javaDocCommentElementMetas)){
            StringBuilder comment = new StringBuilder();
            for (JavaDocCommentElementMeta javaDocCommentElementMeta : javaDocCommentElementMetas) {
                comment.append(javaDocCommentElementMeta.getContent());
            }
            return comment.toString();
        }
        return "";
    }

    public void setJavaDocComment(String javaDocComment) {
        this.javaDocComment = javaDocComment;
    }

    public List<JavaDocCommentElementMeta> getJavaDocCommentElementMetas() {
        return javaDocCommentElementMetas;
    }

    public void setJavaDocCommentElementMetas(List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        this.javaDocCommentElementMetas = javaDocCommentElementMetas;
    }
}
