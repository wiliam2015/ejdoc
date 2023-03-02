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
    private Map<String, JavaParserDependPath> dependPathMap = new HashMap<>();

    public Map<String, JavaParserDependPath> getDependPathMap() {
        return dependPathMap;
    }

    public JavaParserDependPath getDependPathByKey(String key) {
        return dependPathMap.get(key);
    }

    public void addDependPath(String key, JavaParserDependPath javaParserDependPath){
        dependPathMap.put(key, javaParserDependPath);
    }
}
