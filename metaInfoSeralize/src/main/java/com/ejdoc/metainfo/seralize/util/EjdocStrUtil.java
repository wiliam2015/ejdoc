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
        StringTokenizer stringTokenizer = new StringTokenizer(commentStr,"\n。.");
        while (stringTokenizer.hasMoreTokens()) {
            //分割得到的字符串
            String firstStr = stringTokenizer.nextToken();
//            if(startHtml){
//                result.append("<");
//            }
            if(firstStr.endsWith(".")){
                result.append(firstStr);
            }else{
                result.append(firstStr).append(".");;
            }
            break;
        }
        return result.toString();
    }

//    public static void main(String[] args) {
//        String content ="<a href=\"#hutool-paraser/cn/hutool/cache/Hierarchical\">Hierarchical</a>选择器，用于根据一定的规则从两个<a href=\"#hutool-paraser/cn/hutool/cache/Hierarchical\">Hierarchical</a>实现类中选择并返回一个最合适的对象\n";
//        String firstComment = getFirstComment(content);
//        System.out.println(firstComment);
//    }
}
