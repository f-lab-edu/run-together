package com.srltas.runtogether.adapter.out.persistence.mybatis.dao;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNeighborhoodDAO
{
	private NeighborhoodDAO neighborhoodDAO;
	private boolean verified;
	private LocalDateTime verifiedAt;
}
