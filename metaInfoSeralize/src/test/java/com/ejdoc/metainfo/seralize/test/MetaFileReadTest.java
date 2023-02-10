package com.ejdoc.metainfo.seralize.test;

import cn.hutool.core.lang.Console;
import com.ejdoc.metainfo.seralize.resource.MetaFileRead;
import com.ejdoc.metainfo.seralize.resource.impl.DefaultMetaFileRead;

public class MetaFileReadTest {

    public static void main(String[] args) {
        MetaFileRead metaFileRead = new DefaultMetaFileRead();
        Console.log(metaFileRead.readAllMetaFile());
    }
}
