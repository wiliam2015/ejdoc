package com.ejdoc.doc.generate.comment.md.impl;

import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeValueImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.VALUE.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.VALUE.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        return content;
    }
}
