package com.ejdoc.metainfo.seralize.test;

import com.ejdoc.metainfo.seralize.paraser.MetaInfoParaser;
import com.ejdoc.metainfo.seralize.paraser.impl.javaparaser.JavaParaserMetaInfoParaser;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaInfoParaserTest {
    private static final Logger log = LoggerFactory.getLogger(MetaInfoParaserTest.class);

    public static void main(String[] args) {
//        MetaInfoParaser metaInfoParaser = new QdoxMetaInfoParaser();
        MetaInfoParaser metaInfoParaser = new JavaParaserMetaInfoParaser();
//        JavaProjectMeta javaProjectMeta = metaInfoParaser.parseJavaProjectMeta();
//        Console.log("javaProjectMeta："+ JSONUtil.toJsonStr(javaProjectMeta));
//        metaInfoParaser.parseAllJavaModuletMeta();
        log.info("start meta");
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl(metaInfoParaser);
        javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
        log.info("start meta finish");

    }
}
