package com.ejdoc.doc.generate.out;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.env.DocOutEnvironment;
import com.ejdoc.doc.generate.env.impl.DefaultDocOutEnvironment;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.metainfo.seralize.parser.MetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.UnSolvedSymbolTool;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 生成javadoc文档基类
 */
public abstract class AbstractDocGenerate implements DocGenerate{

    private static final Logger log = LoggerFactory.getLogger(AbstractDocGenerate.class);


    private static final String MAIN_JSON_FILE = "projectMetaInfo.json";
    private DocOutEnvironment environment;

    private DocOutTemplate docOutTemplate;

    private DocTemplateTheme docTemplateTheme;
    private DocGenerateConfig docGenerateConfig;


    ClassPathResource logoFile = new ClassPathResource("com/ejdoc/doc/generate/config/logoInfo.properties");

    public AbstractDocGenerate(DocGenerateConfig docGenerateConfig,DocOutTemplate docOutTemplate,DocTemplateTheme docTemplateTheme){
        this(docGenerateConfig,new DefaultDocOutEnvironment(),docOutTemplate,docTemplateTheme);
    }

    public AbstractDocGenerate(DocGenerateConfig docGenerateConfig, DocOutEnvironment environment, DocOutTemplate docOutTemplate,DocTemplateTheme docTemplateTheme){
        Assert.notNull(docGenerateConfig,"docGenerateConfig not null");
        Assert.notNull(environment,"DocOutEnvironment not null");
        Assert.notNull(docOutTemplate,"docOutTemplate not null");
        this.docGenerateConfig = docGenerateConfig;
        this.environment = environment;
        this.docOutTemplate = docOutTemplate;
        this.docTemplateTheme = docTemplateTheme;
        log.info("ready to start \n"+logoFile.readUtf8Str());
    }


    @Override
    public Set<String> printDoc(){
        log.info("start printDoc");
        log.info("environment ready start");
        MetaInfoParser metaInfoParser = new JavaParserMetaInfoParser(this.environment.getMetaEnvironment());
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        seralizeConfig.setUseAbsPath(true);
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl(metaInfoParser);
        log.info("environment ready finish");

        log.info("loadJavaMetaSeralizePlugin start");
        loadJavaMetaSeralizePlugin(javaMetaSeralize);
        log.info("loadJavaMetaSeralizePlugin finish");

        log.info("create javaMetaSeralize file start");
        String jsonFilePath = javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
        log.info("create javaMetaSeralize file finish");

        log.info("ready create render template doc file start");
        String renderFilePath = renderTemplateFile(seralizeConfig, jsonFilePath);
        log.info("ready create render template doc file finish");

        log.info("ready copy resource file file start");
        copyResourceFile(seralizeConfig, jsonFilePath,renderFilePath);
        log.info("ready copy resource file file finish");

        log.info("ready create render theme doc file start");
        renderThemeFile(seralizeConfig, renderFilePath,jsonFilePath);
        log.info("ready create render theme doc file finish");

        log.info("the create doc folder path:{}",renderFilePath);
        return printUnsolveSymbol();
    }

    /**
     * 复制资源文件
     * @param seralizeConfig
     * @param jsonFilePath
     */
    private void copyResourceFile(SeralizeConfig seralizeConfig, String jsonFilePath,String renderFilePath) {
        String docOutRootPath = this.environment.getDocOutRootPath();
        List<File> resourceFiles = FileUtil.loopFiles(jsonFilePath, subFile -> FileTypeUtil.getType(subFile).equals("md"));
        if(CollectionUtil.isNotEmpty(resourceFiles)){
            for (File resFile : resourceFiles) {
                if(resFile.getName().equals(MAIN_JSON_FILE)){
                    continue;
                }
                copyFile(seralizeConfig,docOutRootPath, jsonFilePath, resFile,false);
            }
        }
    }

    /**
     * 渲染主题文件
     * @param seralizeConfig
     * @param renderFilePath
     */
    private void renderThemeFile(SeralizeConfig seralizeConfig, String renderFilePath,String jsonFilePath) {
        DocTemplateThemeInfo docTemplateThemeInfo = new DocTemplateThemeInfo();
        docTemplateThemeInfo.setJsonFilePath(jsonFilePath);
        docTemplateThemeInfo.setSeralizeConfig(seralizeConfig);
        docTemplateThemeInfo.setTemplateType(docGenerateConfig.getTemplateType());
        docTemplateThemeInfo.setRenderFilePath(renderFilePath);
        docTemplateThemeInfo.setDocOutRootPath(environment.getDocOutRootPath());
        docTemplateThemeInfo.setProjectRootPath(environment.getProjectRootPath());
        docTemplateThemeInfo.setVersion(environment.getVersion());
        docTemplateThemeInfo.setTemplateCustomProp(environment.getAllProp());
        String renderFileRootPath = StrUtil.join("/",environment.getDocOutRootPath(),"doc",docGenerateConfig.getDocTypeEnum().getCode(),docGenerateConfig.getTemplateType().getCode());
        docTemplateThemeInfo.setRenderFileRootPath(renderFileRootPath);
        docTemplateTheme.writeTemplateThemeFile(docTemplateThemeInfo);
    }

    private  Set<String> printUnsolveSymbol() {
        Set<String> unSolvedCache = UnSolvedSymbolTool.getUnSolvedCache();
        if(CollectionUtil.isNotEmpty(unSolvedCache)){
            log.info("未解析成功的符号:{}",JSONUtil.toJsonStr(unSolvedCache));
        }
        return unSolvedCache;
    }
    /**
     * 渲染doc文件
     * @param seralizeConfig
     * @param jsonFilePath
     */
    private String renderTemplateFile(SeralizeConfig seralizeConfig, String jsonFilePath) {
        String docOutRootPath = this.environment.getDocOutRootPath();
        String version = this.environment.getVersion();
        List<File> jsonFiles = FileUtil.loopFiles(jsonFilePath, subFile -> FileTypeUtil.getType(subFile).equals("json"));
        //生命周期钩子
        afterCreateBatchMetaFile(jsonFiles, seralizeConfig,this.environment);
        File mainJsonFile = new File(jsonFilePath +"/"+MAIN_JSON_FILE);
        mainJsonFile = beforeResolveFile(mainJsonFile, seralizeConfig,this.environment,true);

        resolveFile(seralizeConfig,docOutRootPath, jsonFilePath,mainJsonFile,true);
        afterResolveFile(mainJsonFile, seralizeConfig,this.environment,true);
        if(CollectionUtil.isNotEmpty(jsonFiles)){
            for (File jsonFile : jsonFiles) {
                if(jsonFile.getName().equals(MAIN_JSON_FILE)){
                    continue;
                }
                jsonFile = beforeResolveFile(jsonFile, seralizeConfig,this.environment,false);
                resolveFile(seralizeConfig,docOutRootPath, jsonFilePath, jsonFile,false);
                afterResolveFile(jsonFile, seralizeConfig,this.environment,false);
            }
        }
        return StrUtil.join("/",docOutRootPath,"doc",docGenerateConfig.getDocTypeEnum().getCode(),docGenerateConfig.getTemplateType().getCode(),"v",version);
    }

    /**
     * 加载序列化插件
     * @param javaMetaSeralize
     */
    private void loadJavaMetaSeralizePlugin(JavaMetaSeralize javaMetaSeralize) {
        doLoadJavaMetaSeralizePlugin(javaMetaSeralize);
        if(CollectionUtil.isNotEmpty(javaMetaSeralize.getMetaSeralizePlugins())){
            List<String> pluginNameList = javaMetaSeralize.getMetaSeralizePlugins().stream().map(metaPlagin -> metaPlagin.getClass().getSimpleName()).collect(Collectors.toList());
            log.info("loadJavaMetaSeralizePlugin the list:{}", JSONUtil.toJsonStr(pluginNameList));
        }
    }

    /**
     *  加载序列化插件 交给子类实现
     * @param javaMetaSeralize
     */
    protected abstract void doLoadJavaMetaSeralizePlugin(JavaMetaSeralize javaMetaSeralize);

    protected void afterResolveFile(File mainJsonFile, SeralizeConfig seralizeConfig, DocOutEnvironment environment,boolean mainFile) {

    }

    protected File beforeResolveFile( File jsonFile, SeralizeConfig seralizeConfig, DocOutEnvironment environment,boolean mainFile) {
        return jsonFile;
    }

    protected void afterCreateBatchMetaFile(List<File> jsonFiles, SeralizeConfig seralizeConfig, DocOutEnvironment environment) {

    }

    protected void resolveFile(SeralizeConfig seralizeConfig,String docOutRootPath, String jsonFilePath, File jsonFile,boolean mainFile) {
        DocOutFileInfo docOutFileInfo = new DocOutFileInfo();
        String jsonFileName = jsonFile.getName();
        if(!mainFile && jsonFileName.equals(MAIN_JSON_FILE)){
            return;
        }
        docOutFileInfo.setFileName(FileUtil.mainName(jsonFileName));
        String filePath = jsonFile.getPath();
        String replaceFilePath = filePath.replace(jsonFilePath,"").replace("/"+jsonFileName,"");
        docOutFileInfo.setRelativeRootPath(replaceFilePath);
        docOutFileInfo.setJsonFileRootPath(jsonFilePath);
        docOutFileInfo.setFullFilePath(jsonFile.getPath());
        docOutFileInfo.setJsonFile(jsonFile);
        docOutFileInfo.setSeralizeConfig(seralizeConfig);
        docOutFileInfo.setMainFile(mainFile);
        docOutFileInfo.setDocOutRootPath(docOutRootPath);
        docOutFileInfo.setVersion(environment.getVersion());
        docOutFileInfo.setLocale(docGenerateConfig.getLocale());
        docOutFileInfo.setTemplateType(docGenerateConfig.getTemplateType());
        docOutFileInfo.setDocType(docGenerateConfig.getDocTypeEnum().getCode());
        String templateData = docOutTemplate.formatTemplate(docOutFileInfo);
        if(StrUtil.isBlank(templateData)){
            log.warn("jsonFileName templateData is Empty fileName:{}", jsonFile.getName());
            return;
        }
        docOutTemplate.writeFormat(templateData,docOutFileInfo);
    }


    /**
     * 复制文件
     * @param seralizeConfig
     * @param docOutRootPath
     * @param jsonFilePath
     * @param jsonFile
     * @param mainFile
     */
    protected void copyFile(SeralizeConfig seralizeConfig,String docOutRootPath, String jsonFilePath, File jsonFile,boolean mainFile) {
        DocOutFileInfo docOutFileInfo = new DocOutFileInfo();
        String jsonFileName = jsonFile.getName();
        if(!mainFile && jsonFileName.equals(MAIN_JSON_FILE)){
            return;
        }
        docOutFileInfo.setFileName(FileUtil.mainName(jsonFileName));
        String filePath = jsonFile.getPath();
        String replaceFilePath = filePath.replace(jsonFilePath,"").replace("/"+jsonFileName,"");
        docOutFileInfo.setRelativeRootPath(replaceFilePath);
        docOutFileInfo.setJsonFileRootPath(jsonFilePath);
        docOutFileInfo.setFullFilePath(jsonFile.getPath());
        docOutFileInfo.setJsonFile(jsonFile);
        docOutFileInfo.setSeralizeConfig(seralizeConfig);
        docOutFileInfo.setMainFile(mainFile);
        docOutFileInfo.setDocOutRootPath(docOutRootPath);
        docOutFileInfo.setLocale(docGenerateConfig.getLocale());
        docOutFileInfo.setTemplateType(docGenerateConfig.getTemplateType());
        docOutFileInfo.setDocType(docGenerateConfig.getDocTypeEnum().getCode());
        docOutFileInfo.setVersion(this.environment.getVersion());
        docOutTemplate.copyFile(docOutFileInfo);
    }

}
