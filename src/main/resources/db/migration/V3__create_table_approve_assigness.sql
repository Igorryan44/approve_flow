CREATE TABLE approve_request_assignees (
    request_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (request_id, user_id),
    CONSTRAINT fk_req_assignees_request
       FOREIGN KEY (request_id) REFERENCES approve_request(id) ON DELETE CASCADE,
    CONSTRAINT fk_req_assignees_user
       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_assignees_user ON approve_request_assignees(user_id);