package com.srltas.runtogether.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.application.port.in.AddGroupCommand;
import com.srltas.runtogether.application.port.in.DeleteGroupCommand;
import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.group.GroupCreationService;
import com.srltas.runtogether.domain.model.group.GroupRepository;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;
import com.srltas.runtogether.testutil.TestIdGenerator;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

	@Mock
	private GroupCreationService groupCreationService;

	@Mock
	private GroupRepository groupRepository;

	@InjectMocks
	private GroupService groupService;

	private AddGroupCommand command = new AddGroupCommand(
		"Test Group",
		"Description",
		"nh_123",
		"user_123");

	@Test
	@DisplayName("정상적으로 Group을 생성하고 저장")
	void create_whenValidCommand_thenSavesGroup() {
		// given
		Group createGroup = new Group(
			TestIdGenerator.generateGroupId(),
			command.name(),
			command.description(),
			command.neighborhoodId(),
			command.createByUserId(),
			LocalDateTime.now(),
			Collections.emptySet()
		);

		when(groupCreationService.create(
			command.name(),
			command.description(),
			command.neighborhoodId(),
			command.createByUserId()
		)).thenReturn(createGroup);

		//when
		groupService.create(command);

		// then
		verify(groupCreationService).create(
			command.name(),
			command.description(),
			command.neighborhoodId(),
			command.createByUserId()
		);

		verify(groupRepository).save(createGroup);
	}

	@Test
	@DisplayName("그룹 생성 과정에서 예외가 발생하면, repository.save는 호출되지 않음")
	void create_whenGroupCreationThrowsException_thenSaveNotCalled() {
		// given
		when(groupCreationService.create(anyString(), anyString(), anyString(), anyString()))
			.thenThrow(new NeighborhoodNotRegisteredException());

		// when & then
		assertThatThrownBy(() -> groupService.create(command))
			.isInstanceOf(NeighborhoodNotRegisteredException.class);

		verifyNoMoreInteractions(groupRepository);
	}

	@Test
	@DisplayName("그룹 삭제 성공")
	void delete_whenValidCommand_thenDeletesGroup() {
		String groupId = TestIdGenerator.generateGroupId();
		DeleteGroupCommand command = new DeleteGroupCommand(groupId);

		groupService.delete(command);

		verify(groupRepository).delete(groupId);
	}
}
