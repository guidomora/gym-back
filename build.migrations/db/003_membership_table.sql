--liquibase formatted sql
--changeset tu_nombre:migrar-schema-a-entidades-java-v1 splitStatements:true

CREATE TABLE memberships (
                    id BIGSERIAL PRIMARY KEY,
                    membership_key VARCHAR(32) NOT NULL,
                    expiration_date DATE
);
ALTER TABLE users ADD COLUMN membership_id BIGINT;

ALTER TABLE users ADD CONSTRAINT fk_membership_on_user 
    FOREIGN KEY (membership_id) REFERENCES memberships(id);

INSERT INTO memberships (membership_key) VALUES ('llkX9stexBFexHZfplQ6EmEOqVm1V2Xm');
INSERT INTO memberships (membership_key) VALUES ('Lp5S97cokQI9vbWm53cbCMexqfkKWTnh');
INSERT INTO memberships (membership_key) VALUES ('GCGRndAilFDNDxDAUk03e2aYb6lxvfZa');
INSERT INTO memberships (membership_key) VALUES ('uRSDsSdXMzShRUVJ5lZhOuY6vAgvQVe4');
INSERT INTO memberships (membership_key) VALUES ('MDihI4rzKR26DIUZxGo1OkfQZRRoZ4pI');
INSERT INTO memberships (membership_key) VALUES ('0aXjPUFttK01K9Sm0LeYVRFc4xSVx2c9');
INSERT INTO memberships (membership_key) VALUES ('ILvv0sUDJ1E5LdW57DX8dgcfLqUdeKrM');
INSERT INTO memberships (membership_key) VALUES ('ONf34GpkVkX6UDR5wIcvC5dyPibVVycz');
INSERT INTO memberships (membership_key) VALUES ('QZZIbBmAa7ElOtcKD8NmplY9GBUyjbph');
INSERT INTO memberships (membership_key) VALUES ('OWz79SDvVfeY1daRvLcwMRNv6OxG6W90');