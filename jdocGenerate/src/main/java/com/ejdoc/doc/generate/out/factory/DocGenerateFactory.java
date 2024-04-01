package com.ejdoc.doc.generate.out.factory;

import cn.hutool.core.bean.BeanUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.env.DocOutEnvironment;
import com.ejdoc.doc.generate.env.impl.DefaultDocOutEnvironment;
import com.ejdoc.doc.generate.out.apidoc.ApiDocGenerate;
import com.ejdoc.doc.generate.out.apidoc.ApiDocGenerateConfig;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.javadoc.JavaDocGenerate;
import com.ejdoc.doc.generate.out.javadoc.JavaDocGenerateConfig;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.html.HtmlDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.JavaDocMarkdownDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.ApiDocMarkdownDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.theme.ApiDocDocsifyTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.theme.JavaDocDocsifyTemplateTheme;

import java.util.Map;

public class DocGenerateFactory {

    private DocGenerateFactory(){}

    public static JavaDocGenerate createDefaultJavaDocGenerate(){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createJavadocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultJavaDocOutTemplate(markdownConfig);
        JavaDocGenerateConfig javaDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,JavaDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new JavaDocDocsifyTemplateTheme(markdownConfig);
        JavaDocGenerate javaDocGenerate = new JavaDocGenerate(javaDocGenerateConfig,defaultDocOutTemplate,docTemplateTheme);
        return javaDocGenerate;
    }

    public static JavaDocGenerate createDefaultJavaDocGenerate(String configFilePath){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createJavadocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultJavaDocOutTemplate(markdownConfig);
        JavaDocGenerateConfig javaDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,JavaDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new JavaDocDocsifyTemplateTheme(markdownConfig);
        JavaDocGenerate javaDocGenerate = new JavaDocGenerate(javaDocGenerateConfig,configFilePath,defaultDocOutTemplate,docTemplateTheme);
        return javaDocGenerate;
    }


    /**
     * 创建默认的api文档实例
     * @return api文档实例
     */
    public static ApiDocGenerate createDefaultApiDocGenerate(){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createApidocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultApiDocOutTemplate(markdownConfig);
        ApiDocGenerateConfig apiDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,ApiDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new ApiDocDocsifyTemplateTheme(markdownConfig);
        ApiDocGenerate apiDocGenerate = new ApiDocGenerate(apiDocGenerateConfig,defaultDocOutTemplate,docTemplateTheme);
        return apiDocGenerate;
    }

    /**
     * 创建默认的api文档实例
     * @param configFilePath 配置文件
     * @return api文档实例
     */
    public static ApiDocGenerate createDefaultApiDocGenerate(String configFilePath){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createApidocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultApiDocOutTemplate(markdownConfig);
        ApiDocGenerateConfig apiDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,ApiDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new ApiDocDocsifyTemplateTheme(markdownConfig);
        ApiDocGenerate apiDocGenerate = new ApiDocGenerate(apiDocGenerateConfig,configFilePath,defaultDocOutTemplate,docTemplateTheme);
        return apiDocGenerate;
    }

    /**
     * 创建默认的api文档实例
     * @param configFilePath 配置文件
     * @param customProps 自定义属性
     * @return api文档实例
     */
    public static ApiDocGenerate createDefaultApiDocGenerate(String configFilePath, Map<String,String> customProps){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createApidocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultApiDocOutTemplate(markdownConfig);
        ApiDocGenerateConfig apiDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,ApiDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new ApiDocDocsifyTemplateTheme(markdownConfig);
        DocOutEnvironment docOutEnvironment =new DefaultDocOutEnvironment(configFilePath,customProps);
        ApiDocGenerate apiDocGenerate = new ApiDocGenerate(apiDocGenerateConfig,docOutEnvironment,defaultDocOutTemplate,docTemplateTheme);
        return apiDocGenerate;
    }

    /**
     *  创建默认的api文档实例
     * @param customProps 自定义属性
     * @return api文档实例
     */
    public static ApiDocGenerate createDefaultApiDocGenerate(Map<String,String> customProps){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createApidocMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultApiDocOutTemplate(markdownConfig);
        ApiDocGenerateConfig apiDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,ApiDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new ApiDocDocsifyTemplateTheme(markdownConfig);
        DocOutEnvironment docOutEnvironment =new DefaultDocOutEnvironment(customProps);
        ApiDocGenerate apiDocGenerate = new ApiDocGenerate(apiDocGenerateConfig,docOutEnvironment,defaultDocOutTemplate,docTemplateTheme);
        return apiDocGenerate;
    }
    private static DocOutTemplate createDefaultJavaDocOutTemplate(DocGenerateConfig docGenerateConfig) {
        DocOutTemplate docOutTemplate = null;
        TemplateTypeEnum templateType = docGenerateConfig.getTemplateType();
        switch (templateType){
            case MarkDown:
                docOutTemplate = new JavaDocMarkdownDocOutTemplate(docGenerateConfig);
                break;
            case Html:
                docOutTemplate = new HtmlDocOutTemplate(docGenerateConfig);
                break;
        }
        return docOutTemplate;
    }


    private static DocOutTemplate createDefaultApiDocOutTemplate(DocGenerateConfig docGenerateConfig) {
        DocOutTemplate docOutTemplate = null;
        TemplateTypeEnum templateType = docGenerateConfig.getTemplateType();
        switch (templateType){
            case MarkDown:
                docOutTemplate = new ApiDocMarkdownDocOutTemplate(docGenerateConfig);
                break;
            case Html:
                docOutTemplate = new HtmlDocOutTemplate(docGenerateConfig);
                break;
        }
        return docOutTemplate;
    }
}
