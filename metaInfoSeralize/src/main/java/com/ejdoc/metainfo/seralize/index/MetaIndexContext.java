package com.ejdoc.metainfo.seralize.index;

import com.ejdoc.metainfo.seralize.model.JavaClassMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 元数据索引上下文
 */
public class MetaIndexContext {

    private static String outFilePath;

    private static List<JavaMetaFileInfo> javaMetaFileInfos = new ArrayList<>();
    /**
     * java元信息
     */
    private static List<JavaClassMeta> javaClassMetaList = new ArrayList<>();

    private static List<TreeIndexClassMeta> treeIndexClassMetas = new ArrayList<>();

    /**
     * 存储所有类的元数据文件信息索引
     */
    private static Map<String,JavaMetaFileInfo> allJavaMetaFileIndexMap = new HashMap<>();
    /**
     * 存储当前包下的类
     */
    private static Map<String,List<JavaClassMeta>> packageNameIndexMap = new HashMap<>();
    /**
     * 存储所有类的元数据信息索引
     */
    private static Map<String,JavaClassMeta> allClassIndexMap = new HashMap<>();

    /**
     * 存储所有类上下引用关系级别的索引,TreeIndexClassMeta只存储类的简称和全名称信息
     */
    private static Map<String, TreeIndexClassMeta> allTreeIndexClassMap = new HashMap<>();


    public static JavaMetaFileInfo getJavaMetaFileByFullName(String fullClassName){
        return allJavaMetaFileIndexMap.get(fullClassName);
    }

    public static JavaClassMeta getClassMetaByFullName(String fullClassName){
        return allClassIndexMap.get(fullClassName);
    }

    public static List<JavaClassMeta> getClassMetaByPackage(String packageName){
        return packageNameIndexMap.get(packageName);
    }

    public static TreeIndexClassMeta getTreeIndexClassMetaByFullName(String fullClassName){
        return allTreeIndexClassMap.get(fullClassName);
    }

    public static Map<String, JavaClassMeta> getAllClassIndexMap() {
        return allClassIndexMap;
    }

    public static Map<String, TreeIndexClassMeta> getAllTreeIndexClassMap() {
        return allTreeIndexClassMap;
    }

    public static void setAllClassIndexMap(Map<String, JavaClassMeta> allClassIndexMap) {
        MetaIndexContext.allClassIndexMap = allClassIndexMap;
    }

    public static void setAllTreeIndexClassMap(Map<String, TreeIndexClassMeta> allTreeIndexClassMap) {
        MetaIndexContext.allTreeIndexClassMap = allTreeIndexClassMap;
    }

    public static List<JavaMetaFileInfo> getJavaMetaFileInfos() {
        return javaMetaFileInfos;
    }

    public static void setJavaMetaFileInfos(List<JavaMetaFileInfo> javaMetaFileInfos) {
        MetaIndexContext.javaMetaFileInfos = javaMetaFileInfos;
    }

    public static Map<String, JavaMetaFileInfo> getAllJavaMetaFileIndexMap() {
        return allJavaMetaFileIndexMap;
    }

    public static void setAllJavaMetaFileIndexMap(Map<String, JavaMetaFileInfo> allJavaMetaFileIndexMap) {
        MetaIndexContext.allJavaMetaFileIndexMap = allJavaMetaFileIndexMap;
    }

    public static String getOutFilePath() {
        return outFilePath;
    }

    public static void setOutFilePath(String outFilePath) {
        MetaIndexContext.outFilePath = outFilePath;
    }

    public static List<JavaClassMeta> getJavaClassMetaList() {
        return javaClassMetaList;
    }

    public static void setJavaClassMetaList(List<JavaClassMeta> javaClassMetaList) {
        MetaIndexContext.javaClassMetaList = javaClassMetaList;
    }

    public static List<TreeIndexClassMeta> getTreeIndexClassMetas() {
        return treeIndexClassMetas;
    }

    public static void setTreeIndexClassMetas(List<TreeIndexClassMeta> treeIndexClassMetas) {
        MetaIndexContext.treeIndexClassMetas = treeIndexClassMetas;
    }

    public static Map<String, List<JavaClassMeta>> getPackageNameIndexMap() {
        return packageNameIndexMap;
    }

    public static void setPackageNameIndexMap(Map<String, List<JavaClassMeta>> packageNameIndexMap) {
        MetaIndexContext.packageNameIndexMap = packageNameIndexMap;
    }
}
