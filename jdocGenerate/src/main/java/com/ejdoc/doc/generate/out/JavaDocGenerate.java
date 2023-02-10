package com.ejdoc.doc.generate.out;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.env.DocOutEnvironment;
import com.ejdoc.doc.generate.env.impl.DefaultDocOutEnvironment;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.JavaDocGenerateConfig;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.html.HtmlDocOutTemplate;
import com.ejdoc.doc.generate.template.markdown.MarkdownDocOutTemplate;
import com.ejdoc.metainfo.seralize.paraser.MetaInfoParaser;
import com.ejdoc.metainfo.seralize.paraser.impl.javaparaser.JavaParaserMetaInfoParaser;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class JavaDocGenerate {
    private static final Logger log = LoggerFactory.getLogger(JavaDocGenerate.class);


    private static final String MAIN_JSON_FILE = "projectMetaInfo.json";
    private DocOutEnvironment environment;

    private DocOutTemplate docOutTemplate;

    private JavaDocGenerateConfig javaDocGenerateConfig;

    public JavaDocGenerate(){
        this(null,new DefaultDocOutEnvironment(),null);
    }

    public JavaDocGenerate(JavaDocGenerateConfig javaDocGenerateConfig){
        this(javaDocGenerateConfig,new DefaultDocOutEnvironment(),null);
    }

    public JavaDocGenerate(JavaDocGenerateConfig javaDocGenerateConfig,DocOutEnvironment environment,DocOutTemplate docOutTemplate){
        Assert.notNull(environment,"DocOutEnvironment not null");
        this.javaDocGenerateConfig = javaDocGenerateConfig;
        if(javaDocGenerateConfig == null){
            this.javaDocGenerateConfig = createDefaultJavaDocGenerateConfig();
        }

        this.environment = environment;
        this.docOutTemplate = docOutTemplate;
        if(docOutTemplate == null){
            this.docOutTemplate = createDefaultDocOutTemplate(this.javaDocGenerateConfig);
        }

    }

    private DocOutTemplate createDefaultDocOutTemplate(JavaDocGenerateConfig javaDocGenerateConfig) {
        DocOutTemplate docOutTemplate = null;
        TemplateTypeEnum templateType = this.javaDocGenerateConfig.getTemplateType();
        switch (templateType){
            case MarkDown:
                docOutTemplate = new MarkdownDocOutTemplate(javaDocGenerateConfig);
                break;
            case Html:
                docOutTemplate = new HtmlDocOutTemplate(javaDocGenerateConfig);
                break;
        }
        return docOutTemplate;
    }


    public void printJavaDoc(){
        String docOutRootPath = this.environment.getDocOutRootPath();
        MetaInfoParaser metaInfoParaser = new JavaParaserMetaInfoParaser();
        log.info("start meta");
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl(metaInfoParaser);
        String jsonFilePath = javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
        File mainJsonFile = new File(jsonFilePath+"/"+MAIN_JSON_FILE);
        resolveFile(docOutRootPath,jsonFilePath,mainJsonFile,true);
        List<File> jsonFiles = FileUtil.loopFiles(jsonFilePath, subFile -> FileTypeUtil.getType(subFile).equals("json"));
        if(CollectionUtil.isNotEmpty(jsonFiles)){
            for (File jsonFile : jsonFiles) {
                resolveFile(docOutRootPath, jsonFilePath, jsonFile,false);
            }
        }

    }

    private void resolveFile(String docOutRootPath, String jsonFilePath, File jsonFile,boolean mainFile) {
        DocOutFileInfo docOutFileInfo = new DocOutFileInfo();
        String jsonFileName = jsonFile.getName();
        if(!mainFile && jsonFileName.equals(MAIN_JSON_FILE)){
            return;
        }
        docOutFileInfo.setFileName(FileUtil.mainName(jsonFileName));
        String filePath = jsonFile.getPath();
        String replaceFilePath = filePath.replace(jsonFilePath,"").replace("/"+jsonFileName,"");
        docOutFileInfo.setRelativeRootPath(replaceFilePath);
        docOutFileInfo.setFullFilePath(jsonFile.getPath());
        docOutFileInfo.setJsonFile(jsonFile);
        docOutFileInfo.setMainFile(mainFile);
        docOutFileInfo.setDocOutRootPath(docOutRootPath);
        docOutFileInfo.setLocale(javaDocGenerateConfig.getLocale());
        docOutFileInfo.setTemplateType(javaDocGenerateConfig.getTemplateType());
        String templateData = docOutTemplate.formatTemplate(docOutFileInfo);
        if(StrUtil.isBlank(templateData)){
            log.warn("jsonFileName templateData is Empty fileName:{}", jsonFile.getName());
            return;
        }
        docOutTemplate.writeFormat(templateData,docOutFileInfo);
    }

    private JavaDocGenerateConfig createDefaultJavaDocGenerateConfig() {
        JavaDocGenerateConfig innerJavaDocGenerateConfig = new JavaDocGenerateConfig();
        innerJavaDocGenerateConfig.setLocale(Locale.CHINA);
        innerJavaDocGenerateConfig.setTemplateType(TemplateTypeEnum.MarkDown);
        return innerJavaDocGenerateConfig;
    }
}
