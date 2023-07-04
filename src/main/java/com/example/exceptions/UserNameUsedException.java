package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username provided is already used")

public class UserNameUsedException extends RuntimeException {
	public UserNameUsedException(String username) {
		super(String.format("The username entered: %s, is already used.", username));
	}
}
