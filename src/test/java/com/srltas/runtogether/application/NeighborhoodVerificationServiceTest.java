package com.srltas.runtogether.application;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.neighborhood.VerifiedNeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;

class NeighborhoodVerificationServiceTest {

	@Mock
	private NeighborhoodRepository neighborhoodRepository;

	@Mock
	private VerifiedNeighborhoodRepository verifiedNeighborhoodRepository;

	@Mock
	private DistanceCalculator distanceCalculator;

	@InjectMocks
	private NeighborhoodVerificationService neighborhoodVerificationService;

	private User user = new User();
	private String neighborhoodName = "Gangnam";
	private Location currentLocation = new Location(37.505858, 127.058319);
	private Neighborhood neighborhood = new Neighborhood(neighborhoodName, new Location(37.517347, 127.047382), 7.0);

	@BeforeEach
	public void setUp() {
		openMocks(this);
	}

	@Nested
	class WhenNeighborhoodIsFound {

		@BeforeEach
		public void setUp() {
			when(neighborhoodRepository.findByName(neighborhoodName)).thenReturn(Optional.of(neighborhood));
		}

		@Test
		public void testVerifyAndRegisterNeighborhood_WithinBoundary() {
			when(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhood.getLocation()))
				.thenReturn(5.0);

			neighborhoodVerificationService.verifyAndRegisterNeighborhood(user, currentLocation, neighborhoodName);

			verify(verifiedNeighborhoodRepository).saveVerifiedNeighborhood(user, neighborhood);
		}

		@Test
		public void testVerifyAndRegisterNeighborhood_OutsideBoundary() {
			when(distanceCalculator.calculateDistanceBetween(currentLocation, neighborhood.getLocation()))
				.thenReturn(15.0);

			OutOfNeighborhoodBoundaryException exception = assertThrows(OutOfNeighborhoodBoundaryException.class,
				() -> {
					neighborhoodVerificationService.verifyAndRegisterNeighborhood(user, currentLocation, neighborhoodName);
				});

			assertEquals(format("User is outside of the boundary of neighborhood: %s", neighborhoodName), exception.getMessage());
			verify(verifiedNeighborhoodRepository, never()).saveVerifiedNeighborhood(user, neighborhood);
		}
	}

	@Nested
	class WhenNeighborhoodIsNotFound {
		@Test
		public void testVerifyAndRegisterNeighborhood_NeighborhoodNotFound() {
			when(neighborhoodRepository.findByName("Homaesil")).thenReturn(Optional.empty());

			NeighborhoodNotFoundException exception = assertThrows(NeighborhoodNotFoundException.class,
				() -> {
					neighborhoodVerificationService.verifyAndRegisterNeighborhood(user, currentLocation, neighborhoodName);
				});

			assertEquals(format("Neighborhood not found: %s", neighborhoodName), exception.getMessage());
			verify(verifiedNeighborhoodRepository, never()).saveVerifiedNeighborhood(user, neighborhood);
		}
	}
}