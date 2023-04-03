package com.ejdoc.metainfo.seralize.model;

public class JavaDocCommentElementMeta {

    /**
     * 类型
     * @see com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum
     */
    private String type;
    private String tagName;
    private String content;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
