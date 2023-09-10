package com.ejdoc.doc.generate.tagtype;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;

import java.util.*;

public class TagTypeSerializeFactory {

    static List<TagTypeSerialize> tagTypeSerializes = null;

    static Map<String,TagTypeSerialize> tagTypeSerializeMap = null;

    private TagTypeSerializeFactory(){}

    public static List<TagTypeSerialize> createMdTagTypeSerialize(){
        if(tagTypeSerializes == null){
            tagTypeSerializes = new ArrayList<>();
        }
        return tagTypeSerializes;
    }


    public static Map<String,TagTypeSerialize> createMdTagTypeSerializeMap(){
        if(tagTypeSerializeMap == null){
            tagTypeSerializeMap = new HashMap<>();
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper("com.ejdoc.doc.generate.tagtype.md.impl", TagTypeSerialize.class);
            if(CollectionUtil.isNotEmpty(classes)){
                try{
                    for (Class<?> aClass : classes) {
                        TagTypeSerialize o = (TagTypeSerialize)aClass.newInstance();
                        tagTypeSerializeMap.put(o.acceptType(),o);
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tagTypeSerializeMap;
    }
}
