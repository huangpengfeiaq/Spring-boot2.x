# Spring boot模板（2019年度版）

## 0.导读
此模板已集成的模块如下：
1. swagger（图形化测试工具）：
   > 项目启动后在浏览器输入[http://localhost:8088/swagger-ui.html](http://localhost:8088/swagger-ui.html)即可加载。
2. tk.mybatis（通用mapper）：
   > 简化dao/mapper层配置，参考目录com.springboot.framework.dao.mapper下AdminMapper类。
3. redis（非关系型数据库）：
   > 模板内用于储存用户token，参考目录com.springboot.framework.service下RedisService类。
4. ObjectStorage（对象存储服务）：
   > ObjectStorage是一个统称（包含百度云bos、腾讯云cos、阿里云oss、华为云obs），此模板为简化各个厂商不同的sdk配置，特此优化为统一sdk。

## 1.开始使用
使用idea导入项目

## 2.创建数据库
> 方式一：使用图形化工具创建（以Navicat为例）admin表，结构如下：

| 名称 | 类型 | 长度| 小数点 | 不是null | 虚拟| 键 | 注释|
|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|
| id | int | 11| 0 | √ | | 🔑1 | ID号|
| account | varchar | 16| 0 | √ | |  | 账号|
| password | varchar | 32| 0 | √ | |  | 密码|
| phone | varchar | 11| 0 | √ | |  | 手机号|
| name | varchar | 16| 0 | √ | |  | 姓名|
| create_by | varchar | 64| 0 |  | |  | 创建者|
| create_date | timestamp | 0| 0 |  | |  | 创建时间|
| update_by | varchar | 64| 0 |  | |  | 修改者|
| update_date | timestamp | 0| 0 |  | |  | 修改时间|
| status | tinyint | 1| 0 | √ | |  | 状态，-1删除，0禁用，1正常|

特别注意：
+ id->√自动递增
+ create_date->默认：CURRENT_TIMESTAMP
+ update_date->默认：CURRENT_TIMESTAMP√根据当前时间戳更新
+ status->默认：1
------------------------------------
> 方式二：使用SQL查询语句创建admin表，如下：
```mysql
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，-1已删除，0禁用，1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;
```

## 3.启动项目
运行com.springboot.framework包下启动类ProjectApplication

## 4.测试接口
项目启动后在浏览器输入[http://localhost:8088/swagger-ui.html](http://localhost:8088/swagger-ui.html)即可加载。

## 6.逆向生成数据表
#### 1.更新src/main/resources目录下mbgconfig.xml文件内配置数据表（代码18-24行）参数。参考如下：
```xml
<!-- JDBC 连接信息（需更新以下三个参数）：1.connectionURL 2.userId= 3.password -->
<jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                connectionURL="数据库连接语句"
                userId="数据库连接用户名" password="数据库连接密码">
        <!-- 针对mysql数据库 -->
        <property name="useInformationSchema" value="true"></property>
</jdbcConnection>
```
#### 2.更新（代码50-54行）参数。参考如下：
```xml
<!-- 管理员表（需更新以下两个参数）：1.tableName 2.domainObjectName -->
<table tableName="MySQL数据库表名称" domainObjectName="逆向生成的pojo类名"
        enableCountByExample="false" enableUpdateByExample="false"
        enableDeleteByExample="false" enableSelectByExample="false"
        selectByExampleQueryId="false"></table>
```
特别注意：
+ 默认pojo类生成路径为com.springboot.framework.dao.pojo包下（代码29行）
#### 3.启动CreateUtil类：
```
运行com.springboot.framework.utils下CreateUtil类
```
特别注意：
+ 单独运行CreateUtil.main()方法后，注意把项目启动类更新回ProjectApplication
#### 4.pojo类配置映射，参考如下：
```java
/**
 * 以下4个注解详解
 * 1.@Data lombok插件，会在编译时自动生成get、set、toString方法（简化代码）
 * 2.@Table(name = "数据库表名") 与数据表绑定，若类名与数据表名遵循驼峰规则对应，可省略此参数
 * 3.@Id 与数据表主键绑定
 * 4.@Transient 冗余字段，额外参数（即数据表不存在的字段）
 */
@Data
@Table(name = "sys_admin")
public class Admin implements Serializable {
    @Id
    private Integer id;
    private String account;
    private String password;
    private String phone;
    private String name;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    @Transient
    private String accessToken;
}
```
#### 5.mapper类配置映射：
在配置传统mybatis映射时，一般为mapper类与xml文件对应。此框架加入tk.mybatis插件，无需xml文件。
1. 删除xml文件包（删除src/main/resources目录下mapper包）
2. 配置mapper接口，参考如下：
```java
/**
 * 1.继承tk.mybatis.mapper.common.Mapper接口（extends Mapper<pojo类名>），
 *   此时已可以直接在Service层直接调用mapper方法，具体方法参考文档https://blog.csdn.net/sinat_38419207/article/details/82907387。
 * 2.以注解方式自定义SQL语句（@Select、@Update、@Insert、@Delete），参考如下登陆接口。
 */
public interface AdminMapper extends Mapper<Admin> {
    /**
     * 登陆
     *
     * @param loginKey 管理员用户名或手机号
     * @param password 密码
     * @return 管理员
     */
    @Select("SELECT * FROM sys_admin WHERE status != -1 AND (phone = #{loginKey} OR account = #{loginKey}) AND password = #{password}")
    Admin login(@Param("loginKey") String loginKey, @Param("password") String password);
    //......
   }
```

## 7.对象存储服务配置
#### 1.引入依赖
在pom.xml文件中引入对应服务商的对象存储依赖，参数如下（以腾讯cos为例，代码179-184行）：
```xml
<!-- 腾讯云COS对象存储 -->
<dependency>
    <groupId>com.qcloud</groupId>
    <artifactId>cos_api</artifactId>
    <version>5.5.7</version>
</dependency>
```
#### 2.更新yaml配置文件
更新src/main/resources目录下application.yml文件内配置object-storage（代码50-54行）参数。参考如下：
```yaml
# 对象存储配置
object-storage:
  accessKeyId: yourAccessKeyId
  accessKeySecret: yourAccessKeySecret
  upload-endpoint: yourEndpoint
  download-endpoint: yourEndpoint
  bucketName: yourBucketName
```
#### 3.ObjectStorage继承对应厂商对象存储类
更新com.springboot.framework.model目录下三个ObjectStorage开头的类，参考如下（以ObjectStorageClient为例）：
```java
/**
 * 1.继承对应厂商对象存储客户机类（extends 对应厂商Client）
 * 2.ObjectStorageObject与ObjectStorageObjectMetadata类也如此即可。
 */
public class ObjectStorageClient extends COSClient {
    /**
     * 自定义构造方法（腾讯云COS）
     */
    public ObjectStorageClient(String secretId, String secretKey, String endpoint) {
        this(new BasicCOSCredentials(secretId, secretKey), new ClientConfig(new Region(endpoint)));
    }

    public ObjectStorageClient(COSCredentials cred, ClientConfig clientConfig) {
        super(cred, clientConfig);
    }
    
    public ObjectStorageClient(COSCredentialsProvider credProvider, ClientConfig clientConfig) {
        super(credProvider, clientConfig);
    }
}
```

## 9.关于
截至目前最后的大版本更新时间为2019年7月16日。
上次大版本更新时间为2019年5月14日。

发布于2019年2月份，使用此模板前请先导入数据库，目录下springboot2019.sql文件。
若要用于个人学习或商用，请删除数据库中除sys_admin以外的所有数据表，以免干扰个人的使用。

欢迎各位使用此模板学习或是开发，欢迎各位提交分支参与此模板的优化。

问题与建议请联系邮箱：641655770@qq.com
其他联系方式：
* WeChat：641655770
* QQ：641655770
