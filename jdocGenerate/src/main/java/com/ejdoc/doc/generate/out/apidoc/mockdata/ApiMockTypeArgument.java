package com.ejdoc.doc.generate.out.apidoc.mockdata;

import java.util.List;

/**
 * api  mock类型参数
 */
public class ApiMockTypeArgument {

    private String className;

    private String fullClassName;

    private List<ApiMockTypeArgument> childApiMockTypeArguments;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public List<ApiMockTypeArgument> getChildApiMockTypeArguments() {
        return childApiMockTypeArguments;
    }

    public void setChildApiMockTypeArguments(List<ApiMockTypeArgument> childApiMockTypeArguments) {
        this.childApiMockTypeArguments = childApiMockTypeArguments;
    }
}
