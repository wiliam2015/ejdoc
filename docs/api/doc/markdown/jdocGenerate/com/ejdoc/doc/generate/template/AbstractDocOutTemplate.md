# 抽象类:AbstractDocOutTemplate

## 基本信息

* **全路径信息:** com.ejdoc.doc.generate.template.AbstractDocOutTemplate
* **包名称:** com.ejdoc.doc.generate.template
* **项目名称:** ejdoc
* **模块名称:** jdocGenerate





* **所有父类：**  
[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)
* **所有父级接口：**  
[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)
* **所有子类：**  
[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md),[JavaDocMarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/JavaDocMarkdownDocOutTemplate.md),[HtmlDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/html/HtmlDocOutTemplate.md)

---

## 声明信息
> public abstract class AbstractDocOutTemplate extends [BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)   implements [DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)   


* **描述：** 

  







## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AbstractDocOutTemplate](#innerlink-abstractdocouttemplate)()   <br/>|
|2|public  [AbstractDocOutTemplate](#innerlink-abstractdocouttemplate-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/>|

## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public String [formatTemplate](#innerlink-formattemplate-comejdocdocgeneratemodeldocoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/>|
|2|public void [writeFormat](#innerlink-writeformat-javalangstring-comejdocdocgeneratemodeldocoutfileinfo)(String formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/>|


---
### 继承类方法:BaseOutTemplate

全路径信息com.ejdoc.doc.generate.template.[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)  
[getDocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getDocGenerateConfig),[getGroupTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getGroupTemplate),[getI18nSetting](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getI18nSetting)


---
### 继承接口方法:DocOutTemplate

全路径信息com.ejdoc.doc.generate.template.[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)  
[formatTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md#formatTemplate-comejdocdocgeneratemodeldocoutfileinfo),[writeFormat](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md#writeFormat-javalangstring-comejdocdocgeneratemodeldocoutfileinfo)



## 构造方法详细信息

---
> **1.<span id="innerlink-abstractdocouttemplate">AbstractDocOutTemplate</span>**

* **构造方法签名：** 

  public  [AbstractDocOutTemplate](#abstractdocouttemplate)()   






---
> **2.<span id="innerlink-abstractdocouttemplate-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig">AbstractDocOutTemplate</span>**

* **构造方法签名：** 

  public  [AbstractDocOutTemplate](#abstractdocouttemplate-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   







## 方法详细信息

---
> **1.<span id="innerlink-formattemplate-comejdocdocgeneratemodeldocoutfileinfo">formatTemplate</span>**

* **方法签名：** 

  public String [formatTemplate](#formattemplate-comejdocdocgeneratemodeldocoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   







---
> **2.<span id="innerlink-writeformat-javalangstring-comejdocdocgeneratemodeldocoutfileinfo">writeFormat</span>**

* **方法签名：** 

  public void [writeFormat](#writeformat-javalangstring-comejdocdocgeneratemodeldocoutfileinfo)(String formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   







