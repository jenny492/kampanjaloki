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
import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.domain.GameSession;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameEventRepositoryTest {

    @Autowired
    private GameEventRepository eRepository;

    @Autowired
    private CampaignRepository cRepository;

    @Autowired
    private GameSessionRepository sRepository;

    private GameEvent event;
    private Campaign campaign;
    private GameSession session;

    @BeforeEach
    public void setUp() throws Exception {
        campaign = new Campaign("name", "desc", "img-url", LocalDateTime.now(), null);
        cRepository.save(campaign);

        session = new GameSession(campaign, "title", "content", LocalDateTime.now(), LocalDateTime.now());
        sRepository.save(session); 
        
        event = new GameEvent(session, "content");
        eRepository.save(event);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        eRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewEvent() throws Exception {
        assertTrue(event.getEventid() != null);
    }

    @Test
    public void shouldFindEventById() throws Exception {
        Long id = event.getEventid();
        assertThat(eRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteEvent() throws Exception {
        Long id = event.getEventid();
        eRepository.delete(event);
        assertThat(eRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditEvent() throws Exception {
        Long id = event.getEventid();
        GameEvent found = eRepository.findById(id).orElse(null);
        assertTrue(found != null);
        found.setContent("new content");
        eRepository.save(found);
        assertTrue(found.getContent().equals("new content"));
    }

}
