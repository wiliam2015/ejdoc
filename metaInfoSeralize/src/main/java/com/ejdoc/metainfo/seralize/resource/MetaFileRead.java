package com.ejdoc.metainfo.seralize.resource;

import com.ejdoc.metainfo.seralize.dto.MetaFileInfoDto;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 元数据读取接口<br>
 * <p>读取jar文件或单个文件内容。</p>
 *
 * @author wiliam.me
 * @since 0.1.0
 */
public interface MetaFileRead {

    File readProjectMetaFile();

    List<File> readModuleProjectMetaFile();

    List<File> readModuleProjectMetaFileByModuleName(String moduleName);


    Map<String,List<MetaFileInfoDto>> readModuleMetaFile();

    List<MetaFileInfoDto> readModuleMetaFileByName(String moduleName);

    List<MetaFileInfoDto> readAllMetaFile();

    List<MetaFileInfoDto> readProjectFileByPath(String path);


    List<MetaFileInfoDto> readProjectFileByClasspath(String classpath);

    MetaEnvironment getMetaEnvironment();


    String getProp(String propKey);

    String getProp(String propKey,String defaultVal);

}
