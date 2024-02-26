package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

import java.util.List;

public class DateApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {

        return "java.util.Date";
    }

    @Override
    public  Object mockBaseData(String name){
        return RandomSource.dateTimeSource().randomFutureDate();
    }
}
