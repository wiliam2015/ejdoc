# 类名称:InnerClassMemberParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.member    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.InnerClassMemberParse|









**所有父类：**  
[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md),[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)







---

## 声明信息

> public class InnerClassMemberParse extends [AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md)     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [InnerClassMemberParse](#innerclassmemberparse)()   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|


---

### 从AbstractJavaParseMemberParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md)  
[doParseMembers](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md#doParseMembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext),[filterModifier](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md#filterModifier-string-list),[parseBodyDeclarationToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md#parseBodyDeclarationToJavaClassMeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext),[parseMemberToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md#parseMemberToJavaClassMeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)

---

### 从BaseJavaParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)  
[createJavaDocTag](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#createJavaDocTag-optional-javamodelmeta),[parseExpression](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#parseExpression-expression-list),[setAnnotationExpr](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotationExpr-annotationexpr-javamodelmeta),[setAnnotations](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotations-nodelist-javamodelmeta),[setFieldResolvedTypeDeclaration](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFieldResolvedTypeDeclaration-javaclassmeta-type),[setFullClassNameFromResolvedType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFullClassNameFromResolvedType-javaclassmeta-resolvedtype)



---

### 从JavaParserMemberParse接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)  
[parseMemberToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md#parseMemberToJavaClassMeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)



## 构造方法详细信息


---

> **1.<span id="innerclassmemberparse">InnerClassMemberParse</span>**

**构造方法签名：** 

  public  [InnerClassMemberParse](#innerclassmemberparse)()   








## 方法详细信息


---

> **1.<span id="parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext">parseBodyDeclarationToJavaClassMeta</span>**

**方法签名：** 

  protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   









