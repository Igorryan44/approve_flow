CREATE TABLE approve_request(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_name VARCHAR(256) NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(256) NOT NULL,
    created_at TIMESTAMP,
    last_update TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);