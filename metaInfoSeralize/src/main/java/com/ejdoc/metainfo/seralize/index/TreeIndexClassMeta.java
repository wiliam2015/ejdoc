package com.ejdoc.metainfo.seralize.index;

import com.ejdoc.metainfo.seralize.model.JavaClassMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 索引信息类包含父子类
 */
public class TreeIndexClassMeta {

    private JavaClassMeta javaClassMeta;

    private List<JavaClassMeta> allSupperClasses;

    private List<JavaClassMeta> allInterfaceClasses;

    private List<JavaClassMeta> allSubClasses;

    private List<JavaClassMeta> allSubInterfaceClasses;

    private List<JavaClassMeta> supperClassList;

    private List<JavaClassMeta> childClassList;

    private List<JavaClassMeta> interfaceList;

    private List<JavaClassMeta> childInterfaceList;

    public List<JavaClassMeta> getAllSupperClasses() {
        return allSupperClasses;
    }

    public boolean addAllSupperClasses(JavaClassMeta javaClassMeta){
        if(javaClassMeta == null){
            return false;
        }
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
        if(javaClassMeta == null){
            return false;
        }
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
    public boolean addAllInterfaceClasses(List<JavaClassMeta> javaClassMetaList){
        if(this.allInterfaceClasses == null){
            this.allInterfaceClasses = new ArrayList<>();
        }
        return this.allInterfaceClasses.addAll(javaClassMetaList);
    }

    public List<JavaClassMeta> getAllSubClasses() {
        return allSubClasses;
    }

    public boolean addAllSubClasses(JavaClassMeta javaClassMeta){
        if(javaClassMeta == null){
            return false;
        }
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

    public boolean addAllSubClasses(List<JavaClassMeta> javaClassMetaList){
        if(this.allSubClasses == null){
            this.allSubClasses = new ArrayList<>();
        }
        return this.allSubClasses.addAll(javaClassMetaList);
    }

    public List<JavaClassMeta> getAllSubInterfaceClasses() {
        return allSubInterfaceClasses;
    }

    public boolean addAllSubInterfaceClasses(JavaClassMeta javaClassMeta){
        if(javaClassMeta == null){
            return false;
        }
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

    public boolean addAllSubInterfaceClasses(List<JavaClassMeta> javaClassMetaList){
        if(this.allSubInterfaceClasses == null){
            this.allSubInterfaceClasses = new ArrayList<>();
        }
        return this.allSubInterfaceClasses.addAll(javaClassMetaList);
    }

    public JavaClassMeta getJavaClassMeta() {
        return javaClassMeta;
    }

    public void setJavaClassMeta(JavaClassMeta javaClassMeta) {
        this.javaClassMeta = javaClassMeta;
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

    public void addInterfaceList(List<JavaClassMeta> classList) {
        if(classList != null && classList.size() != 0){
            if(this.interfaceList == null){
                this.interfaceList = new ArrayList<>();
            }
            this.interfaceList.addAll(classList);
        }
    }

    public List<JavaClassMeta> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<JavaClassMeta> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public void addChildInterfaceList(List<JavaClassMeta> classList) {
        if(classList != null && classList.size() != 0){
            if(this.childInterfaceList == null){
                this.childInterfaceList = new ArrayList<>();
            }
            this.childInterfaceList.addAll(classList);
        }
    }

    public boolean addChildInterface(JavaClassMeta javaClassMeta){
        if(childInterfaceList == null){
            childInterfaceList = new ArrayList<>();
        }
        boolean exist = false;
        for (JavaClassMeta classMeta : childInterfaceList) {
            if(classMeta.getFullClassName().equals(javaClassMeta.getFullClassName())){
                exist = true;
                break;
            }
        }
        if(!exist){
            return childInterfaceList.add(javaClassMeta);
        }
        return false;
    }

    public List<JavaClassMeta> getChildInterfaceList() {
        return childInterfaceList;
    }

    public void setChildInterfaceList(List<JavaClassMeta> childInterfaceList) {
        this.childInterfaceList = childInterfaceList;
    }

}
