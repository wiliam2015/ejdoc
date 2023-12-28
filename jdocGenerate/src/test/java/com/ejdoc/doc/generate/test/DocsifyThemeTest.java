package com.ejdoc.doc.generate.test;

import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.factory.DocGenerateConfigFactory;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.theme.JavaDocDocsifyTemplateTheme;
import com.ejdoc.metainfo.seralize.index.MetaIndexContextBuilder;

public class DocsifyThemeTest {

    public static void main(String[] args) {
        MetaIndexContextBuilder.instance().createMetaIndexContext("/Users/huhailong1/code/github/hutool/docmeta/doc");
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createMarkdownConfig();
        DocTemplateTheme docTemplateTheme = new JavaDocDocsifyTemplateTheme(markdownConfig);
        DocTemplateThemeInfo docTemplateThemeInfo = new DocTemplateThemeInfo();
//        docTemplateThemeInfo.setJsonFilePath("/Users/huhailong1/code/github/java/JDKSourceCode1.8/docmeta/doc");
        docTemplateThemeInfo.setJsonFilePath("/Users/huhailong1/code/github/hutool/docmeta/doc");
        docTemplateThemeInfo.setTemplateType(TemplateTypeEnum.MarkDown);
//        docTemplateThemeInfo.setRenderFilePath("/Users/huhailong1/code/github/java/JDKSourceCode1.8/docMd/doc/markdown");
        docTemplateThemeInfo.setRenderFilePath("/Users/huhailong1/code/github/hutool/docMd/doc/markdown");
//        docTemplateThemeInfo.setDocOutRootPath("/Users/huhailong1/code/github/java/JDKSourceCode1.8/doc");
        docTemplateTheme.writeTemplateThemeFile(docTemplateThemeInfo);

    }
}
