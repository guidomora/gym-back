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
- **Docker** (o una instalación manual de PostgreSQL)
- Gradle (o usar el wrapper incluido)

## Configuración de Base de Datos (Usando Docker)

Sigue estos pasos para levantar y configurar la base de datos con Docker.

### 1\. Levantar el Contenedor PostgreSQL

Este comando levanta un contenedor llamado `postgres_db` con un superusuario `admin` y contraseña `admin123`.

```bash
docker run -d \
  --name postgres_db \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin123 \
  -p 5432:5432 \
  -v postgres_data:/var/lib/postgresql/data \
  --restart unless-stopped \
  postgres:16-alpine
```

### 2\. Conectarse a psql

Espera unos segundos a que la base de datos inicie. Luego, conéctate a la consola `psql` usando el superusuario `admin` (la base de datos `-d postgres` es la que viene por defecto):

```bash
docker exec -it postgres_db psql -U admin -d postgres
```

### 3\. Crear la Base de Datos y el Usuario de la App

Una vez dentro de `psql` (verás el prompt `postgres=#`), ejecuta los siguientes comandos SQL:

```sql
-- 1. Crear la base de datos de la aplicación
CREATE DATABASE gym_db;

-- 2. Crear el usuario que la aplicación usará
CREATE USER "user" WITH PASSWORD 'P4ssw0rd!';

-- 3. Darle todos los permisos a ese usuario sobre la nueva base de datos
GRANT ALL PRIVILEGES ON DATABASE gym_db TO "user";
```

### 4\. Salir de psql

Escribe `\q` y presiona Enter para salir de psql.

### 5\. Ejecutar las Migraciones (Crear Tablas)

Una vez que la base de datos `gym_db` está creada y el `user` tiene permisos, ejecuta el siguiente comando para que Liquibase cree toda la estructura de tablas:

```bash
./gradlew update
```

La base de datos está lista.

## Instalación

1.  Clona el repositorio:

    ```bash
    git clone https://github.com/guidomora/gym-back.git
    cd gym-back
    ```

2.  (Opcional) Verifica las credenciales de la base de datos en `src/main/resources/application.properties` si no usaste los valores por defecto del paso 3.

3.  Compila el proyecto:

    ```bash
    ./gradlew clean build
    ```

## Ejecución

Ejecuta la aplicación:

```bash
./gradlew bootRun
```

(Nota: Si no ejecutaste `./gradlew update` manualmente, `bootRun` también ejecutará las migraciones de Liquibase automáticamente al iniciar).

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

### Limpiar la Base de Datos (dropAll)

Si necesitas borrar todas las tablas y objetos de la base de datos (para empezar de cero), puedes ejecutar:

```bash
./gradlew dropAll
```

**Advertencia:** Esto eliminará todos los datos de la base de datos `gym_db`.

## Recursos Adicionales

- [Documentación oficial de Gradle](https://docs.gradle.org)
- [Guía de referencia de Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/3.5.6/gradle-plugin)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Acceso a datos con JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## Licencia

Este proyecto es de código abierto.

## Contacto

Proyecto: [https://github.com/guidomora/gym-back](https://github.com/guidomora/gym-back)