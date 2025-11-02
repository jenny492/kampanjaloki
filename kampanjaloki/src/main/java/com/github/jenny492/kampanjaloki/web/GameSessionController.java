package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionController {

    private GameSessionRepository repo;

    public GameSessionController(GameSessionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<GameSession> getAllSessions() {
        return (List<GameSession>) repo.findAll();
    }

    @GetMapping("/{id}")
    public GameSession getSessionById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameSession createSession(@Valid @RequestBody GameSession session) {
        return repo.save(session);
    }

    @DeleteMapping("/{id}")
    public void deleteSessionById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Session not found");
        }
        repo.deleteById(id);
    }
}
