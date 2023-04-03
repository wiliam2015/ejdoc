package com.ejdoc.doc.generate.util.beetl.function;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.doc.generate.comment.CommentSerializeFactory;
import org.beetl.core.Context;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

public class DocClassRenderUtil {

    private MemberRenderUtil memberRenderUtil = new MemberRenderUtil();

    public String calClassNameStructure(Object paras, Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject classObj =( JSONObject)paras;
            result.append(calInnerModifer(classObj));
            result.append(calClassType(classObj,ctx));
            result.append(classObj.getStr("className"));
            StringBuilder typeArgumentStr = new StringBuilder();
            createTypeParametersMd(classObj,typeArgumentStr);
            result.append(typeArgumentStr);
            result.append(" ");
            result.append(calExtendClassMd(classObj.getJSONArray("superClasses"),ctx));
            result.append(" ");
            result.append(calImplentClassMd(classObj.getJSONArray("interfaces"),ctx));
        }
        result.append(" ");
        return result.toString();
    }

    public String calAllClassesMd(Object paras, String appendBefore,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONArray){
            JSONArray allClasses =( JSONArray)paras;
            if(allClasses != null && allClasses.size() > 0){
                StringBuilder innerLoopSb = new StringBuilder();
                for (Object allClass : allClasses) {
                    JSONObject allClassObj =( JSONObject)allClass;
                    innerLoopSb.append(",");
                    innerLoopSb.append(createClassNameLinkMd(allClassObj));
                }
                if(StrUtil.isNotBlank(appendBefore)){
                    result.append(appendBefore);
                }
                result.append(innerLoopSb.substring(1));
            }
        }
        return result.toString();
    }

    public String calLinkClassNameMd(Object paras,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONObject){
            JSONObject allClasses =(JSONObject)paras;
            String classNamePrefix = allClasses.getStr("classNamePrefix", "");
            result.append(classNamePrefix);
            result.append(createClassNameLinkMd(allClasses));
        }
        return result.toString();
    }

    /**
     * 计算所有父类的方法明细，生成markdown结构
     * @param paras
     * @param appendBefore
     * @param basePath
     * @param ctx
     * @return
     */
    public String calAllClassesDetailMethodMd(Object paras, String appendBefore,String basePath,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONArray){
            JSONArray allClasses =( JSONArray)paras;
            if(allClasses != null && allClasses.size() > 0){
                StringBuilder innerLoopSb = new StringBuilder();
                int i = 1;
                for (Object allClass : allClasses) {
                    JSONObject methodObj =( JSONObject)allClass;
                    String methodName = methodObj.getStr("name");
                    innerLoopSb.append(",");
                    String linkPath = basePath+".md#"+memberRenderUtil.calUniqueMethodName(methodObj,ctx);
                    innerLoopSb.append(createCommonLinkMd(methodName,linkPath));
                    if((i++)%10==0){
                        innerLoopSb.append("\n");
                    }

                }
                if(StrUtil.isNotBlank(appendBefore)){
                    result.append(appendBefore);
                }
                result.append(innerLoopSb.substring(1));
            }
        }
        return result.toString();
    }

    /**
     * 所有父类的字段明细
     * @param paras
     * @param appendBefore
     * @param basePath
     * @param ctx
     * @return
     */
    public String calAllClassesDetailFieldMd(Object paras, String appendBefore,String basePath,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONArray){
            JSONArray allClasses =( JSONArray)paras;
            if(allClasses != null && allClasses.size() > 0){
                StringBuilder innerLoopSb = new StringBuilder();
                int i = 1;
                for (Object allClass : allClasses) {
                    JSONObject fieldObj =( JSONObject)allClass;
                    String fieldName = fieldObj.getStr("name");
                    innerLoopSb.append(",");
                    String linkPath = basePath+".md#"+fieldName;
                    innerLoopSb.append(createCommonLinkMd(fieldName,linkPath));
                    if((i++)%10==0){
                        innerLoopSb.append("\n");
                    }

                }
                if(StrUtil.isNotBlank(appendBefore)){
                    result.append(appendBefore);
                }
                result.append(innerLoopSb.substring(1));
            }
        }
        return result.toString();
    }


    public String calFieldStructureMd(Object paras, Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject classObj =( JSONObject)paras;
            result.append(calInnerModifer(classObj));
            result.append(" ");
            JSONObject type = classObj.getJSONObject("type");
            result.append(memberRenderUtil.calReturnMd(type,ctx));
            result.append(" ");
            result.append(classObj.getStr("name"));
        }
        result.append(" ");
        return result.toString();
    }

    private void createTypeParametersMd(JSONObject javaClass, StringBuilder typeArgumentStr) {
        if(javaClass.containsKey("typeParameters")){
            JSONArray typeArguments = javaClass.getJSONArray("typeParameters");
            typeArgumentStr.append("<");
            StringBuilder typeArgumentObjStr = new StringBuilder();
            for (Object typeArgument : typeArguments) {
                typeArgumentObjStr.append(",");
                if(typeArgument instanceof JSONObject){
                    JSONObject typeJsonObj = (JSONObject)typeArgument;
                    typeArgumentObjStr.append(createClassNameLinkMd(typeJsonObj));
                    createTypeParametersMd(typeJsonObj,typeArgumentStr);
                }else  if(typeArgument instanceof String){
                    typeArgumentObjStr.append(typeArgument);
                }

            }
            typeArgumentStr.append(typeArgumentObjStr.substring(1));
            typeArgumentStr.append(">");
        }
    }

    private String calExtendClassMd(JSONArray paras, Context ctx) {
        StringBuilder resultStr = new StringBuilder();
        if(paras != null && paras.size()>0){
            resultStr.append("extends ");
            StringBuilder paramStr = new StringBuilder();
            for (Object obj : paras) {
                JSONObject param = (JSONObject)obj;
                paramStr.append(",");
                paramStr.append(createClassNameLinkMd(param));
                StringBuilder typeArgumentStr = new StringBuilder();
                createTypeParametersMd(param,typeArgumentStr);
                paramStr.append(typeArgumentStr);
                paramStr.append(" ");
            }
            resultStr.append(paramStr.substring(1));
        }
        resultStr.append(" ");
        return resultStr.toString();
    }
    private String calImplentClassMd(JSONArray paras, Context ctx) {
        StringBuilder resultStr = new StringBuilder();
        if(paras != null && paras.size()>0){
            resultStr.append("implements ");
            StringBuilder paramStr = new StringBuilder();
            for (Object obj : paras) {
                JSONObject param = (JSONObject)obj;
                paramStr.append(",");
                paramStr.append(createClassNameLinkMd(param));
                StringBuilder typeArgumentStr = new StringBuilder();
                createTypeParametersMd(param,typeArgumentStr);
                paramStr.append(typeArgumentStr);
                paramStr.append(" ");
            }
            resultStr.append(paramStr.substring(1));
        }
        resultStr.append(" ");
        return resultStr.toString();
    }

    public  String createCommonLinkMd(String text,String linkText) {
        StringBuilder result = new StringBuilder();
        if(StrUtil.isNotBlank(linkText)){
            result.append("[");
            result.append(text);
            result.append("](");
            result.append(linkText);
            result.append(")");
        }else{
            result.append(text);
        }
        return result.toString();
    }

    public  String createClassNameLinkMd(JSONObject classJson) {
        StringBuilder result = new StringBuilder();
        if(classJson.containsKey("dependencyRelativePath")){
            result.append("[");
            result.append(classJson.getStr("className"));
            result.append("](");
            result.append(classJson.getStr("dependencyRelativePath"));
            result.append(".md");
            result.append(")");
        }else{
            result.append(classJson.getStr("className"));
        }
        return result.toString();
    }


    public String calClassType(Object paras, Context ctx){
        String result = "class ";
        if(paras instanceof JSONObject){
            JSONObject jsonObject =( JSONObject)paras;
            Boolean interfaceClass = jsonObject.getBool("interfaceClass",false);
            Boolean annotationClass = jsonObject.getBool("annotationClass",false);
            Boolean enumClass = jsonObject.getBool("enumClass",false);
            if(interfaceClass){
                result ="interface ";
            }else if(annotationClass){
                result ="@interface ";
            }else if(enumClass){
                result ="enum ";
            }
        }
        return result;
    }
    private String calInnerModifer(JSONObject javaClass) {
        StringBuilder methodStr = new StringBuilder();
        if(javaClass.containsKey("modifiers")){
            JSONArray modifiers =javaClass.getJSONArray("modifiers");
            if(modifiers.size() > 0){
                for (Object modifier : modifiers) {
                    methodStr.append(modifier.toString());
                    methodStr.append(" ");
                }
            }
        }
        return methodStr.toString();
    }


    public String calModifer(Object paras, Context ctx) {
        StringBuilder methodStr = new StringBuilder();
        if(paras instanceof JSONArray){
            JSONArray modifiers =( JSONArray)paras;

            if(modifiers.size() > 0){
                for (Object modifier : modifiers) {
                    methodStr.append(modifier.toString());
                    methodStr.append(" ");
                }
            }

        }
        return methodStr.toString();
    }

    public String calCommentMd(Object paras,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject jsonObject =( JSONObject)paras;
            if(jsonObject.containsKey("javaModelMeta")){
                result.append("  ");
                result.append(jsonObject.getJSONObject("javaModelMeta").getStr("comment",""));
                result.append("\n");
            }
        }
        if(result.length() > 0 ){
            return appendBefore +"\n\n"+ result;
        }
        return "";
    }

    public String calCommentNoEnterMd(Object paras, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject jsonObject =( JSONObject)paras;
            if(jsonObject.containsKey("javaModelMeta")){

                result.append(jsonObject.getJSONObject("javaModelMeta").getStr("comment",""));
            }
        }
        if(result.length() > 0){
            return result.toString().replace("\n","<br/>");
        }
        return "";
    }

    public String calCommentNoEnterDocMd(Object paras,Object propObj,String appendBefore, Context ctx) {
        String content = calCommentDocMd(paras, propObj, appendBefore, ctx);
        StringBuilder result = new StringBuilder("");
        if(content.length() > 0){
            StringTokenizer stringTokenizer = new StringTokenizer(content,".。");
            while (stringTokenizer.hasMoreTokens()) {
                //分割得到的字符串
                result.append(stringTokenizer.nextToken()).append(".");
                break;
            }
        }
        return result.toString().replace("\n","");
    }

    public String calCommentSeeTagsMd(Object paras,String type,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) paras;
            if(jsonObject.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = jsonObject.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("tags")){
                    JSONArray tags = javaModelMeta.getJSONArray("tags");
                    StringBuilder tagSb = new StringBuilder();
                    for (Object tag : tags) {
                        if(tag instanceof JSONObject){
                            JSONObject tagJsonObj = (JSONObject)tag;
                            String tagType = tagJsonObj.getStr("type", "");
                            if("SEE".equals(tagType)){
                                String name = tagJsonObj.getStr("name", "");
                                String value = tagJsonObj.getStr("value", "");
                                tagSb.append("  ");
                                tagSb.append(name);
                                tagSb.append(" - ");
                                tagSb.append(value);
                                tagSb.append("\n\n");
                            }
                        }
                    }
                    result.append(tagSb);
                }
            }
        }
        if(result.length() > 0 ){
            return appendBefore +"\n\n"+ result;
        }
        return "";
    }

    public String calCommentDocMd(Object paras,Object propObj,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject && propObj instanceof JSONObject) {
            JSONObject rootJsonObj = (JSONObject) propObj;
            Optional<JSONArray> javaDocCommentOptional = getDocCommentElements(paras);
            if(javaDocCommentOptional.isPresent()){
                result.append(parseCommentMd(javaDocCommentOptional.get(),rootJsonObj));
                result.append("\n");
            }
        }
        if(result.length() > 0 ){
            return appendBefore + result;
        }
        return "";
    }

    /**
     * 解析comment注释，包含内联标签
     * @param objects
     */
    private  String parseCommentMd(JSONArray objects,JSONObject rootJsonObj) {
        String packageName = rootJsonObj.getStr("packageName", "");
        String moduleName = rootJsonObj.getStr("moduleName", "");
        String projectName = rootJsonObj.getStr("projectName", "");
        StringBuilder result = new StringBuilder();
        Map<String, CommentSerialize> mdCommentSerializeMap = CommentSerializeFactory.createMdCommentSerializeMap();
        for (Object object : objects) {
            JSONObject commentJsonObj = (JSONObject)object;
            String commentType = commentJsonObj.getStr("type");
            String tagName = commentJsonObj.getStr("tagName");
            String content = commentJsonObj.getStr("content");
            CommentSerialize commentSerialize = mdCommentSerializeMap.get(commentType);
            if(commentSerialize != null){
                if(commentSerialize.accept(commentType)){
                    result.append(commentSerialize.toSerialize(content,projectName,moduleName,packageName));
                }
            }
        }
        return result.toString();
    }

    private Optional<JSONArray> getDocCommentElements(Object paras) {
        JSONArray javaDocCommentElementMetas = null;
        if(paras instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) paras;
            if(jsonObject.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = jsonObject.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("javaDocComment")){
                    JSONObject javaDocComment = javaModelMeta.getJSONObject("javaDocComment");
                    if(javaDocComment.containsKey("javaDocCommentElementMetas")){
                        javaDocCommentElementMetas = javaDocComment.getJSONArray("javaDocCommentElementMetas");
                    }
                }
            }
        }
        Optional<JSONArray> javaDocCommentOptional = Optional.ofNullable(javaDocCommentElementMetas);
        return javaDocCommentOptional;
    }

    public String calCommentTagsMd(Object paras,Object rootProp,String type,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject && rootProp instanceof JSONObject){
            JSONObject jsonObject =( JSONObject)paras;
            JSONObject rootPropObj =( JSONObject)rootProp;

            if(jsonObject.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = jsonObject.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("tags")){
                    JSONArray tags = javaModelMeta.getJSONArray("tags");
                    StringBuilder tagSb = new StringBuilder();
                    for (Object tag : tags) {
                        if(tag instanceof JSONObject){
                            JSONObject tagJsonObj = (JSONObject)tag;
                            String tagType = tagJsonObj.getStr("type", "");
                            if(type.equals(tagType)){
                                String name = tagJsonObj.getStr("name", "");
                                String value = "";
                                boolean values = tagJsonObj.containsKey("values");
                                if(values){
                                    value = parseCommentMd(tagJsonObj.getJSONArray("values"),rootPropObj);
                                }else{
                                    value = tagJsonObj.getStr("value", "");
//                                    value = value.trim().replace("\n","").replaceAll(" {2,}","");
                                    value = value.trim().replaceAll(" {2,}","");
                                }
                                tagSb.append("  ");
                                if(StrUtil.isNotBlank(name)){
                                    tagSb.append(name);
                                    tagSb.append(" - ");
                                }
                                tagSb.append(value);
                                tagSb.append("\n\n");
                            }
                        }
                    }
                    result.append(tagSb);
                }
            }
        }

        if(result.length() > 0 ){
            return appendBefore +result;
        }
        return "";
    }
}