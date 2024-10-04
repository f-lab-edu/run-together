package com.srltas.runtogether.domain.exception;

public class OutOfNeighborhoodBoundaryException extends RuntimeException {

	public OutOfNeighborhoodBoundaryException() {
		super("User is outside of the boundary of neighborhood");
	}
}
