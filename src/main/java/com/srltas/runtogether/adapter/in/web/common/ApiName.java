package com.srltas.runtogether.adapter.in.web.common;

import static com.srltas.runtogether.adapter.in.web.common.UrlConstants.*;
import static org.springframework.http.HttpMethod.*;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiName {

	VERIFY_USER_NEIGHBORHOOD(NEIGHBORHOOD_VERIFICATION, POST, "VERIFY_USER_NEIGHBORHOOD"),
	REGISTER_USER_NEIGHBORHOOD(USER_NEIGHBORHOOD_REGISTRATION, POST, "REGISTER_USER_NEIGHBORHOOD");

	private final String path;
	private final HttpMethod method;
	private final String name;

	public static Optional<ApiName> from(String path, String method) {
		return Arrays.stream(values())
			.filter(apiName -> apiName.path.equals(path) && apiName.method.matches(method))
			.findFirst();
	}
}
