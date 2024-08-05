# 快速开始

## 安装
### 前置准备
* JDK1.8+
* Maven 3.x

### Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
   <groupId>com.ejdoc</groupId>
   <artifactId>jdocGenerate</artifactId>
   <version>0.6.6</version>
</dependency>
```

### Gradle
```
implementation 'com.ejdoc:jdocGenerate:0.6.6'
```

### 下载jar

点击以下链接，下载`jdocGenerate-X.X.X.jar`即可：

- [Maven中央库](https://repo1.maven.org/maven2/com/ejdoc/jdocGenerate/0.6.6/)

### 支持项目的目录结构
支持标准的Maven的单模块和父子模块的目录结构
#### 单模块
```
ProjectName
├── projectMeta.yml(非必须)
├── LICENSE(非必须)
├── README.md(非必须)
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── 代码
│   │   └── resources
│   │       ├── 资源文件

```

#### 父子模块
```
ProjectName
├── ModuleName1
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   └── resources
├── ModuleName2
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   └── resources
├── pom.xml
├── projectMeta.yml(非必须)
├── LICENSE(非必须)
├── README.md(非必须)

```
## 使用
1. (非必须)在项目跟目录下创建项目描述文件`projectMeta.yml`

```yaml
name: "项目名称"
title: "标题"
contract: "联系人|非必须"
description: "项目描述|非必须"
host: "项目网址|非必须"
license:
   - name: "使用的协议名称|非必须"
   - url: "协议地址|非必须"
```

2. (非必须)在项目根目录下创建`javaDocOutConfig.properties`文件

若不创建会自动探测使用当前代码库的根级目录输出

```properties
doc.out.root.dir=文档生成目录
project.root.dir=项目根目录
project.meta.seralize.out=java元数据生成目录
```

3. 创建文档生成类,按照下面方法默认生成[docsify](https://docsify.js.org/)主题结构的文档

目前支持两种类型的文档`javadoc`文档类型，`API`文档类型,具体创建方式
### JavaDoc文档类型创建方式
```java
import com.ejdoc.doc.generate.out.DocGenerate;
import com.ejdoc.doc.generate.out.factory.DocGenerateFactory;

public class JavaDocGenerate {

   public static void main(String[] args) {
      DocGenerate javaDocGenerate = DocGenerateFactory.createDefaultJavaDocGenerate();

      javaDocGenerate.printDoc();
   }
}
```
### API文档类型创建方式
```java
import com.ejdoc.doc.generate.out.DocGenerate;
import com.ejdoc.doc.generate.out.factory.DocGenerateFactory;

public class JavaDocGenerate {

   public static void main(String[] args) {
      DocGenerate apiDocGenerate = DocGenerateFactory.createDefaultApiDocGenerate();

      apiDocGenerate.printDoc();
   }
}
```


4. 查看上面配置的`文档生成目录`是否已经生成了文档，可以点击生成的文件查看，若需要在浏览器中查看效果,可以直接部署在web服务器中，如Nginx、Tomcat等。或者使用`docsify`命令运行查看 `docsify`按照方法：需要安装`node`。
   安装完成后，执行 `npm i docsify-cli -g`,安装`docsify`脚手架，安装完成后进入`文档生成目录/markdown`目录下 ,执行`docsify serve .`
   按照输出提示，在浏览器输入网址查看生成的doc文档吧。
5. 实际效果如图

## 目前支持解析javadoc项目的类型
* Maven:Maven项目类型
* Gradle:Gradle编译类型
* Source:源代码形式，非maven和gradle

## 依赖信息
下面列出使用的主要依赖信息
* 基于JDK1.8开发
* ejdoc使用javaParser分析java语法树，生成java代码json样式虚拟语法树。
* 开发过程中大量的使用了hutool工具包，大幅度提高了编码效率。
* 生成doc模板使用了beetl，使用简单生成快速。
* javadoc默认主题使用的是docsify前端文档生成模块。
