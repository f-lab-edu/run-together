package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteGroupRequest(

	@NotNull(message = "그룹 ID는 필수입니다.")
	String groupId
) {
}
