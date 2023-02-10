package com.ejdoc.doc.generate.out.config;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;

import java.util.Locale;

public class JavaDocGenerateConfigFactory {
    private JavaDocGenerateConfigFactory(){

    }

    public static JavaDocGenerateConfig createMarkdownConfig(){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(Locale.CHINA);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        return javaDocGenerateConfig;
    }

    public static JavaDocGenerateConfig createMarkdownConfig(String i18nClasspath,String templateClasspath){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(Locale.CHINA);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        javaDocGenerateConfig.setTemplateClasspath(templateClasspath);
        javaDocGenerateConfig.setI18nClasspath(i18nClasspath);
        return javaDocGenerateConfig;
    }

    public static JavaDocGenerateConfig createMarkdownConfig(Locale locale,String i18nClasspath,String templateClasspath){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(locale);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        javaDocGenerateConfig.setTemplateClasspath(templateClasspath);
        javaDocGenerateConfig.setI18nClasspath(i18nClasspath);
        return javaDocGenerateConfig;
    }

    public static JavaDocGenerateConfig createHtmlConfig(){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(Locale.CHINA);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        return javaDocGenerateConfig;
    }

    public static JavaDocGenerateConfig createHtmlConfig(String i18nClasspath,String templateClasspath){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(Locale.CHINA);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        javaDocGenerateConfig.setTemplateClasspath(templateClasspath);
        javaDocGenerateConfig.setI18nClasspath(i18nClasspath);
        return javaDocGenerateConfig;
    }

    public static JavaDocGenerateConfig createHtmlConfig(Locale locale,String i18nClasspath,String templateClasspath){
        JavaDocGenerateConfig javaDocGenerateConfig = new JavaDocGenerateConfig();
        javaDocGenerateConfig.setLocale(locale);
        javaDocGenerateConfig.setTemplateType(TemplateTypeEnum.Html);
        javaDocGenerateConfig.setTemplateClasspath(templateClasspath);
        javaDocGenerateConfig.setI18nClasspath(i18nClasspath);
        return javaDocGenerateConfig;
    }


}
