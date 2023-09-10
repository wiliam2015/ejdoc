package com.ejdoc.doc.generate.comment.dto;

import com.ejdoc.metainfo.seralize.model.JavaClassMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * link标签dto
 */
public class CommentLinkDto {

    //基本信息 start
    private String fullName;

    private String packageName;

    private String memberName;

    private String labelName;

    private String methodName;

    private String fieldName;

    private List<String> params;


    private boolean classLink;

    private boolean onlyClassLink;
    /**全路径引用类*/
    private boolean fullClassPathLink;

    private boolean fieldLink;

    private boolean methodLink;

    //基本信息 end

    //加工信息 start
    /**使用自定义名称*/
    private boolean useCustomLableName;

    private List<String> imports;

    /**link标签所在类*/
    private JavaClassMeta linkJavaClassMeta;
    /**link标签引用类*/
    private JavaClassMeta linkRefJavaClassMeta;
    //加工信息 end

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public JavaClassMeta getLinkJavaClassMeta() {
        return linkJavaClassMeta;
    }

    public void setLinkJavaClassMeta(JavaClassMeta linkJavaClassMeta) {
        this.linkJavaClassMeta = linkJavaClassMeta;
    }

    public boolean isClassLink() {
        return classLink;
    }

    public void setClassLink(boolean classLink) {
        this.classLink = classLink;
    }

    public boolean isFieldLink() {
        return fieldLink;
    }

    public void setFieldLink(boolean fieldLink) {
        this.fieldLink = fieldLink;
    }

    public boolean isMethodLink() {
        return methodLink;
    }

    public void setMethodLink(boolean methodLink) {
        this.methodLink = methodLink;
    }

    public boolean isOnlyClassLink() {
        return onlyClassLink;
    }

    public void setOnlyClassLink(boolean onlyClassLink) {
        this.onlyClassLink = onlyClassLink;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public JavaClassMeta getLinkRefJavaClassMeta() {
        return linkRefJavaClassMeta;
    }

    public void setLinkRefJavaClassMeta(JavaClassMeta linkRefJavaClassMeta) {
        this.linkRefJavaClassMeta = linkRefJavaClassMeta;
    }

    public boolean isFullClassPathLink() {
        return fullClassPathLink;
    }

    public void setFullClassPathLink(boolean fullClassPathLink) {
        this.fullClassPathLink = fullClassPathLink;
    }

    public boolean isUseCustomLableName() {
        return useCustomLableName;
    }

    public void setUseCustomLableName(boolean useCustomLableName) {
        this.useCustomLableName = useCustomLableName;
    }
}
