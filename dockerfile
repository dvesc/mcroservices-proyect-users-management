FROM openjdk/11.0-jdk-slim-stretch

RUN apt-get install -y tzdata

ENV  TZ America/Bogota

VOLUME ["/home"]
 
ENTRYPOINT ["java","-jar","-Dspring.profiles.active-release", "/home/users-0.0.1-SNAPSHOT.jar"] 

 