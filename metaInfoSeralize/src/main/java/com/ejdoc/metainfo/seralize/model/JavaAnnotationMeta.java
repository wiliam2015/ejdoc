package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JavaAnnotationMeta implements Serializable {

    private String name;

    private String value;

    private JavaClassMeta type;
    private List<String> parameters;

    private Map<String, Object>  namedParameterMap;

    private int lineNumber;

    private JavaAnnotationElementMeta javaAnnotatedElement;

    private String codeBlock;

    public String getCodeBlock() {
        return codeBlock;
    }


    public JavaClassMeta getType() {
        return type;
    }

    public void setType(JavaClassMeta type) {
        this.type = type;
    }

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

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

    public Map<String, Object> getNamedParameterMap() {
        return namedParameterMap;
    }

    public void setNamedParameterMap(Map<String, Object> namedParameterMap) {
        this.namedParameterMap = namedParameterMap;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public JavaAnnotationElementMeta getJavaAnnotatedElement() {
        return javaAnnotatedElement;
    }

    public void setJavaAnnotatedElement(JavaAnnotationElementMeta javaAnnotatedElement) {
        this.javaAnnotatedElement = javaAnnotatedElement;
    }
}
