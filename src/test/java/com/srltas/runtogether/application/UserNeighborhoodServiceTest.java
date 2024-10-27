package com.srltas.runtogether.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserNeighborhoodServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private NeighborhoodRepository neighborhoodRepository;

	@InjectMocks
	private UserNeighborhoodService userNeighborhoodService;

	@Test
	@DisplayName("사용자가 동네를 성공적으로 등록하는 경우")
	void testAddNeighborhoodSuccess() {
		// given
		long userId = 1L;
		int neighborhoodId = 101;

		User user = new User(userId, "testUser");
		Neighborhood neighborhood = new Neighborhood(neighborhoodId, "Gangnam",
			new Location(37.505858, 127.058319), 10.0);

		AddUserNeighborhoodCommand command = new AddUserNeighborhoodCommand(userId, neighborhoodId);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(neighborhoodRepository.findById(neighborhoodId)).thenReturn(Optional.of(neighborhood));

		// when
		userNeighborhoodService.addNeighborhood(command);

		// then
		assertTrue(user.getUserNeighborhoods().containsKey(neighborhoodId));
		verify(userRepository).addUserNeighborhood(userId, neighborhoodId);
	}
}
