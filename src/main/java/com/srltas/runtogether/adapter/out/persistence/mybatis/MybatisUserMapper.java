package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.AddUserNeighborhoodDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifiedUserNeighborhoodDAO;

@Mapper
public interface MybatisUserMapper {
	void addUserNeighborhood(AddUserNeighborhoodDAO dao);
	void updateVerifiedUserNeighborhood(VerifiedUserNeighborhoodDAO dao);
}
