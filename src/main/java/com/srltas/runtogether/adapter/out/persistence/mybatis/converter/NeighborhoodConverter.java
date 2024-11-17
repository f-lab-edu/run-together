package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.LocationConverter.*;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.NeighborhoodDAO;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NeighborhoodConverter {

	public Neighborhood toNeighborhood(NeighborhoodDAO dao) {
		return Neighborhood.builder()
			.id(dao.getId())
			.name(dao.getName())
			.location(toLocation(dao.getLocationDAO()))
			.boundaryRadius(dao.getBoundaryRadius())
			.build();
	}
}
