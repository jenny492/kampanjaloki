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

import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class GameEventController {

    private GameEventRepository repo;

    public GameEventController(GameEventRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<GameEvent> getAllEvents() {
        return (List<GameEvent>) repo.findAll();
    }

    @GetMapping("/{id}")
    public GameEvent getEventById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameEvent createEvent(@Valid @RequestBody GameEvent event) {
        return repo.save(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Event not found");
        }
        repo.deleteById(id);
    }
}
