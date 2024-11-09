package com.srltas.runtogether.domain.model.user.exception.code;

import com.srltas.runtogether.common.exception.code.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	USER_NOT_FOUND(-101, "해당 사용자를 찾을 수 없습니다.");

	private final int code;
	private final String message;
}
