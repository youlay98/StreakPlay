# Troubleshooting Guide

## Frontend Connection Issue (Fixed)

**Issue:** `localhost:5173` was not working.
**Cause:** The Nginx configuration in the frontend container was trying to connect to a service named `backend` (from the old architecture), but the new service is named `gateway-service`.
**Fix:** Updated `frontend/nginx.conf` to point to `http://gateway-service:8080`.

## Current Status

All services are running correctly:

| Service | Port | Status |
|---------|------|--------|
| **Frontend** | 5173 | ✅ Running |
| **Gateway** | 8080 | ✅ Running |
| **Discovery** | 8761 | ✅ Healthy |
| **Auth Service** | 8081 | ✅ Running |
| **Game Service** | 8082 | ✅ Running |
| **AI Service** | 8083 | ✅ Running |
| **Databases** | 5433-35 | ✅ Healthy |

## How to Verify

1. **Frontend:** Open http://localhost:5173
2. **Eureka Dashboard:** Open http://localhost:8761
3. **API Gateway:** Try http://localhost:8080/actuator/health (if enabled) or check logs.

## Common Commands

- **Restart Frontend:** `docker-compose up -d --build frontend`
- **View Logs:** `docker-compose logs -f frontend`
- **Check Status:** `docker-compose ps`
