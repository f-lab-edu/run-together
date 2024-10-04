package com.srltas.runtogether.application.port.in;

public interface NeighborhoodVerificationUseCase {
	NeighborhoodVerificationResponse verifyAndRegisterNeighborhood(long userId,
		NeighborhoodVerificationCommand neighborhoodVerificationCommand);
}
