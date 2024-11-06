package com.srltas.runtogether.application.port.in;

public interface NeighborhoodVerificationUseCase {
	NeighborhoodVerificationResult verifyAndRegisterNeighborhood(String userId,
		NeighborhoodVerificationCommand neighborhoodVerificationCommand);
}
