package com.srltas.runtogether.application.mappper;

import com.srltas.runtogether.adapter.in.LocationNeighborhoodVerifyRequest;
import com.srltas.runtogether.domain.model.neighborhood.Location;

public class LocationNeighborhoodVerifyRequestMapper {

	/**
	 * 유틸리티 클래스의 인스턴스화를 방지하기 위해 private 생성자를 선언합니다.
	 */
	private LocationNeighborhoodVerifyRequestMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static Location toDomain(LocationNeighborhoodVerifyRequest locationNeighborhoodVerifyRequest) {
		return new Location(locationNeighborhoodVerifyRequest.latitude(), locationNeighborhoodVerifyRequest.longitude());
	}
}
