package com.srltas.runtogether.domain.model.user;

import java.time.LocalDateTime;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

public class VerifiedNeighborhood {

	private final Neighborhood neighborhood;
	private final LocalDateTime verifiedAt;

	public VerifiedNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		this.verifiedAt = LocalDateTime.now();
	}
}
