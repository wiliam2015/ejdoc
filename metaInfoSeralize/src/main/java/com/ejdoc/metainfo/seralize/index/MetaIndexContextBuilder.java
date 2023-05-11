package com.ejdoc.metainfo.seralize.index;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * 索引上下文构造器
 */
public class MetaIndexContextBuilder {
    private static final Logger log = LoggerFactory.getLogger(MetaIndexContextBuilder.class);

    static private MetaIndexContextBuilder metaIndexContextBuilder = new MetaIndexContextBuilder();
    private MetaIndexContextBuilder(){}

    public MetaIndexContextBuilder(String outFilePath){
        createMetaIndexContext(outFilePath);
    }

    public static MetaIndexContextBuilder instance(){
        return metaIndexContextBuilder;
    }

    public void createMetaIndexContext(String outFilePath) {
        log.info("MetaIndexContextBuilder create all class index start");

        Map<String, JavaClassMeta> metaSeralizeFileIndex = new HashMap<>();
        Map<String, TreeIndexClassMeta> treeIndexClassIndex = new HashMap<>();
        Map<String, JavaMetaFileInfo> javaMetaFileIndex = new HashMap<>();


        List<JavaMetaFileInfo> javaMetaFileInfos = createJavaMetaFileIndex(outFilePath,javaMetaFileIndex);

        List<JavaClassMeta> allJavaMetaSeralizeClassList  = createMetaSeralizeFileIndex(javaMetaFileInfos,metaSeralizeFileIndex);

        List<TreeIndexClassMeta> treeIndexClassMetas = createTreeIndexClassIndex(treeIndexClassIndex, allJavaMetaSeralizeClassList,metaSeralizeFileIndex);

        MetaIndexContext.setOutFilePath(outFilePath);

        MetaIndexContext.setJavaMetaFileInfos(javaMetaFileInfos);

        MetaIndexContext.setJavaClassMetaList(allJavaMetaSeralizeClassList);

        MetaIndexContext.setAllJavaMetaFileIndexMap(javaMetaFileIndex);

        MetaIndexContext.setAllClassIndexMap(metaSeralizeFileIndex);

        MetaIndexContext.setAllTreeIndexClassMap(treeIndexClassIndex);

        MetaIndexContext.setTreeIndexClassMetas(treeIndexClassMetas);

        log.info("MetaIndexContextBuilder create all class index finish");
    }

    private List<JavaMetaFileInfo> createJavaMetaFileIndex(String outFilePath, Map<String, JavaMetaFileInfo> javaMetaFileIndex) {
        List<JavaMetaFileInfo> javaMetaFileInfos = new ArrayList<>();
        List<File> jsonFiles = FileUtil.loopFiles(outFilePath, subFile -> FileTypeUtil.getType(subFile).equals("json"));
        for (File jsonFile : jsonFiles) {
            JavaMetaFileInfo javaMetaFileInfo = new JavaMetaFileInfo();
            JSONObject jsonObject = JSONUtil.readJSONObject(jsonFile, CharsetUtil.CHARSET_UTF_8);
            javaMetaFileInfo.setFile(jsonFile);
            javaMetaFileInfo.setJsonObject(jsonObject);
            javaMetaFileInfo.setJsonFilePath(jsonFile.getPath());
            javaMetaFileInfo.setOutFileBasePath(outFilePath);
            javaMetaFileInfos.add(javaMetaFileInfo);
            javaMetaFileIndex.put(jsonObject.getStr("fullClassName"),javaMetaFileInfo);
        }
        return javaMetaFileInfos;
    }

    /**
     * 创建树形结构索引Map
     * @param treeIndexClassIndex
     * @param allJavaMetaSeralizeClassList
     */
    private List<TreeIndexClassMeta> createTreeIndexClassIndex(Map<String, TreeIndexClassMeta> treeIndexClassIndex, List<JavaClassMeta> allJavaMetaSeralizeClassList,Map<String, JavaClassMeta> metaSeralizeFileIndex) {
        List<TreeIndexClassMeta> treeIndexClassMetas = new ArrayList<>();
        Set<String> distinctSet = new HashSet<>();
        for (JavaClassMeta javaClassMeta : allJavaMetaSeralizeClassList) {
            String fullClassName = javaClassMeta.getFullClassName();
            JavaClassMeta javaClassMetaTree = new JavaClassMeta(javaClassMeta.getClassName(),javaClassMeta.getFullClassName());
            javaClassMetaTree.setInterfaceClass(javaClassMeta.getInterfaceClass());

            TreeIndexClassMeta treeIndexClassMeta = createTreeIndexClassByAllMapIndex(treeIndexClassIndex, fullClassName);
            treeIndexClassIndex.put(fullClassName,treeIndexClassMeta);

            addSupperClasses(javaClassMetaTree, javaClassMeta, treeIndexClassMeta);
            if(!distinctSet.contains(fullClassName)){
                treeIndexClassMetas.add(treeIndexClassMeta);
                distinctSet.add(fullClassName);
            }

            treeIndexClassMetas.addAll(addSubClassesFromSupper(javaClassMetaTree,javaClassMeta.getSuperClasses(), distinctSet,treeIndexClassIndex));
            treeIndexClassMetas.addAll(addSubClassesFromSupper(javaClassMetaTree,javaClassMeta.getInterfaces(), distinctSet,treeIndexClassIndex));
        }
        return treeIndexClassMetas;
    }

    /**
     * 增加父类
     * @param javaClassMetaTree
     * @param javaClassMeta
     * @param treeIndexClassMeta 树形结构类
     */
    private TreeIndexClassMeta addSupperClasses(JavaClassMeta javaClassMetaTree, JavaClassMeta javaClassMeta,TreeIndexClassMeta treeIndexClassMeta) {
        List<JavaClassMeta> superClasses =javaClassMeta.getSuperClasses();
        List<JavaClassMeta> interfaces =javaClassMeta.getInterfaces();
        boolean isClass = !BooleanUtil.isTrue(javaClassMeta.getInterfaceClass());
        treeIndexClassMeta.setJavaClassMeta(javaClassMetaTree);
        if(isClass){
            treeIndexClassMeta.addSupperClasses(superClasses);
            treeIndexClassMeta.addInterfaceList(interfaces);
        }else{
            treeIndexClassMeta.addInterfaceList(superClasses);
        }
        return treeIndexClassMeta;
    }

    /**
     * 增加子类
     * @param javaClassMetaTree
     * @param superClasses
     */
    private List<TreeIndexClassMeta> addSubClassesFromSupper(JavaClassMeta javaClassMetaTree, List<JavaClassMeta> superClasses,Set<String> distinctSet,Map<String, TreeIndexClassMeta> treeIndexClassIndex) {
        List<TreeIndexClassMeta> resultList = new ArrayList<>();
        boolean isClass = !BooleanUtil.isTrue(javaClassMetaTree.getInterfaceClass());
        if(CollectionUtil.isNotEmpty(superClasses)){
            for (JavaClassMeta superClass : superClasses) {
                String fullClassName = superClass.getFullClassName();
                TreeIndexClassMeta treeIndexClassMeta = createTreeIndexClassByAllMapIndex(treeIndexClassIndex, fullClassName);
                treeIndexClassMeta.setJavaClassMeta(superClass);
                if(isClass){
                    treeIndexClassMeta.addChildClasses(javaClassMetaTree);
                }else {
                    treeIndexClassMeta.addChildInterface(javaClassMetaTree);
                }

                if(!distinctSet.contains(fullClassName)){
                    resultList.add(treeIndexClassMeta);
                    distinctSet.add(fullClassName);
                }

                treeIndexClassIndex.put(fullClassName,treeIndexClassMeta);
            }
        }
        return resultList;
    }

    /**
     * 准备元数据序列化索引数据
     * @return
     */
    private List<JavaClassMeta> createMetaSeralizeFileIndex(List<JavaMetaFileInfo> javaMetaFileInfos , Map<String,JavaClassMeta> metaSeralizeFileIndex) {
        List<JavaClassMeta> allJavaMetaSeralizeClassList = new ArrayList<>();
        for (JavaMetaFileInfo javaMetaFileInfo : javaMetaFileInfos) {
            JavaClassMeta javaClassMeta = javaMetaFileInfo.getJsonObject().toBean(JavaClassMeta.class);
            allJavaMetaSeralizeClassList.add(javaClassMeta);
            String fullClassName = javaClassMeta.getFullClassName();
            metaSeralizeFileIndex.put(fullClassName,javaClassMeta);
        }
        return allJavaMetaSeralizeClassList;
    }

    /**
     * 从索引map中创建TreeIndexClassMeta
     * @param allClassMapIndex
     * @param fullClassName
     * @return
     */
    private TreeIndexClassMeta createTreeIndexClassByAllMapIndex(Map<String, TreeIndexClassMeta> allClassMapIndex, String fullClassName) {
        TreeIndexClassMeta javaDocClassMeta = allClassMapIndex.get(fullClassName);
        if(javaDocClassMeta == null){
            javaDocClassMeta = new TreeIndexClassMeta();
        }
        return javaDocClassMeta;
    }

}
