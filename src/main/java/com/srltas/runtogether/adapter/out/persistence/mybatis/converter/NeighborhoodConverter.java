package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.LocationConverter.*;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.NeighborhoodDTO;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NeighborhoodConverter {

	public Neighborhood toNeighborhood(NeighborhoodDTO dto) {
		return Neighborhood.builder()
			.id(dto.getId())
			.name(dto.getName())
			.location(toLocation(dto.getLocationDTO()))
			.boundaryRadius(dto.getBoundaryRadius())
			.build();
	}
}
