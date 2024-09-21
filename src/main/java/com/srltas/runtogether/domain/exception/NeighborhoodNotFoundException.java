package com.srltas.runtogether.domain.exception;

public class NeighborhoodNotFoundException extends RuntimeException {

	public NeighborhoodNotFoundException(String neighborhoodName) {
		super(String.format("Neighborhood not found: %s", neighborhoodName));
	}
}
