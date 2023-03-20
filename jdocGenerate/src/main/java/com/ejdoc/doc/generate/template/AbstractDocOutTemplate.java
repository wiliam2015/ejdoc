package com.ejdoc.doc.generate.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import org.beetl.core.GroupTemplate;

import java.util.Map;

public abstract class AbstractDocOutTemplate extends BaseOutTemplate implements DocOutTemplate{

    public AbstractDocOutTemplate(){
        super(null,null);


    }

    public AbstractDocOutTemplate(GroupTemplate groupTemplate, DocGenerateConfig docGenerateConfig){
        super(groupTemplate,docGenerateConfig);
    }


    @Override
    public String formatTemplate(DocOutFileInfo docOutFileInfo) {
        TemplateTypeEnum templateType = loadTemplateType();
        Map propMap = convertJsonFileToProp(docOutFileInfo);
        String template = loadTemplate(templateType,propMap,docOutFileInfo.isMainFile());
        return template;
    }

    @Override
    public void writeFormat(String formatData, DocOutFileInfo docOutFileInfo) {
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String path = docOutRootPath+"/doc/"+templateType.getCode()+relativePath+"/"+docOutFileInfo.getFileName()+templateType.getExtension();
        FileUtil.writeString(formatData, path, "UTF-8");
    }

    protected abstract Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo);

    protected abstract TemplateTypeEnum loadTemplateType();




}
