package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.common.log.RunTogetherMDC;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@Primary
@RequiredArgsConstructor
public class MybatisUserRepositoryLogProxy implements UserRepository {

	private final UserRepository userRepository;

	@Override
	public Optional<User> findById(String id) {
		long startTime = System.currentTimeMillis();
		Optional<User> user = userRepository.findById(id);
		RunTogetherMDC.putMessage("user_findById_SQL_time", String.valueOf(System.currentTimeMillis() - startTime));
		return user;
	}

	@Override
	public void save(User user) {
	}

	@Override
	public void addUserNeighborhood(String userId, String neighborhoodId) {
		long startTime = System.currentTimeMillis();
		userRepository.addUserNeighborhood(userId, neighborhoodId);
		RunTogetherMDC.putMessage("add_user_SQL_time", String.valueOf(System.currentTimeMillis() - startTime));
	}

	@Override
	public void deleteUserNeighborhood(String userId, String neighborhoodId) {
		long startTime = System.currentTimeMillis();
		userRepository.deleteUserNeighborhood(userId, neighborhoodId);
		RunTogetherMDC.putMessage("delete_user_SQL_time", String.valueOf(System.currentTimeMillis() - startTime));
	}

	@Override
	public void updateVerifiedUserNeighborhood(String userId, UserNeighborhood neighborhood) {
		long startTime = System.currentTimeMillis();
		userRepository.updateVerifiedUserNeighborhood(userId, neighborhood);
		RunTogetherMDC.putMessage("update_verified_user_SQL_time",
			String.valueOf(System.currentTimeMillis() - startTime));
	}
}
