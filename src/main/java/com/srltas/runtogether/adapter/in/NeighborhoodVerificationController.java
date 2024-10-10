package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.dto.mapper.NeighborhoodVerificationMapper.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srltas.runtogether.adapter.in.web.dto.NeighborhoodVerificationRequest;
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResponse;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "동네 인증", description = "동네 인증과 관련된 API")
@RestController
@RequiredArgsConstructor
public class NeighborhoodVerificationController {

	private final NeighborhoodVerificationUseCase neighborhoodVerificationUseCase;

	@Operation(
		summary = "동네 인증",
		description = "사용자의 현재 위치를 기반으로 인증받고자 하는 동네 범위 안에 있는지 검증하고, 성공 시 해당 동네를 인증 동네로 등록합니다."
	)
	@ApiResponse(responseCode = "200", description = "동네 인증 성공")
	@PostMapping("/neighborhood/verification")
	public ResponseEntity<NeighborhoodVerificationResponse> verifyNeighborhood(
		@RequestBody @Valid NeighborhoodVerificationRequest neighborhoodVerificationRequest, HttpSession session) {
		UserSessionDTO userSession = (UserSessionDTO)session.getAttribute("USER_SESSION");
		if (userSession == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		NeighborhoodVerificationCommand neighborhoodVerificationCommand = toCommand(neighborhoodVerificationRequest);

		NeighborhoodVerificationResponse response = neighborhoodVerificationUseCase.verifyAndRegisterNeighborhood(
			userSession.userId(), neighborhoodVerificationCommand);

		return ResponseEntity.ok(response);
	}
}
