# Learnings

This document tracks the DevOps concepts and patterns implemented in this project.

## CI/CD
- **GitHub Actions**: Used for continuous integration and delivery.
- **Matrix Builds**: Testing across multiple Node/Python versions.
- **Docker Layer Caching**: Optimizing build times.

## Security
- **Trivy**: Scanning container images for vulnerabilities.
- **CodeQL**: Static analysis for code security.
- **Least Privilege**: Non-root users in Docker containers.

## Observability
- **Structured Logging**: JSON logs for machine parsing.
- **Prometheus & Grafana**: Metrics collection and visualization.
