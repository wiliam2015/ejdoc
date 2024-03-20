package com.ejdoc.doc.generate.test;


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
