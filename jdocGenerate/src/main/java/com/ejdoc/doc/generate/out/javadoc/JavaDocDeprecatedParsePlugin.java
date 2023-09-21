package com.ejdoc.doc.generate.out.javadoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.out.javadoc.dto.JavaDocDeprecatedDto;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 解析Deprecated注解插件在最终的json文件上生成关于Deprecated的描述信息
 */
public class JavaDocDeprecatedParsePlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {

    private static final Logger log = LoggerFactory.getLogger(JavaDocDeprecatedParsePlugin.class);

    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        List<JavaClassMeta> javaClassMetaList = MetaIndexContext.getJavaClassMetaList();


        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                String fullClassName = javaClassMeta.getFullClassName();
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                JavaDocDeprecatedDto javaDocDeprecatedDto = new JavaDocDeprecatedDto();
                parseDeprecatedClassMetaInfo(javaClassMeta,javaDocDeprecatedDto);

                JSONObject jsonObject = javaMetaFile.getJsonObject();
                jsonObject.putOpt("javaDocDeprecatedInfo", JSONUtil.parseObj(javaDocDeprecatedDto));
            }
        }
    }


    private void parseDeprecatedClassMetaInfo(JavaClassMeta javaClassMeta, JavaDocDeprecatedDto javaDocDeprecatedDto) {
        JavaModelMeta javaModelMeta = javaClassMeta.getJavaModelMeta();
        boolean haveDeprecatedInfo = false;
        if(javaModelMeta != null && CollectionUtil.isNotEmpty(javaModelMeta.getAnnotations())){
            for (JavaAnnotationMeta annotation : javaModelMeta.getAnnotations()) {
                if("Deprecated".equals(annotation.getName())){
                    JavaDocletTagMeta deprecatedTag = getDeprecatedTag(javaModelMeta);
                    javaDocDeprecatedDto.setDeprecatedClass(true);
                    String namePath =(javaClassMeta.getModuleName()+"/"+javaClassMeta.getFullClassName()).replace(".","/");
                    JavaDocDeprecatedDto deprecatedClass = new JavaDocDeprecatedDto(javaClassMeta.getFullClassName(),deprecatedTag.getValue(),namePath);
                    deprecatedClass.setDeprecateTag(deprecatedTag);
                    javaDocDeprecatedDto.addJavaDocDeprecatedClass(deprecatedClass);
                    haveDeprecatedInfo = true;
                    break;
                }
            }
        }

        List<JavaMethodMeta> methods = javaClassMeta.getMethods();
        if(CollectionUtil.isNotEmpty(methods)){
            for (JavaMethodMeta method : methods) {
                JavaModelMeta javaModelMeta1 = method.getJavaModelMeta();
                if(javaModelMeta1 != null && CollectionUtil.isNotEmpty(javaModelMeta1.getAnnotations())){
                    for (JavaAnnotationMeta annotation : javaModelMeta1.getAnnotations()) {
                        if("Deprecated".equals(annotation.getName())){
                            List<JavaParameterMeta> parameters = method.getParameters();
                            StringBuilder uniqueName = getMethodUniqueName(parameters);
                            javaDocDeprecatedDto.setDeprecatedMethod(true);
                            JavaDocletTagMeta deprecatedTag = getDeprecatedTag(javaModelMeta1);
                            String fullMethodName = javaClassMeta.getFullClassName()+"."+method.getName()+uniqueName;
                            String namePath =(javaClassMeta.getModuleName()+"/"+javaClassMeta.getFullClassName()).replace(".","/");
                            JavaDocDeprecatedDto deprecatedClass = new JavaDocDeprecatedDto(fullMethodName,deprecatedTag.getValue(),namePath);
                            deprecatedClass.setMethodDetail(method);
                            deprecatedClass.setDeprecateTag(deprecatedTag);
                            javaDocDeprecatedDto.addJavaDocDeprecatedMethods(deprecatedClass);
                            haveDeprecatedInfo = true;
                            break;
                        }
                    }
                }
            }
        }
        List<JavaConstructorMeta> constructors = javaClassMeta.getConstructors();
        if(CollectionUtil.isNotEmpty(constructors)){
            for (JavaConstructorMeta constructor : constructors) {
                JavaModelMeta javaModelMeta1 = constructor.getJavaModelMeta();
                if(javaModelMeta1 != null && CollectionUtil.isNotEmpty(javaModelMeta1.getAnnotations())){
                    for (JavaAnnotationMeta annotation : javaModelMeta1.getAnnotations()) {
                        if("Deprecated".equals(annotation.getName())){
                            List<JavaParameterMeta> parameters = constructor.getParameters();
                            StringBuilder uniqueName = getMethodUniqueName(parameters);
                            JavaDocletTagMeta deprecatedTag = getDeprecatedTag(javaModelMeta1);
                            javaDocDeprecatedDto.setDeprecatedContructor(true);
                            String fullMethodName = javaClassMeta.getFullClassName()+uniqueName;
                            String namePath =(javaClassMeta.getModuleName()+"/"+javaClassMeta.getFullClassName()).replace(".","/");
                            JavaDocDeprecatedDto deprecatedClass = new JavaDocDeprecatedDto(fullMethodName,deprecatedTag.getValue(),namePath);
                            deprecatedClass.setConstructorDetail(constructor);
                            deprecatedClass.setDeprecateTag(deprecatedTag);
                            javaDocDeprecatedDto.addJavaDocDeprecatedConstructors(deprecatedClass);
                            haveDeprecatedInfo = true;
                            break;
                        }
                    }
                }
            }
        }

        if(haveDeprecatedInfo){
            javaDocDeprecatedDto.setClassName(javaClassMeta.getClassName());
            javaDocDeprecatedDto.setProjectName(javaClassMeta.getProjectName());
            javaDocDeprecatedDto.setModuleName(javaClassMeta.getModuleName());
            javaDocDeprecatedDto.setPackageName(javaClassMeta.getPackageName());
            javaDocDeprecatedDto.setFullClassName(javaClassMeta.getFullClassName());
        }
    }

    /**
     * 获取方法唯一名
     * @param parameters
     * @return
     */
    private  StringBuilder getMethodUniqueName(List<JavaParameterMeta> parameters) {
        StringBuilder paramTypeBuilder = new StringBuilder();
        if(CollectionUtil.isNotEmpty(parameters)){
            paramTypeBuilder.append("(");
            int i = 0;
            for (JavaParameterMeta parameter : parameters) {
                if( i++ > 0){
                    paramTypeBuilder.append(",");
                }
                paramTypeBuilder.append(ObjectUtil.isNull(parameter.getJavaClass())? "":parameter.getJavaClass().getClassName());
            }
            paramTypeBuilder.append(")");
        }else{
            paramTypeBuilder.append("()");
        }
        return paramTypeBuilder;
    }

    /**
     * 获取Deprecated类型Tag
     * @param javaModelMeta 注释信息
     * @return
     */
    private  JavaDocletTagMeta getDeprecatedTag(JavaModelMeta javaModelMeta) {
        JavaDocletTagMeta deprecatedTag = null;
        List<JavaDocletTagMeta> tags = javaModelMeta.getTags();
        if(CollectionUtil.isNotEmpty(tags)){
            for (JavaDocletTagMeta tag : tags) {
                if("DEPRECATED".equals(tag.getType())){
                    deprecatedTag = tag;
                    break;
                }
            }
        }
        if(deprecatedTag == null){
            deprecatedTag = new JavaDocletTagMeta();
            deprecatedTag.setType("DEPRECATED");
            deprecatedTag.setTagName("deprecated");
            deprecatedTag.setValue("默认注释:不推荐使用/defult comment: deprecated");
        }
        return deprecatedTag;
    }
}
