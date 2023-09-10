package com.ejdoc.doc.generate.comment;

import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;

public interface CommentSerialize {

    String acceptType();

    boolean accept(String type);

    String toSerialize(String content, CommentSerializeRootDocDto serializeRootDocDto);
}
