FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/restaurant-management.jar"]


#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/skylink.jar skylink.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "target/skylink.jar"]