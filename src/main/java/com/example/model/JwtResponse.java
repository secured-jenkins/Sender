package com.example.model;

public class JwtResponse {
	private final String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
