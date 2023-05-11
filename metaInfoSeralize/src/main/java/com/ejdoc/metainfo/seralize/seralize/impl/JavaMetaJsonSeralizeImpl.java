package com.ejdoc.metainfo.seralize.seralize.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.MetaIndexContextBuilder;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaModuleMeta;
import com.ejdoc.metainfo.seralize.model.JavaProjectMeta;
import com.ejdoc.metainfo.seralize.parser.MetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaJsonSeralize;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaMetaJsonSeralizeImpl implements JavaMetaJsonSeralize {
    private static final Logger log = LoggerFactory.getLogger(JavaMetaJsonSeralizeImpl.class);
    private MetaInfoParser metaInfoParser;

    private List<JavaMetaSeralizePlugin> metaSeralizePlugins;
    private static final String FILE_OUT_PATH_KEY = "project.meta.seralize.out";

    public JavaMetaJsonSeralizeImpl(){
        this(new JavaParserMetaInfoParser());
        this.metaSeralizePlugins = new ArrayList<>();
    }

    public JavaMetaJsonSeralizeImpl(String configFilePath){
        this(new JavaParserMetaInfoParser(configFilePath));
        this.metaSeralizePlugins = new ArrayList<>();
    }

    public JavaMetaJsonSeralizeImpl(MetaInfoParser metaInfoParser){
        Assert.notNull(metaInfoParser, "MetaInfoParaser can not be null !");
        this.metaInfoParser = metaInfoParser;
        this.metaSeralizePlugins = new ArrayList<>();

    }
    @Override
    public String exeJavaMetaSeralize() {
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        String outFilePath = doJavaMetaSeralize(seralizeConfig);
        doJavaMetaPlugin(outFilePath,seralizeConfig);
        return outFilePath;
    }

    @Override
    public String exeJavaMetaSeralize(SeralizeConfig seralizeConfig) {
        String outFilePath = doJavaMetaSeralize(seralizeConfig);
        doJavaMetaPlugin(outFilePath,seralizeConfig);
        return outFilePath;
    }

    private void doJavaMetaPlugin(String outFilePath, SeralizeConfig seralizeConfig) {
        if(CollectionUtil.isNotEmpty(metaSeralizePlugins)){
            MetaIndexContextBuilder.instance().createMetaIndexContext(outFilePath);
            JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto = new JavaMetaServalizePluginContextDto();
            javaMetaServalizePluginContextDto.setSeralizeConfig(seralizeConfig);
            javaMetaServalizePluginContextDto.setSeralizeOutPath(outFilePath);

            log.info("JavaMetaSeralizePlugin ready data finish");
            //自定义实现插件，向插件中的数据对象jsonObject写入属性即可
            for (JavaMetaSeralizePlugin metaSeralizePlugin : metaSeralizePlugins) {
                log.info("JavaMetaSeralizePlugin ready exe start name:{}",metaSeralizePlugin.getClass().getSimpleName());
                metaSeralizePlugin.exePostJavaMetaSeralize(javaMetaServalizePluginContextDto);
                log.info("JavaMetaSeralizePlugin ready exe finish name:{}",metaSeralizePlugin.getClass().getSimpleName());
            }

            log.info("JavaMetaSeralizePlugin ready reWriteJsonFile start");
            reWriteJsonFile(seralizeConfig);
            log.info("JavaMetaSeralizePlugin ready reWriteJsonFile finish");
        }
    }

    /**
     * 准备插件执行数据
     * @param outFilePath
     * @return
     */
    private List<JavaMetaSeralizePluginData> readyPluginData(String outFilePath,List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList,Map<String,JavaMetaSeralizePluginData> metaSeralizeFileIndex) {
        List<File> jsonFiles = FileUtil.loopFiles(outFilePath, subFile -> FileTypeUtil.getType(subFile).equals("json"));
        for (File jsonFile : jsonFiles) {
            JavaMetaSeralizePluginData javaMetaSeralizePluginData = new JavaMetaSeralizePluginData();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            javaMetaSeralizePluginData.setFile(jsonFile);
            javaMetaSeralizePluginData.setJsonObject(jsonObject);
            javaMetaSeralizePluginData.setJsonFilePath(jsonFile.getPath());
            allJavaMetaSeralizeClassList.add(javaMetaSeralizePluginData);
            metaSeralizeFileIndex.put(jsonObject.getStr("fullClassName"),javaMetaSeralizePluginData);
        }
        return allJavaMetaSeralizeClassList;
    }

    /**
     * 重新生成json文件
     * @param seralizeConfig
     */
    private  void reWriteJsonFile(SeralizeConfig seralizeConfig) {

        for (JavaMetaFileInfo javaMetaFileInfo : MetaIndexContext.getJavaMetaFileInfos()) {
            String jsonStr ="";
            if(seralizeConfig.isPrettyFormat()){
                jsonStr = javaMetaFileInfo.getJsonObject().toStringPretty();
            }else{
                jsonStr = javaMetaFileInfo.getJsonObject().toString();
            }
            FileUtil.writeString(jsonStr,javaMetaFileInfo.getJsonFilePath(),CharsetUtil.CHARSET_UTF_8);
        }
    }

    @Override
    public boolean addMetaSeralizePlugins(JavaMetaSeralizePlugin metaSeralizePlugin) {
       return this.metaSeralizePlugins.add(metaSeralizePlugin);
    }

    public String doJavaMetaSeralize(SeralizeConfig seralizeConfig) {
        Assert.notNull(seralizeConfig,"SeralizeConfig object can not be null!");

        MetaEnvironment metaEnvironment = metaInfoParser.getMetaEnvironment();
        JavaProjectMeta javaProjectMeta = metaInfoParser.parseJavaProjectMeta();
        List<JavaModuleMeta> javaModuleMetas = metaInfoParser.parseAllJavaModuletMeta();

        String javaProjectMetaJson;
        if(seralizeConfig.isPrettyFormat()){
            javaProjectMetaJson = JSONUtil.toJsonPrettyStr(javaProjectMeta);
        }else{
            javaProjectMetaJson = JSONUtil.toJsonStr(javaProjectMeta);
        }
        String configFilePath = metaEnvironment.getProp(FILE_OUT_PATH_KEY);
        if(StrUtil.isBlank(configFilePath)){
            configFilePath = metaEnvironment.getProjectRootPath();
        }

        FileUtil.writeString(javaProjectMetaJson, configFilePath + "/doc/projectMetaInfo.json", "UTF-8");
        if(CollectionUtil.isNotEmpty(javaModuleMetas)){
            for (JavaModuleMeta javaModuleMeta : javaModuleMetas) {
                List<JavaClassMeta> javaClassMetas = javaModuleMeta.getJavaClassMetas();
                String moduleName = javaModuleMeta.getName();
                for (JavaClassMeta javaClassMeta : javaClassMetas) {
                    String javaClassMetaJson ;
                    if(seralizeConfig.isPrettyFormat()){
                        javaClassMetaJson = JSONUtil.toJsonPrettyStr(javaClassMeta);
                    }else{
                        javaClassMetaJson = JSONUtil.toJsonStr(javaClassMeta);
                    }
                    String packageName = javaClassMeta.getPackageName();
                    String packageDir = "";
                    if(StrUtil.isNotBlank(packageName)){
                        packageDir = packageName.replace(".","/");
                    }
                    FileUtil.writeString(javaClassMetaJson, configFilePath + "/doc/"+moduleName+"/"+packageDir+"/"+javaClassMeta.getClassName()+".json", "UTF-8");
                }
            }
        }
        return configFilePath+"/doc";
    }

    public MetaInfoParser getMetaInfoParser() {
        return metaInfoParser;
    }

    public void setMetaInfoParser(MetaInfoParser metaInfoParser) {
        this.metaInfoParser = metaInfoParser;
    }

    @Override
    public List<JavaMetaSeralizePlugin> getMetaSeralizePlugins() {
        return metaSeralizePlugins;
    }

}
