package com.ejdoc.doc.generate.enums;

public enum TemplateTypeEnum {
    MarkDown("markdown",".md"),
    Html("html",".html");

    private String code;
    private String extension;

    TemplateTypeEnum(String code,String extension){
        this.code = code;
        this.extension = extension;
    }


    public static TemplateTypeEnum convertToEnumByName(String name){
        TemplateTypeEnum[] values = TemplateTypeEnum.values();
        for (TemplateTypeEnum value : values) {
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
