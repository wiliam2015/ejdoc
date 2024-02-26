package com.ejdoc.doc.generate.comment.jsr.md.impl;

import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocJsrCommentTypeEnum;

public class JsrCommentNotNullImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocJsrCommentTypeEnum.NOT_NULL.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocJsrCommentTypeEnum.NOT_NULL.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        if(StrUtil.isBlank(content)){
            return "必填:是";
        }
        return "必填:"+content.trim();
    }
}
