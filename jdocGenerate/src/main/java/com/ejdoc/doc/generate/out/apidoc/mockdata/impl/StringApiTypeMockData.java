package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

import java.util.List;

public class StringApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {
        return "java.lang.String";
    }

    @Override
    public  Object mockBaseData(String name){
        return RandomSource.personInfoSource().randomEnglishName();
    }
}
