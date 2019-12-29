--#表字段  时间字段设计
--# create_timestamp  DEFAULT CURRENT_TIMESTAMP
--#last_update_timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP



--#常用表 设计

--用户表
create table employee
(
   userid varchar(50) not null,  ---用户ID
   username varchar(100),    ---用户名
   userpassword varchar(100), ---密码
   ..
   ..
   ..
   ..
)
alter table employee        --主键
add constraint pk_employee_userid primary key (userid)


--角色表
create table role
(
  roleid varchar(50) not null, --角色Id
  rolename varchar(100),        --角色名称
)
alter table tole     --主键
add constraint pk_role_roleid primary key (roleid)

--权限表
create table popedom
(
  popedomid int identity(1,1) not null, --权限Id
  popedomname varchar(100), --权限名称
  popedomfatherid int,      --权限父ID
  popedomurl varchar(100)   --树的连接路径
  ..
  ..
）
alter table popedom       --主键
add constraint PK_popedom primary key (popedomid)


--用户和角色关系表
create table user_role
(
 connectionid int identity(1,1) not null, --关系ID
  userid varchar(50) not null,   --管理员表ID
  roleid varchar(50) not null   --角色Id
)
alter table user_role    --主键
add constraint PK_admin_role primary key(connectionid)


--角色和权限关系表
create table role_popedom     --角色与权限表
(
  connectionid int identity(1,1), --关系Id
  roleid varchar(50) not null,      --角色ID
  popedomid int not null,   --权限Id
  popedom    int   --权限 （1为可用，2为不可用）
)
alter table role_popedom       --主键
add constraint PK_role_popedom primary key(connectionid) --主键









