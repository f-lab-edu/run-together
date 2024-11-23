package com.srltas.runtogether.domain.model.neighborhood;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class Neighborhood {

	private final String id;
	private final String name;
	private final Location location;
	private final double boundaryRadius;

	public boolean isWithinBoundary(Location currentLocation) {
		return location.calculateDistance(currentLocation) <= boundaryRadius;
	}
}
