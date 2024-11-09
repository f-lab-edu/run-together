package com.srltas.runtogether.common.exception;

import com.srltas.runtogether.common.exception.code.ErrorCode;

import lombok.Getter;

@Getter
public abstract class RunTogetherException extends RuntimeException {

	private final ErrorCode errorCode;

	public RunTogetherException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
