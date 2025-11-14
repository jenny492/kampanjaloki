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
import com.github.jenny492.kampanjaloki.domain.CampaignMember;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CampaignMemberRepositoryTest {

    @Autowired
    private CampaignMemberRepository cmRepository;

    @Autowired
    private CampaignRepository cRepository;

    @Autowired
    private AppUserRepository uRepository;

    private CampaignMember campaignMember;
    private Campaign campaign;
    private AppUser user;

    @BeforeEach
    public void setUp() throws Exception {
        campaign = new Campaign("name", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(campaign);

        user = new AppUser("username", "password", LocalDateTime.now(), "ROLE_USER");
        uRepository.save(user);

        campaignMember = new CampaignMember(user, campaign);
        cmRepository.save(campaignMember);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        cmRepository.deleteAll();
        cRepository.deleteAll();
        uRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewCampaignMember() throws Exception {
        assertTrue(campaignMember.getCampaignmemberid() != null);
    }

    @Test
    public void shouldFindCampaignMemberById() throws Exception {
        Long id = campaignMember.getCampaignmemberid();
        assertThat(cmRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteCampaignMember() throws Exception {
        Long id = campaignMember.getCampaignmemberid();
        cmRepository.delete(campaignMember);
        assertThat(cmRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditCampaignMember() throws Exception {
        Long id = campaignMember.getCampaignmemberid();
        CampaignMember found = cmRepository.findById(id).orElse(null);
        assertTrue(found != null);
        Campaign newCampaign = new Campaign("new campaign", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(newCampaign);
        found.setCampaign(newCampaign);
        cmRepository.save(found);

        CampaignMember edited = cmRepository.findById(id).orElse(null);
        assertTrue(edited != null);
        assertTrue(edited.getCampaign().getName().equals("new campaign"));
    }


}
