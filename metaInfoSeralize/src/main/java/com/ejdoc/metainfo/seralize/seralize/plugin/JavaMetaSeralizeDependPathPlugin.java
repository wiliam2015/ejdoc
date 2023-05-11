package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;

import java.util.List;

public class JavaMetaSeralizeDependPathPlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {


    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {

        List<JavaClassMeta> javaClassMetaList = MetaIndexContext.getJavaClassMetaList();
        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                parseDependPath(javaClassMeta,javaMetaServalizePluginContextDto.getSeralizeConfig());
            }

        }
    }

    private void parseDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {

        parseSupperClassDependPath(javaClassMeta,seralizeConfig);

        parseInterfaceDependPath(javaClassMeta,seralizeConfig);

        parseContructorDependPath(javaClassMeta,seralizeConfig);

        parseFieldDependPath(javaClassMeta,seralizeConfig);

        parseMethodDependPath(javaClassMeta,seralizeConfig);

    }





    private void parseMethodDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {
        List<JavaMethodMeta> methods = javaClassMeta.getMethods();

        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        if(CollectionUtil.isNotEmpty(methods)){
            for (JavaMethodMeta method : methods) {
                JavaClassMeta returns = method.getReturns();
                JavaMetaFileInfo returnJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(returns.getFullClassName());
                setRelativePath(seralizeConfig,returnJavaMetaFileInfo,jsonFilePath, returns);
                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        JavaMetaFileInfo paramJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(javaClass.getFullClassName());
                        setRelativePath(seralizeConfig,paramJavaMetaFileInfo,jsonFilePath, javaClass);
                    }
                }
                parseBaseClassDependPath(seralizeConfig,method.getExceptions(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
            }

            reSetJsonProp(methods, javaMetaFileInfo, "methods");
        }
    }

    private void parseFieldDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {
        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        List<JavaFieldMeta> fields = javaClassMeta.getFields();
        if(CollectionUtil.isNotEmpty(fields)){
            for (JavaFieldMeta field : fields) {
                JavaClassMeta type = field.getType();
                JavaMetaFileInfo fieldJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(type.getFullClassName());
                setRelativePath(seralizeConfig,fieldJavaMetaFileInfo,jsonFilePath, type);
            }

            reSetJsonProp(fields, javaMetaFileInfo, "fields");
        }
    }

    private void parseContructorDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {
        List<JavaConstructorMeta> constructors = javaClassMeta.getConstructors();

        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        if(CollectionUtil.isNotEmpty(constructors)){
            for (JavaConstructorMeta constructor : constructors) {
                List<JavaParameterMeta> parameters = constructor.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        JavaMetaFileInfo contructorJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(javaClass.getFullClassName());
                        setRelativePath(seralizeConfig,contructorJavaMetaFileInfo,jsonFilePath, javaClass);
                    }
                }
                parseBaseClassDependPath(seralizeConfig,constructor.getExceptions(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());
            }

            reSetJsonProp(constructors, javaMetaFileInfo, "constructors");
        }

    }

    private void parseInterfaceDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getInterfaces();

        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        parseBaseClassDependPath(seralizeConfig,javaClassMetaList,jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());

        reSetJsonProp(javaClassMetaList, javaMetaFileInfo, "interfaces");
    }



    private  void parseSupperClassDependPath(JavaClassMeta javaClassMeta,SeralizeConfig seralizeConfig) {
        List<JavaClassMeta> javaClassMetaList =javaClassMeta.getSuperClasses();
        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        parseBaseClassDependPath(seralizeConfig,javaClassMetaList,jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());

        reSetJsonProp(javaClassMetaList, javaMetaFileInfo, "superClasses");

    }

    /**
     * 重新设置json对象的属性
     * @param javaClassMetaList
     * @param javaMetaFileInfo
     * @param keyName
     */
    private void reSetJsonProp(List javaClassMetaList, JavaMetaFileInfo javaMetaFileInfo, String keyName) {
        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            JSONObject jsonObject = javaMetaFileInfo.getJsonObject();
            jsonObject.putOpt(keyName,JSONUtil.parseArray(javaClassMetaList));
        }
    }
}
