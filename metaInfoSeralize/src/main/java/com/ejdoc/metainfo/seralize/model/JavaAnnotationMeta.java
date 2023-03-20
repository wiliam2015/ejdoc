package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.Map;

public class JavaAnnotationMeta implements Serializable {

    private String name;

    private String value;

    private JavaClassMeta type;

    private Map<String, Object> properties;

    private Integer lineNumber;

    private String codeBlock;

    private Boolean annoField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JavaClassMeta getType() {
        return type;
    }

    public void setType(JavaClassMeta type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    public Boolean getAnnoField() {
        return annoField;
    }

    public void setAnnoField(Boolean annoField) {
        this.annoField = annoField;
    }
}
