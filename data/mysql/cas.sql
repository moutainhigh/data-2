/*
Navicat MySQL Data Transfer

Source Server         : data
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : cas

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-09-02 23:23:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` bigint(20) NOT NULL,
  `dept_name` varchar(30) DEFAULT NULL COMMENT '单位名称',
  `dept_code` varchar(255) DEFAULT NULL COMMENT '单位简称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级单位ID',
  `dept_phone` varchar(11) DEFAULT NULL COMMENT '单位电话',
  `dept_address` varchar(200) DEFAULT NULL COMMENT '单位地址',
  `description` varchar(255) DEFAULT NULL COMMENT '单位简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remove_time` datetime DEFAULT NULL COMMENT '删除时间',
  `operate_user_id` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remove_time` datetime DEFAULT NULL COMMENT '删除时间',
  `operate_user_id` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(30) DEFAULT NULL,
  `role_code` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remove_time` datetime DEFAULT NULL COMMENT '删除时间',
  `operate_user_id` bigint(20) NOT NULL COMMENT '操作用户ID',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`operate_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(16) NOT NULL COMMENT '登录名：不可重复',
  `username` varchar(16) NOT NULL COMMENT '用户真实姓名',
  `password` varchar(16) NOT NULL COMMENT '用户密码',
  `dept_id` bigint(20) NOT NULL COMMENT '单位ID',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remove_time` datetime DEFAULT NULL COMMENT '删除时间',
  `operate_user_id` bigint(20) NOT NULL COMMENT '操作用户ID',
  `status` int(1) DEFAULT NULL COMMENT '状态：0为活跃用户；1为禁用用户；2为删除用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '123456', '1', '18500758641', 'houphiler@sina.com', '2018-09-02 19:22:31', null, null, '1', '0');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `id_code` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `sex` int(1) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `positional` varchar(30) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
