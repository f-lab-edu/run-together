package com.srltas.runtogether.adapter.in.web.common;

import java.util.Arrays;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FilterUrlConstants {
	public final String ALL_URL = "/*";

	// 내 동네 URL
	public final String USER_NEIGHBORHOOD = "/users/neighborhoods";
	public final String USER_NEIGHBORHOOD_WILDCARD = USER_NEIGHBORHOOD + "/*";
	public final String USER_NEIGHBORHOOD_VERIFICATION = USER_NEIGHBORHOOD + "/verification";

	public List<String> getAuthRequiredUrls() {
		return Arrays.asList(USER_NEIGHBORHOOD, USER_NEIGHBORHOOD_WILDCARD);
	}
}
