package com.srltas.runtogether.application.port.in;

import lombok.Builder;

@Builder
public record NeighborhoodVerificationResponse(
	String verifyId,
	boolean verified,
	String verificationTime
) {
}
