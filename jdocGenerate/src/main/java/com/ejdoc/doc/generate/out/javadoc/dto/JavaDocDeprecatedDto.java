package com.ejdoc.doc.generate.out.javadoc.dto;

import com.ejdoc.metainfo.seralize.model.JavaConstructorMeta;
import com.ejdoc.metainfo.seralize.model.JavaDocletTagMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;

import java.util.ArrayList;
import java.util.List;

public class JavaDocDeprecatedDto {

    private String projectName;

    private String moduleName;

    private String moduleDesc;

    private String packageName;
    private String className;
    private String fullClassName;
    private Boolean deprecatedClass;
    private Boolean deprecatedMethod;
    private Boolean deprecatedContructor;

    private String deprecatedName;
    private String deprecatedDesc;
    private String deprecatedNamePath;

    private JavaMethodMeta methodDetail;

    private JavaConstructorMeta constructorDetail;

    private JavaDocletTagMeta  deprecateTag;

    public JavaDocDeprecatedDto(){}

    public JavaDocDeprecatedDto(String deprecatedName,String deprecatedDesc,String deprecatedNamePath){
        this.deprecatedName= deprecatedName;
        this.deprecatedDesc= deprecatedDesc;
        this.deprecatedNamePath= deprecatedNamePath;
    }
    private JavaDocDeprecatedDto deprecatedClasses;

    private List<JavaDocDeprecatedDto> deprecatedMethods;

    private List<JavaDocDeprecatedDto> deprecatedConstructors;


    public void addJavaDocDeprecatedClass(JavaDocDeprecatedDto deprecatedClass){
        this.deprecatedClasses = deprecatedClass;
    }

    public void addJavaDocDeprecatedMethods(JavaDocDeprecatedDto deprecatedMethod){
        if(deprecatedMethods == null){
            deprecatedMethods = new ArrayList<>();
        }
        deprecatedMethods.add(deprecatedMethod);
    }

    public void addJavaDocDeprecatedConstructors(JavaDocDeprecatedDto deprecatedConstructor){
        if(deprecatedConstructors == null){
            deprecatedConstructors = new ArrayList<>();
        }
        deprecatedConstructors.add(deprecatedConstructor);
    }

    public Boolean getDeprecatedClass() {
        return deprecatedClass;
    }

    public void setDeprecatedClass(Boolean deprecatedClass) {
        this.deprecatedClass = deprecatedClass;
    }

    public Boolean getDeprecatedMethod() {
        return deprecatedMethod;
    }

    public void setDeprecatedMethod(Boolean deprecatedMethod) {
        this.deprecatedMethod = deprecatedMethod;
    }

    public Boolean getDeprecatedContructor() {
        return deprecatedContructor;
    }

    public void setDeprecatedContructor(Boolean deprecatedContructor) {
        this.deprecatedContructor = deprecatedContructor;
    }

    public String getDeprecatedName() {
        return deprecatedName;
    }

    public void setDeprecatedName(String deprecatedName) {
        this.deprecatedName = deprecatedName;
    }

    public String getDeprecatedDesc() {
        return deprecatedDesc;
    }

    public void setDeprecatedDesc(String deprecatedDesc) {
        this.deprecatedDesc = deprecatedDesc;
    }

    public String getDeprecatedNamePath() {
        return deprecatedNamePath;
    }

    public void setDeprecatedNamePath(String deprecatedNamePath) {
        this.deprecatedNamePath = deprecatedNamePath;
    }

    public JavaDocDeprecatedDto getDeprecatedClasses() {
        return deprecatedClasses;
    }

    public void setDeprecatedClasses(JavaDocDeprecatedDto deprecatedClasses) {
        this.deprecatedClasses = deprecatedClasses;
    }

    public List<JavaDocDeprecatedDto> getDeprecatedMethods() {
        return deprecatedMethods;
    }

    public void setDeprecatedMethods(List<JavaDocDeprecatedDto> deprecatedMethods) {
        this.deprecatedMethods = deprecatedMethods;
    }

    public List<JavaDocDeprecatedDto> getDeprecatedConstructors() {
        return deprecatedConstructors;
    }

    public void setDeprecatedConstructors(List<JavaDocDeprecatedDto> deprecatedConstructors) {
        this.deprecatedConstructors = deprecatedConstructors;
    }

    public JavaMethodMeta getMethodDetail() {
        return methodDetail;
    }

    public void setMethodDetail(JavaMethodMeta methodDetail) {
        this.methodDetail = methodDetail;
    }

    public JavaConstructorMeta getConstructorDetail() {
        return constructorDetail;
    }

    public void setConstructorDetail(JavaConstructorMeta constructorDetail) {
        this.constructorDetail = constructorDetail;
    }

    public JavaDocletTagMeta getDeprecateTag() {
        return deprecateTag;
    }

    public void setDeprecateTag(JavaDocletTagMeta deprecateTag) {
        this.deprecateTag = deprecateTag;
    }

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

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }
}
