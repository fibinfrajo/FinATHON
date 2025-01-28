FROM tomcat:11.0.2

WORKDIR /usr/local/tomcat/webapps

COPY target/my-wicket-app.war /usr/local/tomcat/webapps/ROOT.war

ENV db_password=root db_url="jdbc:mysql://host.docker.internal:3306/ my_database" db_user=root

EXPOSE 8080

CMD ["catalina.sh", "run"]