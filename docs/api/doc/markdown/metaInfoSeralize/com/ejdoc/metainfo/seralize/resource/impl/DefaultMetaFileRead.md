# 类名称:DefaultMetaFileRead

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.resource.impl    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.resource.impl.DefaultMetaFileRead|











**所有父级接口：**  
[MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)







---

## 声明信息

> public class DefaultMetaFileRead   implements [MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [DefaultMetaFileRead](#defaultmetafileread)()   <br/><br/>|
|2|public  [DefaultMetaFileRead](#defaultmetafileread-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   <br/><br/>|
|3|public  [DefaultMetaFileRead](#defaultmetafileread-metaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   <br/><br/>|
|2|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   <br/><br/>|
|3|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   <br/><br/>|
|4|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readAllMetaFile](#readallmetafile)()   <br/><br/>|
|5|public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < MetaFileInfoDto >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [readModuleMetaFile](#readmodulemetafile)()   <br/><br/>|
|6|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readModuleMetaFileByName](#readmodulemetafilebyname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   <br/><br/>|
|7|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   <br/><br/>|
|8|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   <br/><br/>|
|9|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByClasspath](#readprojectfilebyclasspath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classpath)   <br/><br/>|
|10|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByPath](#readprojectfilebypath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) path)   <br/><br/>|
|11|public [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) [readProjectMetaFile](#readprojectmetafile)()   <br/><br/>|
|12|public void [setMetaEnvironment](#setmetaenvironment-metaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   <br/><br/>|




---

### 从MetaFileRead接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.resource.[MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)  
[getMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getMetaEnvironment),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getProp-string-string),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getProp-string),[readAllMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readAllMetaFile),[readModuleMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleMetaFile),[readModuleMetaFileByName](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleMetaFileByName-string),[readModuleProjectMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleProjectMetaFile),[readModuleProjectMetaFileByModuleName](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleProjectMetaFileByModuleName-string),[readProjectFileByClasspath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectFileByClasspath-string),[readProjectFileByPath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectFileByPath-string)
,[readProjectMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectMetaFile)



## 构造方法详细信息


---

> **1.<span id="defaultmetafileread">DefaultMetaFileRead</span>**

**构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread)()   








---

> **2.<span id="defaultmetafileread-string">DefaultMetaFileRead</span>**

**构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   








---

> **3.<span id="defaultmetafileread-metaenvironment">DefaultMetaFileRead</span>**

**构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread-metaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   








## 方法详细信息


---

> **1.<span id="getmetaenvironment">getMetaEnvironment</span>**

**方法签名：** 

  public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   










---

> **2.<span id="getprop-string-string">getProp</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   










---

> **3.<span id="getprop-string">getProp</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   










---

> **4.<span id="readallmetafile">readAllMetaFile</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readAllMetaFile](#readallmetafile)()   










---

> **5.<span id="readmodulemetafile">readModuleMetaFile</span>**

**方法签名：** 

  public [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < MetaFileInfoDto >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [readModuleMetaFile](#readmodulemetafile)()   










---

> **6.<span id="readmodulemetafilebyname-string">readModuleMetaFileByName</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readModuleMetaFileByName](#readmodulemetafilebyname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   










---

> **7.<span id="readmoduleprojectmetafile">readModuleProjectMetaFile</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   










---

> **8.<span id="readmoduleprojectmetafilebymodulename-string">readModuleProjectMetaFileByModuleName</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   










---

> **9.<span id="readprojectfilebyclasspath-string">readProjectFileByClasspath</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByClasspath](#readprojectfilebyclasspath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classpath)   










---

> **10.<span id="readprojectfilebypath-string">readProjectFileByPath</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByPath](#readprojectfilebypath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) path)   










---

> **11.<span id="readprojectmetafile">readProjectMetaFile</span>**

**方法签名：** 

  public [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) [readProjectMetaFile](#readprojectmetafile)()   










---

> **12.<span id="setmetaenvironment-metaenvironment">setMetaEnvironment</span>**

**方法签名：** 

  public void [setMetaEnvironment](#setmetaenvironment-metaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   









