package com.ejdoc.doc.generate.out.apidoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiMockTypeArgument;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockData;
import com.ejdoc.doc.generate.out.apidoc.mockdata.ApiTypeMockDataFactory;
import com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil;
import com.ejdoc.metainfo.seralize.index.JavaMetaFileInfo;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.*;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin;
import com.ejdoc.metainfo.seralize.seralize.plugin.dto.JavaMetaServalizePluginContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API DOC Mock数据使用
 */
public class ApiDocMockDataParsePlugin extends AbstractJavaMetaSeralizePlugin implements JavaMetaSeralizePlugin {

    private static final Logger log = LoggerFactory.getLogger(ApiDocMockDataParsePlugin.class);

    private DocClassRenderUtil docClassFn = new DocClassRenderUtil();

    @Override
    public void exePostJavaMetaSeralize(JavaMetaServalizePluginContextDto javaMetaServalizePluginContextDto) {

        List<JavaClassMeta> javaClassMetaList = MetaIndexContext.getJavaClassMetaList();


        if(CollectionUtil.isNotEmpty(javaClassMetaList)){
            for (JavaClassMeta javaClassMeta : javaClassMetaList) {
                if(!BooleanUtil.isTrue(javaClassMeta.getInterfaceClass())){
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
                Map<String, List<JavaDocCommentElementMeta>> paramCommentTagMap = getMethodParamCommentTagMap(method);

                JavaClassMeta returns = method.getReturns();
                if(returns != null){
                    ApiTypeMockData apiReturnTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(returns.getClassName(),returns.getFullClassName(),"");
                    List<JavaClassMeta> typeArgumentsParam = returns.getTypeArguments();
                    List<ApiMockTypeArgument> apiMockTypeArguments = new ArrayList<>();
                    fillApiMockTypeArguments(typeArgumentsParam,apiMockTypeArguments);

                    List<JavaDocCommentElementMeta> javaDocCommentElementMetas = paramCommentTagMap.get("apiReturn");
                    Object mockReturnResult= apiReturnTypeMockData.mockData(apiMockTypeArguments,"apiReturn",javaDocCommentElementMetas,0);
                    method.putExtProp("returnMockData",JSONUtil.toJsonStr(mockReturnResult));
                }
                List<JavaParameterMeta> parameters = method.getParameters();
                if(CollectionUtil.isNotEmpty(parameters)){
                    List<Map<String,Object>> mockParamData = new ArrayList<>();
                    for (JavaParameterMeta parameter : parameters) {
                        Map<String,Object> paramMockMap = new HashMap<>();
                        JavaClassMeta javaClass = parameter.getJavaClass();
                        if(javaClass != null){
                            ApiTypeMockData apiTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(javaClass.getClassName(),javaClass.getFullClassName(),"");
                            List<JavaClassMeta> typeArgumentsParam = javaClass.getTypeArguments();
                            List<ApiMockTypeArgument> apiMockTypeArguments = new ArrayList<>();
                            fillApiMockTypeArguments(typeArgumentsParam,apiMockTypeArguments);

                            List<JavaDocCommentElementMeta> javaDocCommentElementMetas = paramCommentTagMap.get(parameter.getName());
                            Object mockResult = apiTypeMockData.mockData(apiMockTypeArguments,parameter.getName(),javaDocCommentElementMetas,0);
                            if(mockResult != null){
                                paramMockMap.put(parameter.getName(),mockResult);
                                mockParamData.add(paramMockMap);
                            }
                        }
                    }
                    method.putExtProp("paramMockData",JSONUtil.toJsonStr(mockParamData));
                }
            }
        }
        return methods;
    }

    /**
     * 递归填充类型参数
     * @param typeArgumentsParam
     * @param apiMockTypeArguments
     */
    private void fillApiMockTypeArguments(List<JavaClassMeta> typeArgumentsParam, List<ApiMockTypeArgument> apiMockTypeArguments) {
        if(CollectionUtil.isNotEmpty(typeArgumentsParam)){
            ApiMockTypeArgument apiMockTypeArgument = null;
            for (JavaClassMeta classMeta : typeArgumentsParam) {
                apiMockTypeArgument = new ApiMockTypeArgument();
                apiMockTypeArgument.setClassName(classMeta.getClassName());
                apiMockTypeArgument.setFullClassName(classMeta.getFullClassName());
                if(CollectionUtil.isNotEmpty(classMeta.getTypeArguments())){
                    List<ApiMockTypeArgument> childApiMockTypeArguments = new ArrayList<>();
                    fillApiMockTypeArguments(classMeta.getTypeArguments(),childApiMockTypeArguments);
                    apiMockTypeArgument.setChildApiMockTypeArguments(childApiMockTypeArguments);
                }
                apiMockTypeArguments.add(apiMockTypeArgument);

            }
        }
    }

    /**
     * 将方法参数中注释tag转换为Map
     * @param method
     * @return
     */
    private Map<String, List<JavaDocCommentElementMeta>> getMethodParamCommentTagMap(JavaMethodMeta method) {
        Map<String,List<JavaDocCommentElementMeta>> paramCommentTagMap = new HashMap<>();
        JavaModelMeta javaModelMeta = method.getJavaModelMeta();
        if(javaModelMeta != null && CollectionUtil.isNotEmpty(javaModelMeta.getTags())){
            List<JavaDocletTagMeta> tags = javaModelMeta.getTags();
            for (JavaDocletTagMeta tag : tags) {
                if((StrUtil.equals(tag.getType(),"PARAM") || StrUtil.equals(tag.getType(),"RETURN")) && CollectionUtil.isNotEmpty(tag.getValues())){
                    List<JavaDocCommentElementMeta> noTextComment = new ArrayList<>();
                    List<JavaDocCommentElementMeta> values = tag.getValues();
                    for (JavaDocCommentElementMeta value : values) {
                        if(!StrUtil.equals(value.getTagName(),"text")){
                            noTextComment.add(value);
                        }
                    }

                    if(StrUtil.equals(tag.getType(),"RETURN")){
                        paramCommentTagMap.put("apiReturn",noTextComment);
                    }else{
                        paramCommentTagMap.put(tag.getName(),noTextComment);
                    }
                }
            }

        }
        return paramCommentTagMap;
    }


}
