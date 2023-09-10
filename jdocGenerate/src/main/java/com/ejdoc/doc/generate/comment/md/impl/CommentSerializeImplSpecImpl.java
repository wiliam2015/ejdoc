package com.ejdoc.doc.generate.comment.md.impl;

import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeImplSpecImpl implements CommentSerialize {
    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.IMPL_SPEC.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.IMPL_SPEC.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        return content;
    }
}
