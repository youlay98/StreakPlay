# Kafka Decision

## Current Status: NOT ADOPTED

## Rationale
We are defaulting to **No Kafka** to keep operations simple. Our current requirements do not justify the complexity of running and managing a Kafka cluster.

## Triggers for Adoption
We will adopt Kafka only if one of the following becomes true:
1.  **Volume**: > 10k events/day requiring cross-service fan-out.
2.  **Reliability**: Strict at-least-once delivery & replay requirements that Postgres outbox cannot handle efficiently.
3.  **Decoupling**: Multiple independent consumer services evolve at different speeds.

## Abstraction
We have implemented a `MessagingPort` interface.
- **Current Implementation**: `InMemoryMessagingAdapter` (Spring Events).
- **Future Implementation**: `KafkaMessagingAdapter` (can be enabled via Spring Profile).
