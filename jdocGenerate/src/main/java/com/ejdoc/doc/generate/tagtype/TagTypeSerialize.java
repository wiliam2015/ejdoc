package com.ejdoc.doc.generate.tagtype;

import com.ejdoc.doc.generate.tagtype.dto.TagTypeSerializeRootDocDto;

public interface TagTypeSerialize {

    String acceptType();

    boolean accept(String type);

    String toSerialize(String type,TagTypeSerializeRootDocDto serializeRootDocDto);
}
