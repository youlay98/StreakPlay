package com.gamenight.shared;

public interface MessagingPort {
    void publish(String topic, Object event);
}
