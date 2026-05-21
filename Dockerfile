FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY pom.xml .
RUN ./mvnw -q -DskipTests dependency:go-offline || true
COPY . .
RUN chmod +x mvnw && ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/ecommerce-enterprise-senior-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
