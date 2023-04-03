package com.ejdoc.doc.generate.comment.md.impl;

import cn.hutool.core.util.StrUtil;
import com.ejdoc.doc.generate.comment.CommentSerialize;
import com.ejdoc.metainfo.seralize.enums.JavaDocCommentTypeEnum;

public class CommentSerializeLinkImpl implements CommentSerialize {

    @Override
    public String acceptType() {
        return JavaDocCommentTypeEnum.LINK.getName();
    }
    @Override
    public boolean accept(String type) {
        return JavaDocCommentTypeEnum.LINK.getName().equals(type);
    }

    @Override
    public String toSerialize(String content,String projectName,String moduleName,String curPackage) {
        if(StrUtil.isBlank(content)){
            return "";
        }
        StringBuilder resultContent = new StringBuilder();
        String[] contentArr = content.split(" ");
        if(contentArr.length > 1){
            String packageAndPath = contentArr[0];
            resultContent.append("[");
            resultContent.append("](");
            resultContent.append(")");
        }
        return content;
    }

    public static void main(String[] args) {
        String data ="Reader#read(char[], int, int) read";
        String data1 ="#read(char[], int, int) read";
        String[] split = data.split("[^#\\(\\)]+");
        boolean matches = data.matches("[#\\(\\)]+");

        System.out.println(data.lastIndexOf(" "));
        System.out.println(split);
    }
}
