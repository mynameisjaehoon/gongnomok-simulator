# version dev:1.1.4

FROM openjdk:17-oracle as builder
WORKDIR /backend

# COPY gradlew .
# COPY gradle gradle
# COPY build.gradle .
# COPY . .

# RUN microdnf install findutils
# RUN chmod +x ./gradlew
# RUN ./gradlew clean build

# FROM openjdk:17-oracle
# COPY /build/libs/gongnomok-app.jar ./app.jar
# ENTRYPOINT [ "java", "-jar", "/app.jar" ]
# VOLUME [ "/tmp" ]

FROM openjdk:17-oracle as builder
WORKDIR /backend

ARG JAR_PATH=gongnomok-api/build/libs/gongnomok-api-1.0.0.jar
COPY ${JAR_PATH} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
