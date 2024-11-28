package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddUserNeighborhoodDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.DeleteUserNeighborhoodDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.UserDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.VerifiedUserNeighborhoodDTO;

@Mapper
public interface MybatisUserMapper {
	Optional<UserDTO> findById(String id);
	void addUserNeighborhood(AddUserNeighborhoodDTO dto);
	void deleteUserNeighborhood(DeleteUserNeighborhoodDTO dto);
	void updateVerifiedUserNeighborhood(VerifiedUserNeighborhoodDTO dto);
}
