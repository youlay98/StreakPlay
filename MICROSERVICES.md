# Microservices Architecture Guide

## Architecture Overview

This application uses a **microservices architecture** with Spring Cloud.

### Services

#### Infrastructure Services
- **Discovery Service** (Port 8761): Eureka Server
- **Gateway Service** (Port 8080): API Gateway

#### Business Services
- **Auth Service** (Port 8081): User management (Database: `auth_db`)
- **Game Service** (Port 8082): Challenges & activities (Database: `game_db`)
- **AI Service** (Port 8083): AI messaging (Database: `ai_db`)

### Running the Application

```bash
docker-compose up --build
```

### Access Points

- Frontend: http://localhost:5173
- API Gateway: http://localhost:8080
- Eureka Dashboard: http://localhost:8761

### API Routes

- `GET /api/auth/users` → Auth Service
- `GET /api/game/challenges` → Game Service
- `GET /api/ai/messages?userId=u1` → AI Service
