package com.github.jenny492.kampanjaloki.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

import org.springframework.ui.Model;

@Controller
public class GameSessionController {

    private final GameSessionRepository repository;
    private final CampaignRepository campaignRepository;

    public GameSessionController(GameSessionRepository repository, CampaignRepository campaignRepository) {
        this.repository = repository;
        this.campaignRepository = campaignRepository;
    }

    @GetMapping(value = { "/addsession" })
    public String addSession(Model model, @RequestParam(name = "campaignid", required = false) Long campaignid) {
        model.addAttribute("session", new GameSession());
        model.addAttribute("campaignid", campaignid);
        return "addsession";
    }

    @PostMapping(value = "/savesession")
    public String saveSession(@ModelAttribute("session") GameSession session, @RequestParam("campaignid") Long campaignid) {
        session.setCampaign(campaignRepository.findById(campaignid)
        .orElseThrow(() -> new NotFoundException("Campaign not found")));

        LocalDateTime timeNow = LocalDateTime.now();
        session.setCreated_at(timeNow);
        session.setUpdated_at(timeNow);
        
        repository.save(session);
        
        return "redirect:/dashboard";
    }

}
