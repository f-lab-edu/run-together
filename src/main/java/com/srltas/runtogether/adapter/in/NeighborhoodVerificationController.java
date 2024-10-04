package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.dto.mapper.NeighborhoodVerificationMapper.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.srltas.runtogether.adapter.in.web.dto.NeighborhoodVerificationRequest;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResponse;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NeighborhoodVerificationController {

	private final NeighborhoodVerificationUseCase neighborhoodVerificationUseCase;

	@PostMapping("/neighborhood/verification")
	public ResponseEntity<NeighborhoodVerificationResponse> verifyNeighborhood(
		@RequestBody @Valid NeighborhoodVerificationRequest neighborhoodVerificationRequest,
		@SessionAttribute(name = "login_user_id", required = false) Long userId) {
		NeighborhoodVerificationCommand neighborhoodVerificationCommand = toCommand(neighborhoodVerificationRequest);

		NeighborhoodVerificationResponse response = neighborhoodVerificationUseCase.verifyAndRegisterNeighborhood(
			userId, neighborhoodVerificationCommand);

		return ResponseEntity.ok(response);
	}
}
