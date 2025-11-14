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
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerCharacterRepositoryTest {

    @Autowired
    private PlayerCharacterRepository cRepository;

    @Autowired
    private AppUserRepository uRepository;

    private PlayerCharacter character;
    private AppUser user;

    @BeforeEach
    public void setUp() throws Exception {
        user = new AppUser("username", "password", LocalDateTime.now(), "USER");
        uRepository.save(user);

        character = new PlayerCharacter("name", "desc", user);
        cRepository.save(character);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        cRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewCharacter() throws Exception {
        assertTrue(character.getCharacterid() != null);
    }

    @Test
    public void shouldFindCharacterById() throws Exception {
        Long id = character.getCharacterid();
        assertThat(cRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldDeleteCharacter() throws Exception {
        Long id = character.getCharacterid();
        cRepository.delete(character);
        assertThat(cRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditCharacter() throws Exception {
        Long id = character.getCharacterid();
        PlayerCharacter found = cRepository.findById(id).orElse(null);
        assertTrue(found != null);
        found.setName("new name");
        cRepository.save(found);
        PlayerCharacter edited = cRepository.findById(id).orElse(null);
        assertTrue(edited != null);
        assertTrue(edited.getName().equals("new name"));
    }

}
