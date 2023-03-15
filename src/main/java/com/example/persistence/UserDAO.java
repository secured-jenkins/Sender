package com.example.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entities.DAOUser;

@Repository
public interface UserDAO extends JpaRepository<DAOUser, Long> {
	DAOUser findByUsername(String username);
}
