package com.github.jenny492.kampanjaloki.web;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

@Controller
public class CampaignController {

    CampaignRepository cRepository;
    AppUserRepository uRepository;

    public CampaignController(CampaignRepository cRepository, AppUserRepository uRepository) {
        this.cRepository = cRepository;
        this.uRepository = uRepository;
    }

    @GetMapping(value = { "/addcampaign" })
    public String addCampaign(Model model) {
        model.addAttribute("campaign", new Campaign());
        return "addcampaign";
    }

    @PostMapping(value = "/savecampaign")
    public String saveCampaign(Campaign campaign) {
        LocalDateTime timeNow = LocalDateTime.now();
        campaign.setCreatedAt(timeNow);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currUserName = auth.getName();
        AppUser user = uRepository.findByUsername(currUserName);
        campaign.setOwner(user);

        cRepository.save(campaign);
        return "redirect:/dashboard";
    }

    @PostMapping(value = "/saveEditedCampaign")
    public String saveEditedCampaign(Campaign campaign) {
        Campaign existing = cRepository.findById(campaign.getCampaignid()).orElse(null);
        if (existing != null) {
            existing.setName(campaign.getName());
            existing.setDescription(campaign.getDescription());
            existing.setImageUrl(campaign.getImageUrl());
            cRepository.save(existing);
        }
        return "redirect:/dashboard";
    }

    @GetMapping(value = "editcampaign/{id}")
    public String editcampaign(@PathVariable("id") Long id, Model model) {
        Campaign campaign = cRepository.findById(id).orElse(null);
        model.addAttribute("campaign", campaign);
        return "editcampaign";
    }

    @GetMapping(value = "/deletecampaign/{id}")
    public String deleteCampaign(@PathVariable("id") Long id) {
        cRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}
