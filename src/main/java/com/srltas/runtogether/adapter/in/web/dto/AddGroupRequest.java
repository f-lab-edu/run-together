package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddGroupRequest(

	@NotNull(message = "동네 ID는 필수입니다.")
	String neighborhoodId,

	@NotNull(message = "그룹 이름은 필수입니다.")
	@Size(min = 2, max = 255, message = "그룹 이름은 2자 이상 255자 이하로 입력해야 합니다.")
	String groupName,

	@Size(max = 2000, message = "그룹 설명은 최대 2000자까지 입력할 수 있습니다.")
	String groupDescription
) {
}
