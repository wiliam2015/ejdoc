package com.ejdoc.doc.generate.comment.md.impl;

import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeCodeImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.CODE.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.CODE.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        return "<code>"+content.trim()+"</code>";
    }
}
