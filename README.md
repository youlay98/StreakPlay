# DevOps Game Night

A low-anxiety, HMI-style web app for practicing DevOps skills. Built as a Modular Monolith to prioritize simplicity and stability.

## Architecture

- **Backend**: Spring Boot 3 (Java 21) - Modular Monolith
- **Frontend**: React + Vite + Tailwind
- **Database**: PostgreSQL
- **Infrastructure**: Docker Compose

## Quick Start

1.  **Prerequisites**: Docker, Java 21, Node.js 20.
2.  **Setup**:
    ```bash
    cp .env.example .env
    docker compose up -d
    ```
3.  **Access**:
    - Frontend: http://localhost:5173
    - Backend API: http://localhost:8080/api/v1/health
    - API Docs: http://localhost:8080/swagger-ui.html

## Documentation

- [Architecture Decision Record](./docs/ADR-0001-arch.md)
- [Kafka Decision](./docs/KAFKA-DECISION.md)
- [Business Plan](./docs/BUSINESS_PLAN.md)
