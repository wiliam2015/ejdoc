# 类名称:DocClassRenderUtil

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.util.beetl.function    |   **全路径信息:**   |com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil|



















---

## 声明信息

> public class DocClassRenderUtil     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesDetailFieldMd](#calallclassesdetailfieldmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) basePath,Context ctx)   <br/><br/>所有父类的字段明细.|
|2|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesDetailMethodMd](#calallclassesdetailmethodmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) basePath,Context ctx)   <br/><br/>计算所有父类的方法明细，生成markdown结构.|
|3|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesHierarchyMd](#calallclasseshierarchymd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|4|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesMd](#calallclassesmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|5|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAnnoHaveDefaultVal](#calannohavedefaultval-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>注解类型是否有默认值.|
|6|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calClassNameStructure](#calclassnamestructure-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|7|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calClassType](#calclasstype-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|8|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentDocMd](#calcommentdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|9|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentMd](#calcommentmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|10|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentNoEnterDocMd](#calcommentnoenterdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|11|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentNoEnterMd](#calcommentnoentermd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|12|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentTagsMd](#calcommenttagsmd-object-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) rootProp,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) type,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|13|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calDeprecatedCommentDocMd](#caldeprecatedcommentdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|14|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calFieldStructureMd](#calfieldstructuremd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|15|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calFunctionInterfaceInfoMd](#calfunctioninterfaceinfomd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   <br/><br/>|
|16|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIncludeMetaAndCustomAnnotation](#calincludemetaandcustomannotation-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>是否包含元注解和自定义注解.|
|17|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIsDeprecated](#calisdeprecated-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>是否有Deprecated注解.|
|18|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIsFunctionalInterface](#calisfunctionalinterface-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|19|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calLinkClassNameMd](#callinkclassnamemd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|20|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calModifer](#calmodifer-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|21|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createClassNameLinkMd](#createclassnamelinkmd-jsonobject)(JSONObject classJson)   <br/><br/>|
|22|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createCommonLinkMd](#createcommonlinkmd-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) text,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) linkText)   <br/><br/>|
|23|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createNestedClassNameLinkMd](#createnestedclassnamelinkmd-jsonobject)(JSONObject classJson)   <br/><br/>|
|24|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [printDeprecatedDesc](#printdeprecateddesc-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|25|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [printMetaAndCustomAnnotation](#printmetaandcustomannotation-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|







## 方法详细信息


---

> **1.<span id="calallclassesdetailfieldmd-object-string-string-context">calAllClassesDetailFieldMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesDetailFieldMd](#calallclassesdetailfieldmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) basePath,Context ctx)   


**描述：** 

所有父类的字段明细

**参数描述：** 

  paras - 

  appendBefore - 

  basePath - 

  ctx - 








---

> **2.<span id="calallclassesdetailmethodmd-object-string-string-context">calAllClassesDetailMethodMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesDetailMethodMd](#calallclassesdetailmethodmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) basePath,Context ctx)   


**描述：** 

计算所有父类的方法明细，生成markdown结构

**参数描述：** 

  paras - 

  appendBefore - 

  basePath - 

  ctx - 








---

> **3.<span id="calallclasseshierarchymd-object-context">calAllClassesHierarchyMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesHierarchyMd](#calallclasseshierarchymd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **4.<span id="calallclassesmd-object-string-context">calAllClassesMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAllClassesMd](#calallclassesmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **5.<span id="calannohavedefaultval-object-context">calAnnoHaveDefaultVal</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calAnnoHaveDefaultVal](#calannohavedefaultval-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   


**描述：** 

注解类型是否有默认值

**参数描述：** 

  paras - 

  ctx - 








---

> **6.<span id="calclassnamestructure-object-context">calClassNameStructure</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calClassNameStructure](#calclassnamestructure-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **7.<span id="calclasstype-object-context">calClassType</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calClassType](#calclasstype-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **8.<span id="calcommentdocmd-object-object-string-context">calCommentDocMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentDocMd](#calcommentdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **9.<span id="calcommentmd-object-string-context">calCommentMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentMd](#calcommentmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **10.<span id="calcommentnoenterdocmd-object-object-string-context">calCommentNoEnterDocMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentNoEnterDocMd](#calcommentnoenterdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **11.<span id="calcommentnoentermd-object-context">calCommentNoEnterMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentNoEnterMd](#calcommentnoentermd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **12.<span id="calcommenttagsmd-object-object-string-string-context">calCommentTagsMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calCommentTagsMd](#calcommenttagsmd-object-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) rootProp,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) type,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **13.<span id="caldeprecatedcommentdocmd-object-object-string-context">calDeprecatedCommentDocMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calDeprecatedCommentDocMd](#caldeprecatedcommentdocmd-object-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) propObj,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **14.<span id="calfieldstructuremd-object-context">calFieldStructureMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calFieldStructureMd](#calfieldstructuremd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **15.<span id="calfunctioninterfaceinfomd-object-string-context">calFunctionInterfaceInfoMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calFunctionInterfaceInfoMd](#calfunctioninterfaceinfomd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) appendBefore,Context ctx)   










---

> **16.<span id="calincludemetaandcustomannotation-object-context">calIncludeMetaAndCustomAnnotation</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIncludeMetaAndCustomAnnotation](#calincludemetaandcustomannotation-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   


**描述：** 

是否包含元注解和自定义注解

**参数描述：** 

  paras - 

  ctx - 








---

> **17.<span id="calisdeprecated-object-context">calIsDeprecated</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIsDeprecated](#calisdeprecated-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   


**描述：** 

是否有Deprecated注解

**参数描述：** 

  paras - 

  ctx - 








---

> **18.<span id="calisfunctionalinterface-object-context">calIsFunctionalInterface</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [calIsFunctionalInterface](#calisfunctionalinterface-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **19.<span id="callinkclassnamemd-object-context">calLinkClassNameMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calLinkClassNameMd](#callinkclassnamemd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **20.<span id="calmodifer-object-context">calModifer</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calModifer](#calmodifer-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **21.<span id="createclassnamelinkmd-jsonobject">createClassNameLinkMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createClassNameLinkMd](#createclassnamelinkmd-jsonobject)(JSONObject classJson)   










---

> **22.<span id="createcommonlinkmd-string-string">createCommonLinkMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createCommonLinkMd](#createcommonlinkmd-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) text,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) linkText)   










---

> **23.<span id="createnestedclassnamelinkmd-jsonobject">createNestedClassNameLinkMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createNestedClassNameLinkMd](#createnestedclassnamelinkmd-jsonobject)(JSONObject classJson)   










---

> **24.<span id="printdeprecateddesc-object-context">printDeprecatedDesc</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [printDeprecatedDesc](#printdeprecateddesc-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **25.<span id="printmetaandcustomannotation-object-context">printMetaAndCustomAnnotation</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [printMetaAndCustomAnnotation](#printmetaandcustomannotation-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   









