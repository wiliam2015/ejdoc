# 接口名称:MetaFileRead

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.resource    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.resource.MetaFileRead|





**作者：** 

  wiliam.me









**所有子类：**  
[DefaultMetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/impl/DefaultMetaFileRead.md)





---

## 声明信息

> public interface MetaFileRead     


**描述：** 元数据读取接口<br>
<p>读取jar文件或单个文件内容。</p>












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   <br/><br/>|
|2|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   <br/><br/>|
|3|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   <br/><br/>|
|4|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readAllMetaFile](#readallmetafile)()   <br/><br/>|
|5|[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < MetaFileInfoDto >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [readModuleMetaFile](#readmodulemetafile)()   <br/><br/>|
|6|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readModuleMetaFileByName](#readmodulemetafilebyname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   <br/><br/>|
|7|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   <br/><br/>|
|8|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   <br/><br/>|
|9|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByClasspath](#readprojectfilebyclasspath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classpath)   <br/><br/>|
|10|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByPath](#readprojectfilebypath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) path)   <br/><br/>|
|11|[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) [readProjectMetaFile](#readprojectmetafile)()   <br/><br/>|







## 方法详细信息


---

> **1.<span id="getmetaenvironment">getMetaEnvironment</span>**

**方法签名：** 

  [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   










---

> **2.<span id="getprop-string-string">getProp</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) defaultVal)   










---

> **3.<span id="getprop-string">getProp</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getProp](#getprop-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) propKey)   










---

> **4.<span id="readallmetafile">readAllMetaFile</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readAllMetaFile](#readallmetafile)()   










---

> **5.<span id="readmodulemetafile">readModuleMetaFile</span>**

**方法签名：** 

  [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < MetaFileInfoDto >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [readModuleMetaFile](#readmodulemetafile)()   










---

> **6.<span id="readmodulemetafilebyname-string">readModuleMetaFileByName</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readModuleMetaFileByName](#readmodulemetafilebyname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   










---

> **7.<span id="readmoduleprojectmetafile">readModuleProjectMetaFile</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   










---

> **8.<span id="readmoduleprojectmetafilebymodulename-string">readModuleProjectMetaFileByModuleName</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) moduleName)   










---

> **9.<span id="readprojectfilebyclasspath-string">readProjectFileByClasspath</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByClasspath](#readprojectfilebyclasspath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) classpath)   










---

> **10.<span id="readprojectfilebypath-string">readProjectFileByPath</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [MetaFileInfoDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/dto/MetaFileInfoDto.md) > [readProjectFileByPath](#readprojectfilebypath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) path)   










---

> **11.<span id="readprojectmetafile">readProjectMetaFile</span>**

**方法签名：** 

  [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) [readProjectMetaFile](#readprojectmetafile)()   









