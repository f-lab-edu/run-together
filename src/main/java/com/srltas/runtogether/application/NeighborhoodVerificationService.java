package com.srltas.runtogether.application;

import static com.srltas.runtogether.application.mappper.LocationMapper.*;

import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;
import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NeighborhoodVerificationService implements NeighborhoodVerificationUseCase {

	private final NeighborhoodRepository neighborhoodRepository;
	private final UserRepository userRepository;

	@Override
	public void verifyAndRegisterNeighborhood(long userId, NeighborhoodVerificationCommand command) {
		Neighborhood neighborhood = neighborhoodRepository.findById(command.neighborhoodId())
			.orElseThrow(NeighborhoodNotFoundException::new);

		Location currentLocation = neighborhoodVerificationCommandToDomain(command);

		if (neighborhood.isWithinBoundary(currentLocation)) {
			User user = userRepository.findById(userId);
			user.addVerifiedNeighborhood(neighborhood);
			userRepository.save(user);
		} else {
			throw new OutOfNeighborhoodBoundaryException();
		}
	}
}
