package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.UserConverter.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

	private final MybatisUserMapper mybatisUserMapper;

	@Override
	public Optional<User> findById(String id) {
		// TODO 사용자 조회 구현
		return Optional.empty();
	}

	@Override
	public void save(User user) {
		// TODO 사용자 저장 구현
	}

	@Override
	public void addUserNeighborhood(String userId, String neighborhoodId) {
		mybatisUserMapper.addUserNeighborhood(toAddUserNeighborhood(userId, neighborhoodId));
	}

	@Override
	public void updateVerifiedUserNeighborhood(String userId, UserNeighborhood userNeighborhood) {
		mybatisUserMapper.updateVerifiedUserNeighborhood(toVerifiedUserNeighborhoodDAO(userId, userNeighborhood));
	}
}
