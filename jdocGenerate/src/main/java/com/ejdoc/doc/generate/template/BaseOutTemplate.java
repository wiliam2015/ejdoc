package com.ejdoc.doc.generate.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.GroupedMap;
import cn.hutool.setting.Setting;
import com.ejdoc.doc.generate.enums.TemplateThemeEnum;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.exception.JavaDocSerializeException;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil;
import com.ejdoc.doc.generate.util.beetl.function.MemberRenderUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class BaseOutTemplate {

    private static final Logger log = LoggerFactory.getLogger(BaseOutTemplate.class);
    private static final String I18N_DEFAULT_CLASSPATH ="com/ejdoc/doc/generate/config/i18n/";

    private static final String TEMPLATE_DEFAULT_CLASSPATH ="com/ejdoc/doc/generate/config/template/";

    private static final String DEFAULT_I18N_FILE_NAME ="language.setting";

    private Setting i18nSetting;
    private GroupTemplate groupTemplate;

    private DocGenerateConfig docGenerateConfig;

    public BaseOutTemplate(){
        this(null,null);


    }

    public BaseOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig){
        Assert.notNull(docGenerateConfig,"JavaDocGenerateConfig not null");
        this.groupTemplate = groupTemplate;
        this.docGenerateConfig = docGenerateConfig;
        if(groupTemplate == null){
            this.groupTemplate = createDefaultGroupTemplate();
        }

        loadI18N(docGenerateConfig.getLocale(), docGenerateConfig.getI18nClasspath());
        registerBeetlFunctionPackage(this.groupTemplate);
    }

    private void registerBeetlFunctionPackage(GroupTemplate groupTemplate) {
        if(groupTemplate != null){
            groupTemplate.registerFunctionPackage("docClassFn",new DocClassRenderUtil());
            groupTemplate.registerFunctionPackage("memberFn",new MemberRenderUtil());
        }
    }

    protected void loadI18N(Locale locale, String configFilePath){
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

    protected String loadTemplate(TemplateTypeEnum templateType, Map propMap,DocOutFileInfo docOutFileInfo){
        Template template = loadCustomTemplate(templateType, docGenerateConfig);
        boolean mainFile = docOutFileInfo.isMainFile();
        String docType = docOutFileInfo.getDocType();
        String templateAppendName = (String)propMap.getOrDefault("templateAppendName", "");
        TemplateThemeEnum templateTheme = docOutFileInfo.getTemplateTheme();
        String appendName = "";
        if(mainFile){
            appendName = "Main";
        }else if(StrUtil.isNotBlank(templateAppendName)){
            appendName = templateAppendName;
        }
        if(template == null){
            if(templateType == null){
                templateType = TemplateTypeEnum.MarkDown;
            }
            if(templateTheme == null){
                templateTheme = TemplateThemeEnum.Docsify;
            }
            switch (templateType){
                case MarkDown:
                    template = groupTemplate.getTemplate( "/markdown/"+docType+"/theme/"+templateTheme.getCode()+"/markdownTemplate"+appendName+".btl");
                    break;
                case Html:
                    template = groupTemplate.getTemplate( "/html/"+docType+"/htmlTemplate"+appendName+".btl");
                    break;
            }
        }

        return renderByTemplate(propMap, template);
    }

    protected String renderByTemplate(Map propMap, Template template) {
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
            template.binding("prop", propMap);
            return template.render();
        } catch (BeetlException e) {
            log.error("load beetl template error templateType",e);
            return "";
        }
    }

    /**
     * 加载自定义模板文件,子类可以重写，扩展自己的逻辑
     * @param templateType
     * @param docGenerateConfig
     * @return
     */
    protected Template loadCustomTemplate(TemplateTypeEnum templateType, DocGenerateConfig docGenerateConfig) {
        return null;
    }


    private GroupTemplate createDefaultGroupTemplate() {
        String templateClasspath = docGenerateConfig.getTemplateClasspath();
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

    public Setting getI18nSetting() {
        return i18nSetting;
    }

    public GroupTemplate getGroupTemplate() {
        return groupTemplate;
    }

    public DocGenerateConfig getDocGenerateConfig() {
        return docGenerateConfig;
    }
}
