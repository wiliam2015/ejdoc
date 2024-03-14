package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

import java.util.List;

public class DoubleApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {
        return "java.lang.Double";
    }

    @Override
    public  Object mockBaseData(String name){
        return RandomSource.numberSource().randomDouble(100.11D, 1000.66D);
    }
}
