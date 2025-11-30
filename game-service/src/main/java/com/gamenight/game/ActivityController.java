package com.gamenight.game;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityLogRepository repository;

    @GetMapping
    public List<ActivityLog> findAll() {
        return repository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<ActivityLog> findByUserId(@PathVariable String userId) {
        return repository.findByUserId(userId);
    }

    @PostMapping
    public ActivityLog create(@RequestBody CreateActivityRequest request) {
        ActivityLog log = ActivityLog.create(request.userId(), request.challengeId(), request.notes());
        return repository.save(log);
    }

    record CreateActivityRequest(String userId, String challengeId, String notes) {
    }
}
