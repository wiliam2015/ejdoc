package com.ejdoc.doc.generate.out;

import java.util.Set;

public interface DocGenerate {

    /**
     * 打印文档
     * @return 返回不能解析的符号
     */
    Set<String> printDoc();
}
