package com.ejdoc.metainfo.seralize.test;

import com.ejdoc.metainfo.seralize.parser.MetaInfoParser;
import com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser;
import com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize;
import com.ejdoc.metainfo.seralize.seralize.config.SeralizeConfig;
import com.ejdoc.metainfo.seralize.seralize.impl.JavaMetaJsonSeralizeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaInfoParserTest {
    private static final Logger log = LoggerFactory.getLogger(MetaInfoParserTest.class);

    public static void main(String[] args) {
//        MetaInfoParaser metaInfoParaser = new QdoxMetaInfoParaser();
        MetaInfoParser metaInfoParser = new JavaParserMetaInfoParser();
//        JavaProjectMeta javaProjectMeta = metaInfoParaser.parseJavaProjectMeta();
//        Console.log("javaProjectMeta："+ JSONUtil.toJsonStr(javaProjectMeta));
//        metaInfoParaser.parseAllJavaModuletMeta();
        log.info("start meta");
        SeralizeConfig seralizeConfig = new SeralizeConfig();
        seralizeConfig.setPrettyFormat(true);
        JavaMetaSeralize javaMetaSeralize = new JavaMetaJsonSeralizeImpl(metaInfoParser);
        javaMetaSeralize.exeJavaMetaSeralize(seralizeConfig);
        log.info("start meta finish");

    }
}
