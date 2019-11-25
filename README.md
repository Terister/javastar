# Javastar 目的

`本项目目的是根据mysql数据库自动创建代码

快速搭建常规java springboot 项目`

#Project 项目结构

`Biz

Controller

Job 

Models 

Repository

Utils  `

#代码操作步骤    

step 1:
   ` 项目job模块为启动项，运行application；`
    
step 2:
    `在controller层中配置项目基本信息`
    
    项目groupId 和 mysql 的连接信息 
    
step 3:
     
    浏览器中访问job的首页：http://localhost:「port」/index
    点击生成java 项目

#生成项目运行步骤
    代码生成后，idea根据打开项目，
    配置好maven 和 terminal配置好git
    执行命令：mvn clean install 执行不通过 主要是install utils 和models作为mybatids plugin 的依赖项
    右边打开maven菜单：找到repository module 里的Plugins  找到mybatis-generator 双击运行
    至此 全部文件生成
    
    项目根目录下面  执行 mvn clean package