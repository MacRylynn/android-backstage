

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

DROP TABLE IF EXISTS `uri_check_result`;
CREATE TABLE `uri_check_result` (
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




