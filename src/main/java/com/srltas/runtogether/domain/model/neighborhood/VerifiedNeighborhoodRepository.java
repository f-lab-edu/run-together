package com.srltas.runtogether.domain.model.neighborhood;

import com.srltas.runtogether.domain.model.user.User;

public interface VerifiedNeighborhoodRepository {
	void saveVerifiedNeighborhood(User user, Neighborhood neighborhood);
}
