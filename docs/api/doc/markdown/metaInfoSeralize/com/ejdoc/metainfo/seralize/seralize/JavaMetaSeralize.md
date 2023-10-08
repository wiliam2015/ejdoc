# 接口名称:JavaMetaSeralize

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.seralize    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.seralize.JavaMetaSeralize|





**作者：** 

  wiliam.hu









**所有子类：**  
[JavaMetaJsonSeralizeImpl](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/impl/JavaMetaJsonSeralizeImpl.md)

**所有子接口：**  
[JavaMetaJsonSeralize](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaJsonSeralize.md),[JavaMetaYamlSeralize](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaYamlSeralize.md)



---

## 声明信息

> public interface JavaMetaSeralize     


**描述：** Java元数据序列化接口<br>
提供默认的java序列化方法和自定义配置能力的序列化输出方法












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addMetaSeralizePlugins](#addmetaseralizeplugins-javametaseralizeplugin)([JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md) metaSeralizePlugin)   <br/><br/>增加序列化插件.|
|2|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [exeJavaMetaSeralize](#exejavametaseralize-seralizeconfig)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   <br/><br/>java序列化方法,传入自定义配置.|
|3|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [exeJavaMetaSeralize](#exejavametaseralize)()   <br/><br/>默认的java序列化方法，使用默认配置.|
|4|[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md) > [getMetaSeralizePlugins](#getmetaseralizeplugins)()   <br/><br/>获取java元数据序列化插件.|







## 方法详细信息


---

> **1.<span id="addmetaseralizeplugins-javametaseralizeplugin">addMetaSeralizePlugins</span>**

**方法签名：** 

  [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addMetaSeralizePlugins](#addmetaseralizeplugins-javametaseralizeplugin)([JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md) metaSeralizePlugin)   


**描述：** 

增加序列化插件

**参数描述：** 

  metaSeralizePlugin - 








---

> **2.<span id="exejavametaseralize-seralizeconfig">exeJavaMetaSeralize</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [exeJavaMetaSeralize](#exejavametaseralize-seralizeconfig)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   


**描述：** 

java序列化方法,传入自定义配置

**参数描述：** 

  seralizeConfig - 自定义配置








---

> **3.<span id="exejavametaseralize">exeJavaMetaSeralize</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [exeJavaMetaSeralize](#exejavametaseralize)()   


**描述：** 

默认的java序列化方法，使用默认配置








---

> **4.<span id="getmetaseralizeplugins">getMetaSeralizePlugins</span>**

**方法签名：** 

  [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md) > [getMetaSeralizePlugins](#getmetaseralizeplugins)()   


**描述：** 

获取java元数据序列化插件







