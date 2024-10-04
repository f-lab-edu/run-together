package com.srltas.runtogether.adapter.in;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srltas.runtogether.adapter.in.web.dto.NeighborhoodVerificationRequest;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResponse;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;

@ExtendWith(MockitoExtension.class)
class NeighborhoodVerificationControllerTest {

	@Mock
	private NeighborhoodVerificationUseCase neighborhoodVerificationUseCase;

	@InjectMocks
	private NeighborhoodVerificationController neighborhoodVerificationController;

	@ParameterizedTest
	@MethodSource("provideNeighborhoodVerificationRequests")
	@DisplayName("verifyNeighborhood 메서드에 대한 Parameterized 테스트")
	void verifyNeighborhood_ShouldReturnOkResponse(NeighborhoodVerificationRequest request, Long userId) {
		// given
		NeighborhoodVerificationCommand command = new NeighborhoodVerificationCommand(request.latitude(),
			request.longitude(), request.neighborhoodId());
		NeighborhoodVerificationResponse expectedResponse = new NeighborhoodVerificationResponse(
			UUID.randomUUID().toString(), true, LocalDateTime.now().toString());

		given(neighborhoodVerificationUseCase.verifyAndRegisterNeighborhood(userId, command)).willReturn(
			expectedResponse);

		// when
		ResponseEntity<NeighborhoodVerificationResponse> response = neighborhoodVerificationController.verifyNeighborhood(
			request, userId);

		// then
		verify(neighborhoodVerificationUseCase).verifyAndRegisterNeighborhood(userId, command);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(expectedResponse));
	}

	static Stream<Arguments> provideNeighborhoodVerificationRequests() {
		return Stream.of(
			Arguments.of(new NeighborhoodVerificationRequest(37.579617, 126.977041, 1), 100L),
			Arguments.of(new NeighborhoodVerificationRequest(37.556201, 126.972286, 2), 101L),
			Arguments.of(new NeighborhoodVerificationRequest(37.497911, 127.027618, 3), 102L));
	}
}