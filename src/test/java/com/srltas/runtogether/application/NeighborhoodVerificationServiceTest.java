package com.srltas.runtogether.application;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
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

import com.srltas.runtogether.application.mappper.LocationMapper;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.LocationUtils;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;
import com.srltas.runtogether.domain.model.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class NeighborhoodVerificationServiceTest {

	@Mock
	private NeighborhoodRepository neighborhoodRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private NeighborhoodVerificationService neighborhoodVerificationService;

	private String neighborhoodName;
	private NeighborhoodVerificationCommand neighborhoodVerificationCommand;

	@BeforeEach
	public void setUp() {
		neighborhoodName = "Gangnam";
		neighborhoodVerificationCommand = new NeighborhoodVerificationCommand(1L, 1L, 1);
	}

	@Nested
	@DisplayName("동네가 존재하는 경우")
	class WhenNeighborhoodIsFound {

		Neighborhood neighborhood = new Neighborhood(1, neighborhoodName, new Location(1L, 1L), 7.0);

		@BeforeEach
		public void setUp() {
			given(neighborhoodRepository.findById(1)).willReturn(Optional.of(neighborhood));
		}

		@Test
		@DisplayName("사용자가 동네 경계 안에 있을 때 동네 인증 성공")
		public void testVerifyAndRegisterNeighborhood_WithinBoundary() {
			User user = mock(User.class);
			UserNeighborhood userNeighborhood = mock(UserNeighborhood.class);
			Location location = mock(Location.class);

			given(user.getId()).willReturn(1L);
			given(user.verifiedNeighborhood(neighborhood.getId())).willReturn(userNeighborhood);
			given(userNeighborhood.getVerifiedAt()).willReturn(LocalDateTime.now());
			given(userRepository.findById(1L)).willReturn(Optional.of(user));

			try (MockedStatic<LocationMapper> locationMapperMock = mockStatic(LocationMapper.class);
				 MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {

				locationMapperMock.when(() ->
						LocationMapper.neighborhoodVerificationCommandToDomain(neighborhoodVerificationCommand))
					.thenReturn(location);

				locationUtilsMock.when(() ->
						LocationUtils.calculateDistanceBetweenLocations(any(Location.class), any(Location.class)))
					.thenReturn(5.0);

				neighborhoodVerificationService.verifyAndRegisterNeighborhood(1L, neighborhoodVerificationCommand);
			}
			then(userRepository).should().updateVerifiedUserNeighborhood(1L, userNeighborhood);
		}

		@Test
		@DisplayName("사용자가 동네 경계 밖에 있을 때 동네 인증 실패")
		public void testVerifyAndRegisterNeighborhood_OutsideBoundary() {
			try (MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {
				locationUtilsMock.when(() ->
						LocationUtils.calculateDistanceBetweenLocations(any(Location.class), any(Location.class)))
					.thenReturn(15.0);

				OutOfNeighborhoodBoundaryException exception = assertThrows(OutOfNeighborhoodBoundaryException.class,
					() -> {
						neighborhoodVerificationService.verifyAndRegisterNeighborhood(1L,
							neighborhoodVerificationCommand);
					});

				assertThat(exception.getMessage(), is("User is outside of the boundary of neighborhood"));
			}
			then(userRepository).should(never()).save(any(User.class));
		}
	}

	@Nested
	@DisplayName("동네가 존재하지 않는 경우")
	class WhenNeighborhoodIsNotFound {
		@Test
		@DisplayName("존재하지 않는 동네를 찾아 예외 발생")
		public void testVerifyAndRegisterNeighborhood_NeighborhoodNotFound() {
			given(neighborhoodRepository.findById(anyInt())).willReturn(Optional.empty());

			NeighborhoodNotFoundException exception = assertThrows(NeighborhoodNotFoundException.class,
				() -> {
					neighborhoodVerificationService.verifyAndRegisterNeighborhood(1L, neighborhoodVerificationCommand);
				});

			assertThat(exception.getMessage(), is("Neighborhood not found"));
			then(userRepository).should(never()).save(any(User.class));
		}
	}
}
