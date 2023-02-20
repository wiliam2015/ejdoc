package com.ejdoc.doc.generate.test;

import com.ejdoc.doc.generate.out.JavaDocGenerate;
import org.junit.jupiter.api.Test;

public class JavaDocGenerateTest {

    @Test
    public void printJavaDocTest(){
        JavaDocGenerate javaDocGenerate = new JavaDocGenerate();
        javaDocGenerate.printJavaDoc();
//
//        String d="/Users/huhailong1/code/github/hutool/hutool-paraser/docs1/doc";
//        String d1="/Users/huhailong1/code/github/hutool/hutool-paraser/docs1/doc/projectMetaInfo.json";
//        String replaceFilePath = StrUtil.replace("", d1, d);
//        System.out.println("s:"+replaceFilePath);
//        String replace = d1.replace(d, "");
//        System.out.println(replace);
    }
}
