# 抽象类:AbstractJavaParserTypeDeclarationParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser.type    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.type.AbstractJavaParserTypeDeclarationParse|









**所有父类：**  
[BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)

**所有父级接口：**  
[JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)

**所有子类：**  
[EnumTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/EnumTypeDeclarationParse.md),[AnnotationTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AnnotationTypeDeclarationParse.md),[ClassTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/ClassTypeDeclarationParse.md),[PackageInfoTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/PackageInfoTypeDeclarationParse.md),[NestedClassMemberExtractParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/NestedClassMemberExtractParse.md)






---

## 声明信息

> public abstract class AbstractJavaParserTypeDeclarationParse extends [BaseJavaParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/BaseJavaParse.md)   implements [JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AbstractJavaParserTypeDeclarationParse](#abstractjavaparsertypedeclarationparse-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [convertClassOrInterfaceTypeToSimpleClassMeta](#convertclassorinterfacetypetosimpleclassmeta-classorinterfacetype)(ClassOrInterfaceType implementedType)   <br/><br/>|
|2|protected abstract [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [doParseChildTypeToJavaClassMeta](#doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|3|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md) > [getJavaParserMemberParseList](#getjavaparsermemberparselist)()   <br/><br/>|
|4|protected ResolvedType [getRefClassResolvedType](#getrefclassresolvedtype-classorinterfacetype)(ClassOrInterfaceType implementedType)   <br/><br/>|
|5|protected void [parseClassDeclaration](#parseclassdeclaration-javaclassmeta-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>解析类声明结构.|
|6|protected void [parseClassMetaProp](#parseclassmetaprop-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfoDto,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>解析class基本信息.|
|7|protected void [parseDocAndAnnotation](#parsedocandannotation-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|8|protected void [parseJavaSouce](#parsejavasouce-javaclassmeta-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|9|protected void [parseMembers](#parsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|10|protected void [parseModifiers](#parsemodifiers-javaclassmeta-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|11|protected void [parseNestedJavaClass](#parsenestedjavaclass-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>嵌套类解析.|
|12|protected void [parseSuperJavaClass](#parsesuperjavaclass-javaclassmeta-typedeclaration)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration)   <br/><br/>|
|13|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   <br/><br/>|
|14|public void [setJavaParserMemberParseList](#setjavaparsermemberparselist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   <br/><br/>|


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

> **1.<span id="abstractjavaparsertypedeclarationparse-list">AbstractJavaParserTypeDeclarationParse</span>**

**构造方法签名：** 

  public  [AbstractJavaParserTypeDeclarationParse](#abstractjavaparsertypedeclarationparse-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   








## 方法详细信息


---

> **1.<span id="convertclassorinterfacetypetosimpleclassmeta-classorinterfacetype">convertClassOrInterfaceTypeToSimpleClassMeta</span>**

**方法签名：** 

  protected [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [convertClassOrInterfaceTypeToSimpleClassMeta](#convertclassorinterfacetypetosimpleclassmeta-classorinterfacetype)(ClassOrInterfaceType implementedType)   










---

> **2.<span id="doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext">doParseChildTypeToJavaClassMeta</span>**

**方法签名：** 

  protected abstract [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [doParseChildTypeToJavaClassMeta](#doparsechildtypetojavaclassmeta-metafileinfodto-javaclassmeta-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **3.<span id="getjavaparsermemberparselist">getJavaParserMemberParseList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaParserMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/JavaParserMemberParse.md) > [getJavaParserMemberParseList](#getjavaparsermemberparselist)()   










---

> **4.<span id="getrefclassresolvedtype-classorinterfacetype">getRefClassResolvedType</span>**

**方法签名：** 

  protected ResolvedType [getRefClassResolvedType](#getrefclassresolvedtype-classorinterfacetype)(ClassOrInterfaceType implementedType)   










---

> **5.<span id="parseclassdeclaration-javaclassmeta-javaparsermetacontext">parseClassDeclaration</span>**

**方法签名：** 

  protected void [parseClassDeclaration](#parseclassdeclaration-javaclassmeta-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


**描述：** 

解析类声明结构

**参数描述：** 

  javaClassMeta - 








---

> **6.<span id="parseclassmetaprop-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext">parseClassMetaProp</span>**

**方法签名：** 

  protected void [parseClassMetaProp](#parseclassmetaprop-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfoDto,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


**描述：** 

解析class基本信息

**参数描述：** 

  javaClassMeta - 

  metaFileInfoDto - 

  typeDeclaration - 








---

> **7.<span id="parsedocandannotation-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext">parseDocAndAnnotation</span>**

**方法签名：** 

  protected void [parseDocAndAnnotation](#parsedocandannotation-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **8.<span id="parsejavasouce-javaclassmeta-compilationunit-javaparsermetacontext">parseJavaSouce</span>**

**方法签名：** 

  protected void [parseJavaSouce](#parsejavasouce-javaclassmeta-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **9.<span id="parsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext">parseMembers</span>**

**方法签名：** 

  protected void [parseMembers](#parsemembers-javaclassmeta-metafileinfodto-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,[MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **10.<span id="parsemodifiers-javaclassmeta-typedeclaration-javaparsermetacontext">parseModifiers</span>**

**方法签名：** 

  protected void [parseModifiers](#parsemodifiers-javaclassmeta-typedeclaration-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **11.<span id="parsenestedjavaclass-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext">parseNestedJavaClass</span>**

**方法签名：** 

  protected void [parseNestedJavaClass](#parsenestedjavaclass-javaclassmeta-typedeclaration-compilationunit-javaparsermetacontext)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration,CompilationUnit rootAst,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   


**描述：** 

嵌套类解析

**参数描述：** 

  javaClassMeta - 

  typeDeclaration - 

  rootAst - 








---

> **12.<span id="parsesuperjavaclass-javaclassmeta-typedeclaration">parseSuperJavaClass</span>**

**方法签名：** 

  protected void [parseSuperJavaClass](#parsesuperjavaclass-javaclassmeta-typedeclaration)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta,TypeDeclaration< ? > typeDeclaration)   










---

> **13.<span id="parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext">parseTypeToJavaClassMeta</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [parseTypeToJavaClassMeta](#parsetypetojavaclassmeta-metafileinfodto-compilationunit-typedeclaration-javaparsermetacontext)([MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) metaFileInfo,CompilationUnit rootAst,TypeDeclaration< ? > typeDeclaration,[JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext)   










---

> **14.<span id="setjavaparsermemberparselist-list">setJavaParserMemberParseList</span>**

**方法签名：** 

  public void [setJavaParserMemberParseList](#setjavaparsermemberparselist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserMemberParse > javaParserMemberParseList)   









