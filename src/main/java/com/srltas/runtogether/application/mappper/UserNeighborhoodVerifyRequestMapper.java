package com.srltas.runtogether.application.mappper;

import com.srltas.runtogether.adapter.in.UserNeighborhoodVerifyRequest;
import com.srltas.runtogether.domain.model.user.User;

public class UserNeighborhoodVerifyRequestMapper {

	public static User toDomain(UserNeighborhoodVerifyRequest userNeighborhoodVerifyRequest) {
		return new User(userNeighborhoodVerifyRequest.id(), userNeighborhoodVerifyRequest.name());
	}
}
