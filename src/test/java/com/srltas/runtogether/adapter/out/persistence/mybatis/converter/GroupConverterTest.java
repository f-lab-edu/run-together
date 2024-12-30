package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddGroupDTO;
import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.user.User;

class GroupConverterTest {

	@Test
	@DisplayName("Group -> AddGroupDTO")
	void shouldConvertGroupToAddGroupDTO() {
		String neighborhoodId = generateNeighborhoodId();
		String userId = generateUserId();
		String groupId = generateGroupId();
		LocalDateTime createTime = LocalDateTime.now();

		Neighborhood mockNeighborhood = mock(Neighborhood.class);
		when(mockNeighborhood.getId()).thenReturn(neighborhoodId);

		User mockUser = mock(User.class);
		when(mockUser.getId()).thenReturn(userId);

		Group group = Group.builder()
			.id(groupId)
			.name("Test Group")
			.description("Test Description")
			.neighborhood(mockNeighborhood)
			.createByUser(mockUser)
			.createdAt(createTime)
			.build();

		AddGroupDTO expectedAddGroupDTO = AddGroupDTO.builder()
			.groupId(groupId)
			.groupName("Test Group")
			.description("Test Description")
			.neighborhoodId(neighborhoodId)
			.createByUserId(userId)
			.createAt(createTime)
			.build();

		AddGroupDTO resultAddGroupDTO = GroupConverter.toAddGroupDTO(group);

		assertThat(resultAddGroupDTO, is(expectedAddGroupDTO));
	}
}
