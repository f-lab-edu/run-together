package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddUserNeighborhoodDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.VerifiedUserNeighborhoodDTO;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

@ExtendWith(MockitoExtension.class)
class MybatisUserRepositoryTest {

	@Mock
	private MybatisUserMapper mybatisUserMapper;

	@InjectMocks
	private MybatisUserRepository mybatisUserRepository;

	@Test
	@DisplayName("동네 인증 정보 업데이트 SQL 실행 위임을 검증")
	void shouldCallMapperToUserRepository() {
		Neighborhood neighborhood = new Neighborhood(generateNeighborhoodId(), "Test Neighborhood",
			new Location(37.505858, 127.058319), 5.0);
		UserNeighborhood userNeighborhood = new UserNeighborhood(neighborhood);

		mybatisUserRepository.updateVerifiedUserNeighborhood(generateUserId(), userNeighborhood);

		verify(mybatisUserMapper).updateVerifiedUserNeighborhood(any(VerifiedUserNeighborhoodDTO.class));
	}

	@Test
	@DisplayName("내 동네 등록 SQL 실행 위임 검증")
	void shouldCallMapperToAddUserNeighborhood() {
		mybatisUserRepository.addUserNeighborhood(generateUserId(), generateNeighborhoodId());
		verify(mybatisUserMapper).addUserNeighborhood(any(AddUserNeighborhoodDTO.class));
	}
}
