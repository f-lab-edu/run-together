package com.srltas.runtogether.application;

import static java.lang.String.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.adapter.in.LocationNeighborhoodVerifyRequest;
import com.srltas.runtogether.adapter.in.UserNeighborhoodVerifyRequest;
import com.srltas.runtogether.application.mappper.LocationNeighborhoodVerifyRequestMapper;
import com.srltas.runtogether.application.mappper.UserNeighborhoodVerifyRequestMapper;
import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class NeighborhoodVerificationServiceTest {

	@Mock
	private NeighborhoodRepository neighborhoodRepository;

	@Mock
	private DistanceCalculator distanceCalculator;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private NeighborhoodVerificationService neighborhoodVerificationService;

	private String neighborhoodName;
	private UserNeighborhoodVerifyRequest userNeighborhoodVerifyRequest;
	private LocationNeighborhoodVerifyRequest locationNeighborhoodVerifyRequest;

	@BeforeEach
	public void setUp() {
		neighborhoodName = "Gangnam";
		userNeighborhoodVerifyRequest = new UserNeighborhoodVerifyRequest(1L, "testUser");
		locationNeighborhoodVerifyRequest = new LocationNeighborhoodVerifyRequest(1L, 1L);
	}

	@Nested
	@DisplayName("동네가 존재하는 경우")
	class WhenNeighborhoodIsFound {

		@BeforeEach
		public void setUp() {
			Neighborhood neighborhood = new Neighborhood(neighborhoodName, new Location(1L, 1L), 7.0, distanceCalculator);
			given(neighborhoodRepository.findByName(neighborhoodName)).willReturn(Optional.of(neighborhood));
		}

		@Test
		@DisplayName("사용자가 동네 경계 안에 있을 때 동네 인증 성공")
		public void testVerifyAndRegisterNeighborhood_WithinBoundary() {
			given(distanceCalculator.calculateDistanceBetween(any(Location.class), any(Location.class)))
				.willReturn(5.0);

			User user = new User(1L, "testUser");
			Location location = new Location(1L, 1L);
			try (MockedStatic<UserNeighborhoodVerifyRequestMapper> userMapperMock =
					 mockStatic(UserNeighborhoodVerifyRequestMapper.class);
				 MockedStatic<LocationNeighborhoodVerifyRequestMapper> locationMapperMock =
					 mockStatic(LocationNeighborhoodVerifyRequestMapper.class)) {

				userMapperMock.when(() ->
					UserNeighborhoodVerifyRequestMapper.toDomain(userNeighborhoodVerifyRequest))
					.thenReturn(user);

				locationMapperMock.when(() ->
					LocationNeighborhoodVerifyRequestMapper.toDomain(locationNeighborhoodVerifyRequest))
					.thenReturn(location);

				neighborhoodVerificationService.verifyAndRegisterNeighborhood(
					userNeighborhoodVerifyRequest,
					locationNeighborhoodVerifyRequest,
					neighborhoodName);
			}
			then(userRepository).should().save(user);
		}

		@Test
		@DisplayName("사용자가 동네 경계 밖에 있을 때 동네 인증 실패")
		public void testVerifyAndRegisterNeighborhood_OutsideBoundary() {
			given(distanceCalculator.calculateDistanceBetween(any(Location.class), any(Location.class)))
				.willReturn(15.0);

			OutOfNeighborhoodBoundaryException exception = assertThrows(OutOfNeighborhoodBoundaryException.class,
				() -> {
					neighborhoodVerificationService.verifyAndRegisterNeighborhood(userNeighborhoodVerifyRequest,
						locationNeighborhoodVerifyRequest, neighborhoodName);
				});

			String expectedExceptionMessage = format("User is outside of the boundary of neighborhood: %s", neighborhoodName);
			assertThat(exception.getMessage(), is(expectedExceptionMessage));
			then(userRepository).should(never()).save(any(User.class));
		}
	}

	@Nested
	@DisplayName("동네가 존재하지 않는 경우")
	class WhenNeighborhoodIsNotFound {
		@Test
		@DisplayName("존재하지 않는 동네를 찾아 예외 발생")
		public void testVerifyAndRegisterNeighborhood_NeighborhoodNotFound() {
			given(neighborhoodRepository.findByName(any())).willReturn(Optional.empty());

			NeighborhoodNotFoundException exception = assertThrows(NeighborhoodNotFoundException.class,
				() -> {
					neighborhoodVerificationService.verifyAndRegisterNeighborhood(userNeighborhoodVerifyRequest,
						locationNeighborhoodVerifyRequest, neighborhoodName);
				});

			String expectedExceptionMessage = format("Neighborhood not found: %s", neighborhoodName);
			assertThat(exception.getMessage(), is(expectedExceptionMessage));
			then(userRepository).should(never()).save(any(User.class));
		}
	}
}
