package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.AddUserNeighborhoodDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifiedUserNeighborhoodDAO;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

	public AddUserNeighborhoodDAO toAddUserNeighborhood(long userId, int neighborhoodId) {
		return AddUserNeighborhoodDAO.builder()
			.userId(userId)
			.neighborhoodId(neighborhoodId)
			.verified(false)
			.verifiedAt(null)
			.build();
	}

	public VerifiedUserNeighborhoodDAO toVerifiedUserNeighborhoodDAO(long userId, UserNeighborhood userNeighborhood) {
		return VerifiedUserNeighborhoodDAO.builder()
			.userId(userId)
			.neighborhoodId(userNeighborhood.getNeighborhood().getId())
			.verified(userNeighborhood.isVerified())
			.verifiedAt(userNeighborhood.getVerifiedAt())
			.build();
	}
}
