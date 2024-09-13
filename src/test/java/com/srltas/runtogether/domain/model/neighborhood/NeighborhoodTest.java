package com.srltas.runtogether.domain.model.neighborhood;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;

class NeighborhoodTest {

	@Mock
	private DistanceCalculator distanceCalculator;

	private Location currentLocation;
	private Location neighborhoodLocation;
	private Neighborhood neighborhood;

	@BeforeEach
	public void setUp() {
		openMocks(this);

		currentLocation = new Location(37.505858, 127.058319);
		neighborhoodLocation = new Location(37.517347, 127.047382);
		neighborhood = new Neighborhood("Gangnam", neighborhoodLocation, 7.0);
	}

	@Test
	public void testIsWithinBoundary_WhenUserInSideBoundary() {
		when(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhoodLocation)).thenReturn(5.0);

		boolean isInSide = neighborhood.isWithinBoundary(currentLocation, distanceCalculator);

		assertTrue(isInSide);
		verify(distanceCalculator).calculateDistanceBetween(currentLocation, neighborhoodLocation);
	}

	@Test
	public void testIsWithinBoundary_WhenUserOutsideBoundary() {
		when(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhoodLocation)).thenReturn(10.0);

		boolean isInSide = neighborhood.isWithinBoundary(currentLocation, distanceCalculator);

		assertFalse(isInSide);
		verify(distanceCalculator).calculateDistanceBetween(currentLocation, neighborhoodLocation);
	}
}