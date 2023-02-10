package com.ejdoc.metainfo.seralize.test;

import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;

public class JavaMetaSeralizeTest {

    public static void main(String[] args) {
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl();
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
    }
}
