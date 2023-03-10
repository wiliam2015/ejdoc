package com.ejdoc.metainfo.seralize.env;


import com.ejdoc.metainfo.seralize.dto.ModuleInfoDto;
import com.ejdoc.metainfo.seralize.enums.ProjectCompileEnum;

import java.util.List;
import java.util.Map;

public interface MetaEnvironment {

    String getProjectRootPath();

    boolean isIncludeSubProject();

    ProjectCompileEnum getProjectCompileType();

    List<String> getSubProjectRootPath();
    List<ModuleInfoDto> getSubProjectInfo();

    String getProp(String propKey);

    Map<String,String> getAllProp();


}
