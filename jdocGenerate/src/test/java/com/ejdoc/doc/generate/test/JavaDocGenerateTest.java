package com.ejdoc.doc.generate.test;

import cn.hutool.http.HttpUtil;
import com.ejdoc.doc.generate.out.DocGenerate;
import com.ejdoc.doc.generate.out.factory.DocGenerateFactory;
import org.junit.jupiter.api.Test;

public class JavaDocGenerateTest {

    @Test
    public void printJavaDocTest(){
        DocGenerate javaDocGenerate = DocGenerateFactory.createDefaultJavaDocGenerate();

        javaDocGenerate.printDoc();

    }



}
