# 类名称:BaseOutTemplate

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template    |   **全路径信息:**   |com.ejdoc.doc.generate.template.BaseOutTemplate|













**所有子类：**  
[AbstractDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/AbstractDocOutTemplate.md),[MarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/MarkdownDocOutTemplate.md),[JavaDocMarkdownDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/JavaDocMarkdownDocOutTemplate.md),[HtmlDocOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/html/HtmlDocOutTemplate.md),[JavaDocDocsifyTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/markdown/theme/JavaDocDocsifyTemplateTheme.md)






---

## 声明信息

> public class BaseOutTemplate     












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [BaseOutTemplate](#baseouttemplate)()   <br/><br/>|
|2|public  [BaseOutTemplate](#baseouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) [getDocGenerateConfig](#getdocgenerateconfig)()   <br/><br/>|
|2|public GroupTemplate [getGroupTemplate](#getgrouptemplate)()   <br/><br/>|
|3|public Setting [getI18nSetting](#geti18nsetting)()   <br/><br/>|
|4|protected Template [loadCustomTemplate](#loadcustomtemplate-templatetypeenum-docgenerateconfig)([TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) templateType,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>加载自定义模板文件,子类可以重写，扩展自己的逻辑.|
|5|protected void [loadI18N](#loadi18n-locale-string)([Locale](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html?is-external=true) locale,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   <br/><br/>|
|6|protected [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [loadTemplate](#loadtemplate-templatetypeenum-map-boolean)([TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) templateType,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) propMap,[boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) mainFile)   <br/><br/>|
|7|protected [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [renderByTemplate](#renderbytemplate-map-template)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) propMap,Template template)   <br/><br/>|






## 构造方法详细信息


---

> **1.<span id="baseouttemplate">BaseOutTemplate</span>**

**构造方法签名：** 

  public  [BaseOutTemplate](#baseouttemplate)()   








---

> **2.<span id="baseouttemplate-grouptemplate-docgenerateconfig">BaseOutTemplate</span>**

**构造方法签名：** 

  public  [BaseOutTemplate](#baseouttemplate-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








## 方法详细信息


---

> **1.<span id="getdocgenerateconfig">getDocGenerateConfig</span>**

**方法签名：** 

  public [DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) [getDocGenerateConfig](#getdocgenerateconfig)()   










---

> **2.<span id="getgrouptemplate">getGroupTemplate</span>**

**方法签名：** 

  public GroupTemplate [getGroupTemplate](#getgrouptemplate)()   










---

> **3.<span id="geti18nsetting">getI18nSetting</span>**

**方法签名：** 

  public Setting [getI18nSetting](#geti18nsetting)()   










---

> **4.<span id="loadcustomtemplate-templatetypeenum-docgenerateconfig">loadCustomTemplate</span>**

**方法签名：** 

  protected Template [loadCustomTemplate](#loadcustomtemplate-templatetypeenum-docgenerateconfig)([TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) templateType,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   


**描述：** 

加载自定义模板文件,子类可以重写，扩展自己的逻辑

**参数描述：** 

  templateType - 

  docGenerateConfig - 








---

> **5.<span id="loadi18n-locale-string">loadI18N</span>**

**方法签名：** 

  protected void [loadI18N](#loadi18n-locale-string)([Locale](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html?is-external=true) locale,[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) configFilePath)   










---

> **6.<span id="loadtemplate-templatetypeenum-map-boolean">loadTemplate</span>**

**方法签名：** 

  protected [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [loadTemplate](#loadtemplate-templatetypeenum-map-boolean)([TemplateTypeEnum](/jdocGenerate/com/ejdoc/doc/generate/enums/TemplateTypeEnum.md) templateType,[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) propMap,[boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html?is-external=true) mainFile)   










---

> **7.<span id="renderbytemplate-map-template">renderByTemplate</span>**

**方法签名：** 

  protected [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [renderByTemplate](#renderbytemplate-map-template)([Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html?is-external=true) propMap,Template template)   









