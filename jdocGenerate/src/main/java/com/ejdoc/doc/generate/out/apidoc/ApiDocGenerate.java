package com.ejdoc.doc.generate.out.apidoc;

import com.ejdoc.doc.generate.env.DocOutEnvironment;
import com.ejdoc.doc.generate.env.impl.DefaultDocOutEnvironment;
import com.ejdoc.doc.generate.out.AbstractDocGenerate;
import com.ejdoc.doc.generate.out.config.DocGenerateConfig;
import com.ejdoc.doc.generate.out.javadoc.JavaDocDeepDependParsePlugin;
import com.ejdoc.doc.generate.out.javadoc.JavaDocDeprecatedParsePlugin;
import com.ejdoc.doc.generate.out.javadoc.JavaDocGenerateConfig;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.plugin.JavaMetaSeralizeDependPathPlugin;

/**
 * API文档生成类
 */
public class ApiDocGenerate extends AbstractDocGenerate {

    public ApiDocGenerate(DocGenerateConfig docGenerateConfig, DocOutTemplate docOutTemplate, DocTemplateTheme docTemplateTheme) {
        super(docGenerateConfig, docOutTemplate, docTemplateTheme);
    }

    public ApiDocGenerate(DocGenerateConfig docGenerateConfig, DocOutEnvironment environment, DocOutTemplate docOutTemplate, DocTemplateTheme docTemplateTheme) {
        super(docGenerateConfig, environment, docOutTemplate, docTemplateTheme);
    }

    public ApiDocGenerate(DocGenerateConfig docGenerateConfig, String configFilePath, DocOutTemplate docOutTemplate, DocTemplateTheme docTemplateTheme){
        super(docGenerateConfig,new DefaultDocOutEnvironment(configFilePath),docOutTemplate,docTemplateTheme);
    }
    @Override
    protected void doLoadJavaMetaSeralizePlugin(JavaMetaSeralize javaMetaSeralize) {
        javaMetaSeralize.addMetaSeralizePlugins(new JavaMetaSeralizeDependPathPlugin());
//        javaMetaSeralize.addMetaSeralizePlugins(new JavaDocDeepDependParsePlugin());
        javaMetaSeralize.addMetaSeralizePlugins(new JavaDocDeprecatedParsePlugin());
        javaMetaSeralize.addMetaSeralizePlugins(new ApiDocTreeTypeParsePlugin());
        javaMetaSeralize.addMetaSeralizePlugins(new ApiDocMockDataParsePlugin());
        doLoadCustomPlugin(javaMetaSeralize);
    }



    /**
     * 加载自定义插件 子类重新使用
     * @param javaMetaSeralize
     */
    protected void doLoadCustomPlugin(JavaMetaSeralize javaMetaSeralize){

    }
}
