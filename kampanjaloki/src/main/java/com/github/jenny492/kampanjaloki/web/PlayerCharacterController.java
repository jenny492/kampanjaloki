package com.github.jenny492.kampanjaloki.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignCharacterRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@Controller
public class PlayerCharacterController {

    PlayerCharacterRepository cRepository;
    CampaignCharacterRepository ccRepository;
    CampaignRepository campaignRepository;

    public PlayerCharacterController(PlayerCharacterRepository cRepository, CampaignRepository campaignRepository,
    CampaignCharacterRepository ccRepository) {
        this.cRepository = cRepository;
        this.campaignRepository = campaignRepository;
        this.ccRepository = ccRepository;
    }

    @PostMapping(value = "/addCharacterToCampaign")
    public String addCharacterToCampaign(@RequestParam("campaignid") Long campaignid, @RequestParam("characterid") Long characterid) {
        Campaign campaign = campaignRepository.findById(campaignid)
        .orElseThrow(() -> new NotFoundException("Campaign not found"));
        PlayerCharacter character = cRepository.findById(characterid)
        .orElseThrow(() -> new NotFoundException("Character not found"));

        // Check if character is already in the campaign
        if(ccRepository.existsByCharacterAndCampaign(character, campaign)) {
            return "redirect:/dashboard";
        }

        CampaignCharacter campaignCharacter = new CampaignCharacter(character, campaign);
        ccRepository.save(campaignCharacter);
        return "redirect:/dashboard";
    }


}
