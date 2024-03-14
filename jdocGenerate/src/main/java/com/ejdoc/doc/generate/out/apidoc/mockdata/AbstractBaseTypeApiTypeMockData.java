package com.ejdoc.doc.generate.out.apidoc.mockdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.metainfo.seralize.model.JavaDocCommentElementMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractBaseTypeApiTypeMockData implements ApiTypeMockData{
    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object mockData(List<ApiMockTypeArgument> apiMockTypeArguments,String name, List<JavaDocCommentElementMeta> javaDocCommentElementMetas,int invokeCount) {
        String mockContent = "";
        if(CollectionUtil.isNotEmpty(javaDocCommentElementMetas)){
            for (JavaDocCommentElementMeta javaDocCommentElementMeta : javaDocCommentElementMetas) {
                if(StrUtil.equals(javaDocCommentElementMeta.getTagName(),"mock")){
                    mockContent = javaDocCommentElementMeta.getContent();
                }
            }
        }
        if(StrUtil.isNotBlank(mockContent)){


            AutoGenerateRealMockData realMockData = new AutoGenerateRealMockData(mockContent);
            Object mockRealContent = realMockData.mockRealData();
            if(mockRealContent != null){
                if(autoConvertVal()){
                    try {
                        String mockType = mockType();
                        Class objectClass = ClassUtil.loadClass(mockType);
                        Convert.convert(objectClass,mockRealContent);
                    } catch (Exception e) {
                        log.debug("mock data error name:{}, mockRealContent:{}",name,mockRealContent,e);
                        return mockRealContent;
                    }
                }
                return mockRealContent;
            }
        }
        return mockBaseData(name);
    }

    public abstract Object mockBaseData(String name);

    /**
     * 自动转换值类型，子类重载使用
     * @return
     */
    public boolean autoConvertVal(){
        return true;
    }
}
