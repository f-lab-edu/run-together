package com.srltas.runtogether.domain.model.user;

import static java.util.Objects.*;

import java.util.HashMap;
import java.util.Map;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodDuplicationException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodLimitExceededException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

	private final String id;
	private final String name;
	private final Map<String, UserNeighborhood> userNeighborhoods = new HashMap<>();

	public void addNeighborhood(Neighborhood neighborhood) {
		if (userNeighborhoods.size() >= 2) {
			throw new NeighborhoodLimitExceededException();
		}

		if (userNeighborhoods.containsKey(neighborhood.getId())) {
			throw new NeighborhoodDuplicationException();
		}

		userNeighborhoods.put(neighborhood.getId(), new UserNeighborhood(neighborhood));
	}

	public UserNeighborhood verifiedNeighborhood(String neighborhoodId) {
		UserNeighborhood userNeighborhood = userNeighborhoods.get(neighborhoodId);

		if (isNull(userNeighborhood)) {
			throw new NeighborhoodNotRegisteredException();
		}

		userNeighborhood.verifyNeighborhood();
		return userNeighborhood;
	}
}
