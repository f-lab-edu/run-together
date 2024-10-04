package com.srltas.runtogether.domain.exception;

public class NeighborhoodNotFoundException extends RuntimeException {

	public NeighborhoodNotFoundException() {
		super("Neighborhood not found");
	}
}
