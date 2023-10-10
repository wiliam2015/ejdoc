# 类名称:ConstructorMemberParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.member    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.ConstructorMemberParse|









**所有父类：**  
[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md),[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md),[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md)







---

## 声明信息

> public class ConstructorMemberParse extends [MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md)     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|2|protected [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) [parseConstructorsMember](#parseconstructorsmember-bodydeclaration)(BodyDeclaration< ? > member)   <br/><br/>|


---

### 从MethodMemberParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.member.[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md)  
[getMethodReturnResolvedType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#getMethodReturnResolvedType-resolvedmethoddeclaration),[parseBodyDeclarationToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseBodyDeclarationToJavaClassMeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext),[parseMethodDocTag](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseMethodDocTag-javamethodmeta-optional-nodelist),[parseMethodMember](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseMethodMember-bodydeclaration-metafileinfodto-typedeclaration),[parseMethodModifiers](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseMethodModifiers-javamethodmeta-nodelist),[parseMethodParamAndException](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseMethodParamAndException-javamethodmeta-callabledeclaration-resolvedmethodlikedeclaration),[parseMethodReturnType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md#parseMethodReturnType-javamethodmeta-methoddeclaration-resolvedmethoddeclaration)

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

> **1.<span id="parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext">parseBodyDeclarationToJavaClassMeta</span>**

**方法签名：** 

  protected void [parseBodyDeclarationToJavaClassMeta](#parsebodydeclarationtojavaclassmeta-javaclassmeta-metafileinfodto-nodelist-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,NodeList< < ? >BodyDeclaration > members,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **2.<span id="parseconstructorsmember-bodydeclaration">parseConstructorsMember</span>**

**方法签名：** 

  protected [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) [parseConstructorsMember](#parseconstructorsmember-bodydeclaration)(BodyDeclaration< ? > member)   









