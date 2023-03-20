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

public class DocsifyTemplateTheme extends BaseOutTemplate implements DocTemplateTheme {
    /***/
    private final Logger log = LoggerFactory.getLogger(JavaDocMarkdownDocOutTemplate.class);

//    private Map<String,String> packageDecsMap = new ConcurrentHashMap<>();

    public DocsifyTemplateTheme() {
        super();
    }
    public DocsifyTemplateTheme (DocGenerateConfig docGenerateConfig) {
       this(null,docGenerateConfig);
    }
    public DocsifyTemplateTheme(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
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
        writeNojekyllFile(renderFilePath);
        Map<String,Object> propMap = new HashMap<>();
        List<DocsifyThemeDto> docsifyThemeDtos = readyBaseData(docTemplateThemeInfo);

        createRouteInfo(docTemplateThemeInfo,docsifyThemeDtos);

        JSONObject jsonObject = JSONUtil.readJSONObject(new File(jsonFilePath+"/projectMetaInfo.json"), CharsetUtil.CHARSET_UTF_8);
        if(jsonObject != null && jsonObject.size()> 0){
            propMap.putAll(jsonObject);
        }

        Template indexTemplate =getGroupTemplate().getTemplate( "/markdown/theme/docsify/index.html");
        String indexHtml = renderByTemplate(propMap, indexTemplate);

        FileUtil.writeString(indexHtml,renderFilePath+"/index.html", CharsetUtil.CHARSET_UTF_8);

        String sideBarFile =renderFilePath+"/_sidebar.md";
        FileUtil.touch(sideBarFile);
        copyStaticResource(renderFilePath);
    }

    /**
     * 准备基础数据
     * @param docTemplateThemeInfo
     * @return
     */
    private List<DocsifyThemeDto> readyBaseData(DocTemplateThemeInfo docTemplateThemeInfo) {
        List<File> jsonFiles = FileUtil.loopFiles(docTemplateThemeInfo.getJsonFilePath(), subFile -> FileTypeUtil.getType(subFile).equals("json"));
        List<DocsifyThemeDto> docsifyThemeDtos = new ArrayList<>();
        Map<String,String> packageDecsMap = new HashMap<>();

        for (File jsonFile : jsonFiles) {
            DocsifyThemeDto docsifyThemeDto = new DocsifyThemeDto();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            String className = jsonObject.getStr("className");
            String moduleName = jsonObject.getStr("moduleName");
            String moduleDesc = jsonObject.getStr("moduleDesc");
            if(StrUtil.equals(className,"package-info")){
                JSONObject javaModelMeta = jsonObject.getJSONObject("javaModelMeta");
                String packageName = jsonObject.getStr("packageName");
                if(javaModelMeta != null){
                    String comment = javaModelMeta.getStr("comment", "");
                    if(StrUtil.isNotBlank(comment)){
                        packageDecsMap.put(packageName,replacePackageDesc(comment));
                    }
                }
            }else if(StrUtil.isNotBlank(moduleName)){
                docsifyThemeDto.setProjectName(jsonObject.getStr("projectName"));
                docsifyThemeDto.setModuleName(moduleName);
                docsifyThemeDto.setModuleDesc(moduleDesc);
                docsifyThemeDto.setClassName(jsonObject.getStr("className"));
                docsifyThemeDto.setPackageName(jsonObject.getStr("packageName"));
                docsifyThemeDtos.add(docsifyThemeDto);
            }
        }

        for (DocsifyThemeDto docsifyThemeDto : docsifyThemeDtos) {
            String packageName = docsifyThemeDto.getPackageName();
            String packageDesc = packageDecsMap.get(packageName);
            docsifyThemeDto.setPackageDesc(packageDesc);
        }
        return docsifyThemeDtos;
    }

    /**
     * 创建路由信息 所有模块  所有包  所有类
     * @param docTemplateThemeInfo
     * @param docsifyThemeDtos
     */
    private void createRouteInfo(DocTemplateThemeInfo docTemplateThemeInfo, List<DocsifyThemeDto> docsifyThemeDtos) {
        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();

        createAllClassRoute(renderFilePath, docsifyThemeDtos);

        createAllPackageRoute(renderFilePath, docsifyThemeDtos);

        createAllModuleRoute(renderFilePath, docsifyThemeDtos);

    }

    /**
     * 创建所有类路由
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassRoute(String renderFilePath, List<DocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",allClassList);
        prop.put("title","- **所有类目录**");
        prop.put("sideType","allClass");
        prop.put("classType","allClass");

        Template sideBarTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/sidebar.btl");
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);

        Template readMeTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allClassReadme.btl");
        String allClassReadme = renderByTemplate(prop, readMeTemplate);

        String sideBarFile = renderFilePath + "/route/allclass/_sidebar.md";
        String readmeFile = renderFilePath + "/route/allclass/README.md";

        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
        FileUtil.writeString(allClassReadme, readmeFile,CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 创建所有包路由
     * @param renderFilePath
     * @param docsifyThemeDtos
     */
    private void createAllPackageRoute(String renderFilePath, List<DocsifyThemeDto> docsifyThemeDtos) {

        List<DocsifyThemeDto> packageFileList = createPackageAndSubFile(docsifyThemeDtos);

        //打印所有包信息
        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",packageFileList);
        prop.put("packageType","allPackage");

        Template readMeTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allPackageReadme.btl");
        String allPackageReadme = renderByTemplate(prop, readMeTemplate);

        prop.put("sideType","allPackage");
        prop.put("title","- **所有包目录**");
        Template sideBarTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/sidebar.btl");
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);

        String sideBarFile = renderFilePath + "/route/allpackage/_sidebar.md";
        String allModuleReadmeFile = renderFilePath + "/route/allpackage/README.md";

        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
        FileUtil.writeString(allPackageReadme, allModuleReadmeFile,CharsetUtil.CHARSET_UTF_8);

        //打印包下类明细
        for (DocsifyThemeDto docsifyThemeDto : packageFileList) {
            String packageName = docsifyThemeDto.getPackageName();
            String packageNamePath = docsifyThemeDto.getPackageNamePath();

            List<DocsifyThemeDto> packageChildList = docsifyThemeDto.getChildList();
            String packageRouteReadmeFile = renderFilePath+"/route/allpackage/" + packageNamePath+"/README.md";
            if(CollectionUtil.isNotEmpty(packageChildList)){
                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("title","**["+packageName+"]包下类信息**");
                childProp.put("classType","allPackage");
                Template childReadMeTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allClassReadme.btl");
                String packageReadme = renderByTemplate(childProp, childReadMeTemplate);
                FileUtil.writeString(packageReadme, packageRouteReadmeFile,CharsetUtil.CHARSET_UTF_8);
            }

        }
    }

    private List<DocsifyThemeDto> createPackageAndSubFile(List<DocsifyThemeDto> docsifyThemeDtos) {
        Map<String, List<DocsifyThemeDto>> groupPackageClassMap = docsifyThemeDtos.stream().collect(Collectors.groupingBy(DocsifyThemeDto::getPackageName));
        List<DocsifyThemeDto> packageFileList = new ArrayList<>();
        groupPackageClassMap.forEach((packageName,packageClassList) ->{
            DocsifyThemeDto packageDocsify = new DocsifyThemeDto();
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
     * @param docsifyThemeDtos
     */
    private void createAllModuleRoute(String renderFilePath, List<DocsifyThemeDto> docsifyThemeDtos) {

        List<DocsifyThemeDto> moduleFileList = createModuleAndSubFile(docsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);

        Template readMeTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allModuleReadme.btl");
        String allPackageReadme = renderByTemplate(prop, readMeTemplate);

        prop.put("sideType","allModule");
        prop.put("title","- **所有模块目录**");
        Template sideBarTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/sidebar.btl");
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);

        String sideBarFile = renderFilePath + "/route/allmodule/_sidebar.md";
        String allModuleReadmeFile = renderFilePath + "/route/allmodule/README.md";

        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
        FileUtil.writeString(allPackageReadme, allModuleReadmeFile,CharsetUtil.CHARSET_UTF_8);


        for (DocsifyThemeDto docsifyThemeDto : moduleFileList) {

            List<DocsifyThemeDto> packageChildList = docsifyThemeDto.getChildList();

            if(CollectionUtil.isNotEmpty(packageChildList)){
                String moduleRouteInfoFile = renderFilePath + "/route/allmodule/"+docsifyThemeDto.getModuleName()+".md";
                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allModule");
                childProp.put("title",docsifyThemeDto.getModuleName()+"下所有包信息");
                Template childTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allPackageReadme.btl");
                String childContent = renderByTemplate(childProp, childTemplate);
                FileUtil.writeString(childContent, moduleRouteInfoFile,CharsetUtil.CHARSET_UTF_8);

                for (DocsifyThemeDto packageDto : packageChildList) {

                    List<DocsifyThemeDto> classDtoList = packageDto.getChildList();

                    if(CollectionUtil.isNotEmpty(classDtoList)){
                        String moduleName = packageDto.getModuleName();
                        Map<String,Object> moduleClassProp = new HashMap<>();
                        moduleClassProp.put("tableList",classDtoList);
                        moduleClassProp.put("classType","packageDetail");
                        moduleClassProp.put("title","**["+packageDto.getPackageName()+"]包下信息**");
                        Template moduleClassTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/allClassReadme.btl");
                        String moduleClassStr = renderByTemplate(moduleClassProp, moduleClassTemplate);

                        moduleClassProp.put("sideType","packageDetail");
                        moduleClassProp.put("title","- **"+packageDto.getPackageName()+"包目录**");
                        Template moduleClassSideBarTemplate = getGroupTemplate().getTemplate("/markdown/theme/docsify/sidebar.btl");
                        String  moduleClassSideBarStr= renderByTemplate(moduleClassProp, moduleClassSideBarTemplate);

                        String packageReadMeFile = renderFilePath + "/" +moduleName + "/" + packageDto.getPackageNamePath()+"/README.md";
                        String packageSideBarFile = renderFilePath + "/" +moduleName+"/"+ packageDto.getPackageNamePath()+"/_sidebar.md";

                        FileUtil.writeString(moduleClassStr, packageReadMeFile,CharsetUtil.CHARSET_UTF_8);
                        FileUtil.writeString(moduleClassSideBarStr, packageSideBarFile,CharsetUtil.CHARSET_UTF_8);
                    }
                }
            }

        }

    }

    private List<DocsifyThemeDto> createModuleAndSubFile(List<DocsifyThemeDto> docsifyThemeDtos) {
        Map<String, List<DocsifyThemeDto>> groupResultMap = docsifyThemeDtos.stream().collect(Collectors.groupingBy(DocsifyThemeDto::getModuleName));
        //按照module->package->class组织
        List<DocsifyThemeDto> moduleFileList = new ArrayList<>();
        groupResultMap.forEach((moduleName,moduleClassList) ->{
            DocsifyThemeDto moduleDocsify = new DocsifyThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<DocsifyThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(DocsifyThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                DocsifyThemeDto packageDocsify = new DocsifyThemeDto();
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

    private  void copyStaticResource(String renderFilePath) {
        FileUtil.copyFile(renderFilePath +"/projectMetaInfo.md", renderFilePath +"/README.md", StandardCopyOption.REPLACE_EXISTING);
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

    private void writeNojekyllFile(String renderFilePath) {
        FileUtil.touch(renderFilePath+"/.nojekyll");
    }
}
