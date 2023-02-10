package com.ejdoc.metainfo.seralize.test;

import cn.hutool.core.lang.Console;
import com.ejdoc.metainfo.seralize.env.MetaEnvironment;
import com.ejdoc.metainfo.seralize.env.impl.DefaultMetaEnvironment;

public class MetaEnvironmentTest {

    public static void main(String[] args) {
        MetaEnvironment metaEnvironment = new DefaultMetaEnvironment();
        Console.log(metaEnvironment.getProjectCompileType());
        Console.log(metaEnvironment.getProjectRootPath());
        Console.log(metaEnvironment.getSubProjectRootPath());
        Console.log(metaEnvironment.isIncludeSubProject());
    }
}
