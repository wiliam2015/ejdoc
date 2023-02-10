package com.ejdoc.doc.generate.template.markdown;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.JavaDocGenerateConfig;
import com.ejdoc.doc.generate.template.AbstractDocOutTemplate;
import org.beetl.core.GroupTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MarkdownDocOutTemplate extends AbstractDocOutTemplate {

    public MarkdownDocOutTemplate(){
        super();
    }

    public MarkdownDocOutTemplate(JavaDocGenerateConfig javaDocGenerateConfig){
        super(null,javaDocGenerateConfig);
    }

    public MarkdownDocOutTemplate(GroupTemplate groupTemplate, JavaDocGenerateConfig javaDocGenerateConfig){
        super(groupTemplate,javaDocGenerateConfig);
    }

    @Override
    protected Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo) {
        String jsonStr = FileUtil.readString(docOutFileInfo.getFullFilePath(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        return jsonObject;
    }

    @Override
    protected TemplateTypeEnum loadTemplateType() {
        return TemplateTypeEnum.MarkDown;
    }
}
