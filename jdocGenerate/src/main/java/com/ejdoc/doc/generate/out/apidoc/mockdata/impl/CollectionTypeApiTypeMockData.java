package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiMockTypeArgument;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockData;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockDataFactory;
import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeApiTypeMockData implements ApiTypeMockData {


    @Override
    public String mockType() {
        return "java.util.Collection";
    }

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments, String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas) {
        List<Object> mockContent = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(apiMockTypeArguments)){
            for (ApiMockTypeArgument apiMockTypeArgument : apiMockTypeArguments) {
                ApiTypeMockData apiTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(apiMockTypeArgument.getClassName(), apiMockTypeArgument.getFullClassName());
                Object mockFieldResult = apiTypeMockData.mockData(apiMockTypeArgument.getChildApiMockTypeArguments(),"apiTypeArgument",javaDocCommentElementMetas);
                if(mockFieldResult != null){
                    mockContent.add(mockFieldResult);
                }
            }

        }
        return mockContent;
    }

}
