package com.srltas.runtogether.domain.model.neighborhood;

import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Neighborhood {

	@Getter
	private final String name;

	@Getter
	private final Location location;

	private final double boundaryRadius;

	private final DistanceCalculator distanceCalculator;

	public boolean isWithinBoundary(Location currentLocation) {
		double distance = location.calculateDistance(currentLocation, distanceCalculator);
		return distance <= boundaryRadius;
	}
}
