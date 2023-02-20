package com.ejdoc.metainfo.seralize.seralize.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaModuleMeta;
import com.ejdoc.metainfo.seralize.model.JavaProjectMeta;
import com.ejdoc.metainfo.seralize.paraser.MetaInfoParaser;
import com.ejdoc.metainfo.seralize.paraser.impl.javaparaser.JavaParaserMetaInfoParaser;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaJsonSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaMetaJsonSeralizeImpl implements JavaMetaJsonSeralize {
    private static final Logger log = LoggerFactory.getLogger(JavaMetaJsonSeralizeImpl.class);
    private MetaInfoParaser metaInfoParaser;

    private static final String FILE_OUT_PATH_KEY = "project.meta.seralize.out";

    public JavaMetaJsonSeralizeImpl(){
        this(new JavaParaserMetaInfoParaser());
    }

    public JavaMetaJsonSeralizeImpl(MetaInfoParaser metaInfoParaser){
        Assert.notNull(metaInfoParaser, "MetaInfoParaser can not be null !");
        this.metaInfoParaser = metaInfoParaser;

    }
    @Override
    public void exeJavaMetaSeralize() {
        doJavaMetaSeralize(new SeralizeConfig());
    }

    @Override
    public String exeJavaMetaSeralize(SeralizeConfig seralizeConfig) {
        return doJavaMetaSeralize(seralizeConfig);
    }

    public String doJavaMetaSeralize(SeralizeConfig seralizeConfig) {
        Assert.notNull(seralizeConfig,"SeralizeConfig object can not be null!");

        MetaEnvironment metaEnvironment = metaInfoParaser.getMetaEnvironment();
        JavaProjectMeta javaProjectMeta = metaInfoParaser.parseJavaProjectMeta();
        List<JavaModuleMeta> javaModuleMetas = metaInfoParaser.parseAllJavaModuletMeta();

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
}
