# Etapa 1: Compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Etapa 2: Crear imagen ligera para ejecucion
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

#Puerto expuesto (mismo que en el .env)
EXPOSE 8080

#Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]