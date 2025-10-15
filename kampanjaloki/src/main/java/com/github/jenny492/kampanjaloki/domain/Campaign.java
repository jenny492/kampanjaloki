package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image_url;
    private LocalDateTime created_at;
    
    @ManyToOne
    private AppUser owner;
  
    @OneToMany
    private List<CampaignCharacter> campaignCharacters;

    @OneToMany
    private List<CampaignMember> campaignMembers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaign")
    @JsonIgnore
    private List<GameSession> sessions;
    
    public Campaign() {
    }

    public Campaign(String name, String description, String image_url, LocalDateTime created_at, AppUser owner) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
        this.owner = owner;
    }

    public List<GameSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession> sessions) {
        this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public List<CampaignCharacter> getCampaignCharacters() {
        return campaignCharacters;
    }

    public void setCampaignCharacters(List<CampaignCharacter> campaignCharacters) {
        this.campaignCharacters = campaignCharacters;
    }

    public List<CampaignMember> getCampaignMembers() {
        return campaignMembers;
    }

    public void setCampaignMembers(List<CampaignMember> campaignMembers) {
        this.campaignMembers = campaignMembers;
    }

    @Override
    public String toString() {
        return "Campaign [id=" + id + ", name=" + name + ", description=" + description + ", image_url=" + image_url
                + ", created_at=" + created_at + ", owner=" + owner + ", sessions=" + sessions + ", campaignCharacters="
                + campaignCharacters + "]";
    }


    
}
