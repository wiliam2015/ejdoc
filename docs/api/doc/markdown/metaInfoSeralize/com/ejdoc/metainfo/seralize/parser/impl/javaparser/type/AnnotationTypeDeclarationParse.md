# 类名称:AnnotationTypeDeclarationParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.type    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.AnnotationTypeDeclarationParse|









**所有父类：**  
[AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md),[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)







---

## 声明信息

> public class AnnotationTypeDeclarationParse extends [AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md)     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AnnotationTypeDeclarationParse](#annotationtypedeclarationparse-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   <br/><br/>包名信息.|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [accept](#accept-typedeclaration-metafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   <br/><br/>|
|2|protected [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [doParseChildTypeToJavaClassMeta](#doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|3|protected void [parseInterfaces](#parseinterfaces-javaclassmeta-typedeclaration)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration)   <br/><br/>|


---

### 从AbstractJavaParserTypeDeclarationParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.[AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md)  
[convertClassOrInterfaceTypeToSimpleClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#convertClassOrInterfaceTypeToSimpleClassMeta-classorinterfacetype),[doParseChildTypeToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#doParseChildTypeToJavaClassMeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext),[getJavaParserMemberParseList](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#getJavaParserMemberParseList),[getRefClassResolvedType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#getRefClassResolvedType-classorinterfacetype),[parseClassDeclaration](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseClassDeclaration-javaclassmeta-javaparsermetacontext),[parseClassMetaProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseClassMetaProp-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext),[parseDocAndAnnotation](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseDocAndAnnotation-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext),[parseJavaSouce](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseJavaSouce-javaclassmeta-compilationunit-javaparsermetacontext),[parseMembers](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseMembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext),[parseModifiers](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseModifiers-javaclassmeta-typedeclaration-javaparsermetacontext)
,[parseNestedJavaClass](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseNestedJavaClass-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext),[parseSuperJavaClass](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseSuperJavaClass-javaclassmeta-typedeclaration),[parseTypeToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#parseTypeToJavaClassMeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext),[setJavaParserMemberParseList](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md#setJavaParserMemberParseList-list)

---

### 从BaseJavaParse类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)  
[createJavaDocTag](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#createJavaDocTag-optional-javamodelmeta),[parseExpression](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#parseExpression-expression-list),[setAnnotationExpr](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotationExpr-annotationexpr-javamodelmeta),[setAnnotations](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setAnnotations-nodelist-javamodelmeta),[setFieldResolvedTypeDeclaration](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFieldResolvedTypeDeclaration-javaclassmeta-type),[setFullClassNameFromResolvedType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md#setFullClassNameFromResolvedType-javaclassmeta-resolvedtype)



---

### 从JavaParserTypeDeclarationParse接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.[JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)  
[accept](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md#accept-typedeclaration-metafileinfodto),[parseTypeToJavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md#parseTypeToJavaClassMeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext)



## 构造方法详细信息


---

> **1.<span id="annotationtypedeclarationparse-list">AnnotationTypeDeclarationParse</span>**

**构造方法签名：** 

  public  [AnnotationTypeDeclarationParse](#annotationtypedeclarationparse-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   


**描述：** 

包名信息






## 方法详细信息


---

> **1.<span id="accept-typedeclaration-metafileinfodto">accept</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [accept](#accept-typedeclaration-metafileinfodto)(TypeDeclaration< ? > typeDeclaration,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo)   










---

> **2.<span id="doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext">doParseChildTypeToJavaClassMeta</span>**

**方法签名：** 

  protected [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [doParseChildTypeToJavaClassMeta](#doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **3.<span id="parseinterfaces-javaclassmeta-typedeclaration">parseInterfaces</span>**

**方法签名：** 

  protected void [parseInterfaces](#parseinterfaces-javaclassmeta-typedeclaration)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration)   









