package com.srltas.runtogether.application;

import static com.srltas.runtogether.application.mappper.LocationMapper.*;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.srltas.runtogether.application.port.in.NeighborhoodVerificationCommand;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationResult;
import com.srltas.runtogether.application.port.in.NeighborhoodVerificationUseCase;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.application.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.domain.model.neighborhood.exception.OutOfNeighborhoodBoundaryException;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;
import com.srltas.runtogether.domain.model.user.UserRepository;
import com.srltas.runtogether.application.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighborhoodVerificationService implements NeighborhoodVerificationUseCase {

	private final NeighborhoodRepository neighborhoodRepository;
	private final UserRepository userRepository;

	@Override
	public NeighborhoodVerificationResult verifyAndRegisterNeighborhood(String userId,
		NeighborhoodVerificationCommand command) {
		Neighborhood neighborhood = neighborhoodRepository.findById(command.neighborhoodId())
			.orElseThrow(NeighborhoodNotFoundException::new);

		Location currentLocation = neighborhoodVerificationCommandToDomain(command);

		if (neighborhood.isWithinBoundary(currentLocation)) {
			User user = userRepository.findById(userId)
					.orElseThrow(UserNotFoundException::new);

			UserNeighborhood userNeighborhood = user.verifiedNeighborhood(neighborhood.getId());
			userRepository.updateVerifiedUserNeighborhood(user.getId(), userNeighborhood);

			return NeighborhoodVerificationResult.builder()
				.verifyId(UUID.randomUUID().toString())
				.verified(true)
				.verificationTime(userNeighborhood.getVerifiedAt().toString())
				.build();
		} else {
			throw new OutOfNeighborhoodBoundaryException();
		}
	}
}
