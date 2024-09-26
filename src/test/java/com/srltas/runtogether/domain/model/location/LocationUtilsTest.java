package com.srltas.runtogether.domain.model.location;

import static com.srltas.runtogether.domain.model.location.LocationUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationUtilsTest {

	private final Location seoul = new Location(37.566535, 126.977969);
	private final Location busan = new Location(35.179554, 129.075641);

	@Test
	@DisplayName("서로 다른 두 위치 간의 거리를 계산")
	public void testCalculateDistanceBetweenLocations_TowLocation() {
		double seoulToBusanDistance = 325.0;

		double distanc = calculateDistanceBetweenLocations(seoul, busan);

		assertThat(distanc, is(closeTo(seoulToBusanDistance, 1.0)));
	}

	@Test
	@DisplayName("같은 위치 간의 거리 계산")
	public void testCalculateDistanceBetweenLocations_SameLocation() {
		double distance = calculateDistanceBetweenLocations(seoul, seoul);

		assertThat(distance, is(0.0));
	}
}