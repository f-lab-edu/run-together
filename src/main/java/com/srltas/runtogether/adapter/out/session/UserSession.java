package com.srltas.runtogether.adapter.out.session;

import lombok.Builder;

@Builder
public record UserSession(
	String userId
) {
}
