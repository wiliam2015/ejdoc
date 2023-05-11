package com.ejdoc.doc.generate.template.markdown;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.exception.JavaDocSerializeException;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.template.AbstractDocOutTemplate;
import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MarkdownDocOutTemplate extends AbstractDocOutTemplate {
    private static final Logger log = LoggerFactory.getLogger(MarkdownDocOutTemplate.class);
    public MarkdownDocOutTemplate(){
        super();
    }

    public MarkdownDocOutTemplate(DocGenerateConfig docGenerateConfig){
        super(null, docGenerateConfig);
    }

    public MarkdownDocOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig){
        super(groupTemplate, docGenerateConfig);
    }

    @Override
    protected Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo) {
        String jsonStr = FileUtil.readString(docOutFileInfo.getFullFilePath(), StandardCharsets.UTF_8);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            log.error("convertJsonFileToProp error file path:{}",docOutFileInfo.getFullFilePath());
            throw new JavaDocSerializeException("java doc serialize exception file path"+docOutFileInfo.getFullFilePath(),e);
        }
        Map customProp = loadCustomProp(docOutFileInfo,jsonObject);
        if(CollectionUtil.isNotEmpty(customProp)){
            jsonObject.putAll(customProp);
        }
        return jsonObject;
    }

    /**
     * 子类加载自定义属性
     * @param docOutFileInfo
     * @param jsonObject
     * @return
     */
    protected Map loadCustomProp(DocOutFileInfo docOutFileInfo, JSONObject jsonObject) {
        return null;
    }

    @Override
    protected TemplateTypeEnum loadTemplateType() {
        return TemplateTypeEnum.MarkDown;
    }
}
