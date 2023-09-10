package com.ejdoc.doc.generate.comment.md.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeTextImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.TEXT.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.TEXT.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
//        return content.trim().replace("\n","").replaceAll(" {2,}","");
        return content.trim().replaceAll(" {2,}","");
    }
}
