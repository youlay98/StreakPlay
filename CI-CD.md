# CI/CD Pipeline Documentation

## Overview

This project implements a comprehensive CI/CD pipeline using **GitHub Actions** with the following requirements:
- ✅ **90% Code Coverage** minimum
- ✅ Unit & Integration Tests
- ✅ Code Quality Checks (Checkstyle)
- ✅ Security Scanning (OWASP, Gitleaks)
- ✅ Docker Build & Artifact Upload

## Pipeline Architecture

```
┌─────────────┐
│  Git Push   │
└──────┬──────┘
       │
       ▼
┌─────────────────────┐
│  GitHub Actions     │
│  ┌───────────────┐  │
│  │ 1. Checkout   │  │
│  └───────┬───────┘  │
│          │          │
│  ┌───────▼───────┐  │
│  │ 2. Run Tests  │  │
│  │  - Unit       │  │
│  │  - Integration│  │
│  └───────┬───────┘  │
│          │          │
│  ┌───────▼───────┐  │
│  │ 3. Coverage   │  │
│  │  (90% min)    │  │
│  └───────┬───────┘  │
│          │          │
│  ┌───────▼───────┐  │
│  │ 4. Quality    │  │
│  │  - Checkstyle │  │
│  │  - OWASP      │  │
│  │  - Gitleaks   │  │
│  └───────┬───────┘  │
│          │          │
│  ┌───────▼───────┐  │
│  │ 5. Build      │  │
│  │  - JAR        │  │
│  │  - Docker     │  │
│  └───────────────┘  │
└─────────────────────┘
```

## Workflows

### Service-Specific Pipelines

Each microservice has its own CI pipeline:

1. **`ci-discovery.yml`** - Discovery Service
2. **`ci-auth.yml`** - Auth Service  
3. **`ci-game.yml`** - Game Service
4. **`ci-ai-service.yml`** - AI Service

### Pipeline Stages

#### 1. **Checkout Code**
```yaml
- name: Checkout code
  uses: actions/checkout@v4
```

#### 2. **Setup Environment**
```yaml
- name: Set up JDK 21
  uses: actions/setup-java@v4
  with:
    java-version: '21'
    distribution: 'temurin'
    cache: 'maven'
```

#### 3. **Run Tests**
```yaml
- name: Run unit tests
  run: mvn test

- name: Run integration tests
  run: mvn verify
```

#### 4. **Code Coverage (90% Minimum)**
```yaml
- name: Generate code coverage report
  run: mvn jacoco:report

- name: Check code coverage (90% minimum)
  run: mvn jacoco:check -Drules.coverage.minimum=0.90
```

**Configuration in `pom.xml`:**
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <configuration>
        <rules>
            <rule>
                <element>PACKAGE</element>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.90</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</plugin>
```

#### 5. **Code Quality Checks**

**Checkstyle (Google Style Guide):**
```yaml
- name: Code quality - Checkstyle
  run: mvn checkstyle:check
```

**OWASP Dependency Check:**
```yaml
- name: Security scan - OWASP Dependency Check
  run: mvn org.owasp:dependency-check-maven:check
```

**Secret Scanning (Gitleaks):**
```yaml
- name: Secret scanning with Gitleaks
  uses: gitleaks/gitleaks-action@v2
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

#### 6. **Build Artifacts**
```yaml
- name: Build application
  run: mvn clean package -DskipTests

- name: Build Docker image
  run: docker build -t service-name:${{ github.sha }} .

- name: Upload artifact
  uses: actions/upload-artifact@v3
  with:
    name: service-jar
    path: target/*.jar
```

## Code Coverage Tools

### JaCoCo Configuration

Each service is configured with JaCoCo for code coverage:

- **Minimum Coverage**: 90%
- **Report Format**: XML (for Codecov)
- **Coverage Type**: Line coverage
- **Scope**: Package level

### Running Coverage Locally

```bash
# Generate coverage report
cd auth-service
mvn clean test jacoco:report

# View report
open target/site/jacoco/index.html

# Check if coverage meets 90%
mvn jacoco:check
```

## Quality Gates

The pipeline will **FAIL** if:
- ❌ Code coverage < 90%
- ❌ Tests fail
- ❌ Critical security vulnerabilities found
- ❌ Build fails

The pipeline will **WARN** (but continue) if:
- ⚠️ Checkstyle violations
- ⚠️ Non-critical OWASP issues

## Database Testing

Services with databases (Auth, Game, AI) use **PostgreSQL service containers** in GitHub Actions:

```yaml
services:
  postgres:
    image: postgres:15-alpine
    env:
      POSTGRES_USER: gamenight
      POSTGRES_PASSWORD: gamenight
      POSTGRES_DB: service_db
    ports:
      - 5432:5432
    options: >-
      --health-cmd pg_isready
      --health-interval 10s
      --health-timeout 5s
      --health-retries 5
```

## Triggers

Pipelines run on:
- **Push** to `main` or `develop` branches
- **Pull Requests** to `main` or `develop` branches
- **Path-specific**: Only when service files change

Example:
```yaml
on:
  push:
    branches: [ main, develop ]
    paths:
      - 'auth-service/**'
  pull_request:
    branches: [ main, develop ]
    paths:
      - 'auth-service/**'
```

## Secrets Management

Required GitHub Secrets:
- `GITHUB_TOKEN` - Auto-provided by GitHub
- `CODECOV_TOKEN` - For uploading coverage reports (optional)

## Artifacts

Each successful build produces:
- JAR file (uploaded to GitHub Actions artifacts)
- Docker image (tagged with commit SHA)
- Code coverage reports (uploaded to Codecov)

## Best Practices

1. **Technology Independence**: Uses Maven, works with any Java project
2. **Reproducible Builds**: Docker ensures consistent environments
3. **Fast Feedback**: Parallel test execution
4. **Security First**: Multiple security scans
5. **Quality Metrics**: Enforced code coverage and style

## Local Development

Before pushing, run locally:

```bash
# Run all checks
mvn clean verify jacoco:report jacoco:check checkstyle:check

# Quick test
mvn test

# Full build
mvn clean package
```

## Monitoring

View pipeline status:
- GitHub Actions tab in repository
- PR status checks
- Codecov dashboard (if configured)

## Future Enhancements

- [ ] Deploy to Kubernetes after successful build
- [ ] Performance testing
- [ ] Load testing
- [ ] Automated rollback on failure
- [ ] Slack/Email notifications
