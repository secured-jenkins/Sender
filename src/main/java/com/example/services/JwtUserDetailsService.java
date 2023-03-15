package com.example.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.UserT;
import com.example.persistence.UserDAO;
import com.example.persistence.entities.DAOUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DAOUser toTest = userDAO.findByUsername(username);
		if (toTest == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			return new User(toTest.getUsername(), toTest.getPassword(),
					new ArrayList<>());
		}
	}

	
}
