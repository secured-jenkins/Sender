package com.example.persistence.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table
public class OAuthUser implements OAuth2User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	
	@ElementCollection(targetClass = Object.class)
//	@CollectionTable(name = "user_attributes", joinColumns = @JoinColumn(name = "user_id"))
//	@MapKeyJoinColumn(name="user_id")
	private Map<String, Object> attributes = new HashMap<>();
	private Collection<? extends GrantedAuthority> authorities;

	public OAuthUser() {

	}

	public OAuthUser(String name, Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.attributes = attributes;
		this.authorities = authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public String toString() {
		return "email is: " + this.name + "\nattributes are: " + attributes.toString() + "Authorities are:"
				+ authorities.toString();
	}

}
