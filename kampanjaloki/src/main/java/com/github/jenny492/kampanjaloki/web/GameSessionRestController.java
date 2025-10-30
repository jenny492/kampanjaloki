package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionRestController {

    private GameSessionRepository repo;

    @GetMapping
    public List<GameSession> getAllSessions() {
        return (List<GameSession>) repo.findAll();
    }

    @GetMapping("/{id}")
    public GameSession getSessionById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found"));
    }
}
