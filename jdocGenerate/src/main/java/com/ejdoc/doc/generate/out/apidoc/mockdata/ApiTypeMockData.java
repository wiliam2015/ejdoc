package com.ejdoc.doc.generate.out.apidoc.mockdata;

import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;

import java.util.List;
import java.util.Map;

/**
 * api类型mock数据使用
 */
public interface ApiTypeMockData {

    /**
     * 支持mock的类型
     * @return
     */
    String mockType();

    Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments ,String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas,int invokeCount );
}
