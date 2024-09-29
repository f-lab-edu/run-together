package com.srltas.runtogether.domain.model.location;

import static com.srltas.runtogether.domain.model.location.LocationUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Location {

	private final double latitude;
	private final double longitude;

	public double calculateDistance(Location otherLocation) {
		return calculateDistanceBetweenLocations(otherLocation, this);
	}
}

