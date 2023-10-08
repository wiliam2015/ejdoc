# 类名称:MetaIndexContext

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.index    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.index.MetaIndexContext|



















---

## 声明信息

> public class MetaIndexContext     


**描述：** 元数据索引上下文












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllClassIndexMap](#getallclassindexmap)()   <br/><br/>|
|2|public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) > [getAllJavaMetaFileIndexMap](#getalljavametafileindexmap)()   <br/><br/>|
|3|public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) > [getAllTreeIndexClassMap](#getalltreeindexclassmap)()   <br/><br/>|
|4|public static [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [getClassMetaByFullName](#getclassmetabyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   <br/><br/>|
|5|public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getClassMetaByPackage](#getclassmetabypackage-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageName)   <br/><br/>|
|6|public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getJavaClassMetaList](#getjavaclassmetalist)()   <br/><br/>|
|7|public static [JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) [getJavaMetaFileByFullName](#getjavametafilebyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   <br/><br/>|
|8|public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) > [getJavaMetaFileInfos](#getjavametafileinfos)()   <br/><br/>|
|9|public static [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getOutFilePath](#getoutfilepath)()   <br/><br/>|
|10|public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < JavaClassMeta >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [getPackageNameIndexMap](#getpackagenameindexmap)()   <br/><br/>|
|11|public static [TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) [getTreeIndexClassMetaByFullName](#gettreeindexclassmetabyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   <br/><br/>|
|12|public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) > [getTreeIndexClassMetas](#gettreeindexclassmetas)()   <br/><br/>|
|13|public static void [setAllClassIndexMap](#setallclassindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaClassMeta > allClassIndexMap)   <br/><br/>|
|14|public static void [setAllJavaMetaFileIndexMap](#setalljavametafileindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaMetaFileInfo > allJavaMetaFileIndexMap)   <br/><br/>|
|15|public static void [setAllTreeIndexClassMap](#setalltreeindexclassmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),TreeIndexClassMeta > allTreeIndexClassMap)   <br/><br/>|
|16|public static void [setJavaClassMetaList](#setjavaclassmetalist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   <br/><br/>|
|17|public static void [setJavaMetaFileInfos](#setjavametafileinfos-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaMetaFileInfo > javaMetaFileInfos)   <br/><br/>|
|18|public static void [setOutFilePath](#setoutfilepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) outFilePath)   <br/><br/>|
|19|public static void [setPackageNameIndexMap](#setpackagenameindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < JavaClassMeta >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > packageNameIndexMap)   <br/><br/>|
|20|public static void [setTreeIndexClassMetas](#settreeindexclassmetas-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< TreeIndexClassMeta > treeIndexClassMetas)   <br/><br/>|







## 方法详细信息


---

> **1.<span id="getallclassindexmap">getAllClassIndexMap</span>**

**方法签名：** 

  public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllClassIndexMap](#getallclassindexmap)()   










---

> **2.<span id="getalljavametafileindexmap">getAllJavaMetaFileIndexMap</span>**

**方法签名：** 

  public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) > [getAllJavaMetaFileIndexMap](#getalljavametafileindexmap)()   










---

> **3.<span id="getalltreeindexclassmap">getAllTreeIndexClassMap</span>**

**方法签名：** 

  public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) > [getAllTreeIndexClassMap](#getalltreeindexclassmap)()   










---

> **4.<span id="getclassmetabyfullname-string">getClassMetaByFullName</span>**

**方法签名：** 

  public static [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [getClassMetaByFullName](#getclassmetabyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   










---

> **5.<span id="getclassmetabypackage-string">getClassMetaByPackage</span>**

**方法签名：** 

  public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getClassMetaByPackage](#getclassmetabypackage-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) packageName)   










---

> **6.<span id="getjavaclassmetalist">getJavaClassMetaList</span>**

**方法签名：** 

  public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getJavaClassMetaList](#getjavaclassmetalist)()   










---

> **7.<span id="getjavametafilebyfullname-string">getJavaMetaFileByFullName</span>**

**方法签名：** 

  public static [JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) [getJavaMetaFileByFullName](#getjavametafilebyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   










---

> **8.<span id="getjavametafileinfos">getJavaMetaFileInfos</span>**

**方法签名：** 

  public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaMetaFileInfo](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/JavaMetaFileInfo.md) > [getJavaMetaFileInfos](#getjavametafileinfos)()   










---

> **9.<span id="getoutfilepath">getOutFilePath</span>**

**方法签名：** 

  public static [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [getOutFilePath](#getoutfilepath)()   










---

> **10.<span id="getpackagenameindexmap">getPackageNameIndexMap</span>**

**方法签名：** 

  public static [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < JavaClassMeta >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > [getPackageNameIndexMap](#getpackagenameindexmap)()   










---

> **11.<span id="gettreeindexclassmetabyfullname-string">getTreeIndexClassMetaByFullName</span>**

**方法签名：** 

  public static [TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) [getTreeIndexClassMetaByFullName](#gettreeindexclassmetabyfullname-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) fullClassName)   










---

> **12.<span id="gettreeindexclassmetas">getTreeIndexClassMetas</span>**

**方法签名：** 

  public static [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [TreeIndexClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/index/TreeIndexClassMeta.md) > [getTreeIndexClassMetas](#gettreeindexclassmetas)()   










---

> **13.<span id="setallclassindexmap-map">setAllClassIndexMap</span>**

**方法签名：** 

  public static void [setAllClassIndexMap](#setallclassindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaClassMeta > allClassIndexMap)   










---

> **14.<span id="setalljavametafileindexmap-map">setAllJavaMetaFileIndexMap</span>**

**方法签名：** 

  public static void [setAllJavaMetaFileIndexMap](#setalljavametafileindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),JavaMetaFileInfo > allJavaMetaFileIndexMap)   










---

> **15.<span id="setalltreeindexclassmap-map">setAllTreeIndexClassMap</span>**

**方法签名：** 

  public static void [setAllTreeIndexClassMap](#setalltreeindexclassmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),TreeIndexClassMeta > allTreeIndexClassMap)   










---

> **16.<span id="setjavaclassmetalist-list">setJavaClassMetaList</span>**

**方法签名：** 

  public static void [setJavaClassMetaList](#setjavaclassmetalist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   










---

> **17.<span id="setjavametafileinfos-list">setJavaMetaFileInfos</span>**

**方法签名：** 

  public static void [setJavaMetaFileInfos](#setjavametafileinfos-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaMetaFileInfo > javaMetaFileInfos)   










---

> **18.<span id="setoutfilepath-string">setOutFilePath</span>**

**方法签名：** 

  public static void [setOutFilePath](#setoutfilepath-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) outFilePath)   










---

> **19.<span id="setpackagenameindexmap-map">setPackageNameIndexMap</span>**

**方法签名：** 

  public static void [setPackageNameIndexMap](#setpackagenameindexmap-map)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true)< < JavaClassMeta >[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true),[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true) > packageNameIndexMap)   










---

> **20.<span id="settreeindexclassmetas-list">setTreeIndexClassMetas</span>**

**方法签名：** 

  public static void [setTreeIndexClassMetas](#settreeindexclassmetas-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< TreeIndexClassMeta > treeIndexClassMetas)   









