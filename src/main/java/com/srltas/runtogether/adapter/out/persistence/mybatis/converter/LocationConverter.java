package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.LocationDAO;
import com.srltas.runtogether.domain.model.neighborhood.Location;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationConverter {

	public Location toLocation(LocationDAO dao) {
		return Location.builder()
			.latitude(dao.getLatitude())
			.longitude(dao.getLongitude())
			.build();
	}
}
