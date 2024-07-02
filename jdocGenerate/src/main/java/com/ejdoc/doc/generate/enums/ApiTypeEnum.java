package com.ejdoc.doc.generate.enums;

/**
 * 文档类型
 */
public enum ApiTypeEnum {
    RPC("rpc"),
    HTTP("http");

    private String code;

    ApiTypeEnum(String code){
        this.code = code;
    }


    public static ApiTypeEnum convertToEnumByName(String name){
        ApiTypeEnum[] values = ApiTypeEnum.values();
        for (ApiTypeEnum value : values) {
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
