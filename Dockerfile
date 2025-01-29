
FROM maven:3 as builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean install


FROM tomcat:11.0.2

WORKDIR /usr/local/tomcat/webapps

COPY --from=builder /app/target/my-wicket-app.war /usr/local/tomcat/webapps/ROOT.war

ENV db_password=root db_url="jdbc:mysql://host.docker.internal:3306/my_database" db_user=root

EXPOSE 8080

CMD ["catalina.sh", "run"]