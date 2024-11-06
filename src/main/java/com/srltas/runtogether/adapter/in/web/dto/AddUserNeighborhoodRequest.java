package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record AddUserNeighborhoodRequest(
	@NotBlank
	String neighborhoodId
) {
}
