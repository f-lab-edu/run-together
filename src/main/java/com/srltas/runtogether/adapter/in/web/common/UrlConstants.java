package com.srltas.runtogether.adapter.in.web.common;

import java.util.Arrays;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlConstants {
	public final String NEIGHBORHOOD_VERIFICATION = "/neighborhood/verification";
	public final String USER_NEIGHBORHOOD_REGISTRATION = "/users/neighborhoods";

	public List<String> getAuthRequiredUrls() {
		return Arrays.asList(NEIGHBORHOOD_VERIFICATION, USER_NEIGHBORHOOD_REGISTRATION);
	}
}
