package com.gamenight.modules.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository repository;

    @GetMapping
    public List<Message> getMessages(@RequestParam String userId) {
        return repository.findByUserIdOrderByCreatedAtAsc(userId);
    }
}
