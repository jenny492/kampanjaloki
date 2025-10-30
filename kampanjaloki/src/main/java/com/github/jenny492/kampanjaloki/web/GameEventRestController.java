package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;

@RestController
@RequestMapping("/api/events")
public class GameEventRestController {

    private GameEventRepository repo;

    @GetMapping
    public List<GameEvent> getAllEvents() {
        return (List<GameEvent>) repo.findAll();
    }

    @GetMapping("/{id}")
    public GameEvent getEventById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));
    }
}
