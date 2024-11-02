package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class NeighborhoodDuplicationException extends NeighborhoodException {

	public NeighborhoodDuplicationException() {
		super(NEIGHBORHOOD_DUPLICATION);
	}
}
