ALTER TABLE `t_project`
ADD COLUMN `n_interview_count`  int NULL DEFAULT 0 COMMENT '项目约谈次数' AFTER `t_update_time`,
ADD COLUMN `n_fav_count`  int NULL DEFAULT 0 COMMENT '项目收藏次数' AFTER `n_interview_count`;

ALTER TABLE `t_project`
ADD COLUMN `n_company_establish`  tinyint(1) NULL DEFAULT 0 COMMENT '公司是否成立0未成立1成立' AFTER `n_fav_count`,
ADD COLUMN `n_team_establish`  tinyint(1) NULL DEFAULT 0 COMMENT '团队信息是否完善0未完善1已完善' AFTER `n_company_establish`;

ALTER TABLE t_project MODIFY c_bpimage_addr VARCHAR(1000) DEFAULT NULL COMMENT '图片地址列表，JSON数组格式';

ALTER TABLE `ct_server`.`t_service_detail`   
  ADD COLUMN `n_investor_id` BIGINT(20) NULL  COMMENT '投资人ID' AFTER `n_customer_id`; 
  ALTER TABLE `ct_server`.`t_service_detail`   
  ADD COLUMN `c_trade_no` VARCHAR(50) NULL  COMMENT '支付宝交易号' AFTER `t_update_time`;
  
  ALTER TABLE `ct_server`.`t_project`   
  ADD COLUMN `c_project_icon` VARCHAR(100) NULL  COMMENT '项目icon图片地址' AFTER `n_team_establish`;
  
  ALTER TABLE `t_user_investor`
MODIFY COLUMN `c_service_meet`  tinyint(1) NULL DEFAULT 0 COMMENT '0 未开通 1开通 2开通并公益' AFTER `c_service_voice_price`;
ALTER TABLE `t_service_detail`
CHANGE COLUMN `c_services_ welfare` `c_services_welfare`  tinyint(1) NULL DEFAULT NULL COMMENT '0、不做公益1、做公益' AFTER `c_services_name`;

ALTER TABLE `ct_server`.`t_user_investor`   
  CHANGE `c_service_voice` `c_service_voice` TINYINT(1) DEFAULT 0  NOT NULL  COMMENT '0 未开通 1开通 2开通并公益',
  CHANGE `c_service_meet` `c_service_meet` TINYINT(1) DEFAULT 0  NOT NULL  COMMENT '0 未开通 1开通 2开通并公益';
  
  ALTER TABLE t_project MODIFY c_bpimage_addr TEXT DEFAULT NULL COMMENT '图片地址列表，JSON数组格式';
  
  ALTER TABLE `t_project`
 ADD COLUMN `n_project_role` TINYINT(1) NULL DEFAULT 1 COMMENT '0投资者项目、1创业者项目';
 ALTER TABLE `t_project`
MODIFY COLUMN `n_user_id`  bigint(20) NOT NULL COMMENT '项目创建者ID' AFTER `n_project_id`;

ALTER TABLE `t_user_investor`
ADD COLUMN `n_identify_result`  tinyint(1) NULL DEFAULT 0 COMMENT '职业认证结果：0未通过认证，1已通过认证' AFTER `f_investor_avg_score`;
ALTER TABLE `t_user_investor`
MODIFY COLUMN `n_is_public`  tinyint(1) NULL DEFAULT 1 COMMENT '是否公开信息：0不公开 1公开' AFTER `f_balance`;

ALTER TABLE `ct_server`.`t_service_detail`   
  CHANGE `n_service_detail_id` `n_service_detail_id` BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '服务交易明细表ID';

ALTER TABLE `ct_server`.`t_service_detail`   
  CHANGE `c_trade_no` `c_trade_no` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci NULL  COMMENT '交易号',
  ADD COLUMN `n_trade_type` TINYINT(1) NULL  COMMENT '交易类型：-1免费0支付宝支付1微信支付' AFTER `c_trade_no`;

