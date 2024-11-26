package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddUserNeighborhoodDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.UserDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.VerifiedUserNeighborhoodDTO;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

	public User toUser(UserDTO dto) {
		return User.builder()
			.id(dto.getId())
			.name(dto.getName())
			.userNeighborhoods(UserNeighborhoodConverter.toUserNeighborhoods(dto.getUserNeighborhoodDTOs()))
			.build();
	}

	public AddUserNeighborhoodDTO toAddUserNeighborhood(String userId, String neighborhoodId) {
		return AddUserNeighborhoodDTO.builder()
			.userId(userId)
			.neighborhoodId(neighborhoodId)
			.verified(false)
			.verifiedAt(null)
			.build();
	}

	public VerifiedUserNeighborhoodDTO toVerifiedUserNeighborhoodDTO(String userId, UserNeighborhood userNeighborhood) {
		return VerifiedUserNeighborhoodDTO.builder()
			.userId(userId)
			.neighborhoodId(userNeighborhood.getNeighborhood().getId())
			.verified(userNeighborhood.isVerified())
			.verifiedAt(userNeighborhood.getVerifiedAt())
			.build();
	}
}
