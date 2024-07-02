# 配置说明

## 默认配置说明


### 文档配置说明
读取项目classpath根目录下的javaDocOutConfig.properties文件,下面元数据的配置信息可以放在文档配置信息里，不用再新建metaDir.properties配置文件
```properties
doc.out.root.dir=文档输出根目录，默认项目根目录下的/docmd
apiIndexHref=首页地址路径，默认/
apiGitRepoAddress=文档代码库地址，默认：https://github.com/wiliam2015/ejdoc.git
```

### 元数据配置说明
读取项目classpath根目录下的metaDir.properties文件
```properties
project.root.dir=项目根目录，(默认当前项目的根路径)
project.compile.type=项目编译类型，Maven和Gradle，默认值(Maven)
project.source.dir=项目下的源码路径，默认值(/src/main/java)
project.meta.seralize.out=项目元数据(json)输出路径,默认当前项目的根路径下的docmeta目录
compile.include.private=false(默认值)
custom.sourcefile.dir=自定义源码目录
custom.sourcejar.path=自定义源码路径
```

### 项目配置说明
使用的是yaml文件，放在项目根目录下的`projectMeta.yml`
```yaml
name: "当前项目的名字"
title: "当前项目的名字"
contract: "当前项目的联系人"
description: "当前项目的描述信息"
version: "当前版本号，默认读取pom.xml里的version"
host: "当前项目的访问地址"
license:
  - name: "当前项目的协议名称"
  - url: "当前项目的协议地址"

```