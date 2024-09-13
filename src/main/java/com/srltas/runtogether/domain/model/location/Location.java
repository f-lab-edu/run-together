package com.srltas.runtogether.domain.model.location;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Location {

	private final double latitude;
	private final double longitude;

	public double calculateDistance(Location otherLocation, DistanceCalculator distanceCalculator) {
		return distanceCalculator.calculateDistanceBetween(otherLocation, this);
	}
}

