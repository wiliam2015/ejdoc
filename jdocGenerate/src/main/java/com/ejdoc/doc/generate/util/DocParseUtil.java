package com.ejdoc.doc.generate.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.CommentSerializeFactory;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocParseUtil {

    /**
     * 解析comment注释，包含内联标签
     * @param objects
     */
    public static  String parseCommentMd(JSONArray objects, JSONObject rootJsonObj) {
        String packageName = rootJsonObj.getStr("packageName", "");
        String moduleName = rootJsonObj.getStr("moduleName", "");
        String projectName = rootJsonObj.getStr("projectName", "");
        String className = rootJsonObj.getStr("className", "");
        String fullClassName = rootJsonObj.getStr("fullClassName", "");
        CommentSerializeRootDocDto serializeRootDocDto = new CommentSerializeRootDocDto();
        serializeRootDocDto.setClassName(className);
        serializeRootDocDto.setFullClassName(fullClassName);
        serializeRootDocDto.setModuleName(moduleName);
        serializeRootDocDto.setPackageName(packageName);
        serializeRootDocDto.setProjectName(projectName);
        StringBuilder result = new StringBuilder();
        Map<String, CommentSerialize> mdCommentSerializeMap = CommentSerializeFactory.createMdCommentSerializeMap();
        for (Object object : objects) {
            JSONObject commentJsonObj = (JSONObject)object;
            String commentType = commentJsonObj.getStr("type");
            String content = commentJsonObj.getStr("content");
            CommentSerialize commentSerialize = mdCommentSerializeMap.get(commentType);
            if(commentSerialize != null){
                if(commentSerialize.accept(commentType)){
                    result.append(commentSerialize.toSerialize(content,serializeRootDocDto));
                }
            }
        }
        return result.toString();
    }

    /**
     * 解析JSF注释
     * @param objects
     * @param rootJsonObj
     * @return
     */
    public static  String parseJSRCommentMd(JSONArray objects, JSONObject rootJsonObj) {
        String packageName = rootJsonObj.getStr("packageName", "");
        String moduleName = rootJsonObj.getStr("moduleName", "");
        String projectName = rootJsonObj.getStr("projectName", "");
        String className = rootJsonObj.getStr("className", "");
        String fullClassName = rootJsonObj.getStr("fullClassName", "");
        CommentSerializeRootDocDto serializeRootDocDto = new CommentSerializeRootDocDto();
        serializeRootDocDto.setClassName(className);
        serializeRootDocDto.setFullClassName(fullClassName);
        serializeRootDocDto.setModuleName(moduleName);
        serializeRootDocDto.setPackageName(packageName);
        serializeRootDocDto.setProjectName(projectName);
        StringBuilder result = new StringBuilder();
        Map<String, CommentSerialize> mdCommentSerializeMap = CommentSerializeFactory.createMdJSRCommentSerializeMap();
        for (Object object : objects) {
            JSONObject commentJsonObj = (JSONObject)object;
            String tagName = commentJsonObj.getStr("tagName");
            String commentType = tagName.toUpperCase();
            String content = commentJsonObj.getStr("content");
            CommentSerialize commentSerialize = mdCommentSerializeMap.get(commentType);
            if(commentSerialize != null){
                if(commentSerialize.accept(commentType)){
                    result.append(",");
                    result.append(commentSerialize.toSerialize(content,serializeRootDocDto));
                }
            }
        }
        if(result.length() > 0){
            return result.substring(1);
        }
        return "";
    }

    /**
     * 解析JSF注释
     * @param objects
     * @param rootJsonObj
     * @return
     */
    public static  String parseJSRCommentMdByType(JSONArray objects, JSONObject rootJsonObj,String jsrType) {
        String packageName = rootJsonObj.getStr("packageName", "");
        String moduleName = rootJsonObj.getStr("moduleName", "");
        String projectName = rootJsonObj.getStr("projectName", "");
        String className = rootJsonObj.getStr("className", "");
        String fullClassName = rootJsonObj.getStr("fullClassName", "");
        CommentSerializeRootDocDto serializeRootDocDto = new CommentSerializeRootDocDto();
        serializeRootDocDto.setClassName(className);
        serializeRootDocDto.setFullClassName(fullClassName);
        serializeRootDocDto.setModuleName(moduleName);
        serializeRootDocDto.setPackageName(packageName);
        serializeRootDocDto.setProjectName(projectName);
        StringBuilder result = new StringBuilder();
        Map<String, CommentSerialize> mdCommentSerializeMap = CommentSerializeFactory.createMdJSRCommentSerializeMap();
        for (Object object : objects) {
            JSONObject commentJsonObj = (JSONObject)object;
            String tagName = commentJsonObj.getStr("tagName");
            String commentType = tagName.toUpperCase();
            if(!StrUtil.equals(jsrType,tagName)){
                continue;
            }
            String content = commentJsonObj.getStr("content");
            CommentSerialize commentSerialize = mdCommentSerializeMap.get(commentType);
            if(commentSerialize != null){
                if(commentSerialize.accept(commentType)){
                    result.append(commentSerialize.toSerialize(content,serializeRootDocDto));
                }
            }
        }
        return result.toString();
    }

    /**
     * 是否是jdk类
     * @param fullClassName
     * @return
     */
    public static boolean isJdkClass(String fullClassName){
        if(StrUtil.isBlank(fullClassName)){
            return false;
        }
        if(StrUtil.startWith(fullClassName,"java.") || StrUtil.startWith(fullClassName,"javax.")){
            return true;
        }
        return false;
    }

    /**
     * 解析jdk类链接
     * @param className
     * @param fullClassName
     * @return
     */
    public static String parseJdkClassLink(String className,String fullClassName,String filePath){
        StringBuilder result = new StringBuilder();
        if(StrUtil.startWith(fullClassName,"java.") || StrUtil.startWith(fullClassName,"javax.")){
            if(StrUtil.isBlank(filePath)){
                filePath = fullClassName.replace(".", "/");
            }
            //https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html?is-external=true
            result.append("[");
            result.append(className);
            result.append("](");
            result.append("https://docs.oracle.com/javase/8/docs/api/");
            result.append(filePath);
            result.append(".html?is-external=true");
            result.append(")");
        }else{
            result.append(fullClassName);
        }
        return result.toString();
    }
    /**
     * 解析jdk类链接
     * @param className
     * @param fullClassName
     * @return
     */
    public static String parseJdkClassLink(String className,String fullClassName){
        return parseJdkClassLink(className,fullClassName,"");
    }

    public static List<String> parseJdkClassMethodMd(String fullClassName){
        List<String> resultList = new ArrayList<>();
        if(StrUtil.startWith(fullClassName,"java.") || StrUtil.startWith(fullClassName,"javax.")){
            List<Method> allPublicMethod = JdkClassUtil.getAllPublicMethod(fullClassName);
            CollectionUtil.sortByProperty(allPublicMethod,"name");
            if(CollectionUtil.isNotEmpty(allPublicMethod)){
                for (Method method : allPublicMethod) {
                    String methodName = method.getName();
                    StringBuilder result = new StringBuilder();
                    String replacefullClassPath = fullClassName.replace(".", "/");
                    //https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html?is-external=true
                    result.append("[");
                    result.append(methodName);
                    result.append("](");
                    result.append("https://docs.oracle.com/javase/8/docs/api/");
                    result.append(replacefullClassPath);
                    result.append(".html?is-external=true#");
                    result.append(JdkClassUtil.getMethodFullName(method));
                    result.append(")");
                    resultList.add(result.toString());
                }
            }
        }
        return resultList;



    }
}
