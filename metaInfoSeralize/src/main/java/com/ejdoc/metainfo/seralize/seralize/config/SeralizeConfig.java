package com.ejdoc.metainfo.seralize.seralize.config;

public class SeralizeConfig {

    /**
     * 格式化字符串
     */
    private boolean prettyFormat;
    /**
     * 依赖信息使用绝对路径
     */
    private boolean useAbsPath;

    public boolean isPrettyFormat() {
        return prettyFormat;
    }

    public void setPrettyFormat(boolean prettyFormat) {
        this.prettyFormat = prettyFormat;
    }

    public boolean isUseAbsPath() {
        return useAbsPath;
    }

    public void setUseAbsPath(boolean useAbsPath) {
        this.useAbsPath = useAbsPath;
    }
}
