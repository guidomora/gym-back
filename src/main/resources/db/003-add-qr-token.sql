-- liquibase formatted sql
-- changeset nico:3
ALTER TABLE users ADD COLUMN qr_token UUID;