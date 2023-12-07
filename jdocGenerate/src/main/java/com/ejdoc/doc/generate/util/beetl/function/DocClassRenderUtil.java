package com.ejdoc.doc.generate.util.beetl.function;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.tagtype.TagTypeSerialize;
import com.ejdoc.doc.generate.tagtype.TagTypeSerializeFactory;
import com.ejdoc.doc.generate.tagtype.dto.TagTypeSerializeRootDocDto;
import com.ejdoc.doc.generate.template.markdown.theme.JavaDocDocsifyThemeDto;
import com.ejdoc.doc.generate.util.DocParseUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocTagTypeEnum;
import com.ejdoc.metainfo.seralize.util.EjdocStrUtil;
import org.beetl.core.Context;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public String calSimpleClassNameStructure(Object paras, Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject classObj =( JSONObject)paras;
            result.append(classObj.getStr("className"));
            StringBuilder typeArgumentStr = new StringBuilder();
            createTypeParametersMd(classObj,typeArgumentStr);
            result.append(typeArgumentStr);
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
                int i = 1;
                for (Object allClass : allClasses) {
                    JSONObject allClassObj =( JSONObject)allClass;
                    innerLoopSb.append(", &nbsp;");
                    innerLoopSb.append(createClassNameLinkMd(allClassObj));
                    StringBuilder typeArgumentStr = new StringBuilder();
                    createTypeParametersMd(allClassObj,typeArgumentStr);
                    innerLoopSb.append(typeArgumentStr);

                    if((i++)%5 == 0 ){
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

    public String calFunctionInterfaceInfoMd(Object paras, String appendBefore,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("annotations")){
                    JSONArray annotations = javaModelMeta.getJSONArray("annotations");
                    for (Object annotation : annotations) {
                        JSONObject jsonObject = (JSONObject) annotation;
                        String name = jsonObject.getStr("name", "");
                        if("FunctionalInterface".equals(name)){
                            if(StrUtil.isNotBlank(appendBefore)){
                                result.append(appendBefore);
                            }
                            result.append("这是一个函数接口，因此可以用作lambda表达式或方法引用的赋值目标。");
                            result.append("<br/>");
                            result.append("This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.");
                        }
                    }
                }
            }
        }

        return result.toString();
    }

    public String calLinkNestedRefClassNameMd(Object paras,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONObject){
            JSONObject allClasses =(JSONObject)paras;
            String nestedClassFullClassName = allClasses.getStr("nestedClassFullClassName", "");
            String nestedClassName = allClasses.getStr("nestedClassName", "");
            String classNamePrefix = nestedClassFullClassName.replace(nestedClassName,"");
            result.append(classNamePrefix);
            result.append(createNestedRefClassNameLinkMd(allClasses));
        }
        return result.toString();
    }

    public  String createNestedRefClassNameLinkMd(JSONObject classJson) {
        StringBuilder result = new StringBuilder();
        if(classJson.containsKey("dependencyRelativePath")){
            result.append("[");
            result.append(classJson.getStr("nestedClassName"));
            result.append("](");
            result.append(classJson.getStr("dependencyRelativePath"));
            result.append(".md");
            result.append(")");
        }else{

            String fullClassName = classJson.getStr("nestedClassFullClassName");
            String className = classJson.getStr("nestedClassName");
            result.append(DocParseUtil.parseJdkClassLink(className,fullClassName));

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
                    innerLoopSb.append(", &nbsp;&nbsp;");
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
     * 计算所有Jdk父类的方法明细，生成markdown结构
     * @param paras
     * @param ctx
     * @return
     */
    public String calAllJdkClassesDetailMethodMd(Object paras,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JSONObject){
            JSONObject classJson =(JSONObject)paras;
            String fullClassName = classJson.getStr("fullClassName");
            String className = classJson.getStr("className");
            List<String> allJdkMethods = DocParseUtil.parseJdkClassMethodMd(fullClassName);
            if(CollectionUtil.isNotEmpty(allJdkMethods)){
                int i = 1;
                for (String allJdkMethod : allJdkMethods) {
                    if((i++)%10==0){
                        result.append("\n");
                    }
                    result.append(allJdkMethod).append(", &nbsp;&nbsp;");
                }
            }else{
                result.append("无方法信息");
            }
        }
        return result.toString();
    }

    public String calAllClassesHierarchyMd(Object paras,Context ctx){
        StringBuilder result = new StringBuilder();
        if(paras != null && paras instanceof JavaDocDocsifyThemeDto){
            JavaDocDocsifyThemeDto javaDocDocsifyThemeDto = (JavaDocDocsifyThemeDto)paras;
            calClassesHierarchyMd(javaDocDocsifyThemeDto,result);
        }
        return result.toString();
    }

    private void calClassesHierarchyMd(JavaDocDocsifyThemeDto javaDocDocsifyThemeDto,StringBuilder result){
        StringBuilder blankSb = new StringBuilder();
        int hierarchy = javaDocDocsifyThemeDto.getHierarchy();
        String fullClassName = javaDocDocsifyThemeDto.getFullClassName();
        for (int i = 1; i < hierarchy; i++) {
            blankSb.append("|....");
        }
        if(hierarchy > 1){
            blankSb.append("└─");
        }
        result.append(blankSb);
        if("java.lang.Object".equals(fullClassName) || "java.lang.Enum".equals(fullClassName)
                || "java.lang.Exception".equals(fullClassName) || "java.lang.RuntimeException".equals(fullClassName)
                || "java.lang.Throwable".equals(fullClassName) ){
            result.append("["+hierarchy+"]");
            result.append(fullClassName);
        }else{
            String classFilePath = javaDocDocsifyThemeDto.getModuleName()+"/"+ javaDocDocsifyThemeDto.getPackageNamePath()+"/"+javaDocDocsifyThemeDto.getClassName()+".md";
            result.append("["+hierarchy+"]");
            result.append(javaDocDocsifyThemeDto.getPackageName()+".");
            result.append(createCommonLinkMd(javaDocDocsifyThemeDto.getClassName(),classFilePath));

            List<JavaDocDocsifyThemeDto> interfaceList = javaDocDocsifyThemeDto.getInterfaceList();
            if(CollectionUtil.isNotEmpty(interfaceList)){
                StringBuilder interfaceSb = new StringBuilder();
                interfaceSb.append("( implements ");
                int i = 1;
                for (JavaDocDocsifyThemeDto docDocsifyThemeDto : interfaceList) {
                    if(i++ > 1){
                        interfaceSb.append(",");
                    }
                    String interfaceClassFilePath = docDocsifyThemeDto.getModuleName()+"/"+ docDocsifyThemeDto.getPackageNamePath()+"/"+docDocsifyThemeDto.getClassName()+".md";
                    interfaceSb.append(createCommonLinkMd(docDocsifyThemeDto.getClassName(),interfaceClassFilePath));
                }
                interfaceSb.append(")");
                result.append(interfaceSb);
            }
        }
        result.append("  \n");
        if(CollectionUtil.isNotEmpty(javaDocDocsifyThemeDto.getChildList())){
            for (JavaDocDocsifyThemeDto docDocsifyThemeDto : javaDocDocsifyThemeDto.getChildList()) {
                calClassesHierarchyMd(docDocsifyThemeDto,result);
            }
        }
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
            typeArgumentStr.append("< ");
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
            typeArgumentStr.append(" >");
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

            String fullClassName = classJson.getStr("fullClassName");
            String className = classJson.getStr("className");

            Boolean nestedClass = classJson.getBool("nestedClass",false);
            if(nestedClass){
                String filePath = "";
                String nestedClassFullClassName = classJson.getStr("nestedClassFullClassName");
                if(StrUtil.isNotBlank(nestedClassFullClassName)){
                    filePath = nestedClassFullClassName.replace(".", "/");
                }
                filePath = filePath +"."+className;
                result.append(DocParseUtil.parseJdkClassLink(className,fullClassName,filePath));
            }else{
                result.append(DocParseUtil.parseJdkClassLink(className,fullClassName));
            }



        }
        return result.toString();
    }

    public  String createNestedClassNameLinkMd(JSONObject classJson) {
        StringBuilder result = new StringBuilder();
        result.append("[");
//        result.append(classJson.getStr("nestedClassName"));
//        result.append(".");
        result.append(classJson.getStr("className"));
        result.append("](");
        String moduleName = classJson.getStr("moduleName");
        String packageName = classJson.getStr("packageName");
        String className = classJson.getStr("className");
        result.append(StrUtil.join("/",moduleName, packageName.replace(".","/"), className));
        result.append(".md");
        result.append(")");
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
        String result = "";
        if(content.length() > 0){
            result = EjdocStrUtil.getFirstComment(content);
        }
        return result.replace("\n","");
    }

    /**
     * 注解类型是否有默认值
     * @param paras
     * @param ctx
     * @return
     */
    public String calAnnoHaveDefaultVal(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject methodJsonObj =( JSONObject)paras;
            if(methodJsonObj.containsKey("defaultValue")){
                return "否";
            }
        }
        return "是";
    }

    public String calCommentDocMd(Object paras,Object propObj,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject && propObj instanceof JSONObject) {
            JSONObject rootJsonObj = (JSONObject) propObj;
            Optional<JSONArray> javaDocCommentOptional = getDocCommentElements(paras);
            if(javaDocCommentOptional.isPresent()){
                result.append(DocParseUtil.parseCommentMd(javaDocCommentOptional.get(),rootJsonObj));
                result.append("\n");
            }
        }
        if(result.length() > 0 ){
            return appendBefore + result;
        }
        return "";
    }
    public String calDeprecatedCommentDocMd(Object paras,Object propObj,String appendBefore, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject && propObj instanceof JSONObject) {
            JSONObject rootJsonObj = (JSONObject) propObj;
            JSONObject jsonObject = (JSONObject) paras;
            if(jsonObject.containsKey("deprecateTag")){
                JSONObject deprecateTagJsonObj = jsonObject.getJSONObject("deprecateTag");
                if(deprecateTagJsonObj.containsKey("values")){
                    JSONArray javaDocCommentElementMetas = deprecateTagJsonObj.getJSONArray("values");
                    Optional<JSONArray> javaDocCommentOptional = Optional.ofNullable(javaDocCommentElementMetas);
                    if(javaDocCommentOptional.isPresent()){
                        result.append(DocParseUtil.parseCommentMd(javaDocCommentOptional.get(),rootJsonObj));
                    }
                }
            }
            if(StrUtil.isBlank(result)){
                result.append(jsonObject.getStr("deprecatedDesc",""));
            }
        }
        if(result.length() > 0 ){
            return appendBefore + result;
        }
        return "";
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


                    Map<String, TagTypeSerialize> mdTagTypeSerializeMap = TagTypeSerializeFactory.createMdTagTypeSerializeMap();

                    JSONArray tags = javaModelMeta.getJSONArray("tags");
                    StringBuilder tagSb = new StringBuilder();
                    for (Object tag : tags) {
                        if(tag instanceof JSONObject){
                            JSONObject tagJsonObj = (JSONObject)tag;

                            String tagType = tagJsonObj.getStr("type", "");
                            if(!type.equals(tagType)){
                                continue;
                            }
                            TagTypeSerialize tagTypeSerialize = mdTagTypeSerializeMap.get(tagType);
                            if(tagTypeSerialize == null){
                                tagTypeSerialize = mdTagTypeSerializeMap.get(JavaDocTagTypeEnum.DEFAULT.getName());
                            }
                            if(tagTypeSerialize != null){
                                TagTypeSerializeRootDocDto tagTypeSerializeRootDocDto = new TagTypeSerializeRootDocDto();
                                tagTypeSerializeRootDocDto.setRootPropObj(rootPropObj);
                                tagTypeSerializeRootDocDto.setTagJsonObj(tagJsonObj);
                                String tagVal =tagTypeSerialize.toSerialize(type,tagTypeSerializeRootDocDto);
                                if(StrUtil.isNotBlank(tagVal)){
                                    tagSb.append(tagVal);
                                    tagSb.append("\n\n");
                                }
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


    /**
     * 是否有Deprecated注解
     * @param paras
     * @param ctx
     * @return
     */
    public boolean calIsDeprecated(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("annotations")){
                    JSONArray annotations = javaModelMeta.getJSONArray("annotations");
                    for (Object annotation : annotations) {
                        JSONObject jsonObject = (JSONObject) annotation;
                        String name = jsonObject.getStr("name", "");
                        if("Deprecated".equals(name)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * 是否包含元注解和自定义注解
     * @param paras
     * @param ctx
     * @return
     */
    public boolean calIncludeMetaAndCustomAnnotation(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("annotations")){
                    JSONArray annotations = javaModelMeta.getJSONArray("annotations");
                    for (Object annotation : annotations) {
                        JSONObject jsonObject = (JSONObject) annotation;
                        JSONObject typeJsonObj = jsonObject.getJSONObject("type");
                        if(typeJsonObj != null){
                            String className = typeJsonObj.getStr("className");
                            String fullClassName = typeJsonObj.getStr("fullClassName");
                            String metaAnno = StrUtil.join("", "java.lang.annotation", className);
                            //标准注解
                            String builtInAnno = StrUtil.join("", "java.lang.", className);
                            if(metaAnno.equals(fullClassName)){
                                return true;
                            }
                            //自定义注解
                            if(!builtInAnno.equals(fullClassName)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String printMetaAndCustomAnnotation(Object paras, Context ctx) {
        StringBuilder result = new StringBuilder();
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("annotations")){
                    JSONArray annotations = javaModelMeta.getJSONArray("annotations");
                    for (Object annotation : annotations) {
                        JSONObject jsonObject = (JSONObject) annotation;
                        JSONObject typeJsonObj = jsonObject.getJSONObject("type");
                        if(typeJsonObj != null){
                            JSONObject propertiesJsonObj = jsonObject.getJSONObject("properties");
                            String className = typeJsonObj.getStr("className");
                            String fullClassName = typeJsonObj.getStr("fullClassName");
                            //标准注解
                            String builtInAnno = StrUtil.join("", "java.lang.", className);
                            //自定义注解
                            if(!builtInAnno.equals(fullClassName)){
                                result.append("@").append(className);
                                if(propertiesJsonObj.containsKey("value")){
                                    result.append("(value=");
                                    Object value = propertiesJsonObj.get("value");
                                    if(value instanceof JSONArray){
                                        StringBuilder inner = new StringBuilder();
                                        JSONArray vals = (JSONArray)value;
                                        for (Object val : vals) {
                                            inner.append(",");
                                            inner.append(val.toString());
                                        }
                                        result.append("{");
                                        result.append(inner.substring(1));
                                        result.append("}");
                                    }else{
                                        result.append(value.toString());
                                    }
                                    result.append(")");
                                }
                                result.append("<br/>");
                            }
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    public boolean calIsFunctionalInterface(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("annotations")){
                    JSONArray annotations = javaModelMeta.getJSONArray("annotations");
                    for (Object annotation : annotations) {
                        JSONObject jsonObject = (JSONObject) annotation;
                        String name = jsonObject.getStr("name", "");
                        if("FunctionalInterface".equals(name)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String printDeprecatedDesc(Object paras, Context ctx) {
        if(paras instanceof JSONObject){
            JSONObject deprecatedJsonObj =( JSONObject)paras;
            if(deprecatedJsonObj.containsKey("javaModelMeta")){
                JSONObject javaModelMeta = deprecatedJsonObj.getJSONObject("javaModelMeta");
                if(javaModelMeta.containsKey("tags")){
                    JSONArray tags = javaModelMeta.getJSONArray("tags");
                    for (Object tagObj : tags) {
                        JSONObject jsonObject = (JSONObject) tagObj;
                        String type = jsonObject.getStr("type", "");
                        if("DEPRECATED".equals(type)){
                            return jsonObject.getStr("value","");
                        }
                    }
                }
            }

        }
        return "";
    }
}
