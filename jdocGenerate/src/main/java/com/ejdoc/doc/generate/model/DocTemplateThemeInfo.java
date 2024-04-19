package com.ejdoc.doc.generate.model;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;

import java.util.Map;

public class DocTemplateThemeInfo {
    /**
     * doc文档输出路径
     */
    private String docOutRootPath;

    private TemplateTypeEnum templateType;

    private String renderFilePath;
    /**渲染文件根目录*/
    private String renderFileRootPath;

    private String jsonFilePath;

    private SeralizeConfig seralizeConfig;

    /**
     * 项目根目录
     */
    private String projectRootPath;
    /**当前版本*/
    private String version;

    /**模板透传属性*/
    private Map<String,String> templateCustomProp;

    public String getDocOutRootPath() {
        return docOutRootPath;
    }

    public void setDocOutRootPath(String docOutRootPath) {
        this.docOutRootPath = docOutRootPath;
    }

    public TemplateTypeEnum getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateTypeEnum templateType) {
        this.templateType = templateType;
    }

    public String getRenderFilePath() {
        return renderFilePath;
    }

    public void setRenderFilePath(String renderFilePath) {
        this.renderFilePath = renderFilePath;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public SeralizeConfig getSeralizeConfig() {
        return seralizeConfig;
    }

    public void setSeralizeConfig(SeralizeConfig seralizeConfig) {
        this.seralizeConfig = seralizeConfig;
    }

    public String getProjectRootPath() {
        return projectRootPath;
    }

    public void setProjectRootPath(String projectRootPath) {
        this.projectRootPath = projectRootPath;
    }

    public String getRenderFileRootPath() {
        return renderFileRootPath;
    }

    public void setRenderFileRootPath(String renderFileRootPath) {
        this.renderFileRootPath = renderFileRootPath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getTemplateCustomProp() {
        return templateCustomProp;
    }

    public void setTemplateCustomProp(Map<String, String> templateCustomProp) {
        this.templateCustomProp = templateCustomProp;
    }
}
