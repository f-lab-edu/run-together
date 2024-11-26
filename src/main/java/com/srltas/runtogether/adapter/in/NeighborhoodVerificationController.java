package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.common.SessionUtils.*;
import static com.srltas.runtogether.adapter.in.web.common.UrlConstants.*;
import static com.srltas.runtogether.adapter.in.web.dto.mapper.NeighborhoodVerificationMapper.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srltas.runtogether.adapter.in.web.dto.NeighborhoodVerificationRequest;
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResult;
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
	@PostMapping(NEIGHBORHOOD_VERIFICATION)
	public ResponseEntity<NeighborhoodVerificationResult> verifyNeighborhood(
		@RequestBody @Valid NeighborhoodVerificationRequest neighborhoodVerificationRequest, HttpSession session) {
		UserSession userSession = getUserSession(session);

		NeighborhoodVerificationCommand neighborhoodVerificationCommand = toCommand(neighborhoodVerificationRequest);

		NeighborhoodVerificationResult result = neighborhoodVerificationUseCase.verifyAndRegisterNeighborhood(
			userSession.userId(), neighborhoodVerificationCommand);

		return ResponseEntity.ok(result);
	}
}
