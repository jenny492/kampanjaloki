package com.github.jenny492.kampanjaloki.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;

// Retrieves username, password and userrole for authentication

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	AppUserRepository repository;

	public UserDetailServiceImpl(AppUserRepository appUserRepository) {
		this.repository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getUserRole()));
		return user;
	}
}