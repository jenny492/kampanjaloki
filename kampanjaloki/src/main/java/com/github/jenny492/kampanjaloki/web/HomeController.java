package com.github.jenny492.kampanjaloki.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignCharacterRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@Controller
public class HomeController {

    @Autowired
    private CampaignRepository cRepository;
    @Autowired
    private AppUserRepository uRepository;
    @Autowired
    private GameSessionRepository sRepository;
    @Autowired
    private GameEventRepository eRepository;
    @Autowired
    private PlayerCharacterRepository charRepository;
    //@Autowired
    //private CampaignCharacterRepository ccRepository;

    public HomeController(CampaignRepository cRepository, AppUserRepository uRepository,
            GameSessionRepository sRepository, GameEventRepository eRepository, CampaignCharacterRepository ccRepository) {
        this.cRepository = cRepository;
        this.uRepository = uRepository;
    }

    @GetMapping(value = { "/" })
    public String home() {
        return "index";
    }

    @GetMapping(value = { "/dashboard" })
    public String dashboard(Model model) {
        model.addAttribute("campaigns", cRepository.findAll());
        model.addAttribute("users", uRepository.findAll());
        model.addAttribute("sessions", sRepository.findAll());
        model.addAttribute("events", eRepository.findAll());
        model.addAttribute("characters", charRepository.findAll());
        //model.addAttribute("campaigncharacters", ccRepository.findAll());
        return "dashboard";
    }

    @GetMapping(value = { "/addcampaign" })
    public String addCampaign(Model model) {
        model.addAttribute("campaign", new Campaign());
        return "addcampaign";
    }

    @PostMapping("/savecampaign")
    public String saveCampaign(Campaign campaign) {
        LocalDateTime timeNow = LocalDateTime.now();
        campaign.setCreated_at(timeNow);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currUserName = auth.getName();
        AppUser user = uRepository.findByUsername(currUserName);
        campaign.setOwner(user);

        cRepository.save(campaign);
        return "redirect:/dashboard";
    }

}
