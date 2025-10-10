package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;

@Entity
public class CampaignCharacter {

    private PlayerCharacter character_id;
    private Campaign campaign_id;

    public CampaignCharacter() {
    }

    public CampaignCharacter(PlayerCharacter character_id, Campaign campaign_id) {
        this.character_id = character_id;
        this.campaign_id = campaign_id;
    }

    public PlayerCharacter getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(PlayerCharacter character_id) {
        this.character_id = character_id;
    }

    public Campaign getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Campaign campaign_id) {
        this.campaign_id = campaign_id;
    }

    @Override
    public String toString() {
        return "CampaignCharacter [character_id=" + character_id + ", campaign_id=" + campaign_id + "]";
    }



    
}
