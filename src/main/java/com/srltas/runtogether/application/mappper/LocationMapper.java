package com.srltas.runtogether.application.mappper;

import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.domain.model.neighborhood.Location;

public class LocationMapper {

	/**
	 * 유틸리티 클래스의 인스턴스화를 방지하기 위해 private 생성자를 선언합니다.
	 */
	private LocationMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static Location neighborhoodVerificationCommandToDomain(NeighborhoodVerificationCommand command) {
		return new Location(command.latitude(), command.longitude());
	}
}
