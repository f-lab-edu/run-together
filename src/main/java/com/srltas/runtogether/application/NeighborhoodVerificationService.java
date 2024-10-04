package com.srltas.runtogether.application;

import static com.srltas.runtogether.application.mappper.LocationMapper.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResponse;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;
import com.srltas.runtogether.domain.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighborhoodVerificationService implements NeighborhoodVerificationUseCase {

	private final NeighborhoodRepository neighborhoodRepository;
	private final UserRepository userRepository;

	@Override
	public NeighborhoodVerificationResponse verifyAndRegisterNeighborhood(long userId,
		NeighborhoodVerificationCommand command) {
		Neighborhood neighborhood = neighborhoodRepository.findById(command.neighborhoodId())
			.orElseThrow(NeighborhoodNotFoundException::new);

		Location currentLocation = neighborhoodVerificationCommandToDomain(command);

		if (neighborhood.isWithinBoundary(currentLocation)) {
			User user = userRepository.findById(userId);
			user.addVerifiedNeighborhood(neighborhood);
			userRepository.save(user);

			return NeighborhoodVerificationResponse.builder()
				.verifyId(UUID.randomUUID().toString())
				.verified(true)
				.verificationTime(LocalDateTime.now().toString())
				.build();
		} else {
			throw new OutOfNeighborhoodBoundaryException();
		}
	}
}
