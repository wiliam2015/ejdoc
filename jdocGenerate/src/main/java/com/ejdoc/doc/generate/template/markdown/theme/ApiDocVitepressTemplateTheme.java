package com.ejdoc.doc.generate.template.markdown.theme;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.*;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.enums.TemplateThemeEnum;
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.javadoc.dto.JavaDocDeprecatedDto;
import com.ejdoc.doc.generate.template.BaseOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.theme.sidebar.VitepressSidebar;
import com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocTagTypeEnum;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.util.EjdocStrUtil;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class ApiDocVitepressTemplateTheme extends BaseOutTemplate implements DocTemplateTheme {
    /***/
    private final Logger log = LoggerFactory.getLogger(ApiDocVitepressTemplateTheme.class);

    private DocClassRenderUtil docClassFn = new DocClassRenderUtil();

    public ApiDocVitepressTemplateTheme() {
        super();
    }
    public ApiDocVitepressTemplateTheme(DocGenerateConfig docGenerateConfig) {
        this(null,docGenerateConfig);
    }
    public ApiDocVitepressTemplateTheme(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
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
        String renderFileRootPath = docTemplateThemeInfo.getRenderFileRootPath();

        Map<String,Object> propMap = new HashMap<>();
        List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos = readyBaseData(docTemplateThemeInfo);

        readyRenderProp(jsonFilePath,renderFileRootPath,docTemplateThemeInfo, propMap);

        //当前版本文件渲染
//        renderTemplateThemeFile(renderFilePath,apiDocDocsifyThemeDtos,projectRootPath,propMap);
        //最新版本文件渲染
        renderTemplateThemeFile(renderFileRootPath,apiDocDocsifyThemeDtos,projectRootPath,propMap);
    }

    private void renderTemplateThemeFile(String renderFilePath,List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos, String projectRootPath, Map<String,Object> propMap) {

        String vitepressConfigDir = renderFilePath;
        renderFilePath = renderFilePath+"/"+ TemplateThemeEnum.Vitepress.getSrcDir();
        Map<String,List<VitepressSidebar>> sideBarMap = new HashMap<>();
        StringBuilder sidbarContentBuilder = new StringBuilder();
        createRouteInfo(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        //渲染主文件index.html
        writeThemeTemplateFile(renderFilePath, propMap,"index.md","/index.md");
        writeThemeTemplateFile(vitepressConfigDir, propMap,"nodePackageJson.btl","/package.json");
        if(StrUtil.isNotBlank(sidbarContentBuilder)){
            propMap.put("sidbarContent",sidbarContentBuilder.substring(1));
        }

        writeThemeTemplateFile(vitepressConfigDir+"/.vitepress", propMap,"config.btl","/config.mjs");


//        FileUtil.copyFile(renderFilePath +"/nodePackageJson.btl", renderFilePath +"/package.json", StandardCopyOption.REPLACE_EXISTING);

//        createFile(renderFilePath,".nojekyll");
//        createFile(renderFilePath,"_sidebar.md");
//        copyStaticResource(renderFilePath,projectRootPath);
    }

    /**
     * 模板渲染动态数据组装
     * @param jsonFilePath
     * @param propMap
     */
    private void readyRenderProp(String jsonFilePath,String renderFileRootPath,DocTemplateThemeInfo docTemplateThemeInfo, Map<String, Object> propMap) {
        JSONObject jsonObject = JSONUtil.readJSONObject(new File(jsonFilePath +"/projectMetaInfo.json"), CharsetUtil.CHARSET_UTF_8);
        if(jsonObject != null && jsonObject.size()> 0){
            propMap.putAll(jsonObject);
        }
        Map<String, String> templateCustomProp = docTemplateThemeInfo.getTemplateCustomProp();
        if(CollectionUtil.isNotEmpty(templateCustomProp)){
            propMap.putAll(templateCustomProp);
        }

//        List<String> versions = Arrays.stream(FileUtil.ls(renderFileRootPath + "/"+TemplateThemeEnum.Vitepress.getSrcDir()+"/v")).filter(File::isDirectory).map(File::getName).collect(Collectors.toList());
//        if(CollectionUtil.isEmpty(versions)){
//            versions = new ArrayList<>();
//        }
//        if(!versions.contains(docTemplateThemeInfo.getVersion())){
//            versions.add(docTemplateThemeInfo.getVersion());
//        }
//        propMap.put("versions",versions);

    }

    /**
     * 准备基础数据
     * @param docTemplateThemeInfo
     * @return
     */
    private List<ApiDocVitepressThemeDto> readyBaseData(DocTemplateThemeInfo docTemplateThemeInfo) {
        List<File> jsonFiles = FileUtil.loopFiles(docTemplateThemeInfo.getJsonFilePath(), subFile -> FileTypeUtil.getType(subFile).equals("json"));
        List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos = new ArrayList<>();
        Map<String,String> packageDecsMap = new HashMap<>();
        Map<String,String> packageAuthorsMap = new HashMap<>();

        for (File jsonFile : jsonFiles) {
            ApiDocVitepressThemeDto apiDocVitepressThemeDto = new ApiDocVitepressThemeDto();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            String className = jsonObject.getStr("className");
            Boolean interfaceClass = jsonObject.getBool("interfaceClass",false);
            String fullClassName = jsonObject.getStr("fullClassName");
            String moduleName = jsonObject.getStr("moduleName");
            String moduleDesc = jsonObject.getStr("moduleDesc");
            String packageName = jsonObject.getStr("packageName");
            JSONObject javaDocDeprecatedInfo = jsonObject.getJSONObject("javaDocDeprecatedInfo");
            apiDocVitepressThemeDto.setInterfaceClass(interfaceClass);
            if(StrUtil.equals(className,"package-info")){
                String packageComment = docClassFn.calCommentDocMd(jsonObject, jsonObject, "", null);
                if(StrUtil.isNotBlank(packageComment) && StrUtil.isNotBlank(packageName)){
                    packageDecsMap.put(packageName, packageComment);
                }
                String packageAuthor = docClassFn.calCommentTagsMd(jsonObject, jsonObject, JavaDocTagTypeEnum.AUTHOR.getName(), "", null);
                if(StrUtil.isNotBlank(packageAuthor) && StrUtil.isNotBlank(packageName)){
                    packageAuthorsMap.put(packageName, packageAuthor);
                }

            }else if(StrUtil.isNotBlank(moduleName) && StrUtil.isNotBlank(packageName)){
                apiDocVitepressThemeDto.setProjectName(jsonObject.getStr("projectName"));
                apiDocVitepressThemeDto.setModuleName(moduleName);
                apiDocVitepressThemeDto.setModuleDesc(moduleDesc);
                apiDocVitepressThemeDto.setClassName(className);
                apiDocVitepressThemeDto.setClassSimpleName(docClassFn.calSimpleClassNameStructure(jsonObject,null));
                String classDescHtml = docClassFn.calCommentNoEnterDocMd(jsonObject, jsonObject, "", null);
                apiDocVitepressThemeDto.setClassDesc(classDescHtml);
//                apiDocVitepressThemeDto.setClassDesc(classDesc);
                apiDocVitepressThemeDto.setHead(className.substring(0,1).toUpperCase());
                apiDocVitepressThemeDto.setFullClassName(fullClassName);
                apiDocVitepressThemeDto.setPackageName(packageName);
                if(javaDocDeprecatedInfo != null){
                    Boolean deprecatedMethod = javaDocDeprecatedInfo.getBool("deprecatedMethod", false);
                    Boolean deprecatedContructor = javaDocDeprecatedInfo.getBool("deprecatedContructor", false);
                    Boolean deprecatedClass = javaDocDeprecatedInfo.getBool("deprecatedClass", false);
                    if(deprecatedMethod || deprecatedContructor || deprecatedClass){
                        apiDocVitepressThemeDto.setDeprecatedModify(true);
                        JavaDocDeprecatedDto javaDocDeprecatedDto = JSONUtil.toBean(javaDocDeprecatedInfo, JavaDocDeprecatedDto.class);
                        apiDocVitepressThemeDto.setDocDeprecatedDto(javaDocDeprecatedDto);
                    }
                }

                apiDocDocsifyThemeDtos.add(apiDocVitepressThemeDto);
            }

        }

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : apiDocDocsifyThemeDtos) {
            String packageName = apiDocVitepressThemeDto.getPackageName();
            String packageDesc = packageDecsMap.get(packageName);
            String authorDesc = packageAuthorsMap.get(packageName);
            apiDocVitepressThemeDto.setPackageDesc(packageDesc);
            apiDocVitepressThemeDto.setAuthor(authorDesc);
        }
        return apiDocDocsifyThemeDtos;
    }

    /**
     * 创建路由信息 所有模块  所有包  所有类 所有树级类索引
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createRouteInfo(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos, Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {
//        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();

        createAllInterfaceRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllClassRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllPackageRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllModuleRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllClassTreeIndexRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllDeprecatedClassIndexRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

        createAllSerialIndexRoute(renderFilePath, apiDocDocsifyThemeDtos,sideBarMap,sidbarContentBuilder);

    }

    /**
     * 创建所有序列化类显示页面
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllSerialIndexRoute(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {


        List<ApiDocVitepressThemeDto> moduleFileList = createAllSerialFile(apiDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allserial/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allSerialReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allSerial");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allserial/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : moduleFileList) {

            List<ApiDocVitepressThemeDto> packageChildList = apiDocVitepressThemeDto.getChildList();

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allSerial");
                childProp.put("title", apiDocVitepressThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allserial/"+ apiDocVitepressThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (ApiDocVitepressThemeDto packageDto : packageChildList) {

                    List<ApiDocVitepressThemeDto> classDtoList = packageDto.getChildList();

                    List<ApiDocVitepressThemeDto> indexClassList = getIndexClassList(classDtoList);

                    if(CollectionUtil.isNotEmpty(classDtoList)){
                        String moduleName = packageDto.getModuleName();
                        Map<String,Object> moduleClassProp = new HashMap<>();
                        moduleClassProp.put("tableList",indexClassList);
                        moduleClassProp.put("classType","packageDetail");
                        moduleClassProp.put("title",packageDto.getPackageName());
                        String packageReadMeFile = "/" +moduleName + "/" + packageDto.getPackageNamePath()+"/README.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"allClassReadme.btl",packageReadMeFile);

                        moduleClassProp.put("sideType","packageDetail");
                        String packageSideBarFile = "/" +moduleName+"/"+ packageDto.getPackageNamePath()+"/_sidebar.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"sidebar.btl",packageSideBarFile);

                        prop.put("sidePath","/" +moduleName+"/"+ packageDto.getPackageNamePath());
                        writeSidebarContent(renderFilePath, prop,"sidebarJson.btl",sidbarContentBuilder);
                    }
                }
            }

        }
    }



    /**
     * 创建所有Deprecated类信息索引
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllDeprecatedClassIndexRoute(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {
        List<JavaDocDeprecatedDto> allDeprecatedClasses = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedMethods = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedContructors = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(apiDocDocsifyThemeDtos)){
            List<JavaDocDeprecatedDto> docDeprecatedDtos = apiDocDocsifyThemeDtos.stream().filter(ApiDocVitepressThemeDto::isDeprecatedModify)
                    .map(ApiDocVitepressThemeDto::getDocDeprecatedDto)
                    .collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(docDeprecatedDtos)){

                for (JavaDocDeprecatedDto docDeprecatedDto : docDeprecatedDtos) {
                    if(BooleanUtil.isTrue(docDeprecatedDto.getDeprecatedClass())){
                        JavaDocDeprecatedDto deprecatedClasses = docDeprecatedDto.getDeprecatedClasses();
                        deprecatedClasses.setProjectName(docDeprecatedDto.getProjectName());
                        deprecatedClasses.setModuleName(docDeprecatedDto.getModuleName());
                        deprecatedClasses.setPackageName(docDeprecatedDto.getPackageName());
                        deprecatedClasses.setClassName(docDeprecatedDto.getClassName());
                        deprecatedClasses.setFullClassName(docDeprecatedDto.getFullClassName());
                        allDeprecatedClasses.add(deprecatedClasses);
                    }
                    if(BooleanUtil.isTrue(docDeprecatedDto.getDeprecatedMethod())){
                        for (JavaDocDeprecatedDto deprecatedMethod : docDeprecatedDto.getDeprecatedMethods()) {
                            deprecatedMethod.setProjectName(docDeprecatedDto.getProjectName());
                            deprecatedMethod.setModuleName(docDeprecatedDto.getModuleName());
                            deprecatedMethod.setPackageName(docDeprecatedDto.getPackageName());
                            deprecatedMethod.setClassName(docDeprecatedDto.getClassName());
                            deprecatedMethod.setFullClassName(docDeprecatedDto.getFullClassName());
                        }
                        allDeprecatedMethods.addAll(docDeprecatedDto.getDeprecatedMethods());
                    }
                    if(BooleanUtil.isTrue(docDeprecatedDto.getDeprecatedContructor())){
                        for (JavaDocDeprecatedDto deprecatedMethod : docDeprecatedDto.getDeprecatedConstructors()) {
                            deprecatedMethod.setProjectName(docDeprecatedDto.getProjectName());
                            deprecatedMethod.setModuleName(docDeprecatedDto.getModuleName());
                            deprecatedMethod.setPackageName(docDeprecatedDto.getPackageName());
                            deprecatedMethod.setClassName(docDeprecatedDto.getClassName());
                            deprecatedMethod.setFullClassName(docDeprecatedDto.getFullClassName());
                        }
                        allDeprecatedContructors.addAll(docDeprecatedDto.getDeprecatedConstructors());
                    }
                }
            }
        }
        Map<String,Object> prop = new HashMap<>();
        prop.put("haveDeprecatedData",true);
        if(CollectionUtil.isEmpty(allDeprecatedClasses)
                && CollectionUtil.isEmpty(allDeprecatedMethods)
                && CollectionUtil.isEmpty(allDeprecatedContructors)){
            prop.put("haveDeprecatedData",false);
        }
        prop.put("allDeprecatedClasses",JSONUtil.parseArray(allDeprecatedClasses));
        prop.put("allDeprecatedMethods",JSONUtil.parseArray(allDeprecatedMethods));
        prop.put("allDeprecatedContructors",JSONUtil.parseArray(allDeprecatedContructors));
        String allModuleReadmeFile = "/route/alldeprecated/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allDeprecatedReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allDeprecated");
        prop.put("title","所有Deprecated");
        String sideBarFile = "/route/alldeprecated/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        prop.put("sidePath","/route/alldeprecated/");
        writeSidebarContent(renderFilePath, prop,"sidebarJson.btl",sidbarContentBuilder);
    }

    /**
     * 创建所有类的层次结构
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassTreeIndexRoute(String renderFilePath, List<ApiDocVitepressThemeDto> allClassList,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {
        CollectionUtil.sortByProperty(allClassList,"fullClassName");

        List<ApiDocVitepressThemeDto> allClassHierarchyList = new ArrayList<>();
        ApiDocVitepressThemeDto javaLangObject = createJavaLangObject();
        allClassHierarchyList.add(javaLangObject);

        List<ApiDocVitepressThemeDto> interfaceClassHierarchyList = new ArrayList<>();
        ApiDocVitepressThemeDto interfaceJavaLangObject = createJavaLangObject();
        interfaceClassHierarchyList.add(interfaceJavaLangObject);



        List<ApiDocVitepressThemeDto> enumClassHierarchyList = new ArrayList<>();
        ApiDocVitepressThemeDto enumJavaLangObject = createJavaLangObject();
        ApiDocVitepressThemeDto enumBase = new ApiDocVitepressThemeDto();
        enumBase.setFullClassName("java.lang.Enum");
        enumBase.setClassName("Enum");
        enumBase.setPackageName("java.lang");
        enumBase.setHierarchy(2);
        enumJavaLangObject.addChild(enumBase);
        enumClassHierarchyList.add(enumJavaLangObject);

        List<ApiDocVitepressThemeDto> exceptionClassHierarchyList = new ArrayList<>();
        ApiDocVitepressThemeDto exceptionJavaLangObject = createJavaLangObject();
        ApiDocVitepressThemeDto throwBase = new ApiDocVitepressThemeDto();
        throwBase.setFullClassName("java.lang.Throwable");
        throwBase.setClassName("Throwable");
        throwBase.setPackageName("java.lang");
        throwBase.setHierarchy(2);
        exceptionJavaLangObject.addChild(throwBase);
        exceptionClassHierarchyList.add(exceptionJavaLangObject);

        List<ApiDocVitepressThemeDto> allChildClassHierarchyList = new ArrayList<>();
        List<ApiDocVitepressThemeDto> allInterfaceClassHierarchyList = new ArrayList<>();
        List<ApiDocVitepressThemeDto> allEnumClassHierarchyList = new ArrayList<>();
        List<ApiDocVitepressThemeDto> allExceptionClassHierarchyList = new ArrayList<>();
        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allClassList) {
            String fullClassName = apiDocVitepressThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
            boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
            if(treeIndexClassMeta != null){
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                if(isClass){
                    if(CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                        apiDocVitepressThemeDto.setHierarchy(2);
                        allChildClassHierarchyList.add(apiDocVitepressThemeDto);
                    }else{
                        boolean isExceptionClass = false;
                        for (JavaClassMeta classMeta : treeIndexClassMeta.getSupperClassList()) {
                            String className = classMeta.getClassName();
                            if("Exception".equals(className)){
                                apiDocVitepressThemeDto.setHierarchy(4);
                                isExceptionClass = true;
                            }
                            if("RuntimeException".equals(className)){
                                apiDocVitepressThemeDto.setHierarchy(5);
                                isExceptionClass = true;
                            }
                        }
                        if(isExceptionClass){
                            allExceptionClassHierarchyList.add(apiDocVitepressThemeDto);
                        }

                    }

                }
                if(!isClass && CollectionUtil.isEmpty(treeIndexClassMeta.getInterfaceList())){
                    apiDocVitepressThemeDto.setHierarchy(2);
                    allInterfaceClassHierarchyList.add(apiDocVitepressThemeDto);

                }
                if(isEnum && CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                    apiDocVitepressThemeDto.setHierarchy(3);
                    allEnumClassHierarchyList.add(apiDocVitepressThemeDto);
                }
            }
        }
        javaLangObject.setChildList(allChildClassHierarchyList);
        interfaceJavaLangObject.setChildList(allInterfaceClassHierarchyList);
        enumBase.setChildList(allEnumClassHierarchyList);


        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allChildClassHierarchyList) {
            String fullClassName = apiDocVitepressThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,3,apiDocVitepressThemeDto);
        }

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allInterfaceClassHierarchyList) {
            String fullClassName = apiDocVitepressThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllInterfaceSubClass(treeIndexClass,treeIndexClass,3,apiDocVitepressThemeDto);
        }

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allEnumClassHierarchyList) {
            String fullClassName = apiDocVitepressThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,4,apiDocVitepressThemeDto);
        }

        ApiDocVitepressThemeDto exceptionBase = new ApiDocVitepressThemeDto();
        exceptionBase.setFullClassName("java.lang.Exception");
        exceptionBase.setClassName("Exception");
        exceptionBase.setPackageName("java.lang");
        exceptionBase.setHierarchy(3);
        throwBase.addChild(exceptionBase);


        ApiDocVitepressThemeDto runtimeExceptionBase = new ApiDocVitepressThemeDto();
        runtimeExceptionBase.setFullClassName("java.lang.RuntimeException");
        runtimeExceptionBase.setClassName("RuntimeException");
        runtimeExceptionBase.setPackageName("java.lang");
        runtimeExceptionBase.setHierarchy(4);
        exceptionBase.addChild(runtimeExceptionBase);

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allExceptionClassHierarchyList) {
            String fullClassName = apiDocVitepressThemeDto.getFullClassName();
            int hierarchy = apiDocVitepressThemeDto.getHierarchy();
            if(hierarchy == 4){
                exceptionBase.addChild(apiDocVitepressThemeDto);
            }
            if(hierarchy == 5){
                runtimeExceptionBase.addChild(apiDocVitepressThemeDto);
            }
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,hierarchy+1,apiDocVitepressThemeDto);
        }

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",allClassHierarchyList);
        prop.put("interfaceTableList",interfaceClassHierarchyList);
        prop.put("enumTableList",enumClassHierarchyList);
        prop.put("exceptionTableList",exceptionClassHierarchyList);
        prop.put("title","所有类层次结构");
        prop.put("sideType","allClasstree");
        prop.put("classType","allClasstree");

        String readMeFile = "/route/allclasstree/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allClassTreeReadme.btl",readMeFile);

        String sideBarFile = "/route/allclasstree/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        prop.put("sidePath","/route/allclasstree/");
        writeSidebarContent(renderFilePath, prop,"sidebarJson.btl",sidbarContentBuilder);
    }

    private ApiDocVitepressThemeDto createJavaLangObject() {
        ApiDocVitepressThemeDto javaLangObject = new ApiDocVitepressThemeDto();
        javaLangObject.setFullClassName("java.lang.Object");
        javaLangObject.setClassName("Object");
        javaLangObject.setPackageName("java.lang");
        javaLangObject.setHierarchy(1);
        return javaLangObject;
    }

    private void addAllSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,ApiDocVitepressThemeDto apiDocVitepressThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childClassList = treeIndexClass.getChildClassList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        apiDocVitepressThemeDto.addInterface(createApiDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childClassList)){
                for (JavaClassMeta classMeta : childClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    ApiDocVitepressThemeDto apiDocDocsifyThemeDtoFromMeta = createApiDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    apiDocVitepressThemeDto.addChild(apiDocDocsifyThemeDtoFromMeta);


                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,apiDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private void addAllInterfaceSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,ApiDocVitepressThemeDto apiDocVitepressThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childInterfaceList = treeIndexClass.getChildInterfaceList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        apiDocVitepressThemeDto.addInterface(createApiDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childInterfaceList)){
                for (JavaClassMeta classMeta : childInterfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    ApiDocVitepressThemeDto apiDocDocsifyThemeDtoFromMeta = createApiDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    apiDocVitepressThemeDto.addChild(apiDocDocsifyThemeDtoFromMeta);

                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllInterfaceSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,apiDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private ApiDocVitepressThemeDto createApiDocDocsifyThemeDtoFromMeta(JavaClassMeta classMeta,int hierarchy) {
        ApiDocVitepressThemeDto apiDocVitepressThemeDto = new ApiDocVitepressThemeDto();
        apiDocVitepressThemeDto.setProjectName(classMeta.getProjectName());
        apiDocVitepressThemeDto.setModuleName(classMeta.getModuleName());
        apiDocVitepressThemeDto.setModuleDesc(classMeta.getModuleDesc());
        apiDocVitepressThemeDto.setClassName(classMeta.getClassName());
        apiDocVitepressThemeDto.setFullClassName(classMeta.getFullClassName());
        apiDocVitepressThemeDto.setHierarchy(hierarchy);
        apiDocVitepressThemeDto.setPackageName(classMeta.getPackageName());
        return apiDocVitepressThemeDto;
    }

    /**
     * 创建所有接口路由
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllInterfaceRoute(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {

        List<ApiDocVitepressThemeDto> allClassInfoList = getIndexInterfaceList(apiDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",allClassInfoList);
        prop.put("title","所有接口");
        prop.put("sideType","allInterface");
        prop.put("classType","allInterface");

        String readMeFile = "/route/allinterface/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allInterfaceReadme.btl",readMeFile);

        String sideBarFile = "/route/allinterface/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);
    }

    /**
     * 创建所有类路由
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassRoute(String renderFilePath, List<ApiDocVitepressThemeDto> allClassList,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {
        List<ApiDocVitepressThemeDto> allClassInfoList = getIndexClassList(allClassList);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",allClassInfoList);
        prop.put("title","所有类");
        prop.put("sideType","allClass");
        prop.put("classType","allClass");

        String readMeFile = "/route/allclass/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allClassReadme.btl",readMeFile);

        String sideBarFile = "/route/allclass/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);
    }

    /**
     * 获取有索引信息的类列表
     * @param allClassList 所有类信息
     * @return
     */
    private  List<ApiDocVitepressThemeDto> getIndexInterfaceList(List<ApiDocVitepressThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        List<ApiDocVitepressThemeDto> allClassInfoList = new ArrayList<>();
        String firstHeadStr = "";
        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allClassList) {
            if(!apiDocVitepressThemeDto.isInterfaceClass()){
                continue;
            }
            String head = apiDocVitepressThemeDto.getHead();
            if(!firstHeadStr.equals(head)){
                firstHeadStr = head;
                ApiDocVitepressThemeDto headTitle = new ApiDocVitepressThemeDto();
                headTitle.setFirstHead(true);
                headTitle.setHead(apiDocVitepressThemeDto.getHead());
                headTitle.setClassName(apiDocVitepressThemeDto.getClassName());
                allClassInfoList.add(headTitle);
            }
            allClassInfoList.add(apiDocVitepressThemeDto);
        }
        return allClassInfoList;
    }

    /**
     * 获取有索引信息的类列表
     * @param allClassList 所有类信息
     * @return
     */
    private  List<ApiDocVitepressThemeDto> getIndexClassList(List<ApiDocVitepressThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        List<ApiDocVitepressThemeDto> allClassInfoList = new ArrayList<>();
        String firstHeadStr = "";
        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : allClassList) {
            String head = apiDocVitepressThemeDto.getHead();
            if(!firstHeadStr.equals(head)){
                firstHeadStr = head;
                ApiDocVitepressThemeDto headTitle = new ApiDocVitepressThemeDto();
                headTitle.setFirstHead(true);
                headTitle.setHead(apiDocVitepressThemeDto.getHead());
                headTitle.setClassName(apiDocVitepressThemeDto.getClassName());
                allClassInfoList.add(headTitle);
            }
            allClassInfoList.add(apiDocVitepressThemeDto);
        }
        return allClassInfoList;
    }

    /**
     * 根据模板文件写入对应的主题信息
     * @param renderFilePath
     * @param prop
     * @param templateName 模板名称
     * @param renderFile 写入文件
     */
    private void writeThemeTemplateFile(String renderFilePath, Map<String, Object> prop,String templateName,String renderFile) {
        String docType = getDocGenerateConfig().getDocTypeEnum().getCode();
        String templateFile = "/markdown/"+ docType +"/theme/vitepress/" + templateName;
        Template sideBarTemplate = getGroupTemplate().getTemplate(templateFile);
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);
        String sideBarFile = renderFilePath + renderFile;
        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 根据模板文件写入对应的主题信息
     * @param renderFilePath
     * @param prop
     * @param templateName 模板名称
     * @param sideBarContentBuilder sidebar构建器
     */
    private void writeSidebarContent(String renderFilePath, Map<String, Object> prop,String templateName,StringBuilder sideBarContentBuilder) {
        String docType = getDocGenerateConfig().getDocTypeEnum().getCode();
        String templateFile = "/markdown/"+ docType +"/theme/vitepress/" + templateName;
        Template sideBarTemplate = getGroupTemplate().getTemplate(templateFile);
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);
        if(StrUtil.isNotBlank(sideBarContent)){
            sideBarContentBuilder.append(",").append(sideBarContent);
        }
//        String sideBarFile = renderFilePath + renderFile;
//        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 创建所有包路由
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllPackageRoute(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {

        List<ApiDocVitepressThemeDto> packageFileList = createPackageAndSubFile(apiDocDocsifyThemeDtos);


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


        prop.put("sidePath","/route/allpackage/");
        writeSidebarContent(renderFilePath, prop,"sidebarJson.btl",sidbarContentBuilder);


        //打印包下类明细
        for (ApiDocVitepressThemeDto apiDocDocsifyThemeDtoPac : packageFileList) {

            List<ApiDocVitepressThemeDto> packageChildList = apiDocDocsifyThemeDtoPac.getChildList();

            List<ApiDocVitepressThemeDto> allClassList= new ArrayList<>();
            List<ApiDocVitepressThemeDto> allInterfaceList = new ArrayList<>();
            List<ApiDocVitepressThemeDto> allEnumList = new ArrayList<>();
            List<ApiDocVitepressThemeDto> allAnnoList = new ArrayList<>();

            for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : packageChildList) {
                String fullClassName = apiDocVitepressThemeDto.getFullClassName();
                JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
                boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                boolean isAnno = BooleanUtil.isTrue(javaClassMeta.getAnnotationClass());
                if(isEnum){
                    allEnumList.add(apiDocVitepressThemeDto);
                }else if(isAnno){
                    allAnnoList.add(apiDocVitepressThemeDto);
                }else if(isClass){
                    allClassList.add(apiDocVitepressThemeDto);
                }else{
                    allInterfaceList.add(apiDocVitepressThemeDto);
                }
            }

            List<ApiDocVitepressThemeDto> allClassIndexClassList = getIndexClassList(allClassList);
            List<ApiDocVitepressThemeDto> allInterfaceIndexClassList = getIndexClassList(allInterfaceList);
            List<ApiDocVitepressThemeDto> allEnumIndexClassList = getIndexClassList(allEnumList);
            List<ApiDocVitepressThemeDto> allAnnoIndexClassList = getIndexClassList(allAnnoList);

            ApiDocVitepressThemeDto packageDesc = packageChildList.get(0);
            String firstComment = "";
            if(StrUtil.isNotBlank(packageDesc.getPackageDesc())){
                firstComment = EjdocStrUtil.getFirstComment(packageDesc.getPackageDesc());
            }

            if(CollectionUtil.isNotEmpty(packageChildList)){
                Map<String,Object> childProp = new HashMap<>();
                childProp.put("classList",allClassIndexClassList);
                childProp.put("interfaceList",allInterfaceIndexClassList);
                childProp.put("enumList",allEnumIndexClassList);
                childProp.put("annoList",allAnnoIndexClassList);
                childProp.put("title", apiDocDocsifyThemeDtoPac.getPackageName());
                childProp.put("classType","allPackage");
                childProp.put("firstComment",firstComment);
                childProp.put("allComment",packageDesc.getPackageDesc());
                childProp.put("author",packageDesc.getAuthor());

                String packageRouteReadmeFile = "/route/allpackage/" + apiDocDocsifyThemeDtoPac.getPackageNamePath()+"/README.md";
                writeThemeTemplateFile(renderFilePath, childProp,"allClassReadme.btl",packageRouteReadmeFile);
            }

        }
    }

    private List<ApiDocVitepressThemeDto> createPackageAndSubFile(List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocVitepressThemeDto>> groupPackageClassMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocVitepressThemeDto::getPackageName));
        List<ApiDocVitepressThemeDto> packageFileList = new ArrayList<>();
        groupPackageClassMap.forEach((packageName,packageClassList) ->{
            ApiDocVitepressThemeDto packageDocsify = new ApiDocVitepressThemeDto();
            packageDocsify.setPackageName(packageName);
            packageDocsify.setChildList(packageClassList);
            if(CollectionUtil.isNotEmpty(packageClassList)){
                String firstCompent = "";
                if(StrUtil.isNotBlank(packageClassList.get(0).getPackageDesc())){
                    firstCompent = EjdocStrUtil.getFirstComment(packageClassList.get(0).getPackageDesc());
                }
                packageDocsify.setPackageDesc(firstCompent);
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
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllModuleRoute(String renderFilePath, List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos,Map<String,List<VitepressSidebar>> sideBarMap,StringBuilder sidbarContentBuilder) {

        List<ApiDocVitepressThemeDto> moduleFileList = createModuleAndSubFile(apiDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allmodule/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allModuleReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allModule");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allmodule/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        prop.put("sidePath","/route/allmodule/");
        writeSidebarContent(renderFilePath, prop,"sidebarJson.btl",sidbarContentBuilder);

        for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : moduleFileList) {

            List<ApiDocVitepressThemeDto> packageChildList = apiDocVitepressThemeDto.getChildList();

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allModule");
                childProp.put("title", apiDocVitepressThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allmodule/"+ apiDocVitepressThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (ApiDocVitepressThemeDto packageDto : packageChildList) {

                    List<ApiDocVitepressThemeDto> classDtoList = packageDto.getChildList();

                    List<ApiDocVitepressThemeDto> indexClassList = getIndexClassList(classDtoList);

                    if(CollectionUtil.isNotEmpty(classDtoList)){
                        String moduleName = packageDto.getModuleName();
                        Map<String,Object> moduleClassProp = new HashMap<>();
                        moduleClassProp.put("tableList",indexClassList);
                        moduleClassProp.put("classType","packageDetail");
                        moduleClassProp.put("title",packageDto.getPackageName());
                        String packageReadMeFile = "/" +moduleName + "/" + packageDto.getPackageNamePath()+"/README.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"allClassReadme.btl",packageReadMeFile);

                        moduleClassProp.put("sideType","packageDetail");
                        String packageSideBarFile = "/" +moduleName+"/"+ packageDto.getPackageNamePath()+"/_sidebar.md";
                        writeThemeTemplateFile(renderFilePath, moduleClassProp,"sidebar.btl",packageSideBarFile);

                        moduleClassProp.put("sidePath","/" +moduleName+"/"+ packageDto.getPackageNamePath());
                        writeSidebarContent(renderFilePath, moduleClassProp,"sidebarJson.btl",sidbarContentBuilder);
                    }
                }
            }

        }

    }

    private List<ApiDocVitepressThemeDto> createAllSerialFile(List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocVitepressThemeDto>> groupResultMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocVitepressThemeDto::getModuleName));
        //按照module->package->class组织
        List<ApiDocVitepressThemeDto> moduleFileList = new ArrayList<>();

        groupResultMap.forEach((moduleName,moduleClassList) ->{
            ApiDocVitepressThemeDto moduleDocsify = new ApiDocVitepressThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<ApiDocVitepressThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(ApiDocVitepressThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                ApiDocVitepressThemeDto packageDocsify = new ApiDocVitepressThemeDto();
                packageDocsify.setModuleName(moduleName);
                if(CollectionUtil.isNotEmpty(packageClassList)){
                    packageDocsify.setPackageDesc(packageClassList.get(0).getPackageDesc());
                }
                packageDocsify.setPackageName(packageName);
                List<ApiDocVitepressThemeDto> serializableList = new ArrayList<>();
                for (ApiDocVitepressThemeDto apiDocVitepressThemeDto : packageClassList) {
                    String fullClassName = apiDocVitepressThemeDto.getFullClassName();
                    TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    if(treeIndexClassMeta != null && CollectionUtil.isNotEmpty(treeIndexClassMeta.getAllInterfaceClasses())){
                        for (JavaClassMeta allInterfaceClass : treeIndexClassMeta.getAllInterfaceClasses()) {
                            if(StrUtil.equals("java.io.Serializable",allInterfaceClass.getFullClassName())){
                                serializableList.add(apiDocVitepressThemeDto);
                            }
                        }
                    }

                }
                packageDocsify.setChildList(serializableList);
                packageDocsify.setPackageNamePath(packageName.replace(".","/"));
                moduleDocsify.addChild(packageDocsify);
            });
            moduleFileList.add(moduleDocsify);
        });
        return moduleFileList;
    }

    private List<ApiDocVitepressThemeDto> createModuleAndSubFile(List<ApiDocVitepressThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocVitepressThemeDto>> groupResultMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocVitepressThemeDto::getModuleName));
        //按照module->package->class组织
        List<ApiDocVitepressThemeDto> moduleFileList = new ArrayList<>();
        groupResultMap.forEach((moduleName,moduleClassList) ->{
            ApiDocVitepressThemeDto moduleDocsify = new ApiDocVitepressThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<ApiDocVitepressThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(ApiDocVitepressThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                ApiDocVitepressThemeDto packageDocsify = new ApiDocVitepressThemeDto();
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

        ClassPathResource staticResource = new ClassPathResource("com/ejdoc/doc/generate/config/template/markdown/api/theme/docsify/static.zip");
        InputStream stream = staticResource.getStream();
        FastByteArrayOutputStream read = IoUtil.read(stream);
        FileUtil.writeBytes(read.toByteArray(), renderFilePath +"/static.zip");
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
