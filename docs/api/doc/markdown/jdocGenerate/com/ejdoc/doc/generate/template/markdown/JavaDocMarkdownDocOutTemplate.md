# 类名称:JavaDocMarkdownDocOutTemplate

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template.markdown    |   **全路径信息:**   |com.ejdoc.doc.generate.template.markdown.JavaDocMarkdownDocOutTemplate|









**所有父类：**  
[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md),[AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md),[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)

**所有父级接口：**  
[DocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/DocOutTemplate.md)







---

## 声明信息

> public class JavaDocMarkdownDocOutTemplate extends [MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md)     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate)()   <br/><br/>|
|2|public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|
|3|public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [loadCustomProp](#loadcustomprop-docoutfileinfo-jsonobject)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo,JSONObject jsonObject)   <br/><br/>|


---

### 从MarkdownDocOutTemplate类继承方法:

全路径信息com.ejdoc.doc.generate.template.markdown.[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md)  
[convertJsonFileToProp](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md#convertJsonFileToProp-docoutfileinfo),[loadCustomProp](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md#loadCustomProp-docoutfileinfo-jsonobject),[loadTemplateType](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md#loadTemplateType)

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

> **1.<span id="javadocmarkdowndocouttemplate">JavaDocMarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate)()   








---

> **2.<span id="javadocmarkdowndocouttemplate-docgenerateconfig">JavaDocMarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








---

> **3.<span id="javadocmarkdowndocouttemplate-grouptemplate-docgenerateconfig">JavaDocMarkdownDocOutTemplate</span>**

**构造方法签名：** 

  public  [JavaDocMarkdownDocOutTemplate](#javadocmarkdowndocouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








## 方法详细信息


---

> **1.<span id="loadcustomprop-docoutfileinfo-jsonobject">loadCustomProp</span>**

**方法签名：** 

  protected [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) [loadCustomProp](#loadcustomprop-docoutfileinfo-jsonobject)([DocOutFileInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocOutFileInfo.md) docOutFileInfo,JSONObject jsonObject)   









