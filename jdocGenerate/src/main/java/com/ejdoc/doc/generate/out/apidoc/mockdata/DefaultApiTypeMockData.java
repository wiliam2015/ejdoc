package com.ejdoc.doc.generate.out.apidoc.mockdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.util.JdkClassUtil;
import com.ejdoc.metainfo.seralize.index.MetaIndexContext;
import com.ejdoc.metainfo.seralize.model.JavaClassMeta;
import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;
import com.ejdoc.metainfo.seralize.model.JavaFieldMeta;
import com.ejdoc.metainfo.seralize.model.JavaModelMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultApiTypeMockData implements ApiTypeMockData{

    private String className;
    private String fullClassName;

    public DefaultApiTypeMockData(String className,String fullClassName){
        this.className = className;
        this.fullClassName = fullClassName;

    }
    @Override
    public String mockType() {
        return this.fullClassName;
    }

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments,String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas ){
        Map<String,Object> mockResult = new HashMap<>();
        JavaClassMeta javaClassMeta = MetaIndexContext.getClassMetaByFullName(this.fullClassName);
        if(javaClassMeta != null){
            if(BooleanUtil.isTrue(javaClassMeta.getEnumClass())){
                return getEnumMockData(javaClassMeta);
            }

            mockResult.putAll(mockFieldData(apiMockTypeArguments, javaClassMeta.getFields()));
        }
        if(CollectionUtil.isNotEmpty(mockResult)){
            return mockResult;
        }
        return null;
    }

    private  Map<String,Object> mockFieldData(List<ApiMockTypeArgument> apiMockTypeArguments, List<JavaFieldMeta> fields ) {
        Map<String,Object> mockResult = new HashMap<>();
        if(CollectionUtil.isNotEmpty(fields)){
            for (JavaFieldMeta field : fields) {
                if(StrUtil.equals("serialVersionUID",field.getName())){
                    continue;
                }
                if(StrUtil.isNotBlank(field.getInitializer())){
                    String processedText = field.getInitializer().replace("\\", "").replace("\"","");
                    mockResult.put(field.getName(),processedText);
                    continue;
                }
                JavaClassMeta type = field.getType();
                if(type != null){
                    setJdkClassTypeParameter(type);

                    List<JavaDocCommentElementMeta> noTextComment = getJavaDocCommentElementMetas(field);
                    //如果有类型参数需要使用类型实参转换具体的值
                    if(BooleanUtil.isTrue(type.getTypeParameter()) && CollectionUtil.isNotEmpty(apiMockTypeArguments)){
                        for (ApiMockTypeArgument apiMockTypeArgument : apiMockTypeArguments) {
                            ApiTypeMockData apiTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(apiMockTypeArgument.getClassName(), apiMockTypeArgument.getFullClassName(),this.fullClassName);
                            Object mockFieldResult = apiTypeMockData.mockData(apiMockTypeArgument.getChildApiMockTypeArguments(),field.getName(),noTextComment);
                            if(mockFieldResult != null){
                                mockResult.put(field.getName(),mockFieldResult);
                            }
                        }

                    }else{
                        ApiTypeMockData apiTypeMockData = ApiTypeMockDataFactory.getApiTypeMockDataIfNullForDefaulMock(type.getClassName(), type.getFullClassName(),this.fullClassName);
                        List<ApiMockTypeArgument> apiFieldMockTypeArguments = new ArrayList<>();
                        fillApiMockTypeArguments(type.getTypeArguments(),apiFieldMockTypeArguments);

//                        Object mockFieldResult = apiTypeMockData.mockData(apiMockTypeArguments,field.getName(),noTextComment);
                        Object mockFieldResult = apiTypeMockData.mockData(apiFieldMockTypeArguments,field.getName(),noTextComment);
                        if(mockFieldResult != null){
                            mockResult.put(field.getName(),mockFieldResult);
                        }
                    }
                }
            }
        }
        return mockResult;
    }

    /**
     * jdk内部类设置是否是TypeParameter
     * @param type
     */
    private  void setJdkClassTypeParameter(JavaClassMeta type) {
        if(JdkClassUtil.isJdkClass(type.getFullClassName())){
            Class<Object> objectClass = ClassUtil.loadClass(type.getFullClassName());
            if(objectClass != null &&  objectClass.getTypeParameters() != null && objectClass.getTypeParameters().length > 0){
                type.setTypeParameter(true);
            }
        }
    }
    private  List<JavaDocCommentElementMeta> getJavaDocCommentElementMetas(JavaFieldMeta field) {
        List<JavaDocCommentElementMeta> noTextComment = new ArrayList<>();
        JavaModelMeta javaModelMeta = field.getJavaModelMeta();
        if(javaModelMeta != null && javaModelMeta.getJavaDocComment() != null){
            if(CollectionUtil.isNotEmpty(javaModelMeta.getJavaDocComment().getJavaDocCommentElementMetas())){
                for (JavaDocCommentElementMeta javaDocCommentElementMeta : javaModelMeta.getJavaDocComment().getJavaDocCommentElementMetas()) {
                    if(!StrUtil.equals(javaDocCommentElementMeta.getTagName(),"text")){
                        noTextComment.add(javaDocCommentElementMeta);
                    }
                }
            }
        }
        return noTextComment;
    }

    private String getEnumMockData(JavaClassMeta javaClassMeta) {
        List<JavaClassMeta> enumConstants = javaClassMeta.getEnumConstants();
        if(CollectionUtil.isNotEmpty(enumConstants)){
            return enumConstants.get(RandomUtil.randomInt(enumConstants.size())).getClassName();
        }
        return null;
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
}
