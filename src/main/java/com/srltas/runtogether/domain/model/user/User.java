package com.srltas.runtogether.domain.model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.srltas.runtogether.domain.exception.CommonException;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

	private final Long id;
	private final String name;
	private final List<UserNeighborhood> userNeighborhoods = new ArrayList<>();

	public void addNeighborhood(Neighborhood neighborhood) {
		if (userNeighborhoods.size() >= 2) {
			throw new CommonException("최대 2개의 동네만 등록할 수 있습니다.");
		}

		if (userNeighborhoods.stream()
			.anyMatch(un -> un.getNeighborhood().getId() == neighborhood.getId())) {
			throw new CommonException("이미 등록된 동네입니다.");
		}
		userNeighborhoods.add(new UserNeighborhood(neighborhood));
	}

	public void verifiedNeighborhood(int neighborhoodId) {
		Optional<UserNeighborhood> userNeighborhood = userNeighborhoods.stream()
			.filter(un -> un.getNeighborhood().getId() == neighborhoodId)
			.findFirst();

		if (userNeighborhood.isPresent()) {
			userNeighborhood.get().verifyNeighborhood();
		} else {
			throw new CommonException("해당 동네가 등록되지 않았습니다.");
		}
	}
}
