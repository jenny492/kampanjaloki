package com.github.jenny492.kampanjaloki.domain;

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
@Table(name = "campaign_members", uniqueConstraints = 
{@UniqueConstraint(columnNames = {"user_id", "campaign_id"})})
public class CampaignMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_member_id", nullable = false, updatable = false)
    private Long campaignmemberid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
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
