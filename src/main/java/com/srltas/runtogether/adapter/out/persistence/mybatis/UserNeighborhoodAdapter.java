package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.VerifyUserNeighborhoodConverter.*;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodCommand;
import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodPort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserNeighborhoodAdapter implements VerifyUserNeighborhoodPort {

	private final UserNeighborhoodMapper userNeighborhoodMapper;

	@Override
	public void verify(VerifyUserNeighborhoodCommand command) {
		userNeighborhoodMapper.verify(toDAO(command));
	}
}
