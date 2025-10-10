package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campaignid;
    private String name;
    private String description;
    private String image_url;
    private LocalDateTime created_at;
    private AppUser owner_user_id;
    
    public Campaign() {
    }

    public Campaign(String name, String description, String image_url, LocalDateTime created_at, AppUser owner_user_id) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
        this.owner_user_id = owner_user_id;
    }

    public Long getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Long campaignid) {
        this.campaignid = campaignid;
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

    public AppUser getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(AppUser owner_user_id) {
        this.owner_user_id = owner_user_id;
    }
    
    @Override
    public String toString() {
        return "Campaign [campaignid=" + campaignid + ", name=" + name + ", description=" + description + ", image_url="
                + image_url + ", created_at=" + created_at + "]";
    }
  
}
