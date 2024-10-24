package com.srltas.runtogether.domain.model.user;

import java.util.Optional;

public interface UserRepository {
	Optional<User> findById(long id);
	void save(User user);
	void updateVerifiedUserNeighborhood(long userId, UserNeighborhood neighborhood);
}
