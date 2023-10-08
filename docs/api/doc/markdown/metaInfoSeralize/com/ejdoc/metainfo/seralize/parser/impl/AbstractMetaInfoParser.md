# 抽象类:AbstractMetaInfoParser

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.AbstractMetaInfoParser|











**所有父级接口：**  
[MetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md),[MetaProjectMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md)

**所有子类：**  
[JavaParserMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaInfoParser.md)





---

## 声明信息

> public abstract class AbstractMetaInfoParser   implements [MetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md)   








## 成员变量汇总

|   索引  |   修饰符  |    类型简称  |   字段与描述   |   初始值   |
| ---- | ---- | ---- | ---- | ---- |
|1|protected |[MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)|[metaFileRead](#metafileread)<br/>|无|




## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AbstractMetaInfoParser](#abstractmetainfoparser)()   <br/><br/>|
|2|public  [AbstractMetaInfoParser](#abstractmetainfoparser-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   <br/><br/>|
|3|public  [AbstractMetaInfoParser](#abstractmetainfoparser-metafileread)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   <br/><br/>|
|2|protected [MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) [initDefaultMetaFileRead](#initdefaultmetafileread)()   <br/><br/>|
|3|public [JavaProjectMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaProjectMeta.md) [parseJavaProjectMeta](#parsejavaprojectmeta)()   <br/><br/>|




---

### 从MetaInfoParser接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.[MetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md)  
[getMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md#getMetaEnvironment),[parseAllJavaModuletMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md#parseAllJavaModuletMeta)

---

### 从MetaProjectMetaInfoParser接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.[MetaProjectMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md)  
[parseJavaProjectMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md#parseJavaProjectMeta)

## 成员变量详细信息


---

> **1.<span id="metafileread">metaFileRead</span>**

**签名信息：** 

  protected  [MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md)  metaFileRead 






## 构造方法详细信息


---

> **1.<span id="abstractmetainfoparser">AbstractMetaInfoParser</span>**

**构造方法签名：** 

  public  [AbstractMetaInfoParser](#abstractmetainfoparser)()   








---

> **2.<span id="abstractmetainfoparser-string">AbstractMetaInfoParser</span>**

**构造方法签名：** 

  public  [AbstractMetaInfoParser](#abstractmetainfoparser-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   








---

> **3.<span id="abstractmetainfoparser-metafileread">AbstractMetaInfoParser</span>**

**构造方法签名：** 

  public  [AbstractMetaInfoParser](#abstractmetainfoparser-metafileread)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead)   








## 方法详细信息


---

> **1.<span id="getmetaenvironment">getMetaEnvironment</span>**

**方法签名：** 

  public [MetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/env/MetaEnvironment.md) [getMetaEnvironment](#getmetaenvironment)()   










---

> **2.<span id="initdefaultmetafileread">initDefaultMetaFileRead</span>**

**方法签名：** 

  protected [MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) [initDefaultMetaFileRead](#initdefaultmetafileread)()   










---

> **3.<span id="parsejavaprojectmeta">parseJavaProjectMeta</span>**

**方法签名：** 

  public [JavaProjectMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaProjectMeta.md) [parseJavaProjectMeta](#parsejavaprojectmeta)()   









