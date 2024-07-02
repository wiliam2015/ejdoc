package com.ejdoc.doc.generate.util.beetl.function;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.util.DocParseUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import org.beetl.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MemberRenderUtil {
    private static final Logger log = LoggerFactory.getLogger(MemberRenderUtil.class);

    /**
     * 计算方法体声明结构
     * @param paras
     * @param ctx
     * @return
     */
    public String calMethodStructureInnerLinkMd(Object paras, String prex,Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            result.append(calModifer(methodObj));
            result.append(calReturnMd(methodObj.getJSONObject("returns"),ctx));
            String methodName = methodObj.getStr("name");
            String methodNameMd = calUniqueMethodName(paras, ctx);
            result.append(createLinkIdMd(methodName,prex+methodNameMd,ctx));
            result.append(calParamMd(methodObj.getJSONArray("parameters"),ctx));
            result.append(calExceptionMd(methodObj.getJSONArray("exceptions"),ctx));
        }
        result.append(" ");
        return result.toString();
    }

    /**
     * 计算方法体声明结构
     * @param paras
     * @param ctx
     * @return
     */
    public String calMethodStructureMd(Object paras, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            result.append(calModifer(methodObj));
            result.append(calReturnMd(methodObj.getJSONObject("returns"),ctx));
            String methodName = methodObj.getStr("name");
            String methodNameMd = calUniqueMethodName(paras, ctx);
            result.append(createLinkIdMd(methodName,methodNameMd,ctx));
            result.append(calParamMd(methodObj.getJSONArray("parameters"),ctx));
            result.append(calExceptionMd(methodObj.getJSONArray("exceptions"),ctx));
        }
        result.append(" ");
        return result.toString();
    }

    public boolean existExtProp(Object paras,String propName, Context ctx){
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            if(methodObj.containsKey("extProp")){
                JSONObject extProp = methodObj.getJSONObject("extProp");
                return extProp.containsKey(propName);
            }
        }
        return false;
    }
    public String formatMockDataJson(Object jsonStr, Context ctx){
        try {
            if(jsonStr instanceof String ){
                String jsonData = Convert.toStr(jsonStr).trim();
                if(jsonData.startsWith("{") || jsonData.startsWith("[")){
                    return  JSONUtil.toJsonPrettyStr(jsonStr);
                }
            }
        } catch (Exception e) {
            log.error("formatMockDataJson error format json:{}",jsonStr,e);
        }
        return Convert.toStr(jsonStr);
    }

    /**
     * 存在类的源文件
     * @param paras
     * @param ctx
     * @return
     */
    public boolean existClassSource(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject classJsonObj =( JSONObject)paras;
            String fullClassName = classJsonObj.getStr("fullClassName","");
            if(StrUtil.isNotBlank(fullClassName)){
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                if(javaMetaFile != null){
                    JSONObject jsonObject = javaMetaFile.getJsonObject();
                    if(jsonObject.containsKey("fields")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 是否是枚举类
     * @param paras
     * @param ctx
     * @return
     */
    public boolean isEnumClass(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            String fullClassName = methodObj.getStr("fullClassName","");
            if(StrUtil.isNotBlank(fullClassName)){
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                if(javaMetaFile != null){
                    JSONObject jsonObject = javaMetaFile.getJsonObject();
                    return jsonObject.getBool("enumClass", false);
                }
            }
        }
        return false;
    }
    /**
     * 存在类的源文件
     * @param paras
     * @param ctx
     * @return
     */
    public JSONArray classFieldsJson(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            String fullClassName = methodObj.getStr("fullClassName","");
            if(StrUtil.isNotBlank(fullClassName)){
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                if(javaMetaFile != null){
                    JSONObject jsonObject = javaMetaFile.getJsonObject();
                    Boolean enumClass = jsonObject.getBool("enumClass", false);
                    if(enumClass){
                        return jsonObject.getJSONArray("enumConstants");
                    }else if(jsonObject.containsKey("fields")){
                        return jsonObject.getJSONArray("fields");

                    }
                }
            }
        }
        return null;
    }

    private String getClassTableMd( JSONObject jsonObject){
        StringBuilder result = new StringBuilder();
        String fullClassName = jsonObject.getStr("fullClassName","");
        result.append(fullClassName).append("\n\n");
        JSONArray fields = jsonObject.getJSONArray("fields");
        if(CollectionUtil.isNotEmpty(fields)){

            for (Object field : fields) {

            }
        }

        return result.toString();
    }
    /**
     * 计算markdown链接
     * @param paras
     * @param prex
     * @param uniqueName
     * @param ctx
     * @return
     */
    public String createInnerLinkIdMd(Object paras,String prex,String uniqueName, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof String){
            result.append("[");
            result.append(paras);
            result.append("](#");
            if(StrUtil.isNotBlank(uniqueName)){
                result.append(replaceSpecChars(prex+uniqueName));
            }else{
                result.append(replaceSpecChars(prex+paras));
            }
            result.append(")");
        }
        return result.toString();
    }

    /**
     * 计算markdown链接
     * @param content
     * @param ctx
     * @return
     */
    public String replaceHtmlArrow(String content, Context ctx) {
        if(StrUtil.isNotBlank(content)){
            return content.replaceAll("<","< ").replaceAll(">"," >");
        }
       return content;
    }
    /**
     * 计算markdown链接
     * @param paras
     * @param uniqueName
     * @param ctx
     * @return
     */
    public String createLinkIdMd(Object paras,String uniqueName, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof String){
            result.append("[");
            result.append(paras);
            result.append("](#");
            if(StrUtil.isNotBlank(uniqueName)){
                result.append(replaceSpecChars(uniqueName));
            }else{
                result.append(replaceSpecChars(paras.toString()));
            }
            result.append(")");
        }
        return result.toString();
    }

    public String createHrefIdHtml(Object paras,String prex,String uniqueName, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof String){
            result.append("<span id=\"");
            if(StrUtil.isNotBlank(uniqueName)){
                result.append(replaceSpecChars(prex+uniqueName));
            }else{
                result.append(replaceSpecChars(prex+paras));
            }
            result.append("\">");
            result.append(paras);
            result.append("</span>");
        }
        return result.toString();
    }

    public String createALinkHrefIdHtml(Object name,String prex,String uniqueName, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(StrUtil.equals("jdkClass",prex) || StrUtil.equals("outHttp",prex)){
            result.append("<a target='_blank' href=\"");
            result.append(uniqueName);
            result.append("\">");
            result.append(name);
            result.append("</a>");
        }else{
            if(name instanceof String){
                result.append("<a href=\"#");
                if(StrUtil.isNotBlank(uniqueName)){
                    result.append(prex+uniqueName);
                }else{
                    result.append(prex+name);
                }
                result.append("\">");
                result.append(name);
                result.append("</a>");
            }
        }
        return result.toString();
    }

    public String calUniqueMethodName(Object paras, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            result.append(methodObj.getStr("uniqueId"));
        }
        return result.toString();
    }

    public String catUniqueMethodParamName(List<String> params){
        if(CollectionUtil.isEmpty(params)){
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (String param : params) {
            result.append("-");
            result.append(param.toLowerCase());
        }
        return result.toString();
    }

    private String replaceSpecChars(String replaceStr){
        if(StrUtil.isNotBlank(replaceStr)){
            return StrUtil.replaceChars(replaceStr,"()<>,?","-").replace(".","").toLowerCase();
        }
        return "";
    }
    public String calReturnMd(Object paras, Context ctx) {
        StringBuilder returnStr = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject returnObj =( JSONObject)paras;
            returnStr.append(calTypeParametersMd(returnObj));
            returnStr.append(createClassNameLinkMd(returnObj));

            StringBuilder typeArgumentStr = new StringBuilder();
            createTypeArgMd(returnObj, typeArgumentStr);
            returnStr.append(typeArgumentStr);
        }
        returnStr.append(" ");
        return returnStr.toString();
    }

    /**
     * 计算字段类型
     * @param paras
     * @param ctx
     * @return
     */
    public String calFieldTypeMd(Object paras, Context ctx) {
        StringBuilder returnStr = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject fieldTypeObj =( JSONObject)paras;
            returnStr.append(createClassNameLinkMd(fieldTypeObj));

            StringBuilder typeArgumentStr = new StringBuilder();
            createTypeArgMd(fieldTypeObj, typeArgumentStr);
            returnStr.append(typeArgumentStr);
        }
        returnStr.append(" ");
        return returnStr.toString();
    }

    /**
     * 计算字段类型
     * @param paras
     * @param apiFieldTypeArgObj  api类型参数字段
     * @param ctx
     * @return
     */
    public String calApiFieldTypeMd(Object paras,Object apiFieldTypeArgObj, Context ctx) {
        StringBuilder returnStr = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject fieldTypeObj =( JSONObject)paras;
            returnStr.append(createClassNameLinkMd(fieldTypeObj));

            StringBuilder typeArgumentStr = new StringBuilder();
//            createTypeArgMd(fieldTypeObj, typeArgumentStr);
            createApiTypeArgMd(fieldTypeObj, typeArgumentStr,apiFieldTypeArgObj,0,true);
            returnStr.append(typeArgumentStr);

            StringBuilder apiFieldTypeArgJSONObjStr = new StringBuilder();
            createApiTypeParamMd(fieldTypeObj,apiFieldTypeArgObj, apiFieldTypeArgJSONObjStr);
            returnStr.append(apiFieldTypeArgJSONObjStr);
        }
        returnStr.append(" ");
        return returnStr.toString();
    }

    private void createApiTypeParamMd( JSONObject fieldTypeObj, Object apiFieldTypeArgObj,StringBuilder apiFieldTypeArgJSONObjStr) {
        Boolean typeParameter = fieldTypeObj.getBool("typeParameter", false);
        if(typeParameter &&  apiFieldTypeArgObj instanceof JSONObject){
            JSONObject apiFieldTypeArgJSONObj =( JSONObject) apiFieldTypeArgObj;
            apiFieldTypeArgJSONObjStr.append(":");
            createApiTypeArgMd(apiFieldTypeArgJSONObj, apiFieldTypeArgJSONObjStr,apiFieldTypeArgObj,0,false);
        }
    }

    private void createApiTypeArgMd(JSONObject javaClass, StringBuilder typeArgumentStr,Object apiFieldTypeArgObj,int count,boolean appendClose) {
        if(javaClass.containsKey("typeArguments")){
            JSONArray typeArguments = javaClass.getJSONArray("typeArguments");
            if(appendClose ){
                typeArgumentStr.append("< ");
            }else if(count > 0){
                typeArgumentStr.append("< ");
            }
            StringBuilder typeArgumentObjStr = new StringBuilder();
            for (Object typeArgument : typeArguments) {
                typeArgumentObjStr.append(",");
                JSONObject typeJsonObj = (JSONObject)typeArgument;
                typeArgumentObjStr.append(createClassNameLinkMd(typeJsonObj));
                createApiTypeArgMd(typeJsonObj,typeArgumentObjStr,apiFieldTypeArgObj,count+1,appendClose);

                StringBuilder apiFieldTypeArgJSONObjStr = new StringBuilder();
                createApiTypeParamMd(typeJsonObj,apiFieldTypeArgObj, apiFieldTypeArgJSONObjStr);

                typeArgumentObjStr.append(apiFieldTypeArgJSONObjStr);
            }
            typeArgumentStr.append(typeArgumentObjStr.substring(1));
            if(appendClose){
                typeArgumentStr.append(" >");
            }else if(count > 0){
                typeArgumentStr.append(" >");
            }

        }
    }

    private String calUniqueParam(Object paras, Context ctx) {
        StringBuilder resultStr = new StringBuilder();
        if(paras instanceof JSONArray){
            JSONArray parameter =( JSONArray)paras;
            if(parameter.size()>0){
                for (Object obj : parameter) {
                    resultStr.append("-");
                    JSONObject param = (JSONObject)obj;
                    JSONObject javaClass = param.getJSONObject("javaClass");
                    resultStr.append(calModifer(javaClass));
                    resultStr.append(createClassNameLinkMd(javaClass));
                    resultStr.append(javaClass.getStr("fullClassName"));
                }

            }else {
                resultStr.append("-");
            }

        }else {
            resultStr.append("-");
        }
        return resultStr.toString();
    }
    public String calParamMd(Object paras, Context ctx) {
        StringBuilder resultStr = new StringBuilder();
        if(paras instanceof JSONArray){
            JSONArray parameter =( JSONArray)paras;
            if(parameter.size()>0){
                resultStr.append("(");
                StringBuilder paramStr = new StringBuilder();
                for (Object obj : parameter) {
                    JSONObject param = (JSONObject)obj;
                    paramStr.append(",");
                    JSONObject javaClass = param.getJSONObject("javaClass");
                    paramStr.append(calModifer(javaClass));
                    paramStr.append(createClassNameLinkMd(javaClass));
                    StringBuilder typeArgumentStr = new StringBuilder();
                    createTypeArgMd(javaClass, typeArgumentStr);
                    paramStr.append(typeArgumentStr);
                    if(param.getBool("varArgs")){
                        paramStr.append("...");
                    }
                    paramStr.append(" ");
                    paramStr.append(param.getStr("name"));
                }
                resultStr.append(paramStr.substring(1));
                resultStr.append(")");
            }else {
                resultStr.append("()");
            }

        }else {
            resultStr.append("()");
        }
        resultStr.append(" ");
        return resultStr.toString();
    }


    public String calExceptionMd(Object paras, Context ctx) {
        StringBuilder resultStr = new StringBuilder();
        if(paras instanceof JSONArray){
            JSONArray throwExe =( JSONArray)paras;
            if(throwExe.size()>0){
                resultStr.append("throws ");
                StringBuilder paramStr = new StringBuilder();
                for (Object obj : throwExe) {
                    JSONObject param = (JSONObject)obj;
                    paramStr.append(",");
                    paramStr.append(createClassNameLinkMd(param));
                    paramStr.append(" ");
                }
                resultStr.append(paramStr.substring(1));
            }

        }
        resultStr.append(" ");
        return resultStr.toString();
    }

    private void createTypeArgMd(JSONObject javaClass, StringBuilder typeArgumentStr) {
        if(javaClass.containsKey("typeArguments")){
            JSONArray typeArguments = javaClass.getJSONArray("typeArguments");
            typeArgumentStr.append("< ");
            StringBuilder typeArgumentObjStr = new StringBuilder();
            for (Object typeArgument : typeArguments) {
                typeArgumentObjStr.append(",");
                JSONObject typeJsonObj = (JSONObject)typeArgument;
                typeArgumentObjStr.append(createClassNameLinkMd(typeJsonObj));
                createTypeArgMd(typeJsonObj,typeArgumentObjStr);
            }
            typeArgumentStr.append(typeArgumentObjStr.substring(1));
            typeArgumentStr.append(" >");

        }
    }



    private String calModifer(JSONObject javaClass) {
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

    private String calTypeParametersMd(JSONObject javaClass) {
        String result = calTypeParameters(javaClass);
        if(StrUtil.isNotBlank(result)){
            return ""+result;
        }
        return "";
    }
    private String calTypeParameters(JSONObject javaClass) {
        StringBuilder result = new StringBuilder();
        if(javaClass.containsKey("typeParameters")){
            JSONArray typeParametersArr =javaClass.getJSONArray("typeParameters");
            if(typeParametersArr.size() > 0){
                result.append("< ");
                StringBuilder typeParametersStr = new StringBuilder();
                for (Object typeParametersObj : typeParametersArr) {
                    JSONObject typeParameters = (JSONObject)typeParametersObj;
                    typeParametersStr.append(",");
                    typeParametersStr.append(typeParameters.getStr("name"));
                    if(typeParameters.containsKey("type")){
                        JSONObject type = typeParameters.getJSONObject("type");
                        typeParametersStr.append(" extends ");
                        typeParametersStr.append(createClassNameLinkMd(type));
                    }
                }
                result.append(typeParametersStr.substring(1));
                result.append(" > ");
            }
        }
        return result.toString();
    }

    private  String createClassNameLinkMd(JSONObject classJson) {
        StringBuilder result = new StringBuilder();
        String className = classJson.getStr("className");
        if(classJson.containsKey("typeArgExtend") && className.contains("?")){
            result.append("? extends ");
            JSONObject typeArgExtend = classJson.getJSONObject("typeArgExtend");
            result.append(innerCreateClassNameLinkMd(typeArgExtend));
        }else{
            result.append(innerCreateClassNameLinkMd(classJson));
        }
        return result.toString();
    }

    private  String innerCreateClassNameLinkMd(JSONObject classJson) {
        StringBuilder result = new StringBuilder();
        if(classJson.containsKey("dependencyRelativePath")){
            result.append("[");
            result.append(classJson.getStr("className"));
            result.append("](");
            result.append(classJson.getStr("dependencyRelativePath"));
            result.append(".md");
            result.append(")");
        }else  if(classJson.getBool("typeParameter",false)){
            result.append(classJson.getStr("className"));
        }else{
            String fullClassName = classJson.getStr("fullClassName");

            String className = classJson.getStr("className");
            result.append(DocParseUtil.parseJdkClassLink(className,fullClassName));
        }
        return result.toString();
    }


}
