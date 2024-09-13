package com.srltas.runtogether.adapter.out;

import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;

public class HaversineDistanceCalculator implements DistanceCalculator {

	private static final double EARTH_RADIUS_KM = 6371;

	@Override
	public double calculateDistanceBetween(Location location1, Location location2) {
		double latitude = Math.toRadians(location1.getLatitude() - location2.getLatitude());
		double longitude = Math.toRadians(location1.getLongitude() - location2.getLongitude());

		double a = Math.sin(latitude / 2) * Math.sin(latitude / 2)
			+ Math.cos(Math.toRadians(location2.getLatitude())) * Math.cos(Math.toRadians(location1.getLatitude()))
			* Math.sin(longitude / 2) * Math.sin(longitude / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS_KM * c;
	}
}
