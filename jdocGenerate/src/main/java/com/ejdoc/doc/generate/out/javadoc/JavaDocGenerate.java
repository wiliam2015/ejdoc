package com.ejdoc.doc.generate.out.javadoc;

import com.ejdoc.doc.generate.out.AbstractDocGenerate;
import com.ejdoc.doc.generate.template.DocOutTemplate;
import com.ejdoc.doc.generate.template.DocTemplateTheme;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.plugin.JavaMetaSeralizeDependPathPlugin;

public class JavaDocGenerate extends AbstractDocGenerate {

    public JavaDocGenerate(JavaDocGenerateConfig docGenerateConfig, DocOutTemplate docOutTemplate, DocTemplateTheme docTemplateTheme){
        super(docGenerateConfig,docOutTemplate,docTemplateTheme);
    }

    @Override
    protected void doLoadJavaMetaSeralizePlugin(JavaMetaSeralize javaMetaSeralize) {
        javaMetaSeralize.addMetaSeralizePlugins(new JavaMetaSeralizeDependPathPlugin());
        javaMetaSeralize.addMetaSeralizePlugins(new JavaDocDeepDependParsePlugin());
    }



}
