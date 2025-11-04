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
    private AppUser user;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Campaign campaign;

    public CampaignMember() {
    }

    public CampaignMember(AppUser user, Campaign campaign) {
        this.user = user;
        this.campaign = campaign;
    }

    public AppUser getuser() {
        return user;
    }

    public void setuser(AppUser user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public String toString() {
        return "CampaignMembers [user=" + user + ", campaign=" + campaign + "]";
    }

    public Long getCampaignmemberid() {
        return campaignmemberid;
    }

    public void setCampaignmemberid(Long campaignmemberid) {
        this.campaignmemberid = campaignmemberid;
    }

}
