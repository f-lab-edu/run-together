package com.srltas.runtogether.adapter.in.web.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NeighborhoodVerificationRequest(

	@NotNull
	@Range(min = -90, max = 90, message="위도는 -90에서 90 사이여야 합니다.")
	double latitude,

	@NotNull
	@Range(min = -180, max = 180, message="경도는 -180에서 180 사이여야 합니다.")
	double longitude,

	@NotBlank
	@Pattern(regexp = "^nhb_([a-fA-F0-9]{8}(-[a-fA-F0-9]{4}){3}-[a-fA-F0-9]{12})$")
	String neighborhoodId
) {
}
