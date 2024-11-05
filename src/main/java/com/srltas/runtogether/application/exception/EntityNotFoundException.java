package com.srltas.runtogether.application.exception;

import com.srltas.runtogether.common.exception.RunTogetherException;
import com.srltas.runtogether.common.exception.code.ErrorCode;

public class EntityNotFoundException extends RunTogetherException {

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
