# 抽象类:AbstractJavaParserTypeDeclarationParse

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.AbstractJavaParserTypeDeclarationParse
* **包名称:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.type
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize





* **所有父类：**  
[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)
* **所有父级接口：**  
[JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)
* **所有子类：**  
[EnumTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/EnumTypeDeclarationParse.md),[AnnotationTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AnnotationTypeDeclarationParse.md),[ClassTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/ClassTypeDeclarationParse.md),[PackageInfoTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/PackageInfoTypeDeclarationParse.md)

---

## 声明信息
> public abstract class AbstractJavaParserTypeDeclarationParse extends [BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)   implements [JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)   


* **描述：** 

  







## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AbstractJavaParserTypeDeclarationParse](#innerlink-abstractjavaparsertypedeclarationparse-javautillist)(List< JavaParserMemberParse > javaParserMemberParseList)   <br/>|

## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public List< JavaParserMemberParse > [getJavaParserMemberParseList](#innerlink-getjavaparsermemberparselist)()   <br/>|
|2|public [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [parseTypeToJavaClassMeta](#innerlink-parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/>|
|3|public void [setJavaParserMemberParseList](#innerlink-setjavaparsermemberparselist-javautillist)(List< JavaParserMemberParse > javaParserMemberParseList)   <br/>|


---
### 继承类方法:BaseJavaParse

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)  



---
### 继承接口方法:JavaParserTypeDeclarationParse

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.[JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)  
[accept](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md#accept-typedeclaration-?--comejdocmetainfoseralizedtometafileinfodto),[parseTypeToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md#parseTypeToJavaClassMeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)



## 构造方法详细信息

---
> **1.<span id="innerlink-abstractjavaparsertypedeclarationparse-javautillist">AbstractJavaParserTypeDeclarationParse</span>**

* **构造方法签名：** 

  public  [AbstractJavaParserTypeDeclarationParse](#abstractjavaparsertypedeclarationparse-javautillist)(List< JavaParserMemberParse > javaParserMemberParseList)   







## 方法详细信息

---
> **1.<span id="innerlink-getjavaparsermemberparselist">getJavaParserMemberParseList</span>**

* **方法签名：** 

  public List< JavaParserMemberParse > [getJavaParserMemberParseList](#getjavaparsermemberparselist)()   







---
> **2.<span id="innerlink-parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext">parseTypeToJavaClassMeta</span>**

* **方法签名：** 

  public [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-compilationunit-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   







---
> **3.<span id="innerlink-setjavaparsermemberparselist-javautillist">setJavaParserMemberParseList</span>**

* **方法签名：** 

  public void [setJavaParserMemberParseList](#setjavaparsermemberparselist-javautillist)(List< JavaParserMemberParse > javaParserMemberParseList)   







