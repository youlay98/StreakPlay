package com.gamenight.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
public class InMemoryMessagingAdapter implements MessagingPort {

    private final ApplicationEventPublisher publisher;

    public InMemoryMessagingAdapter(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(String topic, Object event) {
        log.info("Publishing in-memory event to topic {}: {}", topic, event);
        publisher.publishEvent(event);
    }
}
