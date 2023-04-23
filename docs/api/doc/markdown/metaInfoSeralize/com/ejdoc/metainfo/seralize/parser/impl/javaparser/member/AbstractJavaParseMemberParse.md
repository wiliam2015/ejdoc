# 抽象类:AbstractJavaParseMemberParse

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.AbstractJavaParseMemberParse
* **包名称:** com.ejdoc.metainfo.seralize.parser.impl.javaparser.member
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize





* **所有父类：**  
[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)
* **所有父级接口：**  
[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)
* **所有子类：**  
[FieldMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/FieldMemberParse.md),[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md),[ConstructorMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/ConstructorMemberParse.md),[EnumMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/EnumMemberParse.md),[AnnotationMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AnnotationMemberParse.md),[InnerClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/InnerClassMemberParse.md)

---

## 声明信息
> public abstract class AbstractJavaParseMemberParse extends [BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)   implements [JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)   


* **描述：** 

  








## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public void [doParseMembers](#innerlink-doparsemembers-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/>|
|2|public void [parseMemberToJavaClassMeta](#innerlink-parsemembertojavaclassmeta-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/>|


---
### 继承类方法:BaseJavaParse

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)  



---
### 继承接口方法:JavaParserMemberParse

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)  
[parseMemberToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md#parseMemberToJavaClassMeta-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)




## 方法详细信息

---
> **1.<span id="innerlink-doparsemembers-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext">doParseMembers</span>**

* **方法签名：** 

  public void [doParseMembers](#doparsemembers-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   







---
> **2.<span id="innerlink-parsemembertojavaclassmeta-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext">parseMemberToJavaClassMeta</span>**

* **方法签名：** 

  public void [parseMemberToJavaClassMeta](#parsemembertojavaclassmeta-comejdocmetainfoseralizemodeljavaclassmeta-comejdocmetainfoseralizedtometafileinfodto-typedeclaration-?--comejdocmetainfoseralizeparserimpljavaparserjavaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   







