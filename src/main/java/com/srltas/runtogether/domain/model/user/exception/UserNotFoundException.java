package com.srltas.runtogether.domain.model.user.exception;

import static com.srltas.runtogether.domain.model.user.exception.code.UserErrorCode.*;

import com.srltas.runtogether.common.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
