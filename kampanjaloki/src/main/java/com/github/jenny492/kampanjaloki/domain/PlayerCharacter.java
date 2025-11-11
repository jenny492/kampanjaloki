package com.github.jenny492.kampanjaloki.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "player_characters")
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id", nullable = false, updatable = false)
    private Long characterid;

    @NotBlank(message = "Character must have a name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "link")
    private String link;

    @ManyToOne
    @NotNull
    private AppUser owner;

    @OneToMany
    @JsonIgnore
    private List<CampaignCharacter> campaignCharacters;

    public PlayerCharacter() {
    }

    public PlayerCharacter(String name, String description, AppUser owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public Long getCharacterid() {
        return characterid;
    }

    public void setCharacterid(Long characterid) {
        this.characterid = characterid;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Character [characterid=" + characterid + ", name=" + name + ", description=" + description
                + ", imageUrl=" + imageUrl + ", link=" + link + "]";
    }

}
