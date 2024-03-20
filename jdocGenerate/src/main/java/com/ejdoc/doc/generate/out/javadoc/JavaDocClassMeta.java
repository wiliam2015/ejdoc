package com.ejdoc.doc.generate.out.javadoc;

import cn.hutool.json.JSONObject;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;

import java.util.ArrayList;
import java.util.List;

public class JavaDocClassMeta  {

    private JavaClassMeta javaClassMeta;

    private JSONObject javaClassMetaJsonObject;

    private JavaMetaSeralizePluginData javaMetaSeralizePluginData;

    private String jsonFilePath;
    /**所有嵌套类*/
    private List<JavaClassMeta> allNestedClasses;
    private List<JavaClassMeta> allSupperClasses;

    private List<JavaClassMeta> allInterfaceClasses;

    private List<JavaClassMeta> allSubClasses;

    private List<JavaClassMeta> allSubInterfaceClasses;

    private List<JavaClassMeta> supperClassList;

    private List<JavaClassMeta> childClassList;

    public List<JavaClassMeta> getAllSupperClasses() {
        return allSupperClasses;
    }

    public boolean addAllSupperClasses(JavaClassMeta javaClassMeta){
        if(allSupperClasses == null){
            allSupperClasses = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta allSupperClass : allSupperClasses) {
            if(allSupperClass.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return allSupperClasses.add(javaClassMeta);
        }
        return false;
    }

    public List<JavaClassMeta> getAllInterfaceClasses() {
        return allInterfaceClasses;
    }

    public boolean addAllInterfaceClasses(JavaClassMeta javaClassMeta){
        if(allInterfaceClasses == null){
            allInterfaceClasses = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : allInterfaceClasses) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return allInterfaceClasses.add(javaClassMeta);
        }
        return false;
    }

    public List<JavaClassMeta> getAllSubClasses() {
        return allSubClasses;
    }

    public boolean addAllSubClasses(JavaClassMeta javaClassMeta){
        if(allSubClasses == null){
            allSubClasses = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : allSubClasses) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return allSubClasses.add(javaClassMeta);
        }
        return false;
    }

    public List<JavaClassMeta> getAllSubInterfaceClasses() {
        return allSubInterfaceClasses;
    }

    public boolean addAllSubInterfaceClasses(JavaClassMeta javaClassMeta){
        if(allSubInterfaceClasses == null){
            allSubInterfaceClasses = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : allSubInterfaceClasses) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return allSubInterfaceClasses.add(javaClassMeta);
        }
        return false;
    }

    public JavaClassMeta getJavaClassMeta() {
        return javaClassMeta;
    }

    public void setJavaClassMeta(JavaClassMeta javaClassMeta) {
        this.javaClassMeta = javaClassMeta;
    }

    public JSONObject getJavaClassMetaJsonObject() {
        return javaClassMetaJsonObject;
    }

    public void setJavaClassMetaJsonObject(JSONObject javaClassMetaJsonObject) {
        this.javaClassMetaJsonObject = javaClassMetaJsonObject;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public JavaMetaSeralizePluginData getJavaMetaSeralizePluginData() {
        return javaMetaSeralizePluginData;
    }

    public void setJavaMetaSeralizePluginData(JavaMetaSeralizePluginData javaMetaSeralizePluginData) {
        this.javaMetaSeralizePluginData = javaMetaSeralizePluginData;
    }

    public List<JavaClassMeta> getSupperClassList() {
        return supperClassList;
    }

    public boolean addSupperClasses(JavaClassMeta javaClassMeta){
        if(supperClassList == null){
            supperClassList = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : supperClassList) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return supperClassList.add(javaClassMeta);
        }
        return false;
    }

    public void setSupperClassList(List<JavaClassMeta> supperClassList) {
        this.supperClassList = supperClassList;
    }

    public List<JavaClassMeta> getChildClassList() {
        return childClassList;
    }

    public boolean addChildClasses(JavaClassMeta javaClassMeta){
        if(childClassList == null){
            childClassList = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : childClassList) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return childClassList.add(javaClassMeta);
        }
        return false;
    }

    public void setChildClassList(List<JavaClassMeta> childClassList) {
        this.childClassList = childClassList;
    }

    public void addSupperClasses(List<JavaClassMeta> supperClassList) {
        if(supperClassList != null && supperClassList.size() != 0){
            if(this.supperClassList == null){
                this.supperClassList = new ArrayList<>();
            }
            this.supperClassList.addAll(supperClassList);
        }
    }

    public boolean addNestedClasses(JavaClassMeta javaClassMeta){
        if(allNestedClasses == null){
            allNestedClasses = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : allNestedClasses) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return allNestedClasses.add(javaClassMeta);
        }
        return false;
    }

    public List<JavaClassMeta> getAllNestedClasses() {
        return allNestedClasses;
    }

    public void setAllNestedClasses(List<JavaClassMeta> allNestedClasses) {
        this.allNestedClasses = allNestedClasses;
    }
}
