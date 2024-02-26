package com.ejdoc.doc.generate.template.markdown;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * API文档markdown样式生成模板
 */
public class ApiDocMarkdownDocOutTemplate extends MarkdownDocOutTemplate {

    private static final Logger log = LoggerFactory.getLogger(ApiDocMarkdownDocOutTemplate.class);

    public ApiDocMarkdownDocOutTemplate() {
        super();
    }

    public ApiDocMarkdownDocOutTemplate(DocGenerateConfig docGenerateConfig) {
        super(docGenerateConfig);
    }

    public ApiDocMarkdownDocOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig) {
        super(groupTemplate, docGenerateConfig);
    }

    /**是否是接口类*/
    private static  String INTERFACE_CLASS_KEY ="interfaceClass";
    /**模板后缀名，自定义模板使用*/
    private static  String TEMPLATE_APPEND_NAME_KEY ="templateAppendName";
    /**
     * 子类加载自定义属性
     * @param docOutFileInfo
     * @param jsonObject
     * @return
     */
    @Override
    protected Map loadCustomProp(DocOutFileInfo docOutFileInfo, JSONObject jsonObject) {
        Map<String,Object> resultProp = new HashMap<>();
        try {
            Boolean interfaceClass = jsonObject.getBool(INTERFACE_CLASS_KEY, false);
            if(interfaceClass){
                resultProp.put(TEMPLATE_APPEND_NAME_KEY,"Interface");
            }
        } catch (Exception e) {
            log.error("ApiDocMarkdownDocOutTemplate loadCustomProp exception,docOutFileInfo:{}",docOutFileInfo.getFullFilePath(),e);
        }
        return resultProp;
    }
}
