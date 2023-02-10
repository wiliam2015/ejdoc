package com.ejdoc.doc.generate.env.impl;

import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.ejdoc.doc.generate.env.DocOutEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultDocOutEnvironment implements DocOutEnvironment {

    private static final Logger log = LoggerFactory.getLogger(DefaultDocOutEnvironment.class);

    /**
     * 配置文件
     */
    private static final String CONFIG_FILE_NAME = "javaDocOutConfig.properties";

    private static final String DEFAULT_CONFIG_FILE_DIR="com/ejdoc/doc/generate/config/";



    private final Setting PROPS;

    public DefaultDocOutEnvironment(){
        this("");
    }

    public DefaultDocOutEnvironment(String configFilePath){
        Setting defaultMetaEnvSetting = new Setting(DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME, CharsetUtil.CHARSET_UTF_8, true);

        try {
            defaultMetaEnvSetting.addSetting(new Setting(CONFIG_FILE_NAME,CharsetUtil.CHARSET_UTF_8, true));
        } catch (NoResourceException e) {
            log.warn("not found default classpath config file:[javaDocOutConfig.properties]");
        }

        if(StrUtil.isNotBlank(configFilePath)){
            defaultMetaEnvSetting.addSetting(new Setting(configFilePath,CharsetUtil.CHARSET_UTF_8, true));
        }

        PROPS = defaultMetaEnvSetting;

    }

    @Override
    public String getDocOutRootPath() {
        String projectRootDir = PROPS.getStr("doc.out.root.dir", "");
        Assert.notBlank(projectRootDir, "docOutRoot not Blank properties !");
        return projectRootDir;
    }

    @Override
    public String getProp(String propKey) {
        Assert.notBlank(propKey, "propKey not Blank properties !");
        return this.PROPS.get(propKey);
    }

    @Override
    public Map<String, String> getAllProp() {
        return this.PROPS;
    }
}
