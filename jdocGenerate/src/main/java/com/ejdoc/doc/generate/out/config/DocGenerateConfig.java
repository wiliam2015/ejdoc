package com.ejdoc.doc.generate.out.config;

import com.ejdoc.doc.generate.enums.ApiTypeEnum;
import com.ejdoc.doc.generate.enums.DocTypeEnum;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;

import java.util.Locale;

public class DocGenerateConfig {

    /**
     * 国际化local
     */
    private Locale locale;

    /**
     * 模板文件配置路径
     */
    private String templateClasspath;

    /**
     * 国际化资源文件配置路径
     */
    private String i18nClasspath;

    /**
     * 模板类型
     */
    private TemplateTypeEnum templateType;

    /**
     * 模板主题 {@link com.ejdoc.doc.generate.enums.TemplateThemeEnum}
     */
    private String templateTheme;

    /**
     * 文档类型
     */
    private DocTypeEnum docTypeEnum;
    /**
     * API类型，当docTypeEnum为api时必须
     */
    private ApiTypeEnum apiTypeEnum;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getTemplateClasspath() {
        return templateClasspath;
    }

    public void setTemplateClasspath(String templateClasspath) {
        this.templateClasspath = templateClasspath;
    }

    public String getI18nClasspath() {
        return i18nClasspath;
    }

    public void setI18nClasspath(String i18nClasspath) {
        this.i18nClasspath = i18nClasspath;
    }

    public TemplateTypeEnum getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateTypeEnum templateType) {
        this.templateType = templateType;
    }

    public String getTemplateTheme() {
        return templateTheme;
    }

    public void setTemplateTheme(String templateTheme) {
        this.templateTheme = templateTheme;
    }

    public DocTypeEnum getDocTypeEnum() {
        return docTypeEnum;
    }

    public void setDocTypeEnum(DocTypeEnum docTypeEnum) {
        this.docTypeEnum = docTypeEnum;
    }

    public ApiTypeEnum getApiTypeEnum() {
        return apiTypeEnum;
    }

    public void setApiTypeEnum(ApiTypeEnum apiTypeEnum) {
        this.apiTypeEnum = apiTypeEnum;
    }
}
