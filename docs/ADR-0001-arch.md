# ADR-0001: Modular Monolith Architecture

## Context
We are building "DevOps Game Night", a learning platform. We need a system that is stable, easy to understand, and easy to change. We anticipate future growth but want to avoid premature complexity.

## Decision
We will use a **Modular Monolith** architecture.
- **Single Deployable Unit**: One Spring Boot application.
- **Logical Modules**: Code is organized by domain (users, challenges, etc.) with strict boundaries.
- **In-Memory Messaging**: Events are handled in-process initially.

## Consequences
### Positive
- **Simplicity**: Easy to run, debug, and deploy (one container).
- **Refactoring**: Changing boundaries is a code change, not a network change.
- **Performance**: No network overhead for internal calls.

### Negative
- **Scaling**: Must scale the whole app, not individual parts (acceptable for now).
- **Tech Stack**: Locked into Java for the backend core.
