# 类名称:JavaDocDocsifyTemplateTheme

|  **项目名称:**    |  ejdoc    |   **模块名称:**   |jdocGenerate|
| ----: | :---- | ----: |:---- |
|   **包名称:**   |  com.ejdoc.doc.generate.template.markdown.theme    |   **全路径信息:**   |com.ejdoc.doc.generate.template.markdown.theme.JavaDocDocsifyTemplateTheme|









**所有父类：**  
[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)

**所有父级接口：**  
[DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)







---

## 声明信息

> public class JavaDocDocsifyTemplateTheme extends [BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)   implements [DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)   












## 构造方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme)()   <br/><br/>|
|2|public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|
|3|public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   <br/><br/>|


## 方法汇总

|   索引  |    方法体和描述   |
| ---- | ---- |
|1|public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [replacePackageDesc](#replacepackagedesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) desc)   <br/><br/>|
|2|public void [writeTemplateThemeFile](#writetemplatethemefile-doctemplatethemeinfo)([DocTemplateThemeInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocTemplateThemeInfo.md) docTemplateThemeInfo)   <br/><br/>创建模板主题文件，解耦渲染与数据组装逻辑，渲染使用模板.|


---

### 从BaseOutTemplate类继承方法:

全路径信息com.ejdoc.doc.generate.template.[BaseOutTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md)  
[getDocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getDocGenerateConfig),[getGroupTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getGroupTemplate),[getI18nSetting](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#getI18nSetting),[loadCustomTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadCustomTemplate-templatetypeenum-docgenerateconfig),[loadI18N](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadI18N-locale-string),[loadTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#loadTemplate-templatetypeenum-map-boolean),[renderByTemplate](/jdocGenerate/com/ejdoc/doc/generate/template/BaseOutTemplate.md#renderByTemplate-map-template)



---

### 从DocTemplateTheme接口继承方法:

全路径信息com.ejdoc.doc.generate.template.[DocTemplateTheme](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md)  
[writeTemplateThemeFile](/jdocGenerate/com/ejdoc/doc/generate/template/DocTemplateTheme.md#writeTemplateThemeFile-doctemplatethemeinfo)



## 构造方法详细信息


---

> **1.<span id="javadocdocsifytemplatetheme">JavaDocDocsifyTemplateTheme</span>**

**构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme)()   








---

> **2.<span id="javadocdocsifytemplatetheme-docgenerateconfig">JavaDocDocsifyTemplateTheme</span>**

**构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-docgenerateconfig)([DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








---

> **3.<span id="javadocdocsifytemplatetheme-grouptemplate-docgenerateconfig">JavaDocDocsifyTemplateTheme</span>**

**构造方法签名：** 

  public  [JavaDocDocsifyTemplateTheme](#javadocdocsifytemplatetheme-grouptemplate-docgenerateconfig)(GroupTemplate groupTemplate,[DocGenerateConfig](/jdocGenerate/com/ejdoc/doc/generate/out/config/DocGenerateConfig.md) docGenerateConfig)   








## 方法详细信息


---

> **1.<span id="replacepackagedesc-string">replacePackageDesc</span>**

**方法签名：** 

  public [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) [replacePackageDesc](#replacepackagedesc-string)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html?is-external=true) desc)   










---

> **2.<span id="writetemplatethemefile-doctemplatethemeinfo">writeTemplateThemeFile</span>**

**方法签名：** 

  public void [writeTemplateThemeFile](#writetemplatethemefile-doctemplatethemeinfo)([DocTemplateThemeInfo](/jdocGenerate/com/ejdoc/doc/generate/model/DocTemplateThemeInfo.md) docTemplateThemeInfo)   


**描述：** 

创建模板主题文件，解耦渲染与数据组装逻辑，渲染使用模板

**参数描述：** 

  docTemplateThemeInfo - 







