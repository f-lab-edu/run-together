package com.srltas.runtogether.application;

import com.srltas.runtogether.adapter.in.LocationNeighborhoodVerifyRequest;
import com.srltas.runtogether.adapter.in.UserNeighborhoodVerifyRequest;
import com.srltas.runtogether.application.mappper.LocationNeighborhoodVerifyRequestMapper;
import com.srltas.runtogether.application.mappper.UserNeighborhoodVerifyRequestMapper;
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

	public void verifyAndRegisterNeighborhood(
		UserNeighborhoodVerifyRequest userNeighborhoodVerifyRequest,
		LocationNeighborhoodVerifyRequest locationNeighborhoodVerifyRequest,
		String neighborhoodName) {
		Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName)
			.orElseThrow(() -> new NeighborhoodNotFoundException(neighborhoodName));

		Location currentLocation = LocationNeighborhoodVerifyRequestMapper.toDomain(locationNeighborhoodVerifyRequest);

		if (neighborhood.isWithinBoundary(currentLocation)) {
			User user = UserNeighborhoodVerifyRequestMapper.toDomain(userNeighborhoodVerifyRequest);
			user.addVerifiedNeighborhood(neighborhood);
			userRepository.save(user);
		} else {
			throw new OutOfNeighborhoodBoundaryException(neighborhoodName);
		}
	}
}
