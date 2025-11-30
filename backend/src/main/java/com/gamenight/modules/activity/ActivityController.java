package com.gamenight.modules.activity;

import com.gamenight.shared.MessagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityLogRepository repository;
    private final MessagingPort messagingPort;

    @GetMapping
    public List<ActivityLog> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public ActivityLog logActivity(@RequestBody ActivityLog log) {
        ActivityLog saved = repository.save(log);
        messagingPort.publish("activity.logged", saved);
        return saved;
    }
}
