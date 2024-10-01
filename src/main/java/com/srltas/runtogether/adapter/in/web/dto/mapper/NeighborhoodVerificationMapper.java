package com.srltas.runtogether.adapter.in.web.dto.mapper;

import com.srltas.runtogether.adapter.in.web.dto.NeighborhoodVerificationRequest;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;

public class NeighborhoodVerificationMapper {

	public static NeighborhoodVerificationCommand toCommand(NeighborhoodVerificationRequest request) {
		return NeighborhoodVerificationCommand.builder()
			.latitude(request.latitude())
			.longitude(request.longitude())
			.neighborhoodId(request.neighborhoodId())
			.build();
	}
}
