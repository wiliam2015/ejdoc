package com.ejdoc.metainfo.seralize.paraser;


import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.model.JavaModuleMeta;

import java.util.List;

public interface MetaInfoParaser extends MetaProjectMetaInfoParaser{



    List<JavaModuleMeta> parseAllJavaModuletMeta();

    MetaEnvironment getMetaEnvironment();
}
