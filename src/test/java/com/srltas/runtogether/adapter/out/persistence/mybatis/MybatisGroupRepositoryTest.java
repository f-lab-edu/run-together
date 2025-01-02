package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddGroupDTO;
import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.user.User;

@ExtendWith(MockitoExtension.class)
class MybatisGroupRepositoryTest {

	@Mock
	private MybatisGroupMapper groupMapper;

	@InjectMocks
	private MybatisGroupRepository groupRepository;

	@Test
	@DisplayName("러닝 그룹 조회 SQL 실행 위임을 검증")
	void shouldCallMapperToGroupRepository() {
		Group mockGroup = mock(Group.class);
		Neighborhood mockNeighborhood = mock(Neighborhood.class);
		User mockUser = mock(User.class);

		when(mockGroup.getNeighborhood()).thenReturn(mockNeighborhood);
		when(mockGroup.getCreateByUser()).thenReturn(mockUser);

		when(mockNeighborhood.getId()).thenReturn(generateNeighborhoodId());
		when(mockUser.getId()).thenReturn(generateUserId());

		groupRepository.save(mockGroup);
		verify(groupMapper).save(any(AddGroupDTO.class));
	}
}
