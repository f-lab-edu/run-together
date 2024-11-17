package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.NeighborhoodDAO;

@Mapper
public interface MybatisNeighborhoodMapper {
	Optional<NeighborhoodDAO> findById(String id);
}
