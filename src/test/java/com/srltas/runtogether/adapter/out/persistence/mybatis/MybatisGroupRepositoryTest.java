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
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.DeleteGroupDTO;
import com.srltas.runtogether.domain.model.group.Group;

@ExtendWith(MockitoExtension.class)
class MybatisGroupRepositoryTest {

	@Mock
	private MybatisGroupMapper groupMapper;

	@InjectMocks
	private MybatisGroupRepository groupRepository;

	@Test
	@DisplayName("러닝 그룹 생성 SQL 실행 위임을 검증")
	void shouldCallMapperToGroupRepository() {
		Group mockGroup = mock(Group.class);

		when(mockGroup.getNeighborhoodId()).thenReturn(generateNeighborhoodId());
		when(mockGroup.getCreateByUserId()).thenReturn(generateUserId());

		groupRepository.save(mockGroup);
		verify(groupMapper).save(any(AddGroupDTO.class));
	}

	@Test
	@DisplayName("러닝 그룹 삭제 SQL 실행 위임을 검증")
	void test() {
		groupRepository.delete(generateGroupId());
		verify(groupMapper).delete(any(DeleteGroupDTO.class));
	}
}
