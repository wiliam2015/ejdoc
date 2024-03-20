package com.ejdoc.doc.generate.template;

import com.ejdoc.doc.generate.model.DocOutFileInfo;

public interface DocOutTemplate {

    /**
     * 格式化模板内容
     * @param docOutFileInfo doc文件信息
     * @return 格式化后的模板内容
     */
    String formatTemplate(DocOutFileInfo docOutFileInfo);

    /**
     * 将模板内容输出生成文件
     * @param formatData
     */
    void writeFormat(String formatData,DocOutFileInfo docOutFileInfo);

    /**
     * 复制资源文件到相关目录
     * @param docOutFileInfo
     */
    void copyFile(DocOutFileInfo docOutFileInfo);
}
