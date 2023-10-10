# 类名称:TreeIndexClassMeta

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.index    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.index.TreeIndexClassMeta|



















---

## 声明信息

> public class TreeIndexClassMeta     


**描述：** 索引信息类包含父子类












## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllInterfaceClasses](#addallinterfaceclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   <br/><br/>|
|2|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllInterfaceClasses](#addallinterfaceclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|3|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubClasses](#addallsubclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|4|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubClasses](#addallsubclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   <br/><br/>|
|5|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubInterfaceClasses](#addallsubinterfaceclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   <br/><br/>|
|6|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubInterfaceClasses](#addallsubinterfaceclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|7|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSupperClasses](#addallsupperclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|8|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addChildClasses](#addchildclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|9|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addChildInterface](#addchildinterface-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|10|public void [addChildInterfaceList](#addchildinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > classList)   <br/><br/>|
|11|public void [addInterfaceList](#addinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > classList)   <br/><br/>|
|12|public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addSupperClasses](#addsupperclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|13|public void [addSupperClasses](#addsupperclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > supperClassList)   <br/><br/>|
|14|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllInterfaceClasses](#getallinterfaceclasses)()   <br/><br/>|
|15|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSubClasses](#getallsubclasses)()   <br/><br/>|
|16|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSubInterfaceClasses](#getallsubinterfaceclasses)()   <br/><br/>|
|17|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSupperClasses](#getallsupperclasses)()   <br/><br/>|
|18|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getChildClassList](#getchildclasslist)()   <br/><br/>|
|19|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getChildInterfaceList](#getchildinterfacelist)()   <br/><br/>|
|20|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInterfaceList](#getinterfacelist)()   <br/><br/>|
|21|public [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [getJavaClassMeta](#getjavaclassmeta)()   <br/><br/>|
|22|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getSupperClassList](#getsupperclasslist)()   <br/><br/>|
|23|public void [setChildClassList](#setchildclasslist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > childClassList)   <br/><br/>|
|24|public void [setChildInterfaceList](#setchildinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > childInterfaceList)   <br/><br/>|
|25|public void [setInterfaceList](#setinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > interfaceList)   <br/><br/>|
|26|public void [setJavaClassMeta](#setjavaclassmeta-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   <br/><br/>|
|27|public void [setSupperClassList](#setsupperclasslist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > supperClassList)   <br/><br/>|







## 方法详细信息


---

> **1.<span id="addallinterfaceclasses-list">addAllInterfaceClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllInterfaceClasses](#addallinterfaceclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   










---

> **2.<span id="addallinterfaceclasses-javaclassmeta">addAllInterfaceClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllInterfaceClasses](#addallinterfaceclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **3.<span id="addallsubclasses-javaclassmeta">addAllSubClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubClasses](#addallsubclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **4.<span id="addallsubclasses-list">addAllSubClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubClasses](#addallsubclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   










---

> **5.<span id="addallsubinterfaceclasses-list">addAllSubInterfaceClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubInterfaceClasses](#addallsubinterfaceclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   










---

> **6.<span id="addallsubinterfaceclasses-javaclassmeta">addAllSubInterfaceClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSubInterfaceClasses](#addallsubinterfaceclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **7.<span id="addallsupperclasses-javaclassmeta">addAllSupperClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addAllSupperClasses](#addallsupperclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **8.<span id="addchildclasses-javaclassmeta">addChildClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addChildClasses](#addchildclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **9.<span id="addchildinterface-javaclassmeta">addChildInterface</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addChildInterface](#addchildinterface-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **10.<span id="addchildinterfacelist-list">addChildInterfaceList</span>**

**方法签名：** 

  public void [addChildInterfaceList](#addchildinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > classList)   










---

> **11.<span id="addinterfacelist-list">addInterfaceList</span>**

**方法签名：** 

  public void [addInterfaceList](#addinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > classList)   










---

> **12.<span id="addsupperclasses-javaclassmeta">addSupperClasses</span>**

**方法签名：** 

  public [boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) [addSupperClasses](#addsupperclasses-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **13.<span id="addsupperclasses-list">addSupperClasses</span>**

**方法签名：** 

  public void [addSupperClasses](#addsupperclasses-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > supperClassList)   










---

> **14.<span id="getallinterfaceclasses">getAllInterfaceClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllInterfaceClasses](#getallinterfaceclasses)()   










---

> **15.<span id="getallsubclasses">getAllSubClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSubClasses](#getallsubclasses)()   










---

> **16.<span id="getallsubinterfaceclasses">getAllSubInterfaceClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSubInterfaceClasses](#getallsubinterfaceclasses)()   










---

> **17.<span id="getallsupperclasses">getAllSupperClasses</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getAllSupperClasses](#getallsupperclasses)()   










---

> **18.<span id="getchildclasslist">getChildClassList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getChildClassList](#getchildclasslist)()   










---

> **19.<span id="getchildinterfacelist">getChildInterfaceList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getChildInterfaceList](#getchildinterfacelist)()   










---

> **20.<span id="getinterfacelist">getInterfaceList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getInterfaceList](#getinterfacelist)()   










---

> **21.<span id="getjavaclassmeta">getJavaClassMeta</span>**

**方法签名：** 

  public [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) [getJavaClassMeta](#getjavaclassmeta)()   










---

> **22.<span id="getsupperclasslist">getSupperClassList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) > [getSupperClassList](#getsupperclasslist)()   










---

> **23.<span id="setchildclasslist-list">setChildClassList</span>**

**方法签名：** 

  public void [setChildClassList](#setchildclasslist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > childClassList)   










---

> **24.<span id="setchildinterfacelist-list">setChildInterfaceList</span>**

**方法签名：** 

  public void [setChildInterfaceList](#setchildinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > childInterfaceList)   










---

> **25.<span id="setinterfacelist-list">setInterfaceList</span>**

**方法签名：** 

  public void [setInterfaceList](#setinterfacelist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > interfaceList)   










---

> **26.<span id="setjavaclassmeta-javaclassmeta">setJavaClassMeta</span>**

**方法签名：** 

  public void [setJavaClassMeta](#setjavaclassmeta-javaclassmeta)([JavaClassMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaClassMeta.md) javaClassMeta)   










---

> **27.<span id="setsupperclasslist-list">setSupperClassList</span>**

**方法签名：** 

  public void [setSupperClassList](#setsupperclasslist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > supperClassList)   









