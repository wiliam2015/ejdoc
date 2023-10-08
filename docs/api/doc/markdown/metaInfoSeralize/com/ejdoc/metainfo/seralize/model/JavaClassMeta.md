# 类名称:JavaClassMeta

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.model    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.model.JavaClassMeta|



















---

## 声明信息

> public class JavaClassMeta   implements [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html?is-external=true)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [JavaClassMeta](#javaclassmeta)()   <br/><br/>|
|2|public  [JavaClassMeta](#javaclassmeta-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) className,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getAbsolutePath](#getabsolutepath)()   <br/><br/>|
|2|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getAbstractClass](#getabstractclass)()   <br/><br/>|
|3|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getAnnotationClass](#getannotationclass)()   <br/><br/>|
|4|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaAnnotationMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaAnnotationMeta.md) > [getAnnotationFields](#getannotationfields)()   <br/><br/>|
|5|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getArrayFullClassName](#getarrayfullclassname)()   <br/><br/>|
|6|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassDesc](#getclassdesc)()   <br/><br/>|
|7|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassName](#getclassname)()   <br/><br/>|
|8|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassNamePrefix](#getclassnameprefix)()   <br/><br/>|
|9|public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getConstructorMetaIndex](#getconstructormetaindex)()   <br/><br/>|
|10|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaConstructorMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaConstructorMeta.md) > [getConstructors](#getconstructors)()   <br/><br/>|
|11|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDeclarationStructure](#getdeclarationstructure)()   <br/><br/>|
|12|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDependencyAbsolutePath](#getdependencyabsolutepath)()   <br/><br/>|
|13|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDependencyRelativePath](#getdependencyrelativepath)()   <br/><br/>|
|14|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getEnumClass](#getenumclass)()   <br/><br/>|
|15|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getEnumConstants](#getenumconstants)()   <br/><br/>|
|16|public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getFieldMetaIndex](#getfieldmetaindex)()   <br/><br/>|
|17|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaFieldMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaFieldMeta.md) > [getFields](#getfields)()   <br/><br/>|
|18|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getFinalClass](#getfinalclass)()   <br/><br/>|
|19|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getFullClassName](#getfullclassname)()   <br/><br/>|
|20|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassImportMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassImportMeta.md) > [getImports](#getimports)()   <br/><br/>|
|21|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getInnerClass](#getinnerclass)()   <br/><br/>|
|22|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInnerClasses](#getinnerclasses)()   <br/><br/>|
|23|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getInterfaceClass](#getinterfaceclass)()   <br/><br/>|
|24|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInterfaces](#getinterfaces)()   <br/><br/>|
|25|public [JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) [getJavaModelMeta](#getjavamodelmeta)()   <br/><br/>|
|26|public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getMethodMetaIndex](#getmethodmetaindex)()   <br/><br/>|
|27|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) > [getMethods](#getmethods)()   <br/><br/>|
|28|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getModifiers](#getmodifiers)()   <br/><br/>|
|29|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getModuleDesc](#getmoduledesc)()   <br/><br/>|
|30|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getModuleName](#getmodulename)()   <br/><br/>|
|31|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getNestedClass](#getnestedclass)()   <br/><br/>|
|32|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getNestedClassFullClassName](#getnestedclassfullclassname)()   <br/><br/>|
|33|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getNestedClassName](#getnestedclassname)()   <br/><br/>|
|34|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getNestedClasses](#getnestedclasses)()   <br/><br/>|
|35|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getPackageDesc](#getpackagedesc)()   <br/><br/>|
|36|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getPackageName](#getpackagename)()   <br/><br/>|
|37|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPrimitiveClass](#getprimitiveclass)()   <br/><br/>|
|38|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPrivateClass](#getprivateclass)()   <br/><br/>|
|39|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectName](#getprojectname)()   <br/><br/>|
|40|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getProtectedClass](#getprotectedclass)()   <br/><br/>|
|41|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPublicClass](#getpublicclass)()   <br/><br/>|
|42|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getRelativePath](#getrelativepath)()   <br/><br/>|
|43|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getStaticClass](#getstaticclass)()   <br/><br/>|
|44|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getSuperClasses](#getsuperclasses)()   <br/><br/>|
|45|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getTypeArguments](#gettypearguments)()   <br/><br/>|
|46|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getTypeParameters](#gettypeparameters)()   <br/><br/>|
|47|public [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html?is-external=true) [getUrl](#geturl)()   <br/><br/>|
|48|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getValue](#getvalue)()   <br/><br/>|
|49|public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getVoidClass](#getvoidclass)()   <br/><br/>|
|50|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [parseDeclarationStructure](#parsedeclarationstructure)()   <br/><br/>|
|51|public void [setAbsolutePath](#setabsolutepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath)   <br/><br/>|
|52|public void [setAbstractClass](#setabstractclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) abstractClass)   <br/><br/>|
|53|public void [setAnnotationClass](#setannotationclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) annotationClass)   <br/><br/>|
|54|public void [setAnnotationFields](#setannotationfields-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaAnnotationMeta > annotationFields)   <br/><br/>|
|55|public void [setArrayFullClassName](#setarrayfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) arrayFullClassName)   <br/><br/>|
|56|public void [setClassDesc](#setclassdesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classDesc)   <br/><br/>|
|57|public void [setClassName](#setclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) className)   <br/><br/>|
|58|public void [setClassNamePrefix](#setclassnameprefix-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classNamePrefix)   <br/><br/>|
|59|public void [setConstructorMetaIndex](#setconstructormetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > constructorMetaIndex)   <br/><br/>|
|60|public void [setConstructors](#setconstructors-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaConstructorMeta > constructors)   <br/><br/>|
|61|public void [setDeclarationStructure](#setdeclarationstructure-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) declarationStructure)   <br/><br/>|
|62|public void [setDependencyAbsolutePath](#setdependencyabsolutepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) dependencyAbsolutePath)   <br/><br/>|
|63|public void [setDependencyRelativePath](#setdependencyrelativepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) dependencyRelativePath)   <br/><br/>|
|64|public void [setEnumClass](#setenumclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) enumClass)   <br/><br/>|
|65|public void [setEnumConstants](#setenumconstants-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > enumConstants)   <br/><br/>|
|66|public void [setFieldMetaIndex](#setfieldmetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > fieldMetaIndex)   <br/><br/>|
|67|public void [setFields](#setfields-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaFieldMeta > fields)   <br/><br/>|
|68|public void [setFinalClass](#setfinalclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) finalClass)   <br/><br/>|
|69|public void [setFullClassName](#setfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   <br/><br/>|
|70|public void [setImports](#setimports-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassImportMeta > imports)   <br/><br/>|
|71|public void [setInnerClass](#setinnerclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) innerClass)   <br/><br/>|
|72|public void [setInnerClasses](#setinnerclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > innerClasses)   <br/><br/>|
|73|public void [setInterfaceClass](#setinterfaceclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) interfaceClass)   <br/><br/>|
|74|public void [setInterfaces](#setinterfaces-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > interfaces)   <br/><br/>|
|75|public void [setJavaModelMeta](#setjavamodelmeta-javamodelmeta)([JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   <br/><br/>|
|76|public void [setMethodMetaIndex](#setmethodmetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > methodMetaIndex)   <br/><br/>|
|77|public void [setMethods](#setmethods-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaMethodMeta > methods)   <br/><br/>|
|78|public void [setModifiers](#setmodifiers-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > modifiers)   <br/><br/>|
|79|public void [setModuleDesc](#setmoduledesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleDesc)   <br/><br/>|
|80|public void [setModuleName](#setmodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   <br/><br/>|
|81|public void [setNestedClass](#setnestedclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) nestedClass)   <br/><br/>|
|82|public void [setNestedClassFullClassName](#setnestedclassfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) nestedClassFullClassName)   <br/><br/>|
|83|public void [setNestedClassName](#setnestedclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) nestedClassName)   <br/><br/>|
|84|public void [setNestedClasses](#setnestedclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > nestedClasses)   <br/><br/>|
|85|public void [setPackageDesc](#setpackagedesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageDesc)   <br/><br/>|
|86|public void [setPackageName](#setpackagename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageName)   <br/><br/>|
|87|public void [setPrimitiveClass](#setprimitiveclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) primitiveClass)   <br/><br/>|
|88|public void [setPrivateClass](#setprivateclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) privateClass)   <br/><br/>|
|89|public void [setProjectName](#setprojectname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) projectName)   <br/><br/>|
|90|public void [setProtectedClass](#setprotectedclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) protectedClass)   <br/><br/>|
|91|public void [setPublicClass](#setpublicclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) publicClass)   <br/><br/>|
|92|public void [setRelativePath](#setrelativepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) relativePath)   <br/><br/>|
|93|public void [setStaticClass](#setstaticclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) staticClass)   <br/><br/>|
|94|public void [setSuperClasses](#setsuperclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > superClasses)   <br/><br/>|
|95|public void [setTypeArguments](#settypearguments-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > typeArguments)   <br/><br/>|
|96|public void [setTypeParameters](#settypeparameters-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > typeParameters)   <br/><br/>|
|97|public void [setUrl](#seturl-url)([URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html?is-external=true) url)   <br/><br/>|
|98|public void [setValue](#setvalue-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) value)   <br/><br/>|
|99|public void [setVoidClass](#setvoidclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) voidClass)   <br/><br/>|






## 构造方法详细信息


---

> **1.<span id="javaclassmeta">JavaClassMeta</span>**

**构造方法签名：** 

  public  [JavaClassMeta](#javaclassmeta)()   








---

> **2.<span id="javaclassmeta-string-string">JavaClassMeta</span>**

**构造方法签名：** 

  public  [JavaClassMeta](#javaclassmeta-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) className,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   








## 方法详细信息


---

> **1.<span id="getabsolutepath">getAbsolutePath</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getAbsolutePath](#getabsolutepath)()   










---

> **2.<span id="getabstractclass">getAbstractClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getAbstractClass](#getabstractclass)()   










---

> **3.<span id="getannotationclass">getAnnotationClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getAnnotationClass](#getannotationclass)()   










---

> **4.<span id="getannotationfields">getAnnotationFields</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaAnnotationMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaAnnotationMeta.md) > [getAnnotationFields](#getannotationfields)()   










---

> **5.<span id="getarrayfullclassname">getArrayFullClassName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getArrayFullClassName](#getarrayfullclassname)()   










---

> **6.<span id="getclassdesc">getClassDesc</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassDesc](#getclassdesc)()   










---

> **7.<span id="getclassname">getClassName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassName](#getclassname)()   










---

> **8.<span id="getclassnameprefix">getClassNamePrefix</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getClassNamePrefix](#getclassnameprefix)()   










---

> **9.<span id="getconstructormetaindex">getConstructorMetaIndex</span>**

**方法签名：** 

  public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getConstructorMetaIndex](#getconstructormetaindex)()   










---

> **10.<span id="getconstructors">getConstructors</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaConstructorMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaConstructorMeta.md) > [getConstructors](#getconstructors)()   










---

> **11.<span id="getdeclarationstructure">getDeclarationStructure</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDeclarationStructure](#getdeclarationstructure)()   










---

> **12.<span id="getdependencyabsolutepath">getDependencyAbsolutePath</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDependencyAbsolutePath](#getdependencyabsolutepath)()   










---

> **13.<span id="getdependencyrelativepath">getDependencyRelativePath</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getDependencyRelativePath](#getdependencyrelativepath)()   










---

> **14.<span id="getenumclass">getEnumClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getEnumClass](#getenumclass)()   










---

> **15.<span id="getenumconstants">getEnumConstants</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getEnumConstants](#getenumconstants)()   










---

> **16.<span id="getfieldmetaindex">getFieldMetaIndex</span>**

**方法签名：** 

  public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getFieldMetaIndex](#getfieldmetaindex)()   










---

> **17.<span id="getfields">getFields</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaFieldMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaFieldMeta.md) > [getFields](#getfields)()   










---

> **18.<span id="getfinalclass">getFinalClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getFinalClass](#getfinalclass)()   










---

> **19.<span id="getfullclassname">getFullClassName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getFullClassName](#getfullclassname)()   










---

> **20.<span id="getimports">getImports</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassImportMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassImportMeta.md) > [getImports](#getimports)()   










---

> **21.<span id="getinnerclass">getInnerClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getInnerClass](#getinnerclass)()   










---

> **22.<span id="getinnerclasses">getInnerClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInnerClasses](#getinnerclasses)()   










---

> **23.<span id="getinterfaceclass">getInterfaceClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getInterfaceClass](#getinterfaceclass)()   










---

> **24.<span id="getinterfaces">getInterfaces</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInterfaces](#getinterfaces)()   










---

> **25.<span id="getjavamodelmeta">getJavaModelMeta</span>**

**方法签名：** 

  public [JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) [getJavaModelMeta](#getjavamodelmeta)()   










---

> **26.<span id="getmethodmetaindex">getMethodMetaIndex</span>**

**方法签名：** 

  public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getMethodMetaIndex](#getmethodmetaindex)()   










---

> **27.<span id="getmethods">getMethods</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMethodMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaMethodMeta.md) > [getMethods](#getmethods)()   










---

> **28.<span id="getmodifiers">getModifiers</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getModifiers](#getmodifiers)()   










---

> **29.<span id="getmoduledesc">getModuleDesc</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getModuleDesc](#getmoduledesc)()   










---

> **30.<span id="getmodulename">getModuleName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getModuleName](#getmodulename)()   










---

> **31.<span id="getnestedclass">getNestedClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getNestedClass](#getnestedclass)()   










---

> **32.<span id="getnestedclassfullclassname">getNestedClassFullClassName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getNestedClassFullClassName](#getnestedclassfullclassname)()   










---

> **33.<span id="getnestedclassname">getNestedClassName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getNestedClassName](#getnestedclassname)()   










---

> **34.<span id="getnestedclasses">getNestedClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getNestedClasses](#getnestedclasses)()   










---

> **35.<span id="getpackagedesc">getPackageDesc</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getPackageDesc](#getpackagedesc)()   










---

> **36.<span id="getpackagename">getPackageName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getPackageName](#getpackagename)()   










---

> **37.<span id="getprimitiveclass">getPrimitiveClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPrimitiveClass](#getprimitiveclass)()   










---

> **38.<span id="getprivateclass">getPrivateClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPrivateClass](#getprivateclass)()   










---

> **39.<span id="getprojectname">getProjectName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectName](#getprojectname)()   










---

> **40.<span id="getprotectedclass">getProtectedClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getProtectedClass](#getprotectedclass)()   










---

> **41.<span id="getpublicclass">getPublicClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getPublicClass](#getpublicclass)()   










---

> **42.<span id="getrelativepath">getRelativePath</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getRelativePath](#getrelativepath)()   










---

> **43.<span id="getstaticclass">getStaticClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getStaticClass](#getstaticclass)()   










---

> **44.<span id="getsuperclasses">getSuperClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getSuperClasses](#getsuperclasses)()   










---

> **45.<span id="gettypearguments">getTypeArguments</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getTypeArguments](#gettypearguments)()   










---

> **46.<span id="gettypeparameters">getTypeParameters</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getTypeParameters](#gettypeparameters)()   










---

> **47.<span id="geturl">getUrl</span>**

**方法签名：** 

  public [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html?is-external=true) [getUrl](#geturl)()   










---

> **48.<span id="getvalue">getValue</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getValue](#getvalue)()   










---

> **49.<span id="getvoidclass">getVoidClass</span>**

**方法签名：** 

  public [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [getVoidClass](#getvoidclass)()   










---

> **50.<span id="parsedeclarationstructure">parseDeclarationStructure</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [parseDeclarationStructure](#parsedeclarationstructure)()   










---

> **51.<span id="setabsolutepath-string">setAbsolutePath</span>**

**方法签名：** 

  public void [setAbsolutePath](#setabsolutepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath)   










---

> **52.<span id="setabstractclass-boolean">setAbstractClass</span>**

**方法签名：** 

  public void [setAbstractClass](#setabstractclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) abstractClass)   










---

> **53.<span id="setannotationclass-boolean">setAnnotationClass</span>**

**方法签名：** 

  public void [setAnnotationClass](#setannotationclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) annotationClass)   










---

> **54.<span id="setannotationfields-list">setAnnotationFields</span>**

**方法签名：** 

  public void [setAnnotationFields](#setannotationfields-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaAnnotationMeta > annotationFields)   










---

> **55.<span id="setarrayfullclassname-string">setArrayFullClassName</span>**

**方法签名：** 

  public void [setArrayFullClassName](#setarrayfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) arrayFullClassName)   










---

> **56.<span id="setclassdesc-string">setClassDesc</span>**

**方法签名：** 

  public void [setClassDesc](#setclassdesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classDesc)   










---

> **57.<span id="setclassname-string">setClassName</span>**

**方法签名：** 

  public void [setClassName](#setclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) className)   










---

> **58.<span id="setclassnameprefix-string">setClassNamePrefix</span>**

**方法签名：** 

  public void [setClassNamePrefix](#setclassnameprefix-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classNamePrefix)   










---

> **59.<span id="setconstructormetaindex-map">setConstructorMetaIndex</span>**

**方法签名：** 

  public void [setConstructorMetaIndex](#setconstructormetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > constructorMetaIndex)   










---

> **60.<span id="setconstructors-list">setConstructors</span>**

**方法签名：** 

  public void [setConstructors](#setconstructors-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaConstructorMeta > constructors)   










---

> **61.<span id="setdeclarationstructure-string">setDeclarationStructure</span>**

**方法签名：** 

  public void [setDeclarationStructure](#setdeclarationstructure-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) declarationStructure)   










---

> **62.<span id="setdependencyabsolutepath-string">setDependencyAbsolutePath</span>**

**方法签名：** 

  public void [setDependencyAbsolutePath](#setdependencyabsolutepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) dependencyAbsolutePath)   










---

> **63.<span id="setdependencyrelativepath-string">setDependencyRelativePath</span>**

**方法签名：** 

  public void [setDependencyRelativePath](#setdependencyrelativepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) dependencyRelativePath)   










---

> **64.<span id="setenumclass-boolean">setEnumClass</span>**

**方法签名：** 

  public void [setEnumClass](#setenumclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) enumClass)   










---

> **65.<span id="setenumconstants-list">setEnumConstants</span>**

**方法签名：** 

  public void [setEnumConstants](#setenumconstants-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > enumConstants)   










---

> **66.<span id="setfieldmetaindex-map">setFieldMetaIndex</span>**

**方法签名：** 

  public void [setFieldMetaIndex](#setfieldmetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > fieldMetaIndex)   










---

> **67.<span id="setfields-list">setFields</span>**

**方法签名：** 

  public void [setFields](#setfields-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaFieldMeta > fields)   










---

> **68.<span id="setfinalclass-boolean">setFinalClass</span>**

**方法签名：** 

  public void [setFinalClass](#setfinalclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) finalClass)   










---

> **69.<span id="setfullclassname-string">setFullClassName</span>**

**方法签名：** 

  public void [setFullClassName](#setfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   










---

> **70.<span id="setimports-list">setImports</span>**

**方法签名：** 

  public void [setImports](#setimports-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassImportMeta > imports)   










---

> **71.<span id="setinnerclass-boolean">setInnerClass</span>**

**方法签名：** 

  public void [setInnerClass](#setinnerclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) innerClass)   










---

> **72.<span id="setinnerclasses-list">setInnerClasses</span>**

**方法签名：** 

  public void [setInnerClasses](#setinnerclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > innerClasses)   










---

> **73.<span id="setinterfaceclass-boolean">setInterfaceClass</span>**

**方法签名：** 

  public void [setInterfaceClass](#setinterfaceclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) interfaceClass)   










---

> **74.<span id="setinterfaces-list">setInterfaces</span>**

**方法签名：** 

  public void [setInterfaces](#setinterfaces-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > interfaces)   










---

> **75.<span id="setjavamodelmeta-javamodelmeta">setJavaModelMeta</span>**

**方法签名：** 

  public void [setJavaModelMeta](#setjavamodelmeta-javamodelmeta)([JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   










---

> **76.<span id="setmethodmetaindex-map">setMethodMetaIndex</span>**

**方法签名：** 

  public void [setMethodMetaIndex](#setmethodmetaindex-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > methodMetaIndex)   










---

> **77.<span id="setmethods-list">setMethods</span>**

**方法签名：** 

  public void [setMethods](#setmethods-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaMethodMeta > methods)   










---

> **78.<span id="setmodifiers-list">setModifiers</span>**

**方法签名：** 

  public void [setModifiers](#setmodifiers-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > modifiers)   










---

> **79.<span id="setmoduledesc-string">setModuleDesc</span>**

**方法签名：** 

  public void [setModuleDesc](#setmoduledesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleDesc)   










---

> **80.<span id="setmodulename-string">setModuleName</span>**

**方法签名：** 

  public void [setModuleName](#setmodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   










---

> **81.<span id="setnestedclass-boolean">setNestedClass</span>**

**方法签名：** 

  public void [setNestedClass](#setnestedclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) nestedClass)   










---

> **82.<span id="setnestedclassfullclassname-string">setNestedClassFullClassName</span>**

**方法签名：** 

  public void [setNestedClassFullClassName](#setnestedclassfullclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) nestedClassFullClassName)   










---

> **83.<span id="setnestedclassname-string">setNestedClassName</span>**

**方法签名：** 

  public void [setNestedClassName](#setnestedclassname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) nestedClassName)   










---

> **84.<span id="setnestedclasses-list">setNestedClasses</span>**

**方法签名：** 

  public void [setNestedClasses](#setnestedclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > nestedClasses)   










---

> **85.<span id="setpackagedesc-string">setPackageDesc</span>**

**方法签名：** 

  public void [setPackageDesc](#setpackagedesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageDesc)   










---

> **86.<span id="setpackagename-string">setPackageName</span>**

**方法签名：** 

  public void [setPackageName](#setpackagename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageName)   










---

> **87.<span id="setprimitiveclass-boolean">setPrimitiveClass</span>**

**方法签名：** 

  public void [setPrimitiveClass](#setprimitiveclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) primitiveClass)   










---

> **88.<span id="setprivateclass-boolean">setPrivateClass</span>**

**方法签名：** 

  public void [setPrivateClass](#setprivateclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) privateClass)   










---

> **89.<span id="setprojectname-string">setProjectName</span>**

**方法签名：** 

  public void [setProjectName](#setprojectname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) projectName)   










---

> **90.<span id="setprotectedclass-boolean">setProtectedClass</span>**

**方法签名：** 

  public void [setProtectedClass](#setprotectedclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) protectedClass)   










---

> **91.<span id="setpublicclass-boolean">setPublicClass</span>**

**方法签名：** 

  public void [setPublicClass](#setpublicclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) publicClass)   










---

> **92.<span id="setrelativepath-string">setRelativePath</span>**

**方法签名：** 

  public void [setRelativePath](#setrelativepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) relativePath)   










---

> **93.<span id="setstaticclass-boolean">setStaticClass</span>**

**方法签名：** 

  public void [setStaticClass](#setstaticclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) staticClass)   










---

> **94.<span id="setsuperclasses-list">setSuperClasses</span>**

**方法签名：** 

  public void [setSuperClasses](#setsuperclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > superClasses)   










---

> **95.<span id="settypearguments-list">setTypeArguments</span>**

**方法签名：** 

  public void [setTypeArguments](#settypearguments-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > typeArguments)   










---

> **96.<span id="settypeparameters-list">setTypeParameters</span>**

**方法签名：** 

  public void [setTypeParameters](#settypeparameters-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > typeParameters)   










---

> **97.<span id="seturl-url">setUrl</span>**

**方法签名：** 

  public void [setUrl](#seturl-url)([URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html?is-external=true) url)   










---

> **98.<span id="setvalue-string">setValue</span>**

**方法签名：** 

  public void [setValue](#setvalue-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) value)   










---

> **99.<span id="setvoidclass-boolean">setVoidClass</span>**

**方法签名：** 

  public void [setVoidClass](#setvoidclass-boolean)([Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) voidClass)   









