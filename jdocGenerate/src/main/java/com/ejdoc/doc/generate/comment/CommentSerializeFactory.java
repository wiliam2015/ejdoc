package com.ejdoc.doc.generate.comment;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;

import java.util.*;

public class CommentSerializeFactory {

    static List<CommentSerialize> commentSerializes = null;

    static Map<String,CommentSerialize> commentSerializeMap = null;

    private CommentSerializeFactory(){}

    public static List<CommentSerialize> createMdCommentSerialize(){
        if(commentSerializes == null){
            commentSerializes = new ArrayList<>();
        }
        return commentSerializes;
    }


    public static Map<String,CommentSerialize> createMdCommentSerializeMap(){
        if(commentSerializeMap == null){
            commentSerializeMap = new HashMap<>();
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper("com.ejdoc.doc.generate.comment.md.impl", CommentSerialize.class);
            if(CollectionUtil.isNotEmpty(classes)){
                try{
                    for (Class<?> aClass : classes) {
                        CommentSerialize o = (CommentSerialize)aClass.newInstance();
                        commentSerializeMap.put(o.acceptType(),o);
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return commentSerializeMap;
    }
}
