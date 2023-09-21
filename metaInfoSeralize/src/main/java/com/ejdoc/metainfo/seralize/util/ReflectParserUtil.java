package com.ejdoc.metainfo.seralize.util;

import cn.hutool.core.util.ReflectUtil;

public class ReflectParserUtil extends ReflectUtil{


    public static Class getAllSuper(Class<?> clazz){
        Class<?> superclass = clazz.getSuperclass();
        return superclass;
    }
}
