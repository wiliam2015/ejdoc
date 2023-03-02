package com.ejdoc.metainfo.seralize.parser;


import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.model.JavaModuleMeta;

import java.util.List;

public interface MetaInfoParser extends MetaProjectMetaInfoParser {



    List<JavaModuleMeta> parseAllJavaModuletMeta();

    MetaEnvironment getMetaEnvironment();
}
