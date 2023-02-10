package com.ejdoc.metainfo.seralize.dto;

import java.io.File;

public class MetaFileInfoDto {


    private String projectName;

    private String projectPath;

    private String moduleName;

    private String modulePath;

    private String metaFileName;

    private String metaFilePath;

    private File metaFile;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getMetaFileName() {
        return metaFileName;
    }

    public void setMetaFileName(String metaFileName) {
        this.metaFileName = metaFileName;
    }

    public String getMetaFilePath() {
        return metaFilePath;
    }

    public void setMetaFilePath(String metaFilePath) {
        this.metaFilePath = metaFilePath;
    }

    public File getMetaFile() {
        return metaFile;
    }

    public void setMetaFile(File metaFile) {
        this.metaFile = metaFile;
    }
}
