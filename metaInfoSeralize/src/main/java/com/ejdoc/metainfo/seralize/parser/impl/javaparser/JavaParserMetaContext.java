package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析上下文 传递中间变量使用
 */
public class JavaParserMetaContext {


    /**
     * 配置的环境变量属性
     */
    private Map<String, String> envProp = new HashMap<>();

    public String getEnvPropVal(String key) {
        return envProp.get(key);
    }

    public String getEnvPropVal(String key,String defaultVal) {
       return  StrUtil.isBlank(envProp.get(key)) ? defaultVal : envProp.get(key);
    }

    public Map<String, String> getEnvProp() {
        return envProp;
    }

    public void addEnvProp(Map<String, String> envProp) {
        if(CollectionUtil.isNotEmpty(envProp)){
            this.envProp.putAll(envProp);
        }
    }

    public void addEnvProp(String key ,String val) {
        this.envProp.put(key,val);
    }
}
