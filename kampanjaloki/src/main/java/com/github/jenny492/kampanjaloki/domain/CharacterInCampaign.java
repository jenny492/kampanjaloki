package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CharacterInCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cicid;

}
