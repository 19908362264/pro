#!/bin/bash
#上面是固定的开头
# 命令行运行项目jar包指令
#java -jar /app/app.jar --eureka.instance.hostname=$SLAVE --eureka.client.serviceUrl.defaultZone=$META --server.port=$THE_PORT --eureka.instance.ip-address=114.67.90.8
# 配置 hosts 域名地址
#echo "114.67.90.8 local.register.com"  >>  /etc/hosts
# 命令行运行项目jar包指令
java -jar /app/app.jar