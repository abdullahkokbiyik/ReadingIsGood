FROM maven:3.6.3-openjdk-17 as build

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean install -Dmaven.test.skip=true -q

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=build /build/target/readingisgood-0.0.1.jar /app/
ENTRYPOINT ["java", "-jar", "readingisgood-0.0.1.jar"]
