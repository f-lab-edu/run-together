package com.srltas.runtogether.domain.model.neighborhood;

import static com.srltas.runtogether.domain.model.neighborhood.LocationUtils.*;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class Location {

	private final double latitude;
	private final double longitude;

	public double calculateDistance(Location otherLocation) {
		return calculateDistanceBetweenLocations(otherLocation, this);
	}
}

