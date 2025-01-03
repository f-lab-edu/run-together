package com.srltas.runtogether.application;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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

import com.srltas.runtogether.application.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.application.mappper.LocationMapper;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.LocationUtils;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.neighborhood.exception.OutOfNeighborhoodBoundaryException;
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

	private final String userId = generateUserId();
	private final String neighborhoodId = generateNeighborhoodId();
	private final NeighborhoodVerificationCommand neighborhoodVerificationCommand = new NeighborhoodVerificationCommand(
		1L, 1L, neighborhoodId);

	@Nested
	@DisplayName("동네가 존재하는 경우")
	class WhenNeighborhoodIsFound {
		Neighborhood neighborhood = new Neighborhood(neighborhoodId, "Gangnam", new Location(1L, 1L), 7.0);

		@BeforeEach
		public void setUp() {
			given(neighborhoodRepository.findById(neighborhoodId)).willReturn(Optional.of(neighborhood));
		}

		@Test
		@DisplayName("사용자가 동네 경계 안에 있을 때 동네 인증 성공")
		public void testVerifyAndRegisterNeighborhood_WithinBoundary() {
			User user = mock(User.class);
			UserNeighborhood userNeighborhood = mock(UserNeighborhood.class);
			Location location = mock(Location.class);

			given(user.getId()).willReturn(userId);
			given(user.verifiedNeighborhood(neighborhoodId)).willReturn(userNeighborhood);
			given(userNeighborhood.getVerifiedAt()).willReturn(LocalDateTime.now());
			given(userRepository.findById(userId)).willReturn(Optional.of(user));

			try (MockedStatic<LocationMapper> locationMapperMock = mockStatic(LocationMapper.class);
				MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {

				locationMapperMock.when(
						() -> LocationMapper.neighborhoodVerificationCommandToDomain(neighborhoodVerificationCommand))
					.thenReturn(location);

				locationUtilsMock.when(
						() -> LocationUtils.calculateDistanceBetweenLocations(isA(Location.class), isA(Location.class)))
					.thenReturn(5.0);

				neighborhoodVerificationService.verifyAndRegisterNeighborhood(userId, neighborhoodVerificationCommand);
			}
			then(userRepository).should().updateVerifiedUserNeighborhood(userId, userNeighborhood);
		}

		@Test
		@DisplayName("사용자가 동네 경계 밖에 있을 때 동네 인증 실패")
		public void testVerifyAndRegisterNeighborhood_OutsideBoundary() {
			try (MockedStatic<LocationUtils> locationUtilsMock = mockStatic(LocationUtils.class)) {
				locationUtilsMock.when(
						() -> LocationUtils.calculateDistanceBetweenLocations(isA(Location.class), isA(Location.class)))
					.thenReturn(15.0);

				OutOfNeighborhoodBoundaryException exception = assertThrows(OutOfNeighborhoodBoundaryException.class,
					() -> {
						neighborhoodVerificationService.verifyAndRegisterNeighborhood(userId,
							neighborhoodVerificationCommand);
					});

				assertThat(exception.getErrorCode().getCode(), is(-355));
				assertThat(exception.getMessage(), is("해당 동네 범위를 벗어났습니다."));
			}
			then(userRepository).should(never()).save(isA(User.class));
		}
	}

	@Nested
	@DisplayName("동네가 존재하지 않는 경우")
	class WhenNeighborhoodIsNotFound {
		@Test
		@DisplayName("존재하지 않는 동네를 찾아 예외 발생")
		public void testVerifyAndRegisterNeighborhood_NeighborhoodNotFound() {
			given(neighborhoodRepository.findById(neighborhoodId)).willReturn(Optional.empty());

			NeighborhoodNotFoundException exception = assertThrows(NeighborhoodNotFoundException.class, () -> {
				neighborhoodVerificationService.verifyAndRegisterNeighborhood(userId, neighborhoodVerificationCommand);
			});

			assertThat(exception.getErrorCode().getCode(), is(-301));
			assertThat(exception.getMessage(), is("해당 동네를 찾을 수 없습니다."));
			then(userRepository).should(never()).save(isA(User.class));
		}
	}
}
