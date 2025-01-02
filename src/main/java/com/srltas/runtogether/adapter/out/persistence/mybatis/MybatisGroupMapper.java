package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddGroupDTO;

@Mapper
public interface MybatisGroupMapper {
	void save(AddGroupDTO dto);
}
