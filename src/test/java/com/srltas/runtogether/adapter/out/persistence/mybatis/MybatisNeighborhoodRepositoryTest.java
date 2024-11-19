package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.testutil.TestIdGenerator;

@ExtendWith(MockitoExtension.class)
class MybatisNeighborhoodRepositoryTest {

	@Mock
	private MybatisNeighborhoodRepository mybatisNeighborhoodRepository;

	@InjectMocks
	private MybatisNeighborhoodRepository neighborhoodRepository;

	@Test
	@DisplayName("동네 정보 조회 SQL 실행 위임을 검증")
	void shouldCallMapperToNeighborhoodRepository() {
		mybatisNeighborhoodRepository.findById(TestIdGenerator.generateNeighborhoodId());

		verify(mybatisNeighborhoodRepository).findById(any(String.class));
	}
}