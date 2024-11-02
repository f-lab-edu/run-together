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
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;
import com.srltas.runtogether.application.port.in.AddUserNeighborhood;
import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;

import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class UserNeighborhoodControllerTest {

	@Mock
	private AddUserNeighborhood addUserNeighborhood;

	@Mock
	HttpSession session;

	@InjectMocks
	private UserNeighborhoodController userNeighborhoodController;

	@Test
	@DisplayName("내 동네 등록 성공")
	void testAddUserNeighborhoodSuccess() {
		AddUserNeighborhoodRequest request = new AddUserNeighborhoodRequest(generateNeighborhoodId());
		UserSessionDTO userSessionDTO = new UserSessionDTO(generateUserId(), "user1");
		given(session.getAttribute(USER_SESSION)).willReturn(userSessionDTO);

		ResponseEntity<Void> response = userNeighborhoodController.addUserNeighborhood(request, session);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		verify(addUserNeighborhood).addNeighborhood(
			new AddUserNeighborhoodCommand(userSessionDTO.userId(), request.neighborhoodId()));
	}
}
