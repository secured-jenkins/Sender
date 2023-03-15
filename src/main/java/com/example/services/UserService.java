package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.UserT;
import com.example.persistence.UserDAO;
import com.example.persistence.entities.DAOUser;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder bcrypt;
	public boolean saveNewUser(UserT user) {
		try {
			userDAO.save(new DAOUser(user.getUsername(), bcrypt.encode(user.getPassword())));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
