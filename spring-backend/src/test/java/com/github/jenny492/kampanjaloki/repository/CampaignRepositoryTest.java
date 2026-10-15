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

import com.github.jenny492.kampanjaloki.domain.Campaign;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CampaignRepositoryTest {

    @Autowired
    private CampaignRepository cRepository;

    private Campaign campaign;

    @BeforeEach
    public void setUp() throws Exception {
        campaign = new Campaign("name", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(campaign);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        cRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewCampaign() throws Exception {
        assertTrue(campaign.getCampaignid() != null);
    }

    @Test
    public void shouldFindCampaignById() throws Exception {
        Long id = campaign.getCampaignid();
        assertThat(cRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteCampaign() throws Exception {
        Long id = campaign.getCampaignid();
        cRepository.delete(campaign);
        assertThat(cRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditCampaign() throws Exception {
        Long id = campaign.getCampaignid();
        Campaign foundCampaign = cRepository.findById(id).orElse(null);
        assertTrue(foundCampaign != null);
        foundCampaign.setName("new name");
        cRepository.save(foundCampaign);
        Campaign editedCampaign = cRepository.findById(id).orElse(null);
        assertTrue(editedCampaign != null);
        assertTrue(editedCampaign.getName().equals("new name"));
    }


}
