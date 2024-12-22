package com.srltas.runtogether.adapter.in.exception;

import com.srltas.runtogether.common.exception.code.ErrorCode;
import com.srltas.runtogether.common.exception.RunTogetherException;

public class UnauthorizedException extends RunTogetherException {

	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
