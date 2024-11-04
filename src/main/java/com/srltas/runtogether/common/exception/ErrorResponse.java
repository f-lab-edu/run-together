package com.srltas.runtogether.common.exception;

import com.srltas.runtogether.common.exception.code.ErrorCode;

public record ErrorResponse(
	int code,
	String msg
) {
	public static ErrorResponse of(int code, String msg) {
		return new ErrorResponse(code, msg);
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
	}
}
