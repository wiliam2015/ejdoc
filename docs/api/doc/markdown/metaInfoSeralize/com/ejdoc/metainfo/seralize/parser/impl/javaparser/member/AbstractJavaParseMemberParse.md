# 抽象类:AbstractJavaParseMemberParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.member    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.AbstractJavaParseMemberParse|









**所有父类：**  
[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)

**所有子类：**  
[FieldMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/FieldMemberParse.md),[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md),[ConstructorMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/ConstructorMemberParse.md),[EnumMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/EnumMemberParse.md),[NestedClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/NestedClassMemberParse.md)
,[AnnotationMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AnnotationMemberParse.md),[InnerClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/InnerClassMemberParse.md)





---

## 声明信息

> public abstract class AbstractJavaParseMemberParse extends [BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)   implements [JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)   














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public void [doParseMembers](#doparsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|2|protected [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [filterModifier](#filtermodifier-string-list)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) compileIncludePrivate,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > modifiers)   <br/><br/>过滤修饰符.|
|3|protected abstract void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|4|public void [parseMemberToJavaClassMeta](#parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|


---

### 从BaseJavaParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)  
[createJavaDocTag](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#createJavaDocTag-optional-javamodelmeta),[parseExpression](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#parseExpression-expression-list),[setAnnotationExpr](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotationExpr-annotationexpr-javamodelmeta),[setAnnotations](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotations-nodelist-javamodelmeta),[setFieldResolvedTypeDeclaration](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFieldResolvedTypeDeclaration-javaclassmeta-type),[setFullClassNameFromResolvedType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFullClassNameFromResolvedType-javaclassmeta-resolvedtype)



---

### 从JavaParserMemberParse接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)  
[parseMemberToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md#parseMemberToJavaClassMeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)




## 方法详细信息


---

> **1.<span id="doparsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext">doParseMembers</span>**

**方法签名：** 

  public void [doParseMembers](#doparsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **2.<span id="filtermodifier-string-list">filterModifier</span>**

**方法签名：** 

  protected [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [filterModifier](#filtermodifier-string-list)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) compileIncludePrivate,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > modifiers)   


**描述：** 

过滤修饰符

**参数描述：** 

  compileIncludePrivate - 是否包含私有属性

  modifiers - 修饰符








---

> **3.<span id="parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext">parseBodyDeclarationToJavaClassMeta</span>**

**方法签名：** 

  protected abstract void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **4.<span id="parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext">parseMemberToJavaClassMeta</span>**

**方法签名：** 

  public void [parseMemberToJavaClassMeta](#parsemembertojavaclassmeta-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   









