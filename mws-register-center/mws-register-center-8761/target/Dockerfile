#镜像出处
FROM openjdk:8-jre
#维护者信息
MAINTAINER wanglin <encodmail@126.com>
#镜像操作指令
#在容器的根目录中创建app文件夹
RUN mkdir -p /app
#指定工作目录
WORKDIR /app
#将本地项目jar包拷贝到Docker容器中的位置
COPY target/mws-register-center-8761.jar /app/app.jar
#将本地的可执行文件拷贝到Docker容器中的根目录下
COPY run.sh /
#给app-entrypoint.sh赋予可执行权限
RUN chmod +x /run.sh
#对外暴漏的端口号
EXPOSE 8761
#开机启动
ENTRYPOINT ["/run.sh"]