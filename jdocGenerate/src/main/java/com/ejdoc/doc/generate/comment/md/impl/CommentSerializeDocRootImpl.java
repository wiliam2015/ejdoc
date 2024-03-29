package com.ejdoc.doc.generate.comment.md.impl;

import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeDocRootImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.DOC_ROOT.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.DOC_ROOT.getName().equals(type);
    }

    @Override
    public String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto) {
        return "https://docs.oracle.com/javase/8/docs/api/";
    }
}
