package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.application.port.out.VerifyUserNeighborhoodPort;
import com.srltas.runtogether.application.port.out.dao.VerifyUserNeighborhoodDAO;

@Repository
@Mapper
public interface UserNeighborhoodAdapter extends VerifyUserNeighborhoodPort {

	@Override
	void verify(VerifyUserNeighborhoodDAO dao);
}
