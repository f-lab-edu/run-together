package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.NeighborhoodConverter.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.UserNeighborhoodDTO;
import com.srltas.runtogether.domain.model.user.UserNeighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserNeighborhoodConverter {

	public UserNeighborhood toUserNeighborhood(UserNeighborhoodDTO dto) {
		return UserNeighborhood.builder()
			.neighborhood(toNeighborhood(dto.getNeighborhoodDTO()))
			.verified(dto.isVerified())
			.verifiedAt(dto.getVerifiedAt())
			.build();
	}

	public Map<String, UserNeighborhood> toUserNeighborhoods(List<UserNeighborhoodDTO> dtoList) {
		return dtoList.stream()
			.collect(Collectors.toMap(dto -> dto.getNeighborhoodDTO().getId(),
				UserNeighborhoodConverter::toUserNeighborhood));
	}
}
