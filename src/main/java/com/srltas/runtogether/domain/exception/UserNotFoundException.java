package com.srltas.runtogether.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super("User not found");
	}
}
