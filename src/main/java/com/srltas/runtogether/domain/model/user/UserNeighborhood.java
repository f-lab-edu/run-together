package com.srltas.runtogether.domain.model.user;

import java.time.LocalDateTime;

import com.srltas.runtogether.domain.exception.CommonException;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

import lombok.Getter;

@Getter
public class UserNeighborhood {

	private final Neighborhood neighborhood;
	private boolean verified;
	private LocalDateTime verifiedAt;

	public UserNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		this.verified = false;
	}

	public void verifyNeighborhood() {
		if (verified) {
			throw new CommonException("이미 인증된 동네입니다.");
		}
		verified = true;
		verifiedAt = LocalDateTime.now();
	}
}
