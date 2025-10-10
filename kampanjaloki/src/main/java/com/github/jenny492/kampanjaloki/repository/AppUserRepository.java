package com.github.jenny492.kampanjaloki.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jenny492.kampanjaloki.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

}
