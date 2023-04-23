# 类名称:JavaClassMeta

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.model.JavaClassMeta
* **包名称:** com.ejdoc.metainfo.seralize.model
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize






* **所有父级接口：**  
Serializable


---

## 声明信息
> public class JavaClassMeta   implements Serializable   


* **描述：** 

  








## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public String [getAbsolutePath](#innerlink-getabsolutepath)()   <br/>|
|2|public Boolean [getAbstractClass](#innerlink-getabstractclass)()   <br/>|
|3|public Boolean [getAnnotationClass](#innerlink-getannotationclass)()   <br/>|
|4|public List< JavaAnnotationMeta > [getAnnotationFields](#innerlink-getannotationfields)()   <br/>|
|5|public String [getArrayFullClassName](#innerlink-getarrayfullclassname)()   <br/>|
|6|public String [getClassName](#innerlink-getclassname)()   <br/>|
|7|public String [getClassNamePrefix](#innerlink-getclassnameprefix)()   <br/>|
|8|public List< JavaConstructorMeta > [getConstructors](#innerlink-getconstructors)()   <br/>|
|9|public String [getDeclarationStructure](#innerlink-getdeclarationstructure)()   <br/>|
|10|public String [getDependencyAbsolutePath](#innerlink-getdependencyabsolutepath)()   <br/>|
|11|public String [getDependencyRelativePath](#innerlink-getdependencyrelativepath)()   <br/>|
|12|public Boolean [getEnumClass](#innerlink-getenumclass)()   <br/>|
|13|public List< JavaClassMeta > [getEnumConstants](#innerlink-getenumconstants)()   <br/>|
|14|public List< JavaFieldMeta > [getFields](#innerlink-getfields)()   <br/>|
|15|public Boolean [getFinalClass](#innerlink-getfinalclass)()   <br/>|
|16|public String [getFullClassName](#innerlink-getfullclassname)()   <br/>|
|17|public List< String > [getImports](#innerlink-getimports)()   <br/>|
|18|public Boolean [getInnerClass](#innerlink-getinnerclass)()   <br/>|
|19|public List< JavaClassMeta > [getInnerClasses](#innerlink-getinnerclasses)()   <br/>|
|20|public Boolean [getInterfaceClass](#innerlink-getinterfaceclass)()   <br/>|
|21|public List< JavaClassMeta > [getInterfaces](#innerlink-getinterfaces)()   <br/>|
|22|public [JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) [getJavaModelMeta](#innerlink-getjavamodelmeta)()   <br/>|
|23|public List< JavaMethodMeta > [getMethods](#innerlink-getmethods)()   <br/>|
|24|public List< String > [getModifiers](#innerlink-getmodifiers)()   <br/>|
|25|public String [getModuleDesc](#innerlink-getmoduledesc)()   <br/>|
|26|public String [getModuleName](#innerlink-getmodulename)()   <br/>|
|27|public String [getPackageDesc](#innerlink-getpackagedesc)()   <br/>|
|28|public String [getPackageName](#innerlink-getpackagename)()   <br/>|
|29|public Boolean [getPrimitiveClass](#innerlink-getprimitiveclass)()   <br/>|
|30|public Boolean [getPrivateClass](#innerlink-getprivateclass)()   <br/>|
|31|public String [getProjectName](#innerlink-getprojectname)()   <br/>|
|32|public Boolean [getProtectedClass](#innerlink-getprotectedclass)()   <br/>|
|33|public Boolean [getPublicClass](#innerlink-getpublicclass)()   <br/>|
|34|public String [getRelativePath](#innerlink-getrelativepath)()   <br/>|
|35|public Boolean [getStaticClass](#innerlink-getstaticclass)()   <br/>|
|36|public List< JavaClassMeta > [getSuperClasses](#innerlink-getsuperclasses)()   <br/>|
|37|public List< JavaClassMeta > [getTypeArguments](#innerlink-gettypearguments)()   <br/>|
|38|public List< String > [getTypeParameters](#innerlink-gettypeparameters)()   <br/>|
|39|public URL [getUrl](#innerlink-geturl)()   <br/>|
|40|public String [getValue](#innerlink-getvalue)()   <br/>|
|41|public Boolean [getVoidClass](#innerlink-getvoidclass)()   <br/>|
|42|public String [parseDeclarationStructure](#innerlink-parsedeclarationstructure)()   <br/>|
|43|public void [setAbsolutePath](#innerlink-setabsolutepath-javalangstring)(String absolutePath)   <br/>|
|44|public void [setAbstractClass](#innerlink-setabstractclass-javalangboolean)(Boolean abstractClass)   <br/>|
|45|public void [setAnnotationClass](#innerlink-setannotationclass-javalangboolean)(Boolean annotationClass)   <br/>|
|46|public void [setAnnotationFields](#innerlink-setannotationfields-javautillist)(List< JavaAnnotationMeta > annotationFields)   <br/>|
|47|public void [setArrayFullClassName](#innerlink-setarrayfullclassname-javalangstring)(String arrayFullClassName)   <br/>|
|48|public void [setClassName](#innerlink-setclassname-javalangstring)(String className)   <br/>|
|49|public void [setClassNamePrefix](#innerlink-setclassnameprefix-javalangstring)(String classNamePrefix)   <br/>|
|50|public void [setConstructors](#innerlink-setconstructors-javautillist)(List< JavaConstructorMeta > constructors)   <br/>|
|51|public void [setDeclarationStructure](#innerlink-setdeclarationstructure-javalangstring)(String declarationStructure)   <br/>|
|52|public void [setDependencyAbsolutePath](#innerlink-setdependencyabsolutepath-javalangstring)(String dependencyAbsolutePath)   <br/>|
|53|public void [setDependencyRelativePath](#innerlink-setdependencyrelativepath-javalangstring)(String dependencyRelativePath)   <br/>|
|54|public void [setEnumClass](#innerlink-setenumclass-javalangboolean)(Boolean enumClass)   <br/>|
|55|public void [setEnumConstants](#innerlink-setenumconstants-javautillist)(List< JavaClassMeta > enumConstants)   <br/>|
|56|public void [setFields](#innerlink-setfields-javautillist)(List< JavaFieldMeta > fields)   <br/>|
|57|public void [setFinalClass](#innerlink-setfinalclass-javalangboolean)(Boolean finalClass)   <br/>|
|58|public void [setFullClassName](#innerlink-setfullclassname-javalangstring)(String fullClassName)   <br/>|
|59|public void [setImports](#innerlink-setimports-javautillist)(List< String > imports)   <br/>|
|60|public void [setInnerClass](#innerlink-setinnerclass-javalangboolean)(Boolean innerClass)   <br/>|
|61|public void [setInnerClasses](#innerlink-setinnerclasses-javautillist)(List< JavaClassMeta > innerClasses)   <br/>|
|62|public void [setInterfaceClass](#innerlink-setinterfaceclass-javalangboolean)(Boolean interfaceClass)   <br/>|
|63|public void [setInterfaces](#innerlink-setinterfaces-javautillist)(List< JavaClassMeta > interfaces)   <br/>|
|64|public void [setJavaModelMeta](#innerlink-setjavamodelmeta-comejdocmetainfoseralizemodeljavamodelmeta)([JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   <br/>|
|65|public void [setMethods](#innerlink-setmethods-javautillist)(List< JavaMethodMeta > methods)   <br/>|
|66|public void [setModifiers](#innerlink-setmodifiers-javautillist)(List< String > modifiers)   <br/>|
|67|public void [setModuleDesc](#innerlink-setmoduledesc-javalangstring)(String moduleDesc)   <br/>|
|68|public void [setModuleName](#innerlink-setmodulename-javalangstring)(String moduleName)   <br/>|
|69|public void [setPackageDesc](#innerlink-setpackagedesc-javalangstring)(String packageDesc)   <br/>|
|70|public void [setPackageName](#innerlink-setpackagename-javalangstring)(String packageName)   <br/>|
|71|public void [setPrimitiveClass](#innerlink-setprimitiveclass-javalangboolean)(Boolean primitiveClass)   <br/>|
|72|public void [setPrivateClass](#innerlink-setprivateclass-javalangboolean)(Boolean privateClass)   <br/>|
|73|public void [setProjectName](#innerlink-setprojectname-javalangstring)(String projectName)   <br/>|
|74|public void [setProtectedClass](#innerlink-setprotectedclass-javalangboolean)(Boolean protectedClass)   <br/>|
|75|public void [setPublicClass](#innerlink-setpublicclass-javalangboolean)(Boolean publicClass)   <br/>|
|76|public void [setRelativePath](#innerlink-setrelativepath-javalangstring)(String relativePath)   <br/>|
|77|public void [setStaticClass](#innerlink-setstaticclass-javalangboolean)(Boolean staticClass)   <br/>|
|78|public void [setSuperClasses](#innerlink-setsuperclasses-javautillist)(List< JavaClassMeta > superClasses)   <br/>|
|79|public void [setTypeArguments](#innerlink-settypearguments-javautillist)(List< JavaClassMeta > typeArguments)   <br/>|
|80|public void [setTypeParameters](#innerlink-settypeparameters-javautillist)(List< String > typeParameters)   <br/>|
|81|public void [setUrl](#innerlink-seturl-javaneturl)(URL url)   <br/>|
|82|public void [setValue](#innerlink-setvalue-javalangstring)(String value)   <br/>|
|83|public void [setVoidClass](#innerlink-setvoidclass-javalangboolean)(Boolean voidClass)   <br/>|








## 方法详细信息

---
> **1.<span id="innerlink-getabsolutepath">getAbsolutePath</span>**

* **方法签名：** 

  public String [getAbsolutePath](#getabsolutepath)()   







---
> **2.<span id="innerlink-getabstractclass">getAbstractClass</span>**

* **方法签名：** 

  public Boolean [getAbstractClass](#getabstractclass)()   







---
> **3.<span id="innerlink-getannotationclass">getAnnotationClass</span>**

* **方法签名：** 

  public Boolean [getAnnotationClass](#getannotationclass)()   







---
> **4.<span id="innerlink-getannotationfields">getAnnotationFields</span>**

* **方法签名：** 

  public List< JavaAnnotationMeta > [getAnnotationFields](#getannotationfields)()   







---
> **5.<span id="innerlink-getarrayfullclassname">getArrayFullClassName</span>**

* **方法签名：** 

  public String [getArrayFullClassName](#getarrayfullclassname)()   







---
> **6.<span id="innerlink-getclassname">getClassName</span>**

* **方法签名：** 

  public String [getClassName](#getclassname)()   







---
> **7.<span id="innerlink-getclassnameprefix">getClassNamePrefix</span>**

* **方法签名：** 

  public String [getClassNamePrefix](#getclassnameprefix)()   







---
> **8.<span id="innerlink-getconstructors">getConstructors</span>**

* **方法签名：** 

  public List< JavaConstructorMeta > [getConstructors](#getconstructors)()   







---
> **9.<span id="innerlink-getdeclarationstructure">getDeclarationStructure</span>**

* **方法签名：** 

  public String [getDeclarationStructure](#getdeclarationstructure)()   







---
> **10.<span id="innerlink-getdependencyabsolutepath">getDependencyAbsolutePath</span>**

* **方法签名：** 

  public String [getDependencyAbsolutePath](#getdependencyabsolutepath)()   







---
> **11.<span id="innerlink-getdependencyrelativepath">getDependencyRelativePath</span>**

* **方法签名：** 

  public String [getDependencyRelativePath](#getdependencyrelativepath)()   







---
> **12.<span id="innerlink-getenumclass">getEnumClass</span>**

* **方法签名：** 

  public Boolean [getEnumClass](#getenumclass)()   







---
> **13.<span id="innerlink-getenumconstants">getEnumConstants</span>**

* **方法签名：** 

  public List< JavaClassMeta > [getEnumConstants](#getenumconstants)()   







---
> **14.<span id="innerlink-getfields">getFields</span>**

* **方法签名：** 

  public List< JavaFieldMeta > [getFields](#getfields)()   







---
> **15.<span id="innerlink-getfinalclass">getFinalClass</span>**

* **方法签名：** 

  public Boolean [getFinalClass](#getfinalclass)()   







---
> **16.<span id="innerlink-getfullclassname">getFullClassName</span>**

* **方法签名：** 

  public String [getFullClassName](#getfullclassname)()   







---
> **17.<span id="innerlink-getimports">getImports</span>**

* **方法签名：** 

  public List< String > [getImports](#getimports)()   







---
> **18.<span id="innerlink-getinnerclass">getInnerClass</span>**

* **方法签名：** 

  public Boolean [getInnerClass](#getinnerclass)()   







---
> **19.<span id="innerlink-getinnerclasses">getInnerClasses</span>**

* **方法签名：** 

  public List< JavaClassMeta > [getInnerClasses](#getinnerclasses)()   







---
> **20.<span id="innerlink-getinterfaceclass">getInterfaceClass</span>**

* **方法签名：** 

  public Boolean [getInterfaceClass](#getinterfaceclass)()   







---
> **21.<span id="innerlink-getinterfaces">getInterfaces</span>**

* **方法签名：** 

  public List< JavaClassMeta > [getInterfaces](#getinterfaces)()   







---
> **22.<span id="innerlink-getjavamodelmeta">getJavaModelMeta</span>**

* **方法签名：** 

  public [JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) [getJavaModelMeta](#getjavamodelmeta)()   







---
> **23.<span id="innerlink-getmethods">getMethods</span>**

* **方法签名：** 

  public List< JavaMethodMeta > [getMethods](#getmethods)()   







---
> **24.<span id="innerlink-getmodifiers">getModifiers</span>**

* **方法签名：** 

  public List< String > [getModifiers](#getmodifiers)()   







---
> **25.<span id="innerlink-getmoduledesc">getModuleDesc</span>**

* **方法签名：** 

  public String [getModuleDesc](#getmoduledesc)()   







---
> **26.<span id="innerlink-getmodulename">getModuleName</span>**

* **方法签名：** 

  public String [getModuleName](#getmodulename)()   







---
> **27.<span id="innerlink-getpackagedesc">getPackageDesc</span>**

* **方法签名：** 

  public String [getPackageDesc](#getpackagedesc)()   







---
> **28.<span id="innerlink-getpackagename">getPackageName</span>**

* **方法签名：** 

  public String [getPackageName](#getpackagename)()   







---
> **29.<span id="innerlink-getprimitiveclass">getPrimitiveClass</span>**

* **方法签名：** 

  public Boolean [getPrimitiveClass](#getprimitiveclass)()   







---
> **30.<span id="innerlink-getprivateclass">getPrivateClass</span>**

* **方法签名：** 

  public Boolean [getPrivateClass](#getprivateclass)()   







---
> **31.<span id="innerlink-getprojectname">getProjectName</span>**

* **方法签名：** 

  public String [getProjectName](#getprojectname)()   







---
> **32.<span id="innerlink-getprotectedclass">getProtectedClass</span>**

* **方法签名：** 

  public Boolean [getProtectedClass](#getprotectedclass)()   







---
> **33.<span id="innerlink-getpublicclass">getPublicClass</span>**

* **方法签名：** 

  public Boolean [getPublicClass](#getpublicclass)()   







---
> **34.<span id="innerlink-getrelativepath">getRelativePath</span>**

* **方法签名：** 

  public String [getRelativePath](#getrelativepath)()   







---
> **35.<span id="innerlink-getstaticclass">getStaticClass</span>**

* **方法签名：** 

  public Boolean [getStaticClass](#getstaticclass)()   







---
> **36.<span id="innerlink-getsuperclasses">getSuperClasses</span>**

* **方法签名：** 

  public List< JavaClassMeta > [getSuperClasses](#getsuperclasses)()   







---
> **37.<span id="innerlink-gettypearguments">getTypeArguments</span>**

* **方法签名：** 

  public List< JavaClassMeta > [getTypeArguments](#gettypearguments)()   







---
> **38.<span id="innerlink-gettypeparameters">getTypeParameters</span>**

* **方法签名：** 

  public List< String > [getTypeParameters](#gettypeparameters)()   







---
> **39.<span id="innerlink-geturl">getUrl</span>**

* **方法签名：** 

  public URL [getUrl](#geturl)()   







---
> **40.<span id="innerlink-getvalue">getValue</span>**

* **方法签名：** 

  public String [getValue](#getvalue)()   







---
> **41.<span id="innerlink-getvoidclass">getVoidClass</span>**

* **方法签名：** 

  public Boolean [getVoidClass](#getvoidclass)()   







---
> **42.<span id="innerlink-parsedeclarationstructure">parseDeclarationStructure</span>**

* **方法签名：** 

  public String [parseDeclarationStructure](#parsedeclarationstructure)()   







---
> **43.<span id="innerlink-setabsolutepath-javalangstring">setAbsolutePath</span>**

* **方法签名：** 

  public void [setAbsolutePath](#setabsolutepath-javalangstring)(String absolutePath)   







---
> **44.<span id="innerlink-setabstractclass-javalangboolean">setAbstractClass</span>**

* **方法签名：** 

  public void [setAbstractClass](#setabstractclass-javalangboolean)(Boolean abstractClass)   







---
> **45.<span id="innerlink-setannotationclass-javalangboolean">setAnnotationClass</span>**

* **方法签名：** 

  public void [setAnnotationClass](#setannotationclass-javalangboolean)(Boolean annotationClass)   







---
> **46.<span id="innerlink-setannotationfields-javautillist">setAnnotationFields</span>**

* **方法签名：** 

  public void [setAnnotationFields](#setannotationfields-javautillist)(List< JavaAnnotationMeta > annotationFields)   







---
> **47.<span id="innerlink-setarrayfullclassname-javalangstring">setArrayFullClassName</span>**

* **方法签名：** 

  public void [setArrayFullClassName](#setarrayfullclassname-javalangstring)(String arrayFullClassName)   







---
> **48.<span id="innerlink-setclassname-javalangstring">setClassName</span>**

* **方法签名：** 

  public void [setClassName](#setclassname-javalangstring)(String className)   







---
> **49.<span id="innerlink-setclassnameprefix-javalangstring">setClassNamePrefix</span>**

* **方法签名：** 

  public void [setClassNamePrefix](#setclassnameprefix-javalangstring)(String classNamePrefix)   







---
> **50.<span id="innerlink-setconstructors-javautillist">setConstructors</span>**

* **方法签名：** 

  public void [setConstructors](#setconstructors-javautillist)(List< JavaConstructorMeta > constructors)   







---
> **51.<span id="innerlink-setdeclarationstructure-javalangstring">setDeclarationStructure</span>**

* **方法签名：** 

  public void [setDeclarationStructure](#setdeclarationstructure-javalangstring)(String declarationStructure)   







---
> **52.<span id="innerlink-setdependencyabsolutepath-javalangstring">setDependencyAbsolutePath</span>**

* **方法签名：** 

  public void [setDependencyAbsolutePath](#setdependencyabsolutepath-javalangstring)(String dependencyAbsolutePath)   







---
> **53.<span id="innerlink-setdependencyrelativepath-javalangstring">setDependencyRelativePath</span>**

* **方法签名：** 

  public void [setDependencyRelativePath](#setdependencyrelativepath-javalangstring)(String dependencyRelativePath)   







---
> **54.<span id="innerlink-setenumclass-javalangboolean">setEnumClass</span>**

* **方法签名：** 

  public void [setEnumClass](#setenumclass-javalangboolean)(Boolean enumClass)   







---
> **55.<span id="innerlink-setenumconstants-javautillist">setEnumConstants</span>**

* **方法签名：** 

  public void [setEnumConstants](#setenumconstants-javautillist)(List< JavaClassMeta > enumConstants)   







---
> **56.<span id="innerlink-setfields-javautillist">setFields</span>**

* **方法签名：** 

  public void [setFields](#setfields-javautillist)(List< JavaFieldMeta > fields)   







---
> **57.<span id="innerlink-setfinalclass-javalangboolean">setFinalClass</span>**

* **方法签名：** 

  public void [setFinalClass](#setfinalclass-javalangboolean)(Boolean finalClass)   







---
> **58.<span id="innerlink-setfullclassname-javalangstring">setFullClassName</span>**

* **方法签名：** 

  public void [setFullClassName](#setfullclassname-javalangstring)(String fullClassName)   







---
> **59.<span id="innerlink-setimports-javautillist">setImports</span>**

* **方法签名：** 

  public void [setImports](#setimports-javautillist)(List< String > imports)   







---
> **60.<span id="innerlink-setinnerclass-javalangboolean">setInnerClass</span>**

* **方法签名：** 

  public void [setInnerClass](#setinnerclass-javalangboolean)(Boolean innerClass)   







---
> **61.<span id="innerlink-setinnerclasses-javautillist">setInnerClasses</span>**

* **方法签名：** 

  public void [setInnerClasses](#setinnerclasses-javautillist)(List< JavaClassMeta > innerClasses)   







---
> **62.<span id="innerlink-setinterfaceclass-javalangboolean">setInterfaceClass</span>**

* **方法签名：** 

  public void [setInterfaceClass](#setinterfaceclass-javalangboolean)(Boolean interfaceClass)   







---
> **63.<span id="innerlink-setinterfaces-javautillist">setInterfaces</span>**

* **方法签名：** 

  public void [setInterfaces](#setinterfaces-javautillist)(List< JavaClassMeta > interfaces)   







---
> **64.<span id="innerlink-setjavamodelmeta-comejdocmetainfoseralizemodeljavamodelmeta">setJavaModelMeta</span>**

* **方法签名：** 

  public void [setJavaModelMeta](#setjavamodelmeta-comejdocmetainfoseralizemodeljavamodelmeta)([JavaModelMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModelMeta.md) javaModelMeta)   







---
> **65.<span id="innerlink-setmethods-javautillist">setMethods</span>**

* **方法签名：** 

  public void [setMethods](#setmethods-javautillist)(List< JavaMethodMeta > methods)   







---
> **66.<span id="innerlink-setmodifiers-javautillist">setModifiers</span>**

* **方法签名：** 

  public void [setModifiers](#setmodifiers-javautillist)(List< String > modifiers)   







---
> **67.<span id="innerlink-setmoduledesc-javalangstring">setModuleDesc</span>**

* **方法签名：** 

  public void [setModuleDesc](#setmoduledesc-javalangstring)(String moduleDesc)   







---
> **68.<span id="innerlink-setmodulename-javalangstring">setModuleName</span>**

* **方法签名：** 

  public void [setModuleName](#setmodulename-javalangstring)(String moduleName)   







---
> **69.<span id="innerlink-setpackagedesc-javalangstring">setPackageDesc</span>**

* **方法签名：** 

  public void [setPackageDesc](#setpackagedesc-javalangstring)(String packageDesc)   







---
> **70.<span id="innerlink-setpackagename-javalangstring">setPackageName</span>**

* **方法签名：** 

  public void [setPackageName](#setpackagename-javalangstring)(String packageName)   







---
> **71.<span id="innerlink-setprimitiveclass-javalangboolean">setPrimitiveClass</span>**

* **方法签名：** 

  public void [setPrimitiveClass](#setprimitiveclass-javalangboolean)(Boolean primitiveClass)   







---
> **72.<span id="innerlink-setprivateclass-javalangboolean">setPrivateClass</span>**

* **方法签名：** 

  public void [setPrivateClass](#setprivateclass-javalangboolean)(Boolean privateClass)   







---
> **73.<span id="innerlink-setprojectname-javalangstring">setProjectName</span>**

* **方法签名：** 

  public void [setProjectName](#setprojectname-javalangstring)(String projectName)   







---
> **74.<span id="innerlink-setprotectedclass-javalangboolean">setProtectedClass</span>**

* **方法签名：** 

  public void [setProtectedClass](#setprotectedclass-javalangboolean)(Boolean protectedClass)   







---
> **75.<span id="innerlink-setpublicclass-javalangboolean">setPublicClass</span>**

* **方法签名：** 

  public void [setPublicClass](#setpublicclass-javalangboolean)(Boolean publicClass)   







---
> **76.<span id="innerlink-setrelativepath-javalangstring">setRelativePath</span>**

* **方法签名：** 

  public void [setRelativePath](#setrelativepath-javalangstring)(String relativePath)   







---
> **77.<span id="innerlink-setstaticclass-javalangboolean">setStaticClass</span>**

* **方法签名：** 

  public void [setStaticClass](#setstaticclass-javalangboolean)(Boolean staticClass)   







---
> **78.<span id="innerlink-setsuperclasses-javautillist">setSuperClasses</span>**

* **方法签名：** 

  public void [setSuperClasses](#setsuperclasses-javautillist)(List< JavaClassMeta > superClasses)   







---
> **79.<span id="innerlink-settypearguments-javautillist">setTypeArguments</span>**

* **方法签名：** 

  public void [setTypeArguments](#settypearguments-javautillist)(List< JavaClassMeta > typeArguments)   







---
> **80.<span id="innerlink-settypeparameters-javautillist">setTypeParameters</span>**

* **方法签名：** 

  public void [setTypeParameters](#settypeparameters-javautillist)(List< String > typeParameters)   







---
> **81.<span id="innerlink-seturl-javaneturl">setUrl</span>**

* **方法签名：** 

  public void [setUrl](#seturl-javaneturl)(URL url)   







---
> **82.<span id="innerlink-setvalue-javalangstring">setValue</span>**

* **方法签名：** 

  public void [setValue](#setvalue-javalangstring)(String value)   







---
> **83.<span id="innerlink-setvoidclass-javalangboolean">setVoidClass</span>**

* **方法签名：** 

  public void [setVoidClass](#setvoidclass-javalangboolean)(Boolean voidClass)   







