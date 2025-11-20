--liquibase formatted sql
--changeset tu_nombre:crear-tabla-memberships splitStatements:true

CREATE TABLE memberships (
                             id BIGSERIAL PRIMARY KEY,
                             key VARCHAR(255) NOT NULL,
                             expiration_date DATE
);


ALTER TABLE users ADD COLUMN membership_id BIGINT;

ALTER TABLE users ADD CONSTRAINT fk_users_on_membership
    FOREIGN KEY (membership_id) REFERENCES memberships(id);
