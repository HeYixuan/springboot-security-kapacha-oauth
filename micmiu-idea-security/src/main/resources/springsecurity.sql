/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : springsecurity

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-05-27 14:16:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_resources
-- ----------------------------
DROP TABLE IF EXISTS `system_resources`;
CREATE TABLE `system_resources` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(10) DEFAULT NULL COMMENT '上级资源 父节点ID',
  `name` varchar(36) COLLATE utf8_bin NOT NULL COMMENT '资源名称',
  `action` enum('LINKED','BUTTON','MENU') COLLATE utf8_bin NOT NULL DEFAULT 'LINKED' COMMENT '动作:按钮,菜单',
  `url` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '资源链接地址',
  `descritpion` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `clazz` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj61cu435dgjpl59r5k0rbn4fi` (`parent_id`),
  CONSTRAINT `FKj61cu435dgjpl59r5k0rbn4fi` FOREIGN KEY (`parent_id`) REFERENCES `system_resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统资源表';

-- ----------------------------
-- Records of system_resources
-- ----------------------------
INSERT INTO `system_resources` VALUES ('1', null, '系统管理', 'MENU', '/system/manage', '系统管理', null);
INSERT INTO `system_resources` VALUES ('2', '1', '用户管理', 'MENU', '/systemUser/manage', '用户管理', null);
INSERT INTO `system_resources` VALUES ('3', '2', '用户列表', 'MENU', '/systemUser/getList/**', '用户列表', null);
INSERT INTO `system_resources` VALUES ('4', '1', '角色管理', 'MENU', '/systemRole/manage', '角色管理', null);
INSERT INTO `system_resources` VALUES ('5', '4', '角色列表', 'MENU', '/systemRole/getList/**', '角色列表', null);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `code` varchar(36) COLLATE utf8_bin NOT NULL COMMENT '编码',
  `name` varchar(36) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'C001', 'ROLE_ADMIN');
INSERT INTO `system_role` VALUES ('2', 'C002', 'ROLE_DBA');
INSERT INTO `system_role` VALUES ('3', 'C003', 'ROLE_USER');
INSERT INTO `system_role` VALUES ('4', 'C004', 'ROLE_ANON');

-- ----------------------------
-- Table structure for system_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `system_role_resource`;
CREATE TABLE `system_role_resource` (
  `system_resource_id` int(10) NOT NULL,
  `system_role_id` int(10) NOT NULL,
  PRIMARY KEY (`system_resource_id`,`system_role_id`),
  KEY `FK5j9p7h9ei46dh8i5v9xhv991j` (`system_role_id`),
  CONSTRAINT `FK5j9p7h9ei46dh8i5v9xhv991j` FOREIGN KEY (`system_role_id`) REFERENCES `system_role` (`id`),
  CONSTRAINT `FKafykx3ch5i8c2ndckgkwvgqvo` FOREIGN KEY (`system_resource_id`) REFERENCES `system_resources` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of system_role_resource
-- ----------------------------
INSERT INTO `system_role_resource` VALUES ('1', '1');
INSERT INTO `system_role_resource` VALUES ('3', '1');
INSERT INTO `system_role_resource` VALUES ('5', '1');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(36) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `email` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `phone` bigint(11) DEFAULT NULL COMMENT '用户手机号',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账户是否过期',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '账户是否锁定',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '凭证是否过期',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '账户启用状态',
  `last_time` timestamp NULL DEFAULT NULL COMMENT '用户最后登录时间',
  `attempts` int(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK74y7xiqrvp39wycn0ron4xq4h` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', '$2a$10$xJHQtZV2Dy4sq4bkitCJNeygXbW6mFaLhjT12GS2kxNCBvAP21Bd6', '15517551511@126.com', '15218725510', '1', '0', '1', '1', '2017-05-16 08:39:12', '3');
INSERT INTO `system_user` VALUES ('2', 'HeYixuan', '$2a$10$eRmIB.z4LUJ40N3NQ99y4u.ie2oDXjEO0e6zlJsLU/3IGwZa36FWW', '15517551511@126.com', '15218725510', '1', '1', '1', '1', '2017-04-28 10:26:08', '3');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `system_user_id` int(10) NOT NULL,
  `system_role_id` int(10) NOT NULL,
  PRIMARY KEY (`system_user_id`,`system_role_id`),
  KEY `FK8rnw9xngs7ib3ll8rm6v6nq17` (`system_role_id`),
  CONSTRAINT `FK5soqc4583re86busn1idb194d` FOREIGN KEY (`system_user_id`) REFERENCES `system_user` (`id`),
  CONSTRAINT `FK8rnw9xngs7ib3ll8rm6v6nq17` FOREIGN KEY (`system_role_id`) REFERENCES `system_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('1', '1');
INSERT INTO `system_user_role` VALUES ('2', '3');
