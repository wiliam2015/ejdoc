package com.ejdoc.doc.generate.template.markdown.theme;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.template.BaseOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.JavaDocMarkdownDocOutTemplate;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaDocDocsifyTemplateTheme extends BaseOutTemplate implements DocTemplateTheme {
    /***/
    private final Logger log = LoggerFactory.getLogger(JavaDocMarkdownDocOutTemplate.class);

    public JavaDocDocsifyTemplateTheme() {
        super();
    }
    public JavaDocDocsifyTemplateTheme(DocGenerateConfig docGenerateConfig) {
       this(null,docGenerateConfig);
    }
    public JavaDocDocsifyTemplateTheme(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
        super(groupTemplate, docGenerateConfig);
    }


    /**
     * 创建模板主题文件，解耦渲染与数据组装逻辑，渲染使用模板
     * @param docTemplateThemeInfo
     */
    @Override
    public void writeTemplateThemeFile(DocTemplateThemeInfo docTemplateThemeInfo) {
        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();
        String jsonFilePath = docTemplateThemeInfo.getJsonFilePath();
        String projectRootPath = docTemplateThemeInfo.getProjectRootPath();

        Map<String,Object> propMap = new HashMap<>();
        List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos = readyBaseData(docTemplateThemeInfo);

        createRouteInfo(docTemplateThemeInfo, javaDocDocsifyThemeDtos);

        readyRenderProp(jsonFilePath, propMap);
        //渲染主文件index.html
        writeThemeTemplateFile(renderFilePath, propMap,"index.html","/index.html");

        createFile(renderFilePath,".nojekyll");
        createFile(renderFilePath,"_sidebar.md");
        copyStaticResource(renderFilePath,projectRootPath);
    }

    /**
     * 模板渲染动态数据组装
     * @param jsonFilePath
     * @param propMap
     */
    private void readyRenderProp(String jsonFilePath, Map<String, Object> propMap) {
        JSONObject jsonObject = JSONUtil.readJSONObject(new File(jsonFilePath +"/projectMetaInfo.json"), CharsetUtil.CHARSET_UTF_8);
        if(jsonObject != null && jsonObject.size()> 0){
            propMap.putAll(jsonObject);
        }
    }

    /**
     * 准备基础数据
     * @param docTemplateThemeInfo
     * @return
     */
    private List<JavaDocDocsifyThemeDto> readyBaseData(DocTemplateThemeInfo docTemplateThemeInfo) {
        List<File> jsonFiles = FileUtil.loopFiles(docTemplateThemeInfo.getJsonFilePath(), subFile -> FileTypeUtil.getType(subFile).equals("json"));
        List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos = new ArrayList<>();
        Map<String,String> packageDecsMap = new HashMap<>();

        for (File jsonFile : jsonFiles) {
            JavaDocDocsifyThemeDto javaDocDocsifyThemeDto = new JavaDocDocsifyThemeDto();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            String className = jsonObject.getStr("className");
            String moduleName = jsonObject.getStr("moduleName");
            String moduleDesc = jsonObject.getStr("moduleDesc");
            String packageName = jsonObject.getStr("packageName");
            if(StrUtil.equals(className,"package-info")){
                JSONObject javaModelMeta = jsonObject.getJSONObject("javaModelMeta");
                if(javaModelMeta != null){
                    String comment = javaModelMeta.getStr("comment", "");
                    if(StrUtil.isNotBlank(comment) && StrUtil.isNotBlank(packageName)){
                        packageDecsMap.put(packageName,replacePackageDesc(comment));
                    }
                }
            }else if(StrUtil.isNotBlank(moduleName) && StrUtil.isNotBlank(packageName)){
                javaDocDocsifyThemeDto.setProjectName(jsonObject.getStr("projectName"));
                javaDocDocsifyThemeDto.setModuleName(moduleName);
                javaDocDocsifyThemeDto.setModuleDesc(moduleDesc);
                javaDocDocsifyThemeDto.setClassName(jsonObject.getStr("className"));
                javaDocDocsifyThemeDto.setPackageName(packageName);
                javaDocDocsifyThemeDtos.add(javaDocDocsifyThemeDto);
            }
        }

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : javaDocDocsifyThemeDtos) {
            String packageName = javaDocDocsifyThemeDto.getPackageName();
            String packageDesc = packageDecsMap.get(packageName);
            javaDocDocsifyThemeDto.setPackageDesc(packageDesc);
        }
        return javaDocDocsifyThemeDtos;
    }

    /**
     * 创建路由信息 所有模块  所有包  所有类
     * @param docTemplateThemeInfo
     * @param javaDocDocsifyThemeDtos
     */
    private void createRouteInfo(DocTemplateThemeInfo docTemplateThemeInfo, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();

        createAllClassRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllPackageRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllModuleRoute(renderFilePath, javaDocDocsifyThemeDtos);

    }

    /**
     * 创建所有类路由
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",allClassList);
        prop.put("title","所有类");
        prop.put("sideType","allClass");
        prop.put("classType","allClass");

        String readMeFile = "/route/allclass/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allClassReadme.btl",readMeFile);

        String sideBarFile = "/route/allclass/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);
    }

    /**
     * 根据模板文件写入对应的主题信息
     * @param renderFilePath
     * @param prop
     * @param templateName 模板名称
     * @param renderFile 写入文件
     */
    private void writeThemeTemplateFile(String renderFilePath, Map<String, Object> prop,String templateName,String renderFile) {
        String templateFile = "/markdown/theme/docsify/" + templateName;
        Template sideBarTemplate = getGroupTemplate().getTemplate(templateFile);
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);
        String sideBarFile = renderFilePath + renderFile;
        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 创建所有包路由
     * @param renderFilePath
     * @param javaDocDocsifyThemeDtos
     */
    private void createAllPackageRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {

        List<JavaDocDocsifyThemeDto> packageFileList = createPackageAndSubFile(javaDocDocsifyThemeDtos);

        //打印所有包信息
        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",packageFileList);
        prop.put("packageType","allPackage");
        prop.put("title","所有包");

        String readMeFile = "/route/allpackage/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allPackageReadme.btl",readMeFile);

        prop.put("sideType","allPackage");
        String sideBarFile = "/route/allpackage/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);


        //打印包下类明细
        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : packageFileList) {

            List<JavaDocDocsifyThemeDto> packageChildList = javaDocDocsifyThemeDto.getChildList();

            if(CollectionUtil.isNotEmpty(packageChildList)){
                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("title", javaDocDocsifyThemeDto.getPackageName());
                childProp.put("classType","allPackage");

                String packageRouteReadmeFile = "/route/allpackage/" + javaDocDocsifyThemeDto.getPackageNamePath()+"/README.md";
                writeThemeTemplateFile(renderFilePath, childProp,"allClassReadme.btl",packageRouteReadmeFile);
            }

        }
    }

    private List<JavaDocDocsifyThemeDto> createPackageAndSubFile(List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
        Map<String, List<JavaDocDocsifyThemeDto>> groupPackageClassMap = javaDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(JavaDocDocsifyThemeDto::getPackageName));
        List<JavaDocDocsifyThemeDto> packageFileList = new ArrayList<>();
        groupPackageClassMap.forEach((packageName,packageClassList) ->{
            JavaDocDocsifyThemeDto packageDocsify = new JavaDocDocsifyThemeDto();
            packageDocsify.setPackageName(packageName);
            packageDocsify.setChildList(packageClassList);
            if(CollectionUtil.isNotEmpty(packageClassList)){
                packageDocsify.setPackageDesc(packageClassList.get(0).getPackageDesc());
            }
            packageDocsify.setPackageNamePath(packageName.replace(".","/"));
            packageFileList.add(packageDocsify);
        });
        CollectionUtil.sortByProperty(packageFileList,"packageName");
        return packageFileList;
    }

    /**
     * 创建所有module路由
     * @param renderFilePath
     * @param javaDocDocsifyThemeDtos
     */
    private void createAllModuleRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {

        List<JavaDocDocsifyThemeDto> moduleFileList = createModuleAndSubFile(javaDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allmodule/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allModuleReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allModule");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allmodule/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : moduleFileList) {

            List<JavaDocDocsifyThemeDto> packageChildList = javaDocDocsifyThemeDto.getChildList();

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allModule");
                childProp.put("title", javaDocDocsifyThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allmodule/"+ javaDocDocsifyThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (JavaDocDocsifyThemeDto packageDto : packageChildList) {

                    List<JavaDocDocsifyThemeDto> classDtoList = packageDto.getChildList();

                    if(CollectionUtil.isNotEmpty(classDtoList)){
                        String moduleName = packageDto.getModuleName();
                        Map<String,Object> moduleClassProp = new HashMap<>();
                        moduleClassProp.put("tableList",classDtoList);
                        moduleClassProp.put("classType","packageDetail");
                        moduleClassProp.put("title",packageDto.getPackageName());
                        String packageReadMeFile = "/" +moduleName + "/" + packageDto.getPackageNamePath()+"/README.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"allClassReadme.btl",packageReadMeFile);

                        moduleClassProp.put("sideType","packageDetail");
                        String packageSideBarFile = "/" +moduleName+"/"+ packageDto.getPackageNamePath()+"/_sidebar.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"sidebar.btl",packageSideBarFile);
                    }
                }
            }

        }

    }

    private List<JavaDocDocsifyThemeDto> createModuleAndSubFile(List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
        Map<String, List<JavaDocDocsifyThemeDto>> groupResultMap = javaDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(JavaDocDocsifyThemeDto::getModuleName));
        //按照module->package->class组织
        List<JavaDocDocsifyThemeDto> moduleFileList = new ArrayList<>();
        groupResultMap.forEach((moduleName,moduleClassList) ->{
            JavaDocDocsifyThemeDto moduleDocsify = new JavaDocDocsifyThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<JavaDocDocsifyThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(JavaDocDocsifyThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                JavaDocDocsifyThemeDto packageDocsify = new JavaDocDocsifyThemeDto();
                packageDocsify.setModuleName(moduleName);
                if(CollectionUtil.isNotEmpty(packageClassList)){
                    packageDocsify.setPackageDesc(packageClassList.get(0).getPackageDesc());
                }
                packageDocsify.setPackageName(packageName);
                packageDocsify.setChildList(packageClassList);
                packageDocsify.setPackageNamePath(packageName.replace(".","/"));
                moduleDocsify.addChild(packageDocsify);
            });
            moduleFileList.add(moduleDocsify);
        });
        return moduleFileList;
    }

    private  void copyStaticResource(String renderFilePath,String projectRootPath) {
        String sideBarFile = renderFilePath + "/_sidebar.md";
        String projectReadMeFile = projectRootPath + "/README.md";
        boolean readMeExist = FileUtil.exist(projectReadMeFile);

        if(readMeExist){
            FileUtil.copyFile(projectReadMeFile, renderFilePath +"/README.md", StandardCopyOption.REPLACE_EXISTING);
            StringBuilder sideBarContent = new StringBuilder();
            sideBarContent.append("- **目录**\n");
            sideBarContent.append("\t- [README](README.md)\n");
            sideBarContent.append("\t- [Project Info](projectMetaInfo.md)\n");
            FileUtil.writeString(sideBarContent.toString(),sideBarFile,CharsetUtil.CHARSET_UTF_8);
        }else {
            FileUtil.copyFile(renderFilePath +"/projectMetaInfo.md", renderFilePath +"/README.md", StandardCopyOption.REPLACE_EXISTING);
        }

        ClassPathResource staticResource = new ClassPathResource("com/ejdoc/doc/generate/config/template/markdown/theme/docsify/static.zip");
        FileUtil.copyFile(staticResource.getFile().getAbsolutePath(), renderFilePath +"/static.zip", StandardCopyOption.REPLACE_EXISTING);
        ZipUtil.unzip(renderFilePath +"/static.zip", renderFilePath);
        FileUtil.del(renderFilePath +"/static.zip");
    }

    public String replacePackageDesc(String desc){
        if(StrUtil.isNotBlank(desc)){
            return desc.replace("\n","");
        }
        return desc;
    }

    private void createFile(String renderFilePath,String fileName) {
        FileUtil.touch(renderFilePath+"/" + fileName);
    }
}
