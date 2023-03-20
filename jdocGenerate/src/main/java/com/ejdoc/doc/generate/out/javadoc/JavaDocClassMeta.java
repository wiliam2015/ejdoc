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
    private List<JavaClassMeta> allSupperClasses;

    private List<JavaClassMeta> allInterfaceClasses;

    private List<JavaClassMeta> allSubClasses;

    private List<JavaClassMeta> allSubInterfaceClasses;


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
}
