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
import com.ejdoc.doc.generate.model.DocTemplateThemeInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.javadoc.dto.JavaDocDeprecatedDto;
import com.ejdoc.doc.generate.template.BaseOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.doc.generate.template.markdown.JavaDocMarkdownDocOutTemplate;
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

public class ApiDocDocsifyTemplateTheme extends BaseOutTemplate implements DocTemplateTheme {
    /***/
    private final Logger log = LoggerFactory.getLogger(JavaDocMarkdownDocOutTemplate.class);

    private DocClassRenderUtil docClassFn = new DocClassRenderUtil();
    public ApiDocDocsifyTemplateTheme() {
        super();
    }
    public ApiDocDocsifyTemplateTheme(DocGenerateConfig docGenerateConfig) {
       this(null,docGenerateConfig);
    }
    public ApiDocDocsifyTemplateTheme(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
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
        List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos = readyBaseData(docTemplateThemeInfo);

        readyRenderProp(jsonFilePath,renderFileRootPath,docTemplateThemeInfo, propMap);

        //当前版本文件渲染
        renderTemplateThemeFile(renderFilePath,apiDocDocsifyThemeDtos,projectRootPath,propMap);
        //最新版本文件渲染
        renderTemplateThemeFile(renderFileRootPath,apiDocDocsifyThemeDtos,projectRootPath,propMap);
    }

    private void renderTemplateThemeFile(String renderFilePath,List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos, String projectRootPath, Map<String,Object> propMap) {

        createRouteInfo(renderFilePath, apiDocDocsifyThemeDtos);

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
    private void readyRenderProp(String jsonFilePath,String renderFileRootPath,DocTemplateThemeInfo docTemplateThemeInfo, Map<String, Object> propMap) {
        JSONObject jsonObject = JSONUtil.readJSONObject(new File(jsonFilePath +"/projectMetaInfo.json"), CharsetUtil.CHARSET_UTF_8);
        if(jsonObject != null && jsonObject.size()> 0){
            propMap.putAll(jsonObject);
        }

        List<String> versions = Arrays.stream(FileUtil.ls(renderFileRootPath + "/v")).filter(File::isDirectory).map(File::getName).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(versions)){
            versions = new ArrayList<>();
        }
        if(!versions.contains(docTemplateThemeInfo.getVersion())){
            versions.add(docTemplateThemeInfo.getVersion());
        }
        propMap.put("versions",versions);

    }

    /**
     * 准备基础数据
     * @param docTemplateThemeInfo
     * @return
     */
    private List<ApiDocDocsifyThemeDto> readyBaseData(DocTemplateThemeInfo docTemplateThemeInfo) {
        List<File> jsonFiles = FileUtil.loopFiles(docTemplateThemeInfo.getJsonFilePath(), subFile -> FileTypeUtil.getType(subFile).equals("json"));
        List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos = new ArrayList<>();
        Map<String,String> packageDecsMap = new HashMap<>();
        Map<String,String> packageAuthorsMap = new HashMap<>();

        for (File jsonFile : jsonFiles) {
            ApiDocDocsifyThemeDto apiDocDocsifyThemeDto = new ApiDocDocsifyThemeDto();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            String className = jsonObject.getStr("className");
            Boolean interfaceClass = jsonObject.getBool("interfaceClass",false);
            String fullClassName = jsonObject.getStr("fullClassName");
            String moduleName = jsonObject.getStr("moduleName");
            String moduleDesc = jsonObject.getStr("moduleDesc");
            String packageName = jsonObject.getStr("packageName");
            JSONObject javaDocDeprecatedInfo = jsonObject.getJSONObject("javaDocDeprecatedInfo");
            apiDocDocsifyThemeDto.setInterfaceClass(interfaceClass);
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
                apiDocDocsifyThemeDto.setProjectName(jsonObject.getStr("projectName"));
                apiDocDocsifyThemeDto.setModuleName(moduleName);
                apiDocDocsifyThemeDto.setModuleDesc(moduleDesc);
                apiDocDocsifyThemeDto.setClassName(className);
                apiDocDocsifyThemeDto.setClassSimpleName(docClassFn.calSimpleClassNameStructure(jsonObject,null));
                String classDescHtml = docClassFn.calCommentNoEnterDocMd(jsonObject, jsonObject, "", null);
                apiDocDocsifyThemeDto.setClassDesc(classDescHtml);
//                apiDocDocsifyThemeDto.setClassDesc(classDesc);
                apiDocDocsifyThemeDto.setHead(className.substring(0,1).toUpperCase());
                apiDocDocsifyThemeDto.setFullClassName(fullClassName);
                apiDocDocsifyThemeDto.setPackageName(packageName);
                if(javaDocDeprecatedInfo != null){
                    Boolean deprecatedMethod = javaDocDeprecatedInfo.getBool("deprecatedMethod", false);
                    Boolean deprecatedContructor = javaDocDeprecatedInfo.getBool("deprecatedContructor", false);
                    Boolean deprecatedClass = javaDocDeprecatedInfo.getBool("deprecatedClass", false);
                    if(deprecatedMethod || deprecatedContructor || deprecatedClass){
                        apiDocDocsifyThemeDto.setDeprecatedModify(true);
                        JavaDocDeprecatedDto javaDocDeprecatedDto = JSONUtil.toBean(javaDocDeprecatedInfo, JavaDocDeprecatedDto.class);
                        apiDocDocsifyThemeDto.setDocDeprecatedDto(javaDocDeprecatedDto);
                    }
                }

                apiDocDocsifyThemeDtos.add(apiDocDocsifyThemeDto);
            }

        }

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : apiDocDocsifyThemeDtos) {
            String packageName = apiDocDocsifyThemeDto.getPackageName();
            String packageDesc = packageDecsMap.get(packageName);
            String authorDesc = packageAuthorsMap.get(packageName);
            apiDocDocsifyThemeDto.setPackageDesc(packageDesc);
            apiDocDocsifyThemeDto.setAuthor(authorDesc);
        }
        return apiDocDocsifyThemeDtos;
    }

    /**
     * 创建路由信息 所有模块  所有包  所有类 所有树级类索引
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createRouteInfo(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {
//        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();

        createAllInterfaceRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllClassRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllPackageRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllModuleRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllClassTreeIndexRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllDeprecatedClassIndexRoute(renderFilePath, apiDocDocsifyThemeDtos);

        createAllSerialIndexRoute(renderFilePath, apiDocDocsifyThemeDtos);

    }

    /**
     * 创建所有序列化类显示页面
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllSerialIndexRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {


        List<ApiDocDocsifyThemeDto> moduleFileList = createAllSerialFile(apiDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allserial/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allSerialReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allSerial");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allserial/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : moduleFileList) {

            List<ApiDocDocsifyThemeDto> packageChildList = apiDocDocsifyThemeDto.getChildList();

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allSerial");
                childProp.put("title", apiDocDocsifyThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allserial/"+ apiDocDocsifyThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (ApiDocDocsifyThemeDto packageDto : packageChildList) {

                    List<ApiDocDocsifyThemeDto> classDtoList = packageDto.getChildList();

                    List<ApiDocDocsifyThemeDto> indexClassList = getIndexClassList(classDtoList);

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
    private void createAllDeprecatedClassIndexRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {
        List<JavaDocDeprecatedDto> allDeprecatedClasses = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedMethods = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedContructors = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(apiDocDocsifyThemeDtos)){
            List<JavaDocDeprecatedDto> docDeprecatedDtos = apiDocDocsifyThemeDtos.stream().filter(ApiDocDocsifyThemeDto::isDeprecatedModify)
                    .map(ApiDocDocsifyThemeDto::getDocDeprecatedDto)
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
    }

    /**
     * 创建所有类的层次结构
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassTreeIndexRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"fullClassName");

        List<ApiDocDocsifyThemeDto> allClassHierarchyList = new ArrayList<>();
        ApiDocDocsifyThemeDto javaLangObject = createJavaLangObject();
        allClassHierarchyList.add(javaLangObject);

        List<ApiDocDocsifyThemeDto> interfaceClassHierarchyList = new ArrayList<>();
        ApiDocDocsifyThemeDto interfaceJavaLangObject = createJavaLangObject();
        interfaceClassHierarchyList.add(interfaceJavaLangObject);



        List<ApiDocDocsifyThemeDto> enumClassHierarchyList = new ArrayList<>();
        ApiDocDocsifyThemeDto enumJavaLangObject = createJavaLangObject();
        ApiDocDocsifyThemeDto enumBase = new ApiDocDocsifyThemeDto();
        enumBase.setFullClassName("java.lang.Enum");
        enumBase.setClassName("Enum");
        enumBase.setPackageName("java.lang");
        enumBase.setHierarchy(2);
        enumJavaLangObject.addChild(enumBase);
        enumClassHierarchyList.add(enumJavaLangObject);

        List<ApiDocDocsifyThemeDto> exceptionClassHierarchyList = new ArrayList<>();
        ApiDocDocsifyThemeDto exceptionJavaLangObject = createJavaLangObject();
        ApiDocDocsifyThemeDto throwBase = new ApiDocDocsifyThemeDto();
        throwBase.setFullClassName("java.lang.Throwable");
        throwBase.setClassName("Throwable");
        throwBase.setPackageName("java.lang");
        throwBase.setHierarchy(2);
        exceptionJavaLangObject.addChild(throwBase);
        exceptionClassHierarchyList.add(exceptionJavaLangObject);

        List<ApiDocDocsifyThemeDto> allChildClassHierarchyList = new ArrayList<>();
        List<ApiDocDocsifyThemeDto> allInterfaceClassHierarchyList = new ArrayList<>();
        List<ApiDocDocsifyThemeDto> allEnumClassHierarchyList = new ArrayList<>();
        List<ApiDocDocsifyThemeDto> allExceptionClassHierarchyList = new ArrayList<>();
        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allClassList) {
            String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
            boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
            if(treeIndexClassMeta != null){
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                if(isClass){
                    if(CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                        apiDocDocsifyThemeDto.setHierarchy(2);
                        allChildClassHierarchyList.add(apiDocDocsifyThemeDto);
                    }else{
                        boolean isExceptionClass = false;
                        for (JavaClassMeta classMeta : treeIndexClassMeta.getSupperClassList()) {
                            String className = classMeta.getClassName();
                            if("Exception".equals(className)){
                                apiDocDocsifyThemeDto.setHierarchy(4);
                                isExceptionClass = true;
                            }
                            if("RuntimeException".equals(className)){
                                apiDocDocsifyThemeDto.setHierarchy(5);
                                isExceptionClass = true;
                            }
                        }
                        if(isExceptionClass){
                            allExceptionClassHierarchyList.add(apiDocDocsifyThemeDto);
                        }

                    }

                }
                if(!isClass && CollectionUtil.isEmpty(treeIndexClassMeta.getInterfaceList())){
                    apiDocDocsifyThemeDto.setHierarchy(2);
                    allInterfaceClassHierarchyList.add(apiDocDocsifyThemeDto);

                }
                if(isEnum && CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                    apiDocDocsifyThemeDto.setHierarchy(3);
                    allEnumClassHierarchyList.add(apiDocDocsifyThemeDto);
                }
            }
        }
        javaLangObject.setChildList(allChildClassHierarchyList);
        interfaceJavaLangObject.setChildList(allInterfaceClassHierarchyList);
        enumBase.setChildList(allEnumClassHierarchyList);


        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allChildClassHierarchyList) {
            String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,3,apiDocDocsifyThemeDto);
        }

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allInterfaceClassHierarchyList) {
            String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllInterfaceSubClass(treeIndexClass,treeIndexClass,3,apiDocDocsifyThemeDto);
        }

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allEnumClassHierarchyList) {
            String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,4,apiDocDocsifyThemeDto);
        }

        ApiDocDocsifyThemeDto exceptionBase = new ApiDocDocsifyThemeDto();
        exceptionBase.setFullClassName("java.lang.Exception");
        exceptionBase.setClassName("Exception");
        exceptionBase.setPackageName("java.lang");
        exceptionBase.setHierarchy(3);
        throwBase.addChild(exceptionBase);


        ApiDocDocsifyThemeDto runtimeExceptionBase = new ApiDocDocsifyThemeDto();
        runtimeExceptionBase.setFullClassName("java.lang.RuntimeException");
        runtimeExceptionBase.setClassName("RuntimeException");
        runtimeExceptionBase.setPackageName("java.lang");
        runtimeExceptionBase.setHierarchy(4);
        exceptionBase.addChild(runtimeExceptionBase);

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allExceptionClassHierarchyList) {
            String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
            int hierarchy = apiDocDocsifyThemeDto.getHierarchy();
            if(hierarchy == 4){
                exceptionBase.addChild(apiDocDocsifyThemeDto);
            }
            if(hierarchy == 5){
                runtimeExceptionBase.addChild(apiDocDocsifyThemeDto);
            }
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,hierarchy+1,apiDocDocsifyThemeDto);
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
    }

    private ApiDocDocsifyThemeDto createJavaLangObject() {
        ApiDocDocsifyThemeDto javaLangObject = new ApiDocDocsifyThemeDto();
        javaLangObject.setFullClassName("java.lang.Object");
        javaLangObject.setClassName("Object");
        javaLangObject.setPackageName("java.lang");
        javaLangObject.setHierarchy(1);
        return javaLangObject;
    }

    private void addAllSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,ApiDocDocsifyThemeDto apiDocDocsifyThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childClassList = treeIndexClass.getChildClassList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        apiDocDocsifyThemeDto.addInterface(createApiDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childClassList)){
                for (JavaClassMeta classMeta : childClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    ApiDocDocsifyThemeDto apiDocDocsifyThemeDtoFromMeta = createApiDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    apiDocDocsifyThemeDto.addChild(apiDocDocsifyThemeDtoFromMeta);


                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,apiDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private void addAllInterfaceSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,ApiDocDocsifyThemeDto apiDocDocsifyThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childInterfaceList = treeIndexClass.getChildInterfaceList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        apiDocDocsifyThemeDto.addInterface(createApiDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childInterfaceList)){
                for (JavaClassMeta classMeta : childInterfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    ApiDocDocsifyThemeDto apiDocDocsifyThemeDtoFromMeta = createApiDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    apiDocDocsifyThemeDto.addChild(apiDocDocsifyThemeDtoFromMeta);

                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllInterfaceSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,apiDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private ApiDocDocsifyThemeDto createApiDocDocsifyThemeDtoFromMeta(JavaClassMeta classMeta,int hierarchy) {
        ApiDocDocsifyThemeDto apiDocDocsifyThemeDto = new ApiDocDocsifyThemeDto();
        apiDocDocsifyThemeDto.setProjectName(classMeta.getProjectName());
        apiDocDocsifyThemeDto.setModuleName(classMeta.getModuleName());
        apiDocDocsifyThemeDto.setModuleDesc(classMeta.getModuleDesc());
        apiDocDocsifyThemeDto.setClassName(classMeta.getClassName());
        apiDocDocsifyThemeDto.setFullClassName(classMeta.getFullClassName());
        apiDocDocsifyThemeDto.setHierarchy(hierarchy);
        apiDocDocsifyThemeDto.setPackageName(classMeta.getPackageName());
        return apiDocDocsifyThemeDto;
    }

    /**
     * 创建所有接口路由
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllInterfaceRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {

        List<ApiDocDocsifyThemeDto> allClassInfoList = getIndexInterfaceList(apiDocDocsifyThemeDtos);

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
    private void createAllClassRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> allClassList) {
        List<ApiDocDocsifyThemeDto> allClassInfoList = getIndexClassList(allClassList);

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
    private  List<ApiDocDocsifyThemeDto> getIndexInterfaceList(List<ApiDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        List<ApiDocDocsifyThemeDto> allClassInfoList = new ArrayList<>();
        String firstHeadStr = "";
        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allClassList) {
            if(!apiDocDocsifyThemeDto.isInterfaceClass()){
                continue;
            }
            String head = apiDocDocsifyThemeDto.getHead();
            if(!firstHeadStr.equals(head)){
                firstHeadStr = head;
                ApiDocDocsifyThemeDto headTitle = new ApiDocDocsifyThemeDto();
                headTitle.setFirstHead(true);
                headTitle.setHead(apiDocDocsifyThemeDto.getHead());
                headTitle.setClassName(apiDocDocsifyThemeDto.getClassName());
                allClassInfoList.add(headTitle);
            }
            allClassInfoList.add(apiDocDocsifyThemeDto);
        }
        return allClassInfoList;
    }

    /**
     * 获取有索引信息的类列表
     * @param allClassList 所有类信息
     * @return
     */
    private  List<ApiDocDocsifyThemeDto> getIndexClassList(List<ApiDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        List<ApiDocDocsifyThemeDto> allClassInfoList = new ArrayList<>();
        String firstHeadStr = "";
        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : allClassList) {
            String head = apiDocDocsifyThemeDto.getHead();
            if(!firstHeadStr.equals(head)){
                firstHeadStr = head;
                ApiDocDocsifyThemeDto headTitle = new ApiDocDocsifyThemeDto();
                headTitle.setFirstHead(true);
                headTitle.setHead(apiDocDocsifyThemeDto.getHead());
                headTitle.setClassName(apiDocDocsifyThemeDto.getClassName());
                allClassInfoList.add(headTitle);
            }
            allClassInfoList.add(apiDocDocsifyThemeDto);
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
        String templateFile = "/markdown/"+ docType +"/theme/docsify/" + templateName;
        Template sideBarTemplate = getGroupTemplate().getTemplate(templateFile);
        String sideBarContent = renderByTemplate(prop, sideBarTemplate);
        String sideBarFile = renderFilePath + renderFile;
        FileUtil.writeString(sideBarContent, sideBarFile,CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 创建所有包路由
     * @param renderFilePath
     * @param apiDocDocsifyThemeDtos
     */
    private void createAllPackageRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {

        List<ApiDocDocsifyThemeDto> packageFileList = createPackageAndSubFile(apiDocDocsifyThemeDtos);


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
        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDtoPac : packageFileList) {

            List<ApiDocDocsifyThemeDto> packageChildList = apiDocDocsifyThemeDtoPac.getChildList();

            List<ApiDocDocsifyThemeDto> allClassList= new ArrayList<>();
            List<ApiDocDocsifyThemeDto> allInterfaceList = new ArrayList<>();
            List<ApiDocDocsifyThemeDto> allEnumList = new ArrayList<>();
            List<ApiDocDocsifyThemeDto> allAnnoList = new ArrayList<>();

            for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : packageChildList) {
                String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
                JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
                boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                boolean isAnno = BooleanUtil.isTrue(javaClassMeta.getAnnotationClass());
                if(isEnum){
                    allEnumList.add(apiDocDocsifyThemeDto);
                }else if(isAnno){
                    allAnnoList.add(apiDocDocsifyThemeDto);
                }else if(isClass){
                    allClassList.add(apiDocDocsifyThemeDto);
                }else{
                    allInterfaceList.add(apiDocDocsifyThemeDto);
                }
            }

            List<ApiDocDocsifyThemeDto> allClassIndexClassList = getIndexClassList(allClassList);
            List<ApiDocDocsifyThemeDto> allInterfaceIndexClassList = getIndexClassList(allInterfaceList);
            List<ApiDocDocsifyThemeDto> allEnumIndexClassList = getIndexClassList(allEnumList);
            List<ApiDocDocsifyThemeDto> allAnnoIndexClassList = getIndexClassList(allAnnoList);

            ApiDocDocsifyThemeDto packageDesc = packageChildList.get(0);
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

    private List<ApiDocDocsifyThemeDto> createPackageAndSubFile(List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocDocsifyThemeDto>> groupPackageClassMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocDocsifyThemeDto::getPackageName));
        List<ApiDocDocsifyThemeDto> packageFileList = new ArrayList<>();
        groupPackageClassMap.forEach((packageName,packageClassList) ->{
            ApiDocDocsifyThemeDto packageDocsify = new ApiDocDocsifyThemeDto();
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
    private void createAllModuleRoute(String renderFilePath, List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {

        List<ApiDocDocsifyThemeDto> moduleFileList = createModuleAndSubFile(apiDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allmodule/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allModuleReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allModule");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allmodule/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : moduleFileList) {

            List<ApiDocDocsifyThemeDto> packageChildList = apiDocDocsifyThemeDto.getChildList();

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allModule");
                childProp.put("title", apiDocDocsifyThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allmodule/"+ apiDocDocsifyThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (ApiDocDocsifyThemeDto packageDto : packageChildList) {

                    List<ApiDocDocsifyThemeDto> classDtoList = packageDto.getChildList();

                    List<ApiDocDocsifyThemeDto> indexClassList = getIndexClassList(classDtoList);

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
                    }
                }
            }

        }

    }

    private List<ApiDocDocsifyThemeDto> createAllSerialFile(List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocDocsifyThemeDto>> groupResultMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocDocsifyThemeDto::getModuleName));
        //按照module->package->class组织
        List<ApiDocDocsifyThemeDto> moduleFileList = new ArrayList<>();

        groupResultMap.forEach((moduleName,moduleClassList) ->{
            ApiDocDocsifyThemeDto moduleDocsify = new ApiDocDocsifyThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<ApiDocDocsifyThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(ApiDocDocsifyThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                ApiDocDocsifyThemeDto packageDocsify = new ApiDocDocsifyThemeDto();
                packageDocsify.setModuleName(moduleName);
                if(CollectionUtil.isNotEmpty(packageClassList)){
                    packageDocsify.setPackageDesc(packageClassList.get(0).getPackageDesc());
                }
                packageDocsify.setPackageName(packageName);
                List<ApiDocDocsifyThemeDto> serializableList = new ArrayList<>();
                for (ApiDocDocsifyThemeDto apiDocDocsifyThemeDto : packageClassList) {
                    String fullClassName = apiDocDocsifyThemeDto.getFullClassName();
                    TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    if(treeIndexClassMeta != null && CollectionUtil.isNotEmpty(treeIndexClassMeta.getAllInterfaceClasses())){
                        for (JavaClassMeta allInterfaceClass : treeIndexClassMeta.getAllInterfaceClasses()) {
                            if(StrUtil.equals("java.io.Serializable",allInterfaceClass.getFullClassName())){
                                serializableList.add(apiDocDocsifyThemeDto);
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

    private List<ApiDocDocsifyThemeDto> createModuleAndSubFile(List<ApiDocDocsifyThemeDto> apiDocDocsifyThemeDtos) {
        Map<String, List<ApiDocDocsifyThemeDto>> groupResultMap = apiDocDocsifyThemeDtos.stream().collect(Collectors.groupingBy(ApiDocDocsifyThemeDto::getModuleName));
        //按照module->package->class组织
        List<ApiDocDocsifyThemeDto> moduleFileList = new ArrayList<>();
        groupResultMap.forEach((moduleName,moduleClassList) ->{
            ApiDocDocsifyThemeDto moduleDocsify = new ApiDocDocsifyThemeDto();
            moduleDocsify.setModuleName(moduleName);
            if(CollectionUtil.isNotEmpty(moduleClassList)){
                moduleDocsify.setModuleDesc(moduleClassList.get(0).getModuleDesc());
            }
            Map<String, List<ApiDocDocsifyThemeDto>> groupPackageClassMap = moduleClassList.stream().collect(Collectors.groupingBy(ApiDocDocsifyThemeDto::getPackageName));
            groupPackageClassMap.forEach((packageName,packageClassList) ->{
                ApiDocDocsifyThemeDto packageDocsify = new ApiDocDocsifyThemeDto();
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
