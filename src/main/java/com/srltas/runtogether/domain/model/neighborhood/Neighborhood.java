package com.srltas.runtogether.domain.model.neighborhood;

import com.srltas.runtogether.domain.model.location.DistanceCalculator;
import com.srltas.runtogether.domain.model.location.Location;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Neighborhood {

	@Getter
	private final String name;

	@Getter
	private final Location location;

	private final double boundaryRadius;

	public boolean isWithinBoundary(Location currentLocation, DistanceCalculator distanceCalculator) {
		double distance = location.calculateDistance(currentLocation, distanceCalculator);
		return distance <= boundaryRadius;
	}
}
