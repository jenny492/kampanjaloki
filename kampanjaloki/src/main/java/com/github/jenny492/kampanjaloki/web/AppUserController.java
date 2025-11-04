package com.github.jenny492.kampanjaloki.web;

import java.util.List;

import jakarta.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.exception.ConflictException;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private AppUserRepository repo;
    private CampaignRepository cRepo;

    public AppUserController(AppUserRepository repo, CampaignRepository cRepo) {
        this.repo = repo;
        this.cRepo = cRepo;
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return (List<AppUser>) repo.findAll();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createUser(@Valid @RequestBody AppUser user) {
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) throws BadRequestException {
        AppUser user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        boolean isLinkedToCampaign = cRepo.existsByOwner(user);
        if (isLinkedToCampaign) {
            throw new ConflictException("Can not delete user linked to existing campaign as an owner");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser updateUser(@PathVariable Long id, @Valid @RequestBody AppUser updatedUser) {
        return repo.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setPasswordHash(updatedUser.getPasswordHash());
                    user.setUserrole(updatedUser.getUserrole());
                    return repo.save(user);
                })
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
