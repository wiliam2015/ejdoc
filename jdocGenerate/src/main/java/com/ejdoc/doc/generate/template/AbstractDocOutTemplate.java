package com.ejdoc.doc.generate.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.GroupedMap;
import cn.hutool.setting.Setting;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.exception.JavaDocSerializeException;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.JavaDocGenerateConfig;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public abstract class AbstractDocOutTemplate implements DocOutTemplate{
    private static final Logger log = LoggerFactory.getLogger(AbstractDocOutTemplate.class);
    private static final String I18N_DEFAULT_CLASSPATH ="com/ejdoc/doc/generate/config/i18n/";

    private static final String TEMPLATE_DEFAULT_CLASSPATH ="com/ejdoc/doc/generate/config/template/";

    private static final String DEFAULT_I18N_FILE_NAME ="language.setting";

    private Setting i18nSetting;
    private GroupTemplate groupTemplate;

    private JavaDocGenerateConfig javaDocGenerateConfig;

    public AbstractDocOutTemplate(){
        this(null,null);


    }

    public AbstractDocOutTemplate(GroupTemplate groupTemplate, JavaDocGenerateConfig javaDocGenerateConfig){
        Assert.notNull(javaDocGenerateConfig,"JavaDocGenerateConfig not null");
        this.groupTemplate = groupTemplate;
        this.javaDocGenerateConfig = javaDocGenerateConfig;
        if(groupTemplate == null){
            this.groupTemplate = createDefaultGroupTemplate();
        }

        loadI18N(javaDocGenerateConfig.getLocale(),javaDocGenerateConfig.getI18nClasspath());
    }

    protected void loadI18N(Locale locale,String configFilePath){
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String i18nFileName = StrUtil.format("language_{}_{}.setting",language,country);
        Setting defaultMetaEnvSetting = null;
        try {
            defaultMetaEnvSetting = new Setting(I18N_DEFAULT_CLASSPATH+i18nFileName, CharsetUtil.CHARSET_UTF_8, true);
        } catch (NoResourceException e) {
            log.debug("not found file {} use default file",I18N_DEFAULT_CLASSPATH+i18nFileName);
            defaultMetaEnvSetting = new Setting(I18N_DEFAULT_CLASSPATH+DEFAULT_I18N_FILE_NAME, CharsetUtil.CHARSET_UTF_8, true);
        }
        if(StrUtil.isNotBlank(configFilePath)){
            defaultMetaEnvSetting.addSetting(new Setting(configFilePath,CharsetUtil.CHARSET_UTF_8, true));
        }
        i18nSetting = defaultMetaEnvSetting;

    }

    protected String loadTemplate(TemplateTypeEnum templateType, Map propMap, boolean mainFile){
        Template template = loadCustomTemplate(templateType,javaDocGenerateConfig);
        String appendName = "";
        if(mainFile){
            appendName = "Main";
        }
        if(template == null){
            if(templateType == null){
                templateType = TemplateTypeEnum.MarkDown;
            }
            switch (templateType){
                case MarkDown:
                    template = groupTemplate.getTemplate( "/markdown/markdownTemplate"+appendName+".btl");
                    break;
                case Html:
                    template = groupTemplate.getTemplate( "/html/htmlTemplate"+appendName+".btl");
                    break;
            }
        }

        try {
            GroupedMap groupedMap = i18nSetting.getGroupedMap();
            if(CollectionUtil.isNotEmpty(groupedMap)){
                Map<String,Map<String,String>> i18nSettingGroup = new HashMap<>();
                for (Map.Entry<String, LinkedHashMap<String, String>> singleGroupMap : groupedMap.entrySet()) {
                    String groupName = singleGroupMap.getKey();
                    LinkedHashMap<String, String> groupData = singleGroupMap.getValue();
                    i18nSettingGroup.put(groupName,groupData);
                }
                template.binding("i18n",i18nSettingGroup);
            }
            template.binding("prop",propMap);
            return template.render();
        } catch (BeetlException e) {
            log.error("load beetl template error templateType:{}",templateType,e);
            return "";
        }
    }

    /**
     * 加载自定义模板文件,子类可以重写，扩展自己的逻辑
     * @param templateType
     * @param javaDocGenerateConfig
     * @return
     */
    private Template loadCustomTemplate(TemplateTypeEnum templateType, JavaDocGenerateConfig javaDocGenerateConfig) {
        return null;
    }

    @Override
    public String formatTemplate(DocOutFileInfo docOutFileInfo) {
        TemplateTypeEnum templateType = loadTemplateType();
        Map propMap = convertJsonFileToProp(docOutFileInfo);
        String template = loadTemplate(templateType,propMap,docOutFileInfo.isMainFile());
        return template;
    }

    @Override
    public void writeFormat(String formatData, DocOutFileInfo docOutFileInfo) {
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String path = docOutRootPath+"/javadoc/"+templateType.getCode()+relativePath+"/"+docOutFileInfo.getFileName()+templateType.getExtension();
        FileUtil.writeString(formatData, path, "UTF-8");
    }

    protected abstract Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo);

    protected abstract TemplateTypeEnum loadTemplateType();


    private GroupTemplate createDefaultGroupTemplate() {
        String templateClasspath = javaDocGenerateConfig.getTemplateClasspath();
        if(StrUtil.isBlank(templateClasspath)){
            templateClasspath = TEMPLATE_DEFAULT_CLASSPATH;
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(templateClasspath);
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            log.error("create beetl Configuration error",e);
            throw new JavaDocSerializeException(JavaDocSerializeException.LOAD_FILE_ERROR,"beetl Configuration error");

        }
        return new GroupTemplate(resourceLoader, cfg);
    }


}
