package com.srltas.runtogether.common;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class AuthConstants {
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER_TOKEN_PREFIX = "Bearer ";
	public static final int BEARER_TOKEN_LENGTH = 7;
}
