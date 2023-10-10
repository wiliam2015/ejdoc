# 接口名称:DocOutTemplate

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template    |   **全路径信息:**   |com.ejdoc.doc.generate.template.DocOutTemplate|













**所有子类：**  
[AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md),[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md),[JavaDocMarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/JavaDocMarkdownDocOutTemplate.md),[HtmlDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/html/HtmlDocOutTemplate.md)





---

## 声明信息

> public interface DocOutTemplate     














## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [formatTemplate](#formattemplate-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>格式化模板内容.|
|2|void [writeFormat](#writeformat-string-docoutfileinfo)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>将模板内容输出生成文件.|







## 方法详细信息


---

> **1.<span id="formattemplate-docoutfileinfo">formatTemplate</span>**

**方法签名：** 

  [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [formatTemplate](#formattemplate-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   


**描述：** 

格式化模板内容

**参数描述：** 

  docOutFileInfo - doc文件信息


**返回值描述：**   格式化后的模板内容







---

> **2.<span id="writeformat-string-docoutfileinfo">writeFormat</span>**

**方法签名：** 

  void [writeFormat](#writeformat-string-docoutfileinfo)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   


**描述：** 

将模板内容输出生成文件

**参数描述：** 

  formatData - 







