# 类名称:DocClassRenderUtil

## 基本信息

* **全路径信息:** com.ejdoc.doc.generate.util.beetl.function.DocClassRenderUtil
* **包名称:** com.ejdoc.doc.generate.util.beetl.function
* **项目名称:** ejdoc
* **模块名称:** jdocGenerate









---

## 声明信息
> public class DocClassRenderUtil     


* **描述：** 

  








## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public String [calAllClassesDetailFieldMd](#innerlink-calallclassesdetailfieldmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String appendBefore,String basePath,Context ctx)   <br/><br/><br/>所有父类的字段明细|
|2|public String [calAllClassesDetailMethodMd](#innerlink-calallclassesdetailmethodmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String appendBefore,String basePath,Context ctx)   <br/><br/><br/>计算所有父类的方法明细，生成markdown结构|
|3|public String [calAllClassesMd](#innerlink-calallclassesmd-javalangobject-javalangstring-context)(Object paras,String appendBefore,Context ctx)   <br/>|
|4|public String [calClassNameStructure](#innerlink-calclassnamestructure-javalangobject-context)(Object paras,Context ctx)   <br/>|
|5|public String [calClassType](#innerlink-calclasstype-javalangobject-context)(Object paras,Context ctx)   <br/>|
|6|public String [calCommentDocMd](#innerlink-calcommentdocmd-javalangobject-javalangobject-javalangstring-context)(Object paras,Object propObj,String appendBefore,Context ctx)   <br/>|
|7|public String [calCommentMd](#innerlink-calcommentmd-javalangobject-javalangstring-context)(Object paras,String appendBefore,Context ctx)   <br/>|
|8|public String [calCommentNoEnterDocMd](#innerlink-calcommentnoenterdocmd-javalangobject-javalangobject-javalangstring-context)(Object paras,Object propObj,String appendBefore,Context ctx)   <br/>|
|9|public String [calCommentNoEnterMd](#innerlink-calcommentnoentermd-javalangobject-context)(Object paras,Context ctx)   <br/>|
|10|public String [calCommentSeeTagsMd](#innerlink-calcommentseetagsmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String type,String appendBefore,Context ctx)   <br/>|
|11|public String [calCommentTagsMd](#innerlink-calcommenttagsmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String type,String appendBefore,Context ctx)   <br/>|
|12|public String [calFieldStructureMd](#innerlink-calfieldstructuremd-javalangobject-context)(Object paras,Context ctx)   <br/>|
|13|public String [calLinkClassNameMd](#innerlink-callinkclassnamemd-javalangobject-context)(Object paras,Context ctx)   <br/>|
|14|public String [calModifer](#innerlink-calmodifer-javalangobject-context)(Object paras,Context ctx)   <br/>|
|15|public String [createClassNameLinkMd](#innerlink-createclassnamelinkmd-jsonobject)(JSONObject classJson)   <br/>|
|16|public String [createCommonLinkMd](#innerlink-createcommonlinkmd-javalangstring-javalangstring)(String text,String linkText)   <br/>|








## 方法详细信息

---
> **1.<span id="innerlink-calallclassesdetailfieldmd-javalangobject-javalangstring-javalangstring-context">calAllClassesDetailFieldMd</span>**

* **方法签名：** 

  public String [calAllClassesDetailFieldMd](#calallclassesdetailfieldmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String appendBefore,String basePath,Context ctx)   


* **描述：** 

所有父类的字段明细
* **参数描述：** 

  paras - 

  appendBefore - 

  basePath - 

  ctx - 


* **返回值描述：** 

   - 




---
> **2.<span id="innerlink-calallclassesdetailmethodmd-javalangobject-javalangstring-javalangstring-context">calAllClassesDetailMethodMd</span>**

* **方法签名：** 

  public String [calAllClassesDetailMethodMd](#calallclassesdetailmethodmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String appendBefore,String basePath,Context ctx)   


* **描述：** 

计算所有父类的方法明细，生成markdown结构
* **参数描述：** 

  paras - 

  appendBefore - 

  basePath - 

  ctx - 


* **返回值描述：** 

   - 




---
> **3.<span id="innerlink-calallclassesmd-javalangobject-javalangstring-context">calAllClassesMd</span>**

* **方法签名：** 

  public String [calAllClassesMd](#calallclassesmd-javalangobject-javalangstring-context)(Object paras,String appendBefore,Context ctx)   







---
> **4.<span id="innerlink-calclassnamestructure-javalangobject-context">calClassNameStructure</span>**

* **方法签名：** 

  public String [calClassNameStructure](#calclassnamestructure-javalangobject-context)(Object paras,Context ctx)   







---
> **5.<span id="innerlink-calclasstype-javalangobject-context">calClassType</span>**

* **方法签名：** 

  public String [calClassType](#calclasstype-javalangobject-context)(Object paras,Context ctx)   







---
> **6.<span id="innerlink-calcommentdocmd-javalangobject-javalangobject-javalangstring-context">calCommentDocMd</span>**

* **方法签名：** 

  public String [calCommentDocMd](#calcommentdocmd-javalangobject-javalangobject-javalangstring-context)(Object paras,Object propObj,String appendBefore,Context ctx)   







---
> **7.<span id="innerlink-calcommentmd-javalangobject-javalangstring-context">calCommentMd</span>**

* **方法签名：** 

  public String [calCommentMd](#calcommentmd-javalangobject-javalangstring-context)(Object paras,String appendBefore,Context ctx)   







---
> **8.<span id="innerlink-calcommentnoenterdocmd-javalangobject-javalangobject-javalangstring-context">calCommentNoEnterDocMd</span>**

* **方法签名：** 

  public String [calCommentNoEnterDocMd](#calcommentnoenterdocmd-javalangobject-javalangobject-javalangstring-context)(Object paras,Object propObj,String appendBefore,Context ctx)   







---
> **9.<span id="innerlink-calcommentnoentermd-javalangobject-context">calCommentNoEnterMd</span>**

* **方法签名：** 

  public String [calCommentNoEnterMd](#calcommentnoentermd-javalangobject-context)(Object paras,Context ctx)   







---
> **10.<span id="innerlink-calcommentseetagsmd-javalangobject-javalangstring-javalangstring-context">calCommentSeeTagsMd</span>**

* **方法签名：** 

  public String [calCommentSeeTagsMd](#calcommentseetagsmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String type,String appendBefore,Context ctx)   







---
> **11.<span id="innerlink-calcommenttagsmd-javalangobject-javalangstring-javalangstring-context">calCommentTagsMd</span>**

* **方法签名：** 

  public String [calCommentTagsMd](#calcommenttagsmd-javalangobject-javalangstring-javalangstring-context)(Object paras,String type,String appendBefore,Context ctx)   







---
> **12.<span id="innerlink-calfieldstructuremd-javalangobject-context">calFieldStructureMd</span>**

* **方法签名：** 

  public String [calFieldStructureMd](#calfieldstructuremd-javalangobject-context)(Object paras,Context ctx)   







---
> **13.<span id="innerlink-callinkclassnamemd-javalangobject-context">calLinkClassNameMd</span>**

* **方法签名：** 

  public String [calLinkClassNameMd](#callinkclassnamemd-javalangobject-context)(Object paras,Context ctx)   







---
> **14.<span id="innerlink-calmodifer-javalangobject-context">calModifer</span>**

* **方法签名：** 

  public String [calModifer](#calmodifer-javalangobject-context)(Object paras,Context ctx)   







---
> **15.<span id="innerlink-createclassnamelinkmd-jsonobject">createClassNameLinkMd</span>**

* **方法签名：** 

  public String [createClassNameLinkMd](#createclassnamelinkmd-jsonobject)(JSONObject classJson)   







---
> **16.<span id="innerlink-createcommonlinkmd-javalangstring-javalangstring">createCommonLinkMd</span>**

* **方法签名：** 

  public String [createCommonLinkMd](#createcommonlinkmd-javalangstring-javalangstring)(String text,String linkText)   







