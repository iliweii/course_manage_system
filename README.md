# 易管•学生课程管理系统

> 易管从客户资料管理系统，到信息管理系统，再到学生课程管理系统，不断完善中，我的专业知识水平也不断提升。

## 环境

- java version 1.8.0_251 (jdk1.8)
- MariaDB 10.4.10 (MySQL 5.7及更新)

## 开始

1. 数据库配置。

   创建数据库course_manage_system，服务器连接排序规则为utf8_general_cii或者utf8mb4_general_ci

   导入数据库文件course_manage_system.sql

2. 使用IntelliJ IDEA打开项目文件（Eclipse等其他编辑器未经过验证），点击右上角运行'Application' 或者按组合快捷键Shift + F10。

3. 浏览器打开http://localhost:8080/。项目运行正常。

   运行截图

   <img src="https://img.honghong520.xyz/2020/11/16/b20139496901b.jpg" alt="登录界面" style="zoom: 33%;" />

   <img src="https://img.honghong520.xyz/2020/11/16/67a71122ede0b.jpg" alt="主界面" style="zoom:33%;" />

## 项目依赖

此处说明不用关心，具体见工程下pom.xml文件

```xml
<!-- 提供一些SpringMVC必要的组件-->
<!-- 使用嵌入式的tomcat作为web容器-->
<!-- 提供了以server.为前缀的配置项-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- 测试用，TODO-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <!-- JUnit 4 中使用的测试引擎-->
        <exclusion>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 提供自动配置数据访问的基础设施-->
<!-- DataSource自动配置模块提供的配置参数-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<!-- MyBatis依赖-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<!-- MySQL依赖-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!-- Druid 数据库连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.12</version>
</dependency>
<!-- 解析jsp依赖-->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
<!-- 解析servlet (jsp) 依赖-->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
<!-- Google Gson 用于json数据处理-->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
</dependency>
```

## 功能简介

易管•学生课程管理系统提供四种身份：学生身份，教师身份，管理员身份和高级管理员身份。

除功能模块外，登录系统后系统右上角有当前时间和本地天气。非常银杏。

登录界面要输入验证码，防止机器人攻击。

### 管理员模块

管理员功能分为四大模块，基本数据管理，课程安排管理，学生成绩管理，个人信息管理。

#### 基本数据管理

基本数据管理包括管理员维护（高级管理员独有）、学生信息维护、教师信息维护、班级信息维护、课程信息维护。

##### 管理员维护（高级管理员独有）

管理员信息包括管理员登录名，姓名，密码。

对管理员进行增删改查，额外有批量删除功能、批量添加功能、分页功能，查询是根据管理员登录名模糊查询。

##### 学生信息维护

学生信息包括学号、班级、姓名、密码、性别、年龄、邮箱、手机号。

对学生信息进行增删改查，额外有批量删除功能、批量添加功能、分页功能，查询是根据学号以及姓名模糊查询。

##### 教师信息维护

教师信息包括教职工号、姓名、密码、邮箱、手机号。

对教师信息进行增删改查，额外有批量删除功能、批量添加功能、分页功能，查询是根据教职工号、姓名进行模糊查询。

##### 班级信息维护

班级信息包括班级号，班级名，所属学院、班级人数。

对班级信息进行增删改查，额外功能有批量删除功能、批量修改功能、分页功能，查询是根据班级名称、学院进行模糊查询。

##### 课程信息维护

课程信息包括课程编号、课程名称、课程人数。

对课程信息进行增删改查，额外功能有批量删除功能、批量修改功能、分页功能，查询是根据课程编号、名称进行模糊查询。

#### 课程安排管理

课程安排管理包括学生选课安排、教师授课安排、公选课安排。

##### 学生选课安排

学生选课安排是对学生选课信息进行管理的功能模块。学生选课信息包括学号、学生姓名、课程编号、课程名称、状态。

对学生选课信息进行增删改查，额外功能包括批量删除、批量添加、通过选课、批量通过选课、分页功能，查询是根据学号、课程编号进行模糊查询，还可以查询全部未通过学生选课。

##### 教师授课安排

教师授课安排是对教师授课信息进行管理的功能模块。教师授课信息包括教职工号、教师姓名、课程编号、课程名称、课程用书、状态。

对教师授课信息进行增删改查，额外功能还包括批量删除、批量添加、通过授课、批量通过授课、分页功能，查询是根据教职工号、课程编号进行模糊查询，还可以查询全部未通过教师授课。

##### 公选课安排

公选课安排比较特殊，在本页面有三个下拉框，分别是选择班级下拉框、选择教师下拉框、选择课程下拉框。

通过三个下拉框选择班级、教师、课程后，点击提交会为该班级中的所有学生安排本课程，为该教师安排本授课。十分方便。

#### 学生成绩管理

学生成绩管理只包含学生成绩管理一个功能模块。

##### 学生成绩管理

学生成绩信息包括课程编号、课程名称、学号、姓名、班级、授课教师、成绩。

对学生成绩信息进行删改查，额外功能包括批量删除、分页功能，查询可以根据学号、课程号进行模糊查询，还可以在下拉框中选择具体班级查看该班级中所有学生的成绩，还可以在下拉框中选择具体课程查看该课程中所有学生的成绩。

学生选课尚未通过者不显示在列表中。学生选课通过但未公布成绩者，成绩信息显示为/，已提交学生成绩的，成绩显示为具体数值。任课教师一列，显示的为该课程所有的授课教师。

#### 个人信息管理

个人信息管理包括修改个人信息（管理员独有）和退出登录。

##### 修改个人信息（管理员独有）

允许管理员对自己的姓名或者密码进行修改。

##### 退出登录

点击该模块后会退出登录。

### 学生模块

学生功能分为两大模块：基本信息管理、个人信息管理。

#### 基本信息管理

基本信息管理包括我的成绩查询、我的选课查询、申请新课程。

##### 我的成绩查询

学生成绩信息包括课程编号、课程名称、学号、姓名、班级、任课教师、成绩。未通过选课不会出现在列表中，未公布成绩中成绩信息显示未/，已公布成绩显示具体数值。任课教师一列，显示的为该课程所有的授课教师。

学生只能查询，不能对成绩进行其他操作。

##### 我的选课查询

选课信息包括课程编号、课程名称、学号、学生姓名、状态。

学生只能查询，不能对选课进行其他操作。

##### 申请新课程

申请新课程界面包括一个下拉框，在下拉框中选择课程之后点击申请，选课成功，即选课变为等待通过状态。最终结果要等管理员通过选课。

#### 个人信息管理

个人信息管理包括修改个人信息和退出登录。

##### 修改个人信息

允许学生对自己的姓名、密码、性别、年龄、邮箱、手机号等信息进行修改。

##### 退出登录

点击该模块后会退出登录。

### 教师模块

教师功能分为两大模块：基本信息管理、个人信息管理。

#### 基本信息管理

基本信息管理包括学生成绩管理，我的授课查询，申请新授课。

##### 学生成绩管理

学生成绩信息包括课程编号、课程名称、学号、姓名、班级、成绩。学生成绩信息为本教师所有授课课程下的学生成绩信息。

对学生成绩进行删改查，额外功能有批量删除、分页功能，查询根据学号、课程号进行模糊查询。

##### 我的授课查询

授课信息包括教职工号、教师姓名、课程编号、课程名称、课程用书、状态。

教师只能查询信息，不能进行其他操作。

##### 申请新授课

申请新授课界面包括一个下拉框，在下拉框中选择课程之后点击申请，选择授课成功，即授课变为等待通过状态。最终结果要等管理员通过授课。

#### 个人信息管理

个人信息管理包括修改个人信息和退出登录。

##### 修改个人信息

允许学生对自己的姓名、密码、邮箱、手机号等信息进行修改。

##### 退出登录

点击该模块后会退出登录。

## 功能实现

### 数据库层面

数据库中有8个数据表，分别是admin高级管理员表、tbuser管理员表、student学生表、teacher教师表、class班级表、course课程表、choose选课表、teaching授课表

##### admin高级管理员表

```mysql
CREATE TABLE IF NOT EXISTS `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) NOT NULL,
  `apwd` varchar(40) NOT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aname` (`aname`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='高级管理员表';
```

##### tbuser管理员表

```mysql
CREATE TABLE IF NOT EXISTS `tbuser` (
  `tbid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主码',
  `tbuser` varchar(100) NOT NULL COMMENT '管理员登录名',
  `tbname` varchar(100) NOT NULL COMMENT '姓名',
  `tbpwd` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`tbid`),
  UNIQUE KEY `tbuser` (`tbuser`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户表';
```

##### student学生表

```mysql
CREATE TABLE IF NOT EXISTS `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `sno` varchar(20) NOT NULL COMMENT '学号',
  `cno` varchar(20) NOT NULL COMMENT '班级编号',
  `sname` varchar(15) NOT NULL COMMENT '姓名',
  `spwd` varchar(40) NOT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `sage` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sno` (`sno`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='学生表';
```

teacher教师表

```mysql
CREATE TABLE IF NOT EXISTS `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `tno` varchar(20) NOT NULL COMMENT '教师号',
  `tname` varchar(20) NOT NULL COMMENT '姓名',
  `tpwd` varchar(40) NOT NULL COMMENT '密码',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`tid`),
  UNIQUE KEY `tno` (`tno`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='教师表';
```

##### class班级表

```mysql
CREATE TABLE IF NOT EXISTS `class` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `cno` varchar(20) NOT NULL COMMENT '班级号',
  `cname` varchar(20) NOT NULL COMMENT '班级名',
  `college` varchar(40) NOT NULL COMMENT '所属学院',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cno` (`cno`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='班级表';
```

##### course课程表

```mysql
CREATE TABLE IF NOT EXISTS `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cno` varchar(20) NOT NULL COMMENT '课程编号',
  `cname` varchar(20) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cno` (`cno`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';
```

##### choose选课表

```mysql
CREATE TABLE IF NOT EXISTS `choose` (
  `scid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sno` varchar(20) NOT NULL COMMENT '学号',
  `cno` varchar(20) NOT NULL COMMENT '课程编号',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '申请状态',
  `grade` double DEFAULT NULL COMMENT '成绩 ',
  PRIMARY KEY (`scid`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='学生选课表';
```

##### teaching授课表

```mysql
CREATE TABLE IF NOT EXISTS `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `tno` varchar(20) NOT NULL COMMENT '教师号',
  `tname` varchar(20) NOT NULL COMMENT '姓名',
  `tpwd` varchar(40) NOT NULL COMMENT '密码',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`tid`),
  UNIQUE KEY `tno` (`tno`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='教师表';
```

### 后端

Spring Boot 框架作为技术依托，实现数据库访问层、业务逻辑层、表现层（api接口）。

同时实现过滤器等。

#### 数据库访问层 DAO

主要用到Mybatis的技术，在dao包下创建接口方法，在resources/mapping文件夹下完成与数据库交互。对接口使用@Repository注解对持久层注册到容器

#### 业务逻辑层 SERVICE

先在service包下创建接口方法，再在service/impl包下，实现接口。使用@Service注解对业务逻辑层自动注册到Spring容器，使用@Autowired注解完成自动装配。

#### 表现层(api接口) API

在api包下创建类，使用@RestController注解返回Return 里的内容，使用@PostMapping()注解处理前端的POST请求数据，使用@RequestParam()注解获取请求参数。

#### 其他后端实现

##### 页面控制 Controller

类似于API，使用@Controller注解接收请求并返回一个页面(jsp)。

使用@RequestMapping()注解接收访问请求并return一个页面名。

**注：所有jsp页面请求均在PageController中实现。**

##### 过滤器 Filter

在filter包下创建类，使用@Component注解把普通pojo实例化到spring容器中，使用@ServletComponentScan注解使Filter注册，使用@WebFilter()注解，实现过滤器功能。

##### 错误页 Error

在error包下创建类，继承ErrorPageRegistrar并实现registerErrorPages方法，使用@Configuration注解，即可自定义404界面。

### 前端

前端使用了jsp，css，js(jquery)。其中jsp文件放在webapp/WEB-INF/jsp/下，css、js、fonts、img等静态资源放在resources/static/各自目录下。

#### JSP

jsp文件中把head部分、secondnav部分、foot部分分离。

- head.jsp 部分用于实现系统名称声明，头部信息栏（当前时间、当地天气、登录者姓名），头部导航栏，导航栏下方标识线。
- secondnav.jsp 部分用于实现次级导航栏。
- foot.jsp 部分用于实现底部版权信息。

其他jsp文件基本与html类似，这里不过多赘述。

#### CSS

font-awesome.css用于图标字体化，需配合fontawesome-webfont.woff2字体文件使用。

#### JS

js主要依靠JQuery实现。

js文件中大量使用ajax技术，使用ajax请求后端接口(API)获取并展示数据。

gVerify.js用于验证码的生成和验证。

sweetalert.min.js用于弹窗提示。系统在展示登录、新增、删除、修改、部分查询等结果时，使用该弹窗提示返回结果。

## 写在最后

创作不易，多多支持。