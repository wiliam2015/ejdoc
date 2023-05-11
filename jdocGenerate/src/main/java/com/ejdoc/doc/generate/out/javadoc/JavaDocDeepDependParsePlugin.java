package com.ejdoc.doc.generate.out.javadoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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

        addAllDeepDependMetaToJsonProp(javaClassMeta, javaMetaFileInfo);
    }

    private void parseJavaDocClassMetaInfo(JavaClassMeta javaClassMeta, SeralizeConfig seralizeConfig) {
        String fullClassName = javaClassMeta.getFullClassName();
        TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
        JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);

        addAllSupperClass(treeIndexClass,treeIndexClass);
        addAllSubClass(treeIndexClass,treeIndexClass);

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
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    rootTreeIndexClass.addAllInterfaceClasses(createSimpleClass(classMetaIndex));
                    if(!isJdkClass(fullClassName)){
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSupperClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }

            if(CollectionUtil.isNotEmpty(supperClassList)){
                for (JavaClassMeta classMeta : supperClassList) {
                    String fullClassName = classMeta.getFullClassName();
                    JavaClassMeta classMetaIndex = MetaIndexContext.getClassMetaByFullName(fullClassName);
                    rootTreeIndexClass.addAllSupperClasses(createSimpleClass(classMetaIndex));
                    if(!isJdkClass(fullClassName)){
                        TreeIndexClassMeta superTreeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(fullClassName);
                        addAllSupperClass(superTreeIndexClass,rootTreeIndexClass);
                    }
                }
            }
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
