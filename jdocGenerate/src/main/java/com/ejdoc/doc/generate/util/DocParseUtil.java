package com.ejdoc.doc.generate.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.CommentSerializeFactory;
import com.ejdoc.doc.generate.comment.dto.CommentSerializeRootDocDto;

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
}
