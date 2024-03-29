package com.ejdoc.doc.generate.tagtype.dto;

import cn.hutool.json.JSONObject;

/**
 * TagType序列化DTO
 */
public class TagTypeSerializeRootDocDto {

    /** 根对象 */
    private JSONObject rootPropObj;

    /** 标签对象 */
    private JSONObject tagJsonObj;

    private boolean appendName = true;
    public JSONObject getRootPropObj() {
        return rootPropObj;
    }

    public void setRootPropObj(JSONObject rootPropObj) {
        this.rootPropObj = rootPropObj;
    }

    public JSONObject getTagJsonObj() {
        return tagJsonObj;
    }

    public void setTagJsonObj(JSONObject tagJsonObj) {
        this.tagJsonObj = tagJsonObj;
    }

    public boolean isAppendName() {
        return appendName;
    }

    public void setAppendName(boolean appendName) {
        this.appendName = appendName;
    }
}
