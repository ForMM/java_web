/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : ct_owl

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-06-24 10:55:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_advice`
-- ----------------------------
DROP TABLE IF EXISTS `t_advice`;
CREATE TABLE `t_advice` (
  `n_advice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '意见反馈ID',
  `n_user_id` bigint(20) DEFAULT NULL COMMENT '意见反馈人员ID',
  `n_user_name` varchar(20) DEFAULT NULL COMMENT '意见反馈人员姓名(客户端会传给服务器)',
  `n_user_phone` varchar(20) DEFAULT NULL COMMENT '反馈人员手机号码(客户端会传给服务器)',
  `c_advice_content` varchar(1000) DEFAULT '' COMMENT '意见反馈内容',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_advice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_advice
-- ----------------------------
INSERT INTO `t_advice` VALUES ('1', '1', '清哥哥', '18320792425', 'aaaaa', '2016-06-16 09:52:35', '2016-06-16 09:52:35');
INSERT INTO `t_advice` VALUES ('2', '1', '清哥哥', '18320792425', 'cccc', '2016-06-16 09:55:38', '2016-06-16 09:55:38');
INSERT INTO `t_advice` VALUES ('3', '1', 'asfd', 'asdf', 'asdf', '2016-06-16 09:58:24', '2033-12-02 00:00:00');

-- ----------------------------
-- Table structure for `t_bpimg`
-- ----------------------------
DROP TABLE IF EXISTS `t_bpimg`;
CREATE TABLE `t_bpimg` (
  `n_bpimg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `n_project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `c_bpimg_addr` varchar(200) DEFAULT NULL COMMENT '图片保存地址',
  `n_bpimg_index` bigint(20) NOT NULL COMMENT '图片保存顺序',
  PRIMARY KEY (`n_bpimg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bpimg
-- ----------------------------

-- ----------------------------
-- Table structure for `t_click`
-- ----------------------------
DROP TABLE IF EXISTS `t_click`;
CREATE TABLE `t_click` (
  `n_click_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞记录ID',
  `n_click_type` tinyint(1) NOT NULL COMMENT '点赞类型：0评论1新闻',
  `n_target_id` bigint(20) DEFAULT NULL COMMENT '点赞目标ID',
  `n_user_id` bigint(20) NOT NULL COMMENT '点赞人员ID',
  `t_create_time` timestamp NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`n_click_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_click
-- ----------------------------
INSERT INTO `t_click` VALUES ('1', '0', '1', '9', '2016-06-22 11:15:04');
INSERT INTO `t_click` VALUES ('2', '1', '1', '9', '2016-06-22 15:36:55');
INSERT INTO `t_click` VALUES ('3', '0', '3', '9', '2016-06-22 15:48:40');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `n_comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论表主键ID',
  `c_comment_content` varchar(500) NOT NULL DEFAULT '' COMMENT '评论内容',
  `c_comment_type` int(2) NOT NULL COMMENT '评论类型0，项目评论1，新闻评论',
  `n_target_id` bigint(20) DEFAULT NULL COMMENT '评论对象ID',
  `n_click_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `n_user_id` int(20) NOT NULL COMMENT '评论人员ID',
  `t_comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `n_status` tinyint(1) DEFAULT '1' COMMENT '有效表示0无效1有效',
  `c_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`n_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '测试评论！', '0', '1', '0', '9', '2016-06-22 10:00:24', '1', null);
INSERT INTO `t_comment` VALUES ('2', '测试评论！', '0', '1', '0', '9', '2016-06-22 10:00:55', '1', null);
INSERT INTO `t_comment` VALUES ('3', '新闻评价', '1', '1', '0', '9', '2016-06-22 15:40:28', '1', null);

-- ----------------------------
-- Table structure for `t_edu_experince`
-- ----------------------------
DROP TABLE IF EXISTS `t_edu_experince`;
CREATE TABLE `t_edu_experince` (
  `n_edu_experince_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '经历ID',
  `n_team_id` bigint(20) DEFAULT NULL COMMENT '团队成员ID',
  `c_school_name` varchar(100) DEFAULT NULL COMMENT '根据类型：若为工作经历，此字段表示公司名称，若为教育经历，此字段表示学校名称',
  `c_major_name` varchar(100) DEFAULT NULL COMMENT '专业名称',
  `t_begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `t_end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`n_edu_experince_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_edu_experince
-- ----------------------------
INSERT INTO `t_edu_experince` VALUES ('1', '1', '清华大学', '计算机', '2016-06-23 11:09:15', '2016-06-29 11:11:45');

-- ----------------------------
-- Table structure for `t_identify`
-- ----------------------------
DROP TABLE IF EXISTS `t_identify`;
CREATE TABLE `t_identify` (
  `n_identify_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投资者职业认证',
  `n_user_id` bigint(20) NOT NULL COMMENT '投资者ID',
  `c_identify_info` varchar(100) DEFAULT '' COMMENT '认证信息图片',
  `n_identify_result` tinyint(1) DEFAULT '0' COMMENT '认证结果：0发起认证，1取消认证，2认证中、3认证失败、4认证成功',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_identify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_identify
-- ----------------------------
INSERT INTO `t_identify` VALUES ('1', '1', '', '0', '2016-04-09 22:41:35', '2016-04-09 22:41:39');

-- ----------------------------
-- Table structure for `t_industry`
-- ----------------------------
DROP TABLE IF EXISTS `t_industry`;
CREATE TABLE `t_industry` (
  `n_industry_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '行业表主键',
  `c_industry_name` varchar(20) DEFAULT '' COMMENT '行业名称',
  `n_industry_index` bigint(20) DEFAULT NULL COMMENT '行业排序ID',
  `c_industry_icon` varchar(200) DEFAULT '' COMMENT '行业图标地址',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_industry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_industry
-- ----------------------------
INSERT INTO `t_industry` VALUES ('1', '互联网', '1', 'http://112.74.67.239:8080/downloadFile?filename=hlw@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('2', '电子商务', '2', 'http://112.74.67.239:8080/downloadFile?filename=dzsw@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('3', 'O2O', '3', 'http://112.74.67.239:8080/downloadFile?filename=O2O@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('4', '社交', '4', 'http://112.74.67.239:8080/downloadFile?filename=sj@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('5', '游戏', '5', 'http://112.74.67.239:8080/downloadFile?filename=yx@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('6', '音乐视频', '6', 'http://112.74.67.239:8080/downloadFile?filename=yysp@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('7', '互联网+', '7', 'http://112.74.67.239:8080/downloadFile?filename=hlwplus@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('8', '金融', '8', 'http://112.74.67.239:8080/downloadFile?filename=jr@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('9', '体育', '9', 'http://112.74.67.239:8080/downloadFile?filename=ty@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('10', '智能硬件', '10', 'http://112.74.67.239:8080/downloadFile?filename=znyj@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('11', '生活服务', '11', 'http://112.74.67.239:8080/downloadFile?filename=shfw@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('12', '文化传媒', '12', 'http://112.74.67.239:8080/downloadFile?filename=whcm@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('13', '教育培训', '13', 'http://112.74.67.239:8080/downloadFile?filename=jypx@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('14', '医疗生物', '14', 'http://112.74.67.239:8080/downloadFile?filename=ylsw@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('15', '电子通信', '15', 'http://112.74.67.239:8080/downloadFile?filename=dztx@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('16', '新材料', '16', 'http://112.74.67.239:8080/downloadFile?filename=xcl@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('17', '化工能源', '17', 'http://112.74.67.239:8080/downloadFile?filename=hgny@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('18', '高端装备智造', '18', 'http://112.74.67.239:8080/downloadFile?filename=gdzbzz@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('19', '房产建筑', '19', 'http://112.74.67.239:8080/downloadFile?filename=fcjz@2x.png&filetype=1', null, null);
INSERT INTO `t_industry` VALUES ('20', '其他', '20', 'http://112.74.67.239:8080/downloadFile?filename=qt@2x.png&filetype=1', null, null);

-- ----------------------------
-- Table structure for `t_license`
-- ----------------------------
DROP TABLE IF EXISTS `t_license`;
CREATE TABLE `t_license` (
  `n_license_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '工商信息表主键',
  `c_company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `t_establish_time` timestamp NULL DEFAULT NULL COMMENT '成立时间',
  `c_legal_person` varchar(50) DEFAULT NULL COMMENT '法人代表',
  `f_register_money` float DEFAULT NULL COMMENT '注册资金',
  `c_company_addr` varchar(50) DEFAULT NULL COMMENT '企业地址',
  PRIMARY KEY (`n_license_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_license
-- ----------------------------

-- ----------------------------
-- Table structure for `t_manage_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_manage_account`;
CREATE TABLE `t_manage_account` (
  `n_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理系统用户基本信息表主键',
  `c_user_account` varchar(20) NOT NULL COMMENT '用户帐号',
  `c_user_password` varchar(32) DEFAULT NULL COMMENT '用户密码（密码不能明文存储）',
  `c_user_realname` varchar(50) DEFAULT '' COMMENT '用户真实姓名',
  `c_user_portrait` varchar(200) DEFAULT '' COMMENT '用户头像',
  `c_mobile_phone` varchar(15) NOT NULL COMMENT '用户手机号',
  `c_user_email` varchar(30) DEFAULT '' COMMENT '电子邮箱',
  `c_user_company` varchar(100) DEFAULT '' COMMENT '用户公司名称',
  `c_user_position` varchar(100) DEFAULT '' COMMENT '用户职位',
  `t_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `t_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`n_user_id`),
  UNIQUE KEY `帐号` (`c_user_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_manage_account
-- ----------------------------
INSERT INTO `t_manage_account` VALUES ('1', '18664556416', 't8YMMsuG8Bh6s29ZhQd6/w==', '林可珂', '', '18664556413', '6530319878@qq.com', '同为数码', '软件工程师', '2016-04-01 13:51:13', '2016-04-06 10:22:54');
INSERT INTO `t_manage_account` VALUES ('2', '18664556410', 't8YMMsuG8Bh6s29ZhQd6/w==', '克里克', '', '18664556410', '653031578@qq.com1', '阿里巴巴', '软件工程师', '2016-04-01 13:51:13', '2016-04-06 10:22:54');

-- ----------------------------
-- Table structure for `t_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `n_message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `n_message_from` bigint(20) DEFAULT NULL COMMENT '发送者ID',
  `n_message_from_name` varchar(20) DEFAULT NULL COMMENT '发送者名字',
  `n_message_to` bigint(20) DEFAULT NULL COMMENT '接收者ID',
  `n_message_to_name` varchar(20) DEFAULT NULL COMMENT '接收者名字',
  `c_message_content` varchar(5000) DEFAULT '' COMMENT '消息内容',
  PRIMARY KEY (`n_message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for `t_news`
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `n_news_id` bigint(20) NOT NULL COMMENT '新闻表主键ID',
  `c_news_icon` varchar(100) DEFAULT NULL COMMENT '新闻icon',
  `c_news_title` varchar(100) DEFAULT NULL COMMENT '新闻标题',
  `c_news_content` varchar(5000) DEFAULT NULL COMMENT '新闻内容',
  `c_news_from` varchar(200) DEFAULT NULL COMMENT '出版单位名称',
  `t_news_time` timestamp NULL DEFAULT NULL COMMENT '新闻发布时间',
  `c_origin_url` varchar(200) DEFAULT NULL COMMENT '原文地址',
  `c_news_editor` varchar(20) DEFAULT NULL COMMENT '新闻编辑',
  `t_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `c_news_mongo_id` varchar(255) DEFAULT NULL COMMENT 'mongoDB主键',
  `c_industry_names` varchar(200) DEFAULT NULL COMMENT '新闻行业名称：#分割',
  `n_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '有效标示：0无效1有效',
  `c_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`n_news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('1', 'icon', 'title', 'content', 'from', '2016-06-22 15:38:25', 'url', 'editor', '2016-06-22 15:38:28', '12', 'xx', '1', null);

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `n_project_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目基本信息表ID',
  `n_user_id` bigint(20) NOT NULL COMMENT '项目创建者ID',
  `c_project_name` varchar(100) DEFAULT '' COMMENT '项目名称',
  `c_project_brief` varchar(200) DEFAULT NULL COMMENT '项目简述',
  `c_team_tags` varchar(200) DEFAULT NULL COMMENT '项目团队标签',
  `c_team_brief` varchar(200) DEFAULT NULL COMMENT '团队简述',
  `c_addr` varchar(50) DEFAULT '' COMMENT '项目位置信息：北京上海广州深圳',
  `n_is_public` tinyint(1) DEFAULT '1' COMMENT '是否公开0不公开1公开',
  `c_project_introduce` varchar(1000) DEFAULT '' COMMENT '项目详细介绍',
  `c_bpppt_addr` varchar(200) DEFAULT NULL COMMENT 'PPT地址(BP)',
  `c_bpimage_addr` varchar(1000) DEFAULT NULL COMMENT '图片地址列表，JSON数组格式',
  `n_license_id` bigint(20) DEFAULT NULL COMMENT '关联工商信息表',
  `t_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `t_update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `n_interview_count` int(11) DEFAULT '0' COMMENT '项目约谈次数',
  `n_fav_count` int(11) DEFAULT '0' COMMENT '项目收藏次数',
  `n_company_establish` tinyint(1) DEFAULT '0' COMMENT '公司是否成立0未成立1成立',
  `n_team_establish` tinyint(1) DEFAULT '0' COMMENT '团队信息是否完善0未完善1已完善',
  `c_project_icon` varchar(100) DEFAULT NULL COMMENT '项目icon图片地址',
  `n_project_role` tinyint(1) DEFAULT '1' COMMENT '0投资者项目、1创业者项目',
  `n_project_from` tinyint(1) NOT NULL COMMENT '项目来源：0猫头鹰，1数据抓取',
  `c_mongo_id` varchar(50) DEFAULT NULL COMMENT '若来源是数据抓取，此字段存储mongoDB的主键',
  `c_tag_name` varchar(200) NOT NULL COMMENT '项目标签以#分割',
  `f_avg_score` float DEFAULT NULL COMMENT '项目平均得分',
  `c_team_scale` varchar(50) DEFAULT NULL COMMENT '团队规模',
  `c_project_url` varchar(200) DEFAULT NULL COMMENT '项目网址',
  `f_project_score` float DEFAULT NULL COMMENT '项目评分',
  `n_downloadTimes` int(11) DEFAULT NULL COMMENT '下载次数',
  `n_commendTimes` int(11) DEFAULT NULL COMMENT '抓取的评论次数',
  `c_project_imgs` varchar(500) DEFAULT NULL COMMENT '抓取的截图信息，以#分割',
  `n_hot_level` int(11) DEFAULT '0' COMMENT '热门程度:0不热门：大于0为热门程度，数字越大排名越前',
  PRIMARY KEY (`n_project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '11', '项目aa', '阿斯蒂芬', null, '阿斯蒂芬', '北京', '1', '123', null, '[{\"imgIndex\":1,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_1.png&filetype=4\"},{\"imgIndex\":2,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_2.png&filetype=4\"}]', '33', '2016-06-21 14:25:13', '2016-04-03 16:38:23', '2', '0', '0', '0', null, '1', '0', null, '', null, null, null, null, null, null, null, '1');
INSERT INTO `t_project` VALUES ('2', '2', '项目cc', '烦烦烦', null, '烦烦烦', '上海', '1', '11', '11', '[{\"imgIndex\":1,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_1.png&filetype=4\"},{\"imgIndex\":2,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_2.png&filetype=4\"}]', '11', '2016-06-21 14:25:15', '2016-04-03 13:08:12', '1', '1', '0', '0', null, '1', '0', null, '', null, null, null, null, null, null, null, '2');
INSERT INTO `t_project` VALUES ('3', '3', '项目aa', '答复', null, '答复', '深圳', '1', '信息', null, '[{\"imgIndex\":1,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_1.png&filetype=4\"},{\"imgIndex\":2,\"imgPath\":\"http://112.74.67.239:8080/downloadFile?filename=7234f0fa80d3477bb0352d636c43f8b6_2.png&filetype=4\"}]', '0', '2016-06-21 14:25:32', '2016-04-12 21:25:20', '0', '0', '0', '0', null, '0', '0', null, '', null, null, null, null, null, null, null, '3');

-- ----------------------------
-- Table structure for `t_project_browse`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_browse`;
CREATE TABLE `t_project_browse` (
  `n_project_record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目浏览记录',
  `n_user_id` bigint(20) NOT NULL COMMENT '投资者ID',
  `c_fav_projectId` bigint(20) NOT NULL COMMENT '项目ID',
  `t_create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_project_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_browse
-- ----------------------------
INSERT INTO `t_project_browse` VALUES ('3', '1', '2', '2016-04-14 22:20:23');
INSERT INTO `t_project_browse` VALUES ('4', '1', '1', '2016-04-14 22:21:12');

-- ----------------------------
-- Table structure for `t_project_fav`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_fav`;
CREATE TABLE `t_project_fav` (
  `n_project_record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目收藏记录',
  `n_user_id` bigint(20) NOT NULL COMMENT '投资者ID',
  `c_fav_projectId` bigint(20) NOT NULL COMMENT '项目ID',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_project_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_fav
-- ----------------------------
INSERT INTO `t_project_fav` VALUES ('3', '1', '2', '2016-04-14 22:32:38', '2016-04-14 22:32:38');

-- ----------------------------
-- Table structure for `t_project_file`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_file`;
CREATE TABLE `t_project_file` (
  `n_project_file_id` bigint(20) NOT NULL COMMENT '项目文件ID',
  `n_project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `n_file_type` tinyint(1) DEFAULT NULL COMMENT '文件类型',
  `n_file_size` bigint(20) DEFAULT NULL COMMENT '文件大小，单位byte',
  `c_file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `n_user_id` bigint(20) DEFAULT NULL COMMENT '创建人员ID',
  `t_create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`n_project_file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_file
-- ----------------------------

-- ----------------------------
-- Table structure for `t_project_finance`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_finance`;
CREATE TABLE `t_project_finance` (
  `n_project_finance_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目融资记录',
  `n_project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `c_stage_name` varchar(100) DEFAULT NULL COMMENT '阶段名称',
  `c_finance_from` varchar(50) DEFAULT NULL COMMENT '投资方',
  `f_finance_money` float DEFAULT NULL COMMENT '融资金额',
  `t_finance_time` timestamp NULL DEFAULT NULL COMMENT '融资时间',
  `n_user_id` bigint(20) DEFAULT NULL COMMENT '创建人员',
  `t_create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `n_status` tinyint(1) DEFAULT '1' COMMENT '有效标示0无效1有效',
  `c_finance_mongo_id` varchar(255) DEFAULT NULL COMMENT 'mongoDB主键',
  `c_msg_from` varchar(255) DEFAULT NULL COMMENT '信息来源',
  PRIMARY KEY (`n_project_finance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_finance
-- ----------------------------
INSERT INTO `t_project_finance` VALUES ('1', '1', 'A轮', '烦烦烦', '100', '2016-06-21 14:32:51', '1', '2016-06-21 14:32:57', '1', 'sadf123', 'x');
INSERT INTO `t_project_finance` VALUES ('2', '1', 'B轮', '京津冀', '1000', '2016-06-21 14:35:03', '1', '2016-06-21 14:35:06', '1', '123', 'asdf');
INSERT INTO `t_project_finance` VALUES ('3', '1', 'C轮', '对对对', '10000', '2016-06-21 14:35:13', '1', '2016-06-21 14:35:16', '1', '456', '456');
INSERT INTO `t_project_finance` VALUES ('4', '2', '天使轮', '啊啊啊', '143', '2016-06-21 14:36:14', '1', '2016-06-21 14:36:17', '1', '23', '234');
INSERT INTO `t_project_finance` VALUES ('5', '2', 'A轮', '啧啧啧', '122', '2016-06-21 14:36:50', '1', '2016-06-21 14:36:53', '1', '234', '234');
INSERT INTO `t_project_finance` VALUES ('6', '3', 'B轮', '123', '222', '2016-06-21 14:37:23', '1', '2016-06-21 14:37:28', '1', '3233', 'dd');

-- ----------------------------
-- Table structure for `t_project_industry`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_industry`;
CREATE TABLE `t_project_industry` (
  `n_project_industry_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目行业关系表ID',
  `n_project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `n_industry_id` bigint(20) NOT NULL COMMENT '行业ID',
  `t_create_time` time NOT NULL COMMENT '创建时间',
  `n_user_id` bigint(20) NOT NULL COMMENT '创建人员',
  PRIMARY KEY (`n_project_industry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_industry
-- ----------------------------
INSERT INTO `t_project_industry` VALUES ('1', '1', '1', '14:52:10', '9');

-- ----------------------------
-- Table structure for `t_project_interview`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_interview`;
CREATE TABLE `t_project_interview` (
  `n_project_interview_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目约谈记录',
  `n_user_id` bigint(20) NOT NULL COMMENT '投资者ID',
  `n_project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_project_interview_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_interview
-- ----------------------------
INSERT INTO `t_project_interview` VALUES ('6', '1', '1', '2016-04-08 00:45:13', '2016-04-08 00:45:13');

-- ----------------------------
-- Table structure for `t_project_receive`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_receive`;
CREATE TABLE `t_project_receive` (
  `n_project_record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目接收记录',
  `n_user_id` bigint(20) NOT NULL COMMENT '投资者ID',
  `c_fav_projectId` bigint(20) NOT NULL COMMENT '项目ID',
  `t_receive_time` timestamp NULL DEFAULT NULL COMMENT '收到时间',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  `n_isRead` tinyint(1) DEFAULT '0' COMMENT '0未读1已读',
  PRIMARY KEY (`n_project_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_receive
-- ----------------------------

-- ----------------------------
-- Table structure for `t_project_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_score`;
CREATE TABLE `t_project_score` (
  `n_project_score_id` bigint(20) NOT NULL,
  `n_project_id` bigint(20) NOT NULL,
  `f_score` float DEFAULT NULL COMMENT '项目得分',
  `n_user_id` bigint(20) DEFAULT NULL,
  `t_create_time` timestamp NULL DEFAULT NULL COMMENT '评分时间',
  `n_status` tinyint(1) DEFAULT '1' COMMENT '有效标识0无效1有效',
  PRIMARY KEY (`n_project_score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_score
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recruit`
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit`;
CREATE TABLE `t_recruit` (
  `n_recruit_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '招聘表主键',
  `n_project_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `c_recruit_addr` varchar(100) DEFAULT NULL COMMENT '招聘地区',
  `n_recruit_sex` tinyint(1) DEFAULT NULL,
  `c_recruit_name` varchar(50) DEFAULT NULL COMMENT '招聘对象姓名',
  `t_recruit_time` timestamp NULL DEFAULT NULL COMMENT '招聘时间',
  `c_recruit_type` varchar(50) DEFAULT NULL COMMENT '职位类别（技术，设计等等）',
  `c_education` varchar(50) DEFAULT NULL COMMENT '学历',
  `t_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `c_recruit_from` varchar(100) DEFAULT NULL COMMENT '招聘来源',
  `n_status` tinyint(1) DEFAULT '1' COMMENT '有效标示0无效1有效',
  `c_recruit_mongo_id` varchar(255) NOT NULL COMMENT '对应mongoDB的主键',
  PRIMARY KEY (`n_recruit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recruit
-- ----------------------------

-- ----------------------------
-- Table structure for `t_service_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_service_detail`;
CREATE TABLE `t_service_detail` (
  `n_service_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '服务交易明细表ID',
  `n_customer_id` bigint(20) DEFAULT NULL COMMENT '创业者ID',
  `n_investor_id` bigint(20) DEFAULT NULL COMMENT '投资人USER_ID',
  `c_services_name` varchar(50) DEFAULT NULL COMMENT '见面约谈 或 语音通话',
  `c_services_welfare` tinyint(1) DEFAULT NULL COMMENT '0、不做公益1、做公益',
  `n_service_status` tinyint(1) DEFAULT NULL COMMENT '服务状态：0、待处理1、已完成',
  `f_score` float DEFAULT NULL COMMENT '本次服务评分',
  `f_service_price` float DEFAULT NULL COMMENT '本次服务价格',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` date DEFAULT NULL,
  `c_trade_no` varchar(50) DEFAULT NULL COMMENT '交易号',
  `n_trade_type` tinyint(1) DEFAULT NULL COMMENT '交易类型：-1免费0支付宝支付1微信支付',
  PRIMARY KEY (`n_service_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_service_detail
-- ----------------------------
INSERT INTO `t_service_detail` VALUES ('1', '1', '5', '语音通话', '1', '1', '5', '123456', '2016-04-09 20:05:11', '2016-04-09', null, null);
INSERT INTO `t_service_detail` VALUES ('2', '17', '15', '见面约谈', '1', '1', null, '3000', '2016-04-09 20:07:01', '2016-04-09', null, null);
INSERT INTO `t_service_detail` VALUES ('3', '21', '15', '语音通话', '1', '0', null, '0.01', '2016-04-18 15:18:48', '2016-04-18', '2016041821001004900231816570', '0');
INSERT INTO `t_service_detail` VALUES ('4', '21', '37', '语音通话', '0', '0', null, '0.01', '2016-04-18 15:24:27', '2016-04-18', '2016041821001004900230788555', '0');
INSERT INTO `t_service_detail` VALUES ('5', '39', '14', '语音通话', '0', '1', null, '0.01', '2016-04-18 15:57:43', '2016-04-18', '2016041821001004860221991544', '0');
INSERT INTO `t_service_detail` VALUES ('6', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-18 17:27:20', '2016-04-18', '2016041821001004900231380827', '0');
INSERT INTO `t_service_detail` VALUES ('7', '43', '37', '语音通话', '0', '0', null, '0.01', '2016-04-18 21:07:50', '2016-04-18', '2016041821001004510296865786', '0');
INSERT INTO `t_service_detail` VALUES ('8', '51', '47', '见面约谈', '1', '0', null, '0.01', '2016-04-19 09:52:14', '2016-04-19', '2016041921001004470279515128', '0');
INSERT INTO `t_service_detail` VALUES ('9', '51', '44', '见面约谈', '1', '0', null, '0.01', '2016-04-19 17:35:27', '2016-04-19', '', null);
INSERT INTO `t_service_detail` VALUES ('10', '39', '14', '语音通话', '0', '1', null, '0.01', '2016-04-19 21:49:46', '2016-04-19', '2016041921001004860231278161', '0');
INSERT INTO `t_service_detail` VALUES ('11', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 00:01:31', '2016-04-20', '4004202001201604205008160535', '1');
INSERT INTO `t_service_detail` VALUES ('12', '21', '14', '语音通话', '0', '1', null, '0.01', '2016-04-20 00:09:06', '2016-04-20', '4004202001201604195006236386', '1');
INSERT INTO `t_service_detail` VALUES ('13', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 11:27:31', '2016-04-20', '4004202001201604205018309998', '1');
INSERT INTO `t_service_detail` VALUES ('14', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 11:51:33', '2016-04-20', '4004202001201604205017730117', '1');
INSERT INTO `t_service_detail` VALUES ('15', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 11:52:02', '2016-04-20', '2016042021001004900235469784', '0');
INSERT INTO `t_service_detail` VALUES ('16', '21', '14', '见面约谈', '1', '1', null, '0.01', '2016-04-20 14:32:33', '2016-04-20', '4004202001201604205023351424', '1');
INSERT INTO `t_service_detail` VALUES ('17', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 14:59:26', '2016-04-20', '4004202001201604205023718483', '1');
INSERT INTO `t_service_detail` VALUES ('18', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 15:00:40', '2016-04-20', '2016042021001004900235378908', '0');
INSERT INTO `t_service_detail` VALUES ('19', '21', '50', '见面约谈', '0', '0', null, '0.01', '2016-04-20 15:34:04', '2016-04-20', '2016042021001004900243615741', '0');
INSERT INTO `t_service_detail` VALUES ('20', '21', '56', '见面约谈', '0', '0', null, '0.01', '2016-04-20 15:52:18', '2016-04-20', '4004202001201604205025411880', '1');
INSERT INTO `t_service_detail` VALUES ('21', '21', '56', '语音通话', '0', '0', null, '0.01', '2016-04-20 15:52:52', '2016-04-20', '4004202001201604205025421098', '1');
INSERT INTO `t_service_detail` VALUES ('22', '21', '56', '见面约谈', '0', '0', null, '0.01', '2016-04-20 15:53:50', '2016-04-20', '2016042021001004900241836930', '0');
INSERT INTO `t_service_detail` VALUES ('23', '21', '56', '语音通话', '0', '0', null, '0.01', '2016-04-20 15:54:21', '2016-04-20', '2016042021001004900241907831', '0');
INSERT INTO `t_service_detail` VALUES ('24', '21', '56', '见面约谈', '1', '0', null, '0.01', '2016-04-20 17:44:52', '2016-04-20', '2016042021001004900243915789', '0');
INSERT INTO `t_service_detail` VALUES ('25', '21', '56', '见面约谈', '1', '0', null, '0.01', '2016-04-20 17:46:13', '2016-04-20', '2016042021001004900244140877', '0');
INSERT INTO `t_service_detail` VALUES ('26', '21', '56', '见面约谈', '1', '0', '2.5', '0.01', '2016-04-20 17:54:02', '2016-04-20', '2016042021001004900235381351', '0');
INSERT INTO `t_service_detail` VALUES ('27', '21', '56', '见面约谈', '1', '0', null, '0.01', '2016-04-20 17:54:39', '2016-04-20', '2016042021001004900241839335', '0');
INSERT INTO `t_service_detail` VALUES ('28', '21', '56', '见面约谈', '1', '0', null, '0.01', '2016-04-20 17:55:43', '2016-04-20', '4004202001201604205028301757', '1');
INSERT INTO `t_service_detail` VALUES ('29', '21', '56', '见面约谈', '1', '0', null, '0.01', '2016-04-20 18:07:42', '2016-04-20', '4004202001201604205028929909', '1');

-- ----------------------------
-- Table structure for `t_stage`
-- ----------------------------
DROP TABLE IF EXISTS `t_stage`;
CREATE TABLE `t_stage` (
  `n_stage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投资阶段主键',
  `c_stage_name` varchar(20) DEFAULT '' COMMENT '投资阶段名称',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_stage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stage
-- ----------------------------

-- ----------------------------
-- Table structure for `t_team`
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `n_team_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '团队信息表',
  `n_project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `c_user_portrait` varchar(200) DEFAULT NULL COMMENT '团队成员头像',
  `c_team_name` varchar(100) DEFAULT '' COMMENT '团队人员姓名',
  `c_team_position` varchar(100) DEFAULT '' COMMENT '团队人员职位',
  `c_team_intro` varchar(1000) DEFAULT '' COMMENT '团队人员描述',
  `c_team_tag` varchar(50) DEFAULT NULL COMMENT '团队标签：以#分割',
  `c_from` varchar(50) DEFAULT NULL COMMENT '数据来源',
  `c_team_wechat` varchar(20) DEFAULT NULL COMMENT '成员微信号',
  `c_team_phone` varchar(20) DEFAULT NULL COMMENT '成员电话',
  `c_team_qq` varchar(20) DEFAULT NULL COMMENT '成员qq',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
INSERT INTO `t_team` VALUES ('1', '1', null, '啊啊', '阿斯蒂芬', '等等等等', null, null, null, null, null, '2016-04-03 21:12:47', '2016-04-03 21:12:49');

-- ----------------------------
-- Table structure for `t_user_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account` (
  `n_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户基本信息表主键',
  `c_user_account` varchar(20) NOT NULL COMMENT '用户帐号',
  `c_user_password` varchar(32) DEFAULT NULL COMMENT '用户密码（密码不能明文存储）',
  `c_user_realname` varchar(50) DEFAULT '' COMMENT '用户真实姓名',
  `c_user_portrait` varchar(200) DEFAULT '' COMMENT '用户头像',
  `c_mobile_phone` varchar(15) NOT NULL COMMENT '用户手机号',
  `c_user_email` varchar(30) DEFAULT '' COMMENT '电子邮箱',
  `c_user_company` varchar(100) DEFAULT '' COMMENT '用户公司名称',
  `c_user_position` varchar(100) DEFAULT '' COMMENT '用户职位',
  `c_user_addr` varchar(100) DEFAULT '' COMMENT '用户地址',
  `n_user_role` tinyint(1) NOT NULL COMMENT '用户角色：0投资者、1创业者',
  `c_user_introduce` varchar(500) DEFAULT NULL COMMENT '用户简介',
  `n_iscomplete` tinyint(1) DEFAULT '0' COMMENT '是否完善资料：0、未完善 1、已完善',
  `t_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `t_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`n_user_id`),
  UNIQUE KEY `帐号` (`c_user_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_account
-- ----------------------------
INSERT INTO `t_user_account` VALUES ('9', '18320792425', 't8YMMsuG8Bh6s29ZhQd6/w==', 'ceshi', '', '18320792425', '', 'aa', '开发工程师', '深圳', '0', '我的简介', '0', '2016-06-20 10:23:03', '2016-06-20 09:58:08');

-- ----------------------------
-- Table structure for `t_user_investor`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_investor`;
CREATE TABLE `t_user_investor` (
  `n_investor_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投资人信息主键',
  `n_user_id` bigint(20) NOT NULL COMMENT '关联用户基本信息表ID',
  `c_investor_desc` varchar(500) DEFAULT '' COMMENT '投资理念',
  `c_service_voice` tinyint(1) DEFAULT '0' COMMENT '0 未开通 1开通 2开通并公益',
  `c_service_voice_price` float DEFAULT NULL COMMENT '语音通话服务价格',
  `c_service_meet` tinyint(1) DEFAULT '0' COMMENT '0 未开通 1开通 2开通并公益',
  `c_service_meet_price` float DEFAULT NULL COMMENT '见面约谈服务价格',
  `c_contribute_price` float DEFAULT NULL COMMENT '贡献公益总价格',
  `f_investor_total_score` float DEFAULT NULL COMMENT '投资者总分',
  `f_investor_count` int(11) DEFAULT NULL COMMENT '投资人被评分次数',
  `c_stage_id` varchar(100) DEFAULT NULL COMMENT '投资人投资阶段ID列表(列表以#分割)',
  `c_stage_name` varchar(200) DEFAULT NULL COMMENT '投资人投资阶段名称列表(列表以#分割)',
  `c_industry_id` varchar(100) DEFAULT NULL COMMENT '投资人关注行业ID列表(列表以#分割)',
  `c_industry_name` varchar(200) DEFAULT NULL COMMENT '投资人关注行业名称列表(列表以#分割)',
  `c_alipay_account` varchar(100) DEFAULT NULL COMMENT '支付宝账号',
  `f_balance` float DEFAULT NULL COMMENT '账户余额',
  `n_is_public` tinyint(1) DEFAULT '1' COMMENT '是否公开信息：0不公开 1公开',
  `f_investor_avg_score` float DEFAULT '0' COMMENT '投资者平均得分',
  `n_identify_result` tinyint(1) DEFAULT '0' COMMENT '职业认证结果：0未通过认证，1已通过认证',
  PRIMARY KEY (`n_investor_id`),
  UNIQUE KEY `n_user_id` (`n_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_investor
-- ----------------------------
INSERT INTO `t_user_investor` VALUES ('6', '9', '我的投资理念', '0', null, '0', null, null, null, null, '#2##', '#A轮投资##', null, null, null, null, '1', '0', '0');

-- ----------------------------
-- Table structure for `t_valuation`
-- ----------------------------
DROP TABLE IF EXISTS `t_valuation`;
CREATE TABLE `t_valuation` (
  `n_valuation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目估值记录',
  `n_user_id` bigint(20) NOT NULL COMMENT '估值人员id',
  `n_project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `f_value` float NOT NULL COMMENT '估值结果',
  `t_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '估值时间',
  `t_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`n_valuation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_valuation
-- ----------------------------
INSERT INTO `t_valuation` VALUES ('1', '9', '1', '100', '2016-06-21 18:09:46', '2016-06-21 18:10:57');

-- ----------------------------
-- Table structure for `t_withdraw`
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw`;
CREATE TABLE `t_withdraw` (
  `n_withdraw_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '提现记录ID',
  `n_user_id` bigint(20) DEFAULT NULL COMMENT '提现人员ID',
  `f_amount` float DEFAULT NULL COMMENT '提现金额',
  `n_status` tinyint(1) DEFAULT '0' COMMENT '提现步骤0已提交1已完成2已取消',
  `t_withdraw_time` timestamp NULL DEFAULT NULL COMMENT '提现日期',
  `t_alipay_flag` tinyint(1) DEFAULT NULL COMMENT '0不是1是',
  `c_with_draw_account` varchar(255) DEFAULT NULL COMMENT '提现账户：支付宝账户',
  `t_create_time` timestamp NULL DEFAULT NULL,
  `t_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_withdraw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_withdraw
-- ----------------------------

-- ----------------------------
-- Table structure for `t_work_experince`
-- ----------------------------
DROP TABLE IF EXISTS `t_work_experince`;
CREATE TABLE `t_work_experince` (
  `n_work_experince_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '经历ID',
  `n_team_id` bigint(20) DEFAULT NULL COMMENT '团队成员ID',
  `c_company` varchar(100) DEFAULT NULL COMMENT '根据类型：若为工作经历，此字段表示公司名称，若为教育经历，此字段表示学校名称',
  `c_position` varchar(100) DEFAULT NULL COMMENT '根据类型：若为工作经历，此字段表示职位名称，若为教育经历，此字段表示专业名称',
  `t_begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `t_end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`n_work_experince_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_work_experince
-- ----------------------------
