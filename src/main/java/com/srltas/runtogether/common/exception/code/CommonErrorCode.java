package com.srltas.runtogether.common.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

	SERVER_ERROR(-1, "서버 내부에서 처리 중 에러가 발생했습니다."),
	NO_RESOURCE_FOUND(-2, "요청한 리소스를 찾을 수 없습니다."),
	INVALID_REQUEST_PARAMETER(-3, "잘못된 파라미터입니다."),
	UNAUTHORIZED_REQUEST(-4, "해당 API에 대한 요청 권한이 없습니다."),
	INVALID_HEADER_REQUEST(-5, "올바르지 않은 헤더 요청입니다.");

	private final int code;
	private final String message;
}
