package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.AddUserNeighborhoodDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.UserDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.VerifiedUserNeighborhoodDAO;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

	public User toUser(UserDAO dao) {
		return User.builder()
			.id(dao.getId())
			.name(dao.getName())
			.userNeighborhoods(UserNeighborhoodConverter.toUserNeighborhoods(dao.getUserNeighborhoodDAOs()))
			.build();
	}

	public AddUserNeighborhoodDAO toAddUserNeighborhood(String userId, String neighborhoodId) {
		return AddUserNeighborhoodDAO.builder()
			.userId(userId)
			.neighborhoodId(neighborhoodId)
			.verified(false)
			.verifiedAt(null)
			.build();
	}

	public VerifiedUserNeighborhoodDAO toVerifiedUserNeighborhoodDAO(String userId, UserNeighborhood userNeighborhood) {
		return VerifiedUserNeighborhoodDAO.builder()
			.userId(userId)
			.neighborhoodId(userNeighborhood.getNeighborhood().getId())
			.verified(userNeighborhood.isVerified())
			.verifiedAt(userNeighborhood.getVerifiedAt())
			.build();
	}
}
