package com.srltas.runtogether.domain.model.user;

import java.time.LocalDateTime;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.exception.AlreadyVerifiedNeighborhoodException;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserNeighborhood {

	private final Neighborhood neighborhood;
	private boolean verified;
	private LocalDateTime verifiedAt;

	public UserNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		this.verified = false;
	}

	public UserNeighborhood(Neighborhood neighborhood, boolean verified, LocalDateTime verifiedAt) {
		this.neighborhood = neighborhood;
		this.verified = verified;
		this.verifiedAt = verifiedAt;
	}

	public void verifyNeighborhood() {
		if (verified) {
			throw new AlreadyVerifiedNeighborhoodException();
		}
		verified = true;
		verifiedAt = LocalDateTime.now();
	}
}
