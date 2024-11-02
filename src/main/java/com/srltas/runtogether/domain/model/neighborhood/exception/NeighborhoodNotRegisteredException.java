package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class NeighborhoodNotRegisteredException extends NeighborhoodException {

	public NeighborhoodNotRegisteredException() {
		super(NEIGHBORHOOD_NOT_REGISTERED);
	}
}
