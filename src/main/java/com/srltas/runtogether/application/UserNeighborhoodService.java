package com.srltas.runtogether.application;

import org.springframework.stereotype.Service;

import com.srltas.runtogether.application.port.in.AddUserNeighborhood;
import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;
import com.srltas.runtogether.application.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;
import com.srltas.runtogether.application.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserNeighborhoodService implements AddUserNeighborhood {

	private final UserRepository userRepository;
	private final NeighborhoodRepository neighborhoodRepository;

	@Override
	public void addNeighborhood(AddUserNeighborhoodCommand command) {
		User user = userRepository.findById(command.userId())
			.orElseThrow(UserNotFoundException::new);
		Neighborhood neighborhood = neighborhoodRepository.findById(command.neighborhoodId())
			.orElseThrow(NeighborhoodNotFoundException::new);

		user.addNeighborhood(neighborhood);
		userRepository.addUserNeighborhood(user.getId(), neighborhood.getId());
	}
}
