package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JavaDocletTagMeta implements Serializable {

    private String type;

    private String tagName;
    private String name;

    private String value;

    private List<JavaDocCommentElementMeta> values;

    private List<String> parameters;


    private Map<String, String> namedParameterMap;

    private Integer lineNumber;

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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getNamedParameterMap() {
        return namedParameterMap;
    }

    public void setNamedParameterMap(Map<String, String> namedParameterMap) {
        this.namedParameterMap = namedParameterMap;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<JavaDocCommentElementMeta> getValues() {
        return values;
    }

    public void setValues(List<JavaDocCommentElementMeta> values) {
        this.values = values;
    }
}
