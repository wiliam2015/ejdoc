package com.ejdoc.doc.generate.out.apidoc.mockdata;

import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;

import java.util.List;

/**
 * 循环引用mockdata map
 */
public class DefaultCircleRefApiTypeMockData implements ApiTypeMockData{

    private String className;
    private String fullClassName;

    public DefaultCircleRefApiTypeMockData(String className, String fullClassName){
        this.className = className;
        this.fullClassName = fullClassName;

    }
    @Override
    public String mockType() {
        return this.fullClassName;
    }

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments,String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas ){

        return null;
    }


}
