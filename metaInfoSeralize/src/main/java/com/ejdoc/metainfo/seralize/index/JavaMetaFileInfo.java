package com.ejdoc.metainfo.seralize.index;

import cn.hutool.json.JSONObject;

import java.io.File;

/**
 * java元文件基本信息
 */
public class JavaMetaFileInfo {

    private File file;

    private JSONObject jsonObject;

    private String jsonFilePath;

    private String outFileBasePath;

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

    public String getOutFileBasePath() {
        return outFileBasePath;
    }

    public void setOutFileBasePath(String outFileBasePath) {
        this.outFileBasePath = outFileBasePath;
    }
}
