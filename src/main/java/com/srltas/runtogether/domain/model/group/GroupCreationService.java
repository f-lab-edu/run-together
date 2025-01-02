package com.srltas.runtogether.domain.model.group;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.srltas.runtogether.application.exception.UserNotFoundException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GroupCreationService {

	private final UserRepository userRepository;

	public Group create(String name, String description, String neighborhoodId, String createByUserId) {
		User user = userRepository.findById(createByUserId).orElseThrow(UserNotFoundException::new);
		if (!user.isVerifiedNeighborhood(neighborhoodId)) {
			throw new NeighborhoodNotRegisteredException();
		}

		String groupId = "rg_" + UUID.randomUUID();
		LocalDateTime createdAt = LocalDateTime.now();
		Set<String> groupMemberIds = Collections.emptySet();

		return new Group(groupId, name, description, neighborhoodId, createByUserId, createdAt, groupMemberIds);
	}
}
