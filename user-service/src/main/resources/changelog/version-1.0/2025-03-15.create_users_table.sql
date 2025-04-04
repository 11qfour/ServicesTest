-- changeset 11qfour:1 failOnError:true
-- Create users table
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    company_id   BIGINT  NOT NULL
);
-- rollback DROP TABLE IF EXISTS users;
