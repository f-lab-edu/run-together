package com.srltas.runtogether.application.port.in;

public record AddUserNeighborhoodCommand(
	long userId,
	int neighborhoodId
) {
}
