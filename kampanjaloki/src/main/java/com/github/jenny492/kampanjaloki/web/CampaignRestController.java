package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignRestController {

    private CampaignRepository repo;

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return (List<Campaign>) repo.findAll();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign not found"));
    }

}
