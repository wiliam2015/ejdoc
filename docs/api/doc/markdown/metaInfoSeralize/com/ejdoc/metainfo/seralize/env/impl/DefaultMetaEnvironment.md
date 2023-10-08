# 类名称:DefaultMetaEnvironment

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.env.impl    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.env.impl.DefaultMetaEnvironment|











**所有父级接口：**  
[MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md)







---

## 声明信息

> public class DefaultMetaEnvironment   implements [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [DefaultMetaEnvironment](#defaultmetaenvironment)()   <br/><br/>|
|2|public  [DefaultMetaEnvironment](#defaultmetaenvironment-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getAllProp](#getallprop)()   <br/><br/>|
|2|public [ProjectCompileEnum](/metaInfoSeralize/com/ejdoc/metainfo/seralize/enums/ProjectCompileEnum.md) [getProjectCompileType](#getprojectcompiletype)()   <br/><br/>|
|3|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectRootPath](#getprojectrootpath)()   <br/><br/>|
|4|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectSourceDir](#getprojectsourcedir)()   <br/><br/>|
|5|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   <br/><br/>|
|6|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   <br/><br/>|
|7|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [ModuleInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/ModuleInfoDto.md) > [getSubProjectInfo](#getsubprojectinfo)()   <br/><br/>|
|8|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getSubProjectRootPath](#getsubprojectrootpath)()   <br/><br/>|
|9|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [isIncludeSubProject](#isincludesubproject)()   <br/><br/>|




---

### 从MetaEnvironment接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.env.[MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md)  
[getAllProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getAllProp),[getProjectCompileType](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getProjectCompileType),[getProjectRootPath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getProjectRootPath),[getProjectSourceDir](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getProjectSourceDir),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getProp-string-string),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getProp-string),[getSubProjectInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getSubProjectInfo),[getSubProjectRootPath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#getSubProjectRootPath),[isIncludeSubProject](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md#isIncludeSubProject)



## 构造方法详细信息


---

> **1.<span id="defaultmetaenvironment">DefaultMetaEnvironment</span>**

**构造方法签名：** 

  public  [DefaultMetaEnvironment](#defaultmetaenvironment)()   








---

> **2.<span id="defaultmetaenvironment-string">DefaultMetaEnvironment</span>**

**构造方法签名：** 

  public  [DefaultMetaEnvironment](#defaultmetaenvironment-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   








## 方法详细信息


---

> **1.<span id="getallprop">getAllProp</span>**

**方法签名：** 

  public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getAllProp](#getallprop)()   










---

> **2.<span id="getprojectcompiletype">getProjectCompileType</span>**

**方法签名：** 

  public [ProjectCompileEnum](/metaInfoSeralize/com/ejdoc/metainfo/seralize/enums/ProjectCompileEnum.md) [getProjectCompileType](#getprojectcompiletype)()   










---

> **3.<span id="getprojectrootpath">getProjectRootPath</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectRootPath](#getprojectrootpath)()   










---

> **4.<span id="getprojectsourcedir">getProjectSourceDir</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProjectSourceDir](#getprojectsourcedir)()   










---

> **5.<span id="getprop-string-string">getProp</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   










---

> **6.<span id="getprop-string">getProp</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   










---

> **7.<span id="getsubprojectinfo">getSubProjectInfo</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [ModuleInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/ModuleInfoDto.md) > [getSubProjectInfo](#getsubprojectinfo)()   










---

> **8.<span id="getsubprojectrootpath">getSubProjectRootPath</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) > [getSubProjectRootPath](#getsubprojectrootpath)()   










---

> **9.<span id="isincludesubproject">isIncludeSubProject</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [isIncludeSubProject](#isincludesubproject)()   









