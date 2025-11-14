package com.github.jenny492.kampanjaloki.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
// Makes sure that table contains only unique character and campaign pairs
@Table(name = "campaign_characters", uniqueConstraints = 
{@UniqueConstraint(columnNames = {"character_id", "campaign_id"})})
public class CampaignCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_character_id", nullable = false, updatable = false)
    private Long campaigncharacterid;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private PlayerCharacter character;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
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
