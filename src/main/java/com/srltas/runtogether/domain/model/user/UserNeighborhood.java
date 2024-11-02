package com.srltas.runtogether.domain.model.user;

import java.time.LocalDateTime;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.exception.AlreadyVerifiedNeighborhoodException;

import lombok.Getter;

@Getter
public class UserNeighborhood {

	private final Neighborhood neighborhood;
	private boolean verified;
	private LocalDateTime verifiedAt;

	public UserNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		this.verified = false;
	}

	public void verifyNeighborhood() {
		if (verified) {
			throw new AlreadyVerifiedNeighborhoodException();
		}
		verified = true;
		verifiedAt = LocalDateTime.now();
	}
}
