package com.gamenight.modules.challenges;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeRepository repository;

    @GetMapping
    public List<Challenge> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Challenge create(@RequestBody Challenge challenge) {
        // In a real app, we'd use a DTO and validation
        return repository.save(challenge);
    }
}
