package com.srltas.runtogether.adapter.out.exception;

import static com.srltas.runtogether.domain.model.user.exception.code.UserErrorCode.*;

public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
