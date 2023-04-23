# 接口名称:JavaParserTypeDeclarationParse

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.JavaParserTypeDeclarationParse
* **包名称:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.type
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize







* **所有子类：**  
[AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md)

---

## 声明信息
> public interface JavaParserTypeDeclarationParse     


* **描述：** 

  javaParser  TypeDeclaration类型解析器

* **描述：** 

javaParserTypeDeclaration类型解析器






## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|boolean [accept](#innerlink-accept-typedeclaration-?--comejdocmetainfoseralizedtometafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   <br/><br/><br/>是否接受此类型解析|
|2|[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [parseTypeToJavaClassMeta](#innerlink-parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/><br/>将typeDeclaration解析成JavaClassMeta|








## 方法详细信息

---
> **1.<span id="innerlink-accept-typedeclaration-?--comejdocmetainfoseralizedtometafileinfodto">accept</span>**

* **方法签名：** 

  boolean [accept](#accept-typedeclaration-?--comejdocmetainfoseralizedtometafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   


* **描述：** 

是否接受此类型解析
* **参数描述：** 

  typeDeclaration - 


* **返回值描述：** 

   - 




---
> **2.<span id="innerlink-parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext">parseTypeToJavaClassMeta</span>**

* **方法签名：** 

  [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


* **描述：** 

将typeDeclaration解析成JavaClassMeta
* **参数描述：** 

  metaFileInfo - 

  rootAst - 

  typeDeclaration - 


* **返回值描述：** 

   - 




