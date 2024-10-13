package com.srltas.runtogether.domain.model.neighborhood;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Neighborhood {

	private final int id;
	private final String name;
	private final Location location;
	private final double boundaryRadius;

	public boolean isWithinBoundary(Location currentLocation) {
		return location.calculateDistance(currentLocation) <= boundaryRadius;
	}
}
