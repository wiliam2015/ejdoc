package com.ejdoc.doc.generate.test;

import cn.hutool.core.util.ZipUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.factory.DocGenerateConfigFactory;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.theme.DocsifyTemplateTheme;

public class DocsifyThemeTest {

    public static void main(String[] args) {
        DocGenerateConfig markdownConfig = DocGenerateConfigFactory.createMarkdownConfig();
        DocTemplateTheme docTemplateTheme = new DocsifyTemplateTheme(markdownConfig);
        DocTemplateThemeInfo docTemplateThemeInfo = new DocTemplateThemeInfo();
        docTemplateThemeInfo.setJsonFilePath("/Users/huhailong1/code/github/hutool/docmeta/doc");
        docTemplateThemeInfo.setTemplateType(TemplateTypeEnum.MarkDown);
        docTemplateThemeInfo.setRenderFilePath("/Users/huhailong1/code/github/hutool/docMd/doc/markdown");
        docTemplateThemeInfo.setDocOutRootPath("/Users/huhailong1/code/github/hutool/docMd/doc");
        docTemplateTheme.writeTemplateThemeFile(docTemplateThemeInfo);

//        ZipUtil.unzip(docTemplateThemeInfo.getRenderFilePath()+"/static.zip",docTemplateThemeInfo.getRenderFilePath());
    }
}
