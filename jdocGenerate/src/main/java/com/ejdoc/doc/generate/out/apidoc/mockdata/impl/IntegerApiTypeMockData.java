package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

import java.util.List;

public class IntegerApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {
        return "java.lang.Integer";
    }


    @Override
    public  Object mockBaseData(String name){
        return RandomSource.numberSource().randomInt(1, 101);
    }
}
