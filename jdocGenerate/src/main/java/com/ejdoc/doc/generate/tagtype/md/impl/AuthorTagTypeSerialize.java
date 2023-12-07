package com.ejdoc.doc.generate.tagtype.md.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.tagtype.TagTypeSerialize;
import com.ejdoc.doc.generate.tagtype.dto.TagTypeSerializeRootDocDto;
import com.ejdoc.doc.generate.util.DocParseUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocTagTypeEnum;

public class AuthorTagTypeSerialize implements TagTypeSerialize {

    @Override
    public String acceptType() {
        return JavaDocTagTypeEnum.AUTHOR.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocTagTypeEnum.AUTHOR.getName().equals(type);
    }

    @Override
    public String toSerialize(String type, TagTypeSerializeRootDocDto serializeRootDocDto) {
        JSONObject rootPropObj = serializeRootDocDto.getRootPropObj();
        JSONObject tagJsonObj = serializeRootDocDto.getTagJsonObj();
        StringBuilder tagSb = new StringBuilder();

        String value = "";
        boolean values = tagJsonObj.containsKey("values");
        if(values){
            value = DocParseUtil.parseCommentMd(tagJsonObj.getJSONArray("values"),rootPropObj);
        }else{
            value = tagJsonObj.getStr("value", "");
        }
        tagSb.append("  ");
        tagSb.append(value);
        return tagSb.toString();
    }
}
