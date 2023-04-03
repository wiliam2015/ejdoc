package com.ejdoc.metainfo.seralize.test;

import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.junit.jupiter.api.Test;

public class JavaMetaSeralizeTest {

    @Test
    public  void javaMetaSeralizeDefault() {
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl();
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
    }

    @Test
    public  void javaMetaSeralizeConfig() {
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl("singleFile.properties");
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
    }
}
