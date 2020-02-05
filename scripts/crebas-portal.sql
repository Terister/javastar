/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/5 12:44:06                            */
/*==============================================================*/


drop table if exists portal_action_logs;

drop table if exists portal_article;

drop table if exists portal_menu;

drop table if exists portal_module;

drop table if exists portal_notice;

/*==============================================================*/
/* Table: portal_action_logs                                    */
/*==============================================================*/
create table portal_action_logs
(
   Id                   bigint not null auto_increment,
   operation_type       varchar(64),
   operation_name       varchar(36),
   parent_code          varchar(36),
   describes            varchar(4000),
   ip                   varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (Id)
);

/*==============================================================*/
/* Table: portal_article                                        */
/*==============================================================*/
create table portal_article
(
   Id                   bigint not null auto_increment,
   title                varchar(128),
   sub_title            varchar(128),
   icon                 varchar(256),
   link                 varchar(256),
   contents             blob,
   ar                   int,
   status               int,
   creator_id           varchar(36),
   creator_name         varchar(36),
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (Id)
);

/*==============================================================*/
/* Table: portal_menu                                           */
/*==============================================================*/
create table portal_menu
(
   Id                   bigint not null auto_increment,
   menu_code            varchar(64),
   menu_name            varchar(36),
   parent_code          varchar(36),
   describes            varchar(4000),
   ar                   int,
   link                 varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (Id)
);

/*==============================================================*/
/* Table: portal_module                                         */
/*==============================================================*/
create table portal_module
(
   Id                   bigint not null auto_increment,
   module_name          varchar(64),
   module_code          varchar(36),
   parent_code          varchar(36),
   icon                 varchar(256),
   link                 varchar(256),
   describes            varchar(4000),
   ar                   int,
   status               int,
   creator_id           varchar(36),
   creator_name         varchar(36),
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (Id)
);

/*==============================================================*/
/* Table: portal_notice                                         */
/*==============================================================*/
create table portal_notice
(
   Id                   bigint not null auto_increment,
   title                varchar(128),
   sub_title            varchar(128),
   position_id          int,
   position_name        varchar(36),
   icons                 varchar(256),
   links                 varchar(256),
   begin_times          datetime,
   end_times            datetime,
   contents             blob,
   ar                   int,
   status               int,
   creator_id           varchar(36),
   creator_name         varchar(36),
create_timestamp datetime DEFAULT CURRENT_TIMESTAMP,
last_update_timestamp datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (Id)
);

