package com.ejdoc.metainfo.seralize.env.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.setting.Setting;
import com.ejdoc.metainfo.seralize.dto.ModuleInfoDto;
import com.ejdoc.metainfo.seralize.enums.ProjectCompileEnum;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultMetaEnvironment implements MetaEnvironment {
    private static final Logger log = LoggerFactory.getLogger(JavaMetaJsonSeralizeImpl.class);
    /**
     * 配置文件
     */
    private static final String CONFIG_FILE_NAME = "metaDir.properties";

    private static final String DEFAULT_CONFIG_FILE_DIR="com/ejdoc/metainfo/seralize/config/";


    private final Setting PROPS;

    public DefaultMetaEnvironment(){
        this("");
    }

    public DefaultMetaEnvironment(String configFilePath){
        Setting defaultMetaEnvSetting = new Setting(DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME, CharsetUtil.CHARSET_UTF_8, true);

        try {
            defaultMetaEnvSetting.addSetting(new Setting(CONFIG_FILE_NAME,CharsetUtil.CHARSET_UTF_8, true));
        } catch (NoResourceException e) {
            log.warn("not found default classpath config file:[metaDir.properties]");
        }

        if(StrUtil.isNotBlank(configFilePath)){
            defaultMetaEnvSetting.addSetting(new Setting(configFilePath,CharsetUtil.CHARSET_UTF_8, true));
        }

        PROPS = defaultMetaEnvSetting;

    }


    @Override
    public String getProjectRootPath() {

        String projectRootDir = PROPS.getStr("project.root.dir", "");
        Assert.notBlank(projectRootDir, "projectRootDir not Blank properties !");
        return projectRootDir;
    }

    @Override
    public boolean isIncludeSubProject() {
        ProjectCompileEnum projectCompileType = getProjectCompileType();
        if(projectCompileType == ProjectCompileEnum.Gradle){
            String gradleSettingsFilePath = getProjectRootPath() + "/settings.gradle";
            List<String> gradleSetting = FileUtil.readLines(gradleSettingsFilePath, "UTF-8");
            Assert.notEmpty(gradleSetting, "path:"+gradleSettingsFilePath+"not exists !");
            for (String setting : gradleSetting) {
                if(setting.contains("include")){
                    return true;
                }
            }
        }else  if(projectCompileType == ProjectCompileEnum.Maven){
            String gradleSettingsFilePath = getProjectRootPath() + "/pom.xml";
            Document document = XmlUtil.readXML(gradleSettingsFilePath);
            if(document != null){
                NodeList element = document.getElementsByTagName("modules");
                if(element != null && element.getLength() > 0){
                    for (int i = 0; i < element.getLength(); i++) {
                        Element item = (Element)element.item(i);
                        NodeList module = item.getElementsByTagName("module");
                        if(module != null && module.getLength() > 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ProjectCompileEnum getProjectCompileType() {
        String projectCompileType = PROPS.getStr("project.compile.type", "");
        Assert.notBlank(projectCompileType, "projectCompileType not Blank properties !");
        ProjectCompileEnum projectCompileEnum = ProjectCompileEnum.convertToEnumByName(projectCompileType);
        Assert.notNull(projectCompileEnum, "projectCompileEnum is null !");
        return projectCompileEnum;
    }

    @Override
    public List<String> getSubProjectRootPath() {
        List<String> subProjectRootDir = new ArrayList<>();
        if(isIncludeSubProject()){
            String projectRootPath = getProjectRootPath();
            ProjectCompileEnum projectCompileType = getProjectCompileType();
            if(projectCompileType == ProjectCompileEnum.Gradle){
                subProjectRootDir.addAll(readGradleSubProjectRootPath(projectRootPath));
            }else  if(projectCompileType == ProjectCompileEnum.Maven){
                subProjectRootDir.addAll(readMavenSubProjectRootPath(projectRootPath));
            }
        }
        return subProjectRootDir;
    }

    private List<String> readMavenSubProjectRootPath(String projectRootPath) {
        List<String> subProjectRootDir = new ArrayList<>();
        String gradleSettingsFilePath = getProjectRootPath() + "/pom.xml";
        Document document = XmlUtil.readXML(gradleSettingsFilePath);
        if(document != null){
            NodeList element = document.getElementsByTagName("modules");
            if(element != null && element.getLength() > 0){
                for (int i = 0; i < element.getLength(); i++) {
                    Element item = (Element)element.item(i);
                    NodeList module = item.getElementsByTagName("module");
                    if(module != null && module.getLength() > 0){
                        int length = module.getLength();
                        for (int i1 = 0; i1 < length; i1++) {
                            Node item1 = module.item(i1);
                            String textContent = item1.getTextContent();
                            subProjectRootDir.add(projectRootPath +"/"+textContent);
                        }
                    }
                }
            }
        }
        return subProjectRootDir;
    }

    private List<String> readGradleSubProjectRootPath(String projectRootPath) {
        List<String> subProjectRootDir = new ArrayList<>();
        String gradleSettingsFilePath =  projectRootPath + "/settings.gradle";
        List<String> gradleSetting = FileUtil.readLines(gradleSettingsFilePath, "UTF-8");
        Assert.notEmpty(gradleSetting, "path:"+gradleSettingsFilePath+"not exists !");
        for (String setting : gradleSetting) {
            if(setting.contains("include")){
                String[] split = setting.split("\\s");
                String subName = split[1];
                subName = subName.substring(1,subName.lastIndexOf("'"));
                subProjectRootDir.add(projectRootPath +"/"+subName);
            }
        }
        return subProjectRootDir;
    }

    @Override
    public List<ModuleInfoDto> getSubProjectInfo(){
        List<ModuleInfoDto> subProjectRootDir = new ArrayList<>();
        if(isIncludeSubProject()){
            String projectRootPath = getProjectRootPath();
            ProjectCompileEnum projectCompileType = getProjectCompileType();
            if(projectCompileType == ProjectCompileEnum.Gradle){
                subProjectRootDir.addAll(readGradleSubProjectInfo(projectRootPath));
            }else  if(projectCompileType == ProjectCompileEnum.Maven){
                subProjectRootDir.addAll(readMavenSubProjectInfo(projectRootPath));
            }
        }
        return subProjectRootDir;
    }


    private List<ModuleInfoDto> readGradleSubProjectInfo(String projectRootPath) {
        List<ModuleInfoDto> subProjectRootDir = new ArrayList<>();
        String gradleSettingsFilePath =  projectRootPath + "/settings.gradle";
        List<String> gradleSetting = FileUtil.readLines(gradleSettingsFilePath, "UTF-8");
        Assert.notEmpty(gradleSetting, "path:"+gradleSettingsFilePath+"not exists !");
        ModuleInfoDto moduleInfoDto = null;
        for (String setting : gradleSetting) {
            if(setting.contains("include")){
                moduleInfoDto = new ModuleInfoDto();
                String[] split = setting.split("\\s");
                String subName = split[1];
                subName = subName.substring(1,subName.lastIndexOf("'"));
                moduleInfoDto.setModuleName(subName);
                moduleInfoDto.setModulePath(projectRootPath +"/"+subName);
                moduleInfoDto.setProjectPath(projectRootPath);
                subProjectRootDir.add(moduleInfoDto);
            }
        }
        return subProjectRootDir;
    }

    private List<ModuleInfoDto> readMavenSubProjectInfo(String projectRootPath) {
        List<ModuleInfoDto> subProjectRootDir = new ArrayList<>();
        String gradleSettingsFilePath = getProjectRootPath() + "/pom.xml";
        Document document = XmlUtil.readXML(gradleSettingsFilePath);
        if(document != null){
            NodeList element = document.getElementsByTagName("modules");
            if(element != null && element.getLength() > 0){
                ModuleInfoDto moduleInfoDto = null;
                for (int i = 0; i < element.getLength(); i++) {
                    Element item = (Element)element.item(i);
                    NodeList module = item.getElementsByTagName("module");
                    if(module != null && module.getLength() > 0){
                        int length = module.getLength();
                        for (int i1 = 0; i1 < length; i1++) {
                            moduleInfoDto = new ModuleInfoDto();
                            Node item1 = module.item(i1);
                            String textContent = item1.getTextContent();
                            moduleInfoDto.setModuleName(textContent);
                            moduleInfoDto.setModulePath(projectRootPath +"/"+textContent);
                            moduleInfoDto.setProjectPath(projectRootPath);
                            subProjectRootDir.add(moduleInfoDto);
                        }
                    }
                }
            }
        }
        return subProjectRootDir;
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
