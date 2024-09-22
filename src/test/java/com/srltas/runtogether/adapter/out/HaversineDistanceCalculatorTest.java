package com.srltas.runtogether.adapter.out;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.srltas.runtogether.domain.model.location.Location;

class HaversineDistanceCalculatorTest {

	private final HaversineDistanceCalculator haversineDistanceCalculator = new HaversineDistanceCalculator();

	private final Location seoul = new Location(37.566535, 126.977969);
	private final Location busan = new Location(35.179554, 129.075641);

	@Test
	@DisplayName("서로 다른 두 위치 간의 거리를 계산")
	public void testCalculateDistanceBetween_TwoLocations() {
		double seoulBusanDistance = 325.0;

		double distance = haversineDistanceCalculator.calculateDistanceBetween(seoul, busan);

		assertThat(distance, is(closeTo(seoulBusanDistance, 1.0)));
	}

	@Test
	@DisplayName("같은 위치 간의 거리를 계산")
	public void testCalculateDistanceBetween_SameLocation() {
		double distance = haversineDistanceCalculator.calculateDistanceBetween(seoul, seoul);

		assertThat(distance, is(0.0));
	}
}