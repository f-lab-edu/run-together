package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.common.SessionUtils.*;
import static com.srltas.runtogether.adapter.in.web.common.UrlConstants.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srltas.runtogether.adapter.in.web.dto.AddGroupRequest;
import com.srltas.runtogether.adapter.in.web.dto.DeleteGroupRequest;
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.AddGroup;
import com.srltas.runtogether.application.port.in.AddGroupCommand;
import com.srltas.runtogether.application.port.in.DeleteGroup;
import com.srltas.runtogether.application.port.in.DeleteGroupCommand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping(GROUP)
@RestController
@RequiredArgsConstructor
public class GroupController {

	private final AddGroup addGroup;
	private final DeleteGroup deleteGroup;

	@Operation(
		summary = "러닝 그룹 생성",
		description = "내 동네에 러닝 그룹을 생성합니다."
	)
	@ApiResponse(responseCode = "200", description = "러닝 그룹 생성 성공")
	@PostMapping
	public ResponseEntity<Void> create(
		@RequestBody @Valid AddGroupRequest request, HttpSession session) {
		UserSession userSession = getUserSession(session);
		addGroup.create(new AddGroupCommand(
			request.groupName(),
			request.groupDescription(),
			request.neighborhoodId(),
			userSession.userId()));
		return ResponseEntity.ok().build();
	}

	@Operation(
		summary = "러닝 그룹 삭제",
		description = "내 동네에 생성한 러닝 그룹을 삭제합니다."
	)
	@ApiResponse(responseCode = "200", description = "러닝 그룹 삭제 성공")
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody @Valid DeleteGroupRequest request) {
		deleteGroup.delete(new DeleteGroupCommand(request.groupId()));
		return ResponseEntity.ok().build();
	}
}
