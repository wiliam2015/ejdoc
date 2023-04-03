package com.ejdoc.metainfo.seralize.enums;

/**
 * 环境属性配置信息枚举
 */
public enum EnvPropEnum {
    project_root_dir("project.root.dir","项目根目录"),
    project_compile_type("project.compile.type","项目编译类型"),
    project_source_dir("project.source.dir","项目源文件目录"),
    project_meta_seralize_out("project.meta.seralize.out","项目元数据输出目录"),
    compile_include_private("compile.include.private","是否包含私有类型"),
    ;

    private String code;
    private String desc;

    EnvPropEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
