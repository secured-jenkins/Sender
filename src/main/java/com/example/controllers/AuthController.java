package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTO.EmployeeDTO;
import com.example.jwt.JwtTokenUtil;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.persistence.entities.OAuthUser;
import com.example.services.EmpService;
import com.example.services.OAuthUserService;

@RestController
public class AuthController {

	private Logger logger = Logger.getLogger(EmployeeController.class.getName());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private EmpService empService;
	
	@Autowired
	private OAuthUserService userService;

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

//	@GetMapping("/?continue")
//	public void catchForward(OAuth2AuthenticationToken token) {
//		System.out.println(token);
//	}

	@GetMapping("/getToken")
	public ResponseEntity<String> main(OAuth2AuthenticationToken token) {
		String email_username = token.getPrincipal().getAttribute("email");
		String firstName = token.getPrincipal().getAttribute("given_name");
		String lastName = token.getPrincipal().getAttribute("family_name");
		System.out.println(email_username);
		System.out.println(firstName);
		System.out.println(lastName);
		OAuthUser temp = userService.addUser(email_username, token.getPrincipal().getAttributes());
		System.out.println(temp.toString());
		return new ResponseEntity<String>(jwtTokenUtil.generateTokenSocialLogin(temp), HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/logined")
//	public String gotIt() {
//		return "Hello google";
//	}

}