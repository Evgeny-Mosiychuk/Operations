FROM openjdk:17
RUN useradd -ms /bin/bash newuser
USER newuser
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Paysonix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Paysonix-0.0.1-SNAPSHOT.jar"]