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

import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class PlayerCharacterController {

    private PlayerCharacterRepository repo;

    public PlayerCharacterController(PlayerCharacterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<PlayerCharacter> getAllCharacters() {
        return (List<PlayerCharacter>) repo.findAll();
    }

    @GetMapping("/{id}")
    public PlayerCharacter getCharacterById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Character not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerCharacter createCharacter(@Valid @RequestBody PlayerCharacter character) {
        return repo.save(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Character not found");
        }
        repo.deleteById(id);
    }

}
