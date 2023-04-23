# 接口名称:MetaFileRead

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.resource.MetaFileRead
* **包名称:** com.ejdoc.metainfo.seralize.resource
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize




* **作者：** 

   - wiliam.me




* **所有子类：**  
[DefaultMetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/impl/DefaultMetaFileRead.md)

---

## 声明信息
> public interface MetaFileRead     


* **描述：** 

  元数据读取接口<br>
<p>读取jar文件或单个文件内容。</p>

* **描述：** 

元数据读取接口<br><p>读取jar文件或单个文件内容。</p>






## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#innerlink-getmetaenvironment)()   <br/>|
|2|String [getProp](#innerlink-getprop-javalangstring)(String propKey)   <br/>|
|3|String [getProp](#innerlink-getprop-javalangstring-javalangstring)(String propKey,String defaultVal)   <br/>|
|4|List< MetaFileInfoDto > [readAllMetaFile](#innerlink-readallmetafile)()   <br/>|
|5|Map< < MetaFileInfoDto >String,List > [readModuleMetaFile](#innerlink-readmodulemetafile)()   <br/>|
|6|List< MetaFileInfoDto > [readModuleMetaFileByName](#innerlink-readmodulemetafilebyname-javalangstring)(String moduleName)   <br/>|
|7|List< File > [readModuleProjectMetaFile](#innerlink-readmoduleprojectmetafile)()   <br/>|
|8|List< File > [readModuleProjectMetaFileByModuleName](#innerlink-readmoduleprojectmetafilebymodulename-javalangstring)(String moduleName)   <br/>|
|9|List< MetaFileInfoDto > [readProjectFileByClasspath](#innerlink-readprojectfilebyclasspath-javalangstring)(String classpath)   <br/>|
|10|List< MetaFileInfoDto > [readProjectFileByPath](#innerlink-readprojectfilebypath-javalangstring)(String path)   <br/>|
|11|File [readProjectMetaFile](#innerlink-readprojectmetafile)()   <br/>|








## 方法详细信息

---
> **1.<span id="innerlink-getmetaenvironment">getMetaEnvironment</span>**

* **方法签名：** 

  [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   







---
> **2.<span id="innerlink-getprop-javalangstring">getProp</span>**

* **方法签名：** 

  String [getProp](#getprop-javalangstring)(String propKey)   







---
> **3.<span id="innerlink-getprop-javalangstring-javalangstring">getProp</span>**

* **方法签名：** 

  String [getProp](#getprop-javalangstring-javalangstring)(String propKey,String defaultVal)   







---
> **4.<span id="innerlink-readallmetafile">readAllMetaFile</span>**

* **方法签名：** 

  List< MetaFileInfoDto > [readAllMetaFile](#readallmetafile)()   







---
> **5.<span id="innerlink-readmodulemetafile">readModuleMetaFile</span>**

* **方法签名：** 

  Map< < MetaFileInfoDto >String,List > [readModuleMetaFile](#readmodulemetafile)()   







---
> **6.<span id="innerlink-readmodulemetafilebyname-javalangstring">readModuleMetaFileByName</span>**

* **方法签名：** 

  List< MetaFileInfoDto > [readModuleMetaFileByName](#readmodulemetafilebyname-javalangstring)(String moduleName)   







---
> **7.<span id="innerlink-readmoduleprojectmetafile">readModuleProjectMetaFile</span>**

* **方法签名：** 

  List< File > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   







---
> **8.<span id="innerlink-readmoduleprojectmetafilebymodulename-javalangstring">readModuleProjectMetaFileByModuleName</span>**

* **方法签名：** 

  List< File > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-javalangstring)(String moduleName)   







---
> **9.<span id="innerlink-readprojectfilebyclasspath-javalangstring">readProjectFileByClasspath</span>**

* **方法签名：** 

  List< MetaFileInfoDto > [readProjectFileByClasspath](#readprojectfilebyclasspath-javalangstring)(String classpath)   







---
> **10.<span id="innerlink-readprojectfilebypath-javalangstring">readProjectFileByPath</span>**

* **方法签名：** 

  List< MetaFileInfoDto > [readProjectFileByPath](#readprojectfilebypath-javalangstring)(String path)   







---
> **11.<span id="innerlink-readprojectmetafile">readProjectMetaFile</span>**

* **方法签名：** 

  File [readProjectMetaFile](#readprojectmetafile)()   







