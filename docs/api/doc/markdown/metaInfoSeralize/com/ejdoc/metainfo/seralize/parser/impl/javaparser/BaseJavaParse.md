# 类名称:BaseJavaParse

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.BaseJavaParse|













**所有子类：**  
[AbstractJavaParseMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AbstractJavaParseMemberParse.md),[FieldMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/FieldMemberParse.md),[MethodMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/MethodMemberParse.md),[ConstructorMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/ConstructorMemberParse.md),[EnumMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/EnumMemberParse.md)
,[NestedClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/NestedClassMemberParse.md),[AnnotationMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/AnnotationMemberParse.md),[InnerClassMemberParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/member/InnerClassMemberParse.md),[AbstractJavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AbstractJavaParserTypeDeclarationParse.md),[EnumTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/EnumTypeDeclarationParse.md)
,[AnnotationTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/AnnotationTypeDeclarationParse.md),[ClassTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/ClassTypeDeclarationParse.md),[PackageInfoTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/PackageInfoTypeDeclarationParse.md),[NestedClassMemberExtractParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/NestedClassMemberExtractParse.md)





---

## 声明信息

> public class BaseJavaParse     


**描述：** 解析基类，公共方法












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected void [createJavaDocTag](#createjavadoctag-optional-javamodelmeta)(Optional< Javadoc > javadoc,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   <br/><br/>创建javadoc标签.|
|2|protected void [parseExpression](#parseexpression-expression-list)(Expression expression,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > result)   <br/><br/>|
|3|protected void [setAnnotationExpr](#setannotationexpr-annotationexpr-javamodelmeta)(AnnotationExpr annotation,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   <br/><br/>|
|4|protected void [setAnnotations](#setannotations-nodelist-javamodelmeta)(NodeList< AnnotationExpr > annotations,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   <br/><br/>|
|5|protected void [setFieldResolvedTypeDeclaration](#setfieldresolvedtypedeclaration-javaclassmeta-type)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) returnTypeMeta,Type type)   <br/><br/>|
|6|protected void [setFullClassNameFromResolvedType](#setfullclassnamefromresolvedtype-javaclassmeta-resolvedtype)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) classMeta,ResolvedType resolve)   <br/><br/>|







## 方法详细信息


---

> **1.<span id="createjavadoctag-optional-javamodelmeta">createJavaDocTag</span>**

**方法签名：** 

  protected void [createJavaDocTag](#createjavadoctag-optional-javamodelmeta)(Optional< Javadoc > javadoc,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   


**描述：** 

创建javadoc标签

**参数描述：** 

  javadoc - 

  javaModelMeta - 








---

> **2.<span id="parseexpression-expression-list">parseExpression</span>**

**方法签名：** 

  protected void [parseExpression](#parseexpression-expression-list)(Expression expression,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > result)   










---

> **3.<span id="setannotationexpr-annotationexpr-javamodelmeta">setAnnotationExpr</span>**

**方法签名：** 

  protected void [setAnnotationExpr](#setannotationexpr-annotationexpr-javamodelmeta)(AnnotationExpr annotation,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   










---

> **4.<span id="setannotations-nodelist-javamodelmeta">setAnnotations</span>**

**方法签名：** 

  protected void [setAnnotations](#setannotations-nodelist-javamodelmeta)(NodeList< AnnotationExpr > annotations,[JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   










---

> **5.<span id="setfieldresolvedtypedeclaration-javaclassmeta-type">setFieldResolvedTypeDeclaration</span>**

**方法签名：** 

  protected void [setFieldResolvedTypeDeclaration](#setfieldresolvedtypedeclaration-javaclassmeta-type)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) returnTypeMeta,Type type)   










---

> **6.<span id="setfullclassnamefromresolvedtype-javaclassmeta-resolvedtype">setFullClassNameFromResolvedType</span>**

**方法签名：** 

  protected void [setFullClassNameFromResolvedType](#setfullclassnamefromresolvedtype-javaclassmeta-resolvedtype)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) classMeta,ResolvedType resolve)   









