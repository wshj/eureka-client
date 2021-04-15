# 项目简介

项目|描述|   作用
---|---|---
eureka-client-a-1 | 客户端a | 提供被其他客户端调用的方法
eureka-client-a-2 | 客户端a | 将客户端a作为集群模式
eureka-client-b | 客户端b | 另一个客户端，用来调用客户端a
eureka-server | 服务端 | 单节点的服务注册中心

## 特别说明
eureka-server 是eureka的服务注册中心

eureka-client-* 是客户端，客户端可以相互调用