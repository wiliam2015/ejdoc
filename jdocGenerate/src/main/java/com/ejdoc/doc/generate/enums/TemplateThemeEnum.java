package com.ejdoc.doc.generate.enums;

public enum TemplateThemeEnum {
    Docsify("docsify","markdown",""),
    Vitepress("vitepress","markdown","src"),
    ;

    private String code;
    private String templateType;
    private String srcDir;

    TemplateThemeEnum(String code, String templateType,String srcDir){
        this.code = code;
        this.templateType = templateType;
        this.srcDir = srcDir;
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

    public String getSrcDir() {
        return srcDir;
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }
}
