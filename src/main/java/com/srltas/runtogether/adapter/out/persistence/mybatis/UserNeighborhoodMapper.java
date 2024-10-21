package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifyUserNeighborhoodDAO;

@Mapper
public interface UserNeighborhoodMapper {
	void verify(VerifyUserNeighborhoodDAO dao);
}
