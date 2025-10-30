package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.CampaignMember;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignMemberRepository;

@RestController
@RequestMapping("/api/campaigns/{id}")
public class CampaignMemberRestController {

    private CampaignMemberRepository repo;

    @GetMapping
    public List<CampaignMember> getAllCampaignMembers() {
        return (List<CampaignMember>) repo.findAll();
    }

    @GetMapping("/{id}")
    public CampaignMember getCampaignMembersById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign member not found"));
    }
}
