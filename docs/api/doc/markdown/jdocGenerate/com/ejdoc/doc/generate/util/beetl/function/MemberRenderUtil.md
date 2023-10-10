# 类名称:MemberRenderUtil

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.util.beetl.function    |   **全路径信息:**   |com.ejdoc.doc.generate.util.beetl.function.MemberRenderUtil|



















---

## 声明信息

> public class MemberRenderUtil     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calExceptionMd](#calexceptionmd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|2|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calMethodStructureInnerLinkMd](#calmethodstructureinnerlinkmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,Context ctx)   <br/><br/>计算方法体声明结构.|
|3|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calMethodStructureMd](#calmethodstructuremd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>计算方法体声明结构.|
|4|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calParamMd](#calparammd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|5|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calReturnMd](#calreturnmd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|6|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calUniqueMethodName](#caluniquemethodname-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   <br/><br/>|
|7|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [catUniqueMethodParamName](#catuniquemethodparamname-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > params)   <br/><br/>|
|8|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createALinkHrefIdHtml](#createalinkhrefidhtml-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) name,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   <br/><br/>|
|9|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createHrefIdHtml](#createhrefidhtml-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   <br/><br/>|
|10|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createInnerLinkIdMd](#createinnerlinkidmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   <br/><br/>计算markdown链接.|
|11|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createLinkIdMd](#createlinkidmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   <br/><br/>计算markdown链接.|







## 方法详细信息


---

> **1.<span id="calexceptionmd-object-context">calExceptionMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calExceptionMd](#calexceptionmd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **2.<span id="calmethodstructureinnerlinkmd-object-string-context">calMethodStructureInnerLinkMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calMethodStructureInnerLinkMd](#calmethodstructureinnerlinkmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,Context ctx)   


**描述：** 

计算方法体声明结构

**参数描述：** 

  paras - 

  ctx - 








---

> **3.<span id="calmethodstructuremd-object-context">calMethodStructureMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calMethodStructureMd](#calmethodstructuremd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   


**描述：** 

计算方法体声明结构

**参数描述：** 

  paras - 

  ctx - 








---

> **4.<span id="calparammd-object-context">calParamMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calParamMd](#calparammd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **5.<span id="calreturnmd-object-context">calReturnMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calReturnMd](#calreturnmd-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **6.<span id="caluniquemethodname-object-context">calUniqueMethodName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [calUniqueMethodName](#caluniquemethodname-object-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,Context ctx)   










---

> **7.<span id="catuniquemethodparamname-list">catUniqueMethodParamName</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [catUniqueMethodParamName](#catuniquemethodparamname-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > params)   










---

> **8.<span id="createalinkhrefidhtml-object-string-string-context">createALinkHrefIdHtml</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createALinkHrefIdHtml](#createalinkhrefidhtml-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) name,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   










---

> **9.<span id="createhrefidhtml-object-string-string-context">createHrefIdHtml</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createHrefIdHtml](#createhrefidhtml-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   










---

> **10.<span id="createinnerlinkidmd-object-string-string-context">createInnerLinkIdMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createInnerLinkIdMd](#createinnerlinkidmd-object-string-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) prex,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   


**描述：** 

计算markdown链接

**参数描述：** 

  paras - 

  prex - 

  uniqueName - 

  ctx - 








---

> **11.<span id="createlinkidmd-object-string-context">createLinkIdMd</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [createLinkIdMd](#createlinkidmd-object-string-context)([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html?is-external=true) paras,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) uniqueName,Context ctx)   


**描述：** 

计算markdown链接

**参数描述：** 

  paras - 

  uniqueName - 

  ctx - 







