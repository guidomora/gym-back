--liquibase formatted sql
--changeset splitStatements:true

CREATE TABLE exercise (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(255),
                          muscle_group VARCHAR(50)
);

CREATE TABLE routine (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);