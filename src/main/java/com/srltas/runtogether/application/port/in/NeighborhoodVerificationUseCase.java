package com.srltas.runtogether.application.port.in;

public interface NeighborhoodVerificationUseCase {
	void verifyAndRegisterNeighborhood(long userId, NeighborhoodVerificationCommand neighborhoodVerificationCommand);
}
