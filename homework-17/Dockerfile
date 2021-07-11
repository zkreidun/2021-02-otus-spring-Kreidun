
FROM maven:3.5.4-jdk-8

ENV DIR=/opt/project
RUN mkdir -p $DIR
WORKDIR $DIR

ADD pom.xml $DIR
RUN mvn dependency:resolve

ADD src/ $DIR/src
RUN mvn install -DskipTests

FROM openjdk:8-jdk

ENV DIR=/opt/project
RUN mkdir -p $DIR
WORKDIR $DIR

COPY --from=0 $DIR/target/My-library* $DIR/

RUN ln --symbolic --force /dev/stdout /var/log/access_log.log
RUN ln --symbolic --force /dev/stderr /var/log/error_log.log

EXPOSE 8080

CMD ["java", "-jar", "/opt/project/My-library.jar"]