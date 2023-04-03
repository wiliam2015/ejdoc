package com.ejdoc.doc.generate.env;

import java.util.Map;

public interface DocOutEnvironment {

    /**
     * 获取生成doc文件的目录
     * @return
     */
    String getDocOutRootPath();

    /**
     * 获取项目根目录
     * @return
     */
    String getProjectRootPath();

    String getProp(String propKey);

    Map<String,String> getAllProp();

    /**
     * 获取配置文件路径
     * @return 配置文件路径
     */
    String getJavaDocOutConfigFilePath();
}
