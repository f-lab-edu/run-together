package com.srltas.runtogether.adapter.in.web.common;

import java.util.Arrays;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlConstants {
	public final String ALL_URL = "/*";
	public final String USER_NEIGHBORHOOD_PATTERN = "/users/neighborhoods/*";
	public final String NEIGHBORHOOD_VERIFICATION = "/users/neighborhoods/verification";
	public final String USER_NEIGHBORHOOD = "/users/neighborhoods";

	public List<String> getAuthRequiredUrls() {
		return Arrays.asList(NEIGHBORHOOD_VERIFICATION, USER_NEIGHBORHOOD, USER_NEIGHBORHOOD_PATTERN);
	}
}
