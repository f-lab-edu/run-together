package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

import com.srltas.runtogether.common.exception.EntityNotFoundException;

public class NeighborhoodNotFoundException extends EntityNotFoundException {

	public NeighborhoodNotFoundException() {
		super(NEIGHBORHOOD_NOT_FOUND);
	}
}
