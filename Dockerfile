FROM openjdk:14
LABEL maintener="jl.protois.perso@gmail.com"
EXPOSE 8082
ARG JAR_FILE=target/P9_Notes-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} note.jar
ENTRYPOINT ["java","-jar","/note.jar"]