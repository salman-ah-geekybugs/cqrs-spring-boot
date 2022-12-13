FROM maven:3.6.3 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11
COPY --from=build /usr/src/app/target/demo-0.0.1-SNAPSHOT.jar /usr/app/demo-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/usr/app/demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 5000