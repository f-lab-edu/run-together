package com.srltas.runtogether.application.exception;

import static com.srltas.runtogether.domain.model.user.exception.code.UserErrorCode.*;

public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
