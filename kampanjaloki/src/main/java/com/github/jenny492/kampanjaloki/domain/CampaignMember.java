package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CampaignMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private AppUser user;
    
    @ManyToOne
    @JoinColumn
    private Campaign campaign;
    
    private String role;
    
    public CampaignMember() {
    }
    
    public CampaignMember(AppUser user, Campaign campaign, String role) {
        this.user = user;
        this.campaign = campaign;
        this.role = role;
    }
    
    public AppUser getUser_id() {
        return user;
    }
    
    public void setUser(AppUser user) {
        this.user = user;
    }
    
    public Campaign getCampaign() {
        return campaign;
    }
    
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "CampaignMembers [user=" + user + ", campaign=" + campaign + ", role=" + role + "]";
    }

    

}
