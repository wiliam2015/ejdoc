package com.ejdoc.doc.generate.out.apidoc.mockdata.impl;

import com.apifan.common.random.RandomSource;
import com.ejdoc.doc.generate.out.apidoc.mockdata.AbstractBaseTypeApiTypeMockData;

public class BigDecimalApiTypeMockData extends AbstractBaseTypeApiTypeMockData {

    @Override
    public String mockType() {
        return "java.math.BigDecimal";
    }


    @Override
    public  Object mockBaseData(String name){
        return RandomSource.numberSource().randomInt(100, 1000);
    }

}
