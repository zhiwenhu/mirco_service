# Spring Cloud 微服务


| **子工程名**         |**概要**|
|--------------|---|
| cloud-eureka |Eureka服务注册服务端|
| cloud-user   |用户管理微服务|
| cloud-doc    |文档微服务|

## cloud-eureka
* spring-cloud-starter-netflix-eureka-server依赖
* 启动类加@EnableEurekaServer注解识别Eureka服务端
* 不查找注册中心和不注册

`eureka.client.register-with-eureka=false`

`eureka.client.fetch-registry=false`
* 配置注册中心地址

`eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/`

## cloud-user
### 用户注册登录修改，信息查询，角色修改等
* spring-cloud-starter-netflix-eureka-client依赖
* 启动类加@EnableEurekaClient注解识别Eureka客户端
* 集成druid-spring-boot-starter使用druid数据库连接池技术，并且根据配置监控sql统计信息，慢sql记录
* 集成jpa支持，自动生成表结构，利用JpaRepository实现查询，更新删除等操作
* 集成shiro-spring，实现角色权限管理。
* 集成Swagger2自动生成API文档。http://localhost:9019/usercenter/swagger-ui.html

## cloud-doc
### 上传文件，导出word和PDF文档
* spring-cloud-starter-netflix-eureka-client依赖
* 启动类加@EnableEurekaClient注解识别Eureka客户端
* 集成druid-spring-boot-starter使用druid数据库连接池技术，并且根据配置监控sql统计信息，慢sql记录
* 集成jpa支持，自动生成表结构，利用JpaRepository实现查询，更新删除等操作
* 集成shiro-spring，实现角色权限管理。
* 集成Swagger2自动生成API文档。http://localhost:9025/document/swagger-ui.html
* 利用thumbnailator对图片进行压缩处理
* @LoadBalanced注解实现负载均衡

