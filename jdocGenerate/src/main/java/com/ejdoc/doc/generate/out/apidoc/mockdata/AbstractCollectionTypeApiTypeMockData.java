package com.ejdoc.doc.generate.out.apidoc.mockdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;

import java.util.List;

public abstract class AbstractCollectionTypeApiTypeMockData implements ApiTypeMockData{


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
            return mockContent;
        }
        return mockBaseData(name);
    }

    public abstract Object mockBaseData(String name);
}
