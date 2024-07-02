package com.ejdoc.doc.generate.out.factory;

import com.ejdoc.doc.generate.enums.ApiTypeEnum;
import com.ejdoc.doc.generate.enums.DocTypeEnum;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;

import java.util.Locale;

public class DocGenerateConfigFactory {
    private DocGenerateConfigFactory(){

    }

    public static DocGenerateConfig createApidocMarkdownConfig(){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.API);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setApiTypeEnum(ApiTypeEnum.RPC);
        return docGenerateConfig;
    }
    public static DocGenerateConfig createApidocMarkdownConfig(ApiTypeEnum apiTypeEnum){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.API);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setApiTypeEnum(apiTypeEnum);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocMarkdownConfig(){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocMarkdownConfig(String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocMarkdownConfig(Locale locale, String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(locale);
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocHtmlConfig(){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocHtmlConfig(String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createJavadocHtmlConfig(Locale locale, String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setDocTypeEnum(DocTypeEnum.JAVA_DOC);
        docGenerateConfig.setLocale(locale);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }


}
