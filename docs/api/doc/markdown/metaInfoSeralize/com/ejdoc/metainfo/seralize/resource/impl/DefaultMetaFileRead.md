# 类名称:DefaultMetaFileRead

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.resource.impl.DefaultMetaFileRead
* **包名称:** com.ejdoc.metainfo.seralize.resource.impl
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize






* **所有父级接口：**  
[MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)


---

## 声明信息
> public class DefaultMetaFileRead   implements [MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)   


* **描述：** 

  







## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [DefaultMetaFileRead](#innerlink-defaultmetafileread)()   <br/>|
|2|public  [DefaultMetaFileRead](#innerlink-defaultmetafileread-javalangstring)(String configFilePath)   <br/>|
|3|public  [DefaultMetaFileRead](#innerlink-defaultmetafileread-comejdocmetainfoseralizeenvmetaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   <br/>|

## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#innerlink-getmetaenvironment)()   <br/>|
|2|public String [getProp](#innerlink-getprop-javalangstring-javalangstring)(String propKey,String defaultVal)   <br/>|
|3|public String [getProp](#innerlink-getprop-javalangstring)(String propKey)   <br/>|
|4|public List< MetaFileInfoDto > [readAllMetaFile](#innerlink-readallmetafile)()   <br/>|
|5|public Map< < MetaFileInfoDto >String,List > [readModuleMetaFile](#innerlink-readmodulemetafile)()   <br/>|
|6|public List< MetaFileInfoDto > [readModuleMetaFileByName](#innerlink-readmodulemetafilebyname-javalangstring)(String moduleName)   <br/>|
|7|public List< File > [readModuleProjectMetaFile](#innerlink-readmoduleprojectmetafile)()   <br/>|
|8|public List< File > [readModuleProjectMetaFileByModuleName](#innerlink-readmoduleprojectmetafilebymodulename-javalangstring)(String moduleName)   <br/>|
|9|public List< MetaFileInfoDto > [readProjectFileByClasspath](#innerlink-readprojectfilebyclasspath-javalangstring)(String classpath)   <br/>|
|10|public List< MetaFileInfoDto > [readProjectFileByPath](#innerlink-readprojectfilebypath-javalangstring)(String path)   <br/>|
|11|public File [readProjectMetaFile](#innerlink-readprojectmetafile)()   <br/>|
|12|public void [setMetaEnvironment](#innerlink-setmetaenvironment-comejdocmetainfoseralizeenvmetaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   <br/>|




---
### 继承接口方法:MetaFileRead

全路径信息com.ejdoc.metainfo.seralize.resource.[MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)  
[getMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getMetaEnvironment),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getProp-javalangstring),[getProp](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#getProp-javalangstring-javalangstring),[readAllMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readAllMetaFile),[readModuleMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleMetaFile),[readModuleMetaFileByName](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleMetaFileByName-javalangstring),[readModuleProjectMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleProjectMetaFile),[readModuleProjectMetaFileByModuleName](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readModuleProjectMetaFileByModuleName-javalangstring),[readProjectFileByClasspath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectFileByClasspath-javalangstring),[readProjectFileByPath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectFileByPath-javalangstring)
,[readProjectMetaFile](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md#readProjectMetaFile)



## 构造方法详细信息

---
> **1.<span id="innerlink-defaultmetafileread">DefaultMetaFileRead</span>**

* **构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread)()   






---
> **2.<span id="innerlink-defaultmetafileread-javalangstring">DefaultMetaFileRead</span>**

* **构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread-javalangstring)(String configFilePath)   






---
> **3.<span id="innerlink-defaultmetafileread-comejdocmetainfoseralizeenvmetaenvironment">DefaultMetaFileRead</span>**

* **构造方法签名：** 

  public  [DefaultMetaFileRead](#defaultmetafileread-comejdocmetainfoseralizeenvmetaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   







## 方法详细信息

---
> **1.<span id="innerlink-getmetaenvironment">getMetaEnvironment</span>**

* **方法签名：** 

  public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   







---
> **2.<span id="innerlink-getprop-javalangstring-javalangstring">getProp</span>**

* **方法签名：** 

  public String [getProp](#getprop-javalangstring-javalangstring)(String propKey,String defaultVal)   







---
> **3.<span id="innerlink-getprop-javalangstring">getProp</span>**

* **方法签名：** 

  public String [getProp](#getprop-javalangstring)(String propKey)   







---
> **4.<span id="innerlink-readallmetafile">readAllMetaFile</span>**

* **方法签名：** 

  public List< MetaFileInfoDto > [readAllMetaFile](#readallmetafile)()   







---
> **5.<span id="innerlink-readmodulemetafile">readModuleMetaFile</span>**

* **方法签名：** 

  public Map< < MetaFileInfoDto >String,List > [readModuleMetaFile](#readmodulemetafile)()   







---
> **6.<span id="innerlink-readmodulemetafilebyname-javalangstring">readModuleMetaFileByName</span>**

* **方法签名：** 

  public List< MetaFileInfoDto > [readModuleMetaFileByName](#readmodulemetafilebyname-javalangstring)(String moduleName)   







---
> **7.<span id="innerlink-readmoduleprojectmetafile">readModuleProjectMetaFile</span>**

* **方法签名：** 

  public List< File > [readModuleProjectMetaFile](#readmoduleprojectmetafile)()   







---
> **8.<span id="innerlink-readmoduleprojectmetafilebymodulename-javalangstring">readModuleProjectMetaFileByModuleName</span>**

* **方法签名：** 

  public List< File > [readModuleProjectMetaFileByModuleName](#readmoduleprojectmetafilebymodulename-javalangstring)(String moduleName)   







---
> **9.<span id="innerlink-readprojectfilebyclasspath-javalangstring">readProjectFileByClasspath</span>**

* **方法签名：** 

  public List< MetaFileInfoDto > [readProjectFileByClasspath](#readprojectfilebyclasspath-javalangstring)(String classpath)   







---
> **10.<span id="innerlink-readprojectfilebypath-javalangstring">readProjectFileByPath</span>**

* **方法签名：** 

  public List< MetaFileInfoDto > [readProjectFileByPath](#readprojectfilebypath-javalangstring)(String path)   







---
> **11.<span id="innerlink-readprojectmetafile">readProjectMetaFile</span>**

* **方法签名：** 

  public File [readProjectMetaFile](#readprojectmetafile)()   







---
> **12.<span id="innerlink-setmetaenvironment-comejdocmetainfoseralizeenvmetaenvironment">setMetaEnvironment</span>**

* **方法签名：** 

  public void [setMetaEnvironment](#setmetaenvironment-comejdocmetainfoseralizeenvmetaenvironment)([MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) metaEnvironment)   







