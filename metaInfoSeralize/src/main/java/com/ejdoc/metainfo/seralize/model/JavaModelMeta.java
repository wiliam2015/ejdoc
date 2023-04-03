package com.ejdoc.metainfo.seralize.model;

import java.util.List;

public class JavaModelMeta {
    private String codeBlock;

    private Integer lineNumber;

    /**
     * 去除内置标签后的文本内容
     */
    private String comment;

    /**
     * 包含内容标签的文本内容
     */
    private JavaDocCommentMeta javaDocComment;

    private List<JavaDocletTagMeta>  tags;

    private List<JavaAnnotationMeta> annotations;

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<JavaDocletTagMeta> getTags() {
        return tags;
    }

    public void setTags(List<JavaDocletTagMeta> tags) {
        this.tags = tags;
    }

    public List<JavaAnnotationMeta> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<JavaAnnotationMeta> annotations) {
        this.annotations = annotations;
    }

    public JavaDocCommentMeta getJavaDocComment() {
        return javaDocComment;
    }

    public void setJavaDocComment(JavaDocCommentMeta javaDocComment) {
        this.javaDocComment = javaDocComment;
    }
}
