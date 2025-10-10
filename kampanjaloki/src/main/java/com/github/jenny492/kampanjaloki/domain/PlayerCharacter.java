package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long characterid;
    private String name;
    private String description;
    private String image_url;
    private String link;

    public PlayerCharacter () {

    }

    public PlayerCharacter(String name, String description, String image_url, String link) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.link = link;
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
        return "Character [characterid=" + characterid + ", name=" + name + ", description=" + description
                + ", image_url=" + image_url + ", link=" + link + "]";
    }

}
