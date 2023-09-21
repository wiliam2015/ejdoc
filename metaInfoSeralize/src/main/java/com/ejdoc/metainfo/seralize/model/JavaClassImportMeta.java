package com.ejdoc.metainfo.seralize.model;

public class JavaClassImportMeta {

    /**
     * 导入名称
     */
    private String name;

    /**
     * 是否是静态导入
     */
    private boolean staticImport;

    /**
     * 是否是星号导入
     */
    private boolean asteriskImport;

    /**
     *
     */
    private boolean phantom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStaticImport() {
        return staticImport;
    }

    public void setStaticImport(boolean staticImport) {
        this.staticImport = staticImport;
    }

    public boolean isAsteriskImport() {
        return asteriskImport;
    }

    public void setAsteriskImport(boolean asteriskImport) {
        this.asteriskImport = asteriskImport;
    }

    public boolean isPhantom() {
        return phantom;
    }

    public void setPhantom(boolean phantom) {
        this.phantom = phantom;
    }
}
