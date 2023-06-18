package com.example.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entities.OAuthUser;
@Repository
public interface OAuthUserRepo extends JpaRepository<OAuthUser, Long>{
	OAuthUser findByName(String name);
}
