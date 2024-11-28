package com.srltas.runtogether.application.port.in;

public record DeleteUserNeighborhoodCommand(
	String userId,
	String neighborhoodId
) {
}
