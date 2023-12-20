# Используем базовый образ с Java
FROM gradle-7.6-jdk17 as builder
COPY   --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

FROM gradle-7.6-jdk17
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password

ARG JAR_FILE=target/*.jar

COPY --from-builder home/build/libs/SpringServer-0.0.1-SNAPSHOT.jar /SpringServer/SpringServerApplication

ENV PATH="/opt/gradle/bin:${PATH}"

WORKDIR /SpringServer

COPY . /SpringServer/

RUN gradle clean build

EXPOSE 8070

CMD ["java", "-jar", "/app.jar"]
