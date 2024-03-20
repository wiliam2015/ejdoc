package com.ejdoc.doc.generate.test;

import com.ejdoc.doc.generate.out.DocGenerate;
import com.ejdoc.doc.generate.out.factory.DocGenerateFactory;
import org.junit.jupiter.api.Test;

public class ApiDocGenerateTest {

    @Test
    public void printApiDocTest(){
        DocGenerate apiDocGenerate = DocGenerateFactory.createDefaultApiDocGenerate();

        apiDocGenerate.printDoc();
    }



}
