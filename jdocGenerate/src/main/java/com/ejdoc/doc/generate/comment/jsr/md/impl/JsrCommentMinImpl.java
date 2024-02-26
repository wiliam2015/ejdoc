package com.ejdoc.doc.generate.comment.jsr.md.impl;

import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocJsrCommentTypeEnum;

public class JsrCommentMinImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocJsrCommentTypeEnum.Min.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocJsrCommentTypeEnum.Min.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        if(StrUtil.isBlank(content)){
            return "最小值:无";
        }
        return "最小值:"+content.trim();
    }
}
