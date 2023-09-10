package com.ejdoc.doc.generate.tagtype.md.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.tagtype.TagTypeSerialize;
import com.ejdoc.doc.generate.tagtype.dto.TagTypeSerializeRootDocDto;
import com.ejdoc.doc.generate.util.DocParseUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocTagTypeEnum;

public class DefaultTagTypeSerialize implements TagTypeSerialize {

    @Override
    public String acceptType() {
        return JavaDocTagTypeEnum.DEFAULT.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocTagTypeEnum.DEFAULT.getName().equals(type);
    }

    @Override
    public String toSerialize(String type, TagTypeSerializeRootDocDto serializeRootDocDto) {
        JSONObject rootPropObj = serializeRootDocDto.getRootPropObj();
        JSONObject tagJsonObj = serializeRootDocDto.getTagJsonObj();
        StringBuilder tagSb = new StringBuilder();

//        if(type.equals("SEE")){
//            System.out.println("sss");
//        }
        String name = tagJsonObj.getStr("name", "");
        String value = "";
        boolean values = tagJsonObj.containsKey("values");
        if(values){
            value = DocParseUtil.parseCommentMd(tagJsonObj.getJSONArray("values"),rootPropObj);
        }else{
            value = tagJsonObj.getStr("value", "");
            value = value.trim().replaceAll(" {2,}","");
        }
        tagSb.append("  ");
        if(StrUtil.isNotBlank(name)){
            tagSb.append(name);
            tagSb.append(" - ");
        }
        tagSb.append(value);
        return tagSb.toString();
    }
}
