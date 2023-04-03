package com.ejdoc.metainfo.seralize.enums;

import java.util.Optional;

public enum JavaDocCommentTypeEnum {
    CODE("code","CODE"),
    DOC_ROOT("docRoot","DOC_ROOT"),
    INHERIT_DOC("inheritDoc","INHERIT_DOC"),
    LINK("link","LINK"),
    LINKPLAIN("linkplain","LINKPLAIN"),
    LITERAL("literal","LITERAL"),
    VALUE("value","VALUE"),
    SYSTEM_PROPERTY("systemProperty","SYSTEM_PROPERTY"),
    IMPL_SPEC("implSpec","IMPL_SPEC"),
    TEXT("text","TEXT"),
    UNKNOWN("unknown","UNKNOWN"),
    ;

    private String code;
    private String name;

    JavaDocCommentTypeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

    public static Optional<JavaDocCommentTypeEnum> getJavaDocCommentTypeEnumByName(String name){
        JavaDocCommentTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocCommentTypeEnum value : JavaDocCommentTypeEnum.values()) {
            if(value.name.equals(name)){
                javaDocCommentTypeEnum = value;
                break;
            }
        }
        return Optional.ofNullable(javaDocCommentTypeEnum);
    }

    public static Optional<JavaDocCommentTypeEnum> getJavaDocCommentTypeEnumByCode(String code){
        JavaDocCommentTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocCommentTypeEnum value : JavaDocCommentTypeEnum.values()) {
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
