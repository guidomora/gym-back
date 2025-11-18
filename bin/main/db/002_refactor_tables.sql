--liquibase formatted sql
--changeset tu_nombre:migrar-schema-a-entidades-java-v1 splitStatements:true

ALTER TABLE exercise RENAME TO exercises;

ALTER TABLE exercises DROP COLUMN description;
ALTER TABLE exercises DROP COLUMN muscle_group;

ALTER TABLE exercises ADD COLUMN sets INT NOT NULL DEFAULT 0;
ALTER TABLE exercises ADD COLUMN repetitions INT NOT NULL DEFAULT 0;
ALTER TABLE exercises ADD COLUMN rest_time INT NOT NULL DEFAULT 0;
ALTER TABLE exercises ADD COLUMN weight_type VARCHAR(50) NOT NULL DEFAULT 'KG';
ALTER TABLE exercises ADD COLUMN routine_id BIGINT;

ALTER TABLE routine RENAME TO routines;

ALTER TABLE routines DROP COLUMN created_at;

ALTER TABLE routines ADD COLUMN day_of_week VARCHAR(20) NOT NULL DEFAULT 'MONDAY';
ALTER TABLE routines ADD COLUMN date DATE NOT NULL DEFAULT CURRENT_DATE;
ALTER TABLE routines ADD COLUMN student_id BIGINT;


CREATE TABLE gym (
                     id BIGSERIAL PRIMARY KEY,
                     nombre VARCHAR(255) NOT NULL
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       birthdate DATE NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       gym_id BIGINT
);

ALTER TABLE users ADD CONSTRAINT fk_users_on_gym
    FOREIGN KEY (gym_id) REFERENCES gym(id);

ALTER TABLE routines ADD CONSTRAINT fk_routines_on_student
    FOREIGN KEY (student_id) REFERENCES users(id);

ALTER TABLE exercises ADD CONSTRAINT fk_exercises_on_routine
    FOREIGN KEY (routine_id) REFERENCES routines(id);

INSERT INTO gym (nombre) VALUES ('Super Sport');
INSERT INTO gym (nombre) VALUES ('Gold Gym');
INSERT INTO gym (nombre) VALUES ('Fitter');