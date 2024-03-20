package com.ejdoc.metainfo.seralize.seralize.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
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

        parseClassTypeParameterAndArgsDependPath(javaClassMeta,seralizeConfig);

        parseSupperClassDependPath(javaClassMeta,seralizeConfig);

        parseInterfaceDependPath(javaClassMeta,seralizeConfig);

        parseContructorDependPath(javaClassMeta,seralizeConfig);

        parseFieldDependPath(javaClassMeta,seralizeConfig);

        parseMethodDependPath(javaClassMeta,seralizeConfig);

    }

    /**
     * 解析类级别的类型参数和类型实参依赖路径
     * @param javaClassMeta
     * @param seralizeConfig
     */
    private void parseClassTypeParameterAndArgsDependPath(JavaClassMeta javaClassMeta, SeralizeConfig seralizeConfig) {
        String fullClassName = javaClassMeta.getFullClassName();
        JavaMetaFileInfo javaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
        String jsonFilePath = javaMetaFileInfo.getJsonFilePath();

        setTypeParametersDependPath(seralizeConfig, jsonFilePath,  javaClassMeta.getTypeParameters());
        setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, javaClassMeta.getTypeArguments());

        reSetJsonProp(javaClassMeta.getTypeParameters(), javaMetaFileInfo, "typeParameters");
        reSetJsonProp(javaClassMeta.getTypeArguments(), javaMetaFileInfo, "typeArguments");
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
                setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, returns.getTypeArguments());
                setTypeParametersDependPath(seralizeConfig, jsonFilePath,  returns.getTypeParameters());

                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        JavaMetaFileInfo paramJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(javaClass.getFullClassName());
                        setRelativePath(seralizeConfig,paramJavaMetaFileInfo,jsonFilePath, javaClass);

                        setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, javaClass.getTypeArguments());
                        setTypeParametersDependPath(seralizeConfig, jsonFilePath,  javaClass.getTypeParameters());
                    }
                }
                parseBaseClassDependPath(seralizeConfig,method.getExceptions(),jsonFilePath,MetaIndexContext.getAllJavaMetaFileIndexMap());

            }

            reSetJsonProp(methods, javaMetaFileInfo, "methods");
        }
    }

    /**
     * 设置类型参数依赖路径
     * Type Parameter 是在定义泛型类、接口或方法时使用的占位符，用于表示未知的具体类型。
     * Type Parameter 通常使用单个大写字母来表示，例如 T、E、K 等。
     * Type Parameter 是在定义阶段使用的，用于表示泛型的形式，不是具体的类型。
     * @param seralizeConfig
     * @param jsonFilePath
     * @param typeParameters
     */
    private void setTypeParametersDependPath(SeralizeConfig seralizeConfig, String jsonFilePath, List<JavaTypeParameterMeta> typeParameters) {
        if(CollectionUtil.isNotEmpty(typeParameters)){
            for (JavaTypeParameterMeta typeParameter : typeParameters) {
                if(typeParameter.getType() != null){
                    JavaMetaFileInfo typeParameterJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(typeParameter.getType().getFullClassName());
                    setRelativePath(seralizeConfig,typeParameterJavaMetaFileInfo, jsonFilePath, typeParameter.getType());
                    setTypeParametersDependPath(seralizeConfig,jsonFilePath,typeParameter.getType().getTypeParameters());
                    setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, typeParameter.getType().getTypeArguments());
                }
            }
        }
    }

    /**
     * 设置类型实际参数的依赖路径
     * Type Argument 是在创建对象、调用方法或实现接口时提供的实际类型。
     * Type Argument 是在使用阶段指定的，用于替代 Type Parameter，使得泛型能够适应不同的具体类型。
     * @param seralizeConfig
     * @param jsonFilePath
     * @param typeArguments
     */
    private void setTypeArgumentsDependPath(SeralizeConfig seralizeConfig, String jsonFilePath, List<JavaClassMeta> typeArguments) {
        if(CollectionUtil.isEmpty(typeArguments)){
            return;
        }
        for (JavaClassMeta typeArgument : typeArguments) {
            JavaMetaFileInfo typeArgumentJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(typeArgument.getFullClassName());
            setRelativePath(seralizeConfig,typeArgumentJavaMetaFileInfo, jsonFilePath, typeArgument);

            JavaClassMeta typeArgExtend = typeArgument.getTypeArgExtend();
            if(typeArgExtend != null){
                JavaMetaFileInfo typeArgExtendJavaMetaFileInfo = MetaIndexContext.getJavaMetaFileByFullName(typeArgExtend.getFullClassName());
                setRelativePath(seralizeConfig,typeArgExtendJavaMetaFileInfo, jsonFilePath, typeArgExtend);
            }
            setTypeArgumentsDependPath(seralizeConfig,jsonFilePath,typeArgument.getTypeArguments());
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

                setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, type.getTypeArguments());
                setTypeParametersDependPath(seralizeConfig, jsonFilePath,  type.getTypeParameters());
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

                        setTypeArgumentsDependPath(seralizeConfig, jsonFilePath, javaClass.getTypeArguments());
                        setTypeParametersDependPath(seralizeConfig, jsonFilePath,  javaClass.getTypeParameters());
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
