package com.srltas.runtogether.adapter.in;

import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;
import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srltas.runtogether.adapter.in.web.dto.AddGroupRequest;
import com.srltas.runtogether.adapter.in.web.dto.DeleteGroupRequest;
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.AddGroup;
import com.srltas.runtogether.application.port.in.AddGroupCommand;
import com.srltas.runtogether.application.port.in.DeleteGroup;
import com.srltas.runtogether.application.port.in.DeleteGroupCommand;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

	@Mock
	private AddGroup addGroup;

	@Mock
	private DeleteGroup deleteGroup;

	@Mock
	HttpSession session;

	@InjectMocks
	private GroupController groupController;

	@Test
	@DisplayName("러닝 그룹 생성 성공")
	void testAddGroupSuccess() {
		String userId = generateUserId();
		String neighborhoodId = generateNeighborhoodId();

		AddGroupRequest request = new AddGroupRequest(
			neighborhoodId, "Test Group", "Test Group Description");
		UserSession userSession = new UserSession(userId);
		given(session.getAttribute(USER_SESSION)).willReturn(userSession);

		ResponseEntity<Void> response = groupController.create(request, session);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(addGroup).create(
			new AddGroupCommand("Test Group", "Test Group Description", neighborhoodId, userId));
	}

	@Test
	@DisplayName("러닝 그룹 삭제 성공")
	void testDeleteGroupSuccess() {
		String groupId = generateUserId();

		DeleteGroupRequest request = new DeleteGroupRequest(groupId);

		ResponseEntity<Void> response = groupController.delete(request);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(deleteGroup).delete(new DeleteGroupCommand(groupId));
	}
}
