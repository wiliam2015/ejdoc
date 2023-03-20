package com.ejdoc.metainfo.seralize.parser.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.model.JavaProjectMeta;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.ejdoc.metainfo.seralize.resource.impl.DefaultMetaFileRead;
import com.ejdoc.metainfo.seralize.parser.MetaInfoParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMetaInfoParser implements MetaInfoParser {

    protected MetaFileRead metaFileRead;


    public AbstractMetaInfoParser(){
        this(new DefaultMetaFileRead());
    }
    public AbstractMetaInfoParser(MetaFileRead metaFileRead){
        this.metaFileRead = metaFileRead;
    }



    @Override
    public JavaProjectMeta parseJavaProjectMeta() {
        JavaProjectMeta javaProjectMeta = new JavaProjectMeta();
        File file = metaFileRead.readProjectMetaFile();
        MetaEnvironment metaEnvironment = metaFileRead.getMetaEnvironment();
        Assert.notNull(file,"read projectMeta.yml file is null");
        Dict dict = YamlUtil.loadByPath(file.getPath());
        Assert.notEmpty(dict,"projectMeta.yml  content is empty");
        String name = dict.get("name", "");
        Boolean open = dict.get("open", false);
        String title = dict.get("title", "");
        String contract = dict.get("contract", "");
        String author = dict.get("author", "");
        String email = dict.get("email", "");
        String host = dict.get("host", "");
        String basePath = dict.get("basePath", "");
        String description = dict.get("description", "");
        String version = dict.get("version", "");
        String licenseName = dict.getByPath("license.name",String.class);
        String licenseUrl = dict.getByPath("license.url", String.class);
        javaProjectMeta.setName(name);
        javaProjectMeta.setOpen(open);
        javaProjectMeta.setTitle(title);
        javaProjectMeta.setContract(contract);
        javaProjectMeta.setAuthor(author);
        javaProjectMeta.setEmail(email);
        javaProjectMeta.setHost(host);
        javaProjectMeta.setBasePath(basePath);
        javaProjectMeta.setDescription(description);
        javaProjectMeta.setVersion(version);
        javaProjectMeta.setAllProp(dict);
        Map<String,String> licenseMap = new HashMap<>();
        if(StrUtil.isNotBlank(licenseName)){
            licenseMap.put("name",licenseName);
        }
        if(StrUtil.isNotBlank(licenseUrl)){
            licenseMap.put("url",licenseUrl);
        }
        javaProjectMeta.setLicense(licenseMap);
        if(StrUtil.isBlank(version)){
            String versionEnv = metaEnvironment.getProp("version", "");
            javaProjectMeta.setVersion(versionEnv);
        }
        if(StrUtil.isBlank(description)){
            description = metaEnvironment.getProp("description", "");
            javaProjectMeta.setDescription(description);
        }

        if(StrUtil.isBlank(javaProjectMeta.getRepo())){
            String url = metaEnvironment.getProp("url", "");
            javaProjectMeta.setRepo(url);
        }

        return javaProjectMeta;
    }


    @Override
    public MetaEnvironment getMetaEnvironment() {
        return metaFileRead.getMetaEnvironment();
    }

    protected MetaFileRead initDefaultMetaFileRead(){
        metaFileRead = new DefaultMetaFileRead();
        return metaFileRead;
    }


}
