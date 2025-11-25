# --- Etapa 1: Compilar con Maven y Java 21 ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecutar con Java 21 (Eclipse Temurin) ---
# CAMBIO AQUÍ: Usamos eclipse-temurin en lugar de openjdk
FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
