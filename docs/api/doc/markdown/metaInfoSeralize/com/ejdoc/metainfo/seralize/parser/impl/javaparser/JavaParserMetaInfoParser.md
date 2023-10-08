# 类名称:JavaParserMetaInfoParser

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |metaInfoSeralize|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.metainfo.seralize.parser.impl.javaparser    |   **全路径信息:**   |com.ejdoc.metainfo.seralize.parser.impl.javaparser.JavaParserMetaInfoParser|









**所有父类：**  
[AbstractMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md)

**所有父级接口：**  
[MetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md),[MetaProjectMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md)







---

## 声明信息

> public class JavaParserMetaInfoParser extends [AbstractMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md)     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [JavaParserMetaInfoParser](#javaparsermetainfoparser)()   <br/><br/>|
|2|public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   <br/><br/>|
|3|public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-metafileread)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead)   <br/><br/>|
|4|public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-metafileread-list)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserTypeDeclarationParse > javaParserTypeDeclarationParseList)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md) > [getJavaParserTypeDeclarationParseList](#getjavaparsertypedeclarationparselist)()   <br/><br/>|
|2|public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaModuleMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModuleMeta.md) > [parseAllJavaModuletMeta](#parsealljavamoduletmeta)()   <br/><br/>|
|3|protected void [postParsingJavaClass](#postparsingjavaclass-javaparsermetacontext-list)([JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   <br/><br/>子类重载使用.|
|4|public void [setJavaParserTypeDeclarationParseList](#setjavaparsertypedeclarationparselist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserTypeDeclarationParse > javaParserTypeDeclarationParseList)   <br/><br/>|


---

### 从AbstractMetaInfoParser类继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.impl.[AbstractMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md)  
[getMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md#getMetaEnvironment),[initDefaultMetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md#initDefaultMetaFileRead),[parseJavaProjectMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/AbstractMetaInfoParser.md#parseJavaProjectMeta)



---

### 从MetaInfoParser接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.[MetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md)  
[getMetaEnvironment](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md#getMetaEnvironment),[parseAllJavaModuletMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaInfoParser.md#parseAllJavaModuletMeta)

---

### 从MetaProjectMetaInfoParser接口继承方法:

全路径信息com.ejdoc.metainfo.seralize.parser.[MetaProjectMetaInfoParser](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md)  
[parseJavaProjectMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/MetaProjectMetaInfoParser.md#parseJavaProjectMeta)



## 构造方法详细信息


---

> **1.<span id="javaparsermetainfoparser">JavaParserMetaInfoParser</span>**

**构造方法签名：** 

  public  [JavaParserMetaInfoParser](#javaparsermetainfoparser)()   








---

> **2.<span id="javaparsermetainfoparser-string">JavaParserMetaInfoParser</span>**

**构造方法签名：** 

  public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   








---

> **3.<span id="javaparsermetainfoparser-metafileread">JavaParserMetaInfoParser</span>**

**构造方法签名：** 

  public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-metafileread)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead)   








---

> **4.<span id="javaparsermetainfoparser-metafileread-list">JavaParserMetaInfoParser</span>**

**构造方法签名：** 

  public  [JavaParserMetaInfoParser](#javaparsermetainfoparser-metafileread-list)([MetaFileRead](/metaInfoSeralize/com/ejdoc/metainfo/seralize/resource/MetaFileRead.md) metaFileRead,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserTypeDeclarationParse > javaParserTypeDeclarationParseList)   








## 方法详细信息


---

> **1.<span id="getjavaparsertypedeclarationparselist">getJavaParserTypeDeclarationParseList</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaParserTypeDeclarationParse](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/type/JavaParserTypeDeclarationParse.md) > [getJavaParserTypeDeclarationParseList](#getjavaparsertypedeclarationparselist)()   










---

> **2.<span id="parsealljavamoduletmeta">parseAllJavaModuletMeta</span>**

**方法签名：** 

  public [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< [JavaModuleMeta](/metaInfoSeralize/com/ejdoc/metainfo/seralize/model/JavaModuleMeta.md) > [parseAllJavaModuletMeta](#parsealljavamoduletmeta)()   










---

> **3.<span id="postparsingjavaclass-javaparsermetacontext-list">postParsingJavaClass</span>**

**方法签名：** 

  protected void [postParsingJavaClass](#postparsingjavaclass-javaparsermetacontext-list)([JavaParserMetaContext](/metaInfoSeralize/com/ejdoc/metainfo/seralize/parser/impl/javaparser/JavaParserMetaContext.md) javaParserMetaContext,[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaClassMeta > javaClassMetaList)   


**描述：** 

子类重载使用

**参数描述：** 

  javaParserMetaContext - 

  javaClassMetaList - 








---

> **4.<span id="setjavaparsertypedeclarationparselist-list">setJavaParserTypeDeclarationParseList</span>**

**方法签名：** 

  public void [setJavaParserTypeDeclarationParseList](#setjavaparsertypedeclarationparselist-list)([List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html?is-external=true)< JavaParserTypeDeclarationParse > javaParserTypeDeclarationParseList)   









