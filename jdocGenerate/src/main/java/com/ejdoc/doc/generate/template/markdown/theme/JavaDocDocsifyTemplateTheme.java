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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaDocDocsifyTemplateTheme extends BaseOutTemplate implements DocTemplateTheme {
    /***/
    private final Logger log = LoggerFactory.getLogger(JavaDocMarkdownDocOutTemplate.class);

    private DocClassRenderUtil docClassFn = new DocClassRenderUtil();
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
        Map<String,String> packageAuthorsMap = new HashMap<>();

        for (File jsonFile : jsonFiles) {
            JavaDocDocsifyThemeDto javaDocDocsifyThemeDto = new JavaDocDocsifyThemeDto();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            String className = jsonObject.getStr("className");
            String classDesc = jsonObject.getStr("classDesc");
            String fullClassName = jsonObject.getStr("fullClassName");
            String moduleName = jsonObject.getStr("moduleName");
            String moduleDesc = jsonObject.getStr("moduleDesc");
            String packageName = jsonObject.getStr("packageName");
            JSONObject javaDocDeprecatedInfo = jsonObject.getJSONObject("javaDocDeprecatedInfo");
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
                javaDocDocsifyThemeDto.setProjectName(jsonObject.getStr("projectName"));
                javaDocDocsifyThemeDto.setModuleName(moduleName);
                javaDocDocsifyThemeDto.setModuleDesc(moduleDesc);
                javaDocDocsifyThemeDto.setClassName(className);
                javaDocDocsifyThemeDto.setClassSimpleName(docClassFn.calSimpleClassNameStructure(jsonObject,null));
                String classDescHtml = docClassFn.calCommentNoEnterDocMd(jsonObject, jsonObject, "", null);
                javaDocDocsifyThemeDto.setClassDesc(classDescHtml);
//                javaDocDocsifyThemeDto.setClassDesc(classDesc);
                javaDocDocsifyThemeDto.setHead(className.substring(0,1).toUpperCase());
                javaDocDocsifyThemeDto.setFullClassName(fullClassName);
                javaDocDocsifyThemeDto.setPackageName(packageName);
                if(javaDocDeprecatedInfo != null){
                    Boolean deprecatedMethod = javaDocDeprecatedInfo.getBool("deprecatedMethod", false);
                    Boolean deprecatedContructor = javaDocDeprecatedInfo.getBool("deprecatedContructor", false);
                    Boolean deprecatedClass = javaDocDeprecatedInfo.getBool("deprecatedClass", false);
                    if(deprecatedMethod || deprecatedContructor || deprecatedClass){
                        javaDocDocsifyThemeDto.setDeprecatedModify(true);
                        JavaDocDeprecatedDto javaDocDeprecatedDto = JSONUtil.toBean(javaDocDeprecatedInfo, JavaDocDeprecatedDto.class);
                        javaDocDocsifyThemeDto.setDocDeprecatedDto(javaDocDeprecatedDto);
                    }
                }

                javaDocDocsifyThemeDtos.add(javaDocDocsifyThemeDto);
            }

        }

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : javaDocDocsifyThemeDtos) {
            String packageName = javaDocDocsifyThemeDto.getPackageName();
            String packageDesc = packageDecsMap.get(packageName);
            String authorDesc = packageAuthorsMap.get(packageName);
            javaDocDocsifyThemeDto.setPackageDesc(packageDesc);
            javaDocDocsifyThemeDto.setAuthor(authorDesc);
        }
        return javaDocDocsifyThemeDtos;
    }

    /**
     * 创建路由信息 所有模块  所有包  所有类 所有树级类索引
     * @param docTemplateThemeInfo
     * @param javaDocDocsifyThemeDtos
     */
    private void createRouteInfo(DocTemplateThemeInfo docTemplateThemeInfo, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
        String renderFilePath = docTemplateThemeInfo.getRenderFilePath();

        createAllClassRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllPackageRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllModuleRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllClassTreeIndexRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllDeprecatedClassIndexRoute(renderFilePath, javaDocDocsifyThemeDtos);

        createAllSerialIndexRoute(renderFilePath, javaDocDocsifyThemeDtos);

    }

    /**
     * 创建所有序列化类显示页面
     * @param renderFilePath
     * @param javaDocDocsifyThemeDtos
     */
    private void createAllSerialIndexRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {


        List<JavaDocDocsifyThemeDto> moduleFileList = createAllSerialFile(javaDocDocsifyThemeDtos);

        Map<String,Object> prop = new HashMap<>();
        prop.put("tableList",moduleFileList);
        String allModuleReadmeFile = "/route/allserial/README.md";
        writeThemeTemplateFile(renderFilePath, prop,"allSerialReadme.btl",allModuleReadmeFile);

        prop.put("sideType","allSerial");
        prop.put("title","所有模块");
        String sideBarFile = "/route/allserial/_sidebar.md";
        writeThemeTemplateFile(renderFilePath, prop,"sidebar.btl",sideBarFile);

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : moduleFileList) {

            List<JavaDocDocsifyThemeDto> packageChildList = javaDocDocsifyThemeDto.getChildList();

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allSerial");
                childProp.put("title", javaDocDocsifyThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allserial/"+ javaDocDocsifyThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (JavaDocDocsifyThemeDto packageDto : packageChildList) {

                    List<JavaDocDocsifyThemeDto> classDtoList = packageDto.getChildList();

                    List<JavaDocDocsifyThemeDto> indexClassList = getIndexClassList(classDtoList);

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
     * @param javaDocDocsifyThemeDtos
     */
    private void createAllDeprecatedClassIndexRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
        List<JavaDocDeprecatedDto> allDeprecatedClasses = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedMethods = new ArrayList<>();
        List<JavaDocDeprecatedDto> allDeprecatedContructors = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(javaDocDocsifyThemeDtos)){
            List<JavaDocDeprecatedDto> docDeprecatedDtos = javaDocDocsifyThemeDtos.stream().filter(JavaDocDocsifyThemeDto::isDeprecatedModify)
                    .map(JavaDocDocsifyThemeDto::getDocDeprecatedDto)
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
    private void createAllClassTreeIndexRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"fullClassName");

        List<JavaDocDocsifyThemeDto> allClassHierarchyList = new ArrayList<>();
        JavaDocDocsifyThemeDto javaLangObject = createJavaLangObject();
        allClassHierarchyList.add(javaLangObject);

        List<JavaDocDocsifyThemeDto> interfaceClassHierarchyList = new ArrayList<>();
        JavaDocDocsifyThemeDto interfaceJavaLangObject = createJavaLangObject();
        interfaceClassHierarchyList.add(interfaceJavaLangObject);



        List<JavaDocDocsifyThemeDto> enumClassHierarchyList = new ArrayList<>();
        JavaDocDocsifyThemeDto enumJavaLangObject = createJavaLangObject();
        JavaDocDocsifyThemeDto enumBase = new JavaDocDocsifyThemeDto();
        enumBase.setFullClassName("java.lang.Enum");
        enumBase.setClassName("Enum");
        enumBase.setPackageName("java.lang");
        enumBase.setHierarchy(2);
        enumJavaLangObject.addChild(enumBase);
        enumClassHierarchyList.add(enumJavaLangObject);

        List<JavaDocDocsifyThemeDto> exceptionClassHierarchyList = new ArrayList<>();
        JavaDocDocsifyThemeDto exceptionJavaLangObject = createJavaLangObject();
        JavaDocDocsifyThemeDto throwBase = new JavaDocDocsifyThemeDto();
        throwBase.setFullClassName("java.lang.Throwable");
        throwBase.setClassName("Throwable");
        throwBase.setPackageName("java.lang");
        throwBase.setHierarchy(2);
        exceptionJavaLangObject.addChild(throwBase);
        exceptionClassHierarchyList.add(exceptionJavaLangObject);

        List<JavaDocDocsifyThemeDto> allChildClassHierarchyList = new ArrayList<>();
        List<JavaDocDocsifyThemeDto> allInterfaceClassHierarchyList = new ArrayList<>();
        List<JavaDocDocsifyThemeDto> allEnumClassHierarchyList = new ArrayList<>();
        List<JavaDocDocsifyThemeDto> allExceptionClassHierarchyList = new ArrayList<>();
        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allClassList) {
            String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
            boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
            if(treeIndexClassMeta != null){
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                if(isClass){
                    if(CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                        javaDocDocsifyThemeDto.setHierarchy(2);
                        allChildClassHierarchyList.add(javaDocDocsifyThemeDto);
                    }else{
                        boolean isExceptionClass = false;
                        for (JavaClassMeta classMeta : treeIndexClassMeta.getSupperClassList()) {
                            String className = classMeta.getClassName();
                            if("Exception".equals(className)){
                                javaDocDocsifyThemeDto.setHierarchy(4);
                                isExceptionClass = true;
                            }
                            if("RuntimeException".equals(className)){
                                javaDocDocsifyThemeDto.setHierarchy(5);
                                isExceptionClass = true;
                            }
                        }
                        if(isExceptionClass){
                            allExceptionClassHierarchyList.add(javaDocDocsifyThemeDto);
                        }

                    }

                }
                if(!isClass && CollectionUtil.isEmpty(treeIndexClassMeta.getInterfaceList())){
                    javaDocDocsifyThemeDto.setHierarchy(2);
                    allInterfaceClassHierarchyList.add(javaDocDocsifyThemeDto);

                }
                if(isEnum && CollectionUtil.isEmpty(treeIndexClassMeta.getSupperClassList())){
                    javaDocDocsifyThemeDto.setHierarchy(3);
                    allEnumClassHierarchyList.add(javaDocDocsifyThemeDto);
                }
            }
        }
        javaLangObject.setChildList(allChildClassHierarchyList);
        interfaceJavaLangObject.setChildList(allInterfaceClassHierarchyList);
        enumBase.setChildList(allEnumClassHierarchyList);


        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allChildClassHierarchyList) {
            String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,3,javaDocDocsifyThemeDto);
        }

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allInterfaceClassHierarchyList) {
            String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllInterfaceSubClass(treeIndexClass,treeIndexClass,3,javaDocDocsifyThemeDto);
        }

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allEnumClassHierarchyList) {
            String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,4,javaDocDocsifyThemeDto);
        }

        JavaDocDocsifyThemeDto exceptionBase = new JavaDocDocsifyThemeDto();
        exceptionBase.setFullClassName("java.lang.Exception");
        exceptionBase.setClassName("Exception");
        exceptionBase.setPackageName("java.lang");
        exceptionBase.setHierarchy(3);
        throwBase.addChild(exceptionBase);


        JavaDocDocsifyThemeDto runtimeExceptionBase = new JavaDocDocsifyThemeDto();
        runtimeExceptionBase.setFullClassName("java.lang.RuntimeException");
        runtimeExceptionBase.setClassName("RuntimeException");
        runtimeExceptionBase.setPackageName("java.lang");
        runtimeExceptionBase.setHierarchy(4);
        exceptionBase.addChild(runtimeExceptionBase);

        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allExceptionClassHierarchyList) {
            String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
            int hierarchy = javaDocDocsifyThemeDto.getHierarchy();
            if(hierarchy == 4){
                exceptionBase.addChild(javaDocDocsifyThemeDto);
            }
            if(hierarchy == 5){
                runtimeExceptionBase.addChild(javaDocDocsifyThemeDto);
            }
            TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
            addAllSubClass(treeIndexClass,treeIndexClass,hierarchy+1,javaDocDocsifyThemeDto);
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

    private JavaDocDocsifyThemeDto createJavaLangObject() {
        JavaDocDocsifyThemeDto javaLangObject = new JavaDocDocsifyThemeDto();
        javaLangObject.setFullClassName("java.lang.Object");
        javaLangObject.setClassName("Object");
        javaLangObject.setPackageName("java.lang");
        javaLangObject.setHierarchy(1);
        return javaLangObject;
    }

    private void addAllSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,JavaDocDocsifyThemeDto javaDocDocsifyThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childClassList = treeIndexClass.getChildClassList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        javaDocDocsifyThemeDto.addInterface(createJavaDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childClassList)){
                for (JavaClassMeta classMeta : childClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    JavaDocDocsifyThemeDto javaDocDocsifyThemeDtoFromMeta = createJavaDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    javaDocDocsifyThemeDto.addChild(javaDocDocsifyThemeDtoFromMeta);


                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,javaDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private void addAllInterfaceSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass,int hierarchy,JavaDocDocsifyThemeDto javaDocDocsifyThemeDto) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childInterfaceList = treeIndexClass.getChildInterfaceList();
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    JavaClassMeta classMetaByFullName = MetaIndexContext.getClassMetaByFullName(classMeta.getFullClassName());
                    if(classMetaByFullName != null){
                        javaDocDocsifyThemeDto.addInterface(createJavaDocDocsifyThemeDtoFromMeta(classMetaByFullName,2));
                    }
                }
            }
            if(CollectionUtil.isNotEmpty(childInterfaceList)){
                for (JavaClassMeta classMeta : childInterfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    JavaDocDocsifyThemeDto javaDocDocsifyThemeDtoFromMeta = createJavaDocDocsifyThemeDtoFromMeta(classMetaIndex, hierarchy);
                    javaDocDocsifyThemeDto.addChild(javaDocDocsifyThemeDtoFromMeta);

                    TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    addAllInterfaceSubClass(superTreeIndexClass,rootTreeIndexClass,hierarchy+1,javaDocDocsifyThemeDtoFromMeta);
                }
            }
        }
    }

    private JavaDocDocsifyThemeDto createJavaDocDocsifyThemeDtoFromMeta(JavaClassMeta classMeta,int hierarchy) {
        JavaDocDocsifyThemeDto javaDocDocsifyThemeDto = new JavaDocDocsifyThemeDto();
        javaDocDocsifyThemeDto.setProjectName(classMeta.getProjectName());
        javaDocDocsifyThemeDto.setModuleName(classMeta.getModuleName());
        javaDocDocsifyThemeDto.setModuleDesc(classMeta.getModuleDesc());
        javaDocDocsifyThemeDto.setClassName(classMeta.getClassName());
        javaDocDocsifyThemeDto.setFullClassName(classMeta.getFullClassName());
        javaDocDocsifyThemeDto.setHierarchy(hierarchy);
        javaDocDocsifyThemeDto.setPackageName(classMeta.getPackageName());
        return javaDocDocsifyThemeDto;
    }

    /**
     * 创建所有类路由
     * @param renderFilePath
     * @param allClassList
     */
    private void createAllClassRoute(String renderFilePath, List<JavaDocDocsifyThemeDto> allClassList) {
        List<JavaDocDocsifyThemeDto> allClassInfoList = getIndexClassList(allClassList);

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
    private  List<JavaDocDocsifyThemeDto> getIndexClassList(List<JavaDocDocsifyThemeDto> allClassList) {
        CollectionUtil.sortByProperty(allClassList,"className");

        List<JavaDocDocsifyThemeDto> allClassInfoList = new ArrayList<>();
        String firstHeadStr = "";
        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : allClassList) {
            String head = javaDocDocsifyThemeDto.getHead();
            if(!firstHeadStr.equals(head)){
                firstHeadStr = head;
                JavaDocDocsifyThemeDto headTitle = new JavaDocDocsifyThemeDto();
                headTitle.setFirstHead(true);
                headTitle.setHead(javaDocDocsifyThemeDto.getHead());
                headTitle.setClassName(javaDocDocsifyThemeDto.getClassName());
                allClassInfoList.add(headTitle);
            }
            allClassInfoList.add(javaDocDocsifyThemeDto);
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
        for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDtoPac : packageFileList) {

            List<JavaDocDocsifyThemeDto> packageChildList = javaDocDocsifyThemeDtoPac.getChildList();

            List<JavaDocDocsifyThemeDto> allClassList= new ArrayList<>();
            List<JavaDocDocsifyThemeDto> allInterfaceList = new ArrayList<>();
            List<JavaDocDocsifyThemeDto> allEnumList = new ArrayList<>();
            List<JavaDocDocsifyThemeDto> allAnnoList = new ArrayList<>();

            for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : packageChildList) {
                String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
                JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
                boolean isEnum = BooleanUtil.isTrue(javaClassMeta.getEnumClass());
                boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
                boolean isAnno = BooleanUtil.isTrue(javaClassMeta.getAnnotationClass());
                if(isEnum){
                    allEnumList.add(javaDocDocsifyThemeDto);
                }else if(isAnno){
                    allAnnoList.add(javaDocDocsifyThemeDto);
                }else if(isClass){
                    allClassList.add(javaDocDocsifyThemeDto);
                }else{
                    allInterfaceList.add(javaDocDocsifyThemeDto);
                }
            }

            List<JavaDocDocsifyThemeDto> allClassIndexClassList = getIndexClassList(allClassList);
            List<JavaDocDocsifyThemeDto> allInterfaceIndexClassList = getIndexClassList(allInterfaceList);
            List<JavaDocDocsifyThemeDto> allEnumIndexClassList = getIndexClassList(allEnumList);
            List<JavaDocDocsifyThemeDto> allAnnoIndexClassList = getIndexClassList(allAnnoList);

            JavaDocDocsifyThemeDto packageDesc = packageChildList.get(0);
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
                childProp.put("title", javaDocDocsifyThemeDtoPac.getPackageName());
                childProp.put("classType","allPackage");
                childProp.put("firstComment",firstComment);
                childProp.put("allComment",packageDesc.getPackageDesc());
                childProp.put("author",packageDesc.getAuthor());

                String packageRouteReadmeFile = "/route/allpackage/" + javaDocDocsifyThemeDtoPac.getPackageNamePath()+"/README.md";
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

            CollectionUtil.sortByProperty(packageChildList,"packageName");

            if(CollectionUtil.isNotEmpty(packageChildList)){

                Map<String,Object> childProp = new HashMap<>();
                childProp.put("tableList",packageChildList);
                childProp.put("packageType","allModule");
                childProp.put("title", javaDocDocsifyThemeDto.getModuleName());
                String moduleRouteInfoFile = "/route/allmodule/"+ javaDocDocsifyThemeDto.getModuleName()+".md";
                writeThemeTemplateFile(renderFilePath, childProp,"allPackageReadme.btl",moduleRouteInfoFile);

                for (JavaDocDocsifyThemeDto packageDto : packageChildList) {

                    List<JavaDocDocsifyThemeDto> classDtoList = packageDto.getChildList();

                    List<JavaDocDocsifyThemeDto> indexClassList = getIndexClassList(classDtoList);

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

    private List<JavaDocDocsifyThemeDto> createAllSerialFile(List<JavaDocDocsifyThemeDto> javaDocDocsifyThemeDtos) {
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
                List<JavaDocDocsifyThemeDto> serializableList = new ArrayList<>();
                for (JavaDocDocsifyThemeDto javaDocDocsifyThemeDto : packageClassList) {
                    String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
                    TreeIndexClassMeta treeIndexClassMeta = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                    if(treeIndexClassMeta != null && CollectionUtil.isNotEmpty(treeIndexClassMeta.getAllInterfaceClasses())){
                        for (JavaClassMeta allInterfaceClass : treeIndexClassMeta.getAllInterfaceClasses()) {
                            if(StrUtil.equals("java.io.Serializable",allInterfaceClass.getFullClassName())){
                                serializableList.add(javaDocDocsifyThemeDto);
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

        String docType = getDocGenerateConfig().getDocTypeEnum().getCode();
        ClassPathResource staticResource = new ClassPathResource("com/ejdoc/doc/generate/config/template/markdown/"+docType+"/theme/docsify/static.zip");
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
