package com.srltas.runtogether.domain.model.neighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class LocationUtils {

	private static final double EARTH_RADIUS_KM = 6371;

	public static double calculateDistanceBetweenLocations(Location location1, Location location2) {
		double latitude = Math.toRadians(location1.getLatitude() - location2.getLatitude());
		double longitude = Math.toRadians(location1.getLongitude() - location2.getLongitude());

		double haversineIntermediate = Math.sin(latitude / 2) * Math.sin(latitude / 2)
			+ Math.cos(Math.toRadians(location2.getLatitude())) * Math.cos(Math.toRadians(location1.getLatitude()))
			* Math.sin(longitude / 2) * Math.sin(longitude / 2);

		double centralAngle = 2 * Math.atan2(Math.sqrt(haversineIntermediate), Math.sqrt(1 - haversineIntermediate));

		return EARTH_RADIUS_KM * centralAngle;
	}
}
