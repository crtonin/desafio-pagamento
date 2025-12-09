# ===== BUILD STAGE =====
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN ./mvnw -version || true
RUN apt-get update && apt-get install -y maven

RUN mvn -B -DskipTests clean package

# ===== RUNTIME STAGE =====
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
