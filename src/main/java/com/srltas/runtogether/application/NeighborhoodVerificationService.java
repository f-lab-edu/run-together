package com.srltas.runtogether.application;

import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.location.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NeighborhoodVerificationService {

	private final NeighborhoodRepository neighborhoodRepository;
	private final UserRepository userRepository;

	public void verifyAndRegisterNeighborhood(User user, Location currentLocation, String neighborhoodName) {
		Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName)
			.orElseThrow(() -> new NeighborhoodNotFoundException(neighborhoodName));

		if (neighborhood.isWithinBoundary(currentLocation)) {
			user.addVerifiedNeighborhood(neighborhood);
			userRepository.save(user);
		} else {
			throw new OutOfNeighborhoodBoundaryException(neighborhoodName);
		}
	}
}
