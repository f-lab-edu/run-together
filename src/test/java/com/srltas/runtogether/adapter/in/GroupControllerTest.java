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
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.AddGroup;
import com.srltas.runtogether.application.port.in.AddGroupCommand;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

	@Mock
	private AddGroup addGroup;

	@Mock
	HttpSession session;

	@InjectMocks
	private GroupController groupController;

	@Test
	@DisplayName("그룹 등록 성공")
	void testAddGroupSuccess() {
		String userId = generateUserId();
		String neighborhoodId = generateNeighborhoodId();

		AddGroupRequest request = new AddGroupRequest(
			neighborhoodId,
			"Test Group",
			"Test Group Description");
		UserSession userSession = new UserSession(userId);
		given(session.getAttribute(USER_SESSION)).willReturn(userSession);

		ResponseEntity<Void> response = groupController.create(request, session);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(addGroup).create(
			new AddGroupCommand(
				"Test Group",
				"Test Group Description",
				neighborhoodId,
				userId));
	}
}
