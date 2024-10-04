package com.srltas.runtogether.adapter.in.web.dto;

public record NeighborhoodVerificationRequest(
	double latitude,
	double longitude,
	int neighborhoodId
) {
}
