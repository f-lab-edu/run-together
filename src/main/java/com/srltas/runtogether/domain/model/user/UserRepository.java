package com.srltas.runtogether.domain.model.user;

import java.util.Optional;

public interface UserRepository {
	Optional<User> findById(String id);
	void save(User user);
	void addUserNeighborhood(String userId, String neighborhoodId);
	void deleteUserNeighborhood(String userId, String neighborhoodId);
	void updateVerifiedUserNeighborhood(String userId, UserNeighborhood neighborhood);
}
