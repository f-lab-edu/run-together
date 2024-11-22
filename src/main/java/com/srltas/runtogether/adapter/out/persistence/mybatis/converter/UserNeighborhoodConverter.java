package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.NeighborhoodConverter.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.UserNeighborhoodDAO;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserNeighborhoodConverter {

	public UserNeighborhood toUserNeighborhood(UserNeighborhoodDAO dao) {
		return UserNeighborhood.builder()
			.neighborhood(toNeighborhood(dao.getNeighborhoodDAO()))
			.verified(dao.isVerified())
			.verifiedAt(dao.getVerifiedAt())
			.build();
	}

	public Map<String, UserNeighborhood> toUserNeighborhoods(List<UserNeighborhoodDAO> daoList) {
		return daoList.stream()
			.collect(Collectors.toMap(dao -> dao.getNeighborhoodDAO().getId(),
				UserNeighborhoodConverter::toUserNeighborhood));
	}
}
