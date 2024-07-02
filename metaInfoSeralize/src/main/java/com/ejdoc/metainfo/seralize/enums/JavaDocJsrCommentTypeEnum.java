package com.ejdoc.metainfo.seralize.enums;

import java.util.Optional;

/**
 * javadoc jsr注释枚举类型
 */
public enum JavaDocJsrCommentTypeEnum {
    NOT_NULL("NotNull","NOTNULL"),
    Min("Min","MIN"),
    Max("Max","MAX"),
    Size("Size","SIZE"),
    Past("Past",    "PAST"),
    Future("Future","FUTURE"),
    Pattern("Pattern","PATTERN"),
    Jsr303Tag("Jsr303Tag","JSR303TAG"),
    ;

    private String code;
    private String name;

    JavaDocJsrCommentTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static Optional<JavaDocJsrCommentTypeEnum> getJavaDocCommentTypeEnumByName(String name){
        JavaDocJsrCommentTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocJsrCommentTypeEnum value : JavaDocJsrCommentTypeEnum.values()) {
            if(value.name.equals(name)){
                javaDocCommentTypeEnum = value;
                break;
            }
        }
        return Optional.ofNullable(javaDocCommentTypeEnum);
    }

    public static Optional<JavaDocJsrCommentTypeEnum> getJavaDocCommentTypeEnumByCode(String code){
        JavaDocJsrCommentTypeEnum javaDocCommentTypeEnum = null;
        for (JavaDocJsrCommentTypeEnum value : JavaDocJsrCommentTypeEnum.values()) {
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
