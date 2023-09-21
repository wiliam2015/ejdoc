package com.ejdoc.doc.generate.tagtype.md.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.tagtype.TagTypeSerialize;
import com.ejdoc.doc.generate.tagtype.dto.SeeTagTypeDto;
import com.ejdoc.doc.generate.tagtype.dto.TagTypeSerializeRootDocDto;
import com.ejdoc.doc.generate.util.beetl.function.MemberRenderUtil;
import com.ejdoc.metainfo.seralize.enums.JavaDocTagTypeEnum;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassImportMeta;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeeTagTypeSerialize  implements TagTypeSerialize {

    private static final Logger log = LoggerFactory.getLogger(SeeTagTypeSerialize.class);
    private MemberRenderUtil memberRenderUtil = new MemberRenderUtil();
    @Override
    public String acceptType() {
        return JavaDocTagTypeEnum.SEE.getName();
    }

    @Override
    public boolean accept(String type) {
        return JavaDocTagTypeEnum.SEE.getName().equals(type);
    }

    @Override
    public String toSerialize(String type, TagTypeSerializeRootDocDto serializeRootDocDto) {
        return toSerialize1(type,serializeRootDocDto);
    }

    public String toSerialize1(String type, TagTypeSerializeRootDocDto serializeRootDocDto) {
        JSONObject rootPropObj = serializeRootDocDto.getRootPropObj();
        JSONObject tagJsonObj = serializeRootDocDto.getTagJsonObj();
        StringBuilder tagSb = new StringBuilder();
        String fullClassName = rootPropObj.getStr("fullClassName", "");

        String name = tagJsonObj.getStr("name", "");
        String  value = tagJsonObj.getStr("value", "");
        tagSb.append("  ");
        if(StrUtil.isNotBlank(name)){
            tagSb.append(name);
            tagSb.append(" - ");
        }
        tagSb.append(paraseSeeTagVal(value,fullClassName));
        return tagSb.toString();

    }
    public String paraseSeeTagVal(String content, String fullClassName) {
        if(StrUtil.isBlank(content)){
            return "";
        }
        content=StrUtil.trim(content);
        SeeTagTypeDto seeTagTypeDto = parseSeeTagTypeDto(content);
        processSeeTagTypeDto(seeTagTypeDto,fullClassName);
        content = parseSeeTagContent(seeTagTypeDto,content);
        return content;
    }

    private String parseSeeTagContent(SeeTagTypeDto seeTagTypeDto, String content) {
        String uniqueName ="";
        if(seeTagTypeDto.isOnlyClassLink()){
            if(seeTagTypeDto.getLinkRefJavaClassMeta() != null){
                uniqueName = StrUtil.join("/", seeTagTypeDto.getLinkRefJavaClassMeta().getModuleName(), seeTagTypeDto.getLinkRefJavaClassMeta().getFullClassName().replace(".","/"));
            }
        }else if(seeTagTypeDto.isClassLink()){
            if(seeTagTypeDto.getLinkRefJavaClassMeta() != null){
                String preName = StrUtil.join("/", seeTagTypeDto.getLinkRefJavaClassMeta().getModuleName(), seeTagTypeDto.getLinkRefJavaClassMeta().getFullClassName().replace(".","/"));
                String idLink = "?id=";
                if(seeTagTypeDto.isMethodLink()){
                    idLink +=seeTagTypeDto.getMethodName();
                    if(CollectionUtil.isNotEmpty(seeTagTypeDto.getParams())){
                        idLink+= memberRenderUtil.catUniqueMethodParamName(seeTagTypeDto.getParams());
                    }
                }else if(seeTagTypeDto.isFieldLink()){
                    idLink+=seeTagTypeDto.getFieldName();
                }
                uniqueName = preName+idLink.toLowerCase();
            }
        }else if(seeTagTypeDto.isMethodLink()){
            JavaClassMeta linkJavaClassMeta = seeTagTypeDto.getLinkJavaClassMeta();
            if(linkJavaClassMeta != null){
                String preName = StrUtil.join("/", linkJavaClassMeta.getModuleName(), linkJavaClassMeta.getFullClassName().replace(".","/"));
                String idLink = "?id=";
                String methodUniqueId = seeTagTypeDto.getMethodName();
                if(CollectionUtil.isNotEmpty(seeTagTypeDto.getParams())){
                    methodUniqueId += memberRenderUtil.catUniqueMethodParamName(seeTagTypeDto.getParams());
                }
                Map<String, String> methodMetaIndex = linkJavaClassMeta.getMethodMetaIndex();
                if(methodMetaIndex != null && methodMetaIndex.containsKey(methodUniqueId.toLowerCase())){
                }else{
                    List<JavaClassMeta> allSupperClassAndInterface = getAllSupperClassInterface(linkJavaClassMeta);
                    if(CollectionUtil.isNotEmpty(allSupperClassAndInterface)){
                        for (JavaClassMeta supperClass : allSupperClassAndInterface) {
                            JavaClassMeta supperClassFullInfo = MetaIndexContext.getClassMetaByFullName(supperClass.getFullClassName());
                            if(supperClassFullInfo != null){
                                Map<String, String> supperMethodMetaIndex = supperClassFullInfo.getMethodMetaIndex();
                                if(supperMethodMetaIndex != null && supperMethodMetaIndex.containsKey(methodUniqueId.toLowerCase())){
                                    preName = StrUtil.join("/", supperClassFullInfo.getModuleName(), supperClassFullInfo.getFullClassName().replace(".","/"));
                                    if(!seeTagTypeDto.isUseCustomLableName()){
                                        seeTagTypeDto.setLabelName(supperClassFullInfo.getClassName()+"."+seeTagTypeDto.getLabelName());
                                    }

                                }
                            }
                        }
                    }
                }

                idLink+=methodUniqueId;
                uniqueName = preName+idLink.toLowerCase();
            }
        }else if(seeTagTypeDto.isFieldLink()){
            JavaClassMeta linkJavaClassMeta = seeTagTypeDto.getLinkJavaClassMeta();
            if(linkJavaClassMeta != null){
                String preName = StrUtil.join("/", linkJavaClassMeta.getModuleName(), linkJavaClassMeta.getFullClassName().replace(".","/"));
                String idLink = "?id=";
                String fieldUniqueId = seeTagTypeDto.getFieldName();
                Map<String, String> fieldMetaIndex = linkJavaClassMeta.getFieldMetaIndex();
                if(fieldMetaIndex != null && fieldMetaIndex.containsKey(fieldUniqueId)){
                }else{
                    List<JavaClassMeta> allSupperClassAndInterface = getAllSupperClassInterface(linkJavaClassMeta);
                    if(CollectionUtil.isNotEmpty(allSupperClassAndInterface)){
                        for (JavaClassMeta supperClass : allSupperClassAndInterface) {
                            JavaClassMeta supperClassFullInfo = MetaIndexContext.getClassMetaByFullName(supperClass.getFullClassName());
                            if(supperClassFullInfo != null){
                                Map<String, String> supperMethodMetaIndex = supperClassFullInfo.getFieldMetaIndex();
                                if(supperMethodMetaIndex != null && supperMethodMetaIndex.containsKey(fieldUniqueId.toLowerCase())){
                                    preName = StrUtil.join("/", supperClassFullInfo.getModuleName(), supperClassFullInfo.getFullClassName().replace(".","/"));
                                    if(!seeTagTypeDto.isUseCustomLableName()){
                                        seeTagTypeDto.setLabelName(supperClassFullInfo.getClassName()+"."+seeTagTypeDto.getLabelName());
                                    }
                                }
                            }
                        }
                    }
                }

                idLink+=fieldUniqueId;
                uniqueName = preName+idLink.toLowerCase();
            }
        }
        if(StrUtil.isNotBlank(uniqueName)){
            content = memberRenderUtil.createALinkHrefIdHtml(seeTagTypeDto.getLabelName(),"",uniqueName,null);
        }
        return content;
    }
    /**
     * 获取所有的父类和接口类
     * @param linkJavaClassMeta
     * @return
     */
    private  List<JavaClassMeta> getAllSupperClassInterface(JavaClassMeta linkJavaClassMeta) {
        TreeIndexClassMeta treeIndexClass = MetaIndexContext.getTreeIndexClassMetaByFullName(linkJavaClassMeta.getFullClassName());
        List<JavaClassMeta> allSupperClassAndInterface = new ArrayList<>();
        if(treeIndexClass != null && CollectionUtil.isNotEmpty(treeIndexClass.getAllSupperClasses())){
            allSupperClassAndInterface.addAll(treeIndexClass.getAllSupperClasses());
        }
        if(treeIndexClass != null && CollectionUtil.isNotEmpty(treeIndexClass.getAllInterfaceClasses())){
            allSupperClassAndInterface.addAll(treeIndexClass.getAllInterfaceClasses());
        }
        return allSupperClassAndInterface;
    }

    private void processSeeTagTypeDto(SeeTagTypeDto seeTagTypeDto, String fullClassName) {
        List<String> imports = new ArrayList<>();

        JavaClassMeta classMeta = MetaIndexContext.getClassMetaByFullName(fullClassName);
        if(classMeta != null){
            seeTagTypeDto.setLinkJavaClassMeta(classMeta);
            List<JavaClassMeta> classMetaByPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
            if(CollectionUtil.isNotEmpty(classMetaByPackage)){
                for (JavaClassMeta javaClassMeta : classMetaByPackage) {
                    imports.add(javaClassMeta.getFullClassName());
                }
            }
            List<JavaClassImportMeta> importsClass = classMeta.getImports();
            //导入子包引入
            for (JavaClassImportMeta importInfo : importsClass) {
                if(!importInfo.getName().startsWith("java")){
                    if(importInfo.isAsteriskImport() && !importInfo.isStaticImport()){
                        List<JavaClassMeta> packageClassList = MetaIndexContext.getClassMetaByPackage(importInfo.getName());
                        if(CollectionUtil.isNotEmpty(packageClassList)){
                            for (JavaClassMeta javaClassMeta : packageClassList) {
                                imports.add(javaClassMeta.getFullClassName());
                            }
                        }
                    }else{
                        imports.add(importInfo.getName());
                    }
                }
            }
        }

        seeTagTypeDto.setImports(imports);

        if(seeTagTypeDto.isClassLink()){
            if(seeTagTypeDto.isFullClassPathLink() && !seeTagTypeDto.getFullName().startsWith("java")){
                JavaClassMeta refClassMeta = MetaIndexContext.getClassMetaByFullName(seeTagTypeDto.getFullName());
                seeTagTypeDto.setLinkRefJavaClassMeta(refClassMeta);
            }else if(classMeta != null && !seeTagTypeDto.getFullName().startsWith("java")){
                List<JavaClassMeta> currentPackage = MetaIndexContext.getClassMetaByPackage(classMeta.getPackageName());
                if(CollectionUtil.isNotEmpty(currentPackage)){
                    for (JavaClassMeta javaClassMeta : currentPackage) {
                        if(seeTagTypeDto.getFullName().equals(javaClassMeta.getClassName())){
                            seeTagTypeDto.setLinkRefJavaClassMeta(javaClassMeta);
                        }
                    }
                }
            }
        }
    }

    /**
     * 解析see标签语法树
     * @param content
     * @return
     */
    private SeeTagTypeDto parseSeeTagTypeDto(String content) {
        SeeTagTypeDto seeTagTypeDto = new SeeTagTypeDto();
        String fullName="";
        String packageName="";
        String memberName="";
        String labelName=content;
        String methodName="";
        String fieldName="";
        List<String> params = new ArrayList<>();
        if(content.startsWith("#")){
            memberName = content.substring(1);
            labelName = memberName;
        }else if(content.contains("#")){
            String[] split = content.split("#");
            fullName =split[0];
            memberName =split[1];
            seeTagTypeDto.setClassLink(true);
            if(fullName.contains(".")){
                seeTagTypeDto.setFullClassPathLink(true);
            }
        }else{
            fullName = content;
            seeTagTypeDto.setClassLink(true);
            seeTagTypeDto.setOnlyClassLink(true);
            if(fullName.contains(".")){
                seeTagTypeDto.setFullClassPathLink(true);
            }
        }

        if(StrUtil.isNotBlank(memberName)){
            if(memberName.contains("(")){
                seeTagTypeDto.setMethodLink(true);
                int start = memberName.indexOf("(");
                int end = memberName.indexOf(")");
                String paramStr = null;
                try {
                    paramStr = memberName.substring(start+1, end).trim();
                } catch (StringIndexOutOfBoundsException e) {
                    log.error("CommentSerializeLinkImpl index out of bounds content:{}",content,e);
                }
                if(StrUtil.isNotBlank(paramStr)){
                    String[] paramArr = paramStr.split(",");
                    if(paramArr != null && paramArr.length > 0){
                        for (String param : paramArr) {
                            params.add(param.trim());
                        }
                    }
                }
                methodName = memberName.substring(0,memberName.indexOf("("));
                if(end+1 < memberName.length()){
                    labelName = memberName.substring(end+1).trim();
                    seeTagTypeDto.setUseCustomLableName(true);
                }

            }else{
                String[] split = memberName.split("\\s+");
                fieldName = split[0];
                if(split.length > 1){
                    labelName = split[1];
                    seeTagTypeDto.setUseCustomLableName(true);
                }
                seeTagTypeDto.setFieldLink(true);
            }
        }

        seeTagTypeDto.setFullName(fullName);
        seeTagTypeDto.setPackageName(packageName);
        seeTagTypeDto.setMemberName(memberName);
        seeTagTypeDto.setLabelName(labelName);
        seeTagTypeDto.setMethodName(methodName);
        seeTagTypeDto.setParams(params);
        seeTagTypeDto.setFieldName(fieldName);
        return seeTagTypeDto;
    }

}
