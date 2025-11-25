# --- Etapa 1: Compilar ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# TRUCO: -DfinalName=app obliga a que el archivo se llame app.jar
RUN mvn clean package -DskipTests -DfinalName=app

# --- Etapa 2: Ejecutar ---
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Ahora copiamos el archivo exacto app.jar (sin usar asteriscos)
COPY --from=build /app/target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
