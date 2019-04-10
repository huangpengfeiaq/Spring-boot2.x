/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : liangying

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/03/2019 11:57:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_admin
-- ----------------------------
DROP TABLE IF EXISTS `log_admin`;
CREATE TABLE `log_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '日志标题',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求URI',
  `method` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作方式',
  `request_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作提交的数据',
  `response` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '回调说明',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_user
-- ----------------------------
DROP TABLE IF EXISTS `log_user`;
CREATE TABLE `log_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '日志标题',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求URI',
  `method` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作方式',
  `request_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作提交的数据',
  `response` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '回调说明',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户操作日志表（查）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_user_login
-- ----------------------------
DROP TABLE IF EXISTS `log_user_login`;
CREATE TABLE `log_user_login`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作IP地址',
  `params_phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求手机号',
  `request_params` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求参数',
  `response` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '回调说明',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户登录操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_user_sms
-- ----------------------------
DROP TABLE IF EXISTS `log_user_sms`;
CREATE TABLE `log_user_sms`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（1：登录  2：注册  3：修改密码）',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '日志标题',
  `params_phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求手机号',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求URI',
  `method` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作方式',
  `request_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作提交的数据',
  `response` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '回调说明',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '短信日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_user_update
-- ----------------------------
DROP TABLE IF EXISTS `log_user_update`;
CREATE TABLE `log_user_update`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '日志类型',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '日志标题',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '操作IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '请求URI',
  `request_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作提交的数据',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户更新操作日志表（改）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
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

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, '111', '111', '111', '胡一天', NULL, '2019-03-14 09:26:38', '周恩来', '2019-03-15 11:11:43', 0);
INSERT INTO `sys_admin` VALUES (2, '222', '222', '222', '周恩来', NULL, '2019-03-15 10:26:02', '周恩来', '2019-03-15 11:56:17', -1);
INSERT INTO `sys_admin` VALUES (3, '333', '333', '333', '发哥', '胡一天', '2019-03-15 11:50:24', NULL, '2019-03-15 11:51:31', 1);
INSERT INTO `sys_admin` VALUES (4, '41', '43', '44', '42', '发哥', '2019-03-15 11:52:02', NULL, '2019-03-15 11:52:02', 1);

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，-1已删除，1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_earn
-- ----------------------------
DROP TABLE IF EXISTS `tb_earn`;
CREATE TABLE `tb_earn`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `superior_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级ID',
  `subordinate_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下级ID',
  `subordinate_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下级手机号',
  `earn_money` decimal(10, 2) NOT NULL COMMENT '收益金额',
  `loan_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '贷款金额',
  `percentage` decimal(4, 4) NULL DEFAULT NULL COMMENT '百分比',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '收益类型(1,贷款收益，2介绍信贷员收益)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收益流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_information
-- ----------------------------
DROP TABLE IF EXISTS `tb_information`;
CREATE TABLE `tb_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面图',
  `category` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `introduction` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `read` int(8) NULL DEFAULT NULL COMMENT '阅读数',
  `like` int(8) NULL DEFAULT NULL COMMENT '点赞数',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，-1，删除，0-禁用，1-正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资讯表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_information_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_information_info`;
CREATE TABLE `tb_information_info`  (
  `information_id` int(11) NOT NULL COMMENT '资讯ID',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章起始图',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  PRIMARY KEY (`information_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资讯详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_loan
-- ----------------------------
DROP TABLE IF EXISTS `tb_loan`;
CREATE TABLE `tb_loan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '贷款用户id',
  `money` decimal(10, 2) NOT NULL COMMENT '贷款金额',
  `house_type` tinyint(1) NULL DEFAULT NULL COMMENT '名下房产类型',
  `car_type` tinyint(1) NULL DEFAULT NULL COMMENT '名下是否有车',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，-1，删除，1-提交申请，2-审核中，3-已通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '贷款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_loan_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_loan_info`;
CREATE TABLE `tb_loan_info`  (
  `loan_id` int(11) NOT NULL COMMENT '贷款记录ID',
  `house_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产所在位置',
  `house_master` tinyint(1) NULL DEFAULT NULL COMMENT '是否是主贷人',
  `house_time` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产月供时长',
  `house_monthly_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产月供金额',
  `house file` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '房产证明文件',
  `car_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌所在地',
  `car_year` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆使用年限',
  `car_monthly_amount` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车产月供金额',
  `car_file` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '车产证明文件',
  PRIMARY KEY (`loan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '贷款详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_slideshow
-- ----------------------------
DROP TABLE IF EXISTS `tb_slideshow`;
CREATE TABLE `tb_slideshow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `information_id` int(11) NULL DEFAULT NULL COMMENT '资讯id',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '账号状态，-1，删除，0-禁用，1-正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `id_number` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `wechat` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请人id',
  `Invite_number` int(4) NULL DEFAULT NULL COMMENT '共邀请用户数',
  `earn` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总收益',
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '可提现余额',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账号状态，-1，删除，0-禁用，1-正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `profession` tinyint(1) NULL DEFAULT NULL COMMENT '职业身份(上班族、个体户、企业主、无固定职业) ',
  `accumulation_fund` tinyint(1) NULL DEFAULT NULL COMMENT '是否有本地公积金',
  `social_security` tinyint(1) NULL DEFAULT NULL COMMENT '是否有本地社保',
  `commercial_insurance` tinyint(1) NULL DEFAULT NULL COMMENT '是否有商业保险',
  `credit` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信用情况',
  `id_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照',
  `id_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面照',
  `loan_officer_file` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提供信贷员资料',
  `bind` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定银行账户',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `tb_withdrawal`;
CREATE TABLE `tb_withdrawal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '提现金额',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '提现状态，1-已提交，2-已打款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提现流水表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
