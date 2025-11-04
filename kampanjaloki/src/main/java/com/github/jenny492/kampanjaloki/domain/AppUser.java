package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
// @Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // @Column(name = "user_id", nullable = false, updatable = false)
    private Long userid;

    @NotBlank(message = "Username is mandatory")
    // @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    // @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotNull(message = "Time created missing")
    private LocalDateTime created_at;

    @NotBlank(message = "User role missing")
    private String userrole;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerCharacter> characters;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campaign> ownedCampaigns;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignMember> campaignMembers;

    public AppUser() {
    }

    public AppUser(String username, String passwordHash, LocalDateTime created_at, String userrole) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.created_at = created_at;
        this.userrole = userrole;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long id) {
        this.userid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public List<Campaign> getOwnedCampaigns() {
        return ownedCampaigns;
    }

    public void setOwnedCampaigns(List<Campaign> ownedCampaigns) {
        this.ownedCampaigns = ownedCampaigns;
    }

    public List<CampaignMember> getCampaignMembers() {
        return campaignMembers;
    }

    public void setCampaignMembers(List<CampaignMember> campaignMembers) {
        this.campaignMembers = campaignMembers;
    }

    @Override
    public String toString() {
        return "AppUser [id=" + userid + ", username=" + username + ", passwordHash=" + passwordHash + ", created_at="
                + created_at + ", userrole=" + userrole + ", ownedCampaigns="
                + ownedCampaigns + ", campaignMembers=" + campaignMembers + "]";
    }

}
