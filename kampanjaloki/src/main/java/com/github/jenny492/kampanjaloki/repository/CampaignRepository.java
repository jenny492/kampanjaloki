package com.github.jenny492.kampanjaloki.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {

    boolean existsByOwner(AppUser owner);

}
