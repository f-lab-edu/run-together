package com.srltas.runtogether.application.mappper;

import com.srltas.runtogether.adapter.in.UserNeighborhoodVerifyRequest;
import com.srltas.runtogether.domain.model.user.User;

public class UserNeighborhoodVerifyRequestMapper {

	/**
	 * 유틸리티 클래스의 인스턴스화를 방지하기 위해 private 생성자를 선언합니다.
	 */
	private UserNeighborhoodVerifyRequestMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static User toDomain(UserNeighborhoodVerifyRequest userNeighborhoodVerifyRequest) {
		return new User(userNeighborhoodVerifyRequest.id(), userNeighborhoodVerifyRequest.name());
	}
}
