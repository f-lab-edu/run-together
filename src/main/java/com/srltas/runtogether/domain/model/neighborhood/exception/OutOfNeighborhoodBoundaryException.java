package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class OutOfNeighborhoodBoundaryException extends NeighborhoodException {

	public OutOfNeighborhoodBoundaryException() {
		super(OUT_OF_NEIGHBORHOOD_BOUNDARY);
	}
}
