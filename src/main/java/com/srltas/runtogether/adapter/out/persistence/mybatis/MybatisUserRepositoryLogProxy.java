package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
		return Optional.empty();
	}

	@Override
	public void save(User user) {	}

	@Override
	public void addUserNeighborhood(String userId, String neighborhoodId) {
		long startTime = System.currentTimeMillis();
		userRepository.addUserNeighborhood(userId, neighborhoodId);
		MDC.put("DatabaseFetchTime", String.valueOf(System.currentTimeMillis() - startTime));
	}

	@Override
	public void updateVerifiedUserNeighborhood(String userId, UserNeighborhood neighborhood) {
		long startTime = System.currentTimeMillis();
		userRepository.updateVerifiedUserNeighborhood(userId, neighborhood);
		MDC.put("databaseFetchTime", String.valueOf(System.currentTimeMillis() - startTime));
	}
}
