package com.ejdoc.doc.generate.model;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;

import java.io.File;
import java.util.Locale;

public class DocOutFileInfo {

    /**
     * doc文档输出路径
     */
    private String docOutRootPath;

    private String fileName;

    private String relativeRootPath;

    private String fullFilePath;

    private File jsonFile;

    private Locale locale;

    private TemplateTypeEnum templateType;

    private boolean mainFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRelativeRootPath() {
        return relativeRootPath;
    }

    public void setRelativeRootPath(String relativeRootPath) {
        this.relativeRootPath = relativeRootPath;
    }

    public String getFullFilePath() {
        return fullFilePath;
    }

    public void setFullFilePath(String fullFilePath) {
        this.fullFilePath = fullFilePath;
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public String getDocOutRootPath() {
        return docOutRootPath;
    }

    public void setDocOutRootPath(String docOutRootPath) {
        this.docOutRootPath = docOutRootPath;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public TemplateTypeEnum getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateTypeEnum templateType) {
        this.templateType = templateType;
    }

    public boolean isMainFile() {
        return mainFile;
    }

    public void setMainFile(boolean mainFile) {
        this.mainFile = mainFile;
    }
}
