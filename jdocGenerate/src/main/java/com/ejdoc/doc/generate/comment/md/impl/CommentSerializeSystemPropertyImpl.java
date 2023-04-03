package com.ejdoc.doc.generate.comment.md.impl;

import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeSystemPropertyImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.SYSTEM_PROPERTY.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.SYSTEM_PROPERTY.getName().equals(type);
    }

    @Override
    public String toSerialize(String content,String projectName,String moduleName,String curPackage) {
        return content;
    }
}
