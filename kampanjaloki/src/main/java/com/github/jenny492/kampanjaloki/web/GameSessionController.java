package com.github.jenny492.kampanjaloki.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

import org.springframework.ui.Model;

@Controller
public class GameSessionController {

    private final GameSessionRepository repo;
    private final CampaignRepository campaignRepo;

    public GameSessionController(GameSessionRepository repo, CampaignRepository campaignRepo) {
        this.repo = repo;
        this.campaignRepo = campaignRepo;
    }

    @GetMapping(value = { "/addsession" })
    public String addSession(Model model, @RequestParam(name = "campaignid", required = false) Long campaignid) {
        
        return "addsession";
    }

    @PostMapping(value = "/savesession")
    public String saveSession(@ModelAttribute("session") GameSession session) {
        LocalDateTime timeNow = LocalDateTime.now();
        
        session.setCreated_at(timeNow);
        session.setUpdated_at(timeNow);
        repo.save(session);
        return "redirect:/dashboard";
    }

}
