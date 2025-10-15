package com.github.jenny492.kampanjaloki.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private String image_url;
    private String link;

    @OneToMany
    private List<CampaignCharacter> campaignCharacters;

    public PlayerCharacter () {
    }

    public PlayerCharacter(String name, String description, String image_url, String link) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.link = link;
    }

    public Long getCharacterid() {
        return id;
    }

    public void setCharacterid(Long characterid) {
        this.id = characterid;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Character [characterid=" + id + ", name=" + name + ", description=" + description
                + ", image_url=" + image_url + ", link=" + link + "]";
    }

}
