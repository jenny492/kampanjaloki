package com.github.jenny492.kampanjaloki.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;

public interface CampaignCharacterRepository extends CrudRepository<CampaignCharacter, Long> {
    boolean existsByCharacterAndCampaign(PlayerCharacter character, Campaign campaign);

}
