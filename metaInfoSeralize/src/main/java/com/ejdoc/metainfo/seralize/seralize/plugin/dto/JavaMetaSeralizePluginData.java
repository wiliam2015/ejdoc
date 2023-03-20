package com.ejdoc.metainfo.seralize.seralize.plugin.dto;

import cn.hutool.json.JSONObject;

import java.io.File;

public class JavaMetaSeralizePluginData {

    private File file;

    private JSONObject jsonObject;

    private String jsonFilePath;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }
}
