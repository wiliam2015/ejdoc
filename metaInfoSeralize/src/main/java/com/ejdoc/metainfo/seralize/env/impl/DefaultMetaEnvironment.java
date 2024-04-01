package com.ejdoc.metainfo.seralize.env.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
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

import java.io.File;
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

    private ProjectCompileEnum projectCompile;

    private String projectRootPath;

    private String projectSourcePath;
    private String projectMetaSeralizeOutPath;


    public DefaultMetaEnvironment(){
        this("");
    }


    public DefaultMetaEnvironment(String configFilePath){
        this(configFilePath,null);
    }

    public DefaultMetaEnvironment(String configFilePath,Map<String,String> customProp){
        Setting defaultMetaEnvSetting = new Setting(DEFAULT_CONFIG_FILE_DIR+CONFIG_FILE_NAME, CharsetUtil.CHARSET_UTF_8, true);

        try {
            defaultMetaEnvSetting.addSetting(new Setting(CONFIG_FILE_NAME,CharsetUtil.CHARSET_UTF_8, true));
        } catch (NoResourceException e) {
            log.debug("not found default classpath config file:[metaDir.properties]");
        }

        if(StrUtil.isNotBlank(configFilePath)){
            defaultMetaEnvSetting.addSetting(new Setting(configFilePath,CharsetUtil.CHARSET_UTF_8, true));
        }

        PROPS = defaultMetaEnvSetting;
        if(CollectionUtil.isNotEmpty(customProp)){
            PROPS.putAll(customProp);
        }

        checkRequiredAttribute();

        autoLoadProjectTypeEnv();

        setPropDefaultVal();
    }


    /**
     * 设置属性默认值
     */
    private void setPropDefaultVal() {
        String version = PROPS.getStr("version", "");
        if(StrUtil.isBlank(version)){
            PROPS.put("version","1.0.0");
        }

    }


    /**
     * 自动获取对应项目类型的环境熟悉信息，maven项目从跟pom上获取
     */
    private void autoLoadProjectTypeEnv() {
        ProjectCompileEnum projectCompileType = getProjectCompileType();
        if(ProjectCompileEnum.Maven.equals(projectCompileType)){
            String gradleSettingsFilePath = getProjectRootPath() + "/pom.xml";
            Document document = XmlUtil.readXML(gradleSettingsFilePath);
            if(document != null){
                String version = readXmlEleText(document,"version");
                String name = readXmlEleText(document,"name");
                String description =readXmlEleText(document,"description");
                String url = readXmlEleText(document,"url");
                String configVersion = PROPS.getStr("version", "");
                if(StrUtil.isNotBlank(version) && StrUtil.isBlank(configVersion) ){
                    PROPS.put("version",version);
                }
                if(StrUtil.isNotBlank(name)){
                    PROPS.put("name",name);
                }
                if(StrUtil.isNotBlank(description)){
                    PROPS.put("description",description);
                }
                if(StrUtil.isNotBlank(url)){
                    PROPS.put("url",url);
                }

            }
        }
    }

    private String readXmlEleText( Document document,String tagName){
        try {
            NodeList elementsByTagName = document.getElementsByTagName(tagName);
            if(elementsByTagName != null){
                Node item = elementsByTagName.item(0);
                return item.getTextContent();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 校验必须属性
     */
    private void checkRequiredAttribute() {
        getProjectRootPath();
        getProjectCompileType();
        getProjectSourceDir();
        getProjectMetaSeralizeOutPath();
    }

    private String getProjectMetaSeralizeOutPath() {
        if(StrUtil.isBlank(projectMetaSeralizeOutPath)){
            String projectMetaSeralizeOutDir = PROPS.getStr("project.meta.seralize.out", "");
            if(StrUtil.isBlank(projectMetaSeralizeOutDir)){
                String projectRootPath = getProjectRootPath();
                projectMetaSeralizeOutDir=projectRootPath + "/docmeta";
                if(StrUtil.isNotBlank(projectMetaSeralizeOutDir)){
                    PROPS.set("project.meta.seralize.out", projectMetaSeralizeOutDir);
                    log.info("auto detection project meta seralize out dir:{}",projectMetaSeralizeOutDir);
                }
            }
            Assert.notBlank(projectMetaSeralizeOutDir, "[project.meta.seralize.out] properties is require,please config in metaDir.properties !");
            log.info("project meta seralize out dir:{}",projectMetaSeralizeOutDir);
            projectMetaSeralizeOutPath= projectMetaSeralizeOutDir;
        }
        return projectMetaSeralizeOutPath;
    }


    @Override
    public String getProjectRootPath() {
        if(StrUtil.isBlank(projectRootPath)){
            String projectRootDir = PROPS.getStr("project.root.dir", "");
            if(StrUtil.isBlank(projectRootDir)){
                projectRootDir = getPath("pom.xml");
                if(StrUtil.isBlank(projectRootDir)){
                    projectRootDir = getPath("settings.gradle");
                }
                if(StrUtil.isNotBlank(projectRootDir)){
                    PROPS.set("project.root.dir", projectRootDir);
                    log.info("auto detection project root dir:{}",projectRootDir);
                }
            }
            Assert.notBlank(projectRootDir, "[project.root.dir] properties is require,please config in metaDir.properties !");
            log.info("project root dir:{}",projectRootDir);
            projectRootPath= projectRootDir;
        }
        return projectRootPath;
    }

    public String  getPath(String fileName) {
//        String fileName ="pom.xml";
        ClassPathResource classPathResource = new ClassPathResource("/");
        String classpath = classPathResource.getAbsolutePath();
        if(StrUtil.isNotBlank(classpath)){
            File dir = new File(classpath);
            String projectDir ="";
            int count = 0;
            while (dir.getParentFile() != null){
                dir= dir.getParentFile();
                if(dir != null){
                    File currentPathFile = findCurrentPathFile(dir, fileName);
                    if(currentPathFile != null){
                        projectDir = currentPathFile.getAbsolutePath();

                    }
                }
                if(count++ > 3){
                    break;
                }
            }
            return projectDir;

        }
        return "";
    }

    /**
     * 查找当前文件路径下的文件
     * @param dir
     * @param fileName
     * @return
     */
    private  File findCurrentPathFile(File dir,String fileName) {
        try {
            File[] matches = dir.listFiles((d, f) -> fileName.equals(f));
            return matches != null && matches.length > 0 ? matches[0].getParentFile() : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getProjectSourceDir() {
        if(StrUtil.isBlank(projectSourcePath)){
            String projectSourceDir = PROPS.getStr("project.source.dir", "");
            if(StrUtil.isBlank(projectSourceDir)){
                ProjectCompileEnum projectCompileType = getProjectCompileType();
                if(projectCompileType ==ProjectCompileEnum.Maven || projectCompileType ==ProjectCompileEnum.Gradle){
                    projectSourceDir = "/src/main/java";
                    PROPS.set("project.source.dir", projectSourceDir);
                    log.info("auto detection project source dir:{}",projectSourceDir);
                }
            }
            Assert.notBlank(projectSourceDir, "[project.source.dir] properties is require,please config in metaDir.properties !");
            log.info("project source dir:{}",projectSourceDir);
            projectSourcePath=  projectSourceDir;
        }
        return projectSourcePath;
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
        if(projectCompile == null){
            String projectCompileType = autoDecisionCompileType();
            Assert.notBlank(projectCompileType, "the compile type not detect,please config in metaDir.properties.the porp name is [project.compile.type] !");
            ProjectCompileEnum projectCompileEnum = ProjectCompileEnum.convertToEnumByName(projectCompileType);
            Assert.notNull(projectCompileEnum, "not support the compile type ["+projectCompileType+"]!");
            log.info("project compile type:{}",projectCompileType);
            projectCompile =  projectCompileEnum;
        }
        return projectCompile;
    }

    /**
     * 编译类型为空时尝试自动探测项目编译方式
     * 目前支持的是Maven和Gradle
     * @return
     */
    private String autoDecisionCompileType() {
        String projectCompileType = PROPS.getStr("project.compile.type", "");
        if(StrUtil.isBlank(projectCompileType)){
            String projectRootPath = getProjectRootPath();
            String mavenPomFile = projectRootPath+"/pom.xml";
            String gradleSetFile = projectRootPath+"/settings.gradle";
            if(FileUtil.isNotEmpty(new File(mavenPomFile))){
                PROPS.put("project.compile.type",ProjectCompileEnum.Maven.name());
                projectCompileType = ProjectCompileEnum.Maven.name();
                log.info("auto detection project compile type maven");
            }else if(FileUtil.isNotEmpty(new File(gradleSetFile))){
                PROPS.put("project.compile.type",ProjectCompileEnum.Gradle.name());
                projectCompileType = ProjectCompileEnum.Gradle.name();
                log.info("auto detection project compile type gradle");
            }
        }
        return projectCompileType;
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
        //设置module的描述
        for (ModuleInfoDto moduleInfoDto : subProjectRootDir) {
            String modulePomFile = moduleInfoDto.getModulePath()+ "/pom.xml";
            Document moduleDocument = XmlUtil.readXML(modulePomFile);
            if(moduleDocument != null){
                NodeList element = moduleDocument.getElementsByTagName("description");
                if(element != null && element.getLength() > 0){
                    moduleInfoDto.setModuleDesc(element.item(0).getTextContent());
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
    public String getProp(String propKey, String defaultVal) {
        String prop = getProp(propKey);
        return  prop == null ? defaultVal:prop;
    }

    @Override
    public Map<String, String> getAllProp() {
        return this.PROPS;
    }
}
