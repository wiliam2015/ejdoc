package com.ejdoc.metainfo.seralize.model;

import java.io.Serializable;
import java.util.List;

public class JavaMemberMeta implements Serializable {


    private List<String> modifiers;

    private JavaClassMeta getDeclaringClass;

    private String name;


    private Boolean abstracts;

    private Boolean finals;

    private Boolean natives;

    private Boolean privates;

    private Boolean protecteds;

    private Boolean publics;

    private Boolean statics;

    private Boolean strictfps;

    private Boolean synchronizeds;

    private Boolean transients;

    private Boolean volatiles;
    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public JavaClassMeta getGetDeclaringClass() {
        return getDeclaringClass;
    }

    public void setGetDeclaringClass(JavaClassMeta getDeclaringClass) {
        this.getDeclaringClass = getDeclaringClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(Boolean abstracts) {
        this.abstracts = abstracts;
    }

    public Boolean getFinals() {
        return finals;
    }

    public void setFinals(Boolean finals) {
        this.finals = finals;
    }

    public Boolean getNatives() {
        return natives;
    }

    public void setNatives(Boolean natives) {
        this.natives = natives;
    }

    public Boolean getPrivates() {
        return privates;
    }

    public void setPrivates(Boolean privates) {
        this.privates = privates;
    }

    public Boolean getProtecteds() {
        return protecteds;
    }

    public void setProtecteds(Boolean protecteds) {
        this.protecteds = protecteds;
    }

    public Boolean getPublics() {
        return publics;
    }

    public void setPublics(Boolean publics) {
        this.publics = publics;
    }

    public Boolean getStatics() {
        return statics;
    }

    public void setStatics(Boolean statics) {
        this.statics = statics;
    }

    public Boolean getStrictfps() {
        return strictfps;
    }

    public void setStrictfps(Boolean strictfps) {
        this.strictfps = strictfps;
    }

    public Boolean getSynchronizeds() {
        return synchronizeds;
    }

    public void setSynchronizeds(Boolean synchronizeds) {
        this.synchronizeds = synchronizeds;
    }

    public Boolean getTransients() {
        return transients;
    }

    public void setTransients(Boolean transients) {
        this.transients = transients;
    }

    public Boolean getVolatiles() {
        return volatiles;
    }

    public void setVolatiles(Boolean volatiles) {
        this.volatiles = volatiles;
    }
}
