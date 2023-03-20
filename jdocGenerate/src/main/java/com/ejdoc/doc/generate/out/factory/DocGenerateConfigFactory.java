package com.ejdoc.doc.generate.out.factory;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;

import java.util.Locale;

public class DocGenerateConfigFactory {
    private DocGenerateConfigFactory(){

    }

    public static DocGenerateConfig createMarkdownConfig(){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createMarkdownConfig(String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createMarkdownConfig(Locale locale, String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(locale);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createHtmlConfig(){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createHtmlConfig(String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(Locale.CHINA);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }

    public static DocGenerateConfig createHtmlConfig(Locale locale, String i18nClasspath, String templateClasspath){
        DocGenerateConfig docGenerateConfig = new DocGenerateConfig();
        docGenerateConfig.setLocale(locale);
        docGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        docGenerateConfig.setTemplateClasspath(templateClasspath);
        docGenerateConfig.setI18nClasspath(i18nClasspath);
        return docGenerateConfig;
    }


}
