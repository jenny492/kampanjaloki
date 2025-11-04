package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignCharacterRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigncharacters")
public class CampaignCharacterController {

    private CampaignCharacterRepository repo;
    private CampaignRepository cRepo;
    private PlayerCharacterRepository charRepo;

    public CampaignCharacterController(CampaignCharacterRepository repo, CampaignRepository cRepo,
            PlayerCharacterRepository charRepo) {
        this.repo = repo;
        this.cRepo = cRepo;
        this.charRepo = charRepo;
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

    // TODO: tarvitseeko tähän tarkistuksen, että onko hahmo jo olemassa kampanjassa
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignCharacter createCampaignCharacter(@Valid @RequestBody CampaignCharacter campaignCharacter) {
        Campaign campaign = cRepo.findById(campaignCharacter.getCampaign().getCampaignid())
                .orElseThrow(() -> new NotFoundException("Campaign not found"));

        PlayerCharacter character = charRepo.findById(campaignCharacter.getCharacter().getCharacterid())
                .orElseThrow(() -> new NotFoundException("Character not found"));

        campaignCharacter.setCampaign(campaign);
        campaignCharacter.setCharacter(character);

        return repo.save(campaignCharacter);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaignCharacterById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Campaign character not found");
        }
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignCharacter updateCampaignCharacter(@PathVariable Long id,
            @Valid @RequestBody CampaignCharacter updatedCampaignCharacter) {
        return repo.findById(id)
                .map(campaignCharacter -> {
                    campaignCharacter.setCampaign(updatedCampaignCharacter.getCampaign());
                    campaignCharacter.setCharacter(updatedCampaignCharacter.getCharacter());
                    ;
                    return repo.save(campaignCharacter);
                })
                .orElseThrow(() -> new NotFoundException("Campaign character not found"));
    }

}
