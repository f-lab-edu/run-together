package com.srltas.runtogether.domain.model.neighborhood;

import static com.srltas.runtogether.domain.model.neighborhood.LocationUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NeighborhoodTest {

	private Location currentLocation;
	private Location neighborhoodLocation;
	private Neighborhood neighborhood;

	@BeforeEach
	public void setUp() {
		currentLocation = new Location(37.505858, 127.058319);
		neighborhoodLocation = new Location(37.517347, 127.047382);
		neighborhood = new Neighborhood("Gangnam", neighborhoodLocation, 7.0);
	}

	@Test
	@DisplayName("사용자와 동네 기준이 설정된 범위보다 작을 때, 동네 내에 있다고 판단")
	public void testIsWithinBoundary_WhenUserInSideBoundary() {
		try (MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {
			locationUtilsMock.when(() ->
				calculateDistanceBetweenLocations(currentLocation, neighborhoodLocation)).thenReturn(5.0);

			boolean isInSide = neighborhood.isWithinBoundary(currentLocation);

			assertThat(isInSide, is(true));
		}
	}

	@Test
	@DisplayName("사용자와 동네 기준이 설정된 범위보다 클 때, 동네 밖에 있다고 판단")
	public void testIsWithinBoundary_WhenUserOutsideBoundary() {
		try (MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {
			locationUtilsMock.when(() ->
				calculateDistanceBetweenLocations(currentLocation, neighborhoodLocation)).thenReturn(10.0);

			boolean isInSide = neighborhood.isWithinBoundary(currentLocation);

			assertThat(isInSide, is(false));
		}
	}
}