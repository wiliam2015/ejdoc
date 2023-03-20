package com.ejdoc.doc.generate.out.factory;

import cn.hutool.core.bean.BeanUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.javadoc.JavaDocGenerate;
import com.ejdoc.doc.generate.out.javadoc.JavaDocGenerateConfig;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.html.HtmlDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.JavaDocMarkdownDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.MarkdownDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.theme.DocsifyTemplateTheme;

public class DocGenerateFactory {

    private DocGenerateFactory(){}

    public static JavaDocGenerate createDefaultJavaDocGenerate(){
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createMarkdownConfig();
        DocOutTemplate defaultDocOutTemplate = createDefaultDocOutTemplate(markdownConfig);
        JavaDocGenerateConfig javaDocGenerateConfig = BeanUtil.copyProperties(markdownConfig,JavaDocGenerateConfig.class);
        DocTemplateTheme docTemplateTheme = new DocsifyTemplateTheme(markdownConfig);
        JavaDocGenerate javaDocGenerate = new JavaDocGenerate(javaDocGenerateConfig,defaultDocOutTemplate,docTemplateTheme);
        return javaDocGenerate;
    }


    private static DocOutTemplate createDefaultDocOutTemplate(DocGenerateConfig docGenerateConfig) {
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
}
