package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserNeighborhoodDTO
{
	private NeighborhoodDTO neighborhoodDTO;
	private boolean verified;
	private LocalDateTime verifiedAt;
}
