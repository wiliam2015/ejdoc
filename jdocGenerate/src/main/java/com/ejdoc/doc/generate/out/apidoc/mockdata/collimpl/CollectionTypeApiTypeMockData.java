package com.ejdoc.doc.generate.out.apidoc.mockdata.collimpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiMockTypeArgument;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockData;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockDataFactory;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AutoGenerateRealMockData;
import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionTypeApiTypeMockData implements ApiTypeMockData {

    private String className;
    private String fullClassName;
    private String refFullClassName;

    public CollectionTypeApiTypeMockData(String className,String fullClassName,String refFullClassName){
        this.className = className;
        this.fullClassName = fullClassName;
        this.refFullClassName = refFullClassName;

    }
    public CollectionTypeApiTypeMockData(){}

    @Override
    public String mockType() {
        return "java.util.Collection";
    }

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments, String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        String mockContent = "";
        if(CollectionUtil.isNotEmpty(javaDocCommentElementMetas)){
            for (JavaDocCommentElementMeta javaDocCommentElementMeta : javaDocCommentElementMetas) {
                if(StrUtil.equals(javaDocCommentElementMeta.getTagName(),"mock")){
                    mockContent = javaDocCommentElementMeta.getContent();
                }
            }
        }
        if(StrUtil.isNotBlank(mockContent)){
            return paraseArrayVal(mockContent.trim());
        }
        return mockCollectionData(apiMockTypeArguments,name,javaDocCommentElementMetas);


    }

    private Object mockCollectionData(List<ApiMockTypeArgument> apiMockTypeArguments, String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        List<Object> mockContent = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(apiMockTypeArguments)){
            for (ApiMockTypeArgument apiMockTypeArgument : apiMockTypeArguments) {
                ApiTypeMockData apiTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(apiMockTypeArgument.getClassName(), apiMockTypeArgument.getFullClassName(),apiMockTypeArgument.getApiTypeArgumentUniqueName());
                Object mockFieldResult = apiTypeMockData.mockData(apiMockTypeArgument.getChildApiMockTypeArguments(),name,javaDocCommentElementMetas);
                if(mockFieldResult != null){
                    mockContent.add(mockFieldResult);
                }
            }

        }
        return mockContent;
    }

    private Object paraseMapContent(String mockContent){
        Map<Object,Object> mockContentResult =new HashMap<>();
        mockContent = StrUtil.trim(mockContent);
        String key="";
        String val="";
        if(mockContent.matches("^<key\\:.+,\\s*val\\:.+>$")){
            key = mockContent.substring(5,mockContent.indexOf(","));
            val = mockContent.substring(mockContent.indexOf("val:")+4,mockContent.lastIndexOf(">"));
        }
        AutoGenerateRealMockData realMockData = new AutoGenerateRealMockData(key.trim());
        Object mockRealContentKey = realMockData.mockRealData();
        String contentValTrim = val.trim();
        if(contentValTrim.matches("^<key\\:.+,\\s*val\\:.+>$")){
            Object mapVal = paraseMapContent(contentValTrim);
            mockContentResult.put(mockRealContentKey,mapVal);
        }else if(contentValTrim.matches("\\[.+\\]")){
            Object arrVal = paraseArrayVal(contentValTrim);
            mockContentResult.put(mockRealContentKey,arrVal);
        }else{
            AutoGenerateRealMockData realMockValData = new AutoGenerateRealMockData(contentValTrim);
            Object mockRealContentVal = realMockValData.mockRealData();
            mockContentResult.put(mockRealContentKey,mockRealContentVal);
        }

        return mockContentResult;
    }

    private Object paraseArrayVal(String arrayVal){
        List<Object>  result = new ArrayList<>();
        arrayVal = arrayVal.substring(1, arrayVal.length()-1);
        String[] arrayVals = arrayVal.split(",");
        for (String arrayValStr : arrayVals) {
            if(arrayValStr.matches("^<key\\:.+,\\s*val\\:.+>$")){
                Object mapVal = paraseMapContent(arrayValStr);
                result.add(mapVal);
            }else if(arrayValStr.matches("\\[.+\\]")){
                Object arrVal = paraseArrayVal(arrayValStr);
                result.add(arrVal);
            }else{
                AutoGenerateRealMockData realMockValData = new AutoGenerateRealMockData(arrayValStr);
                Object mockRealContentVal = realMockValData.mockRealData();
                result.add(mockRealContentVal);
            }
        }
        return result;
    }
}
