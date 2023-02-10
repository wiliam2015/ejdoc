package com.ejdoc.metainfo.seralize.model;

import java.util.List;

public class JavaModelMeta {
    private String codeBlock;

    private int lineNumber;

    private String comment;

    private List<JavaDocletTagMeta>  tags;

    private List<JavaAnnotationMeta> annotations;

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    public int getLineNumber() {
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
}
