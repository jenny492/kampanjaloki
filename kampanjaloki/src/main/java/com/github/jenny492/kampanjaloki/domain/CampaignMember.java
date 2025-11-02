package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotNull;

@Entity
public class CampaignMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignmemberid;

    @ManyToOne
    @JoinColumn
    @NotNull
    private AppUser userid;
    
    @ManyToOne
    @JoinColumn
    @NotNull
    private Campaign campaignid;

    public CampaignMember() {
    }
    
    public CampaignMember(AppUser userid, Campaign campaignid) {
        this.userid = userid;
        this.campaignid = campaignid;
    }
    
    public AppUser getUserid() {
        return userid;
    }
    
    public void setUserid(AppUser user) {
        this.userid = user;
    }
    
    public Campaign getCampaignid() {
        return campaignid;
    }
    
    public void setCampaignid(Campaign campaign) {
        this.campaignid = campaign;
    }
    
    @Override
    public String toString() {
        return "CampaignMembers [user=" + userid + ", campaign=" + campaignid + "]";
    }

    public Long getCampaignmemberid() {
        return campaignmemberid;
    }

    public void setCampaignmemberid(Long campaignmemberid) {
        this.campaignmemberid = campaignmemberid;
    }

    

}
