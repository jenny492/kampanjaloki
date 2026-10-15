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
import com.github.jenny492.kampanjaloki.domain.GameSession;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameSessionRepositoryTest {

    @Autowired
    private GameSessionRepository sRepository;

    @Autowired
    private CampaignRepository cRepository;

    private GameSession session;
    private Campaign campaign;

    @BeforeEach
    public void setUp() throws Exception {
        campaign = new Campaign("name", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(campaign);

        session = new GameSession(campaign, "title", "content", LocalDateTime.now(), LocalDateTime.now());
        sRepository.save(session); 
    }

    @AfterEach
    public void cleanUp() throws Exception {
        sRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewSession() throws Exception {
        assertTrue(session.getSessionid() != null);
    }

    @Test
    public void shouldFindSessionById() throws Exception {
        Long id = session.getSessionid();
        assertThat(sRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteSession() throws Exception {
        Long id = session.getSessionid();
        sRepository.delete(session);
        assertThat(sRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditSession() throws Exception {
        Long id = session.getSessionid();
        GameSession found = sRepository.findById(id).orElse(null);
        assertTrue(found != null);
        found.setContent("new content");
        sRepository.save(found);
        GameSession edited = sRepository.findById(id).orElse(null);
        assertTrue(edited != null);
        assertTrue(edited.getContent().equals("new content"));
    }

}
