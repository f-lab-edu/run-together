package com.srltas.runtogether.domain.model.neighborhood;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;

@ExtendWith(MockitoExtension.class)
class NeighborhoodTest {

	@Mock
	private DistanceCalculator distanceCalculator;

	private Location currentLocation;
	private Location neighborhoodLocation;
	private Neighborhood neighborhood;

	@BeforeEach
	public void setUp() {
		currentLocation = new Location(37.505858, 127.058319);
		neighborhoodLocation = new Location(37.517347, 127.047382);
		neighborhood = new Neighborhood("Gangnam", neighborhoodLocation, 7.0, distanceCalculator);
	}

	@Test
	@DisplayName("사용자와 동네 기준이 설정된 범위보다 작을 때, 동네 내에 있다고 판단")
	public void testIsWithinBoundary_WhenUserInSideBoundary() {
		given(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhoodLocation)).willReturn(5.0);

		boolean isInSide = neighborhood.isWithinBoundary(currentLocation);

		assertThat(isInSide, is(true));
		then(distanceCalculator).should().calculateDistanceBetween(currentLocation, neighborhoodLocation);
	}

	@Test
	@DisplayName("사용자와 동네 기준이 설정된 범위보다 클 때, 동네 밖에 있다고 판단")
	public void testIsWithinBoundary_WhenUserOutsideBoundary() {
		when(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhoodLocation)).thenReturn(10.0);

		boolean isInSide = neighborhood.isWithinBoundary(currentLocation);

		assertThat(isInSide, is(false));
		then(distanceCalculator).should().calculateDistanceBetween(currentLocation, neighborhoodLocation);
	}
}