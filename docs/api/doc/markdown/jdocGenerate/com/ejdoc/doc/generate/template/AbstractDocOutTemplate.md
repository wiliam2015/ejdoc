# 抽象类:AbstractDocOutTemplate

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template    |   **全路径信息:**   |com.ejdoc.doc.generate.template.AbstractDocOutTemplate|









**所有父类：**  
[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)

**所有父级接口：**  
[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)

**所有子类：**  
[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md),[JavaDocMarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/JavaDocMarkdownDocOutTemplate.md),[HtmlDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/html/HtmlDocOutTemplate.md)





---

## 声明信息

> public abstract class AbstractDocOutTemplate extends [BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)   implements [DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [AbstractDocOutTemplate](#abstractdocouttemplate)()   <br/><br/>|
|2|public  [AbstractDocOutTemplate](#abstractdocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected abstract [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [convertJsonFileToProp](#convertjsonfiletoprop-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>|
|2|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [formatTemplate](#formattemplate-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>|
|3|protected abstract [TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) [loadTemplateType](#loadtemplatetype)()   <br/><br/>|
|4|public void [writeFormat](#writeformat-string-docoutfileinfo)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>|


---

### 从BaseOutTemplate类继承方法:

全路径信息com.ejdoc.doc.generate.template.[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)  
[getDocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getDocGenerateConfig),[getGroupTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getGroupTemplate),[getI18nSetting](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getI18nSetting),[loadCustomTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadCustomTemplate-templatetypeenum-docgenerateconfig),[loadI18N](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadI18N-locale-string),[loadTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadTemplate-templatetypeenum-map-boolean),[renderByTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#renderByTemplate-map-template)



---

### 从DocOutTemplate接口继承方法:

全路径信息com.ejdoc.doc.generate.template.[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)  
[formatTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md#formatTemplate-docoutfileinfo),[writeFormat](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md#writeFormat-string-docoutfileinfo)



## 构造方法详细信息


---

> **1.<span id="abstractdocouttemplate">AbstractDocOutTemplate</span>**

**构造方法签名：** 

  public  [AbstractDocOutTemplate](#abstractdocouttemplate)()   








---

> **2.<span id="abstractdocouttemplate-grouptemplate-docgenerateconfig">AbstractDocOutTemplate</span>**

**构造方法签名：** 

  public  [AbstractDocOutTemplate](#abstractdocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








## 方法详细信息


---

> **1.<span id="convertjsonfiletoprop-docoutfileinfo">convertJsonFileToProp</span>**

**方法签名：** 

  protected abstract [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [convertJsonFileToProp](#convertjsonfiletoprop-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   










---

> **2.<span id="formattemplate-docoutfileinfo">formatTemplate</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [formatTemplate](#formattemplate-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   










---

> **3.<span id="loadtemplatetype">loadTemplateType</span>**

**方法签名：** 

  protected abstract [TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) [loadTemplateType](#loadtemplatetype)()   










---

> **4.<span id="writeformat-string-docoutfileinfo">writeFormat</span>**

**方法签名：** 

  public void [writeFormat](#writeformat-string-docoutfileinfo)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) formatData,[DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   









