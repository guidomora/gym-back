# API Gym App

Aplicación de gestión de rutinas de gimnasio desarrollada con Spring Boot.

## Descripción

API REST para administrar y consultar información sobre rutinas de gimnasio, ejercicios y entrenamientos.

## Tecnologías

- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL
- Gradle
- Lombok
- SpringDoc OpenAPI (Swagger)

## Requisitos Previos

- JDK 21 o superior
- PostgreSQL
- Gradle (o usar el wrapper incluido)

## Configuración de Base de Datos

Crea una base de datos PostgreSQL:

```sql
CREATE DATABASE gym_db;
CREATE USER user WITH PASSWORD 'P4ssw0rd!';
GRANT ALL PRIVILEGES ON DATABASE gym_db TO user;
```

## Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/guidomora/gym-back.git
cd gym-back
```

2. Configura las credenciales de la base de datos en `src/main/resources/application.properties` si es necesario.

3. Compila el proyecto:
```bash
./gradlew clean build
```

## Ejecución

Ejecuta la aplicación:

```bash
./gradlew bootRun
```

La aplicación estará disponible en `http://localhost:8080`

## Documentación API

La documentación Swagger está disponible en:
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Configuración

Las principales configuraciones se encuentran en `application.properties`:

- Puerto del servidor: `8080` (configurable con variable de entorno `PORT`)
- URL de base de datos: `jdbc:postgresql://localhost:5432/gym_db`
- Usuario: `user`
- Contraseña: `P4ssw0rd!`

## Desarrollo

### Ejecutar Tests

```bash
./gradlew test
```

## Recursos Adicionales

- [Documentación oficial de Gradle](https://docs.gradle.org)
- [Guía de referencia de Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/3.5.6/gradle-plugin)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Acceso a datos con JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## Licencia

Este proyecto es de código abierto.

## Contacto

Proyecto: [https://github.com/guidomora/gym-back](https://github.com/guidomora/gym-back)

