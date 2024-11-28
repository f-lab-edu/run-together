package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.UserConverter.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.adapter.out.persistence.mybatis.converter.UserConverter;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

	private final MybatisUserMapper mapper;

	@Override
	public Optional<User> findById(String id) {
		return mapper.findById(id).map(UserConverter::toUser);
	}

	@Override
	public void save(User user) {
		// TODO 사용자 저장 구현
	}

	@Override
	public void addUserNeighborhood(String userId, String neighborhoodId) {
		mapper.addUserNeighborhood(toAddUserNeighborhood(userId, neighborhoodId));
	}

	@Override
	public void deleteUserNeighborhood(String userId, String neighborhoodId) {
		mapper.deleteUserNeighborhood(toDeleteUserNeighborhood(userId, neighborhoodId));
	}

	@Override
	public void updateVerifiedUserNeighborhood(String userId, UserNeighborhood userNeighborhood) {
		mapper.updateVerifiedUserNeighborhood(toVerifiedUserNeighborhoodDTO(userId, userNeighborhood));
	}
}
