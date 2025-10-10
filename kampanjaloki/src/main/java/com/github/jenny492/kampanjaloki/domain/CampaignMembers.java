package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;

@Entity
public class CampaignMembers {

    private AppUser user_id;
    private Campaign campaign_id;
    private String role;
    
    public CampaignMembers() {
    }
    
    public CampaignMembers(AppUser user_id, Campaign campaign_id, String role) {
        this.user_id = user_id;
        this.campaign_id = campaign_id;
        this.role = role;
    }
    
    public AppUser getUser_id() {
        return user_id;
    }
    
    public void setUser_id(AppUser user_id) {
        this.user_id = user_id;
    }
    
    public Campaign getCampaign_id() {
        return campaign_id;
    }
    
    public void setCampaign_id(Campaign campaign_id) {
        this.campaign_id = campaign_id;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "CampaignMembers [user_id=" + user_id + ", campaign_id=" + campaign_id + ", role=" + role + "]";
    }

    

}
