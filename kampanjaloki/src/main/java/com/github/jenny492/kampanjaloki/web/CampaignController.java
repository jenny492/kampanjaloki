package com.github.jenny492.kampanjaloki.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

@Controller
public class CampaignController {

    @Autowired
    private CampaignRepository cRepository;
    @Autowired
    private AppUserRepository uRepository;

    public CampaignController(CampaignRepository cRepository, AppUserRepository uRepository) {
        this.cRepository = cRepository;
        this.uRepository = uRepository;
    }

    @RequestMapping(value = { "/" })
    public String campaignList(Model model) {
        model.addAttribute("campaigns", cRepository.findAll());
        return "index";
    }

    @Bean
    public CommandLineRunner demo(CampaignRepository cRepository, AppUserRepository uRepository) {
        return (args) -> {
            LocalDateTime testTimeNow = LocalDateTime.now();

            AppUser testUser = new AppUser("username", "password", testTimeNow, "USER");
            uRepository.save(testUser);
            Campaign testCampaign = new Campaign("testi", "desc", "img-url", testTimeNow, testUser);
            cRepository.save(testCampaign);
        };
    }
}
