package com.ejdoc.doc.generate.model;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;

public class DocTemplateThemeInfo {
    /**
     * doc文档输出路径
     */
    private String docOutRootPath;

    private TemplateTypeEnum templateType;

    private String renderFilePath;

    private String jsonFilePath;

    private SeralizeConfig seralizeConfig;

    /**
     * 项目根目录
     */
    private String projectRootPath;

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
}
