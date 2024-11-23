package com.srltas.runtogether.adapter.out.session.dto;

import lombok.Builder;

@Builder
public record UserSessionDTO(
	String userId
) {
}
