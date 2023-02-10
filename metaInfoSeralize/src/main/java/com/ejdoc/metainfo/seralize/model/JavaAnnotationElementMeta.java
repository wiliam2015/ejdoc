package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaAnnotationElementMeta implements Serializable {

    private List<JavaAnnotationMeta> annotations;

    private String comment;

    private List<JavaDocletTagMeta> tags;


    public List<JavaAnnotationMeta> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<JavaAnnotationMeta> annotations) {
        this.annotations = annotations;
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
}
