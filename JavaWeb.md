# 1.基本概念

## 1.1 前言

静态Web：

- 提供给所有人看数据不会发生变化！
- HTML，CSS

动态Web：

- 有数据交互，登录账号密码，网站访问人数等
- 技术栈：Servlet/JSP，ASP，PHP

在Java中，动态web资源开发的技术统称为JavaWeb；

## 1.2 Web 应用程序

Web 应用程序：可以提供浏览器访问的程序；

这个统一的web资源会被放在同一个文件夹下，Web 应用程序 —> Tomcat：服务器
一个 Web 应用由多部分组成（静态Web、动态Web）

- HTML，CSS，JavaScript
- JSP，Servlet
- Java 程序
- jar 包
- 配置文件 （Properties）

Web 应用程序编写完毕后，若想提供给外界访问：需要一个服务器来统一管理；

## 1.3动态 Web 的访问过程

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201029214103518.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

浏览器发送 HTTP 请求，服务器 Tomcat 接收请求，Servlet 容器从磁盘加载 Servlet 程序处理请求 request ，处理结束返回 response。

# 2. Web 服务器

## 2.1 技术讲解

PHP：

- 作为开发速度很快，功能很强大，跨平台
- 无法承载大访问量的情况

JSP/Servlet：

- 基于 Java 语言

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201029214824204.png#pic_center)
Tomcat是Apache 软件基金会（Apache Software Foundation）的Jakarta 项目中的一个核心项目。

Tomcat 技术先进、性能稳定，而且**免费**。

# 3.Tomcat

## 3.1 安装 Tomcat

Tomcat官网：http://tomcat.apache.org/

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201029215004229.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201029215256908.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

# 3.2 Tomcat 启动和配置

文件夹：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020102921535868.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)启动和关闭 Tomcat：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020102921543768.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
访问测试：http://localhost:8080/

可能遇到的问题：

```
1.Java 环境变量没有配置导致闪退
2.乱码问题：可在配置文件中配置
12
```

## 3.3 配置

Servlet 核心配置文件目录如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201029215639475.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
可以配置启动的端口号

- tomcat的默认端口号为：8080
- mysql：3306
- http：80
- https：443

```xml
<Connector port="8081" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
123
```

可配置主机名称

- 默认的主机名为：localhost->127.0.0.1
- 默认网站应用存放的位置为：webapps

```xml
  <Host name="www.qinjiang.com"  appBase="webapps"
        unpackWARs="true" autoDeploy="true">
12
```

**面试题：**

1. 在浏览器输入一个域名，回车；

2. 本机查看 C:\Windows\System32\drivers\etc\hosts 配置文件是否有相应域名的映射。

   case1: 若有，则直接映射到对应的 IP 地址，进行访问。

   case2: 若无，则去 DNS 服务器上查找对应的 IP ，找到就返回相应的 IP，找不到就不返回。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201025183516937.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201025184035890.png#pic_center)

## 3.4 发布一个 Web 网站

网站程序的结构：

```java
--webapps ：Tomcat服务器的web目录
	-ROOT
	-kuangstudy ：网站的目录名
		- WEB-INF
			-classes : java程序
			-lib：web应用所依赖的jar包
			-web.xml ：网站配置文件
		- index.html 默认的首页
		- static 
            -css
            	-style.css
            -js
            -img
         -.....
1234567891011121314
```

# 4.HTTP

## 4.2 什么是 HTTP?

HTTP(hypertext transport protocol) 超文本传输协议。

- 文本 ：HTML，字符串…
- 超文本：图片，音乐，视频，定位，地图…
- 80 端口

HTTPS（Hyper Text Transfer Protocol over SecureSocket Layer）：是以安全为目标的 HTTP 通道，在 HTTP 的基础上通过传输加密和身份认证保证了传输过程的安全性。HTTPS 在HTTP 的基础下加入SSL 层，HTTPS 的安全基础是 SSL。

- 443 端口

## 4.2 两个时代

**HTTP 1.0:**

- HTTP/1.0：客户端与 Web 服务器连接后，只能获得一个Wen 资源，然后就断开连接，加入某个页面有多个图片资源需要加载，那么需要连接多次，影响服务器和客户端的性能。

**HTTP 2.0:**

- HTTP/1.1：客户端可以与web服务器连接后，可以获得多个web资源。

## 4.3 HTTP 请求

客户端 -> 发送请求（Request）->服务器

百度：

```java
Request URL:https://www.baidu.com/   请求地址
Request Method:GET    get方法/post方法
Status Code:200 OK    状态码：200
Remote（远程） Address:14.215.177.39:443
1234
Accept:text/html  
Accept-Encoding:gzip, deflate, br
Accept-Language:zh-CN,zh;q=0.9    语言
Cache-Control:max-age=0
Connection:keep-alive
12345
```

1、请求行

请求行中的请求方式：GET
请求方式：**Get，Post**，HEAD,DELETE,PUT,TRACT…

- get：请求能够携带的参数比较少，大小有限制，会在浏览器的URL地址栏显示数据内容，不安全，但高效
- post：请求能够携带的参数没有限制，大小没有限制，不会在浏览器的URL地址栏显示数据内容，安全，但不高效。

2、消息头

```java
Accept：告诉浏览器，它所支持的数据类型
Accept-Encoding：支持哪种编码格式  GBK   UTF-8   GB2312  ISO8859-1
Accept-Language：告诉浏览器，它的语言环境
Cache-Control：缓存控制
Connection：告诉浏览器，请求完成是断开还是保持连接
HOST：主机..../.
123456
```

## 4.4 HTTP 响应

- 服务器 -> 响应（response） ->客户端

百度：

```java
Cache-Control:private    缓存控制
Connection:Keep-Alive    连接
Content-Encoding:gzip    编码
Content-Type:text/html   类型
1234
```

**1、响应体**

```java
Accept：告诉浏览器，它所支持的数据类型
Accept-Encoding：支持哪种编码格式  GBK   UTF-8   GB2312  ISO8859-1
Accept-Language：告诉浏览器，它的语言环境
Cache-Control：缓存控制
Connection：告诉浏览器，请求完成是断开还是保持连接
HOST：主机..../.
Refresh：告诉客户端，多久刷新一次；
Location：让网页重新定位；
12345678
```

**2、响应状态码**

200：请求响应成功 200

3xx：请求重定向

- 重定向：你重新到我给你新位置去；

4xx：找不到资源 404

5xx：服务器代码错误 500 502:网关错误

**常见面试题：**

当你的浏览器中地址栏输入地址并回车的一瞬间到页面能够展示回来，经历了什么？

# 5. Maven

- 在 JavaWeb 开发中，需要使用大量的 jar 包，我们手动去导入；
- 如何能够让一个东西自动帮我导入和配置这个jar包。

由此，Maven诞生了！

## 5.1 Maven 项目架构管理工具

Maven 的核心思想：**约定大于配置**

- 有约束，不要去违反。
  Maven 会规定好你该如何去编写我们的 Java 代码，必须要按照这个规范来；

## 5.2 下载安装 Maven

[点击我查看安装教程 https://editor.csdn.net/md/?articleId=107500020](https://editor.csdn.net/md/?articleId=107500020)

## 5.3 在 IDEA 中使用 Maven

1. 启动 IDEA
2. 创建一个 MavenWeb 项目

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195424154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195556605.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195633838.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195731149.png#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195748148.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
\3. 等待项目初始化完毕

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195850935.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030195912364.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
\4. 观察 maven 仓库中多了什么东西？
\5. IDEA 中的 Maven 设置

注意：IDEA 项目创建成功后，看一眼 Maven 的配置

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200540879.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200557328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
\6. 到这里，Maven 在 IDEA 中的配置和使用就 OK 了！

## 5.4 创建一个普通的 Maven 项目

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200738636.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200806377.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

这个只有在Web应用下才会有！

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200833737.png#pic_center)

## 5.5 标记文件夹功能

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200931259.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)![在这里插入图片描述](https://img-blog.csdnimg.cn/2020103020094687.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030201001211.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030201021562.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

## 5.6 在 IDEA 中配置 Tomcat

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030201241524.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![请添加图片描述](https://img-blog.csdnimg.cn/20201030201259888.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![请添加图片描述](https://img-blog.csdnimg.cn/20201030201259893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
解决警告问题

必须要的配置：**为什么会有这个问题：我们访问一个网站，需要指定一个文件夹名字；**

![请添加图片描述](https://img-blog.csdnimg.cn/20201030201543514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![请添加图片描述](https://img-blog.csdnimg.cn/20201030201543519.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![请添加图片描述](https://img-blog.csdnimg.cn/20201030201543568.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

## 5.7 pom 文件

pom.xml 是Maven的核心配置文件

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030201714779.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!--Maven版本和头文件-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!--这里就是我们刚才配置的GAV-->
  <groupId>com.kuang</groupId>
  <artifactId>javaweb-01-maven</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!--Package：项目的打包方式
  jar：java应用
  war：JavaWeb应用
  -->
  <packaging>war</packaging>


  <!--配置-->
  <properties>
    <!--项目的默认构建编码-->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--编码版本-->
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!--项目依赖-->
  <dependencies>
    <!--具体依赖的jar包配置文件-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>
  </dependencies>

  <!--项目构建用的东西-->
  <build>
    <finalName>javaweb-01-maven</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849505152535455565758596061626364656667686970717273747576
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030201813771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

Maven 由于他的约定大于配置，我们之后可以能遇到我们写的配置文件，无法被导出或者生效的问题，解决方案：

```xml
<!--在build中配置resources，来防止我们资源导出失败的问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
123456789101112131415161718192021
```

## 5.8 IDEA 查看依赖树

![请添加图片描述](https://img-blog.csdnimg.cn/20201030202127330.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![请添加图片描述](https://img-blog.csdnimg.cn/20201030202127343.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

## 5.9 解决遇到的问题

1. Maven 3.6.2
   解决方法：降级为 3.6.1

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030202248934.png#pic_center)

1. Tomcat 闪退
2. IDEA中每次都要重复配置Maven
   在IDEA中的全局默认配置中去配置

![请添加图片描述](https://img-blog.csdnimg.cn/20201030202443618.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
![请添加图片描述](https://img-blog.csdnimg.cn/20201030202443626.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

1. Maven 默认 Web 项目中的 web.xml 版本问题

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030203819426.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

1. 替换为 webapp4.0 版本和 Tomcat 一致

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">



</web-app>
1234567891011
```

# 6. Servlet 简介

Servlet 就是 Sun 公司开发动态 Web 的一门技术

Sun 在这些 API （Application Programming Interface，应用程序接口）中提供一个接口叫做：Servlet，如果你想开发一个Servlet程序，只需要完成两个小步骤：

- 编写一个类，实现Servlet接口。
- 把开发好的Java类部署到web服务器中。

**把实现了 Servlet 接口的 Java 程序叫做，Servlet**

## 6.2 HelloServlet

Serlvet 接口 Sun 公司有两个默认的实现类：HttpServlet，GenericServlet

1. 构建一个普通的Maven项目，删掉里面的src目录，以后我们的学习就在这个项目里面建立Moudel；这个空的工程就是Maven主工程（建一个WebApp Maven项目，勾选模板）；

2. 关于Maven父子工程的理解：

   父项目中会有：

   ```xml
       <modules>
           <module>servlet-01</module>
       </modules>
   123
   ```

   子项目中会有：

   ```xml
       <parent>
           <artifactId>javaweb-02-servlet</artifactId>
           <groupId>com.kuang</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
   12345
   ```

   父项目中的 Java 子项目可以直接使用

   ```
    son extends father1
   ```

   1. Maven 环境优化
      - 修改 web.xml 为最新的
      - 将 maven 的结构搭建完整

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030212931667.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

   4.编写一个Servlet程序

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030203415617.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
S1：编写一个普通类
S2：实现 Servlet 接口，这里我们直接继承 HttpServlet

```java
public class HelloServlet extends HttpServlet {
    
    //由于get或者post只是请求实现的不同的方式，可以相互调用，业务逻辑都一样；
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter(); //响应流
        writer.print("Hello,Serlvet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
123456789101112131415
```

1. 编写 Servlet 的映射

   为什么需要映射：我们写的是 Java 程序，但是要通过浏览器访问，而浏览器需要连接 Web 服务器，所以我们需要再 Web 服务中注册我们写的 Servlet，还需给他一个浏览器能够访问的路径；

```xml
    <!--注册Servlet-->    <servlet>        <servlet-name>hello</servlet-name>        <servlet-class>com.kuang.servlet.HelloServlet</servlet-class>    </servlet>    <!--Servlet的请求路径-->    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello</url-pattern>    </servlet-mapping>1234567891011
```

1. 配置 Tomcat
   点击编辑，+ 号，选择本地的 Tomcat

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030213329557.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

1. 启动测试！

## 6.3 Servlet 原理

Servlet 是由 Web 服务器调用，Web 服务器在收到浏览器请求之后，会：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030213749187.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

## 6.4 Mapping 问题

1. 一个 Servlet 可以指定一个映射路径

```xml
    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello</url-pattern>    </servlet-mapping>1234
```

1. 一个 Servlet 可以指定一个映射路径

```xml
    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello</url-pattern>    </servlet-mapping>    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello2</url-pattern>    </servlet-mapping>    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello3</url-pattern>    </servlet-mapping>    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello4</url-pattern>    </servlet-mapping>    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello5</url-pattern>    </servlet-mapping>1234567891011121314151617181920
```

1. 一个Servlet可以指定通用映射路径

```xml
    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/hello/*</url-pattern>    </servlet-mapping>1234
```

1. 默认请求路径

```xml
    <!--默认请求路径-->    <servlet-mapping>        <servlet-name>hello</servlet-name>        <url-pattern>/*</url-pattern>    </servlet-mapping>12345
```

1. 指定一些后缀或者前缀等等….

```xml
<!--可以自定义后缀实现请求映射    注意点，*前面不能加项目映射的路径    hello/sajdlkajda.qinjiang    --><servlet-mapping>    <servlet-name>hello</servlet-name>    <url-pattern>*.qinjiang</url-pattern></servlet-mapping>12345678
```

1. 优先级问题
   指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求；

```xml
<!--404--><servlet>    <servlet-name>error</servlet-name>    <servlet-class>com.kuang.servlet.ErrorServlet</servlet-class></servlet><servlet-mapping>    <servlet-name>error</servlet-name>    <url-pattern>/*</url-pattern></servlet-mapping>123456789
```

## 6.5 ServletContext

Web 容器在启动的时候，它会为每个 Web 程序都创建一个对应的 ServletContext 对象，它代表了当前的 Web 应用;

### 6.5.1 共享数据

在一个 servlet 中保存的数据，可以在另一个 servlet 中拿到；

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //this.getInitParameter()   初始化参数
        //this.getServletConfig()   Servlet配置
        //this.getServletContext()  Servlet上下文
        ServletContext context = this.getServletContext();

        String username = "秦疆"; //数据
        context.setAttribute("username",username); //将一个数据保存在了ServletContext中，名字为：username 。值 username

    }

}
123456789101112131415
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String username = (String) context.getAttribute("username");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print("名字"+username);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
1234567891011121314151617
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.kuang.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>


    <servlet>
        <servlet-name>getc</servlet-name>
        <servlet-class>com.kuang.servlet.GetServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>getc</servlet-name>
        <url-pattern>/getc</url-pattern>
    </servlet-mapping>
123456789101112131415161718
```

测试访问结果；

### 6.5.2 获取初始化参数

```xml
    <!--配置一些web应用初始化参数-->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>
12345
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    String url = context.getInitParameter("url");
    resp.getWriter().print(url);
}
12345
```

### 6.5.3 请求转发

```java
@Overrideprotected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    ServletContext context = this.getServletContext();    System.out.println("进入了ServletDemo04");    //RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp"); //转发的请求路径    //requestDispatcher.forward(req,resp); //调用forward实现请求转发；    context.getRequestDispatcher("/gp").forward(req,resp);}12345678
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102191923294.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

### 6.5.4 读取资源文件

Properties

- 在 java 目录下新建 properties
- 在 resources 目录下新建 properties

发现：都被打包到了同一个路径下：classes，我们俗称这个路径为classpath:

思路：需要一个文件流；

```
uername=sjmppassword=12345612public class ServletDemo05 extends HttpServlet {    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/kuang/servlet/aa.properties");        Properties prop = new Properties();        prop.load(is);        String user = prop.getProperty("username");        String pwd = prop.getProperty("password");        resp.getWriter().print(user+":"+pwd);    }    @Override    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        doGet(req, resp);    }}1234567891011121314151617181920
```

## 6.6 HttpServletResponse

Web 服务器接收到客户端的 http 请求，针对这个请求，分别创建一个代表请求的 HttpServletRequest 对象，代表响应的一个 HttpServletResponse；

- 如果想要获取客户端请求过来的参数：找 HttpServletRequest
- 如果要给客户端响应一些信息：找 HttpServletResponse

### 6.6.1 简单分类

负责向浏览器发送数据的方法

```java
ServletOutputStream getOutputStream() throws IOException;PrintWriter getWriter() throws IOException;12
```

负责向浏览器发送响应头的方法

```java
    void setCharacterEncoding(String var1);    void setContentLength(int var1);    void setContentLengthLong(long var1);    void setContentType(String var1);    void setDateHeader(String var1, long var2);    void addDateHeader(String var1, long var2);    void setHeader(String var1, String var2);    void addHeader(String var1, String var2);    void setIntHeader(String var1, int var2);    void addIntHeader(String var1, int var2);12345678910111213141516171819
```

响应的状态码

```java
 int SC_CONTINUE = 100;    int SC_SWITCHING_PROTOCOLS = 101;    int SC_OK = 200;    int SC_CREATED = 201;    int SC_ACCEPTED = 202;    int SC_NON_AUTHORITATIVE_INFORMATION = 203;    int SC_NO_CONTENT = 204;    int SC_RESET_CONTENT = 205;    int SC_PARTIAL_CONTENT = 206;    int SC_MULTIPLE_CHOICES = 300;    int SC_MOVED_PERMANENTLY = 301;    int SC_MOVED_TEMPORARILY = 302;    int SC_FOUND = 302;    int SC_SEE_OTHER = 303;    int SC_NOT_MODIFIED = 304;    int SC_USE_PROXY = 305;    int SC_TEMPORARY_REDIRECT = 307;    int SC_BAD_REQUEST = 400;    int SC_UNAUTHORIZED = 401;    int SC_PAYMENT_REQUIRED = 402;    int SC_FORBIDDEN = 403;    int SC_NOT_FOUND = 404;    int SC_METHOD_NOT_ALLOWED = 405;    int SC_NOT_ACCEPTABLE = 406;    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;    int SC_REQUEST_TIMEOUT = 408;    int SC_CONFLICT = 409;    int SC_GONE = 410;    int SC_LENGTH_REQUIRED = 411;    int SC_PRECONDITION_FAILED = 412;    int SC_REQUEST_ENTITY_TOO_LARGE = 413;    int SC_REQUEST_URI_TOO_LONG = 414;    int SC_UNSUPPORTED_MEDIA_TYPE = 415;    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;    int SC_EXPECTATION_FAILED = 417;    int SC_INTERNAL_SERVER_ERROR = 500;    int SC_NOT_IMPLEMENTED = 501;    int SC_BAD_GATEWAY = 502;    int SC_SERVICE_UNAVAILABLE = 503;    int SC_GATEWAY_TIMEOUT = 504;    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;1234567891011121314151617181920212223242526272829303132333435363738394041
```

### 6.6.2 下载文件

给浏览器输出消息

下载文件

1.要获取下载文件的路径
2.下载的文件名是什么？
3.设置浏览器使其支持下载的内容
4.获取下载文件的输入流
5.创建缓冲区
6.获取 OutputStream 对象
7.将 FileOutputStream 写入到 buffer缓冲区
8.使用 OutputStream 将缓冲区中的数据输出到客户端

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 1. 要获取下载文件的路径
    String realPath = "F:\\班级管理\\西开【19525】\\2、代码\\JavaWeb\\javaweb-02-servlet\\response\\target\\classes\\秦疆.png";
    System.out.println("下载文件的路径："+realPath);
    // 2. 下载的文件名是啥？
    String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
    // 3. 设置想办法让浏览器能够支持(Content-Disposition)下载我们需要的东西,中文文件名URLEncoder.encode编码，否则有可能乱码
    resp.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
    // 4. 获取下载文件的输入流
    FileInputStream in = new FileInputStream(realPath);
    // 5. 创建缓冲区
    int len = 0;
    byte[] buffer = new byte[1024];
    // 6. 获取OutputStream对象
    ServletOutputStream out = resp.getOutputStream();
    // 7. 将FileOutputStream流写入到buffer缓冲区,使用OutputStream将缓冲区中的数据输出到客户端！
    while ((len=in.read(buffer))>0){
        out.write(buffer,0,len);
    }

    in.close();
    out.close();
}
123456789101112131415161718192021222324
```

### 6.6.3 实现重定向

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102193947328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
Web 资源 B 收到客户端 A 请求后，通知 A 访问另一个 Web 资源 C ，这个过程叫做重定向

常见场景：

- 用户登录

```java
void sendRedirect(String var1) throws IOException;
1
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    /*
        resp.setHeader("Location","/r/img");
        resp.setStatus(302);
         */
    resp.sendRedirect("/r/img");//重定向
}
123456789
```

面试题：重定向与转发的区别？

相同点：

- 页面都会实现跳转
  不同点：
- 请求转发的时候，URL 不会发生变化
- 重定向时候，URL 地址栏会发生变化；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102194532388.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

### 6.6.4 简单实现登录重定向

```xml
<%--这里提交的路径，需要寻找到项目的路径--%><%--${pageContext.request.contextPath}代表当前的项目--%><form action="${pageContext.request.contextPath}/login" method="get">    用户名：<input type="text" name="username"> <br>    密码：<input type="password" name="password"> <br>    <input type="submit"></form>12345678    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        //处理请求        String username = req.getParameter("username");        String password = req.getParameter("password");        System.out.println(username+":"+password);        //重定向时候一定要注意，路径问题，否则404；        resp.sendRedirect("/r/success.jsp");    }123456789101112  <servlet>    <servlet-name>requset</servlet-name>    <servlet-class>com.kuang.servlet.RequestTest</servlet-class>  </servlet>  <servlet-mapping>    <servlet-name>requset</servlet-name>    <url-pattern>/login</url-pattern>  </servlet-mapping>12345678<%@ page contentType="text/html;charset=UTF-8" language="java" %><html><head>    <title>Title</title></head><body><h1>Success</h1></body></html>1234567891011
```

## 6.7 HttpServletRequest 获取参数，请求转发

HttpServletRequest 代表客户端的请求，用户通过 HTTP 协议访问服务器，HTTP 请求中的所有信息会被封装到 HttpServletRequest ，通过这个HttpServletRequest 的方法，获得客户端的所有信息；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102195801974.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102195846293.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
**获取参数，请求转发**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102200108754.png#pic_center)

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String[] hobbys = req.getParameterValues("hobbys");
    System.out.println("=============================");
    //后台接收中文乱码问题
    System.out.println(username);
    System.out.println(password);
    System.out.println(Arrays.toString(hobbys));
    System.out.println("=============================");


    System.out.println(req.getContextPath());
    //通过请求转发
    //这里的 / 代表当前的web应用
    req.getRequestDispatcher("/success.jsp").forward(req,resp);

}
1234567891011121314151617181920212223
```

# 7 Cookie、Session

## 7.1 会话

**会话**：用户打开一个浏览器，点击了很多超链接，访问多个 Web 资源，关闭浏览器，这个过程可以称之为会话；

**有状态会话**：一个同学来过教室，下次再来教室，我们会知道这个同学，曾经来过，称之为有状态会话；

**你能怎么证明你是学生？**
你 学校
发票 学校开的发票
学校登记 校牌

| 你         | 学校           |
| ---------- | -------------- |
| 发票       | 学校开的发票   |
| 学校的登记 | 学校给你的校牌 |

**一个网站，怎么证明你来过？**
客户端 服务端
服务端给客户端一个 信件，客户端下次访问服务端带上信件就可以了； cookie

客户端 ： 服务端给客户端一个 信件，客户端下次访问服务端带上信件就可以了； cookie
服务端：服务器登记你来过了，下次你来的时候我来匹配你； seesion

## 7.2 保存会话的两种技术

**cookie**

- 客户端技术（响应，请求）

**session**

- 服务器技术，利用这个技术，可以保存用户的会话信息？可将信息或数据放在 session 中

## 7.3 Cookie

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102201302397.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

1. 从请求中拿到 cookie 信息
2. 服务器响应给客户端 cookie

```java
Cookie[] cookies = req.getCookies(); //获得Cookie
cookie.getName(); //获得cookie中的key
cookie.getValue(); //获得cookie中的vlaue
new Cookie("lastLoginTime", System.currentTimeMillis()+""); //新建一个cookie
cookie.setMaxAge(24*60*60); //设置cookie的有效期
resp.addCookie(cookie); //响应给客户端一个cookie
123456
```

**cookie ： 一般会保存在本地的用户目录下 appdata ；**

- 一个 Web 站点可以给浏览器发送多个 Cookie，最多存放 20 个 cookie；
- cookie 大小有限制 4kb；
- 300 个 cookie 浏览器上限

**删除 Cookie；**

- 不设置有效期，关闭浏览器，自动失效；
- 设置有效期为 0；

**编码解码：**

```xml
URLEncoder.encode("秦疆","utf-8")
URLDecoder.decode(cookie.getValue(),"UTF-8")
12
```

## 7.4 Session（重点）

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102202256154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
什么是 Session ？

- 服务器会给每一个用户（浏览器）创建一个 Session 对象；
- 一个 Session 独占一个浏览器，只要浏览器没有关闭，这个 Session 就存在；
- 用户登录之后，整个网站它都可以访问！-> 保存用户的信息；保存购物车的信息…

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102202640957.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
Session 和 cookie 的区别：

- Cookie是把用户的数据写给用户的浏览器，浏览器保存 （可以保存多个）
- Session把用户的数据写到用户独占Session中，服务器端保存 （保存重要的信息，减少服务器资源的浪费）
- Session 对象由服务创建；

使用场景：

- 保存一个登录用户的信息；
- 购物车信息；
- 在整个网站中经常会使用的数据，我们将它保存在 Session 中；

使用 Session：

```java
package com.kuang.servlet;

import com.kuang.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        
        //得到Session
        HttpSession session = req.getSession();
        //给Session中存东西
        session.setAttribute("name",new Person("秦疆",1));
        //获取Session的ID
        String sessionId = session.getId();

        //判断Session是不是新创建
        if (session.isNew()){
            resp.getWriter().write("session创建成功,ID:"+sessionId);
        }else {
            resp.getWriter().write("session以及在服务器中存在了,ID:"+sessionId);
        }

        //Session创建的时候做了什么事情；
//        Cookie cookie = new Cookie("JSESSIONID",sessionId);
//        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

//得到Session
HttpSession session = req.getSession();

Person person = (Person) session.getAttribute("name");

System.out.println(person.toString());

HttpSession session = req.getSession();
session.removeAttribute("name");
//手动注销Session
session.invalidate();
123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354
```

**会话自动过期：web.xml 配置**

```xml
<!--设置Session默认的失效时间-->
<session-config>
    <!--15分钟后Session自动失效，以分钟为单位-->
    <session-timeout>15</session-timeout>
</session-config>
12345
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110220352212.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

# 8 JSP

## 8.1 什么 JSP

Java Server Pages ： Java服务器端页面，也和Servlet一样，用于动态Web技术！

最大的特点：

写JSP就像在写HTML

区别：

- HTML只给用户提供静态的数据
- JSP 页面中可以嵌入Java 代码，为用户提供动态数据；

## 8.2 JSP 原理

服务器内部工作：
Tomcat 中有一个 work 工作目录；
IDEA 中使用 Tomcat 的会在 IDEA 中 Tomcat 中生产一个 work 目录

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102205139736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

```
C:\Users\Administrator\.IntelliJIdea2018.1\system\tomcat\Unnamed_javaweb-session-cookie\work\Catalina\localhost\ROOT\org\apache\jsp
1
```

发现页面转变成了 Java 程序

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102205258993.png#pic_center)
浏览器向服务器发送请求，不管访问什么资源，起始都是在访问 Servlet ！

JSP 最终也会被转换成一个 Java 类！
JSP 本质上就是一个 Servlet

```java
//初始化
  public void _jspInit() {
      
  }
//销毁
  public void _jspDestroy() {
  }
//JSPService
  public void _jspService(.HttpServletRequest request,HttpServletResponse response)
123456789
```

1. 判断请求
2. 内置一些对象

```java
final javax.servlet.jsp.PageContext pageContext;  //页面上下文
javax.servlet.http.HttpSession session = null;    //session
final javax.servlet.ServletContext application;   //applicationContext
final javax.servlet.ServletConfig config;         //config
javax.servlet.jsp.JspWriter out = null;           //out
final java.lang.Object page = this;               //page：当前
HttpServletRequest request                        //请求
HttpServletResponse response                      //响应
12345678
```

1. 输出页面前增加的代码

```java
response.setContentType("text/html");       //设置响应的页面类型pageContext = _jspxFactory.getPageContext(this, request, response,                                          null, true, 8192, true);_jspx_page_context = pageContext;application = pageContext.getServletContext();config = pageContext.getServletConfig();session = pageContext.getSession();out = pageContext.getOut();_jspx_out = out;123456789
```

4.以上这些对象可直接在 JSP 中使用

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102205856631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

在JSP页面中；

只要是 JAVA代码就会原封不动的输出；

如果是HTML代码，就会被转换为：

```
out.write("<html>\r\n");
1
```

这样的格式，输出到前端！

## 8.3 JSP 基础语法

JSP 表达式

```xml
  <%--JSP表达式
  作用：用来将程序的输出，输出到客户端
  <%= 变量或者表达式%>
  --%>
  <%= new java.util.Date()%>
12345
```

**JSP 脚本片段**

```xml
  <%--jsp脚本片段--%>
  <%
    int sum = 0;
    for (int i = 1; i <=100 ; i++) {
      sum+=i;
    }
    out.println("<h1>Sum="+sum+"</h1>");
  %>
12345678
```

**脚本片段的再实现**

```xml
  <%    int x = 10;    out.println(x);  %>  <p>这是一个JSP文档</p>  <%    int y = 2;    out.println(y);  %>  <hr>  <%--在代码嵌入HTML元素--%>  <%    for (int i = 0; i < 5; i++) {  %>    <h1>Hello,World  <%=i%> </h1>  <%    }  %>123456789101112131415161718192021
```

**JSP 声明**

```xml
  <%!    static {      System.out.println("Loading Servlet!");    }    private int globalVar = 0;    public void kuang(){      System.out.println("进入了方法Kuang！");    }  %>1234567891011
```

JSP 声明： 会被编译到 JSP 生成 Java 的类中！ 其他的，就会被生成 _jspService 方法中！

```
<%%><%=%><%!%><%--注释--%>12345
```

JSP 的注释，不会在客户端显示，HTML就会！

## 8.4 JSP 指令

```xml
<%@page args.... %>
<%@include file=""%>

<%--@include会将两个页面合二为一--%>

<%@include file="common/header.jsp"%>
<h1>网页主体</h1>

<%@include file="common/footer.jsp"%>

<hr>


<%--jSP标签
    jsp:include：拼接页面，本质还是三个
    --%>
<jsp:include page="/common/header.jsp"/>
<h1>网页主体</h1>
<jsp:include page="/common/footer.jsp"/>
12345678910111213141516171819
```

## 8.5 九大内置对象

- PageContext 存东西
- Request 存东西
- Response
- Session 存东西
- Application 【ServletContext】 存东西
- config 【ServletConfig】
- out
- page
- exception

```java
pageContext.setAttribute("name1","秦疆1号"); //保存的数据只在一个页面中有效
request.setAttribute("name2","秦疆2号"); //保存的数据只在一次请求中有效，请求转发会携带这个数据
session.setAttribute("name3","秦疆3号"); //保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
application.setAttribute("name4","秦疆4号");  //保存的数据只在服务器中有效，从打开服务器到关闭服务器
1234
```

request：客户端向服务器发送请求，产生的数据，用户看完就没用了，比如：新闻，用户看完没用的！

session：客户端向服务器发送请求，产生的数据，用户用完一会还有用，比如：购物车；

application：客户端向服务器发送请求，产生的数据，一个用户用完了，其他用户还可能使用，比如：聊天数据；

## 8.6 JSP标签、JSTL标签、EL表达式

```xml
<!-- JSTL表达式的依赖 -->
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<!-- standard标签库 -->
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
123456789101112
```

EL 表达式： ${}

- 获取数据
- 执行运算
- 获取 Web 开发的常用对象

JSP 标签

```xml
<%--jsp:include--%>

<%--
http://localhost:8080/jsptag.jsp?name=kuangshen&age=12
--%>

<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="name" value="kuangshen"></jsp:param>
    <jsp:param name="age" value="12"></jsp:param>
</jsp:forward>
12345678910
```

**JSTL表达式**

JSTL 标签库的使用就是为了弥补 HTML 标签的不足；它自定义许多标签，可以供我们使用，标签的功能和 Java 代码一样！

**格式化标签**

**SQL标签**

**XML 标签**

**核心标签** （掌握部分）

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110221251720.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
**JSTL标签库使用步骤**

- 引入对应的 taglib
- 使用其中的方法
- **在 Tomcat 也需要引入 JSTL 的包，否则会报错：JSTL 解析错误**

c: if

```xml
<head>
    <title>Title</title>
</head>
<body>


<h4>if测试</h4>

<hr>

<form action="coreif.jsp" method="get">
    <%--
    EL表达式获取表单中的数据
    ${param.参数名}
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>

<%--判断如果提交的用户名是管理员，则登录成功--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员欢迎您！"/>
</c:if>

<%--自闭合标签--%>
<c:out value="${isAdmin}"/>

</body>
12345678910111213141516171819202122232425262728
```

c:choose c:when

```xml
<body>

<%--定义一个变量score，值为85--%>
<c:set var="score" value="55"/>

<c:choose>
    <c:when test="${score>=90}">
        你的成绩为优秀
    </c:when>
    <c:when test="${score>=80}">
        你的成绩为一般
    </c:when>
    <c:when test="${score>=70}">
        你的成绩为良好
    </c:when>
    <c:when test="${score<=60}">
        你的成绩为不及格
    </c:when>
</c:choose>

</body>
123456789101112131415161718192021
```

c:forEach

```xml
<%

    ArrayList<String> people = new ArrayList<>();
    people.add(0,"张三");
    people.add(1,"李四");
    people.add(2,"王五");
    people.add(3,"赵六");
    people.add(4,"田六");
    request.setAttribute("list",people);
%>


<%--
var , 每一次遍历出来的变量
items, 要遍历的对象
begin,   哪里开始
end,     到哪里
step,   步长
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>

<hr>

<c:forEach var="people" items="${list}" begin="1" end="3" step="1" >
    <c:out value="${people}"/> <br>
</c:forEach>
12345678910111213141516171819202122232425262728
```

# 9 JavaBean

实体类
JavaBean有特定的写法：

- 必须要有一个无参构造
- 属性必须私有化
- 必须有对应的get/set方法；

一般用来和数据库的字段做映射 ORM；

ORM ：对象关系映射

- 表—>类
- 字段–>属性
- 行记录---->对象

| id   | name   | age  | address |
| ---- | ------ | ---- | ------- |
| 1    | sjmp01 | 3    | 西安    |
| 2    | sjmp02 | 18   | 西安    |
| 3    | sjmp03 | 100  | 川      |

```java
class People{
    private int id;
    private String name;
    private int id;
    private String address;
}

class A{
    new People(1,"秦疆1号",3，"西安");
    new People(2,"秦疆2号",3，"西安");
    new People(3,"秦疆3号",3，"西安");
}
123456789101112
```

过滤器实现登录拦截功能，放一个常量标记是否已登录。

# 10 MVC 三层架构

什么是 MVC ： Model View Controller 模型、视图、控制器

## 10.1 以前

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102213932114.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)用户直接访问控制层，控制层就可以直接操作数据库；

```
servlet--CRUD-->数据库
弊端：程序十分臃肿，不利于维护  
servlet的代码中：处理请求、响应、视图跳转、处理JDBC、处理业务代码、处理逻辑代码

架构：没有什么是加一层解决不了的！
程序猿调用
|
JDBC
|
Mysql Oracle SqlServer ....
12345678910
```

## 10.2 MVC 三层架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102214221869.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
Model

- 业务处理：业务逻辑（Service）
- 数据持久层：CRUD

View

- 展示数据
- 提供链接发起 Servlet 请求（a,form,img…）

Controller （Servlet）

- 接收用户的请求：（req：请求参数、Session 信息…）

- 交给业务层处理对应的代码

- 控制试图的跳转

  ```
    登录--->接收用户的登录请求--->处理用户的请求（获取用户登录的参数，username，password）---->交给业务层处理登录业务（判断用户名密码是否正确：事务）--->Dao层查询用户名和密码是否正确-->数据库
  1
  ```

# 11 Filter（重点）

Filter：过滤器，用来过滤网站的数据；

- 处理中文乱码
- 登录验证

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102214811292.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)
Filter 开发步骤：

1. 导包
2. 编写过滤器
   - 导包不要错
     ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201102214949591.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

```java
public class CharacterEncodingFilter implements Filter {

    //初始化：web服务器启动，就以及初始化了，随时等待过滤对象出现！
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }

    //Chain : 链
    /*
    1. 过滤中的所有代码，在过滤特定请求的时候都会执行
    2. 必须要让过滤器继续同行
        chain.doFilter(request,response);
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CharacterEncodingFilter执行前....");
        chain.doFilter(request,response); //让我们的请求继续走，如果不写，程序到这里就被拦截停止！
        System.out.println("CharacterEncodingFilter执行后....");
    }

    //销毁：web服务器关闭的时候，过滤会销毁
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
12345678910111213141516171819202122232425262728
```

1. 在 web.xml 中配置Filter

```xml
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>com.kuang.filter.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <!--只要是 /servlet的任何请求，会经过这个过滤器-->
    <url-pattern>/servlet/*</url-pattern>
    <!--<url-pattern>/*</url-pattern>-->
</filter-mapping>
12345678910
```

# 12 监听器

实现一个监听器；

1. 编写一个监听器
   实现监听器的接口…

```java
//统计网站在线人数 ： 统计session
public class OnlineCountListener implements HttpSessionListener {

    //创建session监听： 看你的一举一动
    //一旦创建Session就会触发一次这个事件！
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        System.out.println(se.getSession().getId());

        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount==null){
            onlineCount = new Integer(1);
        }else {
            int count = onlineCount.intValue();
            onlineCount = new Integer(count+1);
        }

        ctx.setAttribute("OnlineCount",onlineCount);

    }

    //销毁session监听
    //一旦销毁Session就会触发一次这个事件！
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount==null){
            onlineCount = new Integer(0);
        }else {
            int count = onlineCount.intValue();
            onlineCount = new Integer(count-1);
        }

        ctx.setAttribute("OnlineCount",onlineCount);

    }


    /*
    Session销毁：
    1. 手动销毁  getSession().invalidate();
    2. 自动销毁
     */
}
123456789101112131415161718192021222324252627282930313233343536373839404142434445464748
```

1. 在 web.xml 中注册监听器

```xml
<!--注册监听器-->
<listener>
    <listener-class>com.kuang.listener.OnlineCountListener</listener-class>
</listener>
1234
```

1. 看情况是否使用！

# 13 SMBMS



数据库

![image-20210715143722646](C:\Users\24616\AppData\Roaming\Typora\typora-user-images\image-20210715143722646.png)

**项目如何搭建？**

考虑使不使用Maven？依赖？jar？

## 13.1项目搭建准备工作

1. 搭建一个maven web项目

2. 配置Tomcat

3. 测试项目是否能够运行

4. 导入项目中会遇到的jar包

   jsp，servlet，mysql驱动，jstl，standard标签库

5. 创建项目包结构

6. 编写实体类

   ORM映射：表-类映射

7. 编写基础公共类

   1. 数据库配置文件

      ```properties
      driver=com.mysql.jdbc.Driver
      url=jdbc:mysql://localhost:3306/smbms?userSSL=false&useUnicode=true&characterEncoding=utf-8
      username=root
      password=123456
      ```

   2. 编数据的公共类

      ```java	
      package com.yuno.dao;
      
      import java.io.IOException;
      import java.io.InputStream;
      import java.sql.*;
      import java.util.Properties;
      
      /**
       * @author Yuno
       * @create 2021-07-15-17:47
       */
      //操作数据库的公共类
      public class BaseDao {
          //静态代码块,在类加载的时候执行
          static{
              init();
          }
      
          private static String driver;
          private static String url;
          private static String username;
          private static String password;
      
          //初始化连接参数,从配置文件里获得
          public static void init(){
              Properties params=new Properties();
              String configFile = "db.properties";
              InputStream is= BaseDao.class.getClassLoader().getResourceAsStream(configFile);
              try {
                  params.load(is);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              driver=params.getProperty("driver");
              url=params.getProperty("url");
              username=params.getProperty("username");
              password=params.getProperty("password");
      
          }
      
      
          /**
           * 获取数据库连接
           * @return
           */
          public static Connection getConnection(){
              Connection connection = null;
              try {
                  Class.forName(driver);
                  connection = DriverManager.getConnection(url, username, password);
              } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
      
              return connection;
          }
          /**
           * 查询操作
           * @param connection
           * @param pstm
           * @param rs
           * @param sql
           * @param params
           * @return
           */
          public static ResultSet execute(Connection connection,PreparedStatement pstm,ResultSet rs,
                                          String sql,Object[] params) throws Exception{
              pstm = connection.prepareStatement(sql);
              for(int i = 0; i < params.length; i++){
                  pstm.setObject(i+1, params[i]);
              }
              rs = pstm.executeQuery();
              return rs;
          }
          /**
           * 更新操作
           * @param connection
           * @param pstm
           * @param sql
           * @param params
           * @return
           * @throws Exception
           */
          public static int execute(Connection connection,PreparedStatement pstm,
                                    String sql,Object[] params) throws Exception{
              int updateRows = 0;
              pstm = connection.prepareStatement(sql);
              for(int i = 0; i < params.length; i++){
                  pstm.setObject(i+1, params[i]);
              }
              updateRows = pstm.executeUpdate();
              return updateRows;
          }
      
          /**
           * 释放资源
           * @param connection
           * @param pstm
           * @param rs
           * @return
           */
          public static boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet rs){
              boolean flag = true;
              if(rs != null){
                  try {
                      rs.close();
                      rs = null;//GC回收
                  } catch (SQLException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                      flag = false;
                  }
              }
              if(pstm != null){
                  try {
                      pstm.close();
                      pstm = null;//GC回收
                  } catch (SQLException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                      flag = false;
                  }
              }
              if(connection != null){
                  try {
                      connection.close();
                      connection = null;//GC回收
                  } catch (SQLException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                      flag = false;
                  }
              }
      
              return flag;
          }
      
      }
      
      ```

   3. 编写字符编码过滤器

      ```java
      public class CharacterEncodingFilter implements Filter {
          @Override
          public void init(FilterConfig filterConfig) throws ServletException {
      
          }
      
          @Override
          public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
              request.setCharacterEncoding("utf-8");
              response.setCharacterEncoding("utf-8");
              chain.doFilter(request,response);
          }
      
          @Override
          public void destroy() {
      
          }
      }
      
      ```

      

8. 导入静态资源

   ​	

## 13.2登录功能实现

![image-20210715224836652](C:\Users\24616\AppData\Roaming\Typora\typora-user-images\image-20210715224836652.png)

1. 编写前端页面

2. 设置首页

   ```xml
   <!--设置欢迎页面-->
       <welcome-file-list>
           <welcome-file>login.jsp</welcome-file>
       </welcome-file-list>
   ```

3. 编写dao层用户登录的接口

   ```java
   public interface UserDao {
       //得到要登录的用户
       public User getloginuser(Connection connection, String userCode);
   }
   ```

4. 编写dao接口的实现类

   ```java
   public class UserDaoImpl implements UserDao{
   
       @Override
       public User getloginuser(Connection connection, String userCode) {
           //准备参数
           PreparedStatement pstm = null;
           ResultSet rs = null;
           User user = null;
   
           //判断数据库连接
           if (connection != null){
               String sql = "select * from smbms_user where userCode=?";
               Object[] params = {userCode};
               try {//执行sql操作
                   rs = BaseDao.excute(connection, pstm, rs, sql, params);
                   if (rs.next()){
                       user = new User();
                       user.setId(rs.getInt("id"));
                       user.setUserCode(rs.getString("userCode"));
                       user.setUserPassword(rs.getString("userPassword"));
                       user.setUserName(rs.getString("userName"));
                       user.setGender(rs.getInt("gender"));
                       user.setBirthday(rs.getTimestamp("birthday"));
                       user.setPhone(rs.getString("phone"));
                       user.setAddress(rs.getString("address"));
                       user.setUserRole(rs.getInt("userRole"));
                       user.setCreateBy(rs.getInt("createBy"));
                       user.setCreationDate(rs.getTimestamp("createTime"));
                       user.setModifyBy(rs.getInt("modifyBy"));
                       user.setModifyDate(rs.getTimestamp("modifyDate"));
                   }
                   BaseDao.closeResource(null, pstm, rs);
   
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }
           return user;
       }
   }
   
   ```

5. 业务层接口

   ```java
   package com.yuno.service.user;
   
   import com.yuno.pojo.User;
   
   /**
    * @author Yuno
    * @create 2021-07-15-23:26
    */
   public interface UserService {
       //用户登录
       public User login(String userCode, String userpassword);
   }
   
   ```

6. 业务实现类

   ```java
   public class UserServieImpl implements UserService{
       //业务层都会调用dao层，所以我们要引入dao层
       private UserDao userDao;
       public UserServieImpl(){
           userDao = new UserDaoImpl();
       }
   
   
       @Override
       public User login(String userCode, String userpassword) {
           Connection connection = null;
           User user = null;
           try {
               connection = BaseDao.getConnection();
               //通过业务层调用对应的具体的数据库操作
               user = userDao.getloginuser(connection, userCode);
           }catch (Exception e){
               e.printStackTrace();
           }finally {
               BaseDao.closeResource(connection, null, null);
           }
           return user;
       }
   
   }
   
   ```

7. 编写servlet

   ```java
   public class LoginServlet extends HttpServlet {
       //控制层调用业务层代码
   
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           System.out.println("LoginServlet start");
   
           //获取用户名和密码
           String userCode = req.getParameter("userCode");
           String userPassword = req.getParameter("userPassword");
           //和数据中的密码进行校验
           UserServieImpl userServie = new UserServieImpl();
           User user = userServie.login(userCode, userPassword);//这里已经把登录的人查出来了
   
           if (user!=null){//若用户存在
               //将用户的信息放到session用
               req.getSession().setAttribute(Constants.USER_SESSION,user);
               //跳转到主页
               resp.sendRedirect("jsp/frame.jsp");
           }else {//若用户不存在或密码错误，则跳转到登录页面，提示用户名或密码错误
               req.setAttribute("error","用户名或者密码错误");
               req.getRequestDispatcher("login.jsp").forward(req, resp);
           }
   
   
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   }
   
   ```

8. 注册servlet

   ```xml
   <servlet>
           <servlet-name>LoginServlet</servlet-name>
           <servlet-class>com.yuno.servlet.LoginServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>LoginServlet</servlet-name>
           <url-pattern>/login.do</url-pattern>
       </servlet-mapping>
   ```

9. 测试访问，确保以上功能成功

## 13.3登录功能优化

**注销功能：**

思路：移除session，返回登录页面

```java
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除用户的session
        req.getSession().removeAttribute(Constants.USER_SESSION);
        //返回登录页面
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

```

```xml
<servlet>
        <servlet-name>LogoutServlet</servlet-name>
        <servlet-class>com.yuno.servlet.LogoutServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LogoutServlet</servlet-name>
        <url-pattern>/jsp/logout.do</url-pattern>
    </servlet-mapping>
```

**登录拦截优化**

编写过滤器并注册

```java
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //从session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null){//说明用户已被移除或者注销或者未登录
            response.sendRedirect("/smbms/error.jsp");
        }else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}

```

```xml
<!--用户登录过滤器-->
    <filter>
        <filter-name>SysFilter</filter-name>
        <filter-class>com.yuno.filter.SysFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>SysFilter</filter-name>
        <url-pattern>/jsp/*</url-pattern>
    </filter-mapping>
```



## 13.4密码修改

1. 导入前端素材

2. 从底层向上写

   ![image-20210718171317493](C:\Users\24616\AppData\Roaming\Typora\typora-user-images\image-20210718171317493.png)

3. UserDao接口

   ```java
   //修改密码
       public int updatePwd(Connection connection, int id, int password) throws SQLException;
   ```

   

4. UserDao实现类

   ```java
   //修改密码
       @Override
       public int updatePwd(Connection connection, int id, int password) throws SQLException {
           PreparedStatement pstm = null;
           int execute = 0;
           if (connection != null){
               String sql = "update smbms_user set userPassword = ? where id = ?";
               Object parms[] = {password, id};
               try {
                   execute = BaseDao.execute(connection, pstm, sql, parms);
               } catch (Exception e) {
                   e.printStackTrace();
               }
               BaseDao.closeResource(null, pstm, null);
           }
           return execute;
       }
   ```

5. UserService层

   ```java
   //修改密码
       public Boolean updatePwd(int id, int password);
   ```

6. UserService实现类

   ```java
    @Override
       public Boolean updatePwd(int id, int password) {
           Connection connection = null;
           Boolean flag = false;
   
           try {
               connection = BaseDao.getConnection();
               if (userDao.updatePwd(connection,id,password) > 0){
                   flag = true;
               }
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }finally {
               BaseDao.closeResource(connection, null, null);
           }
           return flag;
       }
   ```

7. servlet实现复用，提取方法

   ```java
   //实现servlet复用
   public class UserServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           String method = req.getParameter("method");
           if (method.equals("updatePwd") && method != null){
               this.updatePwd(req, resp);
           }
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   
       public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //从session里获取用户id
           Object o = req.getSession().getAttribute(Constants.USER_SESSION);
           String newpassword = req.getParameter("newpassword");
           boolean flag = false;
   
           //进行判断
           if (o !=null && !StringUtils.isNullOrEmpty(newpassword)){
               UserServieImpl userServie = new UserServieImpl();
               flag = userServie.updatePwd(((User) o).getId(), newpassword);
               if (flag){
                   req.setAttribute("message","修改密码成功，请退出使用新密码登录");
                   //密码修改成功，移除session
                   req.getSession().removeAttribute(Constants.USER_SESSION);
               }else {
                   req.setAttribute("message","密码修改失败");
               }
           }else {
               req.setAttribute("message","新密码不符合格式");
           }
           req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
       }
   }
   ```

8. servlet注册

   ```xml
   <servlet>
           <servlet-name>UserServlet</servlet-name>
           <servlet-class>com.yuno.servlet.UserServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>UserServlet</servlet-name>
           <url-pattern>/jsp/user.do</url-pattern>
       </servlet-mapping>
   ```

**优化密码管理，使用Ajax**

1. 阿里巴巴的fastjson

   ```xml
   <dependency>
         <groupId>com.alibaba</groupId>
         <artifactId>fastjson</artifactId>
         <version>1.2.76</version>
       </dependency>
   ```

2. ```java
   //验证旧密码
       public void pwdmodify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
           //从session里获取用户id
           Object o = req.getSession().getAttribute(Constants.USER_SESSION);
           String oldpassword = req.getParameter("oldpassword");
   
           //万能的map
           Map<String, String> resultMap = new HashMap<>();
   
   
           if (o == null){//Session失效或者过期了
               resultMap.put("result", "sessionerror");
           }else if(StringUtils.isNullOrEmpty(oldpassword)){
               resultMap.put("result", "error");
           }else {
               String userPassword = ((User)o).getUserPassword();//Session中的密码
               if (oldpassword.equals(userPassword)) {
                   resultMap.put("result", "true");
               }else {
                   resultMap.put("result", "false");
               }
           }
           resp.setContentType("application/json");
           PrintWriter writer = resp.getWriter();
           //JSONArray:阿里巴巴的json工具类,转换格式
           /*resultMap = ["result","true"]
             json格式:{"result":"true"}
           * */
           writer.write(JSONArray.toJSONString(resultMap));
           writer.flush();
           writer.close();
       }
   ```

## 13.5用户管理实现

思路：

![image-20210718212931251](C:\Users\24616\AppData\Roaming\Typora\typora-user-images\image-20210718212931251.png)



1. 导入分类的工具类
2. 导入用户管理页面



### 13.5.1 获取用户数量

1. UserDao

   ```java
   //根据用户名或者角色名查询用户总数
       public int getUserCount(Connection connection, String username, int userRole) throws SQLException;
   ```

2. UserDaoImpl

   ```java
   ////根据用户名或者角色名查询用户总数
       @Override
       public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
           PreparedStatement pstm = null;
           ResultSet rs = null;
           int count = 0;
   
           if (connection != null){
               StringBuffer sql = new StringBuffer();
               sql.append("select count(*) as count from smbms_user u, smbms_role r where u.userRole = r.id");
               ArrayList<Object> list = new ArrayList<>();
               if (!StringUtils.isNullOrEmpty(username)){
                   sql.append(" and u.username like ?");
                   list.add("%" + username + "%");//index:0
               }
               if (userRole > 0){
                   sql.append(" and u.userRole like ?");
                   list.add(userRole);
               }
   
               //把list转换成数组
               Object[] parms = list.toArray();
               //输出完整的sql语句，方便调试
               System.out.println("UserDaoImpl -> getUserCount:" + sql.toString());
   
               try {
                   rs = BaseDao.execute(connection, pstm, rs, sql.toString(), parms);
               } catch (Exception e) {
                   e.printStackTrace();
               }
               if (rs.next()){
                   count = rs.getInt("count");
               }
               BaseDao.closeResource(null, pstm, rs);
           }
           return count;
       }
   ```

3. UserService

   ```java
   //查询记录数
       public int getUserCount(String username, int userRole);
   ```

4. UserServiceImpl

```java
public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;

        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }
```



### 13.5.2获取用户列表

1. UserDao

   ```java
   //用户列表查询
       public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException;
   ```

2. UserDaoImpl

   ```java
   public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) {
           PreparedStatement pstm = null;
           ResultSet rs = null;
           List<User> userList = new ArrayList<>();
           if (connection != null){
               StringBuffer sql = new StringBuffer();
               sql.append("select u.*, r.roleName from smbms_user u, smbms_role r where u.userRole = r.id");
               List<Object> parms = new ArrayList<>();
               if (!StringUtils.isNullOrEmpty(username)){
                   sql.append(" and u.userName like ?");
                   parms.add("%" + username + "%");
               }
               if (userRole > 0){
                   sql.append(" and u.userRole like ?");
                   parms.add(userRole);
               }
   
               //在数据库中，分页使用limit -> StartIndex,Size  总数
               sql.append(" order by creationDate DESC limit ?, ?");
               currentPageNo = (currentPageNo - 1) * pageSize;
               parms.add(currentPageNo);
               parms.add(pageSize);
   
               Object[] objects = parms.toArray();
   
               System.out.println("UserDaoImpl->getUserList sql:" + sql.toString());
   
               try {
                   rs = BaseDao.execute(connection, pstm, rs, sql.toString(), objects);
                   while (rs.next()){
                       User _user = new User();
                       _user.setId(rs.getInt("id"));
                       _user.setUserCode(rs.getString("userCode"));
                       _user.setUserName(rs.getString("userName"));
                       _user.setGender(rs.getInt("gender"));
                       _user.setBirthday(rs.getDate("birthday"));
                       _user.setPhone(rs.getString("phone"));
                       _user.setUserRole(rs.getInt("userRole"));
                       _user.setUserRoleName(rs.getString("roleName"));
                       userList.add(_user);
                   }
                   BaseDao.closeResource(null, pstm, rs);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
           return userList;
       }
   ```

3. UserService

   ```java
   //查询用户列表
       public List<User> getUserList(String username, int userRole, int currenPageNo, int pageSize);
   ```

4. UserServiceImpl

   ```java
   public List<User> getUserList(String username, int userRole, int currenPageNo, int pageSize) {
           Connection connection = null;
           List<User> userList = new ArrayList<>();
           connection = BaseDao.getConnection();
           try {
               userList = userDao.getUserList(connection, username, userRole, currenPageNo, pageSize);
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }finally {
               BaseDao.closeResource(connection, null, null);
           }
           return userList;
       }
   ```


### 13.5.3获取角色列表

为了职责明确统一，可以把角色的操作独立放在一个包中，和pojo类相对应

1. RoleDao

   ```java
   public interface RoleDao {
       //角色列表查询
       public List<Role> getRoleList(Connection connection) throws SQLException;
   }
   ```

2. RoleDaoImpl

   ```java
   public class RoleDaoImpl implements RoleDao{
       @Override
       public List<Role> getRoleList(Connection connection) throws SQLException {
           PreparedStatement pstm = null;
           ResultSet rs = null;
           List<Role> roleList = new ArrayList<>();
   
           if (connection != null){
               String sql = "select * from smbms_role";
               Object[] parms = {};
               try {
                   rs = BaseDao.execute(connection, pstm, rs, sql, parms);
                   while (rs.next()){
                       Role _role = new Role();
                       _role.setId(rs.getInt("id"));
                       _role.setRoleCode(rs.getString("roleCode"));
                       _role.setRoleName(rs.getString("roleName"));
                       _role.setCreatedBy(rs.getInt("createdBy"));
                       _role.setCreationDate(rs.getDate("creationDate"));
                       _role.setModifyBy(rs.getInt("modifyBy"));
                       _role.setModifyDate(rs.getDate("modifyDate"));
                       roleList.add(_role);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }finally {
                   BaseDao.closeResource(null, pstm, rs);
               }
           }
           return roleList;
       }
   }
   ```

   

3. RoleService

   ```java
   public interface RoleService {
       //获取角色列表
       public List<Role> getRoleList();
   }
   ```

   

4. RoleServiceImpl

   ```java
   public class RoleServiceImpl implements RoleService{
       //业务层都会调用dao层，所以我们要引入dao层
       private RoleDao roleDao;
       public RoleServiceImpl(){
           roleDao = new RoleDaoImpl();
       }
   
       @Override
       public List<Role> getRoleList() {
           Connection connection = null;
           List<Role> roleList = new ArrayList<>();
   
           try {
               connection = BaseDao.getConnection();
               roleList = roleDao.getRoleList(connection);
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }finally {
               BaseDao.closeResource(connection, null, null);
           }
           return roleList;
       }
   }
   ```

### 13.5.4用户显示的servlet

1. 获取用户前端的数据
2. 判断请求是否需要执行，判断前端的参数是否正确
3. 为了实现分页，需要计算出当前页面，总页数，页面容量
4. 用户列表展示
5. 返回前端

```java
else if (method.equals("query") && method != null){
            this.query(req, resp);
        }
```

```java
private void query(HttpServletRequest req, HttpServletResponse resp) throws  ServletException,IOException{
        //查询用户列表

        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        //获取用户列表
        UserServieImpl userServie = new UserServieImpl();
        List<User> userList = null;
        //第一次走这个请求默认请求列表首页
        int pageSize = 5;//可以写到配置文件，方便以后修改
        int currentPageNo = 1;//默认第一页

        if (queryUserName == null){
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);//给查询赋值：0,1,2,3
        }
        if (pageIndex != null){
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (NumberFormatException e) {
                resp.sendRedirect("error.jsp");
            }
        }

        //获取用户的总数(分页：上一页和下一页)
        int totalCount = userServie.getUserCount(queryUserName, queryUserRole);
        //总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        //控制首页和尾页
        if (totalCount < 1){//当页面下标小于1时，则默认访问第一页
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){//当前页面大于页面总数
            currentPageNo = totalPageCount;
        }

        //获取用户列表展示
        userList = userServie.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList", userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("queryUserName", queryUserName);
        req.setAttribute("queryUserRole", queryUserRole);

        //返回前端
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);
    }
```



**小黄鸭调试法：自言自语**





# 14文件上传

### **1、准备工作**

采用Apache的开源工具common-fileupload这个文件上传组件。

common-fileupload是依赖于common-io这个包的，所以还需要下载这个包。

### **2、使用类介绍**

【文件上传注意事项】

1、为保证服务器的安全，上传的文件应放在外界无法访问的目录下，如WEN-INF。

2、为防止同名文件产生覆盖现象，要为文件指定一个唯一的文件名。(-时间戳 -uuid -md5 -位运算算法 )

3、要对上传文件的大小进行限制。

4、限制上传文件的类型，收到文件时，判断文件名十分合法。

HTML中

**表单中如果包含一个文件上传项的话，这个表单的entype属性必须设置为multipart/form-data**

```html
    <form action="${pageContext.request.contextPath}/upload.do" method="post"enctype="multipart/form-data">
        <p>用户名：<input type="text" name="username" placeholder="请填写用户名"></p>
        <p>上传文件：<input type="file" name="filename"></p>
        <p><input type="submit" value="提交"><input type="reset" value="重置"></p>
```

【需要用到的类详解】

**ServletFileUpload**负责处理上传的文件数据，并将表单中的每个输入项封装成一个**FileItem对象**，在使用ServletFileUpload对象解析请求时需要**DiskFileItemFactory对象**。所以，我们需要在进行解析工作前构造好DiskFileItemFactory对象，通过ServletFileItem对象的构造方法或setFileItemFactory()设置ServletFileUpload对象的**fileItemFactory**属性。

```java
public class UploadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //判断上传的表单是普通表单还是带文件的表单，是返回true,否返回false；
        if (!ServletFileUpload.isMultipartContent(request)){
            return;//如果这是一个普通文件我们直接返回
        }//如果通过了这个if，说明我们的表单是带文件上传的


        //创建上传文件的保存目录，为了安全建议在WEB-INF目录下，用户无法访问
        String uploadpath = this.getServletContext().getRealPath("WEB-INF/Upload");//获取上传文件的保存路径
        File uploadfile = new File(uploadpath);
        if (!uploadfile.exists()){
            uploadfile.mkdir();//如果目录不存在就创建这样一个目录
        }


        //临时文件
        //临时路径，如果上传的文件超过预期的大小，我们将它存放到一个临时目录中，过几天自动删除，或者提醒用户转存为永久
        String tmppath = this.getServletContext().getRealPath("WEB-INF/tmp");
        File file = new File(tmppath);
        if (!file.exists()){
            file.mkdir();//如果目录不存在就创建这样临时目录
        }
        //处理上传的文件一般需要通过流来获取，我们可以通过request.getInputstream(),原生态文件上传流获取，十分麻烦
        //但是我们都建议使用Apache的文件上传组件来实现，common-fileupload,它需要依赖于common-io组件；


        try {
            //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
            DiskFileItemFactory factory = gteDiskFileItemFactory(file);
            //2、获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);
            //3、处理上传文件
            String msg = uploadParseRequest(upload,request,uploadpath);
            //Servlet请求转发消息
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/info.jsp").forward(request,response);
        }catch (FileUploadException e){
            e.printStackTrace();
        }
    }
    public static DiskFileItemFactory gteDiskFileItemFactory(File file){
        //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
        DiskFileItemFactory factory = new DiskFileItemFactory();


        //通过这个工厂设置一个缓冲区，当上传的文件大小大于缓冲区的时候，将它放到临时文件中；
        factory.setSizeThreshold(1024 * 1024);//缓冲区大小为1M
        factory.setRepository(file);
        return factory;
    }
    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        //2、获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long pBytesRead, long lpContentLenght, int i) {
                //pBytesRead:已读取到的文件大小
                //pContentLenght：文件大小
                System.out.println("总大小："+lpContentLenght+"已上传："+pBytesRead);
            }
        });


        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);
        return upload;
    }
    public static String uploadParseRequest(ServletFileUpload upload,HttpServletRequest request,String uploadpath) throws IOException, FileUploadException {
        String msg = "";
        //3、处理上传文件
        //把前端的请求解析，封装成一个FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()){ //判断是普通表单还是带文件的表单
                //getFieldName指的是前端表单控件的name
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");//处理乱码
                System.out.println(name+":"+value);
            }else {//判断它是带文件的表单


                //======================处理文件=======================//


                //拿到文件的名字
                String uploadFileName = fileItem.getName();
                System.out.println("上传的文件名："+uploadFileName);


                if (uploadFileName.trim().equals("")) continue;


                //获得上传的文件名，例如/img/girl/ooa.jpg,只需要ooa，其前面的后面的都不需要
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                /*
                 * 如果后缀名 fileExtName 不是我们需要的
                 *就直接return，不处理，告诉用户类型不对
                 * */
                System.out.println("文件信息【文件名："+fileName+"文件类型："+fileExtName+"】");


                //可以使用UUID(唯一通用识别码)来保证文件名的统一
                String uuidFileName = UUID.randomUUID().toString();




                //=======================传输文件=========================//
                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();


                //创建一个文件输出流
                FileOutputStream fos = new FileOutputStream(uploadpath + "/" + uuidFileName +"."+ fileExtName);


                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];


                //判断是否读取完毕
                int len;


                //如果大于0，说明还存在数据
                while ((len=inputStream.read(buffer))>0){
                    fos.write(buffer,0,len);
                }


                //关闭流
                fos.close();
                inputStream.close();


                msg = "文件上传成功！";
                fileItem.delete();//上传成功，清除临时文件
            }
        }


        return msg;
    }
}
```

### **3、其他**

重点了解HashMap和线程底层源码

uuid是如何实现的

