package com.srltas.runtogether.domain.model.group;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class GroupCreationServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private GroupCreationService groupCreationService;

	private final String USER_ID = generateUserId();
	private final String VALID_NEIGHBORHOOD_ID = generateNeighborhoodId();
	private final String INVALID_NEIGHBORHOOD_ID = generateNeighborhoodId();

	private User mockUser = mock(User.class);

	@Test
	@DisplayName("그룹 생성 성공")
	void testCreateGroupSuccess() {
		when(userRepository.findById(USER_ID)).thenReturn(Optional.of(mockUser));
		when(mockUser.isVerifiedNeighborhood(VALID_NEIGHBORHOOD_ID)).thenReturn(true);

		Group group = groupCreationService.create("Test Group", "Test Desc", VALID_NEIGHBORHOOD_ID, USER_ID);

		assertThat(group).isNotNull();
	}

	@Test
	@DisplayName("그룹 생성 실패(내 동네로 등록하지 않음)")
	void testCreateGroupFailure() {
		when(userRepository.findById(USER_ID)).thenReturn(Optional.of(mockUser));
		when(mockUser.isVerifiedNeighborhood(INVALID_NEIGHBORHOOD_ID)).thenReturn(false);

		assertThatThrownBy(() -> {
			groupCreationService.create("Test Group", "Test Desc", INVALID_NEIGHBORHOOD_ID, USER_ID);
		}).isInstanceOf(NeighborhoodNotRegisteredException.class);
	}
}
