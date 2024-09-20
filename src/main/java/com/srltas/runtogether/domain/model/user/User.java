package com.srltas.runtogether.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {

	private final Long id;
	private final String name;
	private List<VerifiedNeighborhood> verifiedNeighborhoods = new ArrayList<>();

	public void addVerifiedNeighborhood(Neighborhood neighborhood) {
		this.verifiedNeighborhoods.add(new VerifiedNeighborhood(neighborhood));
	}
}
