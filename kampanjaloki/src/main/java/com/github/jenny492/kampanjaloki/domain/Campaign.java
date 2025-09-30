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
    
    public Campaign() {
    }

    public Campaign(String name, String description, String image_url, LocalDateTime created_at) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
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

    

}
