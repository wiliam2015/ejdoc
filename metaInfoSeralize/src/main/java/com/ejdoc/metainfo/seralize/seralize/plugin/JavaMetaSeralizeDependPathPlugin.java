package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaSeralizePluginData;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;

import java.util.List;
import java.util.Map;

public class JavaMetaSeralizeDependPathPlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {


    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        List<JavaMetaSeralizePluginData> allJavaMetaSeralizeClassList = javaMetaServalizePluginContextDto.getAllJavaMetaSeralizeClassList();
        if(CollectionUtil.isNotEmpty(allJavaMetaSeralizeClassList)){
            for (JavaMetaSeralizePluginData javaMetaSeralizePluginData : allJavaMetaSeralizeClassList) {
                parseDependPath(javaMetaSeralizePluginData, javaMetaServalizePluginContextDto);
            }
        }
    }

    private void parseDependPath(JavaMetaSeralizePluginData javaMetaSeralizePluginData,JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {
        Map<String, JavaMetaSeralizePluginData> dependPathMap = javaMetaServalizePluginContextDto.getMetaSeralizeFileIndex();
        JavaClassMeta javaClassMeta = JSONUtil.toBean(javaMetaSeralizePluginData.getJsonObject(),JavaClassMeta.class);

        parseSupperClassDependPath(javaMetaServalizePluginContextDto,javaClassMeta,javaMetaSeralizePluginData, dependPathMap);

        parseInterfaceDependPath(javaMetaServalizePluginContextDto,javaClassMeta,javaMetaSeralizePluginData, dependPathMap);

        parseContructorDependPath(javaMetaServalizePluginContextDto,javaClassMeta, javaMetaSeralizePluginData,dependPathMap);

        parseFieldDependPath(javaMetaServalizePluginContextDto,javaClassMeta, javaMetaSeralizePluginData,dependPathMap);

        parseMethodDependPath(javaMetaServalizePluginContextDto,javaClassMeta,javaMetaSeralizePluginData, dependPathMap);
    }





    private void parseMethodDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaClassMeta javaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData,  Map<String, JavaMetaSeralizePluginData> dependPathMap) {
        List<JavaMethodMeta> methods = javaClassMeta.getMethods();
        String jsonFilePath = javaMetaSeralizePluginData.getJsonFilePath();
        if(CollectionUtil.isNotEmpty(methods)){
            for (JavaMethodMeta method : methods) {
                JavaClassMeta returns = method.getReturns();
                setRelativePath(javaMetaServalizePluginContextDto,dependPathMap, jsonFilePath, returns);
                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        setRelativePath(javaMetaServalizePluginContextDto,dependPathMap, jsonFilePath, javaClass);
                    }
                }
                parseBaseClassDependPath(javaMetaServalizePluginContextDto,method.getExceptions(),jsonFilePath, dependPathMap);
            }

            JSONObject jsonObject = javaMetaSeralizePluginData.getJsonObject();
            jsonObject.putOpt("methods",JSONUtil.parseArray(methods));
        }
    }

    private void parseFieldDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaClassMeta javaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData,  Map<String, JavaMetaSeralizePluginData> dependPathMap) {
        List<JavaFieldMeta> fields = javaClassMeta.getFields();
        String jsonFilePath = javaMetaSeralizePluginData.getJsonFilePath();
        if(CollectionUtil.isNotEmpty(fields)){
            for (JavaFieldMeta field : fields) {
                JavaClassMeta type = field.getType();
                setRelativePath(javaMetaServalizePluginContextDto,dependPathMap,jsonFilePath, type);
            }

            JSONObject jsonObject = javaMetaSeralizePluginData.getJsonObject();
            jsonObject.putOpt("fields",JSONUtil.parseArray(fields));
        }
    }

    private void parseContructorDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaClassMeta javaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData,  Map<String, JavaMetaSeralizePluginData> dependPathMap) {
        List<JavaConstructorMeta> constructors = javaClassMeta.getConstructors();
        String jsonFilePath = javaMetaSeralizePluginData.getJsonFilePath();
        if(CollectionUtil.isNotEmpty(constructors)){
            for (JavaConstructorMeta constructor : constructors) {
                List<JavaParameterMeta> parameters = constructor.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        setRelativePath(javaMetaServalizePluginContextDto,dependPathMap,jsonFilePath, javaClass);
                    }
                }
                parseBaseClassDependPath(javaMetaServalizePluginContextDto,constructor.getExceptions(),jsonFilePath, dependPathMap);
            }

            JSONObject jsonObject = javaMetaSeralizePluginData.getJsonObject();
            jsonObject.putOpt("constructors",JSONUtil.parseArray(constructors));
        }

    }

    private void parseInterfaceDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaClassMeta javaClassMeta,JavaMetaSeralizePluginData javaMetaSeralizePluginData,  Map<String, JavaMetaSeralizePluginData> dependPathMap) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getInterfaces();
        String jsonFilePath = javaMetaSeralizePluginData.getJsonFilePath();
        parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMetaList,jsonFilePath,dependPathMap);

        reSetJsonProp(javaClassMetaList, javaMetaSeralizePluginData, "interfaces");
    }



    private  void parseSupperClassDependPath(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto,JavaClassMeta javaClassMeta, JavaMetaSeralizePluginData javaMetaSeralizePluginData, Map<String, JavaMetaSeralizePluginData> dependPathMap) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getSuperClasses();
        String jsonFilePath = javaMetaSeralizePluginData.getJsonFilePath();

        parseBaseClassDependPath(javaMetaServalizePluginContextDto,javaClassMetaList,jsonFilePath,dependPathMap);

        reSetJsonProp(javaClassMetaList, javaMetaSeralizePluginData, "superClasses");

    }

    /**
     * 重新设置json对象的属性
     * @param javaClassMetaList
     * @param javaMetaSeralizePluginData
     * @param interfaces
     */
    private void reSetJsonProp(List<JavaClassMeta> javaClassMetaList, JavaMetaSeralizePluginData javaMetaSeralizePluginData, String interfaces) {
        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            JSONObject jsonObject = javaMetaSeralizePluginData.getJsonObject();
            jsonObject.putOpt(interfaces,JSONUtil.parseArray(javaClassMetaList));
        }
    }
}
