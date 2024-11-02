package com.srltas.runtogether.domain.model.neighborhood.exception.code;

import com.srltas.runtogether.common.exception.code.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NeighborhoodErrorCode implements ErrorCode {

	NEIGHBORHOOD_NOT_FOUND(-301, "해당 동네를 찾을 수 없습니다."),
	NEIGHBORHOOD_LIMIT_EXCEEDED(-351, "내 동네는 최대 2개만 등록할 수 있습니다."),
	NEIGHBORHOOD_DUPLICATION(-352, "이미 내 동네로 등록된 동네입니다."),
	NEIGHBORHOOD_NOT_REGISTERED(-353, "내 동네로 등록되지 않은 동네입니다."),
	ALREADY_VERIFIED_NEIGHBORHOOD(-354, "이미 인증된 동네입니다."),
	OUT_OF_NEIGHBORHOOD_BOUNDARY(-355, "해당 동네 범위를 벗어났습니다.");

	private final int code;
	private final String message;
}
