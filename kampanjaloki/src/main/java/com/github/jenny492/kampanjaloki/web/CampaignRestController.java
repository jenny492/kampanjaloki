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
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignRestController {

    private CampaignRepository repo;

    public CampaignRestController(CampaignRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return (List<Campaign>) repo.findAll();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign createCampaign(@Valid @RequestBody Campaign campaign) {
        return repo.save(campaign);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaignById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Campaign not found");
        }
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign updateCampaign(@PathVariable Long id, @Valid @RequestBody Campaign updatedCampaign) {
        return repo.findById(id)
                .map(campaign -> {
                    campaign.setName(updatedCampaign.getName());
                    campaign.setDescription(updatedCampaign.getDescription());
                    campaign.setImageUrl(updatedCampaign.getImageUrl());
                    campaign.setOwner(updatedCampaign.getOwner());
                    return repo.save(campaign);
                })
                .orElseThrow(() -> new NotFoundException("Campaign not found"));
    }

}
