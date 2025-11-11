package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long campaignid;

    @NotBlank(message = "Campaign name is mandatory")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Campaign create time is mandatory")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignCharacter> campaignCharacters;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignMember> campaignMembers;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSession> sessions;

    public Campaign() {
    }

    public Campaign(String name, String description, String image_url, LocalDateTime created_at, AppUser owner) {
        this.name = name;
        this.description = description;
        this.imageUrl = image_url;
        this.createdAt = created_at;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = created_at;
    }

    public Long getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Long id) {
        this.campaignid = id;
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
        return "Campaign [id=" + campaignid + ", name=" + name + ", description=" + description + ", image_url="
                + imageUrl
                + ", created_at=" + createdAt + ", owner=" + owner + ", sessions=" + sessions + ", campaignCharacters="
                + campaignCharacters + "]";
    }

}
