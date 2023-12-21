FROM tomcat:9-jdk21-openjdk

RUN apt-get update
RUN apt-get upgrade -y

RUN command cd /root && wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j_8.1.0-1debian11_all.deb 

# jdbcドライバのインストール
RUN cd /root && ar vx mysql-connector-j_8.1.0-1debian11_all.deb
RUN cd /root && tar Jxvf data.tar.xz
RUN mv /root/usr/share/java/mysql-connector-j-8.1.0.jar /usr/local/tomcat/lib

# jsonファイルを扱えるように
RUN cd /root && curl -o json-20231013.jar https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar
RUN mv /root/json-20231013.jar /usr/local/tomcat/lib

RUN rm -r /root/*

COPY ./conf/context.xml /usr/local/tomcat/conf/context.xml 
COPY ./conf/server.xml /usr/local/tomcat/conf/server.xml
COPY ./conf/web.xml /usr/local/tomcat/conf/web.xml
COPY ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

EXPOSE 8009