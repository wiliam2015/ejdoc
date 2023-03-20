package com.ejdoc.doc.generate.template;

import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;

/**
 * 生成doc文档所使用的主题
 */
public interface DocTemplateTheme {
    /**
     * 生成主题文件
     * @param docTemplateThemeInfo
     */
    void writeTemplateThemeFile(DocTemplateThemeInfo docTemplateThemeInfo);
}
