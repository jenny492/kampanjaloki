package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignCharacterRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigncharacters")
public class CampaignCharacterRestController {

    private CampaignCharacterRepository repo;

    public CampaignCharacterRestController(CampaignCharacterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<CampaignCharacter> getAllCampaignCharacters() {
        return (List<CampaignCharacter>) repo.findAll();
    }

    @GetMapping("/{id}")
    public CampaignCharacter getCampaignCharacterById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign character not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignCharacter createCampaignCharacter(@Valid @RequestBody CampaignCharacter character) {
        return repo.save(character);
    }

}
