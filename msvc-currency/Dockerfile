FROM openjdk:17-alpine as builder

WORKDIR /app/msvc-currency

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/msvc-currency/target/msvc-currency-0.0.1-SNAPSHOT.jar .

EXPOSE 8003

ENTRYPOINT ["java","-jar","msvc-currency-0.0.1-SNAPSHOT.jar"]