/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/5 12:47:11                            */
/*==============================================================*/


drop table if exists authority_action_logs;

drop table if exists authority_project;

drop table if exists authority_project_role;

drop table if exists authority_roles;

drop table if exists authority_user_role;

drop table if exists authority_users;

drop table if exists authorityl_registe_app;

/*==============================================================*/
/* Table: authority_action_logs                                 */
/*==============================================================*/
create table authority_action_logs
(
   Id                   bigint not null auto_increment,
   operation_type       varchar(64),
   operation_name       varchar(36),
   parent_code          varchar(36),
   describes            varchar(4000),
   ip                   varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authority_project                                     */
/*==============================================================*/
create table authority_project
(
   Id                   bigint not null,
   project_code         varchar(64),
   project_name         varchar(36),
   parent_code          varchar(36),
   app_id               varchar(64),
   url                  varchar(256),
   url_name             varchar(256),
   describes            varchar(1024),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authority_project_role                                */
/*==============================================================*/
create table authority_project_role
(
   Id                   bigint not null,
   role_code            varchar(64),
   project_code         varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authority_roles                                       */
/*==============================================================*/
create table authority_roles
(
   Id                   bigint not null,
   role_code            varchar(64),
   role_name            varchar(64),
   ar                   int,
   describes            varchar(1024),
   status               int,
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authority_user_role                                   */
/*==============================================================*/
create table authority_user_role
(
   Id                   bigint not null,
   user_id              varchar(64),
   role_code            varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authority_users                                       */
/*==============================================================*/
create table authority_users
(
   Id                   bigint not null,
   user_id              varchar(64),
   user_name            varchar(36),
   user_email           varchar(128),
   user_phone           varchar(16),
   avatar               varchar(36),
   user_password        varchar(4000),
   registe_ip           varchar(64),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

/*==============================================================*/
/* Table: authorityl_registe_app                                */
/*==============================================================*/
create table authorityl_registe_app
(
   Id                   bigint not null auto_increment,
   app_id               varchar(36),
   app_name             varchar(64),
   app_owner            varchar(64),
   bu_code              varchar(36),
   bu_name              varchar(2048),
   creator_id           varchar(36),
   creator_name         varchar(36),
   craete_timestamp     datetime,
   last_update_timestamp datetime,
   primary key (Id)
);

