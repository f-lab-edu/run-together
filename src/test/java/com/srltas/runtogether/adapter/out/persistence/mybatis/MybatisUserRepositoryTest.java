package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifiedUserNeighborhoodDAO;
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
		long userId = 1L;
		Neighborhood neighborhood = new Neighborhood(1, "Test Neighborhood",
			new Location(37.505858, 127.058319), 5.0);
		UserNeighborhood userNeighborhood = new UserNeighborhood(neighborhood);

		mybatisUserRepository.updateVerifiedUserNeighborhood(userId, userNeighborhood);

		verify(mybatisUserMapper).updateVerifiedUserNeighborhood(any(VerifiedUserNeighborhoodDAO.class));
	}
}