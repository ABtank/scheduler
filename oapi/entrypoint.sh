#!/usr/bin/env sh

/usr/bin/java -Xmx256m -Xss512k -XX:-UseContainerSupport \
              -jar /apps/app.jar \
              --spring.datasource.url=jdbc:mysql://db:3306/scheduler?createDatabaseIfNotExist=true