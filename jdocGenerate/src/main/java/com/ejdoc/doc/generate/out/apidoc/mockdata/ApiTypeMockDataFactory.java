package com.ejdoc.doc.generate.out.apidoc.mockdata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.out.apidoc.mockdata.collimpl.CollectionTypeApiTypeMockData;
import com.ejdoc.doc.generate.out.apidoc.mockdata.collimpl.MapTypeApiTypeMockData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApiTypeMockDataFactory {

    private ApiTypeMockDataFactory(){}

    static Map<String,ApiTypeMockData> apiTypeMockDataMap;

    /**循环引用mapkey，防止循环引用*/
    static Map<String,Integer> circleRefMap = new ConcurrentHashMap<>();
    static Map<String,ApiTypeMockData> defaultApiTypeMockDataMap = new HashMap<>();

//    static CollectionTypeApiTypeMockData collectionTypeApiTypeMockData = new CollectionTypeApiTypeMockData();

//    static MapTypeApiTypeMockData mapTypeApiTypeMockData = new MapTypeApiTypeMockData();


    static Map<String, ApiTypeMockData> createapiTypeMockDataMap(){
        if(apiTypeMockDataMap == null){
            apiTypeMockDataMap = new HashMap<>();
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper("com.ejdoc.doc.generate.out.apidoc.mockdata.impl", ApiTypeMockData.class);
            if(CollectionUtil.isNotEmpty(classes)){
                try{
                    for (Class<?> aClass : classes) {
                        ApiTypeMockData o = (ApiTypeMockData)aClass.newInstance();
                        apiTypeMockDataMap.put(o.mockType(),o);
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return apiTypeMockDataMap;
    }

    public static Map<String, ApiTypeMockData> getApiTypeMockDataMap() {
        return apiTypeMockDataMap;
    }

    public static ApiTypeMockData getApiTypeMockDataIfNullForDefaulMock(String className,String fullClassName,String refUniqueId){
        if(apiTypeMockDataMap == null){
            createapiTypeMockDataMap();
        }

        ApiTypeMockData apiTypeMockData = apiTypeMockDataMap.get(fullClassName);
        if(apiTypeMockData != null){
            return apiTypeMockData;
        }

        if(StrUtil.startWith(fullClassName,"java")){
            Class<Object> objectClass = ClassUtil.loadClass(fullClassName);
            if(objectClass != null && (ArrayUtil.isNotEmpty(objectClass.getInterfaces()) || objectClass.isInterface())){
                for (Class<?> anInterface : objectClass.getInterfaces()) {
                    if(anInterface.getName().equals("java.util.Collection")){
                        return new CollectionTypeApiTypeMockData(className, fullClassName,refUniqueId);

                    }else if(anInterface.getName().equals("java.util.Map")){
                        return new MapTypeApiTypeMockData(className, fullClassName,refUniqueId);
                    }
                }
                if(objectClass.isInterface()){
                    if(fullClassName.equals("java.util.Collection")){
                        return new CollectionTypeApiTypeMockData(className, fullClassName,refUniqueId);

                    }else if(fullClassName.equals("java.util.Map")){
                        return new MapTypeApiTypeMockData(className, fullClassName,refUniqueId);
                    }
                }
            }
        }


        String circleRefMapKey =refUniqueId;
        Integer refCount = circleRefMap.getOrDefault(circleRefMapKey, 0);
        if(refCount > 0){
            return new DefaultCircleRefApiTypeMockData(className, fullClassName);
        }else{
            circleRefMap.put(circleRefMapKey,refCount+1);
        }

        if(defaultApiTypeMockDataMap.containsKey(fullClassName)){
            return defaultApiTypeMockDataMap.get(fullClassName);
        }



        DefaultApiTypeMockData defaultApiTypeMockData = new DefaultApiTypeMockData(className, fullClassName);
        defaultApiTypeMockDataMap.put(fullClassName,defaultApiTypeMockData);
        return defaultApiTypeMockData;
    }

}
