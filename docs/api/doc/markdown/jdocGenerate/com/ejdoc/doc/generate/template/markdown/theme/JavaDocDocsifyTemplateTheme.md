# 类名称:JavaDocDocsifyTemplateTheme

## 基本信息

* **全路径信息:** com.ejdoc.doc.generate.template.markdown.theme.JavaDocDocsifyTemplateTheme
* **包名称:** com.ejdoc.doc.generate.template.markdown.theme
* **项目名称:** ejdoc
* **模块名称:** jdocGenerate





* **所有父类：**  
[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)
* **所有父级接口：**  
[DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)


---

## 声明信息
> public class JavaDocDocsifyTemplateTheme extends [BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)   implements [DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)   


* **描述：** 

  







## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [JavaDocDocsifyTemplateTheme](#innerlink-javadocdocsifytemplatetheme)()   <br/>|
|2|public  [JavaDocDocsifyTemplateTheme](#innerlink-javadocdocsifytemplatetheme-comejdocdocgenerateoutconfigdocgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/>|
|3|public  [JavaDocDocsifyTemplateTheme](#innerlink-javadocdocsifytemplatetheme-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/>|

## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public String [replacePackageDesc](#innerlink-replacepackagedesc-javalangstring)(String desc)   <br/>|
|2|public void [writeTemplateThemeFile](#innerlink-writetemplatethemefile-comejdocdocgeneratemodeldoctemplatethemeinfo)([DocTemplateThemeInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocTemplateThemeInfo.md) docTemplateThemeInfo)   <br/><br/><br/>创建模板主题文件，解耦渲染与数据组装逻辑，渲染使用模板|


---
### 继承类方法:BaseOutTemplate

全路径信息com.ejdoc.doc.generate.template.[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)  
[getDocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getDocGenerateConfig),[getGroupTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getGroupTemplate),[getI18nSetting](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getI18nSetting)


---
### 继承接口方法:DocTemplateTheme

全路径信息com.ejdoc.doc.generate.template.[DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)  
[writeTemplateThemeFile](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md#writeTemplateThemeFile-comejdocdocgeneratemodeldoctemplatethemeinfo)



## 构造方法详细信息

---
> **1.<span id="innerlink-javadocdocsifytemplatetheme">JavaDocDocsifyTemplateTheme</span>**

* **构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme)()   






---
> **2.<span id="innerlink-javadocdocsifytemplatetheme-comejdocdocgenerateoutconfigdocgenerateconfig">JavaDocDocsifyTemplateTheme</span>**

* **构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-comejdocdocgenerateoutconfigdocgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   






---
> **3.<span id="innerlink-javadocdocsifytemplatetheme-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig">JavaDocDocsifyTemplateTheme</span>**

* **构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-grouptemplate-comejdocdocgenerateoutconfigdocgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   







## 方法详细信息

---
> **1.<span id="innerlink-replacepackagedesc-javalangstring">replacePackageDesc</span>**

* **方法签名：** 

  public String [replacePackageDesc](#replacepackagedesc-javalangstring)(String desc)   







---
> **2.<span id="innerlink-writetemplatethemefile-comejdocdocgeneratemodeldoctemplatethemeinfo">writeTemplateThemeFile</span>**

* **方法签名：** 

  public void [writeTemplateThemeFile](#writetemplatethemefile-comejdocdocgeneratemodeldoctemplatethemeinfo)([DocTemplateThemeInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocTemplateThemeInfo.md) docTemplateThemeInfo)   


* **描述：** 

创建模板主题文件，解耦渲染与数据组装逻辑，渲染使用模板
* **参数描述：** 

  docTemplateThemeInfo - 





