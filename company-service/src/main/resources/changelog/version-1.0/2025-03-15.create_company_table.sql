-- changeset 11qfour:1 failOnError:true
-- Create companies table
CREATE TABLE companies
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR NOT NULL,
    budget BIGINT  NOT NULL
);
-- rollback DROP TABLE IF EXISTS companies;
