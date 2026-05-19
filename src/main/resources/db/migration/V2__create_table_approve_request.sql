CREATE TABLE approve_request(
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    status VARCHAR(256) NOT NULL,
    created_at TIMESTAMP,
    last_update TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);