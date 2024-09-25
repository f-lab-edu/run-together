package com.srltas.runtogether.application.mappper;

import com.srltas.runtogether.adapter.in.LocationNeighborhoodVerifyRequest;
import com.srltas.runtogether.domain.model.location.Location;

public class LocationNeighborhoodVerifyRequestMapper {

	public static Location toDomain(LocationNeighborhoodVerifyRequest locationNeighborhoodVerifyRequest) {
		return new Location(locationNeighborhoodVerifyRequest.latitude(), locationNeighborhoodVerifyRequest.longitude());
	}
}
