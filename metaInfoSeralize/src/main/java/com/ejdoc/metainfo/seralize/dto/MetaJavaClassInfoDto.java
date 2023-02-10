package com.ejdoc.metainfo.seralize.dto;

import com.thoughtworks.qdox.model.JavaClass;

public class MetaJavaClassInfoDto {


    private MetaFileInfoDto metaFileInfoDto;

    private JavaClass javaClass;


    public MetaFileInfoDto getMetaFileInfoDto() {
        return metaFileInfoDto;
    }

    public void setMetaFileInfoDto(MetaFileInfoDto metaFileInfoDto) {
        this.metaFileInfoDto = metaFileInfoDto;
    }

    public JavaClass getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(JavaClass javaClass) {
        this.javaClass = javaClass;
    }
}
