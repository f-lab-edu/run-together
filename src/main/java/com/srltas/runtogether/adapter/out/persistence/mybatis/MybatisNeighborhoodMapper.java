package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.NeighborhoodDTO;

@Mapper
public interface MybatisNeighborhoodMapper {
	Optional<NeighborhoodDTO> findById(String id);
}
