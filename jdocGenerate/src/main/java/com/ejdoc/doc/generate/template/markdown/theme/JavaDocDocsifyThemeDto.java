package com.ejdoc.doc.generate.template.markdown.theme;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

public class JavaDocDocsifyThemeDto {

    private String projectName;

    private String moduleName;

    private String moduleDesc;

    private String packageName;

    private String packageDesc;

    private String className;

    private String packageNamePath;

    private List<JavaDocDocsifyThemeDto> childList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        if(StrUtil.isNotBlank(packageName)){
            this.packageNamePath = packageName.replace(".","/");
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageNamePath() {
        return packageNamePath;
    }

    public void setPackageNamePath(String packageNamePath) {
        this.packageNamePath = packageNamePath;
    }

    public List<JavaDocDocsifyThemeDto> getChildList() {
        return childList;
    }

    public void setChildList(List<JavaDocDocsifyThemeDto> childList) {
        this.childList = childList;
    }

    public boolean addChild(JavaDocDocsifyThemeDto childDto){
        if(this.childList == null){
            this.childList = new ArrayList<>();
        }
        return this.childList.add(childDto);
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }
}
