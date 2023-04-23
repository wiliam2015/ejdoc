# ejdoc
## 简介
ejdoc提供基于现有的javadoc无侵入简单生成api文档，目前支持一键生成javadoc。后续功能还在迭代开发中。

原生的javadoc样式一直没什么改变，现在前端的发展这么迅速，所以有了利用现在的前端技术重新生成javadoc的想法，ejdoc就是此想法的实现。

ejdoc充分利用前端的技术，将样式与数据抽离，按照自己想要的样式生成javadoc，充分利用现有的前端技术发展的成果。

## ejdoc名称的由来
ejdoc简单快速生成doc文档，不只是javadoc，让开发更专注，写代码同时就将文档写好了，提高开发效率。

## 包含组件


| 模块               | 介绍                                                     |
|------------------|--------------------------------------------------------|
| metaInfoSeralize | java元数据生成模块，使用json文件，对java文件的基本结构进行描述                  |
| jdocGenerate     | 使用metaInfoSeralize模块生成java代码json样式虚拟语法树，生成各种doc文档 |


## 文档

[中文文档](https://www.ejdoc.com/docs/)

## 安装

### Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
   <groupId>com.ejdoc</groupId>
   <artifactId>jdocGenerate</artifactId>
   <version>1.0.0</version>
</dependency>
```

### Gradle
```
implementation 'com.ejdoc:jdocGenerate:1.0.0'
```

### 下载jar

点击以下链接，下载`jdocGenerate-X.X.X.jar`即可：

- [Maven中央库](https://repo1.maven.org/maven2/cn/hutool/hutool-all/5.8.9/)

## 使用
1. 在项目跟目录下创建项目描述文件`projectMeta.yml`

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

2. 在项目根目录下创建`javaDocOutConfig.properties`文件

```properties
doc.out.root.dir=文档生成目录
project.root.dir=项目根目录
project.meta.seralize.out=java元数据生成目录
```

3. 创建文档生成类,按照下面方法默认生成[docsify](https://docsify.js.org/)结构的javadoc文档

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
4. 查看上面配置的`文档生成目录`是否已经生成了文档，可以点击生成的文件查看，若需要在浏览器中查看效果，需要安装`node`。
   安装完成后，执行 `npm i docsify-cli -g`,安装`docsify`脚手架，安装完成后进入`文档生成目录/markdown`目录下 ,执行`docsify serve`
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
