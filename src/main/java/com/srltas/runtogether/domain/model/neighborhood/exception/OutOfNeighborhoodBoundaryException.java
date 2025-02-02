package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

import com.srltas.runtogether.common.exception.RunTogetherException;

public class OutOfNeighborhoodBoundaryException extends RunTogetherException {

	public OutOfNeighborhoodBoundaryException() {
		super(OUT_OF_NEIGHBORHOOD_BOUNDARY);
	}
}
