package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

import java.util.List;

public class LongApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {
        return "java.lang.Long";
    }

    @Override
    public  Object mockBaseData(String name){
        return RandomSource.numberSource().randomLong(1000L,10000L);
    }
}
