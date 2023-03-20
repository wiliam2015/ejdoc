package com.ejdoc.metainfo.seralize.seralize;

import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;

import java.util.List;
import java.util.Map;

public interface JavaMetaSeralizePlugin {


    /**
     * 执行java序列化插件处理逻辑
     * @param javaMetaServalizePluginContextDto 插件上下文信息对象
     */
    void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto);
}
