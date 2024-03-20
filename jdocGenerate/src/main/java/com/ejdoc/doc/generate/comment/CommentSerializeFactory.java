package com.ejdoc.doc.generate.comment;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;

import java.util.*;

public class CommentSerializeFactory {

    static List<CommentSerialize> commentSerializes = null;

    static Map<String,CommentSerialize> commentSerializeMap = null;
    static Map<String,CommentSerialize> commentJSRSerializeMap = null;

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

    /**
     * 创建JSR注释序列化解析
     * @return
     */
    public static Map<String,CommentSerialize> createMdJSRCommentSerializeMap(){
        if(commentJSRSerializeMap == null){
            commentJSRSerializeMap = new HashMap<>();
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper("com.ejdoc.doc.generate.comment.jsr.md.impl", CommentSerialize.class);
            if(CollectionUtil.isNotEmpty(classes)){
                try{
                    for (Class<?> aClass : classes) {
                        CommentSerialize o = (CommentSerialize)aClass.newInstance();
                        commentJSRSerializeMap.put(o.acceptType(),o);
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return commentJSRSerializeMap;
    }
}
