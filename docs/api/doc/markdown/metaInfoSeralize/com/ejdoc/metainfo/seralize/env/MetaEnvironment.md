# 接口名称:MetaEnvironment

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.env    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.env.MetaEnvironment|













**所有子类：**  
[DefaultMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/impl/DefaultMetaEnvironment.md)





---

## 声明信息

> public interface MetaEnvironment     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getAllProp](#getallprop)()   <br/><br/>|
|2|[ProjectCompileEnum](/metaInfoSeralize/com/ejdoc/metainfo/seralize/enums/ProjectCompileEnum.md) [getProjectCompileType](#getprojectcompiletype)()   <br/><br/>|
|3|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectRootPath](#getprojectrootpath)()   <br/><br/>|
|4|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectSourceDir](#getprojectsourcedir)()   <br/><br/>|
|5|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   <br/><br/>|
|6|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   <br/><br/>|
|7|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [ModuleInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/ModuleInfoDto.md) > [getSubProjectInfo](#getsubprojectinfo)()   <br/><br/>|
|8|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getSubProjectRootPath](#getsubprojectrootpath)()   <br/><br/>|
|9|[boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [isIncludeSubProject](#isincludesubproject)()   <br/><br/>|







## 方法详细信息


---

> **1.<span id="getallprop">getAllProp</span>**

**方法签名：** 

  [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getAllProp](#getallprop)()   










---

> **2.<span id="getprojectcompiletype">getProjectCompileType</span>**

**方法签名：** 

  [ProjectCompileEnum](/metaInfoSeralize/com/ejdoc/metainfo/seralize/enums/ProjectCompileEnum.md) [getProjectCompileType](#getprojectcompiletype)()   










---

> **3.<span id="getprojectrootpath">getProjectRootPath</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectRootPath](#getprojectrootpath)()   










---

> **4.<span id="getprojectsourcedir">getProjectSourceDir</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectSourceDir](#getprojectsourcedir)()   










---

> **5.<span id="getprop-string-string">getProp</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   










---

> **6.<span id="getprop-string">getProp</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   










---

> **7.<span id="getsubprojectinfo">getSubProjectInfo</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [ModuleInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/ModuleInfoDto.md) > [getSubProjectInfo](#getsubprojectinfo)()   










---

> **8.<span id="getsubprojectrootpath">getSubProjectRootPath</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getSubProjectRootPath](#getsubprojectrootpath)()   










---

> **9.<span id="isincludesubproject">isIncludeSubProject</span>**

**方法签名：** 

  [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [isIncludeSubProject](#isincludesubproject)()   









