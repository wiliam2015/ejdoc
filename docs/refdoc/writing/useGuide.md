# 使用说明
快速开始只是简单的介绍如何快速使用ejdoc，但是要想写出清晰易懂比较全面的文档还是需要熟悉javadoc的注解使用规范的。
ejdoc使用的是javadoc自带的注解，并对其进行了简单的扩展，方便易用。


目前

支持的文档类型：javadoc文档和API文档

API文档支持的类型：RPC文档，HTTP文档还在开发中。

支持的主题：docsify和vitepress

## javadoc标签使用说明


Java支持3种注释，分别是单行注释、多行注释和文档注释。文档注释以/**开头，并以*/结束，javadoc标签需要放在文档注释中，放在类、接口、成员变量、方法之前。
Javadoc只处理这些地方的文档注释，而忽略其它地方的文档注释。javadoc标签有两种，`块标签`和`内联标签`，ejdoc对API文档新增了JSR303标签支持和MOCK标签支持

* 块标签：@tag 格式的标签（不被{ }包围的标签）为块标签，只能在主要描述（类注释中对该类的详细说明为主要描述）后面的标签部分（如果块标签放在主要描述的前面，则生成 API 帮助文档时会检测不到主要描述）。
* 内联标签：{@tag} 格式的标签（由{ }包围的标签）为内联标签，可以放在主要描述中的任何位置或块标签的注释中。

javadoc的语法格式说明
```java
 /**
 * 主要描述，可以写内联标签{@tag 标签内容}
 *
 * @tag 标签描述,可以写内联标签{@tag 标签内容}
 */
```

### javadoc常用块标签说明

| tag                 | 描述                                                         | 示例            | since |
|---------------------| ------------------------------------------------------------ | --------------- | ----- |
| [`@param`](#param)  | 说明一个方法的参数，一般用于方法注释                         | @param 参数注释 | -     |
| [`@return`](#return) | 说明返回值类型，一般用于方法注释，不能出现再构造方法中       | @return explanation | -     |
| [`@throws`](#throws) | 和 @exception 标签一样.                                      | 同@exception | -     |
| `@exception`        | 可能抛出异常的说明，一般用于方法注释                         | @exception exception-name explanation | -     |
| `@see`              | 指定一个到另一个主题的链接                                   | @see anchor | -     |
| `@since`            | 说明从哪个版本起开始有了这个函数                             | @since release | -     |
| `@author`           | 标识一个类的作者，一般用于类注释                             | @author description | -     |
| `@version`          | 指定类的版本，一般用于类注释                                 | @version info | -     |
| `@serial`             | 说明一个序列化属性                                           | @serial description | -     |

### javadoc常用内联标签说明

| tag                 | 描述                                                         | 示例            | since |
|---------------------| ------------------------------------------------------------ | --------------- | ----- |
| {@link} | 插入一个到另一个主题的链接            | {@link name text} | -     |
| {@linkplain} | 插入一个到另一个主题的链接，但是该链接显示纯文本字体 | Inserts an in-line link to another topic. | -     |
| {@inheritDoc} | 从直接父类继承的注释                            | Inherits a comment from the immediate surperclass. | -     |
| {@docRoot} | 指明当前文档根目录的路径             | Directory Path | -     |
| {@value} | 显示常量的值，该常量必须是 static 属性。           | Displays the value of a constant, which must be a static field. | -     |

### JSR303校验标签
JSR303校验标签只对API文档类型起作用，ejdoc对JSR303标签设计的也是内联标签。

| tag                 | 描述                                                         | 示例            | since |
|---------------------| ------------------------------------------------------------ | --------------- | ----- |
| {@Null 提示内容(非必须)} | 被注释的元素必须为null | {@Null }，{@Null 可以为空} | -     |
| {@NotNull 提示内容(非必须)} | 被注释的元素必须不为null,默认输出 必填:是 | {@NotNull }，{@NotNull 不能为空} | -     |
| {@Min(value) } | 被注释的元素必须为数字,>=给定的值 | {@Min(18) } | -     |
| {@Max(value)  } | 被注释的元素必须为数字,<=给定的值 | {@Max(100) } | -     |
| {@Size(min,max) } | 被注释的元素必须在指定的范围内 | {@Size(18,100) } | -     |
| {@AssertTrue 提示内容(非必须) } | 被注释的元素必须为true | {@AssertTrue },{@AssertTrue 表达式必须为true } |  |
| {@AssertFalse 提示内容(非必须) } | 被注释的元素必须为false | {@AssertFalse },{@AssertFalse 表达式必须为false } |  |
| {@Past 提示内容(非必须)} | 被注释的元素必须为一个过去的日期 | {@Past },{@AssertFalse 字段时间必须为之前时间 } |  |
| {@Future}提示内容(非必须) | 被注释的元素必须为一个将来的日期 | {@Future },{@AssertFalse 字段时间必须为之后时间 } |  |
| {@Pattern(value)} | 被注释的元素必须符合指定的正则表达式 | {@Pattern(/^1[3-9]\d{9}$/) } |  |


### Mock标签说明
@Mock标签也是内联标签，支持mock数据和指定格式mock数据，功能非常强大，在生成API样例时帮助生成想要的数据。
语法格式
1. 直接写内联标签{@mock}
2. 内联标签中增加内容{@mock 提示内容}
3. 内联标签中使用指定数据mock{@mock (mock类型 mock参数)}
4. 内联标签中增加内容和指定数据mock{@mock 提示内容(mock类型 mock参数)}
具体详见mock标签详细说明

   

### javadoc常用块标签详细说明

#### 1.@param
语法格式
```java
/**
 * @param 参数名字 参数注释
 */
```
@param：指定该注释是关于方法参数的说明。

参数名字：参数名，即要描述的方法参数的名称。

参数注释：对参数的描述，可以是任意文本，用于解释参数的用途、取值范围或其他相关信息。参数注释可以写内联标签，包含ejdoc的自定义标签。

`@param 参数名字 参数注释`，说明一个方法的参数，一般用于方法注释。参数注释可以写内联标签，包含ejdoc的自定义标签。

#### 2.@return
语法格式
```java
/**
 * @return  参数注释
 */
```
@return：说明返回值类型，一般用于方法注释，不能出现再构造方法中。

参数注释：对参数的描述，可以是任意文本，用于解释参数的用途、取值范围或其他相关信息。参数注释可以写内联标签，包含ejdoc的自定义标签。


#### 3.@throws
同@param
#### 4.@exception
语法格式和 @throws 标签一样。描述同@param

#### 5.@see
参考内联标签{@link}使用，唯一区别是@see标签不能放在注释内部，必须放在文档一行的开头。
#### 其余标签
@since，@author，@version使用同@return标签

### javadoc常用内联标签详细说明

#### {@link} 标签说明
@link标签是整个javadoc语法规则最复杂的标签，是 Javadoc 中用于创建超链接的标签之一,它可以用于将文档中的某个位置链接到其他类、方法、字段等的位置; ejdoc也对link标签进行了扩展;

@link 的语法如下： `{@link package.class#member label}`

其中，package.class#member 是要链接的类、方法或字段的完全限定名称，label 是链接的文本标签。

@link 可以链接到以下内容：

1. 类：可以链接到一个类，例如：{@link java.util.ArrayList}
2. 方法：可以链接到一个方法，例如：{@link java.util.List#add(Object)}
3. 字段：可以链接到一个字段，例如：{@link java.util.List#size}
4. 包：可以链接到一个包，例如：{@link java.util}
5. 超类：可以链接到一个类的超类，例如：{@link java.util.ArrayList#add(Object) super.add()}
6. 实现的接口：可以链接到一个类实现的接口，例如：{@link java.util.List#add(Object) List.add()}
7. 异常：可以链接到一个异常，例如：{@link java.io.IOException}

@link 还可以用于链接到当前类或当前方法中的成员。例如，要链接到当前类中的一个字段，可以使用以下语法：

`{@link #fieldName}`

这将创建一个超链接，链接到当前类中名为 fieldName 的字段。
在使用 @link 时，可以提供一个 label 参数，用于指定链接的文本标签。例如：
`{@link java.util.List#add(Object) List.add()}`
这将创建一个超链接，链接到 java.util.List 接口的 add 方法，并使用 "List.add()" 作为链接的文本标签。

ejdoc对@link标签的扩展
1. 增加了http链接支持，可以链接到http地址，例如：{@link http://www.baidu.com 百度}
2. 增加了对markdown文件的支持，可以链接到markdown文件，从classpath下绝对路径开始,例如：{@link /submitOrder.md 提交订单接口说明}

### javadoc常用内联标签详细说明


### Mock标签详细说明
@mock标签语法如下@mock标签可以带参数和不带参数，格式是`{@mock}`和`{@mock text(mockType mockTypeParam)}`

不带参数的@mock会根据实际的参数类型，自动生成Mock值，带参数的会根据参数生成Mock值
其中text：可以是任意内容，mockType是mock生成指定类型的数据，mockTypeParam是mockType对应的参数信息

mockType支持的类型

| 类型              | 描述                                       | 参数 |
| ----------------- | ------------------------------------------ | ---- |
| date              | 随机日期                                   |      |
| datetime          | 随机时间                                   |      |
| timestamp         | 随机时间戳                                 |      |
| timezone          | 随机时区                                   |      |
| password          | 随机密码长度8位                            |      |
| address           | 生成1个随机中国大陆详细地址                |      |
| province          | 随机获取省份                               |      |
| city              | 随机获取城市(省份+城市，以逗号为分隔符)    |      |
| zipCode           | 随机获取邮编                               |      |
| plateNumber       | 随机中国大陆车牌号                         |      |
| nickName          | 生成1个随机英文网络昵称，最大长度为8个字符 |      |
| chineseNickName   | 生成1个随机汉字网络昵称，最大长度为8个汉字 |      |
| chineseName       | 生成1个中文名字                            |      |
| englishName       | 生成1个英文名字                            |      |
| mobileModel       | 随机手机型号                               |      |
| number            | 随机数字，支持随机位数                     |      |
| chinese           | 随机汉字，支持随机位数                     |      |
| chineseIdiom      | 随机成语，支持随机位数                     |      |
| orderId           | 随机订单号11位数字                         |      |
| chineseMobile     | 中国大陆手机号                             |      |
| email             | 随机生成邮箱                               |      |
| domain            | 随机生成域名                               |      |
| ipv4              | 随机生成IPv4地址                           |      |
| ipv6              | 随机生成IPv6地址                           |      |
| url               | 随机生成url                                |      |
| appName           | 随机生成app名称                            |      |
| companyName       | 随机公司名称                               |      |
| companyDepartment | 随机公司部门名称                           |      |
| chineseSentence   | 随机中文短句                               |      |
| englishText       | 随机英文文本                               |      |
| economicCategory  | 随机行业分类                               |      |
| socialCreditCode  | 随机社会信用代码                           |      |

**样例说明**  
1. 直接写内联标签{@mock}
```java
 /**
  * 取消订单
  * @param orderId 订单号  {@mock}
  * @return 是否取消成功
  */
 RpcResult<String> cancelOrder(String orderId);
```
生成的`cancelOrder`方法入参使用样例
 ```json
[
    {
        "orderId": "abcde"
    }
]
```

2. 内联标签中增加内容{@mock 提示内容}
```java
 /**
  * 取消订单
  * @param orderId 订单号  {@mock 订单号}
  * @return 是否取消成功
  */
 RpcResult<String> cancelOrder(String orderId);
```
生成的`cancelOrder`方法入参直接使用"订单号"作为orderId样例值，使用样例
 ```json
[
    {
        "orderId": "订单号"
    }
]
```
3. 内联标签中使用指定数据mock{@mock (mock类型 mock参数)}
```java
 /**
  * 取消订单
  * @param orderId 订单号  {@mock (number)}
  * @return 是否取消成功
  */
 RpcResult<String> cancelOrder(String orderId);
```
生成的`cancelOrder`方法入参使用的是模拟数字类型生成8位长度的数字，使用样例
 ```json
[
    {
        "orderId": 22200101
    }
]
```
4. 内联标签中增加内容和指定数据mock{@mock 提示内容(mock类型 mock参数)}
```java
 /**
  * 取消订单
  * @param orderId 订单号  {@mock (number 6)}
  * @return 是否取消成功
  */
 RpcResult<String> cancelOrder(String orderId);
```
生成的`cancelOrder`方法入参使用的是模拟数字类型生成6位长度的数字，使用样例
 ```json
[
    {
        "orderId": 222001
    }
]
```