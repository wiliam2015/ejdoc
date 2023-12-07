package com.ejdoc.metainfo.seralize.enums;

import java.util.Optional;

public enum JavaDocTagTypeEnum {
    PARAM("param","PARAM"),
    RETURN("return","RETURN"),
    THROWS("throws","THROWS"),
    EXCEPTION("exception","EXCEPTION"),
    SEE("see","SEE"),
    SINCE("since","SINCE"),
    TYPEPARAM("typeparam","TYPEPARAM"),
    DEFAULT("default","DEFAULT"),
    AUTHOR("author","AUTHOR"),
    UNKNOWN("unknown","UNKNOWN"),
    ;

    private String code;
    private String name;

    JavaDocTagTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static Optional<JavaDocTagTypeEnum> getJavaDocTagTypeEnumByName(String name){
        JavaDocTagTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocTagTypeEnum value : JavaDocTagTypeEnum.values()) {
            if(value.name.equals(name)){
                javaDocCommentTypeEnum = value;
                break;
            }
        }
        return Optional.ofNullable(javaDocCommentTypeEnum);
    }

    public static Optional<JavaDocTagTypeEnum> getJavaDocTagTypeEnumByCode(String code){
        JavaDocTagTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocTagTypeEnum value : JavaDocTagTypeEnum.values()) {
            if(value.code.equals(code)){
                javaDocCommentTypeEnum = value;
                break;
            }
        }
        return Optional.ofNullable(javaDocCommentTypeEnum);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
