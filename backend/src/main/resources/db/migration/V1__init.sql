CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE challenges (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    cadence VARCHAR(50) NOT NULL,
    owner_id VARCHAR(255) NOT NULL REFERENCES users(id),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE activity_logs (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL REFERENCES users(id),
    challenge_id VARCHAR(255) NOT NULL REFERENCES challenges(id),
    notes TEXT,
    completed_at TIMESTAMP NOT NULL
);

CREATE TABLE messages (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL REFERENCES users(id),
    role VARCHAR(50) NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

-- Seed Data
INSERT INTO users (id, email, display_name, created_at) VALUES
('u1', 'player1@example.com', 'Player One', NOW());

INSERT INTO challenges (id, title, description, cadence, owner_id, created_at) VALUES
('c1', 'Commit Daily', 'Push code to git', 'DAILY', 'u1', NOW()),
('c2', 'Review PR', 'Review a pull request', 'WEEKLY', 'u1', NOW());
