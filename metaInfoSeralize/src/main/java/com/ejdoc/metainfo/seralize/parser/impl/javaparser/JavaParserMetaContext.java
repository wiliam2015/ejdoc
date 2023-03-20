package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析上下文 传递中间变量使用
 */
public class JavaParserMetaContext {

    /**
     * 依赖路径类map
     */
    private Map<String, String> contextMap = new HashMap<>();

    public Map<String, String> getContextMap() {
        return contextMap;
    }

    public String getContextVal(String key) {
        return contextMap.get(key);
    }

    public void addContext(String key, String val){
        contextMap.put(key, val);
    }
}
