package com.github.jenny492.kampanjaloki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@Controller
public class CampaignController {

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

    public CampaignController(CampaignRepository cRepository, AppUserRepository uRepository,
    GameSessionRepository sRepository, GameEventRepository eRepository) {
        this.cRepository = cRepository;
        this.uRepository = uRepository;
    }

    // Transmits the data to thymeleaf page
    @RequestMapping(value = { "/" })
    public String campaignList(Model model) {
        model.addAttribute("campaigns", cRepository.findAll());
        model.addAttribute("users", uRepository.findAll());
        model.addAttribute("sessions", sRepository.findAll());
        model.addAttribute("events", eRepository.findAll());
        model.addAttribute("characters", charRepository.findAll());
        return "index";
    }

    
}
