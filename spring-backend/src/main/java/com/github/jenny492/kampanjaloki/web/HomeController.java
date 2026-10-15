package com.github.jenny492.kampanjaloki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("gameSessions", sRepository.findAll());
        model.addAttribute("events", eRepository.findAll());
        model.addAttribute("characters", charRepository.findAll());
        //model.addAttribute("campaigncharacters", ccRepository.findAll());
        return "dashboard";
    }



}
