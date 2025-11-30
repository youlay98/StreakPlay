package com.gamenight.modules.ai;

import com.gamenight.modules.activity.ActivityLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIService {

    private final MessageRepository messageRepository;

    @EventListener
    public void onActivityLogged(ActivityLog log) {
        // In a real app, this would call OpenAI
        // For now, we just generate a simple response
        String text = "Great job completing challenge " + log.getChallengeId() + "! Keep it up!";

        Message message = new Message(
                UUID.randomUUID().toString(),
                log.getUserId(),
                "AI",
                text,
                LocalDateTime.now());

        messageRepository.save(message);
    }
}
