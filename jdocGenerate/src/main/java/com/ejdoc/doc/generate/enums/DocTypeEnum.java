package com.ejdoc.doc.generate.enums;

/**
 * 文档类型
 */
public enum DocTypeEnum {
    API("api"),
    JAVA_DOC("javadoc");

    private String code;

    DocTypeEnum(String code){
        this.code = code;
    }


    public static DocTypeEnum convertToEnumByName(String name){
        DocTypeEnum[] values = DocTypeEnum.values();
        for (DocTypeEnum value : values) {
            if(value.name().equals(name)){
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
