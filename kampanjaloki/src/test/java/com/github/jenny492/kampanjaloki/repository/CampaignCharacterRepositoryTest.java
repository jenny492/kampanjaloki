package com.github.jenny492.kampanjaloki.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CampaignCharacterRepositoryTest {

    @Autowired
    private CampaignCharacterRepository ccRepository;

    @Autowired
    private CampaignRepository cRepository;

    @Autowired
    private PlayerCharacterRepository pcRepository;

    @Autowired
    private AppUserRepository uRepository;

    private CampaignCharacter campaignCharacter;
    private Campaign campaign;
    private PlayerCharacter character;
    private AppUser user;

    @BeforeEach
    public void setUp() throws Exception {
        campaign = new Campaign("name", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(campaign);

        user = new AppUser("username", "password", LocalDateTime.now(), "ROLE_USER");
        uRepository.save(user);

        character = new PlayerCharacter("character", "desc", user);
        pcRepository.save(character);

        campaignCharacter = new CampaignCharacter(character, campaign);
        ccRepository.save(campaignCharacter);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        ccRepository.deleteAll();
        cRepository.deleteAll();
        pcRepository.deleteAll();
        uRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewCampaignCharacter() throws Exception {
        assertTrue(campaignCharacter.getCampaigncharacterid() != null);
    }

    @Test
    public void shouldFindCampaignCharacterById() throws Exception {
        Long id = campaignCharacter.getCampaigncharacterid();
        assertThat(ccRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteCampaign() throws Exception {
        Long id = campaignCharacter.getCampaigncharacterid();
        ccRepository.delete(campaignCharacter);
        assertThat(cRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditCampaignCharacter() throws Exception {
        Long id = campaignCharacter.getCampaigncharacterid();
        CampaignCharacter found = ccRepository.findById(id).orElse(null);
        assertTrue(found != null);
        PlayerCharacter newCharacter = new PlayerCharacter("new character", "desc", user);
        pcRepository.save(newCharacter);
        found.setCharacter(newCharacter);
        ccRepository.save(found);
        CampaignCharacter edited = ccRepository.findById(id).orElse(null);
        assertTrue(edited != null);
        assertTrue(edited.getCharacter().getName().equals("new character"));
    }

}
