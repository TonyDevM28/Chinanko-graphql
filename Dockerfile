# --- Etapa 1: Compilar ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecutar ---
FROM eclipse-temurin:21-jdk
WORKDIR /app

# CAMBIO CLAVE: Buscamos *.war en lugar de *.jar
COPY --from=build /app/target/*.war app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
