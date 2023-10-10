# 抽象类:AbstractJavaMetaSeralizePlugin

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.seralize.plugin    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.seralize.plugin.AbstractJavaMetaSeralizePlugin|













**所有子类：**  
[JavaMetaOverrideCommentPlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/JavaMetaOverrideCommentPlugin.md),[JavaMetaSeralizeDependPathPlugin](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/plugin/JavaMetaSeralizeDependPathPlugin.md),[JavaDocDeepDependParsePlugin](/jdocGenerate/com/ejdoc/doc/generate/out/javadoc/JavaDocDeepDependParsePlugin.md),[JavaDocDeprecatedParsePlugin](/jdocGenerate/com/ejdoc/doc/generate/out/javadoc/JavaDocDeprecatedParsePlugin.md)





---

## 声明信息

> public abstract class AbstractJavaMetaSeralizePlugin     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected void [parseBaseClassDependPath](#parsebaseclassdependpath-seralizeconfig-list-string-map)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaMetaFileInfo > dependPathMap)   <br/><br/>|
|2|protected void [setRelativePath](#setrelativepath-seralizeconfig-javametafileinfo-string-javaclassmeta)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig,[JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) dependJavaMetaFileInfo,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) dependClassMeta)   <br/><br/>设置依赖的路径，相对路径或绝对路径.|







## 方法详细信息


---

> **1.<span id="parsebaseclassdependpath-seralizeconfig-list-string-map">parseBaseClassDependPath</span>**

**方法签名：** 

  protected void [parseBaseClassDependPath](#parsebaseclassdependpath-seralizeconfig-list-string-map)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaMetaFileInfo > dependPathMap)   










---

> **2.<span id="setrelativepath-seralizeconfig-javametafileinfo-string-javaclassmeta">setRelativePath</span>**

**方法签名：** 

  protected void [setRelativePath](#setrelativepath-seralizeconfig-javametafileinfo-string-javaclassmeta)([SeralizeConfig](/metaInfoSeralize/com/ejdoc/metainfo/seralize/seralize/config/SeralizeConfig.md) seralizeConfig,[JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) dependJavaMetaFileInfo,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) absolutePath,[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) dependClassMeta)   


**描述：** 

设置依赖的路径，相对路径或绝对路径

**参数描述：** 

  seralizeConfig - 序列化配置信息

  dependJavaMetaFileInfo - 元文件依赖的文件信息

  absolutePath - 元文件的绝对路径

  dependClassMeta - 元文件依赖的类信息 与dependJavaMetaFileInfo是同一个







