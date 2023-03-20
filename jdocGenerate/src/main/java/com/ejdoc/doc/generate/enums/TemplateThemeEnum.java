package com.ejdoc.doc.generate.enums;

public enum TemplateThemeEnum {
    Docsify("docsify","markdown"),
    ;

    private String code;
    private String templateType;

    TemplateThemeEnum(String code, String templateType){
        this.code = code;
        this.templateType = templateType;
    }


    public static TemplateThemeEnum convertToEnumByName(String name){
        TemplateThemeEnum[] values = TemplateThemeEnum.values();
        for (TemplateThemeEnum value : values) {
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

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
