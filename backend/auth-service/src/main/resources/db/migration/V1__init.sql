CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

-- Seed Data
INSERT INTO users (id, email, display_name, created_at) VALUES
('u1', 'player1@example.com', 'Player One', NOW());
