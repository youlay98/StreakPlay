package com.gamenight.game;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeRepository repository;

    @GetMapping
    public List<Challenge> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Challenge create(@RequestBody Challenge challenge) {
        return repository.save(challenge);
    }
}
