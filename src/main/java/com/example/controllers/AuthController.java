package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTO.EmployeeDTO;
import com.example.jwt.JwtTokenUtil;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.services.EmpService;

@RestController
public class AuthController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private EmpService empService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> addEmp(@RequestBody JwtRequest user) {
		String username = user.getUsername();
		UserDetails temp = empService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(temp);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<EmployeeDTO> addUser(@RequestBody EmployeeDTO employee) {
		return new ResponseEntity<EmployeeDTO>(empService.addEmployee(employee), HttpStatus.CREATED);
	}
}