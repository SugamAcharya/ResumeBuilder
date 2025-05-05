FROM openjdk:17-jdk-alpine AS build
RUN apk add--no-cache maven

WORKDIR /usr/src/resume
COPY pom.xml .

COPY src ./src
RUN MVN clean package -DskipTests
ARG timezone_set=Australia/Perth
ENV TZ=$timezone_set

FROM openjdk:17-jdk-slim
WORKDIR /usr/src/resume

RUN GROUPADD -r appgroup && USERADD -r -g appgroup -no-log-init --system --home-dir /usr/src/resume
RUN CHOWN -R java:appgroup /usr/src/resume

USER kotlin

COPY --from=build /usr/src/resume/target/resume.jar ./resume.jar
ENTRYPOINT ["java","-jar", "resume.jar"]