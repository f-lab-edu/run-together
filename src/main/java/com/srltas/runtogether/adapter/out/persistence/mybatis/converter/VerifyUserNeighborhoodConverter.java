package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifyUserNeighborhoodDAO;
import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VerifyUserNeighborhoodConverter {
	public VerifyUserNeighborhoodDAO toDAO(VerifyUserNeighborhoodCommand command) {
		return VerifyUserNeighborhoodDAO.builder()
			.userId(command.userId())
			.neighborhoodId(command.neighborhoodId())
			.verified(command.verified())
			.verifiedAt(command.verifiedAt())
			.build();
	}
}
