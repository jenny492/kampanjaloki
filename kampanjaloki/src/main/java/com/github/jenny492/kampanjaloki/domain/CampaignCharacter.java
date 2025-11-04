package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class CampaignCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaigncharacterid;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "")
    private PlayerCharacter character;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Campaign campaign;

    public CampaignCharacter() {
    }

    public CampaignCharacter(PlayerCharacter character, Campaign campaign) {
        this.character = character;
        this.campaign = campaign;
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PlayerCharacter character_id) {
        this.character = character_id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public String toString() {
        return "CampaignCharacter [character=" + character + ", campaign=" + campaign + "]";
    }

    public Long getCampaigncharacterid() {
        return campaigncharacterid;
    }

    public void setCampaigncharacterid(Long id) {
        this.campaigncharacterid = id;
    }

}
