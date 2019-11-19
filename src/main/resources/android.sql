/*
Navicat MySQL Data Transfer

Source Server         : jd
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : android

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-11-19 10:32:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uri_account_info
-- ----------------------------
DROP TABLE IF EXISTS `uri_account_info`;
CREATE TABLE `uri_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户编号(系统内部使用)',
  `account_no` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户账号（唯一）',
  `account_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `account_password` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `registration_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_no` (`account_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户账户表';

-- ----------------------------
-- Records of uri_account_info
-- ----------------------------
INSERT INTO `uri_account_info` VALUES ('1', 'CO201911171652', 'admin', 'admin', 'admin', '2019-11-17 16:52:30');
INSERT INTO `uri_account_info` VALUES ('2', 'ACCOUNT1574071578961', '530454062', '530', '222', '2019-11-18 18:06:19');
INSERT INTO `uri_account_info` VALUES ('3', 'ACCOUNT1574129171243', 'proident nisi', 'ad', 'sed do ea', '2019-11-19 10:06:11');

-- ----------------------------
-- Table structure for uri_check_result
-- ----------------------------
DROP TABLE IF EXISTS `uri_check_result`;
CREATE TABLE `uri_check_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id（与user表关联）',
  `check_result` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '检测结果',
  `check_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `result_image_path` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '检测结果图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='检测结果表';

-- ----------------------------
-- Records of uri_check_result
-- ----------------------------
INSERT INTO `uri_check_result` VALUES ('1', '1', '[1,1,1,1,1,1,1,1,1,1,1,1,1,1]', '2019-11-17 16:59:47', 'temp/path/1.png');
INSERT INTO `uri_check_result` VALUES ('2', '1', '[1,1,1,1,1,1,1,1,1,1,1,1,1,1]', '2019-11-17 17:00:15', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('3', '1', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:00:33', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('4', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:00:53', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('5', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:01:06', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('6', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:01:14', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('7', '13', 'test result', '2019-11-19 10:23:48', 'C:\\Users\\lijiao73\\Desktop\\tempFile\\file\\0fab1b71-19e1-49f0-8ea9-0b407dc02110.jpg');

-- ----------------------------
-- Table structure for uri_user_info
-- ----------------------------
DROP TABLE IF EXISTS `uri_user_info`;
CREATE TABLE `uri_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_age` int(4) NOT NULL COMMENT '用户年龄',
  `user_career` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '用户职业',
  `user_name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `user_phone` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '用户电话号码',
  `user_address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户住址',
  `ever_uri_sick` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '用户是否有过患病EverSickEnum(YES,是;NO,否)',
  `sick_time` datetime DEFAULT NULL COMMENT '患病时间（若有）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本信息表';

-- ----------------------------
-- Records of uri_user_info
-- ----------------------------
INSERT INTO `uri_user_info` VALUES ('1', '25', '医生', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:55:46');
INSERT INTO `uri_user_info` VALUES ('2', '38', '研究院', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:55:52');
INSERT INTO `uri_user_info` VALUES ('3', '36', '银行职员', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:56:52');
INSERT INTO `uri_user_info` VALUES ('4', '19', '学生', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:56:55');
INSERT INTO `uri_user_info` VALUES ('5', '15', '学生', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:56:57');
INSERT INTO `uri_user_info` VALUES ('6', '24', '护士', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:57:00');
INSERT INTO `uri_user_info` VALUES ('7', '2', '无', '张武', '18969396874', '逸夫楼505', 'NO', '2019-11-17 16:57:02');
INSERT INTO `uri_user_info` VALUES ('8', '80', '无', '张武', '18969396874', '逸夫楼505', 'YES', '2019-11-17 16:57:05');
INSERT INTO `uri_user_info` VALUES ('12', '20', '谷歌', 'nisi', 'Ut dolorlaboris', 'ut do non', 'NO', null);
INSERT INTO `uri_user_info` VALUES ('13', '20', '谷歌', 'nisi', 'Ut dolorlaboris', 'ut do non', 'NO', null);

-- ----------------------------
-- Table structure for uri_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `uri_user_relation`;
CREATE TABLE `uri_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户编号(系统内部使用)',
  `user_id` bigint(20) NOT NULL COMMENT '用户id（与user表关联）',
  `relation_type` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '关系类型RelationTypeEnum(PARENTS,父母;SELF,自己;BROTHER,兄弟;SISTER,姐妹;CHILDREN,子女;OTHERS,其他)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户关系表';

-- ----------------------------
-- Records of uri_user_relation
-- ----------------------------
INSERT INTO `uri_user_relation` VALUES ('1', 'CO201911171652', '1', 'SELF');
INSERT INTO `uri_user_relation` VALUES ('2', 'CO201911171652', '2', 'FATHER');
INSERT INTO `uri_user_relation` VALUES ('3', 'CO201911171652', '3', 'MOTHER');
INSERT INTO `uri_user_relation` VALUES ('4', 'CO201911171652', '4', 'BROTHER');
INSERT INTO `uri_user_relation` VALUES ('5', 'CO201911171652', '5', 'SISTER');
INSERT INTO `uri_user_relation` VALUES ('6', 'CO201911171652', '6', 'SPOUSE');
INSERT INTO `uri_user_relation` VALUES ('7', 'CO201911171652', '7', 'CHILDREN');
INSERT INTO `uri_user_relation` VALUES ('8', 'CO201911171652', '8', 'OTHERS');
INSERT INTO `uri_user_relation` VALUES ('9', 'ACCOUNT1574129171243', '12', 'SISTER');
INSERT INTO `uri_user_relation` VALUES ('10', 'ACCOUNT1574129171243', '13', 'SISTER');
