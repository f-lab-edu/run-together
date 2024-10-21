package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodCommand;
import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodPort;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifyUserNeighborhoodDAO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserNeighborhoodAdapter implements VerifyUserNeighborhoodPort {

	private final UserNeighborhoodMapper userNeighborhoodMapper;

	@Override
	public void verify(VerifyUserNeighborhoodCommand command) {
		VerifyUserNeighborhoodDAO dao = VerifyUserNeighborhoodDAO.builder()
			.userId(command.userId())
			.neighborhoodId(command.neighborhoodId())
			.verified(command.verified())
			.verifiedAt(command.verifiedAt())
			.build();

		userNeighborhoodMapper.verify(dao);
	}
}
