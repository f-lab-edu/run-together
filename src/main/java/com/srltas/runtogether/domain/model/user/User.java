package com.srltas.runtogether.domain.model.user;

import static java.util.Objects.*;

import java.util.HashMap;
import java.util.Map;

import com.srltas.runtogether.domain.exception.CommonException;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

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
			throw new CommonException("최대 2개의 동네만 등록할 수 있습니다.");
		}

		if (userNeighborhoods.containsKey(neighborhood.getId())) {
			throw new CommonException("이미 등록된 동네입니다.");
		}

		userNeighborhoods.put(neighborhood.getId(), new UserNeighborhood(neighborhood));
	}

	public UserNeighborhood verifiedNeighborhood(String neighborhoodId) {
		UserNeighborhood userNeighborhood = userNeighborhoods.get(neighborhoodId);

		if (isNull(userNeighborhood)) {
			throw new CommonException("해당 동네가 등록되지 않았습니다.");
		}

		userNeighborhood.verifyNeighborhood();
		return userNeighborhood;
	}
}
