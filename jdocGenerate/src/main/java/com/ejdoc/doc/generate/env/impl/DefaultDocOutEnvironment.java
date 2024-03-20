package com.ejdoc.doc.generate.env.impl;

import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.ejdoc.doc.generate.env.DocOutEnvironment;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.env.impl.DefaultMetaEnvironment;
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


    private String javaDocOutConfigFilePath;

    private final Setting PROPS;

    private String docOutRootPath;

    private MetaEnvironment metaEnvironment;

    public DefaultDocOutEnvironment(){
        this("");
    }

    public DefaultDocOutEnvironment(String configFilePath){
        Setting defaultMetaEnvSetting = new Setting(DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME, CharsetUtil.CHARSET_UTF_8, true);
        this.javaDocOutConfigFilePath =DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME;


        try {
            defaultMetaEnvSetting.addSetting(new Setting(CONFIG_FILE_NAME,CharsetUtil.CHARSET_UTF_8, true));
            this.javaDocOutConfigFilePath =CONFIG_FILE_NAME;
        } catch (NoResourceException e) {
            log.warn("not found default classpath config file:[javaDocOutConfig.properties]");
            this.javaDocOutConfigFilePath =DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME;
        }

        if(StrUtil.isNotBlank(configFilePath)){
            defaultMetaEnvSetting.addSetting(new Setting(configFilePath,CharsetUtil.CHARSET_UTF_8, true));
            this.javaDocOutConfigFilePath = configFilePath;
        }

        metaEnvironment = new DefaultMetaEnvironment(javaDocOutConfigFilePath);
        defaultMetaEnvSetting.putAll(metaEnvironment.getAllProp());

        PROPS = defaultMetaEnvSetting;

        checkRequiredAttribute();



    }

    private void checkRequiredAttribute() {
        getDocOutRootPath();
    }

    @Override
    public String getDocOutRootPath() {
        if(StrUtil.isBlank(docOutRootPath)){
            String docOutRootDir = PROPS.getStr("doc.out.root.dir", "");
            if(StrUtil.isBlank(docOutRootDir)){
                docOutRootDir = metaEnvironment.getProjectRootPath()+"/docmd";
                log.info("auto detection doc out dir:{}",docOutRootDir);
            }
            Assert.notBlank(docOutRootDir, "docOutRoot not Blank properties !");
            log.info("load doc out dir:{}",docOutRootDir);
            docOutRootPath=  docOutRootDir;
        }
       return docOutRootPath;
    }

    @Override
    public String getProjectRootPath() {
        return metaEnvironment.getProjectRootPath();
    }

    @Override
    public String getVersion() {
        return this.PROPS.get("version");
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
    @Override
    public String getJavaDocOutConfigFilePath() {
        return javaDocOutConfigFilePath;
    }

    @Override
    public MetaEnvironment getMetaEnvironment() {
        return metaEnvironment;
    }
}
