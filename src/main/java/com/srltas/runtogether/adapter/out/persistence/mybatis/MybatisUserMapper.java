package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.AddUserNeighborhoodDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.UserDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifiedUserNeighborhoodDAO;

@Mapper
public interface MybatisUserMapper {
	Optional<UserDAO> findById(String id);
	void addUserNeighborhood(AddUserNeighborhoodDAO dao);
	void updateVerifiedUserNeighborhood(VerifiedUserNeighborhoodDAO dao);
}
