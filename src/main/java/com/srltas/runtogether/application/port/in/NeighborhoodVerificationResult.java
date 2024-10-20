package com.srltas.runtogether.application.port.in;

import lombok.Builder;

@Builder
public record NeighborhoodVerificationResult(
	String verifyId,
	boolean verified,
	String verificationTime
) {
}
