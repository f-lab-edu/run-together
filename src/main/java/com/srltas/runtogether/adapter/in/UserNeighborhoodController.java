package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.common.SessionUtils.*;
import static com.srltas.runtogether.adapter.in.web.common.UrlConstants.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srltas.runtogether.adapter.in.web.dto.AddUserNeighborhoodRequest;
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.AddUserNeighborhood;
import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhood;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhoodCommand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "내 동네 API", description = "내 동네 API")
@RequestMapping(USER_NEIGHBORHOOD_REGISTRATION)
@RestController
@RequiredArgsConstructor
public class UserNeighborhoodController {

	private final AddUserNeighborhood addUserNeighborhood;
	private final DeleteUserNeighborhood deleteUserNeighborhood;

	@Operation(
		summary = "내 동네 등록",
		description = "사용자가 특정 동네를 자신의 동네로 등록합니다."
	)
	@ApiResponse(responseCode = "200", description = "내 동네 등록 성공")
	@PostMapping
	public ResponseEntity<Void> addUserNeighborhood(
		@RequestBody @Valid AddUserNeighborhoodRequest request, HttpSession session) {
		UserSession userSession = getUserSession(session);
		addUserNeighborhood.addNeighborhood(
			new AddUserNeighborhoodCommand(userSession.userId(), request.neighborhoodId()));
		return ResponseEntity.ok().build();
	}

	@Operation(
		summary = "내 동네 삭제",
		description = "사용자의 내 동네를 삭제합니다."
	)
	@ApiResponse(responseCode = "200", description = "내 동네 삭제 성공")
	@DeleteMapping("/{neighborhoodId}")
	public ResponseEntity<Void> deleteUserNeighborhood(
		HttpSession session,
		@PathVariable("neighborhoodId") String neighborhoodId
	) {
		UserSession userSession = getUserSession(session);
		deleteUserNeighborhood.deleteUserNeighborhood(
			new DeleteUserNeighborhoodCommand(userSession.userId(), neighborhoodId));
		return ResponseEntity.ok().build();
	}
}
