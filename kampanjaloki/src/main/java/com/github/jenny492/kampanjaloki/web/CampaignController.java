package com.github.jenny492.kampanjaloki.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

@Controller
public class CampaignController {

    CampaignRepository cRepository;

    public CampaignController(CampaignRepository cRepository) {
        this.cRepository = cRepository;
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCampaign(@PathVariable("id") Long id) {
        cRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}
