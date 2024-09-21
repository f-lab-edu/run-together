package com.srltas.runtogether.domain.model.location;

public interface DistanceCalculator {
	double calculateDistanceBetween(Location location1, Location location2);
}
