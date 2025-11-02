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
    private PlayerCharacter characterid;
    
    @ManyToOne
    @JoinColumn
    @NotNull
    private Campaign campaignid;

    public CampaignCharacter() {
    }

    public CampaignCharacter(PlayerCharacter characterid, Campaign campaignid) {
        this.characterid = characterid;
        this.campaignid = campaignid;
    }

    public PlayerCharacter getCharacterid() {
        return characterid;
    }

    public void setCharacterid(PlayerCharacter character_id) {
        this.characterid = character_id;
    }

    public Campaign getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Campaign campaign) {
        this.campaignid = campaign;
    }

    @Override
    public String toString() {
        return "CampaignCharacter [character=" + characterid + ", campaign=" + campaignid + "]";
    }

    public Long getCampaigncharacterid() {
        return campaigncharacterid;
    }

    public void setCampaigncharacterid(Long id) {
        this.campaigncharacterid = id;
    }



    
}
