# 接口名称:JavaParserTypeDeclarationParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.type    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.JavaParserTypeDeclarationParse|













**所有子类：**  
[AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md),[EnumTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/EnumTypeDeclarationParse.md),[AnnotationTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AnnotationTypeDeclarationParse.md),[ClassTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/ClassTypeDeclarationParse.md),[PackageInfoTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/PackageInfoTypeDeclarationParse.md)
,[NestedClassMemberExtractParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/NestedClassMemberExtractParse.md)





---

## 声明信息

> public interface JavaParserTypeDeclarationParse     


**描述：** javaParserTypeDeclaration类型解析器












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [accept](#accept-typedeclaration-metafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   <br/><br/>是否接受此类型解析.|
|2|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>将typeDeclaration解析成JavaClassMeta.|







## 方法详细信息


---

> **1.<span id="accept-typedeclaration-metafileinfodto">accept</span>**

**方法签名：** 

  [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [accept](#accept-typedeclaration-metafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   


**描述：** 

是否接受此类型解析

**参数描述：** 

  typeDeclaration - 








---

> **2.<span id="parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext">parseTypeToJavaClassMeta</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


**描述：** 

将typeDeclaration解析成JavaClassMeta

**参数描述：** 

  metaFileInfo - 

  rootAst - 

  typeDeclaration - 







