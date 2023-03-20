package com.ejdoc.doc.generate.template.html;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.template.AbstractDocOutTemplate;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import org.beetl.core.GroupTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HtmlDocOutTemplate extends AbstractDocOutTemplate {

    public HtmlDocOutTemplate(){
        super();
    }
    public HtmlDocOutTemplate(DocGenerateConfig docGenerateConfig){
        super(null, docGenerateConfig);
    }
    public HtmlDocOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig){
        super(groupTemplate, docGenerateConfig);
    }

    @Override
    protected Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo) {
        String jsonStr = FileUtil.readString(docOutFileInfo.getFullFilePath(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        return jsonObject;
    }

    @Override
    protected TemplateTypeEnum loadTemplateType() {
        return TemplateTypeEnum.Html;
    }
}
