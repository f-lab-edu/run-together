package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record AddUserNeighborhoodRequest(
	@NotNull
	String neighborhoodId
) {
}
