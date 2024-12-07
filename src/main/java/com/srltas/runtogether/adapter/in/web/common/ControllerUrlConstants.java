package com.srltas.runtogether.adapter.in.web.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUrlConstants {
	// 내 동네 URL
	public final String USER_NEIGHBORHOOD = "/users/neighborhoods";
	public final String NEIGHBORHOOD_VERIFICATION = USER_NEIGHBORHOOD + "/verification";
	public final String USER_NEIGHBORHOOD_DELETE = USER_NEIGHBORHOOD + "/{neighborhoodId}";
}
