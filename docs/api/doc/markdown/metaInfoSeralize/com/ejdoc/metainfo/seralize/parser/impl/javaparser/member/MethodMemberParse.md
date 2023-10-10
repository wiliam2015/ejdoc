# 类名称:MethodMemberParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.member    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.MethodMemberParse|









**所有父类：**  
[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md),[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)

**所有子类：**  
[ConstructorMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/ConstructorMemberParse.md)





---

## 声明信息

> public class MethodMemberParse extends [AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md)     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected static ResolvedType [getMethodReturnResolvedType](#getmethodreturnresolvedtype-resolvedmethoddeclaration)(ResolvedMethodDeclaration resolve)   <br/><br/>|
|2|protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|3|protected void [parseMethodDocTag](#parsemethoddoctag-javamethodmeta-optional-nodelist)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,Optional< Javadoc > javadoc,NodeList< AnnotationExpr > annotations)   <br/><br/>|
|4|protected [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) [parseMethodMember](#parsemethodmember-bodydeclaration-metafileinfodto-typedeclaration)(BodyDeclaration< ? > member,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration)   <br/><br/>|
|5|protected void [parseMethodModifiers](#parsemethodmodifiers-javamethodmeta-nodelist)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,NodeList< Modifier > modifiersList)   <br/><br/>|
|6|protected void [parseMethodParamAndException](#parsemethodparamandexception-javamethodmeta-callabledeclaration-resolvedmethodlikedeclaration)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,CallableDeclaration methodDeclaration,ResolvedMethodLikeDeclaration resolve)   <br/><br/>|
|7|protected void [parseMethodReturnType](#parsemethodreturntype-javamethodmeta-methoddeclaration-resolvedmethoddeclaration)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,MethodDeclaration methodDeclaration,ResolvedMethodDeclaration resolve)   <br/><br/>|


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




## 方法详细信息


---

> **1.<span id="getmethodreturnresolvedtype-resolvedmethoddeclaration">getMethodReturnResolvedType</span>**

**方法签名：** 

  protected static ResolvedType [getMethodReturnResolvedType](#getmethodreturnresolvedtype-resolvedmethoddeclaration)(ResolvedMethodDeclaration resolve)   










---

> **2.<span id="parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext">parseBodyDeclarationToJavaClassMeta</span>**

**方法签名：** 

  protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **3.<span id="parsemethoddoctag-javamethodmeta-optional-nodelist">parseMethodDocTag</span>**

**方法签名：** 

  protected void [parseMethodDocTag](#parsemethoddoctag-javamethodmeta-optional-nodelist)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,Optional< Javadoc > javadoc,NodeList< AnnotationExpr > annotations)   










---

> **4.<span id="parsemethodmember-bodydeclaration-metafileinfodto-typedeclaration">parseMethodMember</span>**

**方法签名：** 

  protected [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) [parseMethodMember](#parsemethodmember-bodydeclaration-metafileinfodto-typedeclaration)(BodyDeclaration< ? > member,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration)   










---

> **5.<span id="parsemethodmodifiers-javamethodmeta-nodelist">parseMethodModifiers</span>**

**方法签名：** 

  protected void [parseMethodModifiers](#parsemethodmodifiers-javamethodmeta-nodelist)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,NodeList< Modifier > modifiersList)   










---

> **6.<span id="parsemethodparamandexception-javamethodmeta-callabledeclaration-resolvedmethodlikedeclaration">parseMethodParamAndException</span>**

**方法签名：** 

  protected void [parseMethodParamAndException](#parsemethodparamandexception-javamethodmeta-callabledeclaration-resolvedmethodlikedeclaration)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,CallableDeclaration methodDeclaration,ResolvedMethodLikeDeclaration resolve)   










---

> **7.<span id="parsemethodreturntype-javamethodmeta-methoddeclaration-resolvedmethoddeclaration">parseMethodReturnType</span>**

**方法签名：** 

  protected void [parseMethodReturnType](#parsemethodreturntype-javamethodmeta-methoddeclaration-resolvedmethoddeclaration)([JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) javaMethodMeta,MethodDeclaration methodDeclaration,ResolvedMethodDeclaration resolve)   









