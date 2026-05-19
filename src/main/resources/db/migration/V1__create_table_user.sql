CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    created_at TIMESTAMP
);