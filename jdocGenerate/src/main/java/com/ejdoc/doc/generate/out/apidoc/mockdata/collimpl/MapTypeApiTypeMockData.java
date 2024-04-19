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

public class MapTypeApiTypeMockData implements ApiTypeMockData {



    private String className;
    private String fullClassName;
    private String refFullClassName;


    public MapTypeApiTypeMockData(){}

    public MapTypeApiTypeMockData(String className,String fullClassName,String refFullClassName){
        this.className = className;
        this.fullClassName = fullClassName;
        this.refFullClassName = refFullClassName;

    }

    @Override
    public String mockType() {
        return "java.util.Map";
    }

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments,String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        String mockContent = "";
        if(CollectionUtil.isNotEmpty(javaDocCommentElementMetas)){
            for (JavaDocCommentElementMeta javaDocCommentElementMeta : javaDocCommentElementMetas) {
                if(StrUtil.equals(javaDocCommentElementMeta.getTagName(),"mock")){
                    mockContent = javaDocCommentElementMeta.getContent();
                }
            }
        }
        if(StrUtil.isNotBlank(mockContent)){
            return paraseMapContent(mockContent);
        }
        return mockMapData(apiMockTypeArguments,name,javaDocCommentElementMetas);
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

    public Object mockMapData(List<ApiMockTypeArgument> apiMockTypeArguments, String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        Map<Object,Object> mockContent =new HashMap<>();
        if(CollectionUtil.size(apiMockTypeArguments) == 2){
            ApiMockTypeArgument mapKeyApiMock = apiMockTypeArguments.get(0);
            ApiMockTypeArgument mapValApiMock = apiMockTypeArguments.get(1);

            ApiTypeMockData mapKeyApiMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(mapKeyApiMock.getClassName(), mapKeyApiMock.getFullClassName(),mapKeyApiMock.getApiTypeArgumentUniqueName());
            Object mapKeyApiFieldResult = mapKeyApiMockData.mockData(mapKeyApiMock.getChildApiMockTypeArguments(),name,javaDocCommentElementMetas);
            ApiTypeMockData mapKeyValMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(mapValApiMock.getClassName(), mapValApiMock.getFullClassName(),mapValApiMock.getApiTypeArgumentUniqueName());
            Object mapKeyValFieldResult = mapKeyValMockData.mockData(mapValApiMock.getChildApiMockTypeArguments(),name,javaDocCommentElementMetas);
            mockContent.put(mapKeyApiFieldResult,mapKeyValFieldResult);

        }else{
            mockContent.put("mockKey","mockValue");
        }
        return mockContent;
    }

}
