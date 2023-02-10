package com.ejdoc.doc.generate.test;

import com.ejdoc.doc.generate.out.JavaDocGenerate;
import org.junit.jupiter.api.Test;

public class JavaDocGenerateTest {

    @Test
    public void printJavaDocTest(){
        JavaDocGenerate javaDocGenerate = new JavaDocGenerate();
        javaDocGenerate.printJavaDoc();

    }
}
