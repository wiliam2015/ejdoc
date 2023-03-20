package com.ejdoc.metainfo.seralize.parser.impl.javaparser;

import java.util.HashSet;
import java.util.Set;

public class UnSolvedSymbolTool {

    private static Set<String> unSolvedCache = new HashSet<>(100);

    /**
     * 添加解析失败信息，最多1000个
     * @param content
     * @return
     */
    public static boolean addUnSolveTOCache(String content){
        if(unSolvedCache.size() > 1000){
            return false;
        }
        return unSolvedCache.add(content);
    }

    public static Set<String> getUnSolvedCache() {
        return unSolvedCache;
    }

    public static void clearCache(){
        unSolvedCache.clear();
    }
}
