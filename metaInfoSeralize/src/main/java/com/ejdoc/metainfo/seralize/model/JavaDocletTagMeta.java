package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JavaDocletTagMeta implements Serializable {

    private String type;

    private String tagName;
    private String name;

    private String value;

    private List<String> parameters;


    private Map<String, String> namedParameterMap;

    private int lineNumber;

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

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
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
}
