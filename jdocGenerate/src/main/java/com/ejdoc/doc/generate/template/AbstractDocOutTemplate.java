package com.ejdoc.doc.generate.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.enums.TemplateThemeEnum;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import org.beetl.core.GroupTemplate;

import java.nio.file.StandardCopyOption;
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
        String template = loadTemplate(templateType,propMap,docOutFileInfo);
        return template;
    }

    @Override
    public void writeFormat(String formatData, DocOutFileInfo docOutFileInfo) {
        String renderCurrentVersion = docOutFileInfo.getRenderCurrentVersion();
        if(StrUtil.equals(renderCurrentVersion,"true")){
            return ;
        }
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        TemplateThemeEnum templateTheme = docOutFileInfo.getTemplateTheme();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String version = docOutFileInfo.getVersion();
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String templateDir =templateType.getCode();
        if(templateTheme != null && StrUtil.isNotBlank(templateTheme.getSrcDir())){
            templateDir = StrUtil.join("/",templateDir,templateTheme.getSrcDir());
        }
        String path = StrUtil.join("/",docOutRootPath,"doc",docOutFileInfo.getDocType(),templateDir,"v",version,relativePath,docOutFileInfo.getFileName()+templateType.getExtension());
        FileUtil.writeString(formatData, path, "UTF-8");
    }


    /**
     * 复制文件
     * @param docOutFileInfo 文件信息
     */
    @Override
    public void copyFile(DocOutFileInfo docOutFileInfo) {
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        String version = docOutFileInfo.getVersion();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        TemplateThemeEnum templateTheme = docOutFileInfo.getTemplateTheme();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String templateDir =templateType.getCode();
        if(templateTheme != null && StrUtil.isNotBlank(templateTheme.getSrcDir())){
            templateDir = StrUtil.join("/",templateDir,templateTheme.getSrcDir());
        }
        String outFilePath = StrUtil.join("/",docOutRootPath,"doc",docOutFileInfo.getDocType(),templateDir,"v",version,relativePath,docOutFileInfo.getFileName()+templateType.getExtension());
        String sourFilePath = docOutFileInfo.getFullFilePath();
        FileUtil.copyFile(sourFilePath, outFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    protected abstract Map convertJsonFileToProp(DocOutFileInfo docOutFileInfo);

    protected abstract TemplateTypeEnum loadTemplateType();




}
