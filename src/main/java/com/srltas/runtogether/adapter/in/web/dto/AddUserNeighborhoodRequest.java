package com.srltas.runtogether.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AddUserNeighborhoodRequest(
	@NotNull
	@PositiveOrZero(message = "동네 ID는 0 이상의 값이어야 합니다.")
	Integer neighborhoodId
) {
}
