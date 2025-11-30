CREATE TABLE messages (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);
