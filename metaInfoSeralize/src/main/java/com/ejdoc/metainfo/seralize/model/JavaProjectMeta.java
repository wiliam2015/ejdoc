package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.Map;

public class JavaProjectMeta implements Serializable {

    private String name;

    private boolean open;

    private String title;

    private String author;
    private String contract;

    private String email;

    private String host;

    private String basePath;

    private String description;

    private String version;

    private Map<String,String> license;

    private Map<String,Object> allProp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getLicense() {
        return license;
    }

    public void setLicense(Map<String, String> license) {
        this.license = license;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getAllProp() {
        return allProp;
    }

    public void setAllProp(Map<String, Object> allProp) {
        this.allProp = allProp;
    }
}
