package com.ejdoc.doc.generate.out.javadoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.*;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaDocDeepDependParsePlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {

    private static final Logger log = LoggerFactory.getLogger(JavaDocDeepDependParsePlugin.class);


    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        List<JavaClassMeta> javaClassMetaList = MetaIndexContext.getJavaClassMetaList();
        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                parseJavaDocClassMetaInfo(javaClassMeta,javaMetaServalizePluginContextDto.getSeralizeConfig());
            }
        }

        List<TreeIndexClassMeta> treeIndexClassMetas = MetaIndexContext.getTreeIndexClassMetas();
        if(CollectionUtil.isNotEmpty(treeIndexClassMetas)){
            for (TreeIndexClassMeta treeJavaClassMeta : treeIndexClassMetas) {
                String fullClassName = treeJavaClassMeta.getJavaClassMeta().getFullClassName();
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                parseAllDependClassRelativePath(javaMetaServalizePluginContextDto.getSeralizeConfig(),treeJavaClassMeta,javaMetaFile);
            }
        }
    }

    /**
     * 解析所有依赖类的相对路径
     * @param seralizeConfig
     * @param javaClassMeta
     */
    private void parseAllDependClassRelativePath(SeralizeConfig seralizeConfig, TreeIndexClassMeta javaClassMeta, JavaMetaFileInfo javaMetaFileInfo) {
        if(javaMetaFileInfo == null){
            return;
        }
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllInterfaceClasses())){
            parseBaseClassDependPath(seralizeConfig,javaClassMeta.getAllInterfaceClasses(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSubInterfaceClasses())){
            parseBaseClassDependPath(seralizeConfig,javaClassMeta.getAllSubInterfaceClasses(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSubClasses())){
            parseBaseClassDependPath(seralizeConfig,javaClassMeta.getAllSubClasses(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllSupperClasses())){
            parseBaseClassDependPath(seralizeConfig,javaClassMeta.getAllSupperClasses(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
        }
        if(CollectionUtil.isNotEmpty(javaClassMeta.getAllNestedClasses())){
            parseBaseClassDependPath(seralizeConfig,javaClassMeta.getAllNestedClasses(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
        }

        addAllDeepDependMetaToJsonProp(javaClassMeta, javaMetaFileInfo);
    }

    private void parseJavaDocClassMetaInfo(JavaClassMeta javaClassMeta, SeralizeConfig seralizeConfig) {
        String fullClassName = javaClassMeta.getFullClassName();
        TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
        JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);

        addAllSupperClass(treeIndexClass,treeIndexClass);
        addAllSubClass(treeIndexClass,treeIndexClass);
        addAllNestedClass(treeIndexClass,treeIndexClass);

        addAllDeepDependMetaToJsonProp(treeIndexClass,javaMetaFile);

    }

    /**
     * 将所有深度依赖属性添加到json属性上
     * @param javaClassMeta
     * @param javaMetaFileInfo
     */
    private void addAllDeepDependMetaToJsonProp(TreeIndexClassMeta javaClassMeta, JavaMetaFileInfo javaMetaFileInfo) {
        reSetJsonMetaFileProp(javaMetaFileInfo, "allInterfaceClasses", javaClassMeta.getAllInterfaceClasses());
        reSetJsonMetaFileProp(javaMetaFileInfo, "allSubInterfaceClasses", javaClassMeta.getAllSubInterfaceClasses());
        reSetJsonMetaFileProp(javaMetaFileInfo, "allSubClasses", javaClassMeta.getAllSubClasses());
        reSetJsonMetaFileProp(javaMetaFileInfo, "allSupperClasses", javaClassMeta.getAllSupperClasses());
        reSetJsonMetaFileProp(javaMetaFileInfo, "allNestedClasses", javaClassMeta.getAllNestedClasses());
    }
    /**
     * 重新设置json元数据属性
     * @param javaMetaFileInfo
     * @param allInterfaceClasses
     * @param javaClassMeta
     */
    private void reSetJsonMetaFileProp(JavaMetaFileInfo javaMetaFileInfo, String allInterfaceClasses, List<JavaClassMeta> javaClassMeta) {
        if(CollectionUtil.isNotEmpty(javaClassMeta)){
            JSONObject jsonObject = javaMetaFileInfo.getJsonObject();
            jsonObject.putOpt(allInterfaceClasses, JSONUtil.parseArray(javaClassMeta));
        }
    }
    private void addAllSubClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> childInterfaceList = treeIndexClass.getChildInterfaceList();
            List<JavaClassMeta> childClassList = treeIndexClass.getChildClassList();
            if(CollectionUtil.isNotEmpty(childInterfaceList)){
                for (JavaClassMeta classMeta : childInterfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    rootTreeIndexClass.addAllSubInterfaceClasses(createSimpleClass(classMetaIndex));
                    if(!isJdkClass(fullClassName)){
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSubClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }

            if(CollectionUtil.isNotEmpty(childClassList)){
                for (JavaClassMeta classMeta : childClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    rootTreeIndexClass.addAllSubClasses(createSimpleClass(classMetaIndex));
                    if(!isJdkClass(fullClassName)){
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSubClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }
        }
    }

    /**
     * 增加所有嵌套类
     * @param treeIndexClass
     * @param rootTreeIndexClass
     */
    private void addAllNestedClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass) {
        if(ObjectUtil.isNotNull(treeIndexClass)){

            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            List<JavaClassMeta> supperClassList = treeIndexClass.getSupperClassList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    if(isJdkClass(fullClassName)){
                        classMeta.setJdkClass(true);
                        Class<?> jdkClass = loadClassByNoException(fullClassName, "JavaDocDeepDependParsePlugin addAllNestedClass loadClass error fullClassName:{}");
                        if(jdkClass != null){
                            addAllJdkSupperNestedClass(jdkClass,rootTreeIndexClass);
                        }
                    }else{
                        JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                        if(classMetaIndex != null){
                            List<JavaClassMeta> nestedClasses = classMetaIndex.getNestedClasses();
                            if(CollectionUtil.isNotEmpty(nestedClasses)){
                                for (JavaClassMeta nestedClass : nestedClasses) {
                                    rootTreeIndexClass.addAllNestedClasses(createSimpleClass(nestedClass));
                                }
                            }
                            TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                            addAllNestedClass(superTreeIndexClass,rootTreeIndexClass);
                        }
                    }
                }
            }

            if(CollectionUtil.isNotEmpty(supperClassList)){
                for (JavaClassMeta classMeta : supperClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    if(isJdkClass(fullClassName)){
                        classMeta.setJdkClass(true);
                        rootTreeIndexClass.addAllSupperClasses(createSimpleClass(classMeta));
                        Class<?> jdkClass = loadClassByNoException(fullClassName, " JavaDocDeepDependParsePlugin  addAllNestedClass loadClass fullClassName:{} error");
                        if(jdkClass != null){
                            Class<?> superclass = jdkClass.getSuperclass();
                            addAllJdkSupperNestedClass(superclass,rootTreeIndexClass);
                        }

                    }else{
                        JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                        if(classMetaIndex != null){
                            List<JavaClassMeta> nestedClasses = classMetaIndex.getNestedClasses();
                            if(CollectionUtil.isNotEmpty(nestedClasses)){
                                for (JavaClassMeta nestedClass : nestedClasses) {
                                    rootTreeIndexClass.addAllNestedClasses(createSimpleClass(nestedClass));
                                }
                            }
                            TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                            addAllNestedClass(superTreeIndexClass,rootTreeIndexClass);
                        }
                    }
                }
            }
        }
    }


    /**
     * 接口类-增加所有父接口
     * @param treeIndexClass
     * @param rootTreeIndexClass
     */
    private void addAllSupperClass(TreeIndexClassMeta treeIndexClass, TreeIndexClassMeta rootTreeIndexClass) {
        if(ObjectUtil.isNotNull(treeIndexClass)){
            List<JavaClassMeta> interfaceList = treeIndexClass.getInterfaceList();
            List<JavaClassMeta> supperClassList = treeIndexClass.getSupperClassList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                for (JavaClassMeta classMeta : interfaceList) {
                    String fullClassName = classMeta.getFullClassName();
                    if(isJdkClass(fullClassName)){
                        classMeta.setJdkClass(true);
                        rootTreeIndexClass.addAllInterfaceClasses(createSimpleClass(classMeta));
                        Class<?> jdkClass = loadClassByNoException(fullClassName, " JavaDocDeepDependParsePlugin  addAllSupperClass loadClass fullClassName:{} error");
                        if(jdkClass != null){
                            Class<?>[] interfaces = jdkClass.getInterfaces();
                            addAllJdkSupperInterfaceClass(interfaces,rootTreeIndexClass);
                        }
                    }else{
                        JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                        if(classMetaIndex == null){
                            rootTreeIndexClass.addAllInterfaceClasses(createSimpleClass(classMeta));
                        }else{
                            rootTreeIndexClass.addAllInterfaceClasses(createSimpleClass(classMetaIndex));
                        }
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSupperClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }

            if(CollectionUtil.isNotEmpty(supperClassList)){
                for (JavaClassMeta classMeta : supperClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    if(isJdkClass(fullClassName)){
                        classMeta.setJdkClass(true);
                        rootTreeIndexClass.addAllSupperClasses(createSimpleClass(classMeta));
                        Class<?> jdkClass = loadClassByNoException(fullClassName, " JavaDocDeepDependParsePlugin  addAllSupperClass loadClass fullClassName:{} error");
                        if(jdkClass != null){
                            Class<?> superclass = jdkClass.getSuperclass();
                            addAllJdkSupperClass(superclass,rootTreeIndexClass);
                        }
                    }else{
                        JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                        if(classMetaIndex == null){
                            rootTreeIndexClass.addAllSupperClasses(createSimpleClass(classMeta));
                        }else{
                            rootTreeIndexClass.addAllSupperClasses(createSimpleClass(classMetaIndex));
                        }
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSupperClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }
        }
    }

    private  Class<?> loadClassByNoException(String fullClassName, String s) {
        Class<?> jdkClass = null;
        try {
            jdkClass = ClassLoaderUtil.loadClass(fullClassName);
        } catch (Exception e) {
            log.debug(s, fullClassName);
        }
        return jdkClass;
    }


    private void addAllJdkSupperNestedClass(Class<?> supperJdkClass, TreeIndexClassMeta rootTreeIndexClass) {
        if(supperJdkClass != null){
            Class<?>[] interfaces = supperJdkClass.getDeclaredClasses();
            if(ArrayUtil.isNotEmpty(interfaces)){
                for (Class<?> anInterface : interfaces) {
                    JavaClassMeta interfaceNestClass = new JavaClassMeta();
                    interfaceNestClass.setJdkClass(true);
                    interfaceNestClass.setClassName(anInterface.getSimpleName());
                    interfaceNestClass.setFullClassName(anInterface.getName());
                    interfaceNestClass.setNestedClass(true);
                    interfaceNestClass.setNestedClassName(supperJdkClass.getSimpleName());
                    interfaceNestClass.setNestedClassFullClassName(supperJdkClass.getName());
                    rootTreeIndexClass.addAllNestedClasses(createSimpleClass(interfaceNestClass));

                    Class<?>[] declaredClasses = anInterface.getDeclaredClasses();
                    if(ArrayUtil.isNotEmpty(declaredClasses)){
                        for (Class<?> declaredClass : declaredClasses) {
                            JavaClassMeta javaClassMeta = new JavaClassMeta();
                            javaClassMeta.setJdkClass(true);
                            javaClassMeta.setClassName(declaredClass.getSimpleName());
                            javaClassMeta.setFullClassName(declaredClass.getName());
                            interfaceNestClass.setNestedClass(true);
                            interfaceNestClass.setNestedClassName(supperJdkClass.getSimpleName());
                            interfaceNestClass.setNestedClassFullClassName(supperJdkClass.getName());
                            rootTreeIndexClass.addAllNestedClasses(createSimpleClass(javaClassMeta));
                            addAllJdkSupperNestedClass(declaredClass,rootTreeIndexClass);
                        }
                    }
                    Class<?>[] superInterfaces = supperJdkClass.getInterfaces();
                    Class<?> superclass = supperJdkClass.getSuperclass();
                    addAllJdkSupperNestedClass(superclass,rootTreeIndexClass);
                    if(ArrayUtil.isNotEmpty(superInterfaces)){
                        for (Class<?> superInterface : superInterfaces) {
                            addAllJdkSupperNestedClass(superInterface,rootTreeIndexClass);
                        }
                    }
                }
            }
        }
    }

    private void addAllJdkSupperInterfaceClass(Class<?>[] interfaces, TreeIndexClassMeta rootTreeIndexClass) {
        if(interfaces != null && interfaces.length > 0){
            for (Class<?> anInterface : interfaces) {
                JavaClassMeta javaClassMeta = new JavaClassMeta();
                javaClassMeta.setJdkClass(true);
                javaClassMeta.setClassName(anInterface.getSimpleName());
                javaClassMeta.setFullClassName(anInterface.getName());
                rootTreeIndexClass.addAllInterfaceClasses(createSimpleClass(javaClassMeta));
                addAllJdkSupperInterfaceClass(anInterface.getInterfaces(),rootTreeIndexClass);
            }
        }
    }

    private void addAllJdkSupperClass(Class<?> superclass, TreeIndexClassMeta rootTreeIndexClass) {
        if(superclass != null){
            JavaClassMeta javaClassMeta = new JavaClassMeta();
            javaClassMeta.setJdkClass(true);
            javaClassMeta.setClassName(superclass.getSimpleName());
            javaClassMeta.setFullClassName(superclass.getName());
            rootTreeIndexClass.addAllSupperClasses(createSimpleClass(javaClassMeta));

            addAllJdkSupperClass(superclass.getSuperclass(),rootTreeIndexClass);
        }
    }


    private  JavaClassMeta createSimpleClass(JavaClassMeta javaClassMeta1) {
        if(javaClassMeta1 == null){
            return null;
        }
        JavaClassMeta supperClassAdd = new JavaClassMeta();
        supperClassAdd.setClassName(javaClassMeta1.getClassName());
        supperClassAdd.setFullClassName(javaClassMeta1.getFullClassName());
        supperClassAdd.setDependencyRelativePath(javaClassMeta1.getDependencyRelativePath());
        supperClassAdd.setDependencyAbsolutePath(javaClassMeta1.getDependencyAbsolutePath());
        supperClassAdd.setTypeParameters(javaClassMeta1.getTypeParameters());
        supperClassAdd.setTypeArguments(javaClassMeta1.getTypeArguments());
        supperClassAdd.setJdkClass(javaClassMeta1.getJdkClass());
        supperClassAdd.setNestedClass(javaClassMeta1.getNestedClass());
        supperClassAdd.setNestedClassName(javaClassMeta1.getNestedClassName());
        supperClassAdd.setNestedClassFullClassName(javaClassMeta1.getNestedClassFullClassName());
        if(BooleanUtil.isTrue(javaClassMeta1.getJdkClass())){
            if(StrUtil.length(javaClassMeta1.getFullClassName()) > StrUtil.length(javaClassMeta1.getClassName())) {
                String replace = StrUtil.replace(javaClassMeta1.getFullClassName(), javaClassMeta1.getClassName(), "", false);
                supperClassAdd.setClassNamePrefix(replace);
            }
        }
        return supperClassAdd;
    }


    private boolean isJdkClass(String fullClassName) {
        if (StrUtil.isBlank(fullClassName)) {
            return false;
        } else {
            return fullClassName.startsWith("java.") || fullClassName.startsWith("javax.");
        }
    }
}
