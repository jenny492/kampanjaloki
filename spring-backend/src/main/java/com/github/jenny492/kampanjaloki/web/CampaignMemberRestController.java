package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.CampaignMember;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.CampaignMemberRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaignmembers")
public class CampaignMemberRestController {

    private CampaignMemberRepository repo;

    public CampaignMemberRestController(CampaignMemberRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<CampaignMember> getAllCampaignMembers() {
        return (List<CampaignMember>) repo.findAll();
    }

    @GetMapping("/{id}")
    public CampaignMember getCampaignMembersById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign member not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignMember createCampaignMember(@Valid @RequestBody CampaignMember member) {
        return repo.save(member);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaignMemberById(@PathVariable Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Campaign member not found");
        }
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignMember updateCampaignMember(@PathVariable Long id,
            @Valid @RequestBody CampaignMember updatedCampaignMember) {
        return repo.findById(id)
                .map(campaignMember -> {
                    campaignMember.setCampaign(updatedCampaignMember.getCampaign());
                    ;
                    campaignMember.setuser(updatedCampaignMember.getuser());
                    ;
                    return repo.save(campaignMember);
                })
                .orElseThrow(() -> new NotFoundException("Campaign member not found"));
    }
}
