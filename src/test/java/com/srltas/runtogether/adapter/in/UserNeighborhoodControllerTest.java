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

import com.srltas.runtogether.adapter.in.web.dto.AddUserNeighborhoodRequest;
import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.application.port.in.AddUserNeighborhood;
import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhood;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhoodCommand;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class UserNeighborhoodControllerTest {

	@Mock
	private AddUserNeighborhood addUserNeighborhood;

	@Mock
	private DeleteUserNeighborhood deleteUserNeighborhood;

	@Mock
	HttpSession session;

	@InjectMocks
	private UserNeighborhoodController userNeighborhoodController;

	@Test
	@DisplayName("내 동네 등록 성공")
	void testAddUserNeighborhoodSuccess() {
		AddUserNeighborhoodRequest request = new AddUserNeighborhoodRequest(generateNeighborhoodId());
		UserSession userSession = new UserSession(generateUserId());
		given(session.getAttribute(USER_SESSION)).willReturn(userSession);

		ResponseEntity<Void> response = userNeighborhoodController.addUserNeighborhood(request, session);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(addUserNeighborhood).addNeighborhood(
			new AddUserNeighborhoodCommand(userSession.userId(), request.neighborhoodId()));
	}

	@Test
	@DisplayName("내 동내 삭제 성공")
	void testDeleteUserNeighborhoodSuccess() {
		String neighborhoodId = generateNeighborhoodId();
		UserSession userSession = new UserSession(generateUserId());
		given(session.getAttribute(USER_SESSION)).willReturn(userSession);

		ResponseEntity<Void> response = userNeighborhoodController.deleteUserNeighborhood(session, neighborhoodId);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(deleteUserNeighborhood).deleteUserNeighborhood(
			new DeleteUserNeighborhoodCommand(userSession.userId(), neighborhoodId));
	}
}
