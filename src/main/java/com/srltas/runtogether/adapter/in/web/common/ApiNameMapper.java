package com.srltas.runtogether.adapter.in.web.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiNameMapper {

	public String getApiName(String path, String method) {
		return ApiName.from(path, method)
			.map(ApiName::getName)
			.orElse("UNKNOWN_API_NAME");
	}
}
