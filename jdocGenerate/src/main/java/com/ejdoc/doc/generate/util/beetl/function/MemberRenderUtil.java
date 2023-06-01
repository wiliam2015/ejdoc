package com.ejdoc.doc.generate.util.beetl.function;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.beetl.core.Context;

public class MemberRenderUtil {

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


    public String calUniqueMethodName(Object paras, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject methodObj =( JSONObject)paras;
            result.append(methodObj.getStr("name"));
            JSONArray parameters = methodObj.getJSONArray("parameters");
            if(parameters != null && parameters.size()>0){
                for (Object obj : parameters) {
                    result.append("-");
                    JSONObject param = (JSONObject)obj;
                    JSONObject javaClass = param.getJSONObject("javaClass");
                    String fullClassName = javaClass.getStr("fullClassName");
                    fullClassName = fullClassName.replace(" ","");
                    result.append(replaceSpecChars(fullClassName));

                }
            }
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
                createTypeArgMd(typeJsonObj,typeArgumentStr);
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
                for (Object typeParameters : typeParametersArr) {
                    typeParametersStr.append(",");
                    typeParametersStr.append(typeParameters.toString());
                }
                result.append(typeParametersStr.substring(1));
                result.append(" > ");
            }
        }
        return result.toString();
    }

    private  String createClassNameLinkMd(JSONObject classJson) {
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



}
