# springboot模板（2019年度版）

## 0.前言
此模板已集成的模块如下
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
```
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
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，-1已删除，0禁用，1正常，8超级管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;
```

## 3.启动项目



## 7.逆向生成数据表



## 9.关于
截至目前最后的大版本更新时间为2019年7月16日。
上次大版本更新时间为2019年5月14日。

发布于2019年2月份，使用此模板前请先导入数据库，目录下springboot2019.sql文件。
若要用于个人学习或商用，请删除数据库中除sys_admin以外的所有数据表，以免干扰个人的使用。

欢迎各位使用此模板学习或是开发，欢迎各位提交分支参与此模板的优化。

其他问题与建议请联系邮箱：641655770@qq.com
