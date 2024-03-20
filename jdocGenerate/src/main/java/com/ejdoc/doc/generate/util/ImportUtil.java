package com.ejdoc.doc.generate.util;

import cn.hutool.core.collection.CollectionUtil;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.JavaClassImportMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入类分析工具包
 */
public class ImportUtil {

    /**
     * 获取类的导入类
     * 1.获取当前包下的导入类
     * 2.获取import语句导入的类
     * @param fullClassName
     * @return
     */
    public static List<String> getImportClassNames(String fullClassName){
        JavaClassMeta classMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
        return getImportClassNames(classMeta);
    }


    /**
     * 获取类的导入类
     * 1.获取当前包下的导入类
     * 2.获取import语句导入的类
     * @param classMeta
     * @return
     */
    public static List<String> getImportClassNames(JavaClassMeta classMeta){
        List<String> imports = new ArrayList<>();
        if(classMeta != null){
            //获取当前包下的导入类
            List<JavaClassMeta> classMetaByPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
            if(CollectionUtil.isNotEmpty(classMetaByPackage)){
                for (JavaClassMeta javaClassMeta : classMetaByPackage) {
                    imports.add(javaClassMeta.getFullClassName());
                }
            }

            //获取import语句导入的类
            List<JavaClassImportMeta> importsClass = classMeta.getImports();
            //导入子包引入
            for (JavaClassImportMeta importInfo : importsClass) {
                //星号导入和非静态导入，静态导入暂时不支持分析
                if(importInfo.isAsteriskImport() && !importInfo.isStaticImport()){
                    if(importInfo.getName().startsWith("java")){
                        //jdk包线的类暂时不支持，没想到好的方案
//                        imports.addAll(JdkClassUtil.getClassNamesByPackage(importInfo.getName()));
                    }else{
                        List<JavaClassMeta> packageClassList = MetaIndexContext.getClassMetaByPackage(importInfo.getName());
                        if(CollectionUtil.isNotEmpty(packageClassList)){
                            for (JavaClassMeta javaClassMeta : packageClassList) {
                                imports.add(javaClassMeta.getFullClassName());
                            }
                        }
                    }
                }
                else{
                    imports.add(importInfo.getName());
                }
            }
        }
        return imports;
    }


    /**
     * 获取类的导入类
     * 1.获取当前包下的导入类
     * 2.获取import语句导入的类
     * @param fullClassName
     * @return
     */
    public static List<JavaClassMeta> getImportClass(String fullClassName){
        JavaClassMeta classMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
        return getImportClass(classMeta);
    }
    /**
     * 获取类的导入类
     * 1.获取当前包下的导入类
     * 2.获取import语句导入的类
     * @param classMeta
     * @return
     */
    public static List<JavaClassMeta> getImportClass(JavaClassMeta classMeta){
        List<JavaClassMeta> imports = new ArrayList<>();
        if(classMeta != null){
            //获取当前包下的导入类
            List<JavaClassMeta> classMetaByPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
            if(CollectionUtil.isNotEmpty(classMetaByPackage)){
                for (JavaClassMeta javaClassMeta : classMetaByPackage) {
                    imports.add(javaClassMeta);
                }
            }

            //获取import语句导入的类
            List<JavaClassImportMeta> importsClass = classMeta.getImports();
            //导入子包引入
            for (JavaClassImportMeta importInfo : importsClass) {
                //星号导入和非静态导入，静态导入暂时不支持分析
                if(importInfo.isAsteriskImport() && !importInfo.isStaticImport()){
                    if(importInfo.getName().startsWith("java")){
                        //jdk包线的类暂时不支持，没想到好的方案
//                        imports.addAll(JdkClassUtil.getClassNamesByPackage(importInfo.getName()));
                    }else{
                        List<JavaClassMeta> packageClassList = MetaIndexContext.getClassMetaByPackage(importInfo.getName());
                        if(CollectionUtil.isNotEmpty(packageClassList)){
                            for (JavaClassMeta javaClassMeta : packageClassList) {
                                imports.add(javaClassMeta);
                            }
                        }
                    }
                }
                else if(!importInfo.isStaticImport()){
                    if(importInfo.getName().startsWith("java")){
                        JavaClassMeta javaClassMeta = new JavaClassMeta();
                        String fullName = importInfo.getName();
                        javaClassMeta.setFullClassName(fullName);
                        String className =fullName.substring(fullName.lastIndexOf(".")+1);
                        javaClassMeta.setClassName(className);
                        javaClassMeta.setValue(className);
                        javaClassMeta.setJdkClass(true);
                        imports.add(javaClassMeta);
                    }else{
                        imports.add(MetaIndexContext.getClassMetaByFullName(importInfo.getName()));
                    }
                }
            }
        }
        return imports;
    }
}
