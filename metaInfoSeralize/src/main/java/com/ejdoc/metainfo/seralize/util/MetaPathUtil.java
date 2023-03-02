package com.ejdoc.metainfo.seralize.util;

import cn.hutool.core.lang.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MetaPathUtil {

    /**
     * 计算两个路径的相对路径
     * @param oldPathStr
     * @param targetPathStr
     * @return
     */
    public static String calRelativePath(String oldPathStr,String targetPathStr){
        Assert.notBlank(oldPathStr,"oldPath not null");
        Assert.notBlank(targetPathStr,"targetPath not null");
        Path oldPath = Paths.get(oldPathStr);
        Path targetPath = Paths.get(targetPathStr);
        Path relativePath = targetPath.relativize(oldPath);
        if(relativePath != null){
            String pathVal = relativePath.toString();
            if(pathVal.startsWith("../")){
                pathVal= pathVal.substring(3);
            }
            return pathVal;
        }
        return "";
    }
}
