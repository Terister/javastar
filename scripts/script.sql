--#表字段 时间字段设计
--# create_timestamp DEFAULT CURRENT_TIMESTAMP
--#last_update_timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP



--#常用表 设计

--用户表
create table employee
(
 userid varchar(50) not null, ---用户ID
 username varchar(100), ---用户名
 userpassword varchar(100) ---密码
)
alter table employee --主键
add constraint pk_employee_userid primary key (userid)



CREATE TABLE wk_project(
id bigint(20) NOT NULL AUTO_INCREMENT,
projrct_code VARCHAR(36) DEFAULT NULL COMMENT '资源代码',
parent_code VARCHAR(36) DEFAULT NULL COMMENT '资源父级代码 ',
describes VARCHAR(4000) DEFAULT NULL COMMENT '描述 ',
url VARCHAR(256) DEFAULT NULL COMMENT '资源地址 ',
url_name VARCHAR(64) DEFAULT NULL COMMENT '资源名称 ',
tag VARCHAR(64) DEFAULT NULL COMMENT '标签 ',
ar int(11) DEFAULT 0 COMMENT '排序',
status int(1) DEFAULT 0 COMMENT '0--有效，1--无效',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP ,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) ;


CREATE TABLE wk_role(
id bigint(20) NOT NULL AUTO_INCREMENT,
describes VARCHAR(4000) DEFAULT NULL COMMENT '描述 ',
role_code VARCHAR(64) DEFAULT NULL COMMENT '角色code ',
role_name VARCHAR(64) DEFAULT NULL COMMENT '角色名称 ',
ar int(11) DEFAULT 0 COMMENT '排序',
status int(1) DEFAULT 0 COMMENT '0--有效，1--无效',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP ,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) ;


CREATE TABLE wk_role_project(
id bigint(20) NOT NULL AUTO_INCREMENT,
role_code VARCHAR(64) DEFAULT NULL COMMENT '角色code ',
project_code VARCHAR(64) DEFAULT NULL COMMENT '资源code ',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP ,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
);

CREATE TABLE wk_user_role(
id bigint(20) NOT NULL AUTO_INCREMENT,
role_code VARCHAR(64) DEFAULT NULL COMMENT '角色code ',
user_id VARCHAR(64) DEFAULT NULL COMMENT '用户id ',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) ;

CREATE TABLE wk_user(
id bigint(20) NOT NULL AUTO_INCREMENT,
user_code VARCHAR(64) DEFAULT NULL COMMENT '用户code ',
user_id VARCHAR(64) DEFAULT NULL COMMENT '用户id ',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP ,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) ;


CREATE TABLE wk_action_logs(
id bigint(20) NOT NULL AUTO_INCREMENT,
operation_type INT(11) DEFAULT NULL COMMENT '操作类型',
operation_name VARCHAR(64) DEFAULT NULL COMMENT '操作类型描述 ',
describes VARCHAR(4000) DEFAULT NULL COMMENT '实际操作描述 ',
ip VARCHAR(64) DEFAULT NULL COMMENT 'IP ',
creator_name VARCHAR(64) DEFAULT NULL COMMENT '创建人名称 ',
creator_id VARCHAR(64) DEFAULT NULL COMMENT '创建人ID ',
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) ;










