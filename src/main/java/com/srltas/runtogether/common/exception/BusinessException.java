package com.srltas.runtogether.common.exception;

import com.srltas.runtogether.common.exception.code.ErrorCode;

public abstract class BusinessException extends RunTogetherException {

	public BusinessException(ErrorCode errorCode) {
		super(errorCode);
	}
}
