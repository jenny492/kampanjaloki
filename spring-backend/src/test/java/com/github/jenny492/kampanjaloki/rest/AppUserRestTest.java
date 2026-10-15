package com.github.jenny492.kampanjaloki.rest;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AppUserRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppUserRepository uRepository;

    @Test
    @WithMockUser
    public void getUserShouldReturnOkAndContainUsername() throws Exception {
        AppUser user = new AppUser("user", "pass", LocalDateTime.now(), "USER");
        uRepository.save(user);
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("user")));
    }

    @Test
    public void getUserShouldReturnUnauthorizedIfNotLoggedIn() throws Exception {
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void getUserByIdShouldReturnCorrectId() throws Exception {
        AppUser user = new AppUser("user", "pass", LocalDateTime.now(), "USER");
        uRepository.save(user);
        Long id = user.getUserid();
        user.getUserid();
        this.mockMvc.perform(get("/api/users/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Long.toString(0))));
    }

    @Test
    @WithMockUser
    public void postUserShouldReturnCreated() throws Exception {
        String newUserJson = "{\"username\":\"newuser\",\"passwordHash\":\"newpass\",\"created_at\":\"2025-10-31T18:12:23.96437\",\"userRole\":\"USER\"}";
        this.mockMvc
                .perform(post("/api/users")
                        .contentType("application/json")
                        .content(newUserJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void deleteUserShouldReturnOk() throws Exception {
        AppUser user = new AppUser("user", "pass", LocalDateTime.now(), "USER");
        uRepository.save(user);
        Long id = user.getUserid();
        this.mockMvc.perform(delete("/api/users/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void putUserShouldReturnOk() throws Exception {
        AppUser user = new AppUser("user", "pass", LocalDateTime.now(), "USER");
        uRepository.save(user);
        Long id = user.getUserid();
        String editedUserJson = "{\"username\":\"editeduser\",\"passwordHash\":\"newpass\",\"created_at\":\"2025-10-31T18:12:23.96437\",\"userRole\":\"USER\"}";
        this.mockMvc
                .perform(put("/api/users/" + id)
                        .contentType("application/json")
                        .content(editedUserJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
