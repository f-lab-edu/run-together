package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;
import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;

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
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResult;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class NeighborhoodVerificationControllerTest {

	@Mock
	private NeighborhoodVerificationUseCase neighborhoodVerificationUseCase;

	@Mock
	private HttpSession session;

	@Mock
	private NeighborhoodVerificationResult neighborhoodVerificationResult;

	@InjectMocks
	private NeighborhoodVerificationController neighborhoodVerificationController;

	@ParameterizedTest
	@MethodSource("provideRequestsForSuccess")
	@DisplayName("세션에 사용자 정보가 있을 때 동네 인증 성공 여부 확인")
	void testVerifyNeighborhood_Success(NeighborhoodVerificationRequest request, UserSessionDTO userSessionDTO) {
		// given
		when(session.getAttribute(USER_SESSION)).thenReturn(userSessionDTO);
		when(neighborhoodVerificationUseCase.verifyAndRegisterNeighborhood(eq(userSessionDTO.userId()),
			any(NeighborhoodVerificationCommand.class))).thenReturn(neighborhoodVerificationResult);

		// when
		ResponseEntity<NeighborhoodVerificationResult> result = neighborhoodVerificationController
			.verifyNeighborhood(request, session);

		// then
		assertThat(result.getStatusCode(), is(HttpStatus.OK));
		assertThat(result.getBody(), is(neighborhoodVerificationResult));
	}

	static Stream<Arguments> provideRequestsForSuccess() {
		return Stream.of(
			Arguments.of(new NeighborhoodVerificationRequest(37.579617, 126.977041, generateNeighborhoodId()),
				new UserSessionDTO(generateUserId())),
			Arguments.of(new NeighborhoodVerificationRequest(37.556201, 126.972286, generateNeighborhoodId()),
				new UserSessionDTO(generateUserId() + UUID.randomUUID())),
			Arguments.of(new NeighborhoodVerificationRequest(37.497911, 127.027618, generateNeighborhoodId()),
				new UserSessionDTO(generateUserId())));
	}
}
