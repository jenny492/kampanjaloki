package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@RestController
@RequestMapping("/api/characters")
public class PlayerCharacterRestController {

    private PlayerCharacterRepository repo;

    @GetMapping
    public List<PlayerCharacter> getAllCharacters() {
        return (List<PlayerCharacter>) repo.findAll();
    }

    @GetMapping("/{id}")
    public PlayerCharacter getCharacterById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Character not found"));
    }

}
