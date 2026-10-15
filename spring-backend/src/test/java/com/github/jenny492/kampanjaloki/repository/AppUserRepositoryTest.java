package com.github.jenny492.kampanjaloki.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.jenny492.kampanjaloki.domain.AppUser;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository uRepository;

    private AppUser user;

    @BeforeEach
    public void setUp() throws Exception {
        user = new AppUser("username", "password", LocalDateTime.now(), "role");
        uRepository.save(user);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        uRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        AppUser newUser = new AppUser("newuser", "newpassword", LocalDateTime.now(), "role");
        uRepository.save(newUser);

        Optional<AppUser> found = uRepository.findById(newUser.getUserid());
        assertTrue(found.isPresent());
        assertEquals(found.get().getUsername(), "newuser");
    }

    @Test
    public void shouldFindUserById() throws Exception {
        Long id = user.getUserid();
        assertThat(uRepository.findById(id).isPresent()).isTrue();
    }

    @Test
    public void shouldFindUserByUsername() throws Exception {
        AppUser found = uRepository.findByUsername("username");
        assertTrue(found != null);
        assertEquals(found.getUsername(), "username");
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        Long id = user.getUserid();
        uRepository.delete(user);
        assertThat(uRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    public void shouldEditUser() throws Exception {
        Long id = user.getUserid();
        AppUser foundUser = uRepository.findById(id).orElse(null);
        assertTrue(foundUser != null);
        foundUser.setUsername("new name");
        uRepository.save(foundUser);
        AppUser editedUser = uRepository.findById(id).orElse(null);
        assertTrue(editedUser != null);
        assertTrue(editedUser.getUsername().equals("new name"));
    }

}
