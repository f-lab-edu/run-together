package com.srltas.runtogether.domain.model.user;

import java.util.Map;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodDuplicationException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodLimitExceededException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class User {

	private final String id;
	private final String name;
	private final Map<String, UserNeighborhood> userNeighborhoods;

	public void addNeighborhood(Neighborhood neighborhood) {
		if (userNeighborhoods.size() >= 2) {
			throw new NeighborhoodLimitExceededException();
		}

		if (userNeighborhoods.containsKey(neighborhood.getId())) {
			throw new NeighborhoodDuplicationException();
		}

		userNeighborhoods.put(neighborhood.getId(), new UserNeighborhood(neighborhood));
	}

	public void deleteNeighborhood(String neighborhoodId) {
		if (!userNeighborhoods.containsKey(neighborhoodId)) {
			throw new NeighborhoodNotRegisteredException();
		}

		userNeighborhoods.remove(neighborhoodId);
	}

	public UserNeighborhood verifiedNeighborhood(String neighborhoodId) {
		UserNeighborhood userNeighborhood = userNeighborhoods.computeIfAbsent(neighborhoodId, id -> {
			throw new NeighborhoodNotRegisteredException();
		});

		userNeighborhood.verifyNeighborhood();
		return userNeighborhood;
	}

	public boolean isVerifiedNeighborhood(String neighborhoodId) {
		return userNeighborhoods.containsKey(neighborhoodId);
	}
}
