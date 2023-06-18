package com.example.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.persistence.OAuthUserRepo;
import com.example.persistence.entities.OAuthUser;

@Service
public class OAuthUserService {

	@Autowired
	private OAuthUserRepo userRepo;

	public OAuthUser addUser(String email, Map<String, Object> attributes) {
		OAuthUser temp = new OAuthUser(email, attributes, getNormalAuthorities());
		System.out.println("From oauth service: " + temp.toString());
		System.out.println("CHECKING::  " + checkUserExists(email));
		if (checkUserExists(email))
			return userRepo.findByName(email);
		else
			return userRepo.save(temp);
	}

	public boolean checkUserExists(String email) {
		OAuthUser temp = userRepo.findByName(email);
		return temp == null ? false : true;
	}

	private Collection<? extends GrantedAuthority> getNormalAuthorities() {
		List<GrantedAuthority> temp = new ArrayList<GrantedAuthority>();
		temp.add(new SimpleGrantedAuthority("Guest"));
		return temp;
	}

	public OAuthUser loadUserByEmail(String email) {
		return userRepo.findByName(email);
	}
}
