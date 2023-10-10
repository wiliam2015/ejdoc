# 类名称:JavaMetaOverrideCommentPlugin

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.seralize.plugin    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.seralize.plugin.JavaMetaOverrideCommentPlugin|









**所有父类：**  
[AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)

**所有父级接口：**  
[JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)







---

## 声明信息

> public class JavaMetaOverrideCommentPlugin extends [AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)   implements [JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)   


**描述：** 重写方法Comment读取设置插件
当前子类如果是重写方法，并且没有注释会读取父类注释信息并添加到子类中












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public void [doPostParseMetaFile](#dopostparsemetafile-string-list-list-map-map-seralizeconfig)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) outFilePath,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMetaOverrideCommentDto > allJavaClassList,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > jsonFiles,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaClassMetaOverrideCommentDto > allFileIndex,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),DependPathPluginDto > dependPathMap,[SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   <br/><br/>|
|2|public void [exePostJavaMetaSeralize](#exepostjavametaseralize-javametaservalizeplugincontextdto)([JavaMetaServalizePluginContextDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/dto/JavaMetaServalizePluginContextDto.md) javaMetaServalizePluginContextDto)   <br/><br/>|


---

### 从AbstractJavaMetaSeralizePlugin类继承方法:

全路径信息com.ejdoc.metainfo.seralize.seralize.plugin.[AbstractJavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md)  
[parseBaseClassDependPath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md#parseBaseClassDependPath-seralizeconfig-list-string-map),[setRelativePath](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/AbstractJavaMetaSeralizePlugin.md#setRelativePath-seralizeconfig-javametafileinfo-string-javaclassmeta)



---

### 从JavaMetaSeralizePlugin接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.seralize.[JavaMetaSeralizePlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md)  
[exePostJavaMetaSeralize](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/JavaMetaSeralizePlugin.md#exePostJavaMetaSeralize-javametaservalizeplugincontextdto)




## 方法详细信息


---

> **1.<span id="dopostparsemetafile-string-list-list-map-map-seralizeconfig">doPostParseMetaFile</span>**

**方法签名：** 

  public void [doPostParseMetaFile](#dopostparsemetafile-string-list-list-map-map-seralizeconfig)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) outFilePath,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMetaOverrideCommentDto > allJavaClassList,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html?is-external=true) > jsonFiles,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaClassMetaOverrideCommentDto > allFileIndex,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),DependPathPluginDto > dependPathMap,[SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig)   










---

> **2.<span id="exepostjavametaseralize-javametaservalizeplugincontextdto">exePostJavaMetaSeralize</span>**

**方法签名：** 

  public void [exePostJavaMetaSeralize](#exepostjavametaseralize-javametaservalizeplugincontextdto)([JavaMetaServalizePluginContextDto](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/dto/JavaMetaServalizePluginContextDto.md) javaMetaServalizePluginContextDto)   









