# 类名称:JavaMetaOverrideCommentPlugin

## 基本信息

* **全路径信息:** com.ejdoc.metainfo.seralize.seralize.plugin.JavaMetaOverrideCommentPlugin
* **包名称:** com.ejdoc.metainfo.seralize.seralize.plugin
* **项目名称:** ejdoc
* **模块名称:** metaInfoSeralize





* **所有父类：**  
[AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)
* **所有父级接口：**  
[JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)


---

## 声明信息
> public class JavaMetaOverrideCommentPlugin extends [AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)   implements [JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)   


* **描述：** 

  重写方法Comment读取设置插件
当前子类如果是重写方法，并且没有注释会读取父类注释信息并添加到子类中

* **描述：** 

重写方法Comment读取设置插件当前子类如果是重写方法，并且没有注释会读取父类注释信息并添加到子类中






## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public void [doPostParseMetaFile](#innerlink-dopostparsemetafile-javalangstring-javautillist-javautillist-javautilmap-javautilmap-comejdocmetainfoseralizeseralizeconfigseralizeconfig)(String outFilePath,List< JavaClassMetaOverrideCommentDto > allJavaClassList,List< File > jsonFiles,Map< String,JavaClassMetaOverrideCommentDto > allFileIndex,Map< String,DependPathPluginDto > dependPathMap,[SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   <br/>|
|2|public void [exePostJavaMetaSeralize](#innerlink-exepostjavametaseralize-comejdocmetainfoseralizeseralizeplugindtojavametaservalizeplugincontextdto)([JavaMetaServalizePluginContextDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/dto/JavaMetaServalizePluginContextDto.md) javaMetaServalizePluginContextDto)   <br/>|


---
### 继承类方法:AbstractJavaMetaSeralizePlugin

全路径信息com.ejdoc.metainfo.seralize.seralize.plugin.[AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)  



---
### 继承接口方法:JavaMetaSeralizePlugin

全路径信息com.ejdoc.metainfo.seralize.seralize.[JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)  
[exePostJavaMetaSeralize](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md#exePostJavaMetaSeralize-comejdocmetainfoseralizeseralizeplugindtojavametaservalizeplugincontextdto)




## 方法详细信息

---
> **1.<span id="innerlink-dopostparsemetafile-javalangstring-javautillist-javautillist-javautilmap-javautilmap-comejdocmetainfoseralizeseralizeconfigseralizeconfig">doPostParseMetaFile</span>**

* **方法签名：** 

  public void [doPostParseMetaFile](#dopostparsemetafile-javalangstring-javautillist-javautillist-javautilmap-javautilmap-comejdocmetainfoseralizeseralizeconfigseralizeconfig)(String outFilePath,List< JavaClassMetaOverrideCommentDto > allJavaClassList,List< File > jsonFiles,Map< String,JavaClassMetaOverrideCommentDto > allFileIndex,Map< String,DependPathPluginDto > dependPathMap,[SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   







---
> **2.<span id="innerlink-exepostjavametaseralize-comejdocmetainfoseralizeseralizeplugindtojavametaservalizeplugincontextdto">exePostJavaMetaSeralize</span>**

* **方法签名：** 

  public void [exePostJavaMetaSeralize](#exepostjavametaseralize-comejdocmetainfoseralizeseralizeplugindtojavametaservalizeplugincontextdto)([JavaMetaServalizePluginContextDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/dto/JavaMetaServalizePluginContextDto.md) javaMetaServalizePluginContextDto)   







