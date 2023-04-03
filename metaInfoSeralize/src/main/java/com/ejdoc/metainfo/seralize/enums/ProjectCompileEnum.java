package com.ejdoc.metainfo.seralize.enums;

public enum ProjectCompileEnum {

    Maven,Gradle,Source;

    public static ProjectCompileEnum convertToEnumByName(String name){
        ProjectCompileEnum[] values = ProjectCompileEnum.values();
        for (ProjectCompileEnum value : values) {
            if(value.name().equals(name)){
                return value;
            }
        }
        return null;
    }
}
