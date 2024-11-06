package com.srltas.runtogether.application.port.in;

public record AddUserNeighborhoodCommand(
	String userId,
	String neighborhoodId
) {
}
