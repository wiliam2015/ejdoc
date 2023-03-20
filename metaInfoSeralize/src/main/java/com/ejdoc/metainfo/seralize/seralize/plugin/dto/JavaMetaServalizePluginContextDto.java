package com.ejdoc.metainfo.seralize.seralize.plugin.dto;

import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;

import java.util.List;
import java.util.Map;

public class JavaMetaServalizePluginContextDto {

    private String seralizeOutPath;

    private SeralizeConfig seralizeConfig;

    private Map<String,JavaMetaSeralizePluginData> metaSeralizeFileIndex;

    private List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList;

    public String getSeralizeOutPath() {
        return seralizeOutPath;
    }

    public void setSeralizeOutPath(String seralizeOutPath) {
        this.seralizeOutPath = seralizeOutPath;
    }

    public SeralizeConfig getSeralizeConfig() {
        return seralizeConfig;
    }

    public void setSeralizeConfig(SeralizeConfig seralizeConfig) {
        this.seralizeConfig = seralizeConfig;
    }

    public Map<String, JavaMetaSeralizePluginData> getMetaSeralizeFileIndex() {
        return metaSeralizeFileIndex;
    }

    public void setMetaSeralizeFileIndex(Map<String, JavaMetaSeralizePluginData> metaSeralizeFileIndex) {
        this.metaSeralizeFileIndex = metaSeralizeFileIndex;
    }

    public List<JavaMetaSeralizePluginData> getAllJavaMetaSeralizeClassList() {
        return allJavaMetaSeralizeClassList;
    }

    public void setAllJavaMetaSeralizeClassList(List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList) {
        this.allJavaMetaSeralizeClassList = allJavaMetaSeralizeClassList;
    }
}
