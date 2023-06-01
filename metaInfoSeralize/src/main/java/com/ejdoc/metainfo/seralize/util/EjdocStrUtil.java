package com.ejdoc.metainfo.seralize.util;

import java.util.StringTokenizer;

public class EjdocStrUtil {

    /**
     * 获取第一句注释
     * @param commentStr  注释内容
     * @return 第一句注释
     */
    public static String getFirstComment(String commentStr){
        StringBuilder  result = new StringBuilder();
        boolean startHtml = false;
        if(commentStr.startsWith("<")){
            startHtml = true;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(commentStr,"。<");
        while (stringTokenizer.hasMoreTokens()) {
            //分割得到的字符串
            String firstStr = stringTokenizer.nextToken();
            if(startHtml){
                result.append("<");
            }
            if(firstStr.endsWith(".")){
                result.append(firstStr);
            }else{
                result.append(firstStr).append(".");;
            }
            break;
        }
        return result.toString();
    }
}
