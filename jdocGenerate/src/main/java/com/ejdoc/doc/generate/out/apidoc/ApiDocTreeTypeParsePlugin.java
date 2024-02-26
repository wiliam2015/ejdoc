package com.ejdoc.doc.generate.out.apidoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaMethodMeta;
import com.ejdoc.metainfo.seralize.model.JavaParameterMeta;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * API DOC 类型依赖树解析
 */
public class ApiDocTreeTypeParsePlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {

    private static final Logger log = LoggerFactory.getLogger(ApiDocTreeTypeParsePlugin.class);

    private DocClassRenderUtil docClassFn = new DocClassRenderUtil();
    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {

        List<JavaClassMeta> javaClassMetaList = MetaIndexContext.getJavaClassMetaList();


        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                if(BooleanUtil.isFalse(javaClassMeta.getInterfaceClass())){
                    continue;
                }
                String fullClassName = javaClassMeta.getFullClassName();
                if(StrUtil.isBlank(fullClassName)){
                    continue;
                }
                JavaMetaFileInfo javaMetaFile = MetaIndexContext.getJavaMetaFileByFullName(fullClassName);
                List<JavaMethodMeta> apiMethodMetas = parseApiDocDependTreeClassMetaInfo(javaClassMeta);

                JSONObject jsonObject = javaMetaFile.getJsonObject();
                jsonObject.putOpt("methods", JSONUtil.parseArray(apiMethodMetas));
            }
        }
    }

    private List<JavaMethodMeta>  parseApiDocDependTreeClassMetaInfo(JavaClassMeta javaClassMeta) {
        return parseMethodTreeType(javaClassMeta);
    }

    /**
     * 解析方法依赖类型树
     * @param javaClassMeta
     * @return
     */
    private  List<JavaMethodMeta> parseMethodTreeType(JavaClassMeta javaClassMeta) {
        List<JavaMethodMeta> methods = javaClassMeta.getMethods();
        if(CollectionUtil.isNotEmpty(methods)){
            for (JavaMethodMeta method : methods) {
                String name = method.getName();
                JavaClassMeta returns = method.getReturns();
                List<JavaClassMeta> returnTreeTypeList = parseClassTreeType(returns);
                returns.putExtProp("returnType",returnTreeTypeList);
                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    for (JavaParameterMeta parameter : parameters) {
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        if(javaClass != null){
                            List<JavaClassMeta> paramTreeTypeList = parseClassTreeType(javaClass);
                            parameter.putExtProp("paramType",paramTreeTypeList);
                        }

                    }
                }
            }
        }
        return methods;
    }

    /**
     * 解析class依赖类型树
     * @param classMeta
     * @return
     */
    private List<JavaClassMeta> parseClassTreeType(JavaClassMeta classMeta) {
        List<JavaClassMeta> resultList = new ArrayList<>();
        JavaClassMeta javaClassMeta = getSimpleJavaClassMeta(classMeta);
        resultList.add(javaClassMeta);

        List<JavaClassMeta> typeArguments = classMeta.getTypeArguments();
        recursionParseTypeArguments(resultList, typeArguments,javaClassMeta);

        recursinoParseSubClassType(classMeta, resultList);
        return  resultList;
    }

    private void recursinoParseSubClassType(JavaClassMeta returns, List<JavaClassMeta> returnList) {
        JavaClassMeta classMeta = MetaIndexContext.getClassMetaByFullName(returns.getFullClassName());
        if(classMeta != null && CollectionUtil.isNotEmpty(classMeta.getFields())){
            for (JavaFieldMeta field : classMeta.getFields()) {
                JavaClassMeta type = field.getType();
                //非类型参数增加
                if(type != null && !BooleanUtil.isTrue(type.getTypeParameter()) && !StrUtil.startWith(type.getFullClassName(),"java")){
                    JavaClassMeta simpleJavaClassMeta = getSimpleJavaClassMeta(type);
                    returnList.add(simpleJavaClassMeta);
                    recursinoParseSubClassType(type,returnList);
                }
            }
        }
    }

    private void recursionParseTypeArguments(List<JavaClassMeta> returnList, List<JavaClassMeta> typeArguments,JavaClassMeta javaClassMeta) {
        if(CollectionUtil.isNotEmpty(typeArguments)){
            List<JavaClassMeta> firstFloorTypeArguments = new ArrayList<>();
            for (JavaClassMeta typeArgument : typeArguments) {
                JavaClassMeta simpleJavaClassMeta = getSimpleJavaClassMeta(typeArgument);
                firstFloorTypeArguments.add(simpleJavaClassMeta);
                returnList.add(simpleJavaClassMeta);
                recursionParseTypeArguments(returnList,typeArgument.getTypeArguments(),simpleJavaClassMeta);
                recursinoParseSubClassType(typeArgument, returnList);
            }
            javaClassMeta.setTypeArguments(firstFloorTypeArguments);
        }
    }

    private  JavaClassMeta getSimpleJavaClassMeta(JavaClassMeta returns) {
        JavaClassMeta javaClassMeta = new JavaClassMeta();
        javaClassMeta.setClassName(returns.getClassName());
        javaClassMeta.setFullClassName(returns.getFullClassName());
        javaClassMeta.setDependencyRelativePath(returns.getDependencyRelativePath());
        javaClassMeta.setEnumClass(returns.getEnumClass());

        if(StrUtil.length(returns.getFullClassName()) > StrUtil.length(returns.getClassName())) {
            String replace = StrUtil.replace(returns.getFullClassName(), returns.getClassName(), "", false);
            javaClassMeta.setClassNamePrefix(replace);
        }
        return javaClassMeta;
    }
}
