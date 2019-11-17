

DROP TABLE IF EXISTS `uri_account_info`;
CREATE TABLE `uri_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(40)  NOT NULL COMMENT '用户编号(系统内部使用)',
  `account_no` varchar(20)  NOT NULL UNIQUE COMMENT '用户账号（唯一）',
  `account_name` varchar(40)  NOT NULL COMMENT '用户名',
  `account_password` varchar(40)  NOT NULL COMMENT '密码',
  `registration_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户账户表';

DROP TABLE IF EXISTS `uri_user_info`;
CREATE TABLE `uri_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_age` int(4)  NOT NULL COMMENT '用户年龄',
  `user_career` varchar(40)  DEFAULT NULL COMMENT '用户职业',
  `user_name` varchar(40)  DEFAULT NULL COMMENT '用户姓名',
  `user_phone` varchar(40)  DEFAULT NULL COMMENT '用户电话号码',
  `user_address` varchar(100)  DEFAULT NULL COMMENT '用户住址',
  `ever_uri_sick` varchar(40)  DEFAULT NULL COMMENT '用户是否有过患病EverSickEnum(YES,是;NO,否)',
  `registration_time` datetime DEFAULT NULL  COMMENT '患病时间（若有）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本信息表';

DROP TABLE IF EXISTS `uri_user_relation`;
CREATE TABLE `uri_user_relation`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(40)  NOT NULL COMMENT '用户编号(系统内部使用)',
  `user_id` bigint(20)  NOT NULL COMMENT '用户id（与user表关联）',
  `relation_type` varchar(40)  NOT NULL COMMENT '关系类型RelationTypeEnum(PARENTS,父母;SELF,自己;BROTHER,兄弟;SISTER,姐妹;CHILDREN,子女;OTHERS,其他)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户关系表';


DROP TABLE IF EXISTS `uri_check_result`; 
CREATE TABLE `uri_check_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20)  NOT NULL COMMENT '用户id（与user表关联）',
  `check_result` varchar(40)  NOT NULL COMMENT '检测结果',
  `check_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `result_image_path` varchar(40)  NOT NULL COMMENT '检测结果图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='检测结果表';

-- ----------------------------
-- Records of uri_account_info
-- ----------------------------
INSERT INTO `uri_account_info` VALUES ('1', 'CO201911171652', 'admin', 'admin', 'admin', '2019-11-17 16:52:30');

-- ----------------------------
-- Records of uri_check_result
-- ----------------------------
INSERT INTO `uri_check_result` VALUES ('1', '1', '[1,1,1,1,1,1,1,1,1,1,1,1,1,1]', '2019-11-17 16:59:47', 'temp/path/1.png');
INSERT INTO `uri_check_result` VALUES ('2', '1', '[1,1,1,1,1,1,1,1,1,1,1,1,1,1]', '2019-11-17 17:00:15', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('3', '1', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:00:33', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('4', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:00:53', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('5', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:01:06', 'temp/path/2.png');
INSERT INTO `uri_check_result` VALUES ('6', '2', '[1,2,3,6,5,4,8,9,7,4,8,5,4,7]', '2019-11-17 17:01:14', 'temp/path/2.png');

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




