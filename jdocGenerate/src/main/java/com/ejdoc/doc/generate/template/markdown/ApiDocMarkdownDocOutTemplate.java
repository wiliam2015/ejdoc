package com.ejdoc.doc.generate.template.markdown;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ejdoc.doc.generate.enums.TemplateTypeEnum;
import com.ejdoc.doc.generate.model.DocOutFileInfo;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.StandardCopyOption;
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

    @Override
    public void writeFormat(String formatData, DocOutFileInfo docOutFileInfo) {
        super.writeFormat(formatData, docOutFileInfo);
        doWriteLatestVersionFile(formatData,docOutFileInfo);
    }

    @Override
    public void copyFile(DocOutFileInfo docOutFileInfo) {
        super.copyFile(docOutFileInfo);
        doCopyLatestVersionFile(docOutFileInfo);
    }

    private void doCopyLatestVersionFile(DocOutFileInfo docOutFileInfo) {
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        String version = docOutFileInfo.getVersion();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String outFilePath = StrUtil.join("/",docOutRootPath,"doc",docOutFileInfo.getDocType(),templateType.getCode(),relativePath,docOutFileInfo.getFileName()+templateType.getExtension());
        String sourFilePath = docOutFileInfo.getFullFilePath();
        FileUtil.copyFile(sourFilePath, outFilePath, StandardCopyOption.REPLACE_EXISTING);
    }
    /**
     * 写最新版本的文件内容
     * @param formatData
     * @param docOutFileInfo
     */
    private void doWriteLatestVersionFile(String formatData, DocOutFileInfo docOutFileInfo) {
        String docOutRootPath = docOutFileInfo.getDocOutRootPath();
        TemplateTypeEnum templateType = docOutFileInfo.getTemplateType();
        Assert.notNull(docOutRootPath,"docOutRootPath not null");
        String version = docOutFileInfo.getVersion();
        String relativePath = "";
        if(StrUtil.isNotBlank(docOutFileInfo.getRelativeRootPath())){
            relativePath = "/"+docOutFileInfo.getRelativeRootPath();
        }
        String path = StrUtil.join("/",docOutRootPath,"doc",docOutFileInfo.getDocType(),templateType.getCode(),relativePath,docOutFileInfo.getFileName()+templateType.getExtension());
        FileUtil.writeString(formatData, path, "UTF-8");
    }


}
