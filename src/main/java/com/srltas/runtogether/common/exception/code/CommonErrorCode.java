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
	INVALID_HEADER_REQUEST(-5, "올바르지 않은 헤더 요청입니다."),
	INVALID_REQUEST_BODY(-6, "요청 본문을 읽을 수 없습니다."),
	METHOD_NOT_ALLOWED(-7, "지원되지 않는 HTTP 메서드입니다."),
	UNSUPPORTED_MEDIA_TYPE(-8, "지원되지 않는 미디어 타입입니다."),
	MISSING_PARAMETER(-9, "필수 요청 파라미터가 누락되었습니다."),
	TYPE_MISMATCH(-10, "요청 파라미터의 타입이 일치하지 않습니다."),
	BINDING_ERROR(-11, "요청 데이터 바인딩 중 오류가 발생했습니다."),
	CONSTRAINT_VIOLATION(-12, "제약 조건을 위반했습니다."),
	NO_HANDLER_FOUND(-13, "요청에 대한 핸들러를 찾을 수 없습니다.");

	private final int code;
	private final String message;
}
