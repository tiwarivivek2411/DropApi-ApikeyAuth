FROM httpd:2.4
COPY ./my-httpd.conf /usr/local/apache2/conf/apache2.conf

FROM openjdk:8-jdk
 
COPY ./config.yml /data/BasicDropwizard/config.yml  
COPY /target/dropApi-1.0-SNAPSHOT.jar /data/BasicDropwizard/BasicDropwizard-1.0.0.jar

WORKDIR /data/BasicDropwizard
CMD  ["java","-jar","BasicDropwizard-1.0.0.jar","server"]
CMD ["/usr/sbin/apachectl", "-D", "FOREGROUND"]
EXPOSE 4000-4001

