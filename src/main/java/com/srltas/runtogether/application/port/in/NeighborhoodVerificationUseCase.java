package com.srltas.runtogether.application.port.in;

public interface NeighborhoodVerificationUseCase {
	NeighborhoodVerificationResult verifyAndRegisterNeighborhood(long userId,
		NeighborhoodVerificationCommand neighborhoodVerificationCommand);
}
