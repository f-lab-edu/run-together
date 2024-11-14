package com.srltas.runtogether.adapter.out.session.dao;

import lombok.Builder;

@Builder
public record UserSessionDAO(
	String userId
) {
}
