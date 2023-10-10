# 类名称:MarkdownDocOutTemplate

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template.markdown    |   **全路径信息:**   |com.ejdoc.doc.generate.template.markdown.MarkdownDocOutTemplate|









**所有父类：**  
[AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md),[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)

**所有父级接口：**  
[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)

**所有子类：**  
[JavaDocMarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/JavaDocMarkdownDocOutTemplate.md)





---

## 声明信息

> public class MarkdownDocOutTemplate extends [AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md)     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [MarkdownDocOutTemplate](#markdowndocouttemplate)()   <br/><br/>|
|2|public  [MarkdownDocOutTemplate](#markdowndocouttemplate-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|
|3|public  [MarkdownDocOutTemplate](#markdowndocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [convertJsonFileToProp](#convertjsonfiletoprop-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   <br/><br/>|
|2|protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [loadCustomProp](#loadcustomprop-docoutfileinfo-jsonobject)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo,JSONObject jsonObject)   <br/><br/>子类加载自定义属性.|
|3|protected [TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) [loadTemplateType](#loadtemplatetype)()   <br/><br/>|


---

### 从AbstractDocOutTemplate类继承方法:

全路径信息com.ejdoc.doc.generate.template.[AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md)  
[convertJsonFileToProp](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md#convertJsonFileToProp-docoutfileinfo),[formatTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md#formatTemplate-docoutfileinfo),[loadTemplateType](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md#loadTemplateType),[writeFormat](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md#writeFormat-string-docoutfileinfo)

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

> **1.<span id="markdowndocouttemplate">MarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [MarkdownDocOutTemplate](#markdowndocouttemplate)()   








---

> **2.<span id="markdowndocouttemplate-docgenerateconfig">MarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [MarkdownDocOutTemplate](#markdowndocouttemplate-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








---

> **3.<span id="markdowndocouttemplate-grouptemplate-docgenerateconfig">MarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [MarkdownDocOutTemplate](#markdowndocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








## 方法详细信息


---

> **1.<span id="convertjsonfiletoprop-docoutfileinfo">convertJsonFileToProp</span>**

**方法签名：** 

  protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [convertJsonFileToProp](#convertjsonfiletoprop-docoutfileinfo)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo)   










---

> **2.<span id="loadcustomprop-docoutfileinfo-jsonobject">loadCustomProp</span>**

**方法签名：** 

  protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [loadCustomProp](#loadcustomprop-docoutfileinfo-jsonobject)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo,JSONObject jsonObject)   


**描述：** 

子类加载自定义属性

**参数描述：** 

  docOutFileInfo - 

  jsonObject - 








---

> **3.<span id="loadtemplatetype">loadTemplateType</span>**

**方法签名：** 

  protected [TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) [loadTemplateType](#loadtemplatetype)()   









