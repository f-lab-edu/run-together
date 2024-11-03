package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddUserNeighborhoodRequest(
	@NotBlank
	@Pattern(regexp = "^usr_([a-fA-F0-9]{8}(-[a-fA-F0-9]{4}){3}-[a-fA-F0-9]{12})$")
	String neighborhoodId
) {
}
