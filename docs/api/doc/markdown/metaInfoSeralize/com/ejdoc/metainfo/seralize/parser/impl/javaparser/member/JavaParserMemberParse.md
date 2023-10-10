# 接口名称:JavaParserMemberParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.member    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.JavaParserMemberParse|













**所有子类：**  
[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md),[FieldMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/FieldMemberParse.md),[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md),[ConstructorMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/ConstructorMemberParse.md),[EnumMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/EnumMemberParse.md)
,[NestedClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/NestedClassMemberParse.md),[AnnotationMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AnnotationMemberParse.md),[InnerClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/InnerClassMemberParse.md)





---

## 声明信息

> public interface JavaParserMemberParse     


**描述：** javaParsermember成员类型解析器












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|void [parseMemberToJavaClassMeta](#parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>将Member解析成JavaClassMeta.|







## 方法详细信息


---

> **1.<span id="parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext">parseMemberToJavaClassMeta</span>**

**方法签名：** 

  void [parseMemberToJavaClassMeta](#parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


**描述：** 

将Member解析成JavaClassMeta

**参数描述：** 

  javaClassMeta - 

  metaFileInfo - 

  typeDeclaration - 







