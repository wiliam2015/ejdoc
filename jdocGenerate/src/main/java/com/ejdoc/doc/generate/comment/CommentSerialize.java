package com.ejdoc.doc.generate.comment;

public interface CommentSerialize {

    String acceptType();

    boolean accept(String type);

    String toSerialize(String content,String projectName,String moduleName,String curPackage);
}
