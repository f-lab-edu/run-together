package com.srltas.runtogether.adapter.out;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.srltas.runtogether.domain.model.location.Location;

class HaversineDistanceCalculatorTest {

	private final HaversineDistanceCalculator haversineDistanceCalculator = new HaversineDistanceCalculator();

	private final Location seoul = new Location(37.566535, 126.977969);
	private final Location busan = new Location(35.179554, 129.075641);

	@Test
	public void testCalculateDistanceBetween_TwoLocations() {
		double seoulBusanDistance = 325.0;

		double distance = haversineDistanceCalculator.calculateDistanceBetween(seoul, busan);

		assertEquals(seoulBusanDistance, distance, 1.0);
	}

	@Test
	public void testCalculateDistanceBetween_SameLocation() {
		double distance = haversineDistanceCalculator.calculateDistanceBetween(seoul, seoul);

		assertEquals(0.0, distance);
	}
}