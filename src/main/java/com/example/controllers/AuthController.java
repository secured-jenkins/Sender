package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.JwtTokenUtil;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;

@RestController
	public class AuthController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> addEmp(@RequestBody JwtRequest user) {
		System.out.println("********************** I ENTERED **********************");
		String username = user.getUsername();
		String password = user.getPassword();
		authenticate(username, password);
		UserDetails temp = jwtInMemoryDetailsService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(temp);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.ACCEPTED);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException e) {
			System.out.println("Authentication not successful");
		}
	}
	
	@PostMapping(value = "/test")
	public ResponseEntity<JwtResponse> getIt(@RequestBody JwtRequest x){
		return new ResponseEntity<JwtResponse>(new JwtResponse(x.getUsername()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/hi")
	public String hi() {
		System.out.println("heLOO");
		return "HI";
	}
}
