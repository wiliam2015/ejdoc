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

    private String fullClassName;

    private String packageNamePath;
    /**
     * 层级
     */
    private int hierarchy;

    private List<JavaDocDocsifyThemeDto> childList;

    private List<JavaDocDocsifyThemeDto> interfaceList;

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

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public boolean addInterface(JavaDocDocsifyThemeDto interfaceDto){
        if(this.interfaceList == null){
            this.interfaceList = new ArrayList<>();
        }
        return this.interfaceList.add(interfaceDto);
    }
    public List<JavaDocDocsifyThemeDto> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<JavaDocDocsifyThemeDto> interfaceList) {
        this.interfaceList = interfaceList;
    }
}
