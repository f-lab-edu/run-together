package com.srltas.runtogether.domain.exception;

public class OutOfNeighborhoodBoundaryException extends RuntimeException {

	public OutOfNeighborhoodBoundaryException(String neighborhoodName) {
		super(String.format("User is outside of the boundary of neighborhood: %s", neighborhoodName));
	}
}
